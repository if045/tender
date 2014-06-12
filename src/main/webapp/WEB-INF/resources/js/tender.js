var TENDER_VIEW_URL = "/tenderView";
var FORMAT_DATE = 'yyyy/mm/dd';
var locs = '';
var Units = new Array();
var Proposals = new Array();

$(document).ready(function () {
    $('#endDate').datepicker({
        format: FORMAT_DATE,
        startDate: '-3d'
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
    var tenderId = tenderURI[tenderURI.length - 1];
    $.getJSON('/tenders/'+tenderId+'/proposals', function (data) {
        var html;
        var len = data.length;
        Proposals = data;
        if(len > 0) {
            document.getElementById("no_proposals_message").setAttribute('hidden','true');
            for (var i = 0; i < len; i++) {
                html += '<tr>' +
                    '<td align="center">' + data[i].fullName + '</td>' +
                    '<td align="center">' + data[i].numberOfBids + '</td>' +
                    '<td align="center">' + data[i].totalBidsPrice + '</td>' +
                    '<td align="center"><button type="submit" class="btn btn-default" disabled>Deal</button></td>' +
                    '</tr>';
            }
            $('#proposals').html(html);
        } else {
            document.getElementById("head_proposals").setAttribute('hidden','true');
        }
    });
}
function showUnit() {
    var str = location.href;
    var tender1 = str.split(TENDER_VIEW_URL);
    var tender = tender1[tender1.length - 1];
    $.getJSON('/tenders/' + tender + '/units', function (data) {
        var html = '';
        var len = data.length;
        Units = data;
        for (var i = 0; i < len; i++) {
            if (data[i].itemType == 'P')
                var itemTypes = 'Product'
            else if (data[i].itemType == 'S')
                var itemTypes = 'Service'
            html += '<tr><td>' + '<input type="checkbox" onchange="" id="ch_box_' + i + '"></td>' +
                '<td>' + data[i].unitName + '</td>' +
                '<td>' + itemTypes + '</td>' +
                '<td>' + data[i].categoryName + '</td>' +
                '<td>' + data[i].quantity + ' ' + data[i].measurementName + '</td>' +
                '<td>' + data[i].numberOfBids + '</td>' +
                '<td>' + data[i].price + '</td>' +
                '<td align="center">' + '<button type="submit" class="btn btn-default" disabled>Deal</button>' + '</td></tr>';
        }
        $('#unitsTable').html(html);
    });
}

function showInfo(){
    var str = location.href;
    var tender1 = str.split(TENDER_VIEW_URL);
    var tender = tender1[tender1.length - 1];
    $.getJSON(TENDERS_URL+'/'+tender.substring(1), function(data){
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
    var selectedUnits = new Array();
    for (var i = 0; i < Units.length; i++) {

    }
}

