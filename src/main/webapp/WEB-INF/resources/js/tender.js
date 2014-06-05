var TENDER_VIEW_URL = "/tenderView";
var FORMAT_DATE = 'yyyy/mm/dd';

$(document).ready(function () {
    $('#endDate').datepicker({
        format: FORMAT_DATE,
        startDate: '-3d'
    });
    showUnit();
    showProposals();
});
function showProposals() {
    var URL = location.href;
    var tenderURI = URL.split(TENDER_VIEW_URL);
    var tenderId = tenderURI[tenderURI.length - 1];
    $.getJSON('/tenders/' + tenderId + '/proposals', function (data) {
        var html;
        var len = data.length;
        if (len > 0) {
            document.getElementById("no_proposals_message").setAttribute('hidden', 'true');
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
            document.getElementById("head_proposals").setAttribute('hidden', 'true');
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
        for (var i = 0; i < len; i++) {
            html += '<tr><td>' + ' <input type="checkbox">' + '</td>' +
                '<td>' + data[i].unitName + '</td>' +
                '<td>' + data[i].itemType + '</td>' +
                '<td>' + data[i].categoryName + '</td>' +
                '<td>' + data[i].quantity + ' ' + data[i].measurementName + '</td>' +
                '<td>' + data[i].numberOfBids + '</td>' +
                '<td>' + data[i].price + '</td>' +
                '<td>' + '<button type="submit" class="btn btn-default" disabled>Deal</button>' + '</td></tr>';
        }
        $('#unitsTable').html(html);
    });
}

