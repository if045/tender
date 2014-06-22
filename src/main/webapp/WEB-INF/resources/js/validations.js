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

    jQuery.validator.addMethod("password", function(value) {
        return /^[A-Za-z0-9\d=!]*$/.test(value) // consists of only these
            && /[a-z]/.test(value) // has a lowercase letter
            && /\d/.test(value); // has a digit
    }, "Please enter only letters");

    registrationPageValidation();
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

    $("#company_data_validation").validate({
        rules: {
            company_name: {
                minlength: 3,
                maxlength: 50,
                lettersonly: true
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
        },

        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
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