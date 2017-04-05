<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Параметры почты</title>
    <script src="${contextPath}/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${contextPath}/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/skins/terrace.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/sidebar.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/locale.js" type="text/javascript"></script>
    <script src="${contextPath}/js/emailAttrs.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <link href="${contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/app.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/newutils.js" type="text/javascript"></script>
</head>
<body>
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>
<div class="pagetitle">
    <ul class="breadcrumbs">
        <li><a href="${contextPath}/">Главная страница</a></li>
        <li class="current"><a>Параметры почты</a></li>
    </ul>
</div>
<div class="mywrapper">
    <div class="middle">
        <div class="mycontainer">
            <div class="mycontent">
                <div class="mainlayout emailattrs_mainlayout">
                    <div id="mainlayout"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <br>
    </div>
</div>
</body>
</html>