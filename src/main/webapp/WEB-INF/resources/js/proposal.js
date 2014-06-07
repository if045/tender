var unitsQuantity;

$(document).ready(function() {
    $("#radio_buttons").change(function() {
        $('#allowance').val('') ;
    });
});

function showUnits(id) {
    $.getJSON(TENDERS_URL + id + '/units', function(data) {
        var html='';
        var len = data.length;
        unitsQuantity = data.length;

        for (var i = 0; i < len; i++) {
            html += '<tr>' +
                '<td>' + data[i].unitName + '</td>' +
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



