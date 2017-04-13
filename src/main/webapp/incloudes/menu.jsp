<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>menu</title>
    <script src="${contextPath}/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootswatch-gh-pages/cerulean/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-3.3.4-dist/css/bootstrap-menu.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/main.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/newutils.js" type="text/javascript"></script>
    <script>
        function logout() {
            $.post("${contextPath}/auth", function () {
                window.location.href = "${contextPath}/login.jsp";
            });
        }
    </script>
    <style>
        a {
            cursor: pointer!important;
        }
    </style>
</head>
<body>
<div style="height: 70px"></div>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href=${contextPath}><span class="glyphicon glyphicon-home"></span></a></li>
                <li><a href="${contextPath}/pages/letter.jsp"><span class="glyphicon glyphicon-sort-by-alphabet">  Әліпби</span></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span>Сабақтар</span><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="reportType(1);" href="">А1 деңгейі</a></li>
                        <li><a onclick="reportType(2);">А2 деңгейі</a></li>
                        <li><a onclick="reportType(3);">B1 деңгейі</a></li>
                        <li><a onclick="reportType(3);">B2 деңгейі</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span>Талдаулар</span><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="analizeType(1);"> Семантикалық талдау </a></li>
                        <li><a onclick="analizeType(2);" href=""> Морфологиялық талдау </a></li>
                        <li><a onclick="analizeType(3);"> Синтаксистық талдау </a></li>
                        <li><a onclick="analizeType(4);"> Лексикалық талдау </a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown light only-icon language-selector ">
                    <a class="dropdown-toggle btn " href="${contextPath}/valuation/index.jsp">
                        <span class="glyphicon glyphicon-leaf"></span>
                        Өз деңгейінді тексеру
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script type="text/javascript">
    $('#profileName').html("<%=request.getRemoteUser()%>");
</script>
<script type="text/javascript">
    function analizeType(id) {
        setLocalStorage("analize", id);
        window.location.href = "${contextPath}/pages/analize.jsp";
    }
</script>
</body>
</html>