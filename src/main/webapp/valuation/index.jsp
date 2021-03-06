<%@ page import="kz.study.gson.GsonUserDetail" %>
<%@ page import="kz.study.gson.GsonUsers" %>
<%@ page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Казак тілі білімін бағалау жүйесі</title>
    <script type="text/javascript">
        <%
        GsonUsers user = (GsonUsers) request.getSession().getAttribute("user");
        String uName =   "";
        String fio = "";
        if(user!=null) {
            uName=user.getuName();
            if(user.getUserDetail()!=null) {
                GsonUserDetail ud = user.getUserDetail();
            fio = ud.getLastname()+" "+ud.getFirstname();
            }
        }
        %>
        var myuser = '<%=uName%>';
        var myuserFio = '<%=fio%>';
        var isAdmin = (myuser == "weblogic" )
        console.log(myuser,isAdmin)
    </script>
    <script src="${contextPath}/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/locale.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <script src="${contextPath}/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootswatch-gh-pages/cerulean/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-3.3.4-dist/css/bootstrap-menu.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/valuation/valuation.js" type="text/javascript"></script>
    <script src="${contextPath}/js/valuation/fill-words.js" type="text/javascript"></script>
    <script src="${contextPath}/js/valuation/determine-level.js" type="text/javascript"></script>
    <script src="${contextPath}/js/valuation/testing.js" type="text/javascript"></script>
    <script src="${contextPath}/js/valuation/audi.js" type="text/javascript"></script>
    <script src="${contextPath}/js/valuation/intellectual-test.js" type="text/javascript"></script>
    <script src="${contextPath}/js/common.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js?version=<%= new Date()%>" type="text/javascript"></script>
    <link href="${contextPath}/css/valuation.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/jquery.i18n.properties.min.js" type="text/javascript"></script>


</head>
<body>

<div id="loginMark"/>
<div class="mainwrapper top80px" style="margin-bottom: 70px">

    <div class="container" style="background: transparent">
        <ul class="change-lang">
            <li><a onclick="changeLangSubmit('Kz')" href="#">Қазақ</a></li>
            <li><span>/</span></li>
            <li><a onclick="changeLangSubmit('ln')" href="#">Latyn</a></li>
        </ul>
    </div>
    <div class="container panel panel-default" id="navbar">
        <div class="panel-body">
            <div id="navbarContainer"></div>
        </div>
    </div>
    <div class="container panel panel-default" style="margin-top: 20px">
        <div class="panel-body">
            <div>
                <div>
                    <div style="float: left;width: 70%">
                        <div id="mainContainer">
                            <h1 class="page-title" style="padding-bottom:20px ; border-bottom: 1px solid silver">Қазақ
                                тілі білімін бағалау
                                жүйесі</h1>
                            <h3 style="color: #222" class="til-bilimingdi-tekseru-ueshin">Тіл біліміңді тексеру үшін
                                сынақтан өт</h3>
                            <br>
                            <button onclick="getTestTypeList(1)" class='btn btn-success start-test'
                                    style="padding-left: 50px;padding-right: 50px">Сынақты бастау
                            </button>
                            <button onclick="determineLevel()" class='btn btn-success denggejdi-anyqtau'
                                    style="display: none; padding-left: 50px;padding-right: 50px">Деңгейді анықтау
                            </button>
                            <button onclick="window.location.href='http://localhost:7001/study'" class='btn btn-success denggejdi-anyqtau'
                                    style=" padding-left: 50px;padding-right: 50px">Оқыту материалдары
                            </button>
                        </div>
                        <div id="defContainer"></div>
                        <div id="fillWordsContainer"></div>
                        <div id="testsContainerAdmin"></div>
                        <div id="testsContainerAdminPaging"></div>

                    </div>
                    <div style="float: left;width: 30%;border-left: 1px solid silver" id="loginForm"></div>

                    <div style="float: left;width: 30%;border-left: 1px solid silver;padding: 10px" id="userInfo"></div>
                    <div class="clearfix"></div>
                </div>

                <div id="testsContainer"></div>
            </div>
        </div>
    </div>

    <div class="container panel panel-default" style="margin-top: 10px" id="gameResultContainerWrapper">
        <div class="panel-body">
            <div id="gameResultContainer"></div>
        </div>
    </div>
</div>
<div style="position: fixed;bottom: 0; width: 100%;z-index: 9999999">
    <div id="mainprogress"></div>
</div>
</body>
</html>
<script>
    function changeLangSubmit(e) {
        var language = e;
        if (e == undefined)
            language = 'kz';
        $('#langBlock').html(' ' + language + ' ');
        localStorage.setItem("lang", language);
        window.location.href = "";
    }
</script>
<script type="text/javascript">
    var lang = localStorage.getItem("lang");
    if (!lang || lang == 'ru_RU' || lang == 'ru' || lang == 'Ru' || lang == '') {
        lang = 'kz'
    }
    jQuery.i18n.properties({
        name: 'Messages',
        path: '${contextPath}/bundle/',
        mode: 'both',
        language: lang,
        async: false,
        callback: function () {
            document.querySelector('.page-title').innerHTML = getResourceName('valuation.title');
            document.querySelector('.til-bilimingdi-tekseru-ueshin').innerHTML = getResourceName('valuation.tilbilimingditekseruueshin');
            document.querySelector('.start-test').innerHTML = getResourceName('valuation.starttest');
            document.querySelector('.denggejdi-anyqtau').innerHTML = getResourceName('valuation.denggejdianyqtau');
        }
    });

    function getResourceName(prop) {
        return jQuery.i18n.prop(prop);
    }
</script>
