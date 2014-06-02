        $(document).ready(function() {
            $('#startDate, #endDate, #create_tender_enddate').datepicker({
                format: 'yyyy/mm/dd',
                startDate: '-5y'
            });

            $("#category_filter").select2({
                placeholder: "Select a category"
            });

            $("#item_filter").select2({
                placeholder: "Select a items"
            });

            $("#location_filter").select2({
                placeholder: "Select a location"
            });

            $("#status_filter").select2({
                placeholder: "Select a status"
            });

            $("#location_filter, #item_filter, #status_filter, #price_from, #price_to, #date_from, #date_to").change(function() {
                enableFilterButtons();
            });

            $("#category_filter").change(function() {
                enableFilterButtons();
                $("#item_filter").select2('val', 'All');
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

                        $('#item_filter').html(html);
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
            itemDropdown();
            showTenders();

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

        function itemDropdown() {
            $.getJSON('/tenders/items', function (data) {
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                }

                $('#item_filter').html(html);
            });
        }

        function showTenders() {
            $.getJSON('/tenders', function (data) {
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<tr><td align="center"><a href="/tenders/view' + data[i].id + '">' + data[i].title + '</a></td>' +
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
                                        '<li><a href="#" data-toggle="modal" data-target="#closeTenderModWind">Close</a></li>' +
                                    '</ul>' +
                                '</div>' +
                            '</td></tr>';
                }
                $('#tenders').html(html);
            });
        }


        function clearFilters() { onchange="location.href=this.value"
            disableFilterButtons();
            $("#price_from").val("");
            $("#price_to").val("");
            $("#date_from").val("");
            $("#date_to").val("");
            $("#location_filter").select2('val', 'All');
            $("#category_filter").select2('val', 'All');
            $("#item_filter").select2('val', 'All');
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
            items=$('#item_filter').val();
            if (items!=null){
                str += (str.length==0)?"items="+items:"&items="+items;
            }
            var categories=new Array();
            categories=$('#category_filter').val();
            if (categories!=null){
                str += (str.length==0)?"categories="+categories:"&categories="+categories;
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
                                            '<li><a href="#" data-toggle="modal" data-target="#closeTenderModWind">Close</a></li>' +
                                        '</ul>' +
                                    '</div>' +
                                '</td></tr>';
                    }

                    $('#tenders').html(html);
                },
                error:function(){
                    alert("ERROR");
                }
            });

            $("#clear_button").removeAttr("disabled");
        }
