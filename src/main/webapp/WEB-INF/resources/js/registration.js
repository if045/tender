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

    addRegisteredUser();
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

function addRegisteredUser() {
    var newJson = $.parseJSON(buildRegisteredUserJSON());
    $.ajax({
        url: USER_DATA_URL,
        type: "POST",
        data:  JSON.stringify(newJson),
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            goToHomePage();
        },
        error: function(){
            alert(ERROR_MESSAGE);
        }
    });
}

function buildRegisteredUserJSON() {
    return '{' + buildUserData() + ',' + buildProfileData() + ',' +
           buildCompanyData() + ',' + buildTradeSphereData() + '}';
}

function buildUserData() {
    login = $('#login').val();
    password = $('#password').val();
    roles = $('#populate_roles_dropdown').val();

    return '"userDto":{'  +
           '"roles":['    + roles    + '],' +
           '"login":"'    + login    + '",' +
           '"password":"' + password + '"}';
}

function buildProfileData() {
    firstNme = $('#first_name').val();
    lastName = $('#last_name').val();
    birthday = $('#birthday').val();
    phoneNumber = $('#phone_number').val();

    if($('#legal').is(":checked")) {
        person = LEGAL_PERSON;
    } else if($('#private').is(":checked")) {
        person = PRIVATE_PERSON;
    }

    return '"profileDto":{' +
           '"firstName":"'  + firstNme    + '",' +
           '"lastName":"'   + lastName    + '",' +
           '"birthday":"'   + birthday    + '",' +
           '"telephone":"'  + phoneNumber + '",' +
           '"person":"'     + person      + '"}';
}

function buildCompanyData() {
    companyName = $('#company_name').val();
    city = $('#city').val();
    street = $('#street').val();
    buildingNumber = $('#building_number').val();
    postcode = $('#postcode').val();
    email = $('#email').val();
    srnNumber = $('#srn_number').val();

    return '"companyDto":{' +
           '"email":"'         + email          + '",' +
           '"addressDto":{' +
           '"buildingNumber":' + buildingNumber + ','  +
           '"city":"'          + city           + '",' +
           '"street":"'        + street         + '",' +
           '"postcode":'       + postcode       + '},' +
           '"srnNumber":'      + srnNumber      + ','  +
           '"name":"'          + companyName    + '"}';
}

function buildTradeSphereData() {
    locations = $('#populate_locations_dropdown').val();
    categories = $('#populate_categories_dropdown').val();

    return '"tradeSphereDto":{' +
           '"locations":['  + locations  + '],' +
           '"categories":[' + categories + ']}';
}

function enableConfirmButton() {
    if($('#agreement').is(":checked")){
        $("#confirm_button").removeAttr("disabled")
    } else {
        $("#confirm_button").attr("disabled", "disabled")
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
    roles = $('#populate_roles_dropdown').val();

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

function goToHomePage() {
    window.location.href = HOME_PAGE_URL;
}