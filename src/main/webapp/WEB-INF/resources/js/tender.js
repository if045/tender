var TENDER_VIEW_URL = "/tenderView";
var FORMAT_DATE = 'yyyy/mm/dd';
var locs = '';
var Units = [];
var Proposals = [];
var highLightedElement;

$(document).ready(function () {
    $('#endDate').datepicker({
        format: FORMAT_DATE,
        startDate: '-3d'
    });

    $('.sortable').on("click", function(){
        console.log(this);
            $( ".sortable" ).each(function( i ) {
                if ($( ".sortable" )!=this.getAttribute("sortable")) {
                    $( ".sortable" ).removeClass('glyphicon-chevron-up');
                    $( ".sortable" ).removeClass('glyphicon-chevron-down');
                }
            });
        if (this.getAttribute("class").indexOf("asc") > -1){
            var sortValue = "desc";
            $(this).addClass('desc').removeClass('asc');
            $(this).addClass('glyphicon').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
        } else{
            var sortValue = "asc";
            $(this).addClass('asc').removeClass('desc');
            $(this).addClass('glyphicon').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
        }
        var nameValue = this.getAttribute("name");

        var str = location.href;
        var tender1 = str.split(TENDER_VIEW_URL);
        var tender = tender1[tender1.length - 1];
        $.getJSON('/tenders' + tender + '/units?direction='+ sortValue +'&field='+ nameValue, buildUnitTable);
    });

    showUnit();
    showProposals();
    showInfo();
    $("#status, #date_to, #description").change(function() {
        enableSaveTenderButton();
    });
    $("[rel='tooltip']").tooltip();
    $("#locations").hover(
        function() {
            if (locs.toString().split(',').length>2) {
                var html = '';
                html += '<div id="hover_locations" class="tooltip fade bottom in" style="top: 75px; left: 100px; display: block;">' +
                    '<div class="tooltip-arrow"></div>' +
                    '<div class="tooltip-inner">' + locs + '</div>' +
                    '</div>'
                $(this).append($(html));
            }
        }, function() {
            $( this ).find( $('#hover_locations') ).remove();
        }
    );
});
function showProposals() {
    var URL = location.href;
    var tenderURI = URL.split(TENDER_VIEW_URL);
    tenderId = tenderURI[tenderURI.length - 1];
    $.getJSON('/tenders/'+tenderId+'/proposals', function (data) {
        Proposals = data.slice(0);
        showProposalsTable(Proposals);
    });
}



function buildUnitTable(data){
    var html = '';
    var len = data.length;
    Units = data.slice(0);
    for (var i = 0; i < len; i++) {
        if (data[i].itemType == 'P')
            var itemTypes = 'Product'
        else if (data[i].itemType == 'S')
            var itemTypes = 'Service';
        var buttonStyle = (data[i].haveDeals) ? " btn-success" : " btn-info";
        html += '<tr class="js-unitRow" id="unit_row_' + data[i].id + '"><td align="center">' + '<input type="checkbox" onchange="showSpecificProposals()" id="ch_box_' + i + '">' +
            '<input  hidden="" type="text" value="' + data[i].id +'" id="unit_id_' + i + '"/></td>' +
            '<td>' + data[i].unitName + '</td>' +
            '<td>' + itemTypes + '</td>' +
            '<td>' + data[i].categoryName + '</td>' +
            '<td>' + data[i].quantity + ' ' + data[i].measurementName + '</td>' +
            '<td>' + data[i].numberOfBids + '</td>' +
            '<td class="js-sellerPrice" id="seller_price_' + data[i].id + '">' + 0.00 + '</td>' +
            '<td align="center">' +
                '<input  hidden="" type="text" id="selected_proposal_id_' + data[i].id + '"/>' +
                '<input  hidden="" type="text" id="selected_bid_id_' + data[i].id + '"/>' +
                '<button type="submit" id="unit_deal_button_'+ data[i].id +'" class="btn' + buttonStyle +'" onclick="createUnitDeal(' + data[i].id + ')" disabled>Deal</button>' +
            '</td></tr>';
    }
    $('#unitsTable').html(html);
    if(!(highLightedElement == undefined)) {
        showUnitSellerPrice(highLightedElement.getAttribute("propId"));
    }
};

function showUnit() {
    var str = location.href;
    var tender1 = str.split(TENDER_VIEW_URL);
    var tender = tender1[tender1.length - 1];
    $.getJSON('/tenders' + tender + '/units?direction=asc&field=item.name', buildUnitTable);
}

function createProposalDeal(proposalId) {
    $.ajax({
        url: TENDERS_URL + "/" + tenderId.substring(1) + PROPOSALS_URL + "/" + proposalId + DEALS_URL,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',

        success: function(data) {
            showProposals();
            showUnit();
            $("#success_create_deal").modal('show');
        },
        error: function() {
            alert("Some error!");
        }
    });
}

function createUnitDeal(unitId) {
    var proposalId = $("#selected_proposal_id_" + unitId).val();
    var bidId = $("#selected_bid_id_" + unitId).val();
    alert("Here!");
    $.ajax({
        url: TENDERS_URL + "/" + tenderId.substring(1) + PROPOSALS_URL + "/" + proposalId + BIDS_URL + "/" + bidId + DEALS_URL,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',

        success: function(data) {
            showProposals();
            showUnit();
            $("#success_create_deal").modal('show');
        },
        error: function() {
            alert("Some error!");
        }
    });
}

function showInfo(){
    var str = location.href;
    var tender1 = str.split(TENDER_VIEW_URL);
    var tender = tender1[tender1.length - 1];
    $.getJSON(TENDERS_URL+'/'+tender.substring(1), function(data){
        var titleHtml = '';
        titleHtml += '<h4>' + data.title + '</h4>';
        $('#tender_title').html(titleHtml);
        var statusHtml = '';
        statusHtml += '<option value="'+ data.status + '">' + data.status + '</option>';
        statusHtml += '<option value="Close">Close</option>';
        $('#status').html(statusHtml);
        document.getElementById('date_to').value = dateConverter(data.endDate);
        var priceHtml ='';
        priceHtml+= '<p class="form-control-static col-md-7">' + data.suitablePrice + '</p>';
        $('#suitablePrice').html(priceHtml);
        if (data.description!=null){
            document.getElementById('description').value = data.description;
        }else{
            document.getElementById('description').value = "";
        }
        var loc =new Array();
        var len = data.locations.toString().split(',').length;
        loc =data.locations.toString().split(',');
        var locations = '';
        if (len>2){
            for (var i=0;i<2;i++){
                locations += loc[i]+','
            }
            locations = locations.substring(0,locations.lastIndexOf(','))+ '...';
        }else{
            locations = data.locations.toString();
        }
        var locationsHtml ='';
        locationsHtml += '<p class="form-control-static col-md-9" id="locations" rel="tooltip">' + locations + '</p>';
        $('#locations').html(locationsHtml);
        locs = data.locations;
    });
}

function dateConverter(timestamp){
    var date = new Date(timestamp);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();

    if(day <= 9) day = "0" + day;
    if(month <= 9) month = "0" + month;

    return year + "/" + month + "/" + day;
}

function saveTenderAfterUpdate(){
    var srt = location.href;
    var tender1 = srt.split(TENDER_VIEW_URL);
    var tender = tender1[tender1.length - 1];
    var str = '';
    str += "statusName=" + $('#status').val()+"&endDate="+$('#date_to').val();
    if ($('#description').val()!=""){
        str +="&description="+$('#description').val();
    }
    $.ajax({
        url: TENDERS_URL + '/' + tender.substring(1) + "?"  + str,
        type: "PUT",
        success: function(data){
        },
        error: function(){
        }
    })
}

function enableSaveTenderButton(){
    $("#save_tender_button").removeAttr("disabled");
}

function showSpecificProposals() {
    var selectedUnitsId = [];
    for (var i = 0; i < Units.length; i++) if ($("#ch_box_" + i).is(":checked")) {
        selectedUnitsId.push($("#unit_id_" + i).val());
    }
    resetRowsStyle();
    cleanSellerPriceColumn();
    showProposalsTable(Proposals, selectedUnitsId);
}

function showProposalsTable(propsArray, unitsArray) {
    var html = '';
    var len = propsArray.length;
    if(len > 0) {
        document.getElementById("no_proposals_message").setAttribute('hidden','true');
        for (var i = 0; i < len; i++) {
            var buttonStyle = (propsArray[i].haveDeals) ? " btn-success" : " btn-info";
            if (unitsArray == null || checkBids(propsArray[i].bidDtos, unitsArray)) {
                html += '<tr class="js-proposalRow" id="proposal_row_' + propsArray[i].id + '">' +
                    '<td class="js-highlightUnits" propId="' + propsArray[i].id +'">' + propsArray[i].fullName + '</td>' +
                    '<td class="js-highlightUnits" propId="' + propsArray[i].id +'">' + propsArray[i].numberOfBids + '</td>' +
                    '<td class="js-highlightUnits" propId="' + propsArray[i].id +'">' + propsArray[i].totalBidsPrice + '</td>' +
                    //This button should only be available to the customer
                    '<td align="center">' +
                        '<button type="submit" class="btn' + buttonStyle + '" onclick="createProposalDeal(' + propsArray[i].id + ')">Deal</button>' +
                    '</td>' +
                    '</tr>';
            }
        }
        $('#proposals').html(html);
    } else {
        document.getElementById("head_proposals").setAttribute('hidden','true');
    }
    $('.js-highlightUnits').click(function(){
        highLightedElement = this;
        showUnitSellerPrice(highLightedElement.getAttribute("propId"));
    });
}

function checkBids(bidsArray, unitsArray) {
    var proposalsUnits = [];
    for (var i = 0; i < bidsArray.length; i++) {        //write unitId, that covers proposal
        proposalsUnits.push(bidsArray[i].unitId);
    }
    proposalsUnits = $.unique(proposalsUnits);

    var count = 0;
    var unitsCount = unitsArray.length;
    for (var j = 0; j < unitsCount; j++) {               //test proposal the capacity of all selected units
        if (proposalsUnits.indexOf(parseInt(unitsArray[j])) > -1) {
            count++;
        }
    }

    if (count == unitsCount) {
        return true;
    } else {
        return false;
    }


}

function showUnitSellerPrice(proposalId) {
    resetRowsStyle();
    cleanSellerPriceColumn();
    disabledUnitDealButton();

    $("#proposal_row_" + proposalId).addClass('info');
    var proposal;
    for (var i = 0; i < Proposals.length; i++) {        //find custom proposal
        if (Proposals[i].id == proposalId) {
            proposal = Proposals[i];
            break;
        }
    }

    var bidsArr = proposal.bidDtos;
    for (var i = 0; i < bidsArr.length; i++) {
        $("#unit_row_" + bidsArr[i].unitId).addClass('info');                   //highlight unit rows witch included proposal
        $("#seller_price_" + bidsArr[i].unitId).html(bidsArr[i].price);         //show seller price of unit
        $("#selected_proposal_id_" + bidsArr[i].unitId).attr('value', proposalId);       //set selected proposal id
        $("#selected_bid_id_" + bidsArr[i].unitId).attr('value', bidsArr[i].bidId);      //set selected proposal bid id
        $("#unit_deal_button_" + bidsArr[i].unitId).removeAttr("disabled");
    }

    var str = '';
    if (proposal.description != null) {
        str += proposal.description + "\n";
    }
    if (proposal.discountCurrency != null) {
        str += "Discount currency: " + proposal.discountCurrency + "\n";
    }
    if (proposal.discountPercentage != null) {
        str += "Discount percentage: " + proposal.discountPercentage;
    }

    $("#proposalDescription").html(str);
}

function cleanSellerPriceColumn() {
    $('.js-sellerPrice').html('0.00');
}

function resetRowsStyle() {
    $('.js-unitRow').removeClass('info');
    $('.js-proposalRow').removeClass('info');
}

function checkAll() {
    var status = $("#ch_box_head").is(":checked");
    for (var i = 0; i < Units.length; i++) {
        $("#ch_box_" + i).prop("checked", status);
    }

    showProposals();
}

function disabledUnitDealButton(){
    for (var i = 0; i < Units.length; i++) {
        $("#unit_deal_button_" + Units[i].id).attr('disabled', 'disabled');
    }
}

