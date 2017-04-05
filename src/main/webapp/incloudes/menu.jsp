<%@ page import="weblogic.security.spi.WLSGroup" %>
<%@ page import="weblogic.security.spi.WLSUser" %>
<%@ page import="javax.security.auth.Subject" %>
<%@ page import="java.security.Principal" %>
<%@ page import="java.util.Date" %>
<%--
    Document   : menu
    Author     : a.amanzhol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script type="text/javascript">
    myuser = "<%=request.getRemoteUser()%>";
    roles = [];
    <%

        Subject subject = weblogic.security.Security.getCurrentSubject();
        for(Principal p: subject.getPrincipals()) {
              if(p instanceof WLSGroup) {
                  %>
    var role = '<%=p.getName() %>';
    roles.push(role);
    <%
} else if (p instanceof WLSUser) {
    %>
    myuser = '<%=p.getName() %>';
    <%
}
}
%>
    console.log("roles", roles)


</script>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>menu</title>
    <script src="/study/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="/study/plugin/bootswatch-gh-pages/cerulean/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/study/plugin/bootstrap-3.3.4-dist/css/bootstrap-menu.css" rel="stylesheet" type="text/css"/>
    <script src="/study/js/password.js?version=<%= new Date()%>" type="text/javascript"></script>
    <link href="/study/css/main.css" rel="stylesheet" type="text/css"/>
    <script>
        function logout() {
            $.post("/study/auth", function () {
                window.location.href = "/study/login.jsp";
            });
        }
    </script>
</head>
<body>
<div style="height: 70px"></div>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="/study"><span class="glyphicon glyphicon-home"></span> &nbsp; study</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <% if (request.isUserInRole("admin_role")) { %>
            <ul class="nav navbar-nav">

                <%--<li class="dropdown">--%>
                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">--%>
                <%--<span>Администрирование</span><span class="caret"></span></a>--%>
                <%--<ul class="dropdown-menu" role="menu" id="mapZhournals">--%>
                <%--<li><a href="/study/pages/admin/users.jsp">Пользователи</a></li>--%>
                <%--<li><a href="/study/pages/admin/emailattrs.jsp">Параметры почты</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>


            </ul>

            <%}%>

            <ul class="nav navbar-nav navbar-right" id="profilePopup">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"
                       style="padding-left: 45px; width: 190px;">
                        <span id="profileName" style="text-transform: capitalize"></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:resetPassword()">Сменить пароль</a></li>
                        <li><a href="profiles.jsp">Мой профиль</a></li>
                        <%--<li><a href="roles">Мои задачи</a></li>--%>
                        <li class="nav-divider"></li>
                        <li><a href="javascript:void(0)" onclick="logout()"><span
                                class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script type="text/javascript">
    $('#profileName').html("<%=request.getRemoteUser()%>");
</script>
</body>
</html>
