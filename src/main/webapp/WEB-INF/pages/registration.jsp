<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/registration.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap-datepicker.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/jquery.validate.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/validations.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/user.js"/>'></script>
</head>

<body>

<div class="container">
    <div class="row">

            <!--navigation-->
        <jsp:include page="header.jsp"/>

        <div class="col-md-offset-2 col-md-12">
            <div class="page_body">
                <div class="panel-heading">
                    <h1 class="panel-title">Sign up</h1>
                </div>

                <form id="user_data_validation">
                    <div class="row">
                        <!-- User's log in data -->
                        <div id="login_panel" class="col-md-4">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Credentials</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-group">
                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-user"></span>
                                                        <input name="login" id="login" type="text" class="form-control required-field" placeholder="User name (email)">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-wrench"></span>
                                                        <input name="password" id="password" type="password" class="form-control required-field" placeholder="Password">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-wrench"></span>
                                                        <input name="confirm_password" id="confirm_password" type="password" class="form-control required-field" placeholder="Confirm password">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                <select name="role" id="populate_roles_dropdown" onclick="hideShowTradeSphereDataPanel()" multiple="multiple"
                                                        class="populate placeholder select2-offscreen location_selector"
                                                        tabindex="-1">
                                                </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown radio-toolbar">
                                            <div class="col-md-4">
                                                <input id="legal" onclick="showCompanyDataPanel()" type="radio" name="person" value="legal"><span id="legal-radio" class="required-radio"> Legal person</span>
                                            </div>
                                            <div class="col-md-5">
                                                <input id="private" onclick="hideCompanyDataPanel()" type="radio" name="person" value="private"><span id="private-radio" class="required-radio"> Private person</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- User's log in data -->

                        <!-- User's data -->
                        <div id="user_panel" class="col-md-4">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title">User's data</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-group">


                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-pencil"></span>
                                                        <input name="first_name" id="first_name" type="text" class="form-control required-field" placeholder="First name">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-pencil"></span>
                                                        <input name="last_name" id="last_name" type="text" class="form-control required-field" placeholder="Last name">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-earphone"></span>
                                                        <input name="phone_number" id="phone_number" type="tel" class="form-control required-field" placeholder="Phone number">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                <span class="input-group-btn">
                                                    <button class="btn btn-primary" type="button">Choose photo</button>
                                                </span>
                                                    <input name="path" type="text" class="form-control" placeholder="Path">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-6">
                                                <div class="input-group date pull-left" id="birth_date"
                                                     data-date-format="dd-mm-yyyy">
                                                    <span class="input-group-addon fa glyphicon glyphicon-calendar"></span>
                                                    <input id="birthday" class="form-control" type="text" value="" placeholder="Birth date">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Panel group -->
                                </div>
                                <!-- Panel body -->
                            </div>
                            <!-- Panel -->
                        </div>
                        <!-- User's data -->
                    </div>
                </form>
                    <div class="row">
                        <!-- Trade sphere data -->
                        <div id="trade_sphere_panel" class="col-md-8" hidden="hidden">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Trade sphere</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-group">
                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <select id="populate_categories_dropdown" multiple="multiple"
                                                        class="populate placeholder select2-offscreen location_selector" tabindex="-1">
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <select id="populate_locations_dropdown" multiple="multiple"
                                                        class="populate placeholder select2-offscreen location_selector" tabindex="-1">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Panel group -->
                                </div>
                                <!-- Panel body -->
                            </div>
                            <!-- Panel -->
                        </div>
                        <!-- Trade sphere data -->
                    <form id="company_data_validation">
                        <!-- Company data -->
                        <div id="company_panel" class="col-md-8" hidden="hidden">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Company data</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-group">
                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-pencil"></span>
                                                        <input name="company_name" id="company_name" type="text" class="form-control" placeholder="Name">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-home"></span>
                                                        <input name="city" id="city" type="text" class="form-control" placeholder="City">
                                                        <span class="input-group-addon"></span>
                                                        <input name="street" id="street" type="text" class="form-control" placeholder="Street">
                                                        <span class="input-group-addon"></span>
                                                        <input name="building_number" id="building_number" type="text" class="form-control" placeholder="Building number">
                                                        <span class="input-group-addon"></span>
                                                        <input name="postcode" id="postcode" type="text" class="form-control" placeholder="Postcode">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">@</span>
                                                        <input name="email" id="email" type="text" class="form-control" placeholder="Email">
                                                        <span class="input-group-addon"></span>
                                                        <input name="srn_number" id="srn_number" type="text" class="form-control" placeholder="SRN number">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-primary" type="button">Choose logotype</button>
                                                        </span>
                                                        <input name="path" type="text" class="form-control" placeholder="Path">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Panel group -->
                                </div>
                                <!-- Panel body -->
                            </div>
                            <!-- Panel -->
                        </div>
                        <!-- Company data -->
                    </form>
                        <!-- Agreement and confirm -->
                        <div class="col-md-8">
                            <div class="panel">
                                <div class="panel-body">
                                    <div class="panel-group">
                                        <div class="row">
                                            <h3>Accept website <a href="">policy</a>  <input id="agreement" onclick="enableConfirmButton()" type="checkbox"></h3>
                                        </div>
                                        <div class="row">
                                            <button id="confirm_button" onclick="addRegisteredUser()" class="btn btn-success" type="button" disabled>Confirm</button>
                                        </div>
                                    </div>
                                    <!-- Panel group -->
                                </div>
                                <!-- Panel body -->
                            </div>
                            <!-- Panel -->
                        </div>
                        <!-- Agreement and confirm -->
                    </div>
            </div>
        </div>
    </div>
    <!--footer -->
    <jsp:include page="footer.jsp"/>
</div>
<!-- Container -->
</body>

</html>
