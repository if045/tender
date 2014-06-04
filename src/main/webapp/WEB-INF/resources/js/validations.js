$(document).ready(function () {

    $("#create_tender_form_validation").validate({

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

            description: {
                maxlength: 100
            }
        },

        messages: {

            title: {
                required: "Це поле не може бути пустим!",
                minlength: "Мінімальна довжина 4 символа",
                maxlength: "Максимальна довжина 30 символів"
            },

            price: {
                required: "Це поле не може бути пустим!",
                number: "Неправильний формат вводу!"
            },

            quantity: {
                required: "Це поле не може бути пустим!",
                digits: "Неправильний формат вводу!"
            },

            description: {
                maxlength: "Максимальна довжина 100 символів"
            }
        }
    });

    $("#edit_tender_form").validate({
       rules: {
           description: {
               maxlength: 100
           }
       },

        messages: {
            description: {
                maxlength: "100 characters - the maximum length you can enter"
            }
        }
    });

    $('#create_proposal_form').validate({
        rules: {
            description: {
                maxlength: 100
            },

            allowance: {
                required: true,
                number: true,
                minlength: 1,
                maxlength: 3
            },

            price: {
                number: true
            }
        },

        messages: {
            allowance: {
                required: "This field can not be empty",
                number: "Input format - numbers",
                minlength: "The minimum length of 1 character",
                maxlength: "The maximum length of 3 characters"
            },

            description: {
                maxlength: "100 characters - the maximum length you can enter"
            },

            price: {
                number: "Input format - numbers"
            }
        }
    });
});