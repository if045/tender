<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div class="modal fade" id="new_tender_mod_wind" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">New tender successfully created</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <h4>Want to see the contents of the tender?</h4>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" onclick="showNewTenderModalWindow()">Yes</button>
                <button class="btn btn-default" type="button" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>