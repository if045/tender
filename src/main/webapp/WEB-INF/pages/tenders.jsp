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

    <script type='text/javascript' src='<c:url value="../resources/js/tenders.js"/>'></script>
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
                            <select id="item_dropdown" multiple="multiple" class
                                    ="populate placeholder select2-offscreen location_selector" tabindex="-1"></select>
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
                            <form id="search_form" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_input" type="text" class="form-control" placeholder="Search...">
                                </div>
                            </form>
                    </div>
                </div>

                <!-- items -->
                <div class="row">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th align="center">Title</th>
                            <th align="center">Author</th>
                            <th align="center">Category</th>
                            <th align="center">Location</th>
                            <th align="center">Suitable Price</th>
                            <th align="center">Status</th>
                            <th align="center">Proposals</th>
                            <th align="center">Action</th>
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

<!--create tender modal -->
<jsp:include page="createtender.jsp"/>
<!--create tender modal -->

<!--create proposal modal -->
<jsp:include page="createProposal.jsp" />
<!--create proposal modal -->

</body>

</html>

