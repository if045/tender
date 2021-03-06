<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<div id="createTenderWindow" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">New tender</h4>
      </div>
      <div class="modal-body">
        <div>
            <form class="form-horizontal" role="form" id="create_tender_form_validation">
                <div class="form-group">
                    <label for="create_tender_title" class="col-sm-2 control-label">Title*:</label>
                    <div class="col-sm-6">
                      <input type="text" class="form-control" id="create_tender_title" placeholder="Title of the tender" name="title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_price" class="col-sm-2 control-label">Suitable price:</label>
                    <div class="col-sm-2">
                      <input type="text" class="form-control" id="create_tender_price" placeholder="Suitable price" name="price">
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_enddate" class="col-sm-2 control-label">End date:</label>
                    <div class="col-sm-3">
                        <div class="input-group date pull-left" id="create_tender_enddate" data-date="" data-date-format="dd-mm-yyyy">
                            <input id="create_tender_enddate_input" class="form-control" size="10" type="text" value="">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_location" class="col-sm-2 control-label">Location:</label>
                    <div class="col-sm-6">
                        <select id="create_tender_location" multiple="multiple" class="populate placeholder select2-offscreen location_selector" tabindex="-1" ></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_tender_location" class="col-sm-2 control-label">Description:</label>
                    <div class="col-sm-10">
                        <textarea id="create_tender_description" class="form-control" rows="5" name="description"></textarea>
                    </div>
                </div>

                <div class="">
                    <div><label class="control-label">New unit:</label></div>
                    <div class="row addunit_body">
                        <div class="col-sm-12">
                            <div class="col-sm-2 addunit_body_item">
                                <div class="form-group">
                                    <div>Category*:</div>
                                    <div class="selectpicker">
                                        <select id="create_tender_unit_category" class="form-control selectpicker col-sm-6"></select>
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-4 addunit_body_item field_group">
                                <div class="form-group btn-group" data-toggle="buttons-radio">
                                    <div>Type*:</div>
                                    <div class="btn-group" data-toggle="buttons" id="radio_group">
                                        <label class="btn btn-default" >
                                            <input type="radio" name="options" id="option1" value="">All
                                        </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="options" id="option2" value="P">Product
                                        </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="options" id="option3" value="S">Service
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 addunit_body_item">
                                <div class="form-group">
                                    <div class="field_title">Item*:</div>
                                    <div class="selectpicker col-sm-12">
                                        <div class="pull-left col-sm-6">
                                        	<select id="create_tender_unit_item" class="form-control selectpicker col-sm-6"></select>
                                        </div>
                                        <div class="pull-left col-sm-6">	
                                        	<input type="text" class="form-control" id="create_tender_unit_newitem" placeholder="New item">
                                        </div>	
                                    </div>
                                </div> 
                            </div>
                        </div> 
                        <div class="col-sm-12">
                            <div class="col-sm-2 addunit_body_item">
                                <div class="form-group">
                                    <div>Quantity*:</div>
                                    <div class="selectpicker">
                                        <input type="text" class="form-control" id="create_tender_unit_quantity" placeholder="0" name="quantity">
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-3 addunit_body_item field_group">
                                <div class="form-group">
                                    <div>Measurement*:</div>
                                    <div>
                                        <select id="create_tender_unit_measurement" class="form-control selectpicker col-sm-6"></select>
                                    </div>
                                </div> 
                            </div>
                            <div class="col-sm-6 addunit_body_item">
                                <div class="form-group">
                                    <div class="selectpicker">
                                        <button type="button" class="btn btn-primary pull-right addbutton"
                                                id="add_unit_button" onclick="addUnit();" disabled>Add</button>
                                    </div>
                                </div> 
                            </div>
                        </div>   
                    </div>   
                </div>

                <div id="create_tender_units_table" class="" style="display: none">
                    <div><label class="control-label">Units:</label></div>
                    <div>
                        <table class="table table-bordered table-striped units_table">
                            <tr>
                                <td align="center" class="units_table_header_name">Name</td>
                                <td align="center" class="units_table_header_type">Type</td>
                                <td align="center" class="units_table_header_category">Category</td>
                                <td align="center" class="units_table_header_quantity">Quantity</td>
                                <td align="center" class="units_table_header_measurement">Measurement</td>
                                <td align="center" class="units_table_header_action">Action</td>
                            </tr>
                        </table>    
                    </div>   
                    <div class="create_tender_units">
                        <table class="table table-bordered table-striped">
                            <tbody id="create_tender_units_table_body"></tbody>
                        </table>
                    </div>
                </div>


            </form>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cleanCreateTenderFields();">Cancel</button>
        <button type="button" class="btn btn-primary" id="create_tender_button"
                onclick="createTender();" disabled>Create</button>
      </div>
    </div>
  </div>
</div>
