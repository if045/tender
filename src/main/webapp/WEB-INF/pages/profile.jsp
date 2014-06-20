<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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
    <script type='text/javascript' src='<c:url value="../resources/js/jquery.validate.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/validations.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/star-rating.min.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/registration.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/profile.js"/>'></script>

</head>

<body>

<jsp:include page="editProfileInfoModal.jsp"/>

<div class="container">
    <div class="row">
        <!--navigation-->
        <jsp:include page="header.jsp"/>
    </div>

    <%-- Page containning --%>
    <div class="page_body">
        <div class="panel-primary">
            <div class="panel-heading">
                <h1 class="panel-title">Profile</h1>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="panel-group">
                        <form class="form-horizontal" role="form">
                            <div class="col-md-4">

                                <!-- Avatar -->
                                <div class="input-group backdown">
                                    <div>
                                        <img src="//placehold.it/300" class="avatar img-circle" alt="avatar">
                                    </div>
                                </div>
                                <!-- Avatar -->

                                <!-- Rating info -->
                                <div class="panel-group">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapseRating">
                                                    Rating
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseRating" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        <div class="input-group">
                                                            <div class="form-group">
                                                                <h4 class="col-sm-6 control-label">Communication</h4>
                                                                <div class="col-sm-2">
                                                                    <input type="number" class="rating" min="0" max="5" step="0.5" data-size="xs" data-show-clear="false" data-show-caption="false">
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <h4 class="col-sm-6 control-label">Speed</h4>
                                                                <div class="col-sm-2">
                                                                    <input type="number" class="rating" min="0" max="5" step="0.5" data-size="xs" data-show-clear="false" data-show-caption="false">
                                                                </div>
                                                            </div>
                                                            <div class="form-group text-left">
                                                                <h4 class="col-sm-6 control-label">Logistic</h4>
                                                                <div class="col-sm-2">
                                                                    <input type="number" class="rating" min="0" max="5" step="0.5" data-size="xs" data-show-clear="false" data-show-caption="false">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Rating info -->
                            </div>
                        </form>

                        <form class="form-horizontal" role="form">
                            <div class="col-md-8">

                                <div class="panel-group" id="accordion">
                                    <!-- Personal info -->
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapsePerson">
                                                    Personal info
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapsePerson" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        <div class="input-group leftbackdown">
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> First name: </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> Slavik </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Last name: </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> Kynyk </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Email: </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> GUSbYa@gmail.com </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Phone number: </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> 0941243745 </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Birthday: </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> 1991/01/09 </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Person </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> Legal person </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <button type="button"
                                                                            class="btn btn-primary btn-lg"
                                                                            data-toggle="modal"
                                                                            data-target="#editProfile">
                                                                        Edit
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Personal info -->

                                    <!-- Company info -->
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapseCompany">
                                                    Company info
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseCompany" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        <div class="input-group">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> Company name: </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> GUSbCompany </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> City: </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> Ivano-Frankivsk </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> Street: </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> Mazepy </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> Building number: </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> 10 </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> Postcode: </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> 834634 </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> Email </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> GUSbCompany@gmail.com </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <h4> SRN number </h4>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <h4> 4534636 </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <button type="button"
                                                                            class="btn btn-primary btn-lg"
                                                                            data-toggle="modal"
                                                                            data-target="#editProfile">
                                                                        Edit
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Company info -->

                                    <!-- Trade Sphere info -->
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapseTradeSphere">
                                                    Trade Sphere info
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseTradeSphere" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        <div class="input-group">
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Locations: </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> Lviv, Ivano-Frankivsk, Kiiv </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <h4> Categories </h4>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h4> Computers, Giutars, Drums </h4>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-8">
                                                                    <button type="button"
                                                                            class="btn btn-primary btn-lg"
                                                                            data-toggle="modal"
                                                                            data-target="#editProfile">
                                                                        Edit
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Company info -->
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <%-- Page containning --%>

    <!--footer -->
    <jsp:include page="footer.jsp"/>
</div>
<!-- Container -->

</body>

</html>
