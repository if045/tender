<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>tenderViewFor</title>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/bootstrap.min.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/datepicker.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/select2.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/style.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap-datepicker.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>


</head>
<body>
<div class="container">
    <!--navigation-->
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">TenderViewFor....</a>
        </div>
    </div>

    <!--main-->
    <div class="page_body">
        <div class="head_body">
            <div class="row">
                <div class="col-md-2">
                    <div>End date:</div>
                    <div>Status:</div>
                    <div>Suitable price:</div>
                    <div>Location:</div>
                    <div>Description:</div>
                    <textarea class="input-large"></textarea>

                </div>
                <div class="col-md-4">
                    <div> date</div>
                    <div> date</div>
                    <div> date</div>
                    <div> date</div>
                </div>
            </div>
        </div>

        <div class="body_body">
            <div class="row2">
                <div class="col-md-6">Items</div>
                <small>border table</small>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Box</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Measurement</th>
                        <th>Seller price</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="unit" items="${tenderView}">
                                <tr>
                                    <td align="center"><input type="checkbox" class="checkbox" />
                                    </td>
                                    <td align="center"><c:out value="${unit.item.name}"></c:out></td>
                                    <td align="center"><c:out value="${unit.item.type}"></c:out></td>
                                    <td align="center"><c:out value="${unit.item.category.name}"></c:out></td>
                                    <td align="center"><c:out value="${unit.quantity}"></c:out></td>
                                    <td align="center"><c:out value="${unit.measurement.name}"></c:out></td>
                                    <td align="center"><c:out value="sdfdsf"></c:out></td>
                                </tr>
                            </c:forEach>
                        </tr>
                    </tbody>
                </table>


                <div class="col-md-6">Proposal</div>


                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>seller</th>
                        <th>number of bids</th>
                        <th>total price</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>