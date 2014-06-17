var tenderId='';
var newString = '';
var sendToServer = '';

$(document).ready(function() {
    $('#createTenderWindow').on('shown.bs.modal', function () {
        $('.datepicker').addClass('modal_datepicker');

        $("#radio_group").change(function() {
            if ($("#create_tender_unit_newitem").val()!=""){
                $("#create_tender_unit_newitem").val("");
            }
            $("#create_tender_unit_item").val('0');
            var str='';
            var category=$('#create_tender_unit_category').val().substring(0,$('#create_tender_unit_category').val().indexOf(','));
            if (category!=""){
                str += "category="+category;
            }
            if($("#option2").is(":checked")){
                str += (str.length==0)?"type="+$("#option2").val():"&type="+$("#option2").val();
            }
            if($("#option3").is(":checked")){
                str += (str.length==0)?"type="+$("#option3").val():"&type="+$("#option3").val();
            }
            $.ajax({
                url: ITEMS_URL,
                type: "GET",
                data:  str,
                dataType:'json',

                success: function(loc) {
                    var html = '<option value="0">--Select One--</option>';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].type+',' +loc[i].name+',' +loc[i].id + '">'
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
            var category=$('#create_tender_unit_category').val().substring(0,$('#create_tender_unit_category').val().indexOf(','));
            if (category!=""){
                str += "category="+category;
            }
            if($("#option2").is(":checked")){
                str += (str.length==0)?"type="+$("#option2").val():"&type="+$("#option2").val();
            }
            if($("#option3").is(":checked")){
                str += (str.length==0)?"type="+$("#option3").val():"&type="+$("#option3").val();
            }
            $.ajax({
                url: ITEMS_URL,
                type: "GET",
                data:  str,
                dataType:'json',

                success: function(loc) {
                    var html = '<option value="0">--Select One--</option>';
                    var len = loc.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + loc[i].type+',' +loc[i].name+',' +loc[i].id + '">'
                            +loc[i].name + '</option>';
                    }

                    $('#create_tender_unit_item').html(html);
                },
                error:function(){
                    alert("ERROR");
                }
            });
        });

        $("#create_tender_unit_newitem").change(function() {
            var option1=$("#option1").is(":checked");
            var option2=$("#option2").is(":checked");
            var option3=$("#option3").is(":checked");
            if (option1){
                $("#option1").attr("checked",false);
                $("#option3").attr("checked",false);
                $("#option2").attr("checked",true);
            }else if (!option2&!option3){
                $("#option1").attr("checked",false);
                $("#option3").attr("checked",false);
                $("#option2").attr("checked",true);
            }
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

        $.getJSON(LOCATIONS_URL, function(loc){
            var html = ' ';
            var len = loc.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + loc[i].id + '">'
                    +loc[i].name + '</option>';
            }

            $('#create_tender_location').html(html);
        });

        $.getJSON(CATEGORIES_URL, function(loc){
            var html = '<option value="0">--Select One--</option>';
            var len = loc.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + loc[i].id+',' +loc[i].name + '">'
                    +loc[i].name + '</option>';
            }

            $('#create_tender_unit_category').html(html);
        });

        $.getJSON(ITEMS_URL, function(loc){
            var html = '<option value="0">--Select One--</option>';
            var len = loc.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + loc[i].type+',' +loc[i].name+',' +loc[i].id+ '">'
                    +loc[i].name + '</option>';
            }

            $('#create_tender_unit_item').html(html);
        });

        $.getJSON(MEASUREMENTS_URL, function(loc){
            var html = '<option value="0">--Select One--</option>';
            var len = loc.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + loc[i].id+',' +loc[i].name + '">'
                    + loc[i].name + '</option>';
            }

            $('#create_tender_unit_measurement').html(html);
        });
    });
});

function addUnit() {
    var categoryName = $('#create_tender_unit_category').val().substring($('#create_tender_unit_category').val().indexOf(',')+1);
    var categoryId=$('#create_tender_unit_category').val().substring(0,$('#create_tender_unit_category').val().indexOf(','));
    var measurementName = $('#create_tender_unit_measurement').val().substring($('#create_tender_unit_measurement').val().indexOf(',')+1);
    var measurementId=$('#create_tender_unit_measurement').val().substring(0,$('#create_tender_unit_measurement').val().indexOf(','));
    var itemName='';
    var itemType='';
    var itemId = '';
    if ($('#create_tender_unit_item').val()!=0){
        itemName=$('#create_tender_unit_item').val().substring($('#create_tender_unit_item').val().indexOf(',')+1,$('#create_tender_unit_item').val().lastIndexOf(','));
        itemType=$('#create_tender_unit_item').val().substring(0,$('#create_tender_unit_item').val().indexOf(','));
        itemId=$('#create_tender_unit_item').val().substring($('#create_tender_unit_item').val().lastIndexOf(',')+1);
    }else{
        itemName=$('#create_tender_unit_newitem').val();
        if (itemName!=""&!$("#option3").is(":checked")&!$("#option2").is(":checked")&!$("#option1").is(":checked")){
            itemType= "P";
        }else {
            itemType = $("input:radio[name ='options']:checked").val();
        }
    }
    var type='';
    type = (itemType=="P")?"Product":"Service";
    if (newString==""){
        if (($('#create_tender_unit_item').val()!=0)&($('#create_tender_unit_newitem').val()=="")){
            newString+='{"units" : [{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryName+'\"'+
                ',"item":'+'\"'+itemName+'\"'+',"measurment":'+'\"'+measurementName+'\"'+
                ',"itemType":'+'\"'+type+'\"'+'}]}';
        }
        if (($('#create_tender_unit_newitem').val()!="")&($('#create_tender_unit_item').val()==0)){
            newString+='{"units" : [{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryName+'\"'+
                ',"item":'+'\"'+itemName+'\"'+',"measurment":'+'\"'+measurementName+'\"'+
                ',"itemType":'+'\"'+type+'\"'+'}]}';
        }
    }else{
        if (($('#create_tender_unit_item').val()!=0)&($('#create_tender_unit_newitem').val()=="")){
            var strf = newString.substring(0,newString.indexOf(']'));
            var strm = ',{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryName+'\"'+
                ',"item":'+'\"'+itemName+'\"'+',"measurment":'+'\"'+measurementName+'\"'+
                ',"itemType":'+'\"'+type+'\"'+'}';
            var stre = newString.substring(newString.indexOf(']'));
            newString = strf+strm+stre;
        }
        if (($('#create_tender_unit_newitem').val()!="")&($('#create_tender_unit_item').val()==0)){
            var strf = newString.substring(0,newString.indexOf(']'));
            var strm = ',{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryName+'\"'+
                ',"item":'+'\"'+itemName+'\"'+',"measurment":'+'\"'+measurementName+'\"'+
                ',"itemType":'+'\"'+type+'\"'+'}';
            var stre = newString.substring(newString.indexOf(']'));
            newString = strf+strm+stre;
        }
    }
    var item='';
    if ($('#create_tender_unit_item').val()!=0) {
        item=itemId;
    }else{
        item=itemName;
    }
    if (sendToServer==""){
        if (($('#create_tender_unit_item').val()!=0)&($('#create_tender_unit_newitem').val()=="")){
            sendToServer+='{"units" : [{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryId+'\"'+
                ',"item":'+'\"'+item+'\"'+',"measurment":'+'\"'+measurementId+'\"'+
                ',"itemType":'+'\"'+itemType+'\"'+'}]}';
        }
        if (($('#create_tender_unit_newitem').val()!="")&($('#create_tender_unit_item').val()==0)){
            sendToServer+='{"units" : [{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryId+'\"'+
                ',"item":'+'\"'+item+'\"'+',"measurment":'+'\"'+measurementId+'\"'+
                ',"itemType":'+'\"'+itemType+'\"'+'}]}';
        }
    }else{
        if (($('#create_tender_unit_item').val()!=0)&($('#create_tender_unit_newitem').val()=="")){
            var strf = sendToServer.substring(0,sendToServer.indexOf(']'));
            var strm = ',{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryId+'\"'+
                ',"item":'+'\"'+item+'\"'+',"measurment":'+'\"'+measurementId+'\"'+
                ',"itemType":'+'\"'+itemType+'\"'+'}';
            var stre = sendToServer.substring(sendToServer.indexOf(']'));
            sendToServer = strf+strm+stre;
        }
        if (($('#create_tender_unit_newitem').val()!="")&($('#create_tender_unit_item').val()==0)){
            var strf = sendToServer.substring(0,sendToServer.indexOf(']'));
            var strm = ',{"quantity":'+'\"'+$('#create_tender_unit_quantity').val()+'\"'+',"category":'+'\"'+categoryId+'\"'+
                ',"item":'+'\"'+item+'\"'+',"measurment":'+'\"'+measurementId+'\"'+
                ',"itemType":'+'\"'+itemType+'\"'+'}';
            var stre = sendToServer.substring(sendToServer.indexOf(']'));
            sendToServer = strf+strm+stre;
        }
    }
    var newJson=$.parseJSON(newString);
    var html = '';
    var len = newJson.units.length;
    $('#create_tender_units_table').show();
    for (var i = 0; i < len; i++) {
        html += '<tr><td align="center" class="units_table_body_name">'+newJson.units[i].item +'</td>' +
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
            html += '<tr><td align="center" class="units_table_body_name">'+newJson.units[i].item +'</td>' +
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

    if (sendToServer!=""&sendToServer.length>130) {
        var strf = sendToServer.substring(0,sendToServer.indexOf('{"quantity'));
        var beforesplit = sendToServer.replace(sendToServer.substring(0,sendToServer.indexOf('"quantity')),'').substring(0,sendToServer.replace(sendToServer.substring(0,sendToServer.indexOf('"quantity')),'').indexOf(']'));
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
        sendToServer = strf +strb+ strl;
    }else
    if (sendToServer.length<130) {
        sendToServer = '';
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
    if (quantity!=""&category!=0&(item!=0|newItem!="")&measurement!=0){
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
    if (!unitTable&title!=""&price!=""&date!=""){
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
    var strf = sendToServer.substring(0, sendToServer.lastIndexOf('}'));
    var createTenderLocations=new Array();
    createTenderLocations=$('#create_tender_location').val();
    var locationStr='';
    var description ='';
    var strm = '';
    if (createTenderLocations!=null){
        locationStr += ', "locationsIds":'+'\"'+createTenderLocations+'\"'
    }
    if ($('#create_tender_description').val()!=""){
        description+=',"description":'+'\"'+$('#create_tender_description').val()+'\"';
    }
    strm += locationStr+', "title":'+'\"'+$('#create_tender_title').val()+'\"'
        +',"suitablePrice":'+'\"'+$('#create_tender_price').val()+'\"'+',"endDate":'+'\"'+$('#create_tender_enddate_input').val()+'\"'
        +description+'}';
    sendToServer=strf+strm;
    var newJson=$.parseJSON(sendToServer);
    $.ajax({
        url: TENDERS_URL,
        type: "POST",
        data:  JSON.stringify(newJson),
        dataType:'json',
        contentType: 'application/json',

        success: function(data) {
            showResultAfterTenderAdded();
            $('#createTenderWindow').modal('hide');
            $('#new_tender_mod_wind').modal('show');
            cleanCreateTenderFields();
            tenderId=data.id;
        },
        error:function(){
            showResultAfterTenderAdded();
            $('#createTenderWindow').modal('hide');
            cleanCreateTenderFields();
        }
    });
}

function showResultAfterTenderAdded(){
    $.ajax({
        url: TENDERS_URL,
        type: "GET",
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
}

function showNewTenderModalWindow(){
    window.location.href = TENDER_VIEW_URL+"/"+tenderId;
}