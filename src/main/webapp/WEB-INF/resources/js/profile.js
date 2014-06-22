$(document).ready(function() {
    $('#birth_date').datepicker({
        format: 'yyyy/mm/dd'
    });

    $("#populate_categories_edit_dropdown").select2({
        placeholder: "Select new trade categories"

    });

    $("#populate_locations_edit_dropdown").select2({
        placeholder: "Select new trade locations"
    });

    mapDropdownData(CATEGORIES_URL, '#populate_locations_edit_dropdown');
    mapDropdownData(LOCATIONS_URL, "#populate_categories_edit_dropdown");

    $.getJSON(NEW_DEALS_URL, function(data){
        var newDealsNumber = data.dealsNumber;

        if(newDealsNumber > 0) {
            $('.new_deal_notification').html("&nbsp;+"+newDealsNumber);
        } else {
            $('.new_deal_notification').html("");
        }
    });
});

function mapDropdownData(url, id) {
    $.getJSON(url, {
        ajax : 'true'
    }, function(data){
        var html;
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">'
                + data[i].name + '</option>';
        }
        $(id).html(html);
    });
}