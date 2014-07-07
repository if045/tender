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
    <script type='text/javascript' src='<c:url value="../resources/js/jquery.validate.min.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/star-rating.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>

    <script type='text/javascript' src='<c:url value="../resources/js/validations.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/header.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/user.js"/>'></script>
    <script type="text/javascript" src='<c:url value="../resources/js/cookie.js"/>'></script>

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
                                                <dl class="dl-horizontal">
                                                    <dt> <p class="text-left"> <strong> Communication </strong> </p> </dt>
                                                    <dd>
                                                        <input type="number" class="rating" min="0" max="5" step="0.5"
                                                               data-size="xs" data-show-clear="false"
                                                               data-show-caption="false" data-readonly="true" value=""
                                                               id="communication_rating">
                                                    </dd>

                                                    <dt> <p class="text-left"> <strong> Speed </strong> </p> </dt>
                                                    <dd>
                                                        <input type="number" class="rating" min="0" max="5" step="0.5"
                                                               data-size="xs" data-show-clear="false"
                                                               data-show-caption="false" data-readonly="true" value=""
                                                               id="speed_rating">
                                                    </dd>

                                                    <dt> <p class="text-left"> <strong> Logistic </strong> </p> </dt>
                                                    <dd>
                                                        <input type="number" class="rating" min="0" max="5" step="0.5"
                                                               data-size="xs" data-show-clear="false"
                                                               data-show-caption="false" data-readonly="true" value=""
                                                               id="logistic_rating">
                                                    </dd>
                                                </dl>
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
                                    <div class="panel panel-info" id="personal_info">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapsePerson">
                                                    Personal info
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapsePerson" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <dl class="dl-horizontal">
                                                    <dt> <p class="text-left"> <strong> First name </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="first_name_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Last name </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="last_name_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Role </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="roles_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> E-mail </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <a href="mailto:#" id="login_info"> </a> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Phone number </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="telephone_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Birthday </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="birthday_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Person </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="person_info"> </em> </p> </dd>
                                                </dl>
                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <button type="button"
                                                                class="btn btn-primary btn-lg"
                                                                data-toggle="modal"
                                                                data-target="#editProfile"
                                                                onclick="showEditPersonalPanel()"
                                                                id="edit_personal_info">
                                                            Edit
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Personal info -->

                                    <!-- Company info -->
                                    <div class="panel panel-info" id="company_info">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapseCompany">
                                                    Company info
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseCompany" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <dl class="dl-horizontal">
                                                    <dt> <p class="text-left"> <strong> Company name </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="company_name_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> City </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="city_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Street </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="street_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Building number </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="build_number_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> Postcode </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="postcode_info"> </em> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> E-mail </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <a href="mailto:#" id="email_info"> </a> </p> </dd>

                                                    <dt> <p class="text-left"> <strong> SRN </strong> </p> </dt>
                                                    <dd> <p class="text-left"> <em id="srn_info"> </em> </p> </dd>
                                                </dl>
                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <button type="button"
                                                                class="btn btn-primary btn-lg"
                                                                data-toggle="modal"
                                                                data-target="#editProfile"
                                                                onclick="showEditCompanyPanel()"
                                                                id="edit_company_info">
                                                            Edit
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Company info -->

                                    <security:authorize access="hasRole('SELLER')">
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
                                                    <dl class="dl-horizontal">
                                                        <dt> <p class="text-left"> <strong> Locations </strong> </p> </dt>
                                                        <dd> <p class="text-left"> <em id="locations_info"> </em> </p> </dd>

                                                        <dt> <p class="text-left"> <strong> Categories </strong> </p> </dt>
                                                        <dd> <p class="text-left"> <em id="categories_info"> </em> </p> </dd>
                                                    </dl>
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <button type="button"
                                                                    class="btn btn-primary btn-lg"
                                                                    data-toggle="modal"
                                                                    data-target="#editProfile"
                                                                    onclick="showEditTradeSpherePanel()"
                                                                    id="edit_trade_sphere_info">
                                                                Edit
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Trade Sphere info -->
                                    </security:authorize>
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

<jsp:include page="createTender.jsp"/>
<jsp:include page="newTenderCreated.jsp"/>
</body>

</html>
