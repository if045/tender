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
                                                <span id="legal-radio" class="required-radio">
                                                    Legal person
                                                </span>
                                        </div>
                                        <div class="col-md-5">
                                            <input id="private_to_update" type="radio" name="person" value="private">
                                                <span id="private-radio" class="required-radio">
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
                                                    <input name="password" id="password_to_update"
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
                                                    <input name="confirm_password" id="confirm_password_to_update"
                                                           type="password" class="form-control required-field"
                                                           placeholder="Confirm password">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <div class="row">
                                            <button type="button" onclick="updateUserData()" class="btn btn-success">Confirm</button>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- Edit user credentials --%>

                <%--Edit company data--%>
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
                                                    <input name="company_name" type="text" class="form-control" placeholder="Name">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row backdown">
                                        <div class="col-md-12">
                                            <div class="form-group has-feedback">
                                                <div class="input-group">
                                                    <span class="input-group-addon glyphicon glyphicon-home"></span>
                                                    <input name="city" type="text" class="form-control" placeholder="City">
                                                    <span class="input-group-addon"></span>
                                                    <input name="street" type="text" class="form-control" placeholder="Street">
                                                    <span class="input-group-addon"></span>
                                                    <input name="building_namber" type="text" class="form-control" placeholder="Building number">
                                                    <span class="input-group-addon"></span>
                                                    <input name="postcode" type="text" class="form-control" placeholder="Postcode">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row backdown">
                                        <div class="col-md-12">
                                            <div class="form-group has-feedback">
                                                <div class="input-group">
                                                    <span class="input-group-addon">@</span>
                                                    <input name="email" type="text" class="form-control" placeholder="Email">
                                                    <span class="input-group-addon"></span>
                                                    <input name="srn_number" type="text" class="form-control" placeholder="SRN number">
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
                                            <button type="button" onclick="updateCompanyData()" class="btn btn-success">Confirm</button>
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

                                <div class="modal-footer">
                                    <div class="row">
                                        <button type="button" onclick="updateTradeSphereData()" class="btn btn-success">Confirm</button>
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
