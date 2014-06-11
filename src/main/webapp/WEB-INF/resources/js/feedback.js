$(document).ready(function() {

});

function createFeedback() {
    var str = '';
    str += '{"communication": ' + '\"' + $('#create_communication_rating').val() + '\"' +
        ', "speed": ' + '\"' + $('#create_speed_rating').val()+ '\"' +
        ', "logistic": ' + '\"' + $('#create_logistic_rating').val()+ '\"' +
        ', "comment": ' + '\"' + $('#feedback_comment').val()+ '\"}';
    var newJson = $.parseJSON(str);
    $.ajax({
        url: "/deals/" + 1 + "/feedback",
        type: "POST",
        data: JSON.stringify(newJson),
        dataType: 'json',
        contentType: 'application/json',

        success: function(data){
            closeFeedbackModalWindow('feedback_mod_wind');
            cleanFeedback();
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