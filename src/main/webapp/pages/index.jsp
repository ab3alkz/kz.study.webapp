<%@ page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Страница администратора</title>
    <script src="${contextPath}/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="${contextPath}/css/app.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/locale.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/admin.js" type="text/javascript"></script>
    <script src="${contextPath}/js/common.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js?version=<%= new Date()%>" type="text/javascript"></script>


    <%--<script src="http://cdn.webix.com/components/mercury/mercury.js"></script>--%>
</head>
<body>
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>

<div style="text-align: center; margin-top: 12%">
    <h1 class="display-1" style="color: black; font-size: 51px">Қазақ тілін оқытудың интеллектуалды жүйесі</h1>
</div>
</body>
</html>