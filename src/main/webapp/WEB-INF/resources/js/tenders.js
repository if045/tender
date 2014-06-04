        $(document).ready(function() {
            $('#startDate, #endDate, #create_tender_enddate').datepicker({
                format: 'yyyy/mm/dd',
                startDate: '-5y'
            });

            $("#category_filter").select2({
                placeholder: "All categories"
            });

            $("#item_dropdown").select2({
                placeholder: "Select a items"

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

            $('#search_input').keydown(function(event) {
                if (event.keyCode == 13) {
                    //search query

                    return false;
                }
            });

            $('#createTenderWindow').on('shown.bs.modal', function () {
                $('.datepicker').addClass('modal_datepicker');

                $("#create_tender_unit_category").select2({
                    placeholder: "Select a category"
                });

                $("#create_tender_unit_item").select2({
                    placeholder: "Select a item"
                });

                $("#create_tender_location").select2({
                    placeholder: "Select a location"
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

                    $('#create_tender_location').html(html);
                });

                $.getJSON('/tenders/categories', {
                    ajax : 'true'
                }, function(loc){
                    var html = ' ';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].id + '">'
                            +loc[i].name + '</option>';
                    }

                    $('#create_tender_unit_category').html(html);
                });

                $.getJSON('/tenders/items', {
                    ajax : 'true'
                }, function(loc){
                    var html = ' ';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].id + '">'
                            +loc[i].name + '</option>';
                    }

                    $('#create_tender_unit_item').html(html);
                });
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
            $.getJSON('/tenders', function (data) {
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
                                            '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow">Create proposal</a></li>' +
                                            '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>' +
                                        '</ul>' +
                                    '</div>' +
                                '</td></tr>';
                    }

                    $('#user_message').html('');
                    $('#tender_items').show();
                    $('#tenders').html(html);

                    var pageItemsNumber = $('#pagination_itemsnum').val();
                    if(pageItemsNumber < len) {
                        $('#pagination').show();
                    } else {
                        $('#pagination').hide();
                    }
                } else {
                    $('#user_message').html('<h4>Your filter parameters did not match any tender</h4>');
                    $('#tender_items').hide();
                    $('#pagination').hide();
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
            
            $.ajax({
              dataType: "json",
              url: '/tenders/categories',
              async: false,
              success: function(data){
                    var dataLength = data.length;
                    var catNumber = categories.length;
                    for(var z = 0;z<catNumber;z++) {
                        for (var i = 0; i < dataLength; i++) {
                            if(categories[z] == data[i].parent) {
                                categories.push(""+data[i].id);
                            }
                        }   
                    }
                }
            });

            if (categories != null){
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
            $.ajax({
                url: "/tenders",
                type: "GET",
                data:  str,
                dataType:'json',

                success: function(data) {
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
                                    '<td align="center">' + data[i].proposals + '</td>'+
                                    '<td align="center">' +
                                        '<div class="btn-group">' +
                                            '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                                            '<ul class="dropdown-menu">' +
                                                '<li><a href="/tenderView/' + data[i].id + '">View</a></li>' +
                                                '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow">Create proposal</a></li>' +
                                                '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>' +
                                            '</ul>' +
                                        '</div>' +
                                    '</td></tr>';
                        }

                        $('#user_message').html('');
                        $('#tender_items').show();
                        $('#tenders').html(html);

                        var pageItemsNumber = $('#pagination_itemsnum').val();
                        if(pageItemsNumber < len) {
                            $('#pagination').show();
                        } else {
                            $('#pagination').hide();
                        }
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
            str += "statusName=" + CLOSE_STATUS_NAME;
            $.ajax({
                url: TENDERS_URL + $('#close_tender_id').val() + "?"  + str,
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

        function showDeals() {
            window.location.href='/deals/';
        }