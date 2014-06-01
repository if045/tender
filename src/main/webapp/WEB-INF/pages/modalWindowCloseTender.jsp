<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="modal fade" id="closeTenderModWind" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                 <h4 class="modal-title">Attention</h4>
             </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <h4>Are you sure you want to close this tender?</h4>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">No</button>
                <button class="btn btn-primary" type="button">Yes</button>
            </div>
        </div>
    </div>
</div>