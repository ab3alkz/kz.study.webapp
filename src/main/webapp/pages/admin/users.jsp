<%@ page import="java.util.Date" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Пользователи</title>
    <link rel="icon" type="image/ico" sizes="16x16" href="../../images/favicon.ico">


    <script src="/study/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="/study/css/app.css" rel="stylesheet" type="text/css"/>
    <script src="/study/js/locale.js" type="text/javascript"></script>
    <link href="/study/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="/study/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="/study/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="/study/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <script src="/study/js/users.js" type="text/javascript"></script>
    <script src="/study/js/newutils.js?version=<%= new Date()%>" type="text/javascript"></script>
</head>
<body  >
<div id="menu" >
    <%@include file='/incloudes/menu.jsp' %>
</div>


<div style="padding: 0 15px 10px 15px">
    <h3 style="margin: 0">Пользователи</h3>
    <br>
    <div class="panel panel-default">
        <div class="panel-body">
            <div id="mainContainer"></div>
        </div>
    </div>
    <div id="userDetailTablePaging"></div>
</div>


</body>
</html>
