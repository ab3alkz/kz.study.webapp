<%@ page import="kz.study.gson.GsonUsers" %>
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
    <script src="${contextPath}/plugin/jquery.i18n.properties.min.js" type="text/javascript"></script>
    <script>
        function logout() {
            $.post("${contextPath}/auth", function () {
                window.location.href = "${contextPath}/valuation/index.jsp";
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
                <li><a href="${contextPath}/pages/letter.jsp"><span
                        class="glyphicon glyphicon-sort-by-alphabet">&nbsp;<span class="menuAplh"></span></span></a>
                </li>
                <li><a href="${contextPath}/pages/video.jsp"><span class="glyphicon glyphicon-facetime-video"></span>&nbsp;<span
                        class="videLess"></span></a></li>
                <li><a href="${contextPath}/pages/translate.jsp"><span class="translateMenu"></span></a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span class="lessonsMenu"></span><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="setLocalStorage('part', 'A1')" href="${contextPath}/pages/A2.jsp"
                               class="classA1"></a></li>
                        <li><a onclick="setLocalStorage('part', 'A2')" href="${contextPath}/pages/A2.jsp"
                               class="classA2"></a></li>
                        <li><a onclick="setLocalStorage('part', 'B1')" href="${contextPath}/pages/A2.jsp"
                               class="classB1"></a></li>
                        <li><a onclick="setLocalStorage('part', 'B2')" href="${contextPath}/pages/A2.jsp"
                               class="classB2"></a></li>
                        <li><a onclick="setLocalStorage('part', 'C1')" href="${contextPath}/pages/A2.jsp"
                               class="classC1"></a></li>
                    </ul>
                </li>
                <li><a href=${contextPath}/pages/analize.jsp><span class="analize"></span></a></li>

                <% GsonUsers user = (GsonUsers) request.getSession().getAttribute("user");
                    if (user.getRole().equals("admin_role")) { %>
                <li><a href="${contextPath}/pages/adminstrate.jsp"><span>Администрирование</span></a></li>
                <% } %>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown light only-icon language-selector ">
                    <a class="dropdown-toggle btn " data-toggle="dropdown" href="#">
                        <i class="fa fa-globe hidden-xs"></i>&nbsp;<span id='langBlock' class="menuLang"></span>&nbsp;<b
                            class="caret"></b>
                    </a>
                    <ul class="dropdown-menu pull-right" style="max-height: 407px;">
                        <li dir="ltr" onclick="changeLangSubmit('Ln')"><a href="#">Latynsha</a></li>
                        <li dir="ltr" onclick="changeLangSubmit('Kz')"><a href="#">Қазақша</a></li>
                        <li dir="ltr" onclick="changeLangSubmit('Ru')"><a href="#">Русский</a></li>
                    </ul>
                </li>
                <li class="dropdown light only-icon language-selector ">
                    <a class="dropdown-toggle btn " href="${contextPath}/valuation/index.jsp">
                        <span class="glyphicon glyphicon-leaf">&nbsp;</span>
                        <span class="proverMenu"></span>
                    </a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"
                       style="padding-left: 45px;padding-right: 45px; width: auto;">
                        <span class="glyphicon glyphicon-user">&nbsp;</span>
                        <span id="profileName" style="text-transform: capitalize"></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">

                        <li class="nav-divider"></li>
                        <li><a href="javascript:void(0)" onclick="logout()"><span
                                class="glyphicon glyphicon-log-out"></span>&nbsp;<span class="menuExit"></span></a></li>
                    </ul>
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
        window.location.href = "/study/pages/analize.jsp";
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
            document.querySelector('.menuAplh').innerHTML = getResourceName('menu.alphabet');
            document.querySelector('.videLess').innerHTML = getResourceName('menu.vide.less');
            document.querySelector('.translateMenu').innerHTML = getResourceName('menu.translate');
            document.querySelector('.lessonsMenu').innerHTML = getResourceName('menu.lessons');
            document.querySelector('.proverMenu').innerHTML = getResourceName('menu.prover');
            document.querySelector('.analize').innerHTML = getResourceName('menu.analize');
            document.querySelector('.classA1').innerHTML = getResourceName('a.one.urover');
            document.querySelector('.classA2').innerHTML = getResourceName('a.two.urover');
            document.querySelector('.classB1').innerHTML = getResourceName('b.one.urover');
            document.querySelector('.classB2').innerHTML = getResourceName('b.two.urover');
            document.querySelector('.classC1').innerHTML = getResourceName('c.one.urover');
            document.querySelector('.menuExit').innerHTML = getResourceName('menu.exit.admin');
        }
    });

    function getResourceName(prop) {
        return jQuery.i18n.prop(prop);
    }
</script>
</body>
</html>