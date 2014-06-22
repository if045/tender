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