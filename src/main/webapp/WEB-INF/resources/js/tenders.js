        $(document).ready(function() {
            $('#startDate, #endDate, #create_tender_enddate').datepicker({
                format: 'yyyy/mm/dd',
                startDate: '-5y'
            });

            $("#category_filter").select2({
                placeholder: "All categories"
            });

            $("#item_dropdown").select2({
                placeholder: "All items"

            });

            $("#location_filter").select2({
                placeholder: "All locations"
            });

            $("#status_filter").select2({
                placeholder: "All active statuses"
            });

            $("#location_filter, #item_dropdown, #status_filter, #price_from, #price_to, #date_from, #date_to").change(function() {
                enableFilterButtons();
            });

            $("#category_filter").change(function() {
                enableFilterButtons();
                $("#item_dropdown").select2('val', 'All');
                var str='';
                var categories=new Array();
                categories=$('#category_filter').val();
                if (categories!=null){
                    str += "categories="+categories;
                }
                $.ajax({
                    url: "/tenders/items",
                    type: "GET",
                    data:  str,
                    dataType:'json',

                    success: function(data) {
                        var html = '';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].id + '">'
                                + data[i].name + '</option>';
                        }

                        $('#item_dropdown').html(html);
                    },
                    error:function(){
                        alert("ERROR");
                    }
                });
            });

            $.getJSON('/tenders/statuses', {
                  ajax : 'true'
                }, function(data){
                  var html;
                  var len = data.length;
                  for (var i = 0; i < len; i++) {
                      html += '<option value="' + data[i].id + '">'
                               + data[i].name + '</option>';
                  }
                  $('#status_filter').html(html);
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

                $('#location_filter').html(html);
            });

            $.getJSON('/tenders/categories', function(data){
                var html = ' ';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                }

                $('#category_filter').html(html);
            });

            populateItemDropdown();
            showTenders();

            $('#search_input').keydown(function(event) {
                if (event.keyCode == 13) {
                    //search query

                    return false;
                }
            });

            $('#createTenderWindow').on('shown.bs.modal', function () {
                $('.datepicker').addClass('modal_datepicker');

                $("#radio_group").change(function() {
                    $("#create_tender_unit_item").val('0');
                    var str='';
                    var category=$('#create_tender_unit_category').val();
                    if (category!=0){
                        str += "category="+category;
                    }
                    if($("#option2").is(":checked")){
                        str += (str.length==0)?"type="+$("#option2").val():"&type="+$("#option2").val();
                    }
                    if($("#option3").is(":checked")){
                        str += (str.length==0)?"type="+$("#option3").val():"&type="+$("#option3").val();
                    }
                    $.ajax({
                        url: '/items',
                        type: "GET",
                        data:  str,
                        dataType:'json',

                        success: function(loc) {
                            var html = '<option value="0">--Select One--</option>';
                            var len = loc.length;
                            for (var i = 0; i < len; i++) {
                                html += '<option value="' + loc[i].name + '">'
                                    +loc[i].name + '</option>';
                            }

                            $('#create_tender_unit_item').html(html);
                        },
                        error:function(){
                            alert("ERROR");
                        }
                    });
                });
                $("#create_tender_unit_category").change(function() {
                    $("#create_tender_unit_item").val('0');
                    var str='';
                    var category=$('#create_tender_unit_category').val();
                    if (category!=0){
                        str += "category="+category;
                    }
                    if($("#option2").is(":checked")){
                        str += (str.length==0)?"type="+$("#option2").val():"&type="+$("#option2").val();
                    }
                    if($("#option3").is(":checked")){
                        str += (str.length==0)?"type="+$("#option3").val():"&type="+$("#option3").val();
                    }
                    $.ajax({
                        url: '/items',
                        type: "GET",
                        data:  str,
                        dataType:'json',

                        success: function(loc) {
                            var html = '<option value="0">--Select One--</option>';
                            var len = loc.length;
                            for (var i = 0; i < len; i++) {
                                html += '<option value="' + loc[i].name + '">'
                                    +loc[i].name + '</option>';
                            }

                            $('#create_tender_unit_item').html(html);
                        },
                        error:function(){
                            alert("ERROR");
                        }
                    });
                });

                $("#radio_group, #create_tender_unit_category, #create_tender_unit_item").change(function() {
                    newItem();
                });

                $("#create_tender_unit_quantity, #create_tender_unit_item, #create_tender_unit_measurement, #radio_group, #create_tender_unit_category, #create_tender_unit_newitem").change(function() {
                    enableAddUnitButton();
                });

                $("#create_tender_location, #create_tender_title, #create_tender_price, #create_tender_enddate_input, #create_tender_description, #create_tender_unit_quantity, #create_tender_unit_item, #create_tender_unit_measurement, #radio_group, #create_tender_unit_category, #create_tender_unit_newitem, #create_tender_units_table_body").change(function() {
                    enableCreateTenderButton();
                });

                $("#add_unit_button").click(function() {
                    enableCreateTenderButton();
                });

                $("#create_tender_location").select2({
                    placeholder: "All"
                });

                $.getJSON('/locations', function(loc){
                    var html = ' ';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].id + '">'
                            +loc[i].name + '</option>';
                    }

                    $('#create_tender_location').html(html);
                });

                $.getJSON('/categories', function(loc){
                    var html = '<option value="0">--Select One--</option>';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].name + '">'
                            +loc[i].name + '</option>';
                    }

                    $('#create_tender_unit_category').html(html);
                });

                $.getJSON('/items', function(loc){
                    var html = '<option value="0">--Select One--</option>';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].name + '">'
                            +loc[i].name + '</option>';
                    }

                    $('#create_tender_unit_item').html(html);
                });

                $.getJSON('/measurements', function(loc){
                    var html = '<option value="0">--Select One--</option>';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].name + '">'
                            + loc[i].name + '</option>';
                    }

                    $('#create_tender_unit_measurement').html(html);
                });
            });
        });


        var newString = '';

        function addUnit() {
            if (newString==""){
                if (($('#create_tender_unit_item').val()!=0)&($('#create_tender_unit_newitem').val()=="")){
                    newString+='{"units" : [{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+$('#create_tender_unit_category').val()+'\"'+
                        ',"item":'+'\"'+$('#create_tender_unit_item').val()+'\"'+',"measurment":'+'\"'+$('#create_tender_unit_measurement').val()+'\"'+
                        ',"itemType":'+'\"'+$("input:radio[name ='options']:checked").val()+'\"'+'}]}';
                }
                if (($('#create_tender_unit_newitem').val()!="")&($('#create_tender_unit_item').val()==0)){
                    newString+='{"units" : [{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+$('#create_tender_unit_category').val()+'\"'+
                        ',"item":'+'\"'+$('#create_tender_unit_newitem').val()+'\"'+',"measurment":'+'\"'+$('#create_tender_unit_measurement').val()+'\"'+
                        ',"itemType":'+'\"'+$("input:radio[name ='options']:checked").val()+'\"'+'}]}';
                }
            }else{
                if (($('#create_tender_unit_item').val()!=0)&($('#create_tender_unit_newitem').val()=="")){
                    var strf = newString.substring(0,newString.indexOf(']'));
                    var strm = ',{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+$('#create_tender_unit_category').val()+'\"'+
                        ',"item":'+'\"'+$('#create_tender_unit_item').val()+'\"'+',"measurment":'+'\"'+$('#create_tender_unit_measurement').val()+'\"'+
                        ',"itemType":'+'\"'+$("input:radio[name ='options']:checked").val()+'\"'+'}';
                    var stre = newString.substring(newString.indexOf(']'));
                    newString = strf+strm+stre;
                }
                if (($('#create_tender_unit_newitem').val()!="")&($('#create_tender_unit_item').val()==0)){
                    var strf = newString.substring(0,newString.indexOf(']'));
                    var strm = ',{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+$('#create_tender_unit_category').val()+'\"'+
                        ',"item":'+'\"'+$('#create_tender_unit_newitem').val()+'\"'+',"measurment":'+'\"'+$('#create_tender_unit_measurement').val()+'\"'+
                        ',"itemType":'+'\"'+$("input:radio[name ='options']:checked").val()+'\"'+'}';
                    var stre = newString.substring(newString.indexOf(']'));
                    newString = strf+strm+stre;
                }
            }
            var newJson=$.parseJSON(newString);
            var html = '';
            var len = newJson.units.length;
            $('#create_tender_units_table').show();
            for (var i = 0; i < len; i++) {
                html += '<tr><td align="center">'+newJson.units[i].item +'</td>' +
                    '<td align="center" class="units_table_body_type">' + newJson.units[i].itemType + '</td>' +
                    '<td align="center" class="units_table_body_category">' + newJson.units[i].category + '</td>' +
                    '<td align="center" class="units_table_body_quantity">' + newJson.units[i].quantity + '</td>' +
                    '<td align="center" class="units_table_body_measurement">' + newJson.units[i].measurment + '</td>'+
                    '<td align="center" class="units_table_body_action"><span class="glyphicon glyphicon-remove"  onclick="removeUnit('+i+')"></span></td></tr>';
            }
            $('#create_tender_units_table_body').html(html);
        }

        function removeUnit(id) {
            if (newString!=""&newString.length>130) {
                var strf = newString.substring(0,newString.indexOf('{"quantity'));
                var beforesplit = newString.replace(newString.substring(0,newString.indexOf('"quantity')),'').substring(0,newString.replace(newString.substring(0,newString.indexOf('"quantity')),'').indexOf(']'));
                var body = '';
                var unitsArray = new Array();
                unitsArray = beforesplit.split(',{');
                for (var i =0;i<unitsArray.length;i++){
                    if (id==i){
                    }else{
                       body+='{'+unitsArray[i]+',';
                    }
                }
                var strb=body.substring(0,body.lastIndexOf(','));
                var strl = ']}';
                newString = strf +strb+ strl;
                var newJson=$.parseJSON(newString);
                var html = '';
                var len = newJson.units.length;
                for (var i = 0; i < len; i++) {
                    html += '<tr><td align="center">'+newJson.units[i].item +'</td>' +
                        '<td align="center" class="units_table_body_type">' + newJson.units[i].itemType + '</td>' +
                        '<td align="center" class="units_table_body_category">' + newJson.units[i].category + '</td>' +
                        '<td align="center" class="units_table_body_quantity">' + newJson.units[i].quantity + '</td>' +
                        '<td align="center" class="units_table_body_measurement">' + newJson.units[i].measurment + '</td>'+
                        '<td align="center" class="units_table_body_action"><span class="glyphicon glyphicon-remove"  onclick="removeUnit('+i+')"></span></td></tr>';
                }
                $('#create_tender_units_table_body').html(html);
            }else
            if (newString.length<130) {
                newString = '';
                var newJson= '';
                var html = '';
                $('#create_tender_units_table').hide();
                $('#create_tender_units_table_body').html(html);
            }
            if (newString==""){
                $("#create_tender_button").attr("disabled", "disabled");
            }
        }

        function enableAddUnitButton() {
            var quantity=$('#create_tender_unit_quantity').val();
            var category=$('#create_tender_unit_category').val();
            var item=$('#create_tender_unit_item').val();
            var newItem=$("#create_tender_unit_newitem").val();
            var measurement=$('#create_tender_unit_measurement').val();
            var option2=$("#option2").is(":checked");
            var option3=$("#option3").is(":checked");
            if (quantity!=""&category!=0&(item!=0|newItem!="")&measurement!=0&(option2|option3)){
                $("#add_unit_button").removeAttr("disabled");
            }else {
                $("#add_unit_button").attr("disabled", "disabled");
            }
        }

        function enableCreateTenderButton() {
            var unitTable = $('#create_tender_units_table').is(":hidden");
            var title = $("#create_tender_title").val();
            var price = $("#create_tender_price").val();
            var date = $("#create_tender_enddate_input").val();
            var description = $("#create_tender_description").val();
            if (!unitTable&title!=""&price!=""&date!=""&description!=""){
                $("#create_tender_button").removeAttr("disabled");
            }else {
                $("#create_tender_button").attr("disabled", "disabled");
            }
        }

        function newItem(){
            var item=$('#create_tender_unit_item').val();
            if(item==0){
                $("#create_tender_unit_newitem").removeAttr("disabled");
            }else{
                $("#create_tender_unit_newitem").val("");
                $("#create_tender_unit_newitem").attr("disabled", "disabled");
            }
        }

        function cleanCreateTenderFields() {
            $("#create_tender_unit_quantity").val("");
            $("#create_tender_unit_newitem").val("");
            $("#create_tender_unit_category").val('0');
            $("#create_tender_unit_item").val('0');
            $("#create_tender_unit_measurement").val('0');
            $("#option1").prop('checked', true);
            $("#create_tender_location").select2('val', 'All');
            $("#create_tender_description").val("");
            $("#create_tender_enddate_input").val("");
            $("#create_tender_price").val("");
            $("#create_tender_title").val("");
            $("#add_unit_button").attr("disabled", "disabled");
            $("#create_tender_button").attr("disabled", "disabled");
            newString = '';
            $('#create_tender_units_table').hide();
            showTenders();
        }

        function createTender() {
            var strf = newString.substring(0, newString.lastIndexOf('}'));
            var createTenderLocations=new Array();
            createTenderLocations=$('#create_tender_location').val();
            var locationStr='';
            if (createTenderLocations!=null){
                for (var i = 0; i < createTenderLocations.length; i++){
                    locationStr+='{"id":'+'\"'+createTenderLocations[i]+'\"'+'},';
                }
            }else{
                locationStr+='{"id":'+'\"'+0+'\"'+'},'
            }
            var strm = ', "locations" : ['+locationStr.substring(0,locationStr.lastIndexOf(','))+']'+', "title":'+'\"'+$('#create_tender_title').val()+'\"'
                        +',"suitablePrice":'+'\"'+$('#create_tender_price').val()+'\"'+',"endDate":'+'\"'+$('#create_tender_enddate_input').val()+'\"'
                        +',"description":'+'\"'+$('#create_tender_description').val()+'\"'+'}';
            newString=strf+strm;
            var newJson=$.parseJSON(newString);
            $.ajax({
                url: "/tenders",
                type: "POST",
                data:  JSON.stringify(newJson),
                dataType:'json',
                contentType: 'application/json',

                success: function(data) {
                },
                error:function(){
                }
            });
            applyFilters();
            $('#createTenderWindow').modal('hide');
            cleanCreateTenderFields();
        }

        function populateItemDropdown() {
            $.getJSON('/tenders/items', function (data) {
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                }

                $('#item_dropdown').html(html);
            });
        }

        function showTenders() {
            $.getJSON('/tenders', function (data) {
                var html = '';
                var len = data.length;

                if(len > 0) {
                    for (var i = 0; i < len; i++) {
                        html += '<tr><td align="center"><a href="/tenderView/' + data[i].id + '">' + data[i].title + '</a></td>' +
                                '<td align="center">' + data[i].authorName + '</td>' +
                                '<td align="center">' + data[i].categories + '</td>' +
                                '<td align="center">' + data[i].locations + '</td>' +
                                '<td align="center">' + data[i].suitablePrice + '</td>' +
                                '<td align="center">' + data[i].status + '</td>' +
                                '<td align="center">' + data[i].proposals + '</td>' +
                                '<td align="center">' +
                                    '<div class="btn-group">' +
                                        '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                                        '<ul class="dropdown-menu">' +
                                            '<li><a href="/tenderView/' + data[i].id + '">View</a></li>' +
                                            '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow">Create proposal</a></li>' +
                                            '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>' +
                                        '</ul>' +
                                    '</div>' +
                                '</td></tr>';
                    }

                    $('#user_message').html('');
                    $('#tender_items').show();
                    $('#tenders').html(html);

                    var pageItemsNumber = $('#pagination_itemsnum').val();
                    if(pageItemsNumber < len) {
                        $('#pagination').show();
                    } else {
                        $('#pagination').hide();
                    }
                } else {
                    $('#user_message').html('<h4>Your filter parameters did not match any tender</h4>');
                    $('#tender_items').hide();
                    $('#pagination').hide();
                }
            });
        }

        function clearFilters() {
            disableFilterButtons();
            $("#price_from").val("");
            $("#price_to").val("");
            $("#date_from").val("");
            $("#date_to").val("");
            $("#location_filter").select2('val', 'All');
            $("#category_filter").select2('val', 'All');
            $("#item_dropdown").select2('val', 'All');
            $("#status_filter").select2('val', 'All');

            showTenders();
        }

        function enableFilterButtons() {
            $("#filter_button").removeAttr("disabled");
            $("#clear_button").removeAttr("disabled");
        }

        function disableFilterButtons() {
            $("#filter_button").attr("disabled", "disabled");
            $("#clear_button").attr("disabled", "disabled");
        }

        function applyFilters() {
            disableFilterButtons();
            var str='';
            if($("#price_from").val()!=""){
                str+="minPrice="+$("#price_from").val();
            }
            if($("#price_to").val()!=""){
                str += (str.length==0)?"maxPrice="+$("#price_to").val():"&maxPrice="+$("#price_to").val();
            }
            var items=new Array();
            items=$('#item_dropdown').val();
            if (items!=null){
                str += (str.length==0)?"items="+items:"&items="+items;
            }
            
            var categories = new Array();
            categories = $('#category_filter').val();
            
            $.ajax({
              dataType: "json",
              url: '/tenders/categories',
              async: false,
              success: function(data){
                    var dataLength = data.length;
                    var catNumber = categories.length;
                    for(var z = 0;z<catNumber;z++) {
                        for (var i = 0; i < dataLength; i++) {
                            if(categories[z] == data[i].parent) {
                                categories.push(""+data[i].id);
                            }
                        }   
                    }
                }
            });

            if (categories != null){
                str += (str.length == 0)?"categories="+categories:"&categories="+categories;
            }

            var locations=new Array();
            locations=$('#location_filter').val();
            if (locations!=null){
                str += (str.length==0)?"locations="+locations:"&locations="+locations;
            }
            var statuses=new Array();
            statuses=$('#status_filter').val();
            if (statuses!=null){
                str += (str.length==0)?"statuses="+statuses:"&statuses="+statuses;
            }
            if($("#date_from").val()!=""){
                str += (str.length==0)?"minDate="+$("#date_from").val():"&minDate="+$("#date_from").val();
            }
            if($("#date_to").val()!=""){
                str += (str.length==0)?"maxDate="+$("#date_to").val():"&maxDate="+$("#date_to").val();
            }
            $.ajax({
                url: "/tenders",
                type: "GET",
                data:  str,
                dataType:'json',

                success: function(data) {
                    var html = '';
                    var len = data.length;

                    if(len > 0) {
                        for (var i = 0; i < len; i++) {
                            html += '<tr><td align="center"><a href="/tenderView/' + data[i].id + '">' + data[i].title + '</a></td>' +
                                    '<td align="center">' + data[i].authorName + '</td>' +
                                    '<td align="center">' + data[i].categories + '</td>' +
                                    '<td align="center">' + data[i].locations + '</td>' +
                                    '<td align="center">' + data[i].suitablePrice + '</td>' +
                                    '<td align="center">' + data[i].status + '</td>' +
                                    '<td align="center">' + data[i].proposals + '</td>'+
                                    '<td align="center">' +
                                        '<div class="btn-group">' +
                                            '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action<span class="caret"></span></button>' +
                                            '<ul class="dropdown-menu">' +
                                                '<li><a href="/tenderView/' + data[i].id + '">View</a></li>' +
                                                '<li><a href="#" data-toggle="modal" data-target="#createProposalWindow">Create proposal</a></li>' +
                                                '<li><a href="#" data-toggle="modal" data-target="#close_tender_mod_wind" onclick="writeCloseTenderId(' + data[i].id + ')">Close</a></li>' +
                                            '</ul>' +
                                        '</div>' +
                                    '</td></tr>';
                        }

                        $('#user_message').html('');
                        $('#tender_items').show();
                        $('#tenders').html(html);

                        var pageItemsNumber = $('#pagination_itemsnum').val();
                        if(pageItemsNumber < len) {
                            $('#pagination').show();
                        } else {
                            $('#pagination').hide();
                        }
                    } else {
                        $('#user_message').html('<h4>Your filter parameters did not match any tender</h4>');
                        $('#tender_items').hide();
                        $('#pagination').hide();
                    }
                },
                error:function(){
                    alert("ERROR");
                }
            });
            $("#clear_button").removeAttr("disabled");
        }

        function closeTender() {
            var str = '';
            str += "statusName=" + CLOSE_STATUS_NAME;
            $.ajax({
                url: TENDERS_URL + $('#close_tender_id').val() + "?"  + str,
                type: "PUT",

                success: function(data){
                    closeModalWindow('close_tender_mod_wind');
                    showTenders()},
                error: function(){
                    alert("Something wrong")
                }
            })
        }

        function writeCloseTenderId(id) {
            document.getElementById('close_tender_id').value = id;
        }

        function closeModalWindow(id) {
            $('#' + id).modal('hide');
        }

        function showDeals() {
            window.location.href='/mydeals';
        }
