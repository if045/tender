$(document).ready(function() {
    $('#birth_date').datepicker({
        format: 'yyyy/mm/dd'
    });

    $("#users_role_select").select2({
        placeholder: "Select role"
    });

    $("#categories_select").select2({
        placeholder: "Select categories"

    });

    $("#locations_select").select2({
        placeholder: "Select locations"
    });
});
