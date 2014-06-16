$(document).ready(function() {

});
function createConflict() {
    var str = '';
    str += '{"description": ' + '\"' + $('#conflict_comment').val() +'\"'+
        ', "bidId": ' + '\"' + $('#conflict_id').val() +'\"}';
    var newJson = $.parseJSON(str);
    $.ajax({
        url: "/deals/" + $('#conflict_id').val() + "/conflicts",
        type: "POST",
        data: JSON.stringify(newJson),
        dataType: 'json',
        contentType: 'application/json',

        success: function (data) {
            closeConflictModalWindow('conflict_mod_wind');
            cleanConflict();
        },
        error: function () {
            alert("Something wrong")
        }
    })
}
function closeConflictModalWindow(id) {
$('#' + id).modal('hide');
}

function cleanConflict() {
$("#conflict_comment").val("");
}

function writeConflictId(id) {
document.getElementById('conflict_id').value = id;
}