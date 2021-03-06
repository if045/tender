var isNewLogin = false;
$(document).ready(function() {
    $('#birth_date').datepicker({
        format: 'yyyy/mm/dd'
    });

    $("#populate_roles_dropdown").select2({
        placeholder: "Select role"
    });

    $("#populate_categories_dropdown").select2({
        placeholder: "Select categories"
    });

    $("#populate_locations_dropdown").select2({
        placeholder: "Select locations"
    });

    mapDropdownData(ROLES_URL, '#populate_roles_dropdown');
    mapDropdownData(CATEGORIES_URL, '#populate_categories_dropdown');
    mapDropdownData(LOCATIONS_URL, "#populate_locations_dropdown");

    showUserPersonalInfoPanelData();
    showCompanyInfoPanelData();
    showTradeSphereInfoPanelData();
    showRating();
    checkPerson();
    showEditPersonalPanel();
    showEditCompanyPanel();
    showEditTradeSpherePanel();
    setDefaultPersonRadio();
});

function mapDropdownData(url, id) {
    $.getJSON(url, {
        ajax : 'true'
    }, function(data){
        var html;
        var len = data.length;
        for (var i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">'
                + data[i].name + '</option>';
        }
        $(id).html(html);
    });
}

// Registration logic
function addRegisteredUser() {
    var newJson;
    var url;
    var roles = $('#populate_roles_dropdown').val();

    if(roles != null) {
        for (var i = 0; i < roles.length; i++) {
            var role = roles[i];
            if (role == SELLER_ID) {
                if($('#legal').is(":checked")) {
                    newJson = $.parseJSON(buildRegisteredSellerJSON());
                    url = SELLER_REGISTRATION_URL;
                } else if($('#private').is(":checked")) {
                    newJson = $.parseJSON(buildRegisteredPrivateSellerJSON());
                    url = PRIVATE_SELLER_REGISTRATION_URL;
                }
            } else {
                if($('#legal').is(":checked")) {
                    newJson = $.parseJSON(buildRegisteredCustomerJSON());
                    url = CUSTOMER_REGISTRATION_URL;
                } else if($('#private').is(":checked")) {
                    newJson = $.parseJSON(buildRegisteredPrivateCustomerJSON());
                    url = PRIVATE_CUSTOMER_REGISTRATION_URL;
                }
            }
        }
    }

    $.ajax({
        url: url,
        type: "POST",
        data:  JSON.stringify(newJson),
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            goToLoginPage();
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function buildRegisteredSellerJSON() {
    return '{' + buildUserData() + ',' + buildProfileData() + ',' +
           buildCompanyData() + ',' + buildTradeSphereData() + '}';
}

function buildRegisteredPrivateSellerJSON() {
    return '{' + buildUserData() + ',' + buildProfileData() + ',' +
        buildTradeSphereData() + '}';
}

function buildRegisteredCustomerJSON() {
    return '{' + buildUserData() + ',' + buildProfileData() + ',' +
        buildCompanyData() + '}';
}

function buildRegisteredPrivateCustomerJSON() {
    return '{' + buildUserData() + ',' + buildProfileData() + '}';
}

function buildUserData() {
    var login = $('#login').val();
    var password = $('#password').val();
    var roles = $('#populate_roles_dropdown').val();

    var passwordChecked = passwordIsChecked(password);

    if(!passwordChecked) {
        alert("passwords are mismatched");
        return;
    }

    if(login == '') {
        alert("type your login please!");
        return;
    }

    if(roles == '') {
        alert("choose at least one role");
        return;
    }

    return '"userDto":{'  +
           '"roles":['    + roles    + '],' +
           '"login":"'    + login    + '",' +
           '"password":"' + password + '"}';
}

function buildProfileData() {
    var firstNme = $('#first_name').val();
    var lastName = $('#last_name').val();
    var birthday = $('#birthday').val();
    var phoneNumber = $('#phone_number').val();
    var person;

    if($('#legal').is(":checked")) {
        person = LEGAL_PERSON;
    } else if($('#private').is(":checked")) {
        person = PRIVATE_PERSON;
    }

    if(firstNme == '') {
        alert("type your first name");
        return;
    }

    if(lastName == '') {
        alert("type your last name");
        return;
    }

    if(phoneNumber == '') {
        alert("type your phone number");
        return;
    }

    return '"profileDto":{' +
           '"firstName":"'  + firstNme    + '",' +
           '"lastName":"'   + lastName    + '",' +
           '"birthday":"'   + birthday    + '",' +
           '"telephone":"'  + phoneNumber + '",' +
           '"person":"'     + person      + '"}';
}

function buildCompanyData() {
    var companyName = $('#company_name').val();
    var city = $('#city').val();
    var street = $('#street').val();
    var buildingNumber = $('#building_number').val();
    var postcode = $('#postcode').val();
    var email = $('#email').val();
    var srnNumber = $('#srn_number').val();

    return '"companyDto":{' +
           '"email":"'         + email          + '",' +
           '"addressDto":{' +
           '"buildingNumber":"' + buildingNumber + '",'  +
           '"city":"'          + city           + '",' +
           '"street":"'        + street         + '",' +
           '"postcode":"'       + postcode       + '"},' +
           '"srnNumber":"'      + srnNumber      + '",'  +
           '"name":"'          + companyName    + '"}';
}

function buildTradeSphereData() {
    var locations = $('#populate_locations_dropdown').val();
    var categories = $('#populate_categories_dropdown').val();

    return '"tradeSphereDto":{' +
           '"locations":['  + locations  + '],' +
           '"categories":[' + categories + ']}';
}

function enableConfirmButton() {
    if($('#agreement').is(":checked")){
        $("#registration_confirm_button").removeAttr("disabled")
    } else {
        $("#registration_confirm_button").attr("disabled", "disabled")
    }
}

function hideCompanyDataPanel() {
    if($('#private').is(":checked")) {
        document.getElementById("company_panel").setAttribute('hidden','true');
    }
    hideRequiredRadio();
}

function showCompanyDataPanel() {
    if($('#legal').is(":checked")) {
        document.getElementById("company_panel").removeAttribute('hidden');
    }
    hideRequiredRadio();
}

function hideRequiredRadio() {
    $('#legal-radio').removeClass('required-radio');
    $('#private-radio').removeClass('required-radio');
}

function hideShowTradeSphereDataPanel() {
    var roles = $('#populate_roles_dropdown').val();

    if(roles != null) {
        for (var i = 0; i < roles.length; i++) {
            var role = roles[i];
            if (role == SELLER_ID) {
                document.getElementById("trade_sphere_panel").removeAttribute('hidden');
            } else {
                document.getElementById("trade_sphere_panel").setAttribute('hidden', 'hidden');
            }
        }
    } else {
        document.getElementById("trade_sphere_panel").setAttribute('hidden', 'hidden');
    }
}

function goToLoginPage() {
    window.location.href = LOGIN_PAGE_URL;
}

function passwordIsChecked(password) {
    var confirmPassword = $('#confirm_password').val();

    if(password == confirmPassword) {
        return true;
    } else {
        return false;
    }
}

// Fill profile logic
function showUserPersonalInfoPanelData() {
    var queryParams = '';
    var queryURL = USER_PROFILE_DATA_URL;
    if(getCookie("userRole") == "MODERATOR") {
        var userLogin = location.search.split('userLogin=')[1];
        queryParams += 'userLogin='+userLogin;
        queryURL = MODERATOR_PROFILE_VIEW_DATA_URL;
    }

    $.ajax({
        url: queryURL,
        type: "GET",
        data:  queryParams,
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            var firstName = checkNullData(data.personalInfoDto.profileDto.firstName);
            var lastName = checkNullData(data.personalInfoDto.profileDto.lastName);
            var email = checkNullData(data.personalInfoDto.userDto.login);
            var telephone = checkNullData(data.personalInfoDto.profileDto.telephone);
            var birthday = checkNullData(data.personalInfoDto.profileDto.birthday);
            var person = checkNullData(data.personalInfoDto.profileDto.person);

            person = checkPerson(person);


            var roles = '';
            var length = data.personalInfoDto.userDto.rolesNames.length;

            for(var i = 0; i < length; i++){
                var role = data.personalInfoDto.userDto.rolesNames[i].name;

                if(i == 0) roles += role;
                else roles += ', ' + role;

                if(role == CUSTOMER) {
                    document.getElementById("trade_sphere_info").setAttribute('hidden', 'true');
                }
                if(role == SELLER) {
                    document.getElementById("trade_sphere_info").removeAttribute('hidden');
                }
            }

            $('#first_name_info').html(firstName);
            $('#last_name_info').html(lastName);
            $('#roles_info').html(roles);
            $('#login_info').html(email);
            $('#telephone_info').html(telephone);
            $('#birthday_info').html(birthday);
            $('#person_info').html(person);
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function showCompanyInfoPanelData() {
    var queryParams = '';
    var queryURL = USER_PROFILE_DATA_URL;
    if(getCookie("userRole") == "MODERATOR") {
        var userLogin = location.search.split('userLogin=')[1];
        queryParams += 'userLogin='+userLogin;
        queryURL = MODERATOR_PROFILE_VIEW_DATA_URL;
    }

    $.ajax({
        url: queryURL,
        type: "GET",
        data:  queryParams,
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            var companyName = checkNullData(data.companyDto.name);
            var city = checkNullData(data.companyDto.addressDto.city);
            var street = checkNullData(data.companyDto.addressDto.street);
            var buildingNumber = checkNullData(data.companyDto.addressDto.buildingNumber);
            var email = checkNullData(data.companyDto.email);
            var postcode = checkNullData(data.companyDto.addressDto.postcode);
            var srn = checkNullData(data.companyDto.srnNumber);

            $('#company_name_info').html(companyName);
            $('#city_info').html(city);
            $('#street_info').html(street);
            $('#build_number_info').html(buildingNumber);
            $('#email_info').html(email);
            $('#postcode_info').html(postcode);
            $('#srn_info').html(srn);
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function showTradeSphereInfoPanelData() {
    var queryParams = '';
    var queryURL = USER_PROFILE_DATA_URL;
    if(getCookie("userRole") == "MODERATOR") {
        var userLogin = location.search.split('userLogin=')[1];
        queryParams += 'userLogin='+userLogin;
        queryURL = MODERATOR_PROFILE_VIEW_DATA_URL;
    }

    $.ajax({
        url: queryURL,
        type: "GET",
        data:  queryParams,
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            var i;
            var categories = '';
            var locations = '';
            var categoriesLength = data.tradeSphereDto.categoriesDto.length;
            var locationsLength = data.tradeSphereDto.locationsDto.length;

            for(i = 0; i < categoriesLength; i++){
                if(i == 0) categories += data.tradeSphereDto.categoriesDto[i].name;
                else categories += ', ' + data.tradeSphereDto.categoriesDto[i].name;
            }

            for(i = 0; i < locationsLength; i++){
                if(i == 0) locations += data.tradeSphereDto.locationsDto[i].name;
                else locations += ', ' + data.tradeSphereDto.locationsDto[i].name;
            }

            if(locations.length > 0 || categories.length > 0) {
                $('#locations_info').html(locations);
                $('#categories_info').html(categories);
                $('#trade_sphere_info').show();
            } else {
                $('#trade_sphere_info').hide();
            }
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function showRating() {
    var queryParams = '';
    var queryURL = USER_PROFILE_DATA_URL;
    if(getCookie("userRole") == "MODERATOR") {
        var userLogin = location.search.split('userLogin=')[1];
        queryParams += 'userLogin='+userLogin;
        queryURL = MODERATOR_PROFILE_VIEW_DATA_URL;
    }

    $.ajax({
        url: queryURL,
        type: "GET",
        data:  queryParams,
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            var averageCommunication = 0;
            var averageSpeed = 0;
            var averageLogistic = 0;

            var communicationDegree = 0;
            var speedDegree = 0;
            var logisticDegree = 0;

            var ratingLength = data.ratingDto.length;

            for(i = 0; i < ratingLength; i++){
                communicationDegree += data.ratingDto[i].communication;
                speedDegree += data.ratingDto[i].speed;
                logisticDegree += data.ratingDto[i].logistic;
            }

            averageCommunication = communicationDegree / ratingLength;
            averageSpeed = speedDegree / ratingLength;
            averageLogistic = logisticDegree / ratingLength;

            $("#communication_rating").rating('update', roundHalf(averageCommunication));
            $("#speed_rating").rating('update', roundHalf(averageSpeed));
            $("#logistic_rating").rating('update', roundHalf(averageLogistic));
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function checkNullData(value) {
    if((value == null) || (value == "") || (value == 0)) return "---";
    return value;
}

function roundHalf(num) {
    num = Math.round(num*2)/2;
    return num;
}

function checkPerson(person) {
    if(person == PRIVATE_PERSON) {
        document.getElementById("company_info").setAttribute('hidden', 'true');
        return "Private";
    }
    if(person == LEGAL_PERSON) {
        document.getElementById("company_info").removeAttribute('hidden');
        return "Legal";
    }
}

// Edit profile logic
function showEditPersonalPanel() {
    $(".personal-info").removeAttr("hidden");
    $(".company-info").attr("hidden", "hidden");
    $(".trade-sphere-info").attr("hidden", "hidden");
}

function showEditCompanyPanel() {
    $(".personal-info").attr("hidden", "hidden");
    $(".company-info").removeAttr("hidden");
    $(".trade-sphere-info").attr("hidden", "hidden");
}

function showEditTradeSpherePanel() {
    $(".personal-info").attr("hidden", "hidden");
    $(".company-info").attr("hidden", "hidden");
    $(".trade-sphere-info").removeAttr("hidden");
    setTradeSphereDropdownWithData('#populate_update_locations_dropdown', USER_PROFILE_DATA_URL, LOCATIONS_URL, TRADE_SPHERE_LOCATION);
    setTradeSphereDropdownWithData('#populate_update_categories_dropdown', USER_PROFILE_DATA_URL, CATEGORIES_URL, TRADE_SPHERE_CATEGORY);
}

function updateUserData() {
    var newJson = $.parseJSON(buildPersonalDataJSON());

    var url = USER_PERSONAL_DATA_URL;

    $.ajax({
        url: url,
        type: "PUT",
        data:  JSON.stringify(newJson),
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            if(isNewLogin) {
                goLogOut();
            } else {
                goToUserProfilePage();
            }
        },

        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function updateCompanyData() {
    var newJson = $.parseJSON(buildCompanyDataJSON());

    var url = USER_UPDATE_COMPANY_URL;

    $.ajax({
        url: url,
        type: "PUT",
        data:  JSON.stringify(newJson),
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            goToUserProfilePage();
        },

        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function updateTradeSphereData() {
    var newJson = $.parseJSON(buildTradeSphereDataJSON());

    var url = USER_UPDATE_TRADE_SPHERE_DATA;

    $.ajax({
        url: url,
        type: "PUT",
        data:  JSON.stringify(newJson),
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            goToUserProfilePage();
        },

        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function buildPersonalDataJSON() {
    return '{' + buildUserPersonalData() + ',' + buildProfileUpdateData() + '}';
}

function buildUserPersonalData() {
    var login = $('#login_to_update').val();
    var password = $('#password_to_update').val();

    isNewLogin = login != '';

    return '"login":"'    + login    + '",' +
           '"password":"' + password + '"';
}

function buildProfileUpdateData() {
    var firstNme = $('#first_name_to_update').val();
    var lastName = $('#last_name_to_update').val();
    var birthday = $('#birth_to_update').val();
    var phoneNumber = $('#phone_to_update').val();
    var person;

    if($('#legal_to_update').is(":checked")) {
        person = LEGAL_PERSON;
    } else if($('#private_to_update').is(":checked")) {
        person = PRIVATE_PERSON;
    }

    return '"profileDto":{' +
        '"firstName":"'  + firstNme    + '",' +
        '"lastName":"'   + lastName    + '",' +
        '"birthday":"'   + birthday    + '",' +
        '"telephone":"'  + phoneNumber + '",' +
        '"person":"'     + person      + '"}';
}

function buildCompanyDataJSON() {
    var companyName = $('#company_name_to_update').val();
    var city = $('#city_to_update').val();
    var street = $('#street_to_update').val();
    var buildingNumber = $('#building_number_to_update').val();
    var postcode = $('#postcode_to_update').val();
    var email = $('#email_to_update').val();
    var srnNumber = $('#srn_number_to_update').val();

    return '{"name":"'      + companyName + '",' +
        '"srnNumber":"'     + srnNumber + '",' +
        '"email":"'         + email + '",' +
        '"addressDto":{' +
            '"city":"'              + city+ '",' +
            '"street":"'            + street + '",' +
            '"buildingNumber":"'    + buildingNumber + '",' +
            '"postcode":"'          + postcode +'"}}';
}

function buildTradeSphereDataJSON() {
    var locations = $('#populate_update_locations_dropdown').val();
    var categories = $('#populate_update_categories_dropdown').val();

    return '{' +
        '"locations":['  + locations  + '],' +
        '"categories":[' + categories + ']}';
}

function setDefaultPersonRadio() {

    $.getJSON(USER_PROFILE_DATA_URL, function (data) {
        var person = data.personalInfoDto.profileDto.person;

        var value;

        if (person == LEGAL_PERSON) {
            value = 'legal';
        } else if (person == PRIVATE_PERSON) {
            value = 'private';
        }

        var $radios = $('input:radio[name=person]');
        if ($radios.is(':checked') === false) {
            $radios.filter('[value=' + value + ']').prop('checked', true);
        }
    });
}

function populateInputValues(el_id, url, type) {
    $.getJSON(url, function (data) {
        var result = [];
        var temp;
        switch(type) {
            case (TRADE_SPHERE_LOCATION) :
                temp = data.tradeSphereDto.locationsDto;
                break;
            case(TRADE_SPHERE_CATEGORY) :
                temp = data.tradeSphereDto.categoriesDto;
                break;
        }
        var length = temp.length;

        for (var i = 0; i < length; i++) {
            result.push(temp[i].id);
        }
        result = result.join(",");
        $(el_id).attr("value", result);
    });
}

function wrapInputWithSelect2(el_id, data) {
    $(el_id).select2({
        width: "100%",
        multiple: true,
        placeholder: EMPTY_FIELD_MESSAGE,
        data: data
    });
}

function prepareDropdownData(data, type) {
    var result = [];
    var temp = [];
    temp = data;

    for ( var i = 0 ; i < temp.length; i++) {
        var self = {};
        self.id = temp[i].id;
        self.text = temp[i].name;
        result.push(self);
    }

    return result;
}

/**
 *  el_id - id of the HTML element to act on
 *  url - path for getting user data
 *  data_url - path for getting information for trade sphere (categories or locations)
 *  type - key in ["location", "category"] - for location or categories
**/
function setTradeSphereDropdownWithData(el_id, url, data_url, type) {
    $.getJSON(data_url, function(data){
        var dropdownData = prepareDropdownData(data, type);
        populateInputValues(el_id, url, type);
        wrapInputWithSelect2(el_id, dropdownData);
    });
}

function checkValidDropdownInputs() {
    var categories = document.getElementById("populate_update_categories_dropdown").getAttribute('value');
    var locations = document.getElementById("populate_update_locations_dropdown").getAttribute('value');

    if((locations == []) || ( categories == [])) {
        document.getElementById("trade-sphere-info").setAttribute('disabled', 'disabled');
    } else {
        document.getElementById("trade-sphere-info").removeAttribute('disabled');
    }
}
