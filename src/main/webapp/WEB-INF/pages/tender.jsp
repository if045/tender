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
            $('#endDate').datepicker({
                format: 'yyyy/mm/dd',
                startDate: '-3d'
            });
            showUnit();
        });
        var tender;

        function showUnit() {

            $.getJSON('/tenders/view'+tender+'/unit', function (data) {
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    tender = data[i].tender_id
                    html += '<tr><td>' +' <input type="checkbox">' + '</td>' +
                            '<td>' + data[i].unit_name + '</td>' +
                            '<td>' + data[i].type + '</td>' +
                            '<td>' + data[i].category_name + '</td>' +
                            '<td>' + data[i].quantity + '</td>' +
                            '<td>' + data[i].measurement_name + '</td>' +
                            '<td>' + data[i].price + '</td>' +
                            '<td>' + '<button type="submit" class="btn btn-default" disabled>Deal</button>' + '</td></tr>';
                }
                $('#unitTable').html(html);
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
                <!-- content -->
                <div class="col-md-12">
                    <div class="row">
                        <div class="pull-left">
                            <h3>Tender title</h3>
                        </div>
                    </div>
                </div>

            <!-- information about tender -->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="endDate" class="col-md-2 control-label">End date</label>
                        <div class="col-md-4">
                            <div class="input-group date" id="endDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                                <input id="date_to" class="form-control" size="10" type="text" value="">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">Status</label>
                        <div class="col-md-2">
                            <select id="status" class="form-control selectpicker"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="suitablePrice" class="col-md-2 control-label">Suitable price</label>
                        <p class="form-control-static col-md-2" id="suitablePrice">0000.00</p>
                    </div>
                    <div class="form-group">
                        <label for="locations" class="col-md-2 control-label">Locations</label>
                        <p class="form-control-static col-md-2" id="locations">Some locations</p>
                        <button type="submit" class="btn btn-default col-md-1 col-md-offset-1">Save</button>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-2 control-label">Description</label>
                        <div class="col-md-4">
                            <textarea id="description" class="form-control" rows="3">There is some description</textarea>
                        </div>
                    </div>
                </form>

                <div class="row">
                    <label for="units" class="col-md-7 control-label">Units</label>
                    <label for="proposals" class="col-md-5 control-label">Proposals</label>
                </div>

                <div class="row">
                    <div class="col-md-7">
                        <table class="table table-bordered table-striped" id="units">
                            <thead>
                                <th></th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>Measurement</th>
                                <th>Seller price</th>
                                <th>Deal</th>
                            </thead>
                            <tbody id="unitTable"></tbody>
                        </table>
                    </div>
                    <div class="col-md-5">
                        <table class="table table-bordered table-striped" id="proposals">
                            <tr>
                                <th>Seller</th>
                                <th>Number of bids</th>
                                <th>Total price</th>
                                <th>Deal</th>
                            </tr>
                            <tr>
                                <td align="center">Bob</td>
                                <td align="center">2</td>
                                <td align="center">451</td>
                                <td align="center"><button type="submit" class="btn btn-default" disabled>Deal</button></td>
                            </tr>
                            <c:forEach var="proposal" items="${proposals}">
                                <tr>
                                    <td align="center"><c:out value="${unit.sellerName}"/></td>
                                    <td align="center"><c:out value="${unit.numberOfBids}"/></td>
                                    <td align="center"><c:out value="${unit.totalPrice}"/></td>
                                    <td align="center"><button type="submit" class="btn btn-default" disabled>Deal</button></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-7">
                        <label for="message" class="col-md-6 control-label">AuthorName dd/mm/yyyy hh:mm</label>
                        <textarea id="message" class="form-control" rows="3" disabled>Message from this author</textarea>
                    </div>
                    <div class="col-md-5">
                        <textarea id="proposalDescription" class="form-control" rows="3" disabled>There is proposal description and some allowance</textarea>
                    </div>
                </div>
            </div>
            <!--footer -->
            <jsp:include page="footer.jsp"/>
            <!-- footer -->
        </div>
    </div>
</body>
</html>
