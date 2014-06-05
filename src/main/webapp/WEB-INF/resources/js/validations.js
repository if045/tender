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

            description: {
                maxlength: "The maximum length of 100 characters"
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

    function validateDescription(id) {
        $('#' + id).validate({
           rules: {
               description: {
                   maxlength: 100
               }
           } ,

            messages: {
                description: "The maximum length of 100 characters"
            }
        });
    }
});