$(document).ready(function () {
    $.getJSON(PROPOSALS_URL + NEW_PROPOSAL_URL, function(data){
         var newProposalsNumber = data;
         if(newProposalsNumber > 0) {
            $("#my_tenders_btn").html("My tenders <span class='badge'>" + newProposalsNumber + "</span>");
         }
    });
});