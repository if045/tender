        var pageSize = DEFAULT_PAGE_SIZE;
        var currPageNumber = 0;
        var ENTER_BUTTON_CODE =13;
        var TENDERS_QUANTITY= 1000;

        var sortDirection = false;
        var orderBy = DEFAULT_SORT_FIELD;


        $(document).ready(function() {
            $('#startDate, #endDate, #create_tender_enddate').datepicker({
                format: 'yyyy/mm/dd',
                startDate: '-5y'
            });

            initializeDateFilter();

            $("#category_filter").select2({
                placeholder: "All categories"
            });

            $("#item_dropdown").select2({
                placeholder: "All items"
            });

            $("#location_filter").select2({
                placeholder: "All locations"
            });

            $("#status_filter").select2({
                placeholder: "All active statuses"
            });

            $("#location_filter, #item_dropdown, #status_filter, #price_from, #price_to, #date_from, #date_to").change(function() {
                enableFilterButtons();
            });

            $("#pagination_itemsnum").on('change', function() {
                pageSize = this.value;
                currPageNumber = 0;
                showPage(currPageNumber);
            });

            $("#tender_title").click(function(){
                sortTenders("title","tender_title");
            });

            $("#tender_author").click(function(){
                sortTenders("author.firstName","tender_author");
            });

            $("#tender_suitable_price").click(function(){
                sortTenders("suitablePrice","tender_suitable_price");
            });

            $("#tender_status").click(function(){
                sortTenders("status.name","tender_status");
            });

            $("#tender_proposals").click(function(){
                sortTenders("proposals.size","tender_proposals");
            });

            $("#category_filter").change(function() {
                enableFilterButtons();
                $("#item_dropdown").select2('val', 'All');
                var str='';
                var categories=new Array();
                categories=$('#category_filter').val();
                if (categories!=null){
                    str += "categories="+categories;
                }
                $.ajax({
                    url: "/tenders/items",
                    type: "GET",
                    data:  str,
                    dataType:'json',

                    success: function(data) {
                        var html = '';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].id + '">'
                                + data[i].name + '</option>';
                        }

                        $('#item_dropdown').html(html);
                    },
                    error:function(){
                        alert("ERROR");
                    }
                });
            });

            $.getJSON('/tenders/statuses', {
                  ajax : 'true'
                }, function(data){
                  var html;
                  var len = data.length;
                  for (var i = 0; i < len; i++) {
                      html += '<option value="' + data[i].id + '">'
                               + data[i].name + '</option>';
                  }
                  $('#status_filter').html(html);
            });

            $.getJSON('/tenders/locations', {
                ajax : 'true'
            }, function(loc){
                var html = ' ';
                var len = loc.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + loc[i].id + '">'
                            +loc[i].name + '</option>';
                }

                $('#location_filter').html(html);
            });

            $.getJSON('/tenders/categories', function(data){
                var html = ' ';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                }

                $('#category_filter').html(html);
            });

            populateItemDropdown();
            showTenders();

            $('#search_tenders').keypress(function(e) {
                if (e.keyCode == ENTER_BUTTON_CODE) {
                    if (this.value!=""){
                        applyFilters();
                        pageSize = TENDERS_QUANTITY;
                        currPageNumber = 0;
                        showPage(currPageNumber);
                    }else {
                        applyFilters();
                        pageSize = $('#pagination_itemsnum').val();
                        currPageNumber = 0;
                        showPage(currPageNumber);
                    }
                    return false;
                }
            });
        });

        function populateItemDropdown() {
            $.getJSON('/tenders/items', function (data) {
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                }

                $('#item_dropdown').html(html);
            });
        }

        function showTenders() {
            var queryParams = "";

            if($("#date_from").val()!="" && $("#date_from").val() != undefined){
                queryParams += (queryParams.length==0)?"minDate="+$("#date_from").val():"&minDate="+$("#date_from").val();
            }
            if($("#date_to").val()!="" && $("#date_to").val() != undefined){
                queryParams += (queryParams.length==0)?"maxDate="+$("#date_to").val():"&maxDate="+$("#date_to").val();
            }

            showPagination(queryParams);

            queryParams += (queryParams.length==0)?"pageSize="+pageSize:"&pageSize="+pageSize;
            queryParams += "&pageNumber=" + currPageNumber + "&sortDirection=" +
                                                                ((sortDirection)?"desc":"asc") + "&orderBy=" + orderBy;

            $.ajax({
                url: TENDERS_URL,
                type: "GET",
                data:  queryParams,
                dataType:'json',

                success: function(data) {
                    var html = '';
                    var dataSize = data.length;

                    if(dataSize > 0) {
                        for (var i = 0; i < dataSize; i++) {
                            html += '<tr><td align="center">' + data[i].title + '</td>' +
                                '<td align="center">' + data[i].authorName + '</td>' +
                                '<td align="center">' + data[i].categories + '</td>';
                                if (data[i].locations.toString().split(',').length>2){
                                    html += '<td align="center" data-toggle="tooltip" data-placement="bottom" title="'+data[i].locations+'">' + data[i].locations.toString().split(',')[0] +','+data[i].locations.toString().split(',')[1] + '...'+'</td>';
                                }else{
                                    html += '<td align="center">' + data[i].locations + '</td>';
                                }

                            html += '<td align="center">' + data[i].suitablePrice + '</td>' +
                                '<td align="center">' + data[i].status + '</td>' +
                                '<td align="center">' + data[i].proposals + '</td>' +
                                '<td align="center">' +
                                '<div class="btn-group">' +
                                '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                                '<ul class="dropdown-menu">'+
                                '<li><a href="/tenderView/' + data[i].id + '">View</a></li>';
                            if (data[i].roles.toString().search('CUSTOMER')!=-1){
                                if (data[i].userId.toString()==data[i].authorId.toString()){
                                    html += '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>';
                                }
                            }
                            if (data[i].roles.toString().search('SELLER')!=-1){
                                html += '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow" onclick="showUnits(' + data[i].id + ')">Create proposal</a></li>';
                            }
                            html +='</ul>' +
                                '</div>' +
                                '</td></tr>';
                        }


                        $('#user_message').html('');
                        $('#tender_items').show();
                        $('#tenders').html(html);
                    } else {
                        $('#user_message').html('<h4>Your filter parameters did not match any tender</h4>');
                        $('#tender_items').hide();
                    }
                },
                error:function(){
                    alert("ERROR");
                }
            });
        }

        function clearFilters() {
            disableFilterButtons();
            $("#price_from").val("");
            $("#price_to").val("");
            $("#date_from").val("");
            $("#date_to").val("");
            $("#location_filter").select2('val', 'All');
            $("#category_filter").select2('val', 'All');
            $("#item_dropdown").select2('val', 'All');
            $("#status_filter").select2('val', 'All');
            $('#search_tenders').val("");
            initializeDateFilter();
            
            showTenders();
        }

        function enableFilterButtons() {
            $("#filter_button").removeAttr("disabled");
            $("#clear_button").removeAttr("disabled");
        }

        function disableFilterButtons() {
            $("#filter_button").attr("disabled", "disabled");
            $("#clear_button").attr("disabled", "disabled");
        }

        function applyFilters() {
            disableFilterButtons();
            var str='';
            if($("#price_from").val()!=""){
                str+="minPrice="+$("#price_from").val();
            }
            if($("#price_to").val()!=""){
                str += (str.length==0)?"maxPrice="+$("#price_to").val():"&maxPrice="+$("#price_to").val();
            }
            var items=new Array();
            items=$('#item_dropdown').val();
            if (items!=null){
                str += (str.length==0)?"items="+items:"&items="+items;
            }

            var categories = new Array();
            categories = $('#category_filter').val();

            if (categories != null){
                $.ajax({
                    dataType: "json",
                    url: '/tenders/categories',
                    async: false,
                    success: function(data){
                        for(var z=0;z<categories.length;z++) {
                            var subCats = getSubCategories(categories[z],data,categories);
                            categories = categories.concat(subCats);
                        }
                    }
                });
                str += (str.length == 0)?"categories="+categories:"&categories="+categories;
            }

            var locations=new Array();
            locations=$('#location_filter').val();
            if (locations!=null){
                str += (str.length==0)?"locations="+locations:"&locations="+locations;
            }
            var statuses=new Array();
            statuses=$('#status_filter').val();
            if (statuses!=null){
                str += (str.length==0)?"statuses="+statuses:"&statuses="+statuses;
            }
            if($("#date_from").val()!=""){
                str += (str.length==0)?"minDate="+$("#date_from").val():"&minDate="+$("#date_from").val();
            }
            if($("#date_to").val()!=""){
                str += (str.length==0)?"maxDate="+$("#date_to").val():"&maxDate="+$("#date_to").val();
            }
            if($('#search_tenders').val()!=""){
                str += (str.length==0)?"searchParam="+$('#search_tenders').val():"&searchParam="+$('#search_tenders').val();
            }

            showPagination(str);
            str += (str.length==0)?"pageSize="+pageSize:"&pageSize="+pageSize;
            str += "&pageNumber="+currPageNumber + "&sortDirection=" + ((sortDirection)?"desc":"asc") + "&orderBy=" + orderBy;

            $.ajax({
                url: TENDERS_URL,
                type: "GET",
                data:  str,
                dataType:'json',

                success: function(data) {
                    var html = '';
                    var dataSize = data.length;

                    if(dataSize > 0) {
                        for (var i = 0; i < dataSize; i++) {
                            html += '<tr><td align="center">' + data[i].title + '</td>' +
                                '<td align="center">' + data[i].authorName + '</td>' +
                                '<td align="center">' + data[i].categories + '</td>';
                            if (data[i].locations.toString().split(',').length>2){
                                html += '<td align="center" data-toggle="tooltip" data-placement="bottom" title="'+data[i].locations+'">' + data[i].locations.toString().split(',')[0] +','+data[i].locations.toString().split(',')[1] + '...'+'</td>';
                            }else{
                                html += '<td align="center">' + data[i].locations + '</td>';
                            }

                            html += '<td align="center">' + data[i].suitablePrice + '</td>' +
                                '<td align="center">' + data[i].status + '</td>' +
                                '<td align="center">' + data[i].proposals + '</td>' +
                                '<td align="center">' +
                                '<div class="btn-group">' +
                                '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                                '<ul class="dropdown-menu">'+
                                '<li><a href="/tenderView/' + data[i].id + '">View</a></li>';
                            if (data[i].roles.toString().search('CUSTOMER')!=-1){
                                html += '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>';
                            }
                            if (data[i].roles.toString().search('SELLER')!=-1){
                                html += '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow" onclick="showUnits(' + data[i].id + ')">Create proposal</a></li>';
                            }
                            html +='</ul>' +
                                '</div>' +
                                '</td></tr>';
                        }

                        $('#user_message').html('');
                        $('#tender_items').show();
                        $('#tenders').html(html);
                        $('#pagination').show();
                    } else {
                        $('#user_message').html('<h4>Your filter parameters did not match any tender</h4>');
                        $('#tender_items').hide();
                        $('#pagination').hide();
                    }
                },
                error:function(){
                    alert("ERROR");
                }
            });

            $("#clear_button").removeAttr("disabled");
        }

        function closeTender() {
            var str = '';
            str += "statusName=" + CLOSE_TENDER_STATUS_NAME;
            $.ajax({
                url: TENDERS_URL + "/" + $('#close_tender_id').val() + "?"  + str,
                type: "PUT",

                success: function(data){
                    closeModalWindow('close_tender_mod_wind');
                    showTenders()},
                error: function(){
                    alert("Something wrong")
                }
            })
        }

        function writeCloseTenderId(id) {
            document.getElementById('close_tender_id').value = id;
        }

        function closeModalWindow(id) {
            $('#' + id).modal('hide');
        }

        function initializeDateFilter() {
            var currentDate = getCurrentDate();
            var nextMonthDate = getNextMonthDate();

            $('#startDate').data({date: currentDate});
            $('#startDate').datepicker('update');
            $('#startDate').datepicker().children('input').val(currentDate);

            $('#endDate').data({date: nextMonthDate});
            $('#endDate').datepicker('update');
            $('#endDate').datepicker().children('input').val(nextMonthDate);
        }

        function getSubCategories(id,data,categories) {
            var dataSize = data.length;
            var subCats = new Array();

            for(var z=0;z<dataSize;z++) {
                if(id == data[z].parentId)
                {
                    if($.inArray(data[z].id, categories) != 0) {
                        subCats.push(data[z].id);
                    }
                }
            }

            return subCats;
        }

        function getCurrentDate() {
            var today = new Date();
            var day = today.getDate();
            var month = today.getMonth()+1;
            var year = today.getFullYear();

            if(day < 10) day = '0' + day;
            if(month < 10) month = '0' + month;

            return year + '/' + month + '/' + day;
        }

        function getNextMonthDate() {
            var today = new Date();
            var day = today.getDate();
            var month = today.getMonth()+2;
            var year = today.getFullYear();

            if(day < 10) day = '0' + day;
            if(month < 10) month = '0' + month;

            return year + '/' + month + '/' + day;
        }

        function goToRegistrationPage() {
            window.location.href = REGISTRATION_PAGE_URL;
        }

        function goToMyDealsPage() {
            window.location.href = MYDEALS_PAGE_URL;
        }

        function showPagination(queryParams) {
            $.ajax({
                url: TENDERS_NUMBER,
                async: false,
                type: "GET",
                data:  queryParams,
                dataType:'json',

                success: function(data) {
                    var dataSize = data.tendersNumber;
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
            applyFilters();
        }

        function sortTenders(orderByField, elementId) {
            sortDirection = (orderBy == orderByField) ? !sortDirection : false;
            orderBy = orderByField;

            $('#tender_items .sortable').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
            if(sortDirection == false) {
                $('#'+elementId+' .sortable').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
            } else {
                $('#'+elementId+' .sortable').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
            }

            applyFilters();
        }

        function goToUserProfilePage() {
            window.location.href = USER_PROFILE_PAGE_URL;
        }
