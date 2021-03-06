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
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/star-rating.min.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap-datepicker.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/cookie.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/star-rating.min.js"/>'></script>

    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/feedback.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/addTenderModal.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/tenders.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/conflict.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/deals.js"/>'></script>
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
                <!-- content -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="pull-left">
                            <h3>Deals</h3>
                        </div>
                        <div class="pull-right">
                            <form id="search_form" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_deals" type="text" class="form-control" placeholder="Search by tender title...">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- information about deals -->
                <div id="deals_user_message"></div>
                <div id="deal_items" class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped" id="units">
                            <thead>
                                <tr>
                                    <th id="deal_title">
                                        <span class="glyphicon sortable"><span>Tender title</span></span>
                                    </th>
                                    <th id="deal_date">
                                        <span class="glyphicon sortable glyphicon-chevron-down"><span>Date</span></span>
                                    </th>
                                    <th id="deal_partner">
                                        <span class="glyphicon sortable"><span>Business Partner</span></span>
                                    </th>
                                    <th id="deal_status">
                                        <span class="glyphicon sortable"><span>Status</span></span>
                                    </th>
                                    <th id="deal_sum">
                                        <span class="glyphicon sortable"><span>Sum</span></span>
                                    </th>
                                    <th class="deal_action_field">Action</th>
                                </tr>
                            </thead>
                            <tbody id="deals"></tbody>
                        </table>
                    </div>
                </div>

                <!-- pagination -->
                <div id="pagination" class="row">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <ul class="pagination page_pagination pull-right"></ul>
                        </div>
                        <div class="pull-right">
                            <div class="control-group">
                                <select id="pagination_dealsnum" class="form-control pull-right items_number_dropdown">
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
            <!--footer -->
            <jsp:include page="footer.jsp"/>
            <!-- footer -->
        </div>
    </div>

<!--create tender modal -->
<jsp:include page="createTender.jsp"/>
<!--create tender modal -->

<!--action button conflict-->
<jsp:include page="conflict.jsp"/>
<!--action button conflict-->

<!--action button feedback-->
<jsp:include page="feedback.jsp"/>
<!--action button feedback-->

<!-- new tender modal window -->
<jsp:include page="newTenderCreated.jsp"/>
<!-- new tender modal window -->

<!-- close deal modal window -->
<div class="modal fade" id="close_deal_mod_wind" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Attention</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <h4>Are you sure you want to close this deal?</h4>
                    <input id="close_deal_id" type="text" value="" hidden=""/>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" onclick="closeDeal();">Yes</button>
                <button class="btn btn-default" type="button" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>
<!-- close deal modal window -->

</body>
</html>
