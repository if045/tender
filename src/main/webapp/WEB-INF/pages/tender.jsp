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
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/fixedTendersTables.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap-datepicker.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/jquery.validate.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/validations.js"/>'></script>

    <script type='text/javascript' src='<c:url value="../resources/js/tenders.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/tender.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/tenderview.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/addTenderModal.js"/>'></script>
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
                <div class="row col-md-12 pull-left">
                    <div id="tender_title"></div>
                </div>

                <!-- information about tender -->
                <form class="form-horizontal" role="form" id="edit_tender_form">
                    <div class="form-group col-md-3">
                        <label for="endDate" class="control-label col-md-5">End date</label>
                        <div class="input-group date col-md-7" id="endDate" data-date="" data-date-format="dd-mm-yyyy">
                            <input id="date_to" class="form-control custom_datepicker" size="10" type="text" value="">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                        </div>
                        <label for="suitablePrice" class="control-label col-md-5">Suitable price</label>
                        <div id="suitablePrice"></div>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="status" class="control-label col-md-3">Status</label>
                        <div class="col-md-8">
                            <select id="status" class="form-control"></select>
                        </div>
                        <label for="locations" class="control-label col-md-3">Locations</label>
                        <div id="locations"></div>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="description" class="control-label col-md-2">Description</label>
                        <div class="col-md-10">
                            <textarea id="description" class="form-control" rows="2" name="description"></textarea>
                        </div>
                    </div>
                </form>
                <!-- information about tender -->

                <div class="row col-md-12">
                    <security:authorize access="hasRole('CUSTOMER')">
                        <button type="submit" class="btn btn-primary col-md-1 col-md-offset-10"
                                id="save_tender_button" onclick="saveTenderAfterUpdate();" disabled>Save</button>
                    </security:authorize>
                </div>

                <div class="row">
                    <label for="units" class="col-md-6 col-md-offset-1 control-label"><h4>Units</h4></label>
                    <label for="proposals" class="col-md-5 control-label"><h4>Proposals</h4></label>
                </div>

                <div class="row">
                    <div class="col-md-7 height">
                        <table class="table table-bordered table-striped fancyTable" id="units">
                            <thead>
                                <th align="center"><span class="glyphicon glyphicon-check"></span></th>
                                <th><span class="sortable asc glyphicon glyphicon-chevron-up" name="item.name">Name</span></th>
                                <th><span class="sortable" name="item.type">Type</span></th>
                                <th><span class="sortable" name="item.category">Category</span></th>
                                <th><span class="sortable" name="quantity">Quantity</span></th>
                                <th>Bids</th>
                                <th>Seller price</th>
                                <th>Deal</th>
                            </thead>
                            <tbody id="unitsTable"></tbody>
                        </table>
                    </div>

                    <div class="col-md-4 height">
                        <table class="table table-bordered table-striped" id="head_proposals">
                            <thead>
                                <th>Seller</th>
                                <th>Units</th>
                                <th>Total price</th>
                                <th>Deal</th>
                            </thead>
                            <tbody id="proposals"/>
                        </table>
                        <div id="no_proposals_message">
                            <h4>There is no proposals</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-7">
                        <label for="message" class="col-md-6 control-label">AuthorName dd/mm/yyyy hh:mm</label>
                        <textarea id="message" class="form-control" rows="3" disabled>Message from this author</textarea>
                    </div>
                    <div class="col-md-5">
                        <label for="proposalDescription" class="col-md-6 control-label">Proposal description</label>
                        <textarea id="proposalDescription" class="form-control" rows="3" disabled>There is proposal description and some allowance</textarea>
                    </div>
                </div>
                <!--main-->
            </div>
            <!--footer -->
            <jsp:include page="footer.jsp"/>
            <!-- footer -->
        </div>
    </div>

    <!-- success create deal modal window -->
    <div class="modal fade" id="success_create_deal" tabindex="-1" role="dialog" hidden="">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Success</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <h5>You have successfully signed an agreement!</h5>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal">Good</button>
                </div>
            </div>
        </div>
    </div>
    <!-- success create deal modal window -->

<!--create tender modal -->
<jsp:include page="createTender.jsp"/>
<!--create tender modal -->

<!-- new tender modal window -->
<jsp:include page="newTenderCreated.jsp"/>
<!-- new tender modal window -->

</body>
</html>
