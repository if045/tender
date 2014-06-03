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

    <script type="text/javascript">
        $(document).ready(function() {
            $('#endDate').datepicker({
                format: 'mm-dd-yyyy',
                startDate: '-3d'
            });
        });
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
                <!-- content -->
                <div class="col-md-12">
                    <div class="row">
                        <div class="pull-left">
                            <h3>Tender title</h3>
                        </div>
                    </div>
                </div>

            <!-- information about tender -->
                <form class="form-horizontal col-md-7" role="form">
                    <div class="form-group">
                        <label for="endDate" class="col-md-4 control-label">End date</label>
                        <div class="col-md-5">
                            <div class="input-group date" id="endDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                                <input id="date_to" class="form-control" size="10" type="text" value="">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-md-4 control-label">Status</label>
                        <div class="col-md-2">
                            <select id="status" class="form-control selectpicker"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="suitablePrice" class="col-md-4 control-label">Suitable price</label>
                        <p class="form-control-static col-md-2" id="suitablePrice">0000.00</p>
                    </div>
                    <div class="form-group">
                        <label for="locations" class="col-md-4 control-label">Locations</label>
                        <p class="form-control-static col-md-4" id="locations">Some locations</p>
                        <button type="submit" class="btn btn-default col-md-1 col-md-offset-1">Save</button>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-4 control-label">Description</label>
                        <div class="col-md-6">
                            <textarea id="description" class="form-control" rows="3">There is some description</textarea>
                        </div>
                    </div>
                </form>

                <div class="right" hidden="">
                    <div class="row">
                        <div class="col-md-5">
                            <textarea id="proposalDescription" class="form-control" rows="3" disabled>There is proposal description and some allowance</textarea>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label for="units" class="col-md-6 col-md-offset-1 control-label"><h4>Units</h4></label>
                    <label for="proposals" class="col-md-5 control-label"><h4>Proposals</h4></label>
                </div>

                <div class="row">
                    <div class="col-md-7">
                        <table class="table table-bordered table-striped" id="units">
                            <tr>
                                <td align="center"><span class="glyphicon glyphicon-check"></span></td>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>Bids</th>
                                <th>Seller price</th>
                                <th>Deal</th>
                            </tr>
                            <tr>
                                <td align="center"><input type="checkbox"></td>
                                <td align="center">Bricks</td>
                                <td align="center">P</td>
                                <td align="center">Building</td>
                                <td align="center">23 kg</td>
                                <td align="center">4</td>
                                <td align="center"></td>
                                <td align="center"><button type="submit" class="btn btn-default" disabled>Deal</button></td>
                            </tr>
                            <c:forEach var="unit" items="${units}">
                                <tr>
                                    <td align="center"><input type="checkbox"></td>
                                    <td align="center"><c:out value="${unit.name}"/></td>
                                    <td align="center"><c:out value="${unit.type}"/></td>
                                    <td align="center"><c:out value="${unit.category}"/></td>
                                    <td align="center"><c:out value="${unit.quantity} ${unit.measurement}"/></td>
                                    <td align="center"><c:out value="${unit.bids}"/></td>
                                    <td align="center"></td>
                                    <td align="center"><button type="submit" class="btn btn-default" disabled>Deal</button></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="col-md-4">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <th>Seller</th>
                                <th>Bids</th>
                                <th>Total price</th>
                                <th>Deal</th>
                            </thead>
                            <tbody id="proposals"/>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-7">
                        <label for="message" class="col-md-6 control-label">AuthorName dd/mm/yyyy hh:mm</label>
                        <textarea id="message" class="form-control" rows="3" disabled>Message from this author</textarea>
                    </div>
                    <%--<div class="col-md-5">
                        <textarea id="proposalDescription" class="form-control" rows="3" disabled>There is proposal description and some allowance</textarea>
                    </div>--%>
                </div>
            </div>
            <!--footer -->
            <jsp:include page="footer.jsp"/>
            <!-- footer -->
        </div>
    </div>
</body>
</html>
