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


    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>

</head>
<body>
<div class="row col-sm-2">
    <div class="col-sm-12">
        <div class="dropdown">
            <button class="btn btn-default" id="pickButton">
                Select One...
            </button>
            <button class="btn btn-default" data-toggle="dropdown">
                <span class="caret"></span>
            </button>
            <%--<ul class="dropdown-menu" id="reasonDropdown">
                <c:forEach var="tender" items="${tenders}">
                    <c:forEach var="unit" items="${tender.units}">
                        <li><a href="#" tabindex="-1"><c:out value="${unit.item.name}" /></a></li>
                    </c:forEach>
                </c:forEach>
            </ul>--%>
            <ul class="dropdown-menu" id="reasonDropdown">
                <c:forEach var="location" items="${locations}">
                    <li><a href="#" tabindex="-1"><c:out value="${location.name}" /></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="col-sm-12">
        <table class="table table-bordered table-striped">
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>CreateDate</th>
                <th>EndDate</th>
                <th>Status</th>
                <th>SuitablePrice</th>
            </tr>
            <c:forEach var="tender" items="${tenders}">
                <tr>
                    <td align="center">
                        <a href="<spring:url value="/tenders/${tender.id}.html" />">
                                ${tender.title}
                        </a>
                    </td>
                    <td align="center"><c:out value="${tender.author.firstName}"></c:out></td>
                    <td align="center"><c:out value="${tender.createDate}"></c:out></td>
                    <td align="center"><c:out value="${tender.endDate}"></c:out></td>
                    <td align="center"><c:out value="${tender.status.name}"></c:out></td>
                    <td align="center"><c:out value="${tender.suitablePrice}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
</div>

<script type="text/javascript">
    (function () {
        "use strict"

        var $pickButton = $("#pickButton");

        $("#reasonDropdown li a").on("click", function () {
            var reason = $(this).text();
            $pickButton.text(reason);
        });
    })();
</script>

</body>


</html>