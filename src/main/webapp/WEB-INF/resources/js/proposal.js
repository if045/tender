var unitsQuantity;
var tenderId;

$(document).ready(function() {
    $("#radio_buttons").change(function() {
        $('#allowance').val('') ;
    });
});

function showUnits(id) {
    tenderId = id;
    $.getJSON(TENDERS_URL + id + '/units', function(data) {
        var html='';
        var len = data.length;
        unitsQuantity = data.length;

        for (var i = 0; i < len; i++) {
            html += '<tr>' +
                '<td>' + data[i].unitName + '<input  hidden="" type="text" value="' + data[i].id +'" id="unit_id_' + i + '"/>' + '</td>' +
                '<td>' + data[i].itemType + '</td>' +
                '<td>' + data[i].categoryName + '</td>' +
                '<td>' + data[i].quantity + ' ' + data[i].measurementName + '</td>' +
                '<td>'+ '<input type="text" class="form-control" onchange="enableCreateButton()" id="' + i + '"/>' +'</td>' +
            '</tr>';
        }
        $('#tenderUnits').html(html);
    });
}

function enableAllowance() {
    if ($("#make_allowance").is(":checked")){
        $("#optRadSum").removeAttr("disabled");
        $("#optRadPercent").removeAttr("disabled");
        $("#allowance").removeAttr("disabled");
    } else {
        $("#optRadSum").attr("disabled", "disabled");
        $("#optRadPercent").attr("disabled", "disabled");
        $("#allowance").attr("disabled", "disabled");
    }
}

function enableCreateButton() {
    var flag = false;
    for (var i = 0; i < unitsQuantity; i++) {
        var value = $.trim($("#" + i).val());
        if (value.length != 0) {
            flag = true;
            break;
        }
    }
    if (flag) {
        $("#create_proposal_button").removeAttr("disabled");
    } else {
        $("#create_proposal_button").attr("disabled", "disabled");
    }
}

function createProposal() {
    var str = '';
    str += '{"bids" : [';

    for (var i = 0; i < unitsQuantity; i++) {
        var value = $.trim($("#" + i).val());
        if (value.length != 0) {
           str += '{"unitId":' + '\"' + $('#unit_id_' + i).val() + '\"' + ', "price":' + '\"' + $("#" + i).val() + '\"}, ';
        }
    }
    str = str.substr(0,str.lastIndexOf(','));
    str += ']';
    if ($("#make_allowance").is(":checked")) {
        str += ', ';
        if ($("#optRadSum").is(":checked")) {
            str += '"discountCurrency"';
        } else if ($("#optRadPercent").is(":checked")) {
            str += '"discountPercentage"';
        }
        str += ': ' + '\"' + $("#allowance").val() + '\"';
    }
    str += ', "description": ' + '\"' + $('#proposal_description').val()+'\"' +
            ', "tenderId": ' + '\"' + tenderId + '\"}';

    var newJson = $.parseJSON(str);
    $.ajax({
        url: TENDERS_URL + tenderId + "/proposals",
        type: "POST",
        data: JSON.stringify(newJson),
        dataType: 'json',
        contentType: 'application/json',

        success: function(data) {
        },
        error: function() {}
    });
    var delay=100;
    setTimeout(function(){
        cleanCreateProposalFields();
    },delay);
    $('#createProposalWindow').modal('hide');

}

function cleanCreateProposalFields() {
    for (var i = 0; i < unitsQuantity; i++) {
        $("#" + i).val("");
    }
    $("#proposal_description").val("");
    $("#make_allowance").attr("checked", false);
    $("#optRadSum").attr("disabled", "disabled");
    $("#optRadPercent").attr("disabled", "disabled");
    $("#allowance").val("");
    showTenders();
}


