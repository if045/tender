$(document).ready(function () {
    $('#endDate').datepicker({
        format: 'yyyy/mm/dd',
        startDate: '-3d'
    });
    showUnit();
});
function showUnit() {
    var str = location.href;
    var tender1 = str.split("http://localhost:8080/tenderView/");
    var tender = tender1[tender1.length - 1];
    $.getJSON('/tenders/tenderView/' + tender + '/units', function (data) {
        var html = '';
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<tr><td>' + ' <input type="checkbox">' + '</td>' +
                '<td>' + data[i].unitName + '</td>' +
                '<td>' + data[i].itemType + '</td>' +
                '<td>' + data[i].categoryName + '</td>' +
                '<td>' + data[i].quantity +' ' +data[i].measurementName+ '</td>' +
                '<td>' + data[i].id + '</td>' +
                '<td>' + data[i].price + '</td>' +
                '<td>' + '<button type="submit" class="btn btn-default" disabled>Deal</button>' + '</td></tr>';
        }
        $('#unitsTable').html(html);
    });
}
