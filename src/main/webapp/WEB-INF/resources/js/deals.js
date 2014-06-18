$(document).ready(function() {
    $('#endDate, #create_tender_enddate').datepicker({
        format: 'mm-dd-yyyy',
        startDate: '-5y'
    });

    showDeals();
});

function showDeals() {
    $.getJSON(DEALS_URL, function (data) {
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
                        '<div class="btn-group">' +
                            '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                            '<ul class="dropdown-menu">' +
                                '<li><a href="#" data-toggle="modal" data-target="#feedback_mod_wind" onclick="writeFeedbackId(' + data[i].id + ')">Feedback</a></li>' +
                                '<li><a href="#" data-toggle="modal" data-target="#conflict_mod_wind" onclick="writeConflictId(' + data[i].id + ')">Conflict</a></li>' +
                                '<li><a href="#" data-toggle="modal" data-target="#close_deal_mod_wind" onclick="writeCloseDealId(' + data[i].id + ')">Close</a></li>' +
                            '</ul>' +
                        '</div>' +
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

function closeDeal() {
    var str = '';
    str += "statusName=" + DEAL_STATUS;
    $.ajax({
        url: DEALS_URL+"/" + $('#close_deal_id').val() + "?"  + str,
        type: "PUT",

        success: function(data){
            closeDealModalWindow('close_deal_mod_wind');
            showDeals()},
        error: function(){
            alert("Can't close this deal")
        }
    })
}

function writeCloseDealId(id) {
    document.getElementById('close_deal_id').value = id;
}

function closeDealModalWindow(id) {
    $('#' + id).modal('hide');
}