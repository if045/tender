<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- show conflict modal window -->
<div class="modal fade bs-example-modal-sm" id="conflict_mod_wind" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Сonflict</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input id="conflict_id" type="text" value="" hidden=""/>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <h4>Comment</h4>
                        </div>
                        <div class="col-sm-12">
                            <textarea id="conflict_comment" class="form-control" rows="5" name="comment"></textarea>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal" onclick="cleanConflict();">Cancel</button>
                    <button class="btn btn-primary" type="button" onclick="createConflict();">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- show conflict modal window -->
