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
            $('#startDate, #endDate, #create_tender_enddate').datepicker({
                format: 'mm-dd-yyyy',
                startDate: '-3d'
            });

            $("#category_filter").select2({
                placeholder: "Select a category"
            });

            $("#item_filter").select2({
                placeholder: "Select a Items"
            });

            $("#location_filter, #create_tender_location").select2({
                placeholder: "Select a location"
            });

            $("#status_filter").select2({
                placeholder: "Select a status"
            });


            $("#category_filter, #location_filter, #item_filter, #status_filter, #price_from, #price_to, #date_from, #date_to").change(function() {
                enableFilterButtons();
            });

            $('#createTenderWindow').on('shown.bs.modal', function () {
                $('.datepicker').addClass('modal_datepicker');

                $("#create_tender_unit_category").select2({
                    placeholder: "Select a category"
                });

                $("#create_tender_unit_item").select2({
                    placeholder: "Select a item"
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

            $.getJSON('http://localhost:8080/tenders/statuses', {
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

            $.getJSON('/tenders/items', function(data){
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                }

                $('#item_filter').html(html);
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
        });

        function clearFilters() {
            disableFilterButtons();
            $("#price_from").val("");
            $("#price_to").val("");
            $("#date_from").val("");
            $("#date_to").val("");
            $("#location_filter").select2('val', 'All');
            $("#category_filter").select2('val', 'All');
            $('#item_filter option').eq(0).prop('selected', true);
            $('#status_filter option').eq(0).prop('selected', true);
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
            //TO:DO apply filters
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row">
        <!--navigation-->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <ul class="nav navbar-nav navbar-left nav_buttons">
                        <li><a class="navbar-brand" href="/">UATender</a></li>
                        <li><button type="button" class="btn btn-default nav_button" disabled>My tenders</button></li>
                        <li><button type="button" class="btn btn-default nav_button" disabled>My deals</button></li>
                        <li><button type="button" class="btn btn-default nav_button" data-toggle="modal" data-target="#createTenderWindow">Create tender</button></li>
                    </ul>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/login">Log in</a></li>
                        <li><a href="/signup">Sign up</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--navigation-->

        <!--main-->
        <div class="page_body">
            <!-- sidebar -->
            <div class="col-md-3">

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
            <div class="col-md-9">
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
                        <tr>
                            <th>Name</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Location</th>
                            <th>Suitable price</th>
                            <th>Status</th>
                            <th>Proposals</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var="tender" items="${tenders}">
                            <tr>
                                <td align="center">
                                    <a href="<spring:url value="/tenders/${tender.id}.html" />">
                                            ${tender.title}
                                    </a>
                                </td>
                                <td align="center"><c:out value="${tender.author.firstName}"></c:out></td>
                                <td align="center"><c:out value="Build"></c:out></td>
                                <td align="center"><c:out value="Lviv"></c:out></td>
                                <td align="center"><c:out value="$${tender.suitablePrice}"></c:out></td>
                                <td align="center"><c:out value="${tender.status.name}"></c:out></td>
                                <td align="center"><c:out value="6"></c:out></td>
                                <td align="center">
                                    <div class="control-group">
                                        <select class="form-control items_number_dropdown">
                                            <option>View</option>
                                            <option>Delete</option>
                                            <option>Close</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
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
        <div class="footer">
            <div class="pull-left">
                <p>Copyright © UATender 2014
            </div>
        </div>
        <!-- footer -->

    </div>
</div>

<div id="createTenderWindow" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">New tender</h4>
      </div>
      <div class="modal-body">
        <div>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="create_tender_title" class="col-sm-2 control-label">Title*:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="create_tender_title" placeholder="Title of the tender">
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_price" class="col-sm-2 control-label">Suitable price:</label>
                    <div class="col-sm-2">
                      <input type="text" class="form-control" id="create_tender_price" placeholder="Suitable price">
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_enddate" class="col-sm-2 control-label">End date:</label>
                    <div class="col-sm-3">
                        <div class="input-group date pull-left" id="create_tender_enddate" data-date="" data-date-format="dd-mm-yyyy">
                            <input id="create_tender_enddate_input" class="form-control" size="10" type="text" value="">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_location" class="col-sm-2 control-label">Location:</label>
                    <div class="col-sm-6">
                        <select id="create_tender_location" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1" ></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_location" class="col-sm-2 control-label">Description:</label>
                    <div class="col-sm-10">
                        <textarea id="create_tender_description" class="form-control" rows="5"></textarea>
                    </div>
                </div>

                <div class="">
                    <div><label class="control-label">New unit:</label></div>
                    <div class="row addunit_body">
                        <div class="col-sm-12">
                            <div class="col-sm-2 addunit_body_item">
                                <div class="form-group">
                                    <div>Category*:</div>
                                    <div class="selectpicker">
                                        <select id="create_tender_unit_category" multiple="multiple" class="populate placeholder select2-offscreen category_selector" tabindex="-1" ></select>
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-2 addunit_body_item">
                                <div class="form-group btn-group" data-toggle="buttons-radio">
                                    <div>Type*:</div>
                                    <div class="radio selectpicker">
                                        <label><input id="create_tender_unit_type_product" name="create_tender_unit_type" value="" type="radio"/>Product</label>
                                        <label><input id="create_tender_unit_type_service" name="create_tender_unit_type" value="" type="radio"/>Service</label>
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-2 addunit_body_item">
                                <div class="form-group">
                                    <div>Item*:</div>
                                    <div class="selectpicker">
                                        <select id="create_tender_unit_item" multiple="multiple" class="populate placeholder select2-offscreen category_selector" tabindex="-1" ></select>
                                        <input type="text" class="form-control" id="create_tender_unit_newitem" placeholder="New item">
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-1 addunit_body_item">
                                <div class="form-group">
                                    <div>Quantity*:</div>
                                    <div class="selectpicker">
                                        <input type="text" class="form-control" id="create_tender_unit_quantity" placeholder="0">
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-4 addunit_body_item">
                                <div class="form-group">
                                    <div>Measurement*:</div>
                                    <div class="row">
                                        <select id="create_tender_unit_measurement" class="form-control selectpicker col-sm-6">
                                            <option>All</option>
                                            <option>Ketchup</option>
                                            <option>Relish</option>
                                        </select>

                                        <button type="button" class="btn btn-primary pull-right col-sm-6">Add</button>
                                    </div>
                                </div> 
                            </div>
                        </div>    
                    </div>   
                </div>

                <div class="">
                    <div><label class="control-label">Units:</label></div>
                    <div>
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td align="center">Name</td>
                                <td align="center">Type</td>
                                <td align="center">Category</td>
                                <td align="center">Quantity</td>
                                <td align="center">Measurement</td>
                                <td align="center">Action</td>
                            </tr>
                        </table>    
                    </div>   
                    <div class="create_tender_units">
                        <table class="table table-bordered table-striped">
                            <tbody>
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center"><i class="icon-trash icon-white"></i></td>
                                </tr>
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center">6</td>
                                </tr>
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center">6</td>
                                </tr>
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center">6</td>
                                </tr>   
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center">6</td>
                                </tr>  
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center">6</td>
                                </tr>  
                                <tr>
                                    <td align="center">Ivan</td>
                                    <td align="center">Build</td>
                                    <td align="center">Lviv</td>
                                    <td align="center">$1000000.0</td>
                                    <td align="center">Open</td>
                                    <td align="center">6</td>
                                </tr>                                                                                                                     
                            </tbody>                          
                        </table>
                    </div>
                </div>


            </form>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary">Create</button>
      </div>
    </div>
  </div>
</div>

</body>


</html>

