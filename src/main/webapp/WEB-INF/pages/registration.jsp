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
    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/registration.js"/>'></script>
</head>

<body>

<div class="container">
    <div class="row">
        <!--navigation-->
        <jsp:include page="header.jsp"/>

        <div class="page_body">
            <div class="panel-heading">
                <h1 class="panel-title">Sign up</h1>
            </div>

            <div class="row">
                <!-- User's log in data -->
                <div class="col-md-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Creds</h3>
                        </div>
                        <div class="panel-body">
                            <div class="panel-group">
                                <div class="row backdown">
                                    <div class="col-md-12">
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-user"></span>
                                            <input type="text" class="form-control" placeholder="User name (email)">
                                        </div>
                                    </div>
                                </div>

                                <div class="row backdown">
                                    <div class="col-md-12">
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-wrench"></span>
                                            <input type="text" class="form-control" placeholder="Password">
                                        </div>
                                    </div>
                                </div>

                                <div class="row backdown">
                                    <div class="col-md-12">
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-wrench"></span>
                                            <input type="text" class="form-control" placeholder="Confirm password">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- User's log in data -->
            </div>
        </div>
    </div>
    <!--footer -->
    <jsp:include page="footer.jsp"/>
</div>
<!-- Container -->
</body>

</html>
