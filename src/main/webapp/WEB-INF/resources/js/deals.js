var pageSize = DEFAULT_PAGE_SIZE;
var currPageNumber = 0;

$(document).ready(function() {
    $('#endDate, #create_tender_enddate').datepicker({
        format: 'mm-dd-yyyy',
        startDate: '-5y'
    });

    $('#pagination_itemsnum').on('change', function() {
        pageSize = this.value;
        currPageNumber = 0;
        showPage(currPageNumber);
    });

    showDeals();
});

function showDeals() {
    var queryParams = ''; 
    
    showPagination(queryParams);
    
    queryParams += (queryParams.length==0)?"pageSize="+pageSize:"&pageSize="+pageSize;
    queryParams += "&pageNumber="+currPageNumber;

    $.ajax({
        url: DEALS_URL,
        type: "GET",
        data:  queryParams,
        dataType:'json',

        success: function(data) {
            var html = '';
            var dataSize = data.length;

            if(dataSize > 0) {
                for (var i = 0; i < dataSize; i++) {
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
                                    '<li><a href="#">Conflict</a></li>' +
                                    '<li><a href="#" data-toggle="modal" data-target="#close_deal_mod_wind" onclick="writeCloseDealId(' + data[i].id + ')">Close</a></li>' +
                                '</ul>' +
                            '</div>' +
                        '</td></tr>';
                }

                $('#user_message').html('');
                $('#deal_items').show();
                $('#deals').html(html);
            } else {
                $('#user_message').html('<h4>No deals found</h4>');
                $('#deal_items').hide();
            }
        },
        error:function(){
            alert("ERROR");
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

function goToRegistrationPage() {
    window.location.href = REGISTRATION_PAGE_URL;
}

function showPagination(queryParams) {
    $.ajax({
        url: DEALS_NUMBER_URL,
        async: false,
        type: "GET",
        data:  queryParams,
        dataType:'json',

        success: function(data) {
            var dataSize = data.dealsNumber;
            var pageNumber = Math.ceil(dataSize / pageSize);

            if(dataSize > 0 && pageSize < dataSize) {
                var html = '';
                html += '<li class="'+((currPageNumber==0)?"disabled":"")+'"><a id="prev_page" href="#">&laquo;</a></li>';
                for(var z=1;z<=pageNumber;z++) {
                    html += '<li class="'+((currPageNumber==z-1)?"active":"")+'"><a href="#" onclick="showPage('+(z-1)+');">'+z+'</a></li>';
                }
                html += '<li class="'+((currPageNumber==pageNumber-1)?"disabled":"")+'"><a id="next_page" href="#">&raquo;</a></li>';

                $('.page_pagination').html(html).show();
                $('#pagination').show();

                if(currPageNumber != 0) {
                    document.getElementById('prev_page').setAttribute("onclick", "showPage("+(currPageNumber-1)+");");
                }

                if(currPageNumber != pageNumber-1) {
                    document.getElementById('next_page').setAttribute("onclick", "showPage("+(currPageNumber+1)+");");
                }
            } else {
                $('.page_pagination').hide();
            }
        },
        error:function(){
            $('#pagination').hide();
        }
    });
}

function showPage(pageNumber) {
    currPageNumber = pageNumber;
    showDeals();
}