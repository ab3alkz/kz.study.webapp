<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/ico" sizes="16x16" href="../images/favicon.ico">
    <title>A1</title>
    <script src="${contextPath}/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/sidebar.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/admin.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js" type="text/javascript"></script>
    <script src="${contextPath}/js/locale.js" type="text/javascript"></script>
    <link href="${contextPath}/css/app.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/a_lesson.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
    <script src="${contextPath}/plugin/croper/jquery.cropit.js"></script>
    <style>
        .cropit-preview {
            width: 200px;
            height: 200px;
            margin: 0 auto;
        }
        .cropBtn{
            margin: 10px auto 0 auto;
            width: 250px;
        }
        .cropit-image-input{
            float: left;
            margin-right: 15px;
            width: 113px;
        }
    </style>
</head>
<body style="background: url('../images/ltsBg.jpg')">
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>
<div style="width: 80%; margin: 0 auto">
    <div class="panel-body">
        <div>
            <div id="mainContainer"></div>
        </div>
    </div>
</div>
<div style="display: none;">
    <div id="uploadCropContainer">
        <div class="image-editor">
            <div class="cropit-preview"></div>
            <div class="image-size-label">
                Размер фото
            </div>
            <input type="range" class="cropit-image-zoom-input">
            <div class="cropBtn">
                <input type="file" class="cropit-image-input">
                <button data-action="rotate-left" title="Перевернуть (L)" class="rotate-ccw">
                    <span class="fa fa-rotate-left"></span>
                </button>
                <button data-action="rotate-right" title="Перевернуть (R)" class="rotate-cw">
                    <span class="fa fa-rotate-right"></span>
                </button>
                <button style="margin-left: 30px" data-action="rotate-right" title="Сохранить" id="cropBtn" class="export">
                    <span class="fa fa-check"></span>
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function() {
        $('.image-editor').cropit({
            imageState: {}
        });

        $('.rotate-cw').click(function() {
            $('.image-editor').cropit('rotateCW');
        });
        $('.rotate-ccw').click(function() {
            $('.image-editor').cropit('rotateCCW');
        });

        $('.export').click(function() {
            var imageData = $('.image-editor').cropit('export');
            isLocate(imageData);
        });
    });
</script>
<script>
    function isLocate(e) {
        $$('cropBtn').disable();
        get_ajax('/xdoc/ws/profile/uploadUserAva', 'GET', {data: e}, function (gson) {
            $$('uploadUserPhoto').hide();
            $$('avaRows').removeView('ava');
            window.onscroll = null;
            getProfile();
            $$('cropBtn').enable();
            notifyMessage('info', 'Редактирование! ', 'Профиль пользователя загружен');
        });
    }
</script>
</body>
</html>