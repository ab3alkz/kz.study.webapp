<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/ico" sizes="16x16" href="../images/favicon.ico">
    <title>Талдау</title>
    <script src="${contextPath}/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/sidebar.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/analize.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
    <link href="${contextPath}/css/app.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
    <style>
        .mainAnalizeTitle {
            font-family: Cuprum, sans-serif;
            font-size: 40px;
            color: navy;
        }
        .windowLabel {
            font-family: Cuprum, sans-serif;
            font-size: 20px;
            color: navy;
        }
        .wordStyle {
            font-family: Cuprum, sans-serif;
            font-size: 20px;
            color: navy;
        }
        .repeatWC {
            font-family: Cuprum, sans-serif;
            font-size: 16px;
            color: #08802f;
        }
        .noBorder .webix_template {
            padding: 0 !important;
            border: none;
        }
        .noBorder button {
            /*border-radius: 0;*/
            width: 180px;
        }
    </style>
</head>
<body style="background: url('../images/ltsBg.jpg')">
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>
<div  style="width: 80%; margin: 0 auto">
    <div class="panel panel-default">
        <div class="panel-body">
            <div>
                <div id="mainContainer"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>