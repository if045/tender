<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- show conflict modal window -->
<div class="modal fade bs-example-modal-sm" id="moderator_conflict_mod_wind" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Сonflict actions</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input id="moderator_conflict_id" type="text" value="" hidden=""/>

                    <div class="form-group">
                        <div class="col-sm-10">
                            <h5>Description</h5>
                        </div>
                        <div class="jumbotron">
                            <div class="container">
                                Demo text
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <h4>Note</h4>
                        </div>
                        <div class="col-sm-12">
                            <textarea id="conflict_comment" class="form-control" rows="5" name="comment" maxlength='100' placeholder="(less than 100 chars)"></textarea>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <div class="col-sm-6">
                        <div class="control-group">
                            <select id="category" class="form-control pull-right items_number_dropdown">
                                <option value="5" selected>open</option>
                                <option value="10">in progres</option>
                                <option value="15">resolved</option>
                            </select>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="button" data-dismiss="modal" onclick="cleanConflict();">Cancel</button>
                    <button class="btn btn-primary" type="button" onclick="createConflict();">Send</button>
                </div>
                <div id="moderator_conflict_action" class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th align="center" id="m_author">
                                    <span>Author</span>
                                </th>
                                <th align="center" id="m_date">
                                    <span>Date</span>
                                </th>
                                <th align="center" id="m_status">
                                    <span>Status</span>
                                </th>
                                <th align="center" id="m_moderator">
                                    <span>Moderator</span>
                                </th>
                                <th align="center" id="m_note">
                                    <span>Note</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>