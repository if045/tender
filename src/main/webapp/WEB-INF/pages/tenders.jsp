<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


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
    <script type='text/javascript' src='<c:url value="../resources/js/jquery.validate.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/validations.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/cookie.js"/>'></script>

    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/proposal.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/tenders.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/addTenderModal.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/header.js"/>'></script>
</head>
<body>

<div class="container">
    <div class="row">
        <!--navigation-->
        <jsp:include page="header.jsp"/>
        <!--navigation-->

        <!--main-->
        <div class="page_body">
            <div class="row">
                <div class="col-md-12 search_bar">
                    <div class="pull-left"></div>
                        <div class="pull-right">
                            <form id="search_form" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_tenders" type="text" class="form-control"
                                           placeholder="Search by tender title...">
                                </div>
                            </form>
                        </div>
                </div>
            </div>

            <!-- sidebar -->
            <div class="col-md-3">

                <div class="panel panel-default sidebar">
                    <form id="tendersHome_validation">
                        <div class="panel-heading">
                            <h3 class="panel-title">Tender filter</h3>
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
                                    <input id="price_from" type="text" class="pull-left form-control suitable_price" placeholder="From" name="price">
                                    <input id="price_to" type="text" class="pull-right form-control suitable_price" placeholder="To" name="price">
                                </div>
                                <div class="error"></div>
                            </div>
                            <div>
                                <div>Active</div>
                                <div class="date_filter">
                                    <div class="input-group date pull-left" id="startDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                                        <input id="date_from" class="form-control" size="10" type="text" value="">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                    </div>
                                    <div class="input-group date pull-right" id="endDate" data-date="29-03-2015" data-date-format="dd-mm-yyyy">
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
                    </form>
                </div>

            </div>
            <!-- sidebar -->

            <!-- content -->
            <div class="col-md-9">
                <!-- items -->
                <div id="user_message" class="row"></div>
                <div id="tender_items" class="row">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th align="center" id="tender_title">
                                <span class="glyphicon sortable"><span>Title</span></span>
                            </th>
                            <th align="center" id="tender_author">
                                <span class="glyphicon sortable"><span>Author</span></span>
                            </th>
                            <th align="center" id="tender_category"><span>Category</span></th>
                            <th align="center" id="tender_location"><span>Location</span></th>
                            <th align="center" id="tender_suitable_price">
                                <span class="glyphicon sortable"><span>Suitable Price</span></span>
                            </th>
                            <th align="center" id="tender_status">
                                <span class="glyphicon sortable"><span>Status</span></span>
                            </th>
                            <th align="center" id="tender_proposals">
                                <span class="glyphicon sortable"><span>Proposals</span></span>
                            </th>
                            <th align="center" id="tender_action"><span>Action</span></th>
                        </tr>
                        </thead>
                        <tbody id="tenders"></tbody>
                    </table>
                </div>
                <!-- items -->

                <!-- pagination -->
                <div id="pagination" class="row">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <ul class="pagination page_pagination pull-right"></ul>
                        </div>
                        <div class="pull-right">
                            <div class="control-group">
                                <select id="pagination_itemsnum" class="form-control pull-right items_number_dropdown">
                                    <option value="5">5</option>
                                    <option value="10" selected>10</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
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


    </div>

    <!--footer -->
    <jsp:include page="footer.jsp"/>
    <!-- footer -->
</div>

<!--create tender modal -->
<jsp:include page="createTender.jsp"/>
<!--create tender modal -->

<!--create proposal modal -->
<jsp:include page="createProposal.jsp" />
<!--create proposal modal -->

<!-- close tender modal window -->
<div class="modal fade" id="close_tender_mod_wind" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Attention</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <h4>Are you sure you want to close this tender?</h4>
                    <input id="close_tender_id" type="text" value="" hidden=""/>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">No</button>
                <button class="btn btn-primary" type="button" onclick="closeTender();">Yes</button>
            </div>
        </div>
    </div>
</div>
<!-- close tender modal window -->

<!-- new tender modal window -->
<jsp:include page="newTenderCreated.jsp"/>
<!-- new tender modal window -->

</body>

</html>

