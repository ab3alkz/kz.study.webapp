<%--
  Created by IntelliJ IDEA.
  User: amanzhol-ak
  Date: 03.09.2016
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Параметры почты</title>
    <script src="/study/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="/study/plugin/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="/study/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/study/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="/study/plugin/webix/codebase/skins/terrace.css" rel="stylesheet" type="text/css"/>
    <script src="/study/plugin/webix/codebase/sidebar.js" type="text/javascript"></script>
    <link href="/study/plugin/webix/codebase/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="/study/js/locale.js" type="text/javascript"></script>
    <script src="/study/js/emailAttrs.js" type="text/javascript"></script>
    <link href="/study/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="/study/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <link href="/study/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/study/css/app.css" rel="stylesheet" type="text/css"/>
    <script src="/study/js/newutils.js" type="text/javascript"></script>
</head>
<body>
<div id="menu" >
    <%@include file='/incloudes/menu.jsp' %>
</div>
<div class="pagetitle">
    <ul class="breadcrumbs">
        <li><a href="/study/">Главная страница</a></li>
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
