$(document).ready(function() {
    $('#endDate').datepicker({
        format: 'mm-dd-yyyy',
        startDate: '-5y'
    });

    showDeals();
    $('#search_deals').keypress(function(e) {
        if (e.keyCode == 13) {
            var filter=$('#search_deals').val();
            if (filter.length > 0) {
                searchDeals(filter);
            }else{
                showDeals();
            }
            return false;
        }
    });
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
                        '<div class="btn-group">' +
                            '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                            '<ul class="dropdown-menu">' +
                                '<li><a href="#">Feedback</a></li>' +
                                '<li><a href="#">Conflict</a></li>' +
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
    str += "statusName=" + "close";
    $.ajax({
        url: "/deals/" + $('#close_deal_id').val() + "?"  + str,
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

function searchDeals(filter){
    $.ajax({
        url: TENDERS_URL + "search/" + filter,
        type: "GET",

        success: function(data){
            var html = '';
            var len = data.length;

            if(len > 0) {
                for (var i = 0; i < len; i++) {
                    html += '<tr><td align="center"><a href="/tenderView/' + data[i].id + '">' + data[i].title + '</a></td>' +
                        '<td align="center">' + data[i].authorName + '</td>' +
                        '<td align="center">' + data[i].categories + '</td>' +
                        '<td align="center">' + data[i].locations + '</td>' +
                        '<td align="center">' + data[i].suitablePrice + '</td>' +
                        '<td align="center">' + data[i].status + '</td>' +
                        '<td align="center">' + data[i].proposals + '</td>' +
                        '<td align="center">' +
                        '<div class="btn-group">' +
                        '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                        '<ul class="dropdown-menu">' +
                        '<li><a href="/tenderView/' + data[i].id + '">View</a></li>' +
                        '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow" onclick="showUnits(' + data[i].id + ')">Create proposal</a></li>' +
                        '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>' +
                        '</ul>' +
                        '</div>' +
                        '</td></tr>';
                }
                $('#tenders').html(html);
            }
        },
        error: function(){
            alert("Something wrong")
        }
    });
}