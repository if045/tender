<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <ul class="nav navbar-nav navbar-left nav_buttons">
                <li><a class="navbar-brand" href="/">UATender</a></li>
                <li><button type="button" class="btn btn-default nav_button" disabled>My tenders</button></li>
                <li><button type="button" class="btn btn-default nav_button" onclick="window.location.href='/deals';">My deals</button></li>
                <li><button type="button" class="btn btn-default nav_button" data-toggle="modal" data-target="#createTenderWindow">Create tender</button></li>
            </ul>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login">Log in</a></li>
                <li><a href="/signup">Sign up</a></li>
            </ul>
        </div>
    </div>
</nav>
