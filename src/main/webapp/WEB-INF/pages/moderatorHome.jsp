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
    <script type='text/javascript' src='<c:url value="../resources/js/constants.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/moderator.js"/>'></script>

</head>
<body>

<div class="container">
    <div class="row-header">
        <nav class="navbar navbar-default" role="navigation">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="">Switch to admin</a></li>
                    <li><a class="glyphicon glyphicon-user" onclick=""></a></li>
                    <li><button type="button" class="btn btn-default nav_button" onclick="">Sign up</button></li>
                </ul>
            </div>
        </nav>
    </div>

    <!--main-->

    <div class="page_body">
        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a href="#conflict" data-toggle="tab">Conflicts</a></li>
            <li><a href="#profile" data-toggle="tab">New Profiles</a></li>
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
                            <form id="search_form" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_tenders_for_moderator" type="text" class="form-control"
                                           placeholder="Search by tender title...">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- search-->
                <!-- table-->
                <div id="conflict_for_moderator" class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th align="center" id="tender_title">
                                    <span>Title</span>
                                </th>
                                <th align="center" id="customer_name">
                                    <span>Customer name</span>
                                </th>
                                <th align="center" id="seller_name">
                                    <span>Seller name</span>
                                </th>
                                <th align="center" id="options_action"><span>Options</span></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Title1</td><td>Name1</td><td>Name1</td><td><button class="btn btn-default" type="button">Action</button></td>
                            </tr>
                            <tr>
                                <td>Title2</td><td>Name2</td><td>Name2</td><td><button class="btn btn-default" type="button">Action</button></td>
                            </tr>
                            <tr>
                                <td>Title3</td><td>Name3</td><td>Name3</td><td><button class="btn btn-default" type="button">Action</button></td>
                            </tr>
                            <tr>
                                <td>Title4</td><td>Name4</td><td>Name4</td><td><button class="btn btn-default" type="button">Action</button></td>
                            </tr>
                            <tr>
                                <td>Title5</td><td>Name5</td><td>Name5</td><td><button class="btn btn-default" type="button">Action</button></td>
                            </tr>
                            <tr>
                                <td>Title6</td><td>Name6</td><td>Name6</td><td><button class="btn btn-default" type="button">Action</button></td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- table-->
                        <!-- pagination -->
                        <div id="pagination_for_conflict" class="row">
                            <div class="col-md-12">
                                <div class="pull-right">
                                    <ul class="pagination page_pagination pull-right"></ul>
                                </div>
                                <div class="pull-right">
                                    <div class="control-group">
                                        <select id="pagination_items_num_conflict" class="form-control pull-right items_number_dropdown">
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
            <!-- conflict-tab content-->

            <!-- new-profile-tab content-->
            <div class="tab-pane" id="profile">
                <!-- search -->
                <div class="row">
                    <div class="col-md-12 search_bar">
                        <div class="pull-left"></div>
                        <div class="pull-right">
                            <form id="search_form_for_profile" class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input id="search_tenders_by_login" type="text" class="form-control"
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
                                    <span>Profile</span>
                                </th>
                                <th align="center" id="moderator_profile_login">
                                    <span>User login</span>
                                </th>
                                <th align="center" id="moderator_profile_telephone">
                                    <span>Telephone</span>
                                </th>
                                <th align="center" id="moderator_profile_action"><span>Action</span></th>
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
                                <ul class="pagination page_pagination pull-right"></ul>
                            </div>
                            <div class="pull-right">
                                <div class="control-group">
                                    <select id="moderator_profile_pagination_itemsnum" class="form-control pull-right items_number_dropdown">
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
<div class="modal fade" id="moderator_profile_status" tabindex="-1" role="dialog" hidden="">
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
                <button id="confirm_button" class="btn btn-primary" type="button" onclick="setProfileStatus(userId, status);">Yes</button>
                <button class="btn btn-default" type="button" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>
<!--Profile status updating confirm-->

</body>
</html>
