$(document).ready(function () {
    $.getJSON(PROPOSALS_URL + "?isNew=true", function(data){
         var newProposalsNumber = data;
         if(newProposalsNumber > 0) {
            $("#my_tenders_btn").html("My tenders <span class='badge'>" + newProposalsNumber + "</span>");
         }
    });
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
    deleteCookie("roleFlag");
    var options = {};
    options.path = "/";
    setCookie("roleFlag", "false", options);
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