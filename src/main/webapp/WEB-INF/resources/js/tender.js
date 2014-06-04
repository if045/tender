$(document).ready(function() {
    showProposals()
});

var TENDER_VIEW_URL = "/tenderView";

function showProposals() {
    var URL = location.href;
    var tenderURI = URL.split(TENDER_VIEW_URL);
    var tenderId = tenderURI[tenderURI.length - 1];
    $.getJSON('/tenders/'+tenderId+'/proposals', function (data) {
        var html;
        var len = data.length;
        if(len > 0) {
            document.getElementById("GUSb").setAttribute('hidden','true');
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
            document.getElementById("head_proposals").setAttribute('hidden','true');
        }
    });
}
