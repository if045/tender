<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div class="modal fade" id="createProposalWindow" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="proposalModalLabel">Create proposal</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="create_proposal_form">
                    <label for="tenderUnits" class="control-label"><h3>Tender units:</h3></label>
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>Your price</th>
                            </tr>
                        </thead>
                        <tbody id="tenderUnits"></tbody>

                    </table>

                    <div class="form-group">
                        <label for="description" class="col-md-2 control-label">Description</label>
                        <div class="col-md-6">
                            <textarea id="description" class="form-control" rows="3" name="description"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="checkbox col-md-3 col-md-offset-2">
                            <label>
                                <input type="checkbox" id="make_allowance" onclick="enableAllowance()"> Make allowance
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div id="radio_buttons">
                            <div class="radio col-md-1 col-md-offset-2">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optRadSum" value="sum" disabled>
                                    Sum
                                </label>
                            </div>
                            <div class="radio col-md-1">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optRadPercent" value="percent" checked disabled>
                                    Percent
                                </label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="allowance" name="allowance" disabled/>
                        </div>
                     </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">Cancel</button>
                <button class="btn btn-primary" type="button" disabled id="create_proposal_button">Create</button>
            </div>
        </div>
    </div>
</div>