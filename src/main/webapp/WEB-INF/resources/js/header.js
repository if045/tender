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