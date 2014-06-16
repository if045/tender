$(document).ready(function() {

});

function closeConflictModalWindow(id) {
$('#' + id).modal('hide');
}

function cleanConflict() {
$("#conflict_comment").val("");
}

function writeConflictId(id) {
document.getElementById('conflict_id').value = id;
}