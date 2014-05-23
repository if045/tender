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
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/datepicker.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/select2.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/style.css"/>'/>

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/bootstrap-datepicker.js"/>'></script>
    <script type='text/javascript' src='<c:url value="../resources/js/select2.min.js"/>'></script>

    <script>
        $(document).ready(function() {
            $('#startDate, #endDate').datepicker({
                format: 'mm-dd-yyyy',
                startDate: '-3d'
            });

            $("#category_filter").select2({
                placeholder: "Select a category"
            });

            $("#location_filter").select2({
                placeholder: "Select a location"
            });
        });
    </script>
</head>
<body>

<div class="container">
<div class="row">
<!--navigation-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">UATender</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login">Log in</a></li>
                <li><a href="/signup">Sign up</a></li>
            </ul>
        </div>
    </div>
</nav>
<!--navigation-->

<!--main-->
<div class="page_body">
<!-- sidebar -->
<div class="col-md-3">

    <div class="panel panel-default sidebar">
        <div class="panel-heading">
            <h3 class="panel-title">Filter</h3>
        </div>
        <div class="panel-body">
            <div>
                <div>Category</div>
                <div>
                    <select id="category_filter" multiple="multiple" class="populate placeholder select2-offscreen category_selector" tabindex="-1">
                        <option></option>
                        <optgroup label="Alaskan/Hawaiian Time Zone">
                            <option value="AK">Alaska</option>
                            <option value="HI">Hawaii</option>
                        </optgroup>
                        <optgroup label="Pacific Time Zone">
                            <option value="CA">California</option>
                            <option value="NV">Nevada</option>
                            <option value="OR">Oregon</option>
                            <option value="WA">Washington</option>
                        </optgroup>
                        <optgroup label="Mountain Time Zone">
                            <option value="AZ">Arizona</option>
                            <option value="CO">Colorado</option>
                            <option value="ID">Idaho</option>
                            <option value="MT">Montana</option>
                            <option value="NE">Nebraska</option>
                            <option value="NM">New Mexico</option>
                            <option value="ND">North Dakota</option>
                            <option value="UT">Utah</option>
                            <option value="WY">Wyoming</option>
                        </optgroup>
                    </select>
                </div>
            </div>
            <div>
                <div>Item</div>
                <select class="form-control selectpicker">
                    <c:forEach var="item" items="${items}">
                        <option><c:out value="${item.name}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <div>Location</div>
                <div>
                    <select id="location_filter" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1">
                        <c:forEach var="location" items="${locations}">
                            <option value="${location.id}"><c:out value="${location.name}" /></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div>
                <div>Status</div>
                <select class="form-control selectpicker">
                    <option>Active status</option>
                    <option>Ketchup</option>
                    <option>Relish</option>
                </select>
            </div>
            <div>
                <div>Suitable price</div>
                <div>
                    <input type="price_from" class="pull-left form-control suitable_price" id="price_from" placeholder="From">
                    <input type="price_to" class="pull-right form-control suitable_price" id="price_to" placeholder="To">
                </div>
            </div>
            <div>
                <div>Active</div>
                <div class="date_filter">
                    <div class="input-group date pull-left" id="startDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                        <input class="form-control" size="10" type="text" value="29-03-2013">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                    </div>
                    <div class="input-group date pull-right" id="endDate" data-date="29-03-2013" data-date-format="dd-mm-yyyy">
                        <input class="form-control" size="10" type="text" value="29-03-2013">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                    </div>


                </div>
            </div>
            <div><br/></div>
            <div>
                <div class="pull-left"><button type="button" class="btn btn-default" disabled>Filter</button></div>
                <div class="pull-right"><button type="button" class="btn btn-default" disabled>Clear filters</button></div>
            </div>
        </div>
    </div>

</div>
<!-- sidebar -->

<!-- content -->
<div class="col-md-9">
    <div class="row">
        <div class="pull-left">
            <h3>Tenders</h3>
        </div>
        <div class="pull-right">
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search...">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
        </div>
    </div>

    <!-- items -->
    <div class="row">
        <table class="table table-bordered table-striped">
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Category</th>
                <th>Location</th>
                <th>Suitable price</th>
                <th>Status</th>
                <th>Proposals</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tender" items="${tenders}">
                <tr>
                    <td align="center">
                        <a href="<spring:url value="/tenders/${tender.id}.html" />">
                                ${tender.title}
                        </a>
                    </td>
                    <td align="center"><c:out value="${tender.author.firstName}"></c:out></td>
                    <td align="center"><c:out value="Build"></c:out></td>
                    <td align="center"><c:out value="Lviv"></c:out></td>
                    <td align="center"><c:out value="$${tender.suitablePrice}"></c:out></td>
                    <td align="center"><c:out value="${tender.status.name}"></c:out></td>
                    <td align="center"><c:out value="6"></c:out></td>
                    <td align="center">
                        <div class="control-group">
                            <select class="form-control items_number_dropdown">
                                <option>View</option>
                                <option>Delete</option>
                                <option>Close</option>
                            </select>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- items -->

    <!-- pagination -->
    <div class="row">
        <div class="col-md-12">
            <div class="pull-right">
                <ul class="pagination page_pagination pull-right">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </div>
            <div class="pull-right">
                <div class="control-group">
                    <select class="form-control pull-right items_number_dropdown">
                        <option>10</option>
                        <option>15</option>
                        <option>20</option>
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
<!-- content -->
</div>
<!--main-->

<!--footer -->
<div class="footer">
    <div class="pull-left">
        <p>Copyright © UATender 2014
    </div>
</div>
<!-- footer -->

</div>
</div>




</body>


</html>

