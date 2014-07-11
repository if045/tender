$(document).ready(function () {
    $.getJSON(USER_URL + USER_INFO_URL, function(data) {
        var roleSwitcherButton = '';
        var createTenderButton = '';
        var loggedUserName = '';
        var options = {};
        options.path = "/";
        setCookie("roleNumber", data.roles.length, options);
        var role = data.roles[0].toString();
        if (getCookie("userRole") == "undefined" || getCookie("userRole") == undefined) {
            setCookie("userRole", role, options)
        }
        setCookie("login", data.login, options);

        if (getCookie("userRole") != "undefined" || getCookie("userRole") != undefined){
            CURRENT_ROLE = getCookie("userRole");
        } else {
            deleteCookie("userRole");
        }
        if (getCookie("roleNumber") != 0) {
            if (CURRENT_ROLE.search(CUSTOMER) != -1 & (getCookie("roleNumber") > 1)) {
                roleSwitcherButton = '<button type="button" class="btn btn-default nav_button" onclick="roleSwitcher()">Switch to Seller View</button>';
            } else if (CURRENT_ROLE.search(SELLER) != -1 & (getCookie("roleNumber") > 1)) {
                roleSwitcherButton = '<button type="button" class="btn btn-default nav_button" onclick="roleSwitcher()">Switch to Customer View</button>';
            }
            if (CURRENT_ROLE.search(CUSTOMER) != -1 & (getCookie("roleNumber") >= 1)) {
                createTenderButton = '<button type="button" class="btn btn-default nav_button" data-toggle="modal" data-target="#createTenderWindow">Create tender</button>'
            } else if (CURRENT_ROLE.search(SELLER) != -1 & (getCookie("roleNumber") >= 1)) {
                createTenderButton = '';
            }
        }
        if (getCookie("login") != null){
            loggedUserName = '<h6>' + getCookie("login") + '</h6>'
        }
        $('#logged_user_name').html(loggedUserName);
        $('#role_switcher_button').html(roleSwitcherButton);
        $('#create_tender_button_onHeader').html(createTenderButton);

        $.getJSON(PROPOSALS_URL + "?isNew=true&userRole=" + getCookie("userRole"), function(data){
            var newProposalsNumber = data;
            if(newProposalsNumber > 0) {
                $("#my_tenders_btn").html("My tenders <span class='badge'>" + newProposalsNumber + "</span>");
            }
        });
    });

    if (getCookie("userRole") == SELLER) {
        $.getJSON(NEW_DEALS_URL+"?userRole=" + getCookie("userRole"), function (data) {
            var newDealsNumber = data;
            if (newDealsNumber > 0) {
                $('#my_deals_btn').html("My deals <span class='badge'>" + newDealsNumber + "</span>");
            } else {
                $('#my_deals_btn').html("My deals");
            }
        });
    }
});

function goToMyTenders() {
    var options = {};
    options.path = "/";
    setCookie("roleFlag", "true", options);
    window.location.href = HOME_PAGE_URL;
}

function goToHomePage() {
    deleteCookie("roleFlag");
    var options = {};
    options.path = "/";
    setCookie("roleFlag", "false", options);
    window.location.href = HOME_PAGE_URL;
}

function goLogOut() {
    var options = {};
    options.path = "/";
    setCookie("roleFlag", "false", options);
    setCookie("userRole", undefined, options);
    window.location.href = LOG_OUT;
}

function goToUserProfilePage() {
    window.location.href = USER_PROFILE_PAGE_URL;
}

function goToRegistrationPage() {
    window.location.href = REGISTRATION_PAGE_URL;
}

function goToMyDealsPage() {
    window.location.href = MYDEALS_PAGE_URL;
}

function roleSwitcher(){
    var options = {};
    options.path = "/";
    if (CURRENT_ROLE.search(CUSTOMER) != -1){
        setCookie("userRole", SELLER, options);
    } else if (CURRENT_ROLE.search(SELLER)!= -1){
        setCookie("userRole", CUSTOMER, options);
    }
    window.location.href = HOME_PAGE_URL;
}
