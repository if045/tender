function showUnits(id) {
    $.getJSON(TENDERS_URL + id + '/units', function(data) {
        var html='';
        var len = data.length;

        for (var i = 0; i < len; i++) {
            html += '<tr>' +
                '<td>' + data[i].unitName + '</td>' +
                '<td>' + data[i].itemType + '</td>' +
                '<td>' + data[i].categoryName + '</td>' +
                '<td>' + data[i].quantity + ' ' + data[i].measurementName + '</td>' +
                '<td>'+ '<input type="text" class="form-control" />' +'</td>' +
            '</tr>';
        }
        $('#tenderUnits').html(html);
    });

}
