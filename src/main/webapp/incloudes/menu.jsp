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
    <script src="${contextPath}/js/navbar.js" type="text/javascript"></script>
    <script>
        function logout() {
            $.post("${contextPath}/auth", function () {
                window.location.href = "${contextPath}/login.jsp";
            });
        }
    </script>
    <style>
        a {
            cursor: pointer !important;
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
                <li><a href="${contextPath}/pages/letter.jsp"><span class="glyphicon glyphicon-sort-by-alphabet">  Әліпби</span></a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span>Сабақтар</span><span class="caret"></span></a>

                    <ul class="dropdown-menu" role="menu" id="mapLists">
                        <li class="dropdown-submenu">
                            <a tabindex="-1" href="#">А1 деңгейі</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="window.location.href='../pages/A1.jsp'" href="#">Сұрауға бола ма?</a>
                                </li>
                                <li><a onclick="window.location.href='sslists.jsp?list=GBDFL_REP_DISAPPEAR'" href="#"
                                       class="menuListsItemSSMissingPerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_RISK_DATE'" href="#"
                                       class="menuListsItemSSRiskDatePerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_CLOSE_DATE'" href="#"
                                       class="menuListsItemSSCloseDatePerson"></a></li>
                                <li style="<%= request.isUserInRole("EMAKET_DEVELOPER")?"":"display:none;" %>"><a onclick="window.location.href='sslists.jsp?list=REP_GBDFL_17628'" href="#"
                                                                                                                  class="menuListsItemGBDFL17628Person"></a></li>
                            </ul>
                            <a tabindex="-1" href="#">А2 деңгейі</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="window.location.href='../pages/A1.jsp'" href="#">Сұрауға бола ма?</a>
                                </li>
                                <li><a onclick="window.location.href='sslists.jsp?list=GBDFL_REP_DISAPPEAR'" href="#"
                                       class="menuListsItemSSMissingPerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_RISK_DATE'" href="#"
                                       class="menuListsItemSSRiskDatePerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_CLOSE_DATE'" href="#"
                                       class="menuListsItemSSCloseDatePerson"></a></li>
                                <li style="<%= request.isUserInRole("EMAKET_DEVELOPER")?"":"display:none;" %>"><a onclick="window.location.href='sslists.jsp?list=REP_GBDFL_17628'" href="#"
                                                                                                                  class="menuListsItemGBDFL17628Person"></a></li>
                            </ul>
                            <a tabindex="-1" href="#">B1 деңгейі</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="window.location.href='../pages/A1.jsp'" href="#">Сұрауға бола ма?</a>
                                </li>
                                <li><a onclick="window.location.href='sslists.jsp?list=GBDFL_REP_DISAPPEAR'" href="#"
                                       class="menuListsItemSSMissingPerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_RISK_DATE'" href="#"
                                       class="menuListsItemSSRiskDatePerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_CLOSE_DATE'" href="#"
                                       class="menuListsItemSSCloseDatePerson"></a></li>
                                <li style="<%= request.isUserInRole("EMAKET_DEVELOPER")?"":"display:none;" %>"><a onclick="window.location.href='sslists.jsp?list=REP_GBDFL_17628'" href="#"
                                                                                                                  class="menuListsItemGBDFL17628Person"></a></li>
                            </ul>
                            <a tabindex="-1" href="#">B2 деңгейі</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="window.location.href='../pages/A1.jsp'" href="#">Сұрауға бола ма?</a>
                                </li>
                                <li><a onclick="window.location.href='sslists.jsp?list=GBDFL_REP_DISAPPEAR'" href="#"
                                       class="menuListsItemSSMissingPerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_RISK_DATE'" href="#"
                                       class="menuListsItemSSRiskDatePerson"></a></li>
                                <li><a onclick="window.location.href='sslists.jsp?list=REP_0703_CLOSE_DATE'" href="#"
                                       class="menuListsItemSSCloseDatePerson"></a></li>
                                <li style="<%= request.isUserInRole("EMAKET_DEVELOPER")?"":"display:none;" %>"><a onclick="window.location.href='sslists.jsp?list=REP_GBDFL_17628'" href="#"
                                                                                                                  class="menuListsItemGBDFL17628Person"></a></li>
                            </ul>
                        </li>

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
                    <a class="dropdown-toggle btn " data-toggle="dropdown" href="#">
                        <i class="fa fa-globe hidden-xs"></i>&nbsp;<span id='langBlock' class="menuLang"></span>&nbsp;<b
                            class="caret"></b>
                    </a>
                    <ul class="dropdown-menu pull-right" style="max-height: 407px;">
                        <li dir="ltr" onclick="changeLangSubmit('Kz')"><a href="#">Қазақша</a></li>
                        <li dir="ltr" onclick="changeLangSubmit('Ru')"><a href="#">Русский</a></li>
                    </ul>
                </li>
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