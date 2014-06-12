$(document).ready(function() {
    $('#birth_date').datepicker({
        format: 'yyyy/mm/dd'
    });

    $("#populate_roles_dropdown").select2({
        placeholder: "Select role"
    });

    $("#populate_categories_dropdown").select2({
        placeholder: "Select categories"

    });

    $("#populate_locations_dropdown").select2({
        placeholder: "Select locations"
    });

    $.getJSON(ROLES_URL, {
        ajax : 'true'
    }, function(data){
        var html;
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">'
                + data[i].name + '</option>';
        }
        $('#populate_roles_dropdown').html(html);
    });

    $.getJSON(CATEGORIES_URL, {
        ajax : 'true'
    }, function(data){
        var html;
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">'
                + data[i].name + '</option>';
        }
        $('#populate_categories_dropdown').html(html);
    });

    $.getJSON(LOCATIONS_URL, {
        ajax : 'true'
    }, function(data){
        var html;
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">'
                + data[i].name + '</option>';
        }
        $('#populate_locations_dropdown').html(html);
    });
});