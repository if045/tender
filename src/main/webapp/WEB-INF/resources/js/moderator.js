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
                        '<td align="center">' + data[i].userId + '</td>' +
                        '<td align="center">' + data[i].telephone + '</td>' +
                        '<td align="center">' +
                        '<div class="btn-group">' +
                        '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                        '<ul class="dropdown-menu">' +
                        '<li><a href="#" data-toggle="modal" data-target="#feedback_mod_wind" onclick="writeFeedbackId(' + data[i].userId + ')">Feedback</a></li>' +
                        '<li><a href="#" data-toggle="modal" data-target="#conflict_mod_wind" onclick="writeConflictId(' + data[i].userId + ')">Conflict</a></li>' +
                        '<li><a href="#" data-toggle="modal" data-target="#close_deal_mod_wind" onclick="writeCloseDealId(' + data[i].userId + ')">Close</a></li>' +
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
