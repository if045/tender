<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- show feedback modal window -->
<div class="modal fade bs-example-modal-sm" id="feedback_mod_wind" tabindex="-1" role="dialog" hidden="">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Feedback</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <h4 for="create_communication_rating1" class="col-sm-4 control-label">Communication</h4>
                        <div class="col-sm-2">
                            <input type="number" class="rating" id="create_communication_rating" min="0" max="5" step="1" data-size="xs" data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <h4 for="create_speed_rating1" class="col-sm-4 control-label">Speed</h4>
                        <div class="col-sm-2">
                            <input type="number" class="rating" id="create_speed_rating" min="0" max="5" step="1" data-size="xs" data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <h4 for="create_logistic_rating1" class="col-sm-4 control-label">Logistic</h4>
                        <div class="col-sm-2">
                            <input type="number" class="rating" id="create_logistic_rating" min="0" max="5" step="1" data-size="xs" data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <h4>Comment</h4>
                        </div>
                        <div class="col-sm-12">
                            <textarea id="feedback_comment" class="form-control" rows="5" name="comment" maxlength='100' placeholder="Please enter your comment here (less than 100 chars)"></textarea>
                        </div>
                    </div>
                    <input id="feedback_id" type="text" value="" hidden=""/>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal" onclick="cleanFeedback();">Cancel</button>
                <button class="btn btn-primary" type="button" onclick="createFeedback();">Send</button>
            </div>
        </div>
    </div>
</div>
<!-- show feedback modal window  -->

</body>
</html>