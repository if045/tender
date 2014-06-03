$(document).ready(function() {
    $('#endDate').datepicker({
        format: 'mm-dd-yyyy',
         startDate: '-5y'
    });

    $('#search_input').keydown(function(event) {
        if (event.keyCode == 13) {
            //search query

            return false;
        }
    });  
});

function showDeals() {
    window.location.href='/deals/';
}