$(document).ready(function() {
    $('#endDate').datepicker({
        format: 'mm-dd-yyyy',
        startDate: '-5y'
    });

    showDeals();
});

function showDeals() {
    $.getJSON('/deals', function (data) {
        var html = '';
        var len = data.length;
        if(len > 0) {
            for (var i = 0; i < len; i++) {
                html += '<tr><td align="center">' + data[i].title + '</td>' +
                    '<td align="center">' + unixTimeConverter(data[i].date) + '</td>' +
                    '<td align="center">' + data[i].businessPartner + '</td>' +
                    '<td align="center">' + data[i].status + '</td>' +
                    '<td align="center">' + data[i].sum + '</td>' +
                    '<td align="center">' +
                        '<select class="form-control items_number_dropdown action_button">'+
                            '<option value="">Action</option>'+
                            '<option value="done' + data[i].id + '">Done</option>'+
                            '<option value="conflict' + data[i].id + '">Conflict</option>'+
                            '<option value="feedback' + data[i].id + '">Feedback</option>'+
                        '</select>'+
                    '</td></tr>';
            }

            $('#user_message').html('');
            $('#deal_items').show();
            $('#deals').html(html);

            var pageItemsNumber = $('#pagination_itemsnum').val();
            if(pageItemsNumber < len) {
                $('#pagination').show();
            } else {
                $('#pagination').hide();
            }
        } else {
            $('#user_message').html('<h4>No deals found</h4>');
            $('#deal_items').hide();
            $('#pagination').hide();
        }
    });
}

function unixTimeConverter(timestamp){
    var date = new Date(timestamp);
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDate();

    if(day <= 9) day = "0" + day;
    if(month <= 9) month = "0" + month;

    return day + "/" + month + "/" + year;
}