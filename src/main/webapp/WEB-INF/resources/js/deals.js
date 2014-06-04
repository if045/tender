$(document).ready(function() {
    $('#endDate').datepicker({
        format: 'mm-dd-yyyy',
        startDate: '-5y'
    });  
});

function showDeals() {
    window.location.href='/mydeals';
}