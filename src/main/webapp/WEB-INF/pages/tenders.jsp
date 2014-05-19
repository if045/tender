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


</body>


</html>