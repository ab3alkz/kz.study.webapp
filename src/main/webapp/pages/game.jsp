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
    <script src="${contextPath}/js/game.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js" type="text/javascript"></script>
    <link href="${contextPath}/css/app.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/a_lesson.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
</head>
<body style="background: url('../images/ltsBg.jpg'); cursor: pointer!important;">
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>
<div style="width: 80%; margin: 0 auto">
    <div class="panel panel-default">
        <div class="panel-body">
            <div style="margin: 0 auto; width: 600px">
                <h1 class="page-header game1Tbl"></h1>
                <span class="label label-info" style="font-size: 16px">1. Прочтите вопрос и найдите правильный перевод выделенных слов.</span>
                <br><br><br>
                <span class="label label-success">Перетащите нужный вариант удерживая левую кнопку мышки.</span>
                <br><br><br>
                <span class="label label-info lblCls"></span>
                <div id="maindiv"></div>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/plugin/drag/jquery.min.js" type="text/javascript"></script>
<script src="${contextPath}/plugin/drag/jquery-ui.min.js" type="text/javascript"></script>
<script src="${contextPath}/plugin/drag/bgiframe-2.1.2.js" type="text/javascript"></script>
<script src="${contextPath}/plugin/drag/jquery-ui-i18n.min.js" type="text/javascript"></script>
<script>
    var k = 0;
    function addGme(list) {
        var answer;
        list.forEach(function (e) {
            $('#maindiv').html('<h1>'+e.question+'</h1><br/>' + '&nbsp;&nbsp;&nbsp;&nbsp;<input class="dropdiv" id="outputI" type="text" style="overflow:scroll"/><br/><div id="dragdiv"></div>');
            $('#dragdiv').html('<ul id="allItems" runat="server">'
                + '<li id="node' + 1 + '">' + e.var1 + '</li>'
                + '<li id="node' + 2 + '">' + e.var2 + '</li>'
                + '<li id="node' + 3 + '">' + e.var3 + '</li>'
                + '<li id="node' + 4 + '">' + e.var4 + '</li>'
                + ' </ul>');
            k++;

            answer = e.answer;
        });
        SDDF();
        function SDDF() {
            $("#dragdiv li").draggable({
                appendTo: "body",
                helper: "clone",
                cursor: "move",
                revert: "invalid"
            });

            initDroppable($(".dropdiv"));
            function initDroppable($elements) {
                $elements.droppable({
                    activeClass: "ui-state-default",
                    hoverClass: "ui-drop-hover",
                    accept: ":not(.ui-sortable-helper)",

                    drop: function (event, ui) {
                        var $this = $(this);
                        $this.val(ui.draggable.text());
                        if(ui.draggable.text() == answer) {
                            $("#outputI").css("background-color", "green");
                            $("#outputI").css("color", "white");
                            alert("Правильно");
                        } else {
                            $("#outputI").css("background-color", "red");
                            $("#outputI").css("color", "white");
                            alert("Неправильно");
                        }
                    }
                });
            }
        }
    }
</script>

</body>
</html>