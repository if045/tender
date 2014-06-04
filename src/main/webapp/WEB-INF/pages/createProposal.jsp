<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div class="modal fade" id="createProposalWindow" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="proposalModalLabel">Create proposal</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <label for="tenderUnits" class="control-label"><h3>Tender units:</h3></label>
                    <table class="table table-bordered table-striped" id="tenderUnits">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>Measurement</th>
                                <th>Your price</th>
                            </tr>
                        </thead>
                        <tr>
                            <td align="center">Bricks</td>
                            <td align="center">P</td>
                            <td align="center">Building</td>
                            <td align="center">23</td>
                            <td align="center">kg</td>
                            <td align="center"><input type="text" class="form-control" /></td>
                        </tr>
                        <tr>
                            <td align="center">Calx</td>
                            <td align="center">P</td>
                            <td align="center">Building</td>
                            <td align="center">45</td>
                            <td align="center">l</td>
                            <td align="center"><input type="text" class="form-control" /></td>
                        </tr>
                        <c:forEach var="unit" items="${units}">
                            <tr>
                                <td align="center"><c:out value="${unit.name}"/></td>
                                <td align="center"><c:out value="${unit.type}"/></td>
                                <td align="center"><c:out value="${unit.category}"/></td>
                                <td align="center"><c:out value="${unit.quantity}"/></td>
                                <td align="center"><c:out value="${unit.measurement}"/></td>
                                <td align="center"><input type="text" class="form-control" /></td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div class="form-group">
                        <label for="description" class="col-md-2 control-label">Description</label>
                        <div class="col-md-6">
                            <textarea id="description" class="form-control" rows="3"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="checkbox col-md-3 col-md-offset-2">
                            <label>
                                <input type="checkbox"> Make allowance
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
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
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="allowance" disabled/>
                        </div>
                     </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">Cancel</button>
                <button class="btn btn-primary" type="button">Create</button>
            </div>
        </div>
    </div>
</div>