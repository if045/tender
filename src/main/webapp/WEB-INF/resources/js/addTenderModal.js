$(document).ready(function() {
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
    window.location.href = "/tendersHome";
    applyFilters();
    $('#createTenderWindow').modal('hide');
    cleanCreateTenderFields();
}