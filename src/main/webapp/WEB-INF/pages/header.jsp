<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <ul class="nav navbar-nav navbar-left nav_buttons">
                <li><a class="navbar-brand" href="/tendersHome">UATender</a></li>
                <security:authorize access="hasAnyRole('CUSTOMER','SELLER')">
                    <li>
                        <button id="my_tenders_btn" type="button" class="btn btn-default nav_button"
                               onclick="goToMyTenders()" disabled>My tenders</button>
                    </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('CUSTOMER','SELLER')">
                    <li><button type="button" class="btn btn-default nav_button" onclick="goToMyDealsPage();">My
                                    deals</button></li>
                </security:authorize>
                <security:authorize access="hasRole('CUSTOMER')">
                    <li><button type="button" class="btn btn-default nav_button" data-toggle="modal"
                                 data-target="#createTenderWindow">Create tender</button></li>
                    <input id="CURRENT_USER_ROLE" value="CUSTOMER" hidden=""/>
                </security:authorize>
                <security:authorize access="hasRole('SELLER')">
                    <input id="CURRENT_USER_ROLE" value="SELLER" hidden=""/>
                </security:authorize>
                <security:authorize access="! isAuthenticated()">
                    <input id="CURRENT_USER_ROLE" value="" hidden=""/>
                </security:authorize>

            </ul>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="! isAuthenticated()">
                    <li><a href="/login">Log in</a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li><a href="/logout">Log out</a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li><li><a class="glyphicon glyphicon-user" onclick="goToUserProfilePage()"></a></li></li>
                </security:authorize>
                <security:authorize access="! isAuthenticated()">
                    <li><button type="button" class="btn btn-default nav_button" onclick="goToRegistrationPage()">Sign up</button></li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>
