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

    <script type="text/javascript">
        $(document).ready(function() {
            $('#endDate').datepicker({
                format: 'mm-dd-yyyy',
                startDate: '-3d'
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="row">
            <!--navigation-->
            <jsp:include page="header.jsp"/>
            <!--navigation-->

            <!--main-->
            <div class="page_body">
                <!-- content -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="pull-left">
                            <h3>Deals</h3>
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
                </div>

                <!-- information about deals -->
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped" id="units">
                            <tr>
                                <th>Tender title</th>
                                <th>Date</th>
                                <th>Business Partner</th>
                                <th>Status</th>
                                <th>Sum</th>
                                <th class="deal_action_field">Action</th>
                            </tr>
                            <tr>
                                <td align="center">Cegla</td>
                                <td align="center">03/08/2014</td>
                                <td align="center">name</td>
                                <td align="center">Open</td>
                                <td align="center">40000</td>
                                <td align="center">
                                    <select class="form-control items_number_dropdown action_button">
                                        <option value="">Action</option>
                                        <option value="done' + data[i].id + '">Done</option>
                                        <option value="conflict' + data[i].id + '">Conflict</option>
                                        <option value="feedback' + data[i].id + '">Feedback</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">Cegla</td>
                                <td align="center">03/08/2014</td>
                                <td align="center">name</td>
                                <td align="center">Open</td>
                                <td align="center">40000</td>
                                <td align="center">
                                    <select class="form-control items_number_dropdown action_button">
                                        <option value="">Action</option>
                                        <option value="done' + data[i].id + '">Done</option>
                                        <option value="conflict' + data[i].id + '">Conflict</option>
                                        <option value="feedback' + data[i].id + '">Feedback</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">Cegla</td>
                                <td align="center">03/08/2014</td>
                                <td align="center">name</td>
                                <td align="center">Open</td>
                                <td align="center">40000</td>
                                <td align="center">
                                    <select class="form-control items_number_dropdown action_button">
                                        <option value="">Action</option>
                                        <option value="done' + data[i].id + '">Done</option>
                                        <option value="conflict' + data[i].id + '">Conflict</option>
                                        <option value="feedback' + data[i].id + '">Feedback</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <!-- pagination -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <ul class="pagination page_pagination pull-right">
                                <li><a href="#">&laquo;</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">&laquo;</a></li>
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
            <!--footer -->
            <jsp:include page="footer.jsp"/>
            <!-- footer -->
        </div>
    </div>
</body>
</html>
