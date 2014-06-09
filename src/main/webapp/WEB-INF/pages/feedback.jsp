<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="../resources/css/bootstrap.min.css"/>'/>
    <link href="../resources/css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />

    <script type='text/javascript' src='<c:url value="../resources/js/jquery-2.1.1.min.js"/>'></script>
    <script src="../resources/js/star-rating.min.js" type="text/javascript"></script>
</head>
<body>
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
                        <label for="create_comunication_rating" class="col-sm-4 control-label">Communication</label>
                        <div class="col-sm-2">
                            <input type="number" class="rating" id="create_comunication_rating" min="0" max="5" step="1" data-size="xs" data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create_speed_rating" class="col-sm-4 control-label">Speed</label>
                        <div class="col-sm-2">
                            <input type="number" class="rating" id="create_speed_rating" min="0" max="5" step="1" data-size="xs" data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create_logistic_rating" class="col-sm-4 control-label">Logistic</label>
                        <div class="col-sm-2">
                            <input type="number" class="rating" id="create_logistic_rating" min="0" max="5" step="1" data-size="xs" data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <h5>Comment</h5>
                        </div>
                        <div class="col-sm-12">
                            <textarea id="feedback_comment" class="form-control" rows="5" name="comment"></textarea>
                        </div>
                    </div>
                    <input id="feedback_id" type="text" value="" hidden=""/>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">Send</button>
            </div>
        </div>
    </div>
</div>
<!-- show feedback modal window  -->

</body>
</html>