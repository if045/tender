var moderatorProfilePageSize = DEFAULT_PAGE_SIZE;
var moderatorProfileCurrPageNumber = 0;

var moderatorProfileSortDirection = false;
var moderatorProfileOrderBy = MODERATOR_PROFILE_DEFAULT_SORT_FIELD;
var ENTER_BUTTON_CODE = 13;

$(document).ready(function() {
    showUsersProfiles();
    populateConflictTable();

    $('#moderator_profilesnum').on('change', function() {
        moderatorProfilePageSize = $('#moderator_profilesnum').val();
        moderatorProfileCurrPageNumber = 0;
        showProfilesPage(moderatorProfileCurrPageNumber);
    });

    $("#moderator_profile_title").click(function(){
        sortUsersProfiles("firstName","moderator_profile_title");
    });

    $("#moderator_profile_login").click(function(){
        sortUsersProfiles("user.login","moderator_profile_login");
    });

    $("#moderator_profile_telephone").click(function(){
        sortUsersProfiles("telephone","moderator_profile_telephone");
    });

    $('#search_profiles').keypress(function(e) {
        if (e.keyCode == ENTER_BUTTON_CODE) {
            moderatorProfilePageSize = $('#moderator_profilesnum').val();
            moderatorProfileCurrPageNumber = 0;
            showProfilesPage(moderatorProfileCurrPageNumber);
            return false;
        }
    });
});

function showUsersProfiles() {
    var queryParams = '';

    if($('#search_profiles').val()!=""){
        queryParams += (queryParams.length==0)?"searchParam="+$('#search_profiles').val():"&searchParam="+$('#search_profiles').val();
    }

    showProfilesPagination(queryParams);

    queryParams += (queryParams.length==0)?"pageSize="+moderatorProfilePageSize:"&pageSize="+moderatorProfilePageSize;
    queryParams += "&pageNumber=" + moderatorProfileCurrPageNumber + "&sortDirection=" +
        ((moderatorProfileSortDirection)?"desc":"asc") + "&orderBy=" + moderatorProfileOrderBy;

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

function populateConflictTable() {
    $.getJSON(MODERATOR_URL + CONFLICTS, function (data) {
        var html = '';
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<tr><td align="center">' + data[i].title + '</td>' +
                '<td align="center">' + data[i].customerName + '</td>' +
                '<td align="center">' + data[i].sellerName + '</td>' +
                '<td align="center"><a href="#" data-toggle="modal" data-target="#moderator_conflict_mod_wind" button class="btn btn-default" type="button" data-dismiss="modal" onclick="writeModeratorConflictId();">Cancel</a></button></td></tr>';
        }

        $('#conflict_table').html(html);
    });
}

function showProfilesPagination(queryParams) {
    $.ajax({
        url: MODERATOR_PROFILES_NUMBER_URL,
        async: false,
        type: "GET",
        data:  queryParams,
        dataType:'json',

        success: function(data) {
            var dataSize = data;
            var pageNumber = Math.ceil(dataSize / moderatorProfilePageSize);

            if(dataSize > 0 && moderatorProfilePageSize < dataSize) {
                var html = '';
                html += '<li class="'+((moderatorProfileCurrPageNumber==0)?"disabled":"")+'"><a id="profiles_prev_page" href="#">&laquo;</a></li>';
                for(var z=1;z<=pageNumber;z++) {
                    html += '<li class="'+((moderatorProfileCurrPageNumber==z-1)?"active":"")+'"><a href="#" onclick="showProfilesPage('+(z-1)+');">'+z+'</a></li>';
                }
                html += '<li class="'+((moderatorProfileCurrPageNumber==pageNumber-1)?"disabled":"")+'"><a id="profiles_next_page" href="#">&raquo;</a></li>';

                $('.moderator_profile_page_pagination').html(html).show();
                $('#moderator_profile_pagination').show();

                if(moderatorProfileCurrPageNumber != 0) {
                    document.getElementById('profiles_prev_page').setAttribute("onclick", "showProfilesPage("+(moderatorProfileCurrPageNumber-1)+");");
                }

                if(moderatorProfileCurrPageNumber != pageNumber-1) {
                    document.getElementById('profiles_next_page').setAttribute("onclick", "showProfilesPage("+(moderatorProfileCurrPageNumber+1)+");");
                }
            } else {
                $('.moderator_profile_page_pagination').html('').hide();
                $('#moderator_profile_pagination').hide();
            }
        },
        error:function(){
            $('#moderator_profile_pagination').hide();
        }
    });
}

function showProfilesPage(pageNumber) {
    moderatorProfileCurrPageNumber = pageNumber;
    showUsersProfiles();
}

function sortUsersProfiles(orderByField, elementId) {
    moderatorProfileSortDirection = (moderatorProfileOrderBy == orderByField) ? !moderatorProfileSortDirection : false;
    moderatorProfileOrderBy = orderByField;

    $('#moderator_profile_items .sortable').removeClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
    if(moderatorProfileSortDirection == false) {
        $('#'+elementId+' .sortable').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
    } else {
        $('#'+elementId+' .sortable').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
    }

    showUsersProfiles();
}
