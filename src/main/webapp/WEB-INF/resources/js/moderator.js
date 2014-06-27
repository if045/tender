$(document).ready(function () {
    populateConflictTable();

});

function populateConflictTable() {
    $.getJSON(MODERATOR_URL + CONFLICTS, function (data) {
        var html = '';
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<tr><td align="center">' + data[i].title + '</td>' +
                '<td align="center">' + data[i].customerName + '</td>' +
                '<td align="center">' + data[i].sellerName + '</td>' +
                '<td align="center"><button class="btn btn-default" type="button">Action</button></td></tr>';
        }

        $('#conflict_table').html(html);
    });
}