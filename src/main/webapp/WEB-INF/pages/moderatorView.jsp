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
    <title>Moderator view</title>

    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/bootstrap.min.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/style.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>

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
                <div id="profile_check" class="row">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th align="center" id="profile_title">
                                <span>Profile</span>
                            </th>
                            <th align="center" id="user_login_for_moder">
                                <span>User login</span>
                            </th>
                            <th align="center" id="telephone">
                                <span>Telephone</span>
                            </th>
                            <th align="center" id="options_action_for"><span>Options</span></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Profile</td><td>Email</td><td>Telephone</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            Status <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Unchecked</a></li>
                                            <li><a href="#">Checked</a></li>
                                            <li><a href="#">In progres</a></li>
                                            <li><a href="#">Denied</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Profile2</td><td>Email2</td><td>Telephone2</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            Status <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Unchecked</a></li>
                                            <li><a href="#">Checked</a></li>
                                            <li><a href="#">In progres</a></li>
                                            <li><a href="#">Denied</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Profile3</td><td>Email3</td><td>Telephone3</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            Status <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Unchecked</a></li>
                                            <li><a href="#">Checked</a></li>
                                            <li><a href="#">In progres</a></li>
                                            <li><a href="#">Denied</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Profile4</td><td>Email4</td><td>Telephone4</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            Status <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Unchecked</a></li>
                                            <li><a href="#">Checked</a></li>
                                            <li><a href="#">In progres</a></li>
                                            <li><a href="#">Denied</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- table -->
                    <!-- pagination -->
                    <div id="pagination" class="row">
                        <div class="col-md-12">
                            <div class="pull-right">
                                <ul class="pagination page_pagination pull-right"></ul>
                            </div>
                            <div class="pull-right">
                                <div class="control-group">
                                    <select id="pagination_items_num" class="form-control pull-right items_number_dropdown">
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
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">Category</button>
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Another category</a></li>
                                <li><a href="#">Another category</a></li>
                                <li><a href="#">Some else category</a></li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">Sub category</button>
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Another category</a></li>
                                <li><a href="#">Another category</a></li>
                                <li><a href="#">Some else category</a></li>
                            </ul>
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
</body>
</html>
