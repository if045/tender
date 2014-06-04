$(document).ready(function () {
    $('#date_to').datepicker({
        format: 'yyyy/mm/dd',
        startDate: '-5y'
    });


    var str = location.href;
    var str1 = str.split("/tenderView/");
    var tenderID = str1[str1.length - 1];
    $.getJSON('/tenders/' + tenderID, function (data) {
        var price = '';
        var endDate = new Date(data.endDate);
        endDate.toString();
        price += '<p class="form-control-static col-md-2">' + data.suitablePrice + '</p>';
        $('#suitablePrice').html(price);
        $('#date_to').val(endDate);
    });
});