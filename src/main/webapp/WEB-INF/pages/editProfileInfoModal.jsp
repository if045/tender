<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<div id="editProfile" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="editProfile" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit profile</h4>
            </div>
            <div class="modal-body">

                <%-- Edit user data --%>
                <form id="profile_data_validation">
                    <div class="row personal-info" hidden="hidden">
                        <div>
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
                                                        <input name="first_name" id="first_name_to_update"
                                                               type="text" class="form-control required-field"
                                                               placeholder="First name">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-pencil"></span>
                                                        <input name="last_name" id="last_name_to_update"
                                                               type="text" class="form-control required-field"
                                                               placeholder="Last name">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-earphone"></span>
                                                        <input name="phone_number" id="phone_to_update"
                                                               type="tel" class="form-control required-field"
                                                               placeholder="Phone number">
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
                                                    <input class="form-control" id="birth_to_update"
                                                           type="text" value="" placeholder="Birth date">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown radio-toolbar">
                                            <div class="col-md-4">
                                                <input id="legal_to_update" type="radio" name="person" value="legal">
                                                    <span>
                                                        Legal person
                                                    </span>
                                            </div>
                                            <div class="col-md-5">
                                                <input id="private_to_update" type="radio" name="person" value="private">
                                                    <span>
                                                        Private person
                                                    </span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Panel group -->
                                </div>
                                <!-- Panel body -->
                            </div>
                            <!-- Panel -->
                        </div>
                    </div>
                    <%--Edit user data--%>

                    <%-- Edit user credentials --%>
                    <div class="row personal-info" hidden="hidden">
                        <div>
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Edit credentials</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-group">
                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-user"></span>
                                                        <input name="login" id="login_to_update"
                                                               type="text" class="form-control required-field"
                                                               placeholder="User name (email)">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-wrench"></span>
                                                        <input name="password_to_update" id="password_to_update"
                                                               type="password" class="form-control required-field"
                                                               placeholder="Password">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-wrench"></span>
                                                        <input name="confirm_password_to_update" id="confirm_password_to_update"
                                                               type="password" class="form-control required-field"
                                                               placeholder="Confirm password">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <div class="row">
                                                <button id="personal-info" type="button" onclick="updateUserData()" class="btn btn-success">Confirm</button>
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <%-- Edit user credentials --%>

                <%--Edit company data--%>
                <form id="profile_company_data_validation">
                    <div class="row company-info" hidden="hidden">
                        <div>
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
                                                        <input id="company_name_to_update" name="company_name"
                                                               type="text" class="form-control" placeholder="Name">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon glyphicon glyphicon-home"></span>
                                                        <input id="city_to_update" name="city" type="text"
                                                               class="form-control" placeholder="City">
                                                        <span class="input-group-addon"></span>
                                                        <input id="street_to_update" name="street" type="text"
                                                               class="form-control" placeholder="Street">
                                                        <span class="input-group-addon"></span>
                                                        <input id="building_number_to_update" name="building_number"
                                                               type="text" class="form-control" placeholder="Building number">
                                                        <span class="input-group-addon"></span>
                                                        <input id="postcode_to_update" name="postcode" type="text"
                                                               class="form-control" placeholder="Postcode">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row backdown">
                                            <div class="col-md-12">
                                                <div class="form-group has-feedback">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">@</span>
                                                        <input id="email_to_update" name="email" type="text"
                                                               class="form-control" placeholder="Email">
                                                        <span class="input-group-addon"></span>
                                                        <input id="srn_number_to_update" name="srn_number" type="text"
                                                               class="form-control" placeholder="SRN number">
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

                                        <div class="modal-footer">
                                            <div class="row">
                                                <button id="company-info" type="button" onclick="updateCompanyData()" class="btn btn-success">Confirm</button>
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Panel group -->
                                </div>
                                <!-- Panel body -->
                            </div>
                            <!-- Panel -->
                        </div>
                    </div>
                </form>
                <%--Edit company data--%>

                <%--Edit trade sphere--%>
                <div class="row trade-sphere-info" hidden="hidden">
                    <div>
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Trade sphere</h3>
                            </div>
                            <div class="panel-body">
                                <div class="panel-group">
                                    <div class="row backdown">
                                        <div class="col-md-12">
                                            <input type="hidden" id="populate_update_locations_dropdown" onclick="checkValidDropdownInputs()">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="hidden" id="populate_update_categories_dropdown" onclick="checkValidDropdownInputs()">
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <div class="row">
                                        <button id="trade-sphere-info" type="button" onclick="updateTradeSphereData()" class="btn btn-success">Confirm</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                    </div>
                                </div>
                                <!-- Panel group -->
                            </div>
                            <!-- Panel body -->
                        </div>
                        <!-- Panel -->
                    </div>
                </div>
                <%--Edit trade sphere--%>
            </div>
        </div>
    </div>
</div>
