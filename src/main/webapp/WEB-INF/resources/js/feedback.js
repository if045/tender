$(document).ready(function() {

});

function createFeedback() {
    var str = '';
    //str += "statusName=" + CLOSE_STATUS_NAME;
    $.ajax({
        url: TENDERS_URL + $('#feedback_id').val() + "?"  + str,
        type: "POST",

        success: function(data){
            closeFeedbackModalWindow('feedback_mod_wind');
            },
        error: function(){
            alert("Something wrong")
        }
    })
}
function closeFeedbackModalWindow(id) {
    $('#' + id).modal('hide');
}

function writeFeedbackId(id) {
    document.getElementById('feedback_id').value = id;
}
function cleanFeedback() {
    $("#create_communication_rating").rating('update', 0);
    $("#create_speed_rating").rating('update', 0);
    $("#create_logistic_rating").rating('update', 0);
    $("#feedback_comment").val("");
}