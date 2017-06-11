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
    <script src="${contextPath}/js/a2.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
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
        <div id="btnBlog" style="visibility: hidden; margin: 0 auto">
            <button type="button" class="btn btn-info btnWidth videoBtn" onclick="addVideoViewByID()"></button>
            <button type="button" class="btn btn-warning btnWidth grammarBtn" onclick="addGrammarViewByID()"></button>
            <button type="button" class="btn btnWidth classA1_10 audioBtn" onclick="addAudioViewByID()"></button>
            <button type="button" class="btn btn-success btnWidth classA1_10 game1Btn"
                    onclick="addGame1ViewByID()"></button>
            <button type="button" class="btn btn-primary btnWidth classA1_10 game2Btn"
                    onclick="addAudioViewByID()"></button>
        </div>
        <div class="panel-body">
            <div>
                <%--<h1 class="page-header game1Tbl"></h1>--%>
                <div id="mainContainer">
                    <%--<span class="label label-info" style="font-size: 16px">1. Прочтите краткий текст и найдите правильный перевод выделенных слов.</span>--%>
                    <%--<br><br><br>--%>
                    <%--<span class="label label-success">Перетащите нужный вариант удерживая левую кнопку мышки.</span>--%>
                    <%--<br><br><br>--%>
                    <%--<span class="label label-info lblCls"></span>--%>
                </div>
                <%--<div id="maindiv"></div>--%>
            </div>
        </div>
    </div>
</div>
<script>
    function addViewLocal(id) {
        setLocalStorage("btnParam", id);
        $('#btnBlog').css("visibility", "visible")
    }
</script>
<script type="text/javascript">
    var l = localStorage.getItem("lang");
    if (!l || l == 'ru_RU' || l == '') {
        l = 'Ru'
    }
    jQuery.i18n.properties({
        name: 'Messages',
        path: '${contextPath}/bundle/',
        mode: 'both',
        language: l,
        async: false,
        callback: function () {
            document.querySelector('.videoBtn').innerHTML = getResourceName('aone.vide.btn');
            document.querySelector('.grammarBtn').innerHTML = getResourceName('aone.grammar.btn');
            document.querySelector('.audioBtn').innerHTML = getResourceName('aone.audi.btn');
            document.querySelector('.game1Btn').innerHTML = getResourceName('btn.game.adw');
            document.querySelector('.game2Btn').innerHTML = getResourceName('btn.game.adw2');
//            document.querySelector('.game1Tbl').innerHTML = getResourceName('btn.game.adw');
        }
    });
    function getResourceName(prop) {
        return jQuery.i18n.prop(prop);
    }
</script>
<script src="${contextPath}/plugin/drag/jquery.min.js" type="text/javascript"></script>
<script src="${contextPath}/plugin/drag/jquery-ui.min.js" type="text/javascript"></script>
<script src="${contextPath}/plugin/drag/bgiframe-2.1.2.js" type="text/javascript"></script>
<script src="${contextPath}/plugin/drag/jquery-ui-i18n.min.js" type="text/javascript"></script>
<%--<script>--%>
<%--var k = 0;--%>
<%--function addGame1Data(list) {--%>
<%--list.forEach(function (e) {--%>
<%--$('#maindiv').html(e.question + '<input class="dropdiv" type="text" style="overflow:scroll"/><div id="dragdiv"></div>');--%>
<%--$('#dragdiv').html('<ul id="allItems" runat="server">'--%>
<%--+ '<li id="node' + 1 + '">' + e.var1 + '</li>'--%>
<%--+ '<li id="node' + 2 + '">' + e.var2 + '</li>'--%>
<%--+ '<li id="node' + 3 + '">' + e.var3 + '</li>'--%>
<%--+ '<li id="node' + 4 + '">' + e.var4 + '</li>'--%>
<%--+ ' </ul>');--%>
<%--k++;--%>
<%--});--%>
<%--SDDF();--%>
<%--function SDDF() {--%>
<%--$("#dragdiv li").draggable({--%>
<%--appendTo: "body",--%>
<%--helper: "clone",--%>
<%--cursor: "move",--%>
<%--revert: "invalid"--%>
<%--});--%>

<%--initDroppable($(".dropdiv"));--%>
<%--function initDroppable($elements) {--%>
<%--$elements.droppable({--%>
<%--activeClass: "ui-state-default",--%>
<%--hoverClass: "ui-drop-hover",--%>
<%--accept: ":not(.ui-sortable-helper)",--%>

<%--drop: function (event, ui) {--%>
<%--var $this = $(this);--%>
<%--$this.val(ui.draggable.text());--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--</script>--%>
</body>
</html>