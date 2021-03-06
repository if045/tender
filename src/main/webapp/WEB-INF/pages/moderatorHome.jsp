<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Moderator Home</title>

    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/bootstrap.min.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/style.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/jquery.validate.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/validations.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/cookie.js"/>'></script>

    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/moderator.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/header.js"/>'></script>

</head>
<body>

<div class="container">
    <!--navigation-->
    <jsp:include page="header.jsp"/>
    <!--navigation-->

    <!--main-->

    <div class="page_body">
        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a href="#conflict" data-toggle="tab">Conflicts</a></li>
            <li><a href="#profile" data-toggle="tab">Users</a></li>
            <li><a href="#addCategory" data-toggle="tab">Add category </a></li>
        </ul>

        <div class="tab-content">
            <!-- conflict-tab content-->
            <div class="tab-pane active" id="conflict">
                <!-- search-->
                <div class="row">
                    <div class="col-md-12 search_bar">
                        <div class="pull-left"></div>
                        <div class="pull-right">
                            <form id="search_form_for_conflicts" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_conflicts" type="text" class="form-control"
                                           placeholder="Search by tender title...">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- search-->
                <!-- table-->
                <div id="conflict_for_moderator" class="row">
                    <div id="moderator_conflicts_message"></div>
                    <div class="col-md-12" id="moderator_conflicts_items">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th align="center" id="moderator_conflict_tender_title">
                                    <span class="glyphicon sortable"><span>Tender title</span></span>
                                </th>
                                <th align="center" id="moderator_conflict_customer_name">
                                    <span class="glyphicon sortable"><span>Customer name</span></span>
                                </th>
                                <th align="center" id="moderator_conflict_seller_name">
                                    <span class="glyphicon sortable"><span>Seller name</span></span>
                                </th>
                                <th align="center" id="moderator_conflict_status_name">
                                    <span class="glyphicon sortable glyphicon-chevron-down"><span>Status</span></span>
                                </th>
                                <th align="center" id="options_action"><span>Options</span></th>
                            </tr>
                            </thead>
                            <tbody id="conflict_table"></tbody>
                        </table>
                        <!-- table-->
                        <!-- pagination -->
                        <div id="moderator_conflicts_pagination" class="row">
                            <div class="col-md-12">
                                <div class="pull-right">
                                    <ul
                                            class="moderator_conflicts_page_pagination page_pagination pagination pull-right"></ul>
                                </div>
                                <div class="pull-right">
                                    <div class="control-group">
                                        <select id="moderator_conflictsnum"
                                                class="form-control pull-right items_number_dropdown">
                                            <option value="5">5</option>
                                            <option value="10" selected>10</option>
                                            <option value="15">15</option>
                                            <option value="20">20</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="pull-right">
                                    <div class="items_number_dropdown_title">Items on page: &nbsp;</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--action button-->
            <jsp:include page="moderatorAction.jsp"/>
            <!--action button-->

            <!-- conflict-tab content-->

            <!-- new-profile-tab content-->
            <div class="tab-pane" id="profile">
                <!-- search -->
                <div class="row">
                    <div class="col-md-12 search_bar">
                        <div class="pull-left">
                            <button id="add_moderator_btn" data-toggle="modal" data-target="#moderator_profile_add" class="btn btn-primary" type="button">Add moderator</button>
                        </div>
                        <div class="pull-right">
                            <form id="search_form_for_profile" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_profiles" type="text" class="form-control"
                                           placeholder="Search by login...">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- search -->
                <!-- table -->
                <div id="profile_check" class="">
                    <div id="moderator_profile_message"></div>
                    <div id="moderator_profile_items">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th align="center" id="moderator_profile_title">
                                    <span class="glyphicon sortable"><span>Profile</span></span>
                                </th>
                                <th align="center" id="moderator_profile_login">
                                    <span class="glyphicon sortable glyphicon-chevron-down"><span>User login</span></span>
                                </th>
                                <th align="center" id="moderator_profile_telephone">
                                    <span class="glyphicon sortable"><span>Telephone</span></span>
                                </th>
                                <th align="center" id="moderator_profile_roles">
                                    <span class="glyphicon sortable"><span>Roles</span></span>
                                </th>
                                <th align="center" id="moderator_profile_status">
                                    <span class="glyphicon sortable"><span>Status</span></span>
                                </th>
                                <th align="center" id="moderator_profile_action">
                                    <span class="glyphicon sortable"><span>Action</span></span>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="moderator_profiles"></tbody>
                        </table>
                    </div>
                    <!-- table -->
                    <!-- pagination -->
                    <div id="moderator_profile_pagination" class="row">
                        <div class="col-md-12">
                            <div class="pull-right">
                                <ul class="moderator_profile_page_pagination page_pagination pagination pull-right"></ul>
                            </div>
                            <div class="pull-right">
                                <div class="control-group">
                                    <select id="moderator_profilesnum" class="form-control pull-right items_number_dropdown">
                                        <option value="5">5</option>
                                        <option value="10" selected>10</option>
                                        <option value="15">15</option>
                                        <option value="20">20</option>
                                    </select>
                                </div>
                            </div>
                            <div class="pull-right">
                                <div class="items_number_dropdown_title">Items on page: &nbsp;</div>
                            </div>
                        </div>
                    </div>
                    <!-- pagination -->
                </div>
            </div>
            <!-- new-profile-tab content-->

            <!-- new-category-tab content-->
            <div class="tab-pane" id="addCategory">
                <div class="col-md-4">
                    <div>
                        <div class="control-group">
                            <select id="category" class="form-control pull-right items_number_dropdown">
                                <option value="5" selected>Category</option>
                                <option value="10">Another category</option>
                                <option value="15">Another category</option>
                                <option value="20">Another category</option>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div>
                            <div class="control-group">
                                <select id="sub_category" class="form-control pull-right items_number_dropdown">
                                    <option value="5" selected>Sub category</option>
                                    <option value="10">Another category</option>
                                    <option value="15">Another category</option>
                                    <option value="20">Another category</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div>
                            <div class="control-group">
                                <select id="sub_sub_category" class="form-control pull-right items_number_dropdown">
                                    <option value="5" selected>Sub sub category</option>
                                    <option value="10">Another category</option>
                                    <option value="15">Another category</option>
                                    <option value="20">Another category</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8" >
                    <form class="form-horizontal" role="form" id="create_tender_form_validation">
                        <div class="form-group">
                            <label for="create_category" class="col-sm-2 control-label">New category:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="create_category" placeholder="Category name" name="title">
                            </div>
                        </div>
                        <div class="col-sm-4">
                        <button class="btn btn-default" type="button">Save</button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- new-category-tab content-->
        </div>
    </div>
    <!--main-->
</div>

<!--Profile status updating confirm-->
<div class="modal fade" id="moderator_profile_status_confirm" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Confirm</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <h4>Do you want to update profile status?</h4>
                </form>
            </div>
            <div class="modal-footer">
                <button id="confirm_button" class="btn btn-primary" type="button">Yes</button>
                <button class="btn btn-default" type="button" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>
<!--Profile status updating confirm-->

<!--Add moderator modal window-->
<div class="modal fade" id="moderator_profile_add" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add new moderator</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="new_moderator_data_validation">
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <span class="input-group-addon glyphicon glyphicon-user m_glyphicon"></span>
                            <input type="text" name="m_userlogin" id="m_userlogin" class="form-control required-field" placeholder="Email address" required autofocus>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <span class="input-group-addon glyphicon glyphicon-lock m_glyphicon"></span>
                            <input type="password" name="m_password" id="m_password" class="form-control required-field" placeholder="Password" required autofocus>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <span class="input-group-addon glyphicon glyphicon-lock m_glyphicon"></span>
                            <input type="password" name="m_confirm_password" id="m_confirm_password" class="form-control required-field" placeholder="Confirm password" required autofocus>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="add_moderator_cancel_button" class="btn btn-default" type="button" data-dismiss="modal">Cancel</button>
                <button id="add_moderator_button" class="btn btn-primary" type="button" disabled>Confirm</button>
            </div>
        </div>
    </div>
</div>
<!--Add moderator modal window-->

<!--Moderator register success-->
<div class="modal fade" id="moderator_register_success" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Success</h4>
            </div>
            <div class="modal-body">
                <div>You have successfully registered new moderator with login <span id="moderator_register_login"></span>.</div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
<!--Moderator register success-->

</body>
</html>
