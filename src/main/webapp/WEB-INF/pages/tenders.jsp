<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UATenders</title>

    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/bootstrap.min.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/datepicker.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/select2.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/style.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap-datepicker.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#startDate, #endDate').datepicker({
                format: 'yyyy/mm/dd',
                startDate: '-3d'
            });

            $("#category_filter").select2({
                placeholder: "Select a category"
            });

            $("#item_filter").select2({
                placeholder: "Select a Items"
            });

            $("#location_filter").select2({
                placeholder: "Select a location"
            });

            $("#status_filter").select2({
                placeholder: "Select a status"
            });


            $("#category_filter, #location_filter, #item_filter, #status_filter, #price_from, #price_to, #date_from, #date_to").change(function() {
                enableFilterButtons();
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
                    html += '<tr><td>' + data[i].title + '</td>' +
                            '<td>' + data[i].authorName + '</td>' +
                            '<td>' + data[i].categories + '</td>' +
                            '<td>' + data[i].locations + '</td>' +
                            '<td>' + data[i].status + '</td>' +
                            '<td>' + data[i].suitablePrice + '</td>' +
                            '<td>' + data[i].proposals + '</td></tr>';
                }
                $('#tenders').html(html);
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
            $("#item_filter").select2('val', 'All');
            $("#status_filter").select2('val', 'All');
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
                        html += '<tr><td>' + data[i].title + '</td>' +
                                '<td>' + data[i].authorName + '</td>' +
                                '<td>' + data[i].categories + '</td>' +
                                '<td>' + data[i].locations + '</td>' +
                                '<td>' + data[i].status + '</td>' +
                                '<td>' + data[i].suitablePrice + '</td>' +
                                '<td>' + data[i].proposals + '</td></tr>';
                    }
                    $('#tenders').html(html);
                },
                error:function(){
                    alert("ERROR");
                }
            });
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row">
        <!--navigation-->
        <jsp:include page="header.jsp"/>
        <!--navigation-->

        <!--main-->
        <div class="page_body">
            <!-- sidebar -->
            <div class="col-md-2">

                <div class="panel panel-default sidebar">
                    <div class="panel-heading">
                        <h3 class="panel-title">Filter</h3>
                    </div>
                    <div class="panel-body">
                        <div>
                            <div>Category</div>
                            <div>
                                <select id="category_filter" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1"></select>
                            </div>
                        </div>
                        <div>
                            <div>Item</div>
                            <select id="item_filter" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1"></select>
                        </div>
                        <div>
                            <div>Location</div>
                            <div>
                                <select id="location_filter" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1" >
                                </select>
                            </div>
                        </div>
                        <div>
                            <div>Status</div>
                            <select id="status_filter" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1"></select>
                        </div>
                        <div>
                            <div>Suitable price</div>
                            <div>
                                <input id="price_from" type="price_from" class="pull-left form-control suitable_price" placeholder="From">
                                <input id="price_to" type="price_to" class="pull-right form-control suitable_price" placeholder="To">
                            </div>
                        </div>
                        <div>
                            <div>Active</div>
                            <div class="date_filter">
                                <div class="input-group date pull-left" id="startDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                                    <input id="date_from" class="form-control" size="10" type="text" value="">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                </div>
                                <div class="input-group date pull-right" id="endDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                                    <input id="date_to" class="form-control" size="10" type="text" value="">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                </div>


                            </div>
                        </div>
                        <div><br/></div>
                        <div>
                            <div class="pull-left"><button id="filter_button" type="button" class="btn btn-default" onclick="applyFilters();" disabled>Filter</button></div>
                            <div class="pull-right"><button id="clear_button" type="button" class="btn btn-default" onclick="clearFilters();" disabled>Clear filters</button></div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- sidebar -->

            <!-- content -->
            <div class="col-md-10">
                <div class="row">
                    <div class="pull-left">
                        <h3>Tenders</h3>
                    </div>
                    <div class="pull-right">
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Search...">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                        </form>
                    </div>
                </div>

                <!-- items -->
                <div class="row">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Location</th>
                            <th>SuitablePrice</th>
                            <th>Status</th>
                            <th>Proposals</th>
                        </tr>
                        </thead>
                        <tbody id="tenders"></tbody>
                    </table>
                </div>
                <!-- items -->

                <!-- pagination -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <ul class="pagination page_pagination pull-right">
                                <li><a href="#">&laquo;</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">&laquo;</a></li>
                            </ul>
                        </div>
                        <div class="pull-right">
                            <div class="control-group">
                                 <select class="form-control pull-right items_number_dropdown">
                                     <option>10</option>
                                     <option>15</option>
                                     <option>20</option>
                                 </select>
                            </div>
                        </div>
                        <div class="pull-right">
                            <div class="items_number_dropdown_title">Items on page: &nbsp;</div>
                        </div>
                    </div>
                </div>
                <!-- pagination -->
            </div>
            <!-- content -->
        </div>
        <!--main-->

        <!--footer -->
        <jsp:include page="footer.jsp"/>
        <!-- footer -->

    </div>
</div>

</body>

</html>

