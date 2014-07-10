var moderatorProfilePageSize = DEFAULT_PAGE_SIZE;
var moderatorProfileCurrPageNumber = 0;

var moderatorProfileSortDirection = false;
var moderatorProfileOrderBy = MODERATOR_PROFILE_DEFAULT_SORT_FIELD;
var ENTER_BUTTON_CODE = 13;

var moderatorConflictsPageSize = DEFAULT_PAGE_SIZE;
var moderatorConflictsCurrPageNumber = 0;
var moderatorConflictsOrderBy = MODERATOR_CONFLICTS_DEFAULT_SORT_FIELD;
var moderatorConflictsSortDirection = false;

$(document).ready(function () {
    showUsersProfiles();
    showConflicts();

    $('#moderator_profilesnum').on('change', function () {
        moderatorProfilePageSize = $('#moderator_profilesnum').val();
        moderatorProfileCurrPageNumber = 0;
        showProfilesPage(moderatorProfileCurrPageNumber);
    });

    $("#moderator_profile_title").click(function () {
        sortUsersProfiles("firstName", "moderator_profile_title");
    });

    $("#moderator_profile_login").click(function () {
        sortUsersProfiles("user.login", "moderator_profile_login");
    });

    $("#moderator_profile_status").click(function(){
        sortUsersProfiles("checkedProfile.status.name","moderator_profile_status");
    });

    $("#moderator_profile_telephone").click(function(){
        sortUsersProfiles("telephone","moderator_profile_telephone");
    });

    $('#search_profiles').keypress(function (e) {
        if (e.keyCode == ENTER_BUTTON_CODE) {
            moderatorProfilePageSize = $('#moderator_profilesnum').val();
            moderatorProfileCurrPageNumber = 0;
            showProfilesPage(moderatorProfileCurrPageNumber);
            return false;
        }
    });

    $('#moderator_conflictsnum').on('change', function () {
        moderatorConflictsPageSize = $('#moderator_conflictsnum').val();
        moderatorConflictsCurrPageNumber = 0;
        showConflictsPage(moderatorConflictsCurrPageNumber);
    });

    $("#moderator_conflict_tender_title").click(function () {
        sortConflicts("bid.proposal.tender.title", "moderator_conflict_tender_title");
    });

    $("#moderator_conflict_customer_name").click(function () {
        sortConflicts("bid.proposal.tender.author.firstName", "moderator_conflict_customer_name");
    });

    $("#moderator_conflict_seller_name").click(function () {
        sortConflicts("bid.proposal.seller.profile.firstName", "moderator_conflict_seller_name");
    });

    $("#moderator_conflict_status_name").click(function () {
        sortConflicts("status.name", "moderator_conflict_status_name");
    });

    $('#search_conflicts').keypress(function (e) {
        if (e.keyCode == ENTER_BUTTON_CODE) {
            moderatorConflictsPageSize = $('#moderator_conflictsnum').val();
            moderatorConflictsCurrPageNumber = 0;
            showConflictsPage(moderatorConflictsCurrPageNumber);
            return false;
        }
    });

    $("#add_moderator_button").click(function () {
        var userLogin = $('#m_userlogin').val();
        var userPassword = $('#m_password').val();

        registerNewModerator(userLogin, userPassword);
    });
});

function showUsersProfiles() {
    var queryParams = '';

    if ($('#search_profiles').val() != "") {
        queryParams += (queryParams.length == 0) ? "searchParam=" + $('#search_profiles').val() : "&searchParam=" + $('#search_profiles').val();
    }

    showProfilesPagination(queryParams);

    queryParams += (queryParams.length == 0) ? "pageSize=" + moderatorProfilePageSize : "&pageSize=" + moderatorProfilePageSize;
    queryParams += "&pageNumber=" + moderatorProfileCurrPageNumber + "&sortDirection=" +
        ((moderatorProfileSortDirection) ? "desc" : "asc") + "&orderBy=" + moderatorProfileOrderBy;

    $.ajax({
        url: MODERATOR_PROFILE_DATA_URL,
        type: "GET",
        data: queryParams,
        async: false,
        dataType: 'json',

        success: function (data) {
            var html = '';
            var dataSize = data.length;

            if (dataSize > 0) {
                for (var i = 0; i < dataSize; i++) {
                    var userRoles = '';
                    var userRolesNumber = data[i].roles.length;
                    for(var z=0;z<userRolesNumber;z++) {
                        userRoles += data[i].roles[z].name;
                        if(z+1<userRolesNumber) userRoles += ', ';
                    }

                    html += '<tr><td align="center"><a href="/user/profile?userLogin='+data[i].userLogin+'" target="_blank">' + data[i].firstName + ' ' + data[i].lastName + '</a></td>' +
                        '<td align="center"><a href="mailto:' + data[i].userLogin + '">' + data[i].userLogin + '</a></td>' +
                        '<td align="center">' + data[i].telephone + '</td>' +
                        '<td align="center">' + userRoles + '</td>' +
                        '<td align="center">' + data[i].status + '</td>' +
                        '<td align="center">' +
                        '<div class="btn-group">' +
                        '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Status<span class="caret"></span></button>' +
                        '<ul class="dropdown-menu">' +
                        '<li class="'+((data[i].status=="Unchecked")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status_confirm" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'Unchecked\');">Unchecked</a></li>' +
                        '<li class="'+((data[i].status=="Checked")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status_confirm" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'Checked\');">Checked</a></li>' +
                        '<li class="'+((data[i].status=="In progress")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status_confirm" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'In progress\');">In progress</a></li>' +
                        '<li class="'+((data[i].status=="Denied")?"moderator_profile_status":"")+'"><a href="#" data-toggle="modal" data-target="#moderator_profile_status_confirm" onclick="addConfirmListender(\'confirm_button\',' + data[i].userId + ',\'Denied\');">Denied</a></li>' +
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

function addConfirmListender(buttonId, userId, status) {
    $("#" + buttonId).unbind('click');
    $("#" + buttonId).click(function () {
        setProfileStatus(userId, status);
        $("#moderator_profile_status_confirm").modal('hide');
    });
}

function setProfileStatus(userId, statusName) {
    var queryParams = 'statusName=' + statusName;

    $.ajax({
        url: MODERATOR_PROFILE_STATUS_URL + '/' + userId,
        type: "POST",
        data: queryParams,
        dataType: 'json',

        success: function (data) {
            showUsersProfiles();
        }
    });
}

function showConflicts() {
    var queryParams = '';

    if ($('#search_conflicts').val() != "") {
        queryParams += (queryParams.length == 0) ? "searchParam=" + $('#search_conflicts').val() : "&searchParam=" + $('#search_conflicts').val();
    }

    showConflictsPagination(queryParams);

    queryParams += (queryParams.length == 0) ? "pageSize=" + moderatorConflictsPageSize : "&pageSize=" + moderatorConflictsPageSize;
    queryParams += "&pageNumber=" + moderatorConflictsCurrPageNumber + "&sortDirection=" +
        ((moderatorConflictsSortDirection) ? "desc" : "asc") + "&orderBy=" + moderatorConflictsOrderBy;

    $.ajax({
        url: MODERATOR_URL + CONFLICTS,
        type: "GET",
        data: queryParams,
        async: false,
        dataType: 'json',

        success: function (data) {
            var html = '';
            var dataSize = data.length;

            if (dataSize > 0) {
                for (var i = 0; i < dataSize; i++) {
                    html += '<tr><td align="center">' + data[i].title + '</td>' +
                        '<td align="center">' + data[i].customerName + '</td>' +
                        '<td align="center">' + data[i].sellerName + '</td>' +
                        '<td align="center">' + data[i].status + '</td>' +
                        '<td align="center"><a href="#" data-toggle="modal" data-target="#moderator_conflict_mod_wind" button class="btn btn-default" type="button" data-dismiss="modal" onclick="writeModeratorConflictId();">Action</a></td></tr>';
                }

                $('#moderator_conflicts_message').html('');
                $('#moderator_conflicts_items').show();
                $('#conflict_table').html(html);
                $('#moderator_conflicts_pagination').show();
            } else {
                $('#moderator_conflicts_message').html('<h4>No profiles found</h4>').show();
                $('#moderator_conflicts_items').hide();
                $('#moderator_conflicts_pagination').hide();
            }
        }
    });
}

function showProfilesPagination(queryParams) {
    $.ajax({
        url: MODERATOR_PROFILES_NUMBER_URL,
        async: false,
        type: "GET",
        data: queryParams,
        dataType: 'json',

        success: function (data) {
            var dataSize = data;
            var pageNumber = Math.ceil(dataSize / moderatorProfilePageSize);

            if (dataSize > 0 && moderatorProfilePageSize < dataSize) {
                var html = '';
                html += '<li class="' + ((moderatorProfileCurrPageNumber == 0) ? "disabled" : "") + '"><a id="profiles_prev_page" href="#">&laquo;</a></li>';
                for (var z = 1; z <= pageNumber; z++) {
                    html += '<li class="' + ((moderatorProfileCurrPageNumber == z - 1) ? "active" : "") + '"><a href="#" onclick="showProfilesPage(' + (z - 1) + ');">' + z + '</a></li>';
                }
                html += '<li class="' + ((moderatorProfileCurrPageNumber == pageNumber - 1) ? "disabled" : "") + '"><a id="profiles_next_page" href="#">&raquo;</a></li>';

                $('.moderator_profile_page_pagination').html(html).show();
                $('#moderator_profile_pagination').show();

                if (moderatorProfileCurrPageNumber != 0) {
                    document.getElementById('profiles_prev_page').setAttribute("onclick", "showProfilesPage(" + (moderatorProfileCurrPageNumber - 1) + ");");
                }

                if (moderatorProfileCurrPageNumber != pageNumber - 1) {
                    document.getElementById('profiles_next_page').setAttribute("onclick", "showProfilesPage(" + (moderatorProfileCurrPageNumber + 1) + ");");
                }
            } else {
                $('.moderator_profile_page_pagination').html('').hide();
                $('#moderator_profile_pagination').hide();
            }
        },
        error: function () {
            $('#moderator_profile_pagination').hide();
        }
    });
}

function showConflictsPagination(queryParams) {
    $.ajax({
        url: MODERATOR_CONFLICTS_NUMBER_URL,
        async: false,
        type: "GET",
        data: queryParams,
        dataType: 'json',

        success: function (data) {
            var dataSize = data;
            var pageNumber = Math.ceil(dataSize / moderatorConflictsPageSize);

            if (dataSize > 0 && moderatorConflictsPageSize < dataSize) {
                var html = '';
                html += '<li class="' + ((moderatorConflictsCurrPageNumber == 0) ? "disabled" : "") + '"><a id="conflicts_prev_page" href="#">&laquo;</a></li>';
                for (var z = 1; z <= pageNumber; z++) {
                    html += '<li class="' + ((moderatorConflictsCurrPageNumber == z - 1) ? "active" : "") + '"><a href="#" onclick="showConflictsPage(' + (z - 1) + ');">' + z + '</a></li>';
                }
                html += '<li class="' + ((moderatorConflictsCurrPageNumber == pageNumber - 1) ? "disabled" : "") + '"><a id="conflicts_next_page" href="#">&raquo;</a></li>';

                $('.moderator_conflicts_page_pagination').html(html).show();
                $('#moderator_conflicts_pagination').show();

                if (moderatorConflictsCurrPageNumber != 0) {
                    document.getElementById('conflicts_prev_page').setAttribute("onclick", "showConflictsPage(" + (moderatorConflictsCurrPageNumber - 1) + ");");
                }

                if (moderatorConflictsCurrPageNumber != pageNumber - 1) {
                    document.getElementById('conflicts_next_page').setAttribute("onclick", "showConflictsPage(" + (moderatorConflictsCurrPageNumber + 1) + ");");
                }
            } else {
                $('.moderator_conflicts_page_pagination').html('').hide();
                $('#moderator_conflicts_pagination').hide();
            }
        },
        error: function () {
            $('#moderator_conflicts_pagination').hide();
        }
    });
}

function showProfilesPage(pageNumber) {
    moderatorProfileCurrPageNumber = pageNumber;
    showUsersProfiles();
}

function showConflictsPage(pageNumber) {
    moderatorConflictsCurrPageNumber = pageNumber;
    showConflicts();
}

function sortUsersProfiles(orderByField, elementId) {
    moderatorProfileSortDirection = (moderatorProfileOrderBy == orderByField) ? !moderatorProfileSortDirection : false;
    moderatorProfileOrderBy = orderByField;

    $('#moderator_profile_items .sortable').removeClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
    if (moderatorProfileSortDirection == false) {
        $('#' + elementId + ' .sortable').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
    } else {
        $('#' + elementId + ' .sortable').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
    }

    showUsersProfiles();
}

function sortConflicts(orderByField, elementId) {
    moderatorConflictsSortDirection = (moderatorConflictsOrderBy == orderByField) ? !moderatorConflictsSortDirection : false;
    moderatorConflictsOrderBy = orderByField;

    $('#moderator_conflicts_items .sortable').removeClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
    if (moderatorConflictsSortDirection == false) {
        $('#' + elementId + ' .sortable').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
    } else {
        $('#' + elementId + ' .sortable').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
    }

    showConflicts();
}

function registerNewModerator(userLogin, userPassword) {
    var userDto = '{"roles":[' + MODERATOR_ROLE_ID + '],' +
                   '"login":"' + userLogin + '",' +
                   '"password":"' + userPassword + '"}';

    $.ajax({
        url: USER_REGISTRATION_URL,
        type: "POST",
        data:  userDto,
        dataType: 'json',
        contentType: 'application/json',

        success: function(data) {
            $('#m_userlogin').val('');
            $('#m_password').val('');
            $("#moderator_profile_add").modal('hide');
            $("#moderator_register_login").html('<b>'+data.login+'</b>');
            $("#moderator_register_success").modal("show");
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

