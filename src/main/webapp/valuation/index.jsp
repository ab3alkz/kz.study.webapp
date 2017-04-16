<%@ page import="kz.study.entity.UserDetail" %>
<%@ page import="kz.study.entity.Users" %>
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
        Users user = (Users) request.getSession().getAttribute("user");
        String uName =   "";
        String fio = "";
        if(user!=null) {
            uName=user.getuName();
            if(user.getUserDetail()!=null) {
                UserDetail ud = user.getUserDetail();
            fio = ud.getLastname()+" "+ud.getFirstname();
            }
        }
        %>
        var myuser = '<%=uName%>';
        var myuserFio = '<%=fio%>';
        console.log(myuser)
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
    <script src="${contextPath}/js/common.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js?version=<%= new Date()%>" type="text/javascript"></script>
    <link href="${contextPath}/css/valuation.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div  style="margin-top: 80px;margin-bottom: 70px">
    <div class="container panel panel-default" id="navbar">
        <div class="panel-body">
            <div id="navbarContainer"></div>
        </div>
    </div>
    <div class="container panel panel-default"  style="margin-top: 20px" >
        <div class="panel-body">
            <div>
                <div>
                    <div style="float: left;width: 70%">
                        <div id="mainContainer">
                            <h1 style="padding-bottom:20px ; border-bottom: 1px solid silver">Қазақ тілі білімін бағалау
                                жүйесі</h1>
                            <h3 style="color: #222">Тіл біліміңді тексеру үшін сынақтан өт</h3>
                            <br>
                            <button onclick="getTestTypeList()" class='btn btn-success'
                                    style="padding-left: 50px;padding-right: 50px">Сынақты бастау
                            </button>
                        </div>
                        <div id="defContainer"></div>
                        <div id="fillWordsContainer"></div>

                    </div>
                    <div style="float: left;width: 30%;border-left: 1px solid silver" id="loginForm"></div>

                    <div style="float: left;width: 30%;border-left: 1px solid silver;padding: 10px" id="userInfo"></div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="container panel panel-default" style="margin-top: 10px" id="gameResultContainerWrapper">
        <div class="panel-body">
            <div id="gameResultContainer"></div>
        </div>
    </div>
</div>
</body>
</html>
