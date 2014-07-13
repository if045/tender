$(document).ready(function () {

    var validationForTenderHomePrice = {
        rules: {
            price: {
                number: true
            }
        },

        showErrors: function (errorMap, errorList) {
            if (errorList.length) {
                $("div.error").html(errorList[0].message);
            } else {
                $("div.error").html("");
            }
        }
    };

    var validationForAllInput = {
        rules: {
            title: {
                required: true,
                minlength: 4,
                maxlength: 30
            },

            price: {
                required: true,
                number: true
            },

            quantity: {
                required: true,
                digits: true
            },

            allowance: {
                required: true,
                number: true,
                minlength: 1,
                maxlength: 3
            },

            description: {
                maxlength: 100
            }
        },

        messages: {
            title: {
                required: "This field can not be empty!",
                minlength: "The minimum length of 4 character",
                maxlength: "The maximum length of 30 characters"
            },

            price: {
                required: "This field can not be empty!",
                number: "Input format - numbers!"
            },

            quantity: {
                required: "This field can not be empty!",
                digits: "Input format - digits!"
            },

            allowance: {
                required: "This field can not be empty",
                number: "Input format - numbers",
                minlength: "The minimum length of 1 character",
                maxlength: "The maximum length of 3 characters"
            },

            description: {
                maxlength: "The maximum length of 100 characters"
            }
        }
    };

    jQuery("#create_tender_form_validation").validate(validationForAllInput);
    jQuery("#edit_tender_form").validate(validationForAllInput);
    jQuery("#create_proposal_form").validate(validationForAllInput);
    jQuery("#tendersHome_validation").validate(validationForTenderHomePrice);

    jQuery.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[a-z]+$/i.test(value);
    }, "Please enter only letters");

    jQuery.validator.addMethod("lettersAndWhitespaces", function(value, element) {
        return this.optional(element) || /^[a-z]/i.test(value);
    }, "Please enter only letters");

    jQuery.validator.addMethod("password", function(value) {
        return /^[A-Za-z0-9\d=!]*$/.test(value) // consists of only these
            && /[a-z]/.test(value) // has a lowercase letter
            && /\d/.test(value); // has a digit
    }, "Password must not be shorter than 6 characters and contain both numbers and letters");

    registrationPageValidation();
    addNewModeratorValidation();
    updateProfileValidation();
});

function registrationPageValidation() {

    $("#user_data_validation").validate({
        rules: {
            login: {
                email: true,
                maxlength: 30,
                required: true
            },

            password: {
                required: true,
                minlength: 6,
                password: true
            },

            confirm_password: {
                equalTo: "#password",
                required: true
            },

            first_name: {
                minlength: 3,
                maxlength: 20,
                lettersonly: true,
                required: true
            },

            last_name: {
                minlength: 3,
                maxlength: 20,
                lettersonly: true,
                required: true
            },

            phone_number: {
                maxlength: 10,
                minlength: 10,
                digits: true,
                required: true
            }
        },

        messages : {
            first_name: {
                lettersonly: "please enter your name correctly, like: John"
            },
            last_name: {
                lettersonly: "please enter your name correctly, like: Doe"
            }
        },

        highlight: function(element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            document.getElementById("registration_confirm_button").setAttribute('disabled', 'disabled');
        },

        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            if($('#agreement').is(":checked")) {
                document.getElementById("registration_confirm_button").removeAttribute('disabled');
            }
        },

        errorElement: 'span',
        errorClass: 'help-block',

        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

    $("#company_data_validation").validate({
        rules: {
            company_name: {
                minlength: 3,
                maxlength: 50,
                lettersAndWhitespaces: true
            },

            city: {
                minlength: 3,
                maxlength: 30,
                lettersonly: true
            },

            street: {
                minlength: 3,
                maxlength: 30,
                lettersonly: true
            },

            building_number: {
                maxlength: 5
            },

            postcode: {
                digits: true
            },

            email: {
                email: true,
                maxlength: 30
            },

            srn_number: {
                digits: true
            }
        },

        messages : {

        },

        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
            document.getElementById("registration_confirm_button").setAttribute('disabled', 'disabled');
        },

        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
            if($('#agreement').is(":checked")) {
                document.getElementById("registration_confirm_button").removeAttribute('disabled');
            }
        },

        errorElement: 'span',
        errorClass: 'help-block',

        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
}

function updateProfileValidation() {
    $("#profile_data_validation").validate({
        rules: {
            login: {
                email: true,
                maxlength: 30
            },

            password_to_update: {
                minlength: 6,
                password: true,
                required: false
            },

            confirm_password_to_update: {
                equalTo: "#password_to_update"
            },

            first_name: {
                minlength: 3,
                maxlength: 20,
                lettersonly: true
            },

            last_name: {
                minlength: 3,
                maxlength: 20,
                lettersonly: true
            },

            phone_number: {
                maxlength: 10,
                minlength: 10,
                digits: true
            }
        },

        messages : {
            first_name: {
                lettersonly: "please enter your name correctly, like: John"
            },
            last_name: {
                lettersonly: "please enter your name correctly, like: Doe"
            }
        },

        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
            document.getElementById("personal-info").setAttribute('disabled', 'disabled');
        },

        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
            document.getElementById("personal-info").removeAttribute('disabled');
        },

        errorElement: 'span',
        errorClass: 'help-block',

        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

    $("#profile_company_data_validation").validate({
        rules: {
            company_name: {
                minlength: 3,
                maxlength: 50,
                lettersAndWhitespaces: true
            },

            city: {
                minlength: 3,
                maxlength: 30,
                lettersonly: true
            },

            street: {
                minlength: 3,
                maxlength: 30,
                lettersonly: true
            },

            building_number: {
                maxlength: 5
            },

            postcode: {
                digits: true
            },

            email: {
                email: true,
                maxlength: 30
            },

            srn_number: {
                digits: true
            }
        },

        messages : {

        },

        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
            document.getElementById("company-info").setAttribute('disabled', 'disabled');
        },

        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
            document.getElementById("company-info").removeAttribute('disabled');
        },

        errorElement: 'span',
        errorClass: 'help-block',

        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
}

function addNewModeratorValidation() {
    $("#new_moderator_data_validation").validate({
        rules: {
            m_userlogin: {
                email: true,
                maxlength: 30,
                required: true
            },

            m_password: {
                minlength: 6,
                password: true
            },

            m_confirm_password: {
                equalTo: "#m_password",
                minlength: 6
            }
        },

        highlight: function(element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },

        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        },

        errorElement: 'span',
        errorClass: 'help-block',

        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

    $('#moderator_profile_add').bind('change keyup', function() {
        if($(this).validate().checkForm()) {
            $('#add_moderator_button').attr('disabled', false);
        } else {
            $('#add_moderator_button').attr('disabled', true);
        }
    });
}