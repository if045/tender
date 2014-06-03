$(document).ready(function() {
    $('#endDate').datepicker({
        format: 'mm-dd-yyyy',
         startDate: '-5y'
    });

    $('#search_input').keydown(function(event) {
        if (event.keyCode == 13) {
            //search query

            return false;
        }
    });

    $('#createTenderWindow').on('shown.bs.modal', function () {
        $('.datepicker').addClass('modal_datepicker');

        $("#create_tender_unit_category").select2({
             placeholder: "Select a category"
        });

        $("#create_tender_unit_item").select2({
             placeholder: "Select a item"
        });

        $("#create_tender_location").select2({
             placeholder: "Select a location"
        });

        $.getJSON('/tenders/locations', {
              ajax : 'true'
        }, function(loc){
              var html = ' ';
              var len = loc.length;
              for (var i = 0; i < len; i++) {
                  html += '<option value="' + loc[i].id + '">'
                            +loc[i].name + '</option>';
              }

              $('#create_tender_location').html(html);
        });

        $.getJSON('/tenders/categories', {
              ajax : 'true'
        }, function(loc){
              var html = ' ';
              var len = loc.length;
              for (var i = 0; i < len; i++) {
                  html += '<option value="' + loc[i].id + '">'
                          +loc[i].name + '</option>';
              }

              $('#create_tender_unit_category').html(html);
        });

        $.getJSON('/tenders/items', {
              ajax : 'true'
        }, function(loc){
              var html = ' ';
              var len = loc.length;
              for (var i = 0; i < len; i++) {
                  html += '<option value="' + loc[i].id + '">'
                          +loc[i].name + '</option>';
              }

              $('#create_tender_unit_item').html(html);
           });
     });    
});

function showDeals() {
    window.location.href='/deals/';
}