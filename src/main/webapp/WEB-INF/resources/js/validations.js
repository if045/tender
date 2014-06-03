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
                number: true
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
                number: "Неправильний формат вводу!"
            }
        }
    });
});