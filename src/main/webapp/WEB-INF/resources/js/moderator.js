$(document).ready(function() {
    showUsersProfiles();
});

function showUsersProfiles() {
    var queryParams = '';

    $.ajax({
        url: MODERATOR_PROFILE_DATA_URL,
        type: "GET",
        data:  queryParams,
        async: false,
        dataType:'json',

        success: function(data) {
            var html = '';
            var dataSize = data.length;

            if(dataSize > 0) {
                for (var i = 0; i < dataSize; i++) {
                    html += '<tr><td align="center">' + data[i].firstName + ' ' + data[i].lastName + '</td>' +
                        '<td align="center"><a href="mailto:' + data[i].userLogin + '">' + data[i].userLogin + '</a></td>' +
                        '<td align="center">' + data[i].telephone + '</td>' +
                        '<td align="center">' +
                        '<div class="btn-group">' +
                        '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Status<span class="caret"></span></button>' +
                        '<ul class="dropdown-menu">' +
                        '<li class="'+((data[i].status=="Unchecked")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'Unchecked\');">Unchecked</a></li>' +
                        '<li class="'+((data[i].status=="Checked")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'Checked\');">Checked</a></li>' +
                        '<li class="'+((data[i].status=="In progress")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'In progress\');">In progress</a></li>' +
                        '<li class="'+((data[i].status=="Denied")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'Denied\');">Denied</a></li>' +
                        '</ul>' +
                        '</div>' +
                        '</td></tr>';
                }

                $('#moderator_profile_message').html('');
                $('#moderator_profile_items').show();
                $('#moderator_profiles').html(html);
                $('#moderator_profile_pagination').show();
            } else {
                $('#moderator_profile_message').html('<h4>No profiles found</h4>').show();
                $('#moderator_profile_items').hide();
                $('#moderator_profile_pagination').hide();
            }
        }
    });
}

function addConfirmListender(buttonId,userId,status) {
    $("#"+buttonId).unbind('click');
    $("#"+buttonId).click(function(){
        setProfileStatus(userId, status);
        $("#moderator_profile_status").modal('hide');
    });
}

function setProfileStatus(userId, statusName) {
    var queryParams = 'statusName='+statusName;

    $.ajax({
        url: MODERATOR_PROFILE_STATUS_URL + '/' + userId,
        type: "POST",
        data:  queryParams,
        dataType:'json',

        success: function(data) {
            showUsersProfiles();
        }
    });
}
