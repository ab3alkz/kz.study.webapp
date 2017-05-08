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
    <link rel="icon" type="image/ico" sizes="16x16" href="../images/favicon.ico">
    <title>A1</title>
    <script src="${contextPath}/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/codebase/sidebar.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/webix/codebase/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/a1.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
    <script src="${contextPath}/js/modules/a1_1.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js" type="text/javascript"></script>
    <link href="${contextPath}/css/app.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/a_lesson.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
</head>
<body style="background: url('../images/ltsBg.jpg')">
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>
<div style="width: 80%; margin: 0 auto">
    <div class="panel panel-default">
        <div>
            <button type="button" class="btn btn-success btnWidth classA1_1" onclick="addViewLocal(1)"></button>
            <button type="button" class="btn btn-success btnWidth classA1_2" onclick="addViewLocal(2)"></button>
            <button type="button" class="btn btn-success btnWidth classA1_3" onclick="addViewLocal(3)"></button>
            <button type="button" class="btn btn-success btnWidth classA1_4" onclick="addViewLocal(4)"></button>
            <button type="button" class="btn btn-success btnWidth classA1_5" onclick="addViewLocal(5)"></button>
            <br><br>
            <button type="button" class="btn btn-primary btnWidth classA1_6" onclick="addViewLocal(6)"></button>
            <button type="button" class="btn btn-primary btnWidth classA1_7" onclick="addViewLocal(7)"></button>
            <button type="button" class="btn btn-primary btnWidth classA1_8" onclick="addViewLocal(8)"></button>
            <button type="button" class="btn btn-primary btnWidth classA1_9" onclick="addViewLocal(9)"></button>
            <button type="button" class="btn btn-primary btnWidth classA1_10" onclick="addViewLocal(10)"></button>
        </div>
        <br><br><br>
        <div id="btnBlog" style="visibility: hidden;">
            <button type="button" class="btn btn-info btnWidth videoBtn" onclick="addVideoViewById()"></button>
            <button type="button" class="btn btn-warning btnWidth grammarBtn" onclick="addGrammarViewById()"></button>
            <button type="button" class="btn btnWidth classA1_10 audioBtn" onclick="addAudioViewById()"></button>
        </div>
        <div class="panel-body">
            <div>
                <div id="mainContainer"></div>
            </div>
        </div>
    </div>
</div>
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
            document.querySelector('.classA1_1').innerHTML = getResourceName('aone.one.urover');
            document.querySelector('.classA1_2').innerHTML = getResourceName('aone.two.urover');
            document.querySelector('.classA1_3').innerHTML = getResourceName('aone.th.urover');
            document.querySelector('.classA1_4').innerHTML = getResourceName('aone.four.urover');
            document.querySelector('.classA1_5').innerHTML = getResourceName('aone.five.urover');
            document.querySelector('.classA1_6').innerHTML = getResourceName('aone.six.urover');
            document.querySelector('.classA1_7').innerHTML = getResourceName('aone.seven.urover');
            document.querySelector('.classA1_8').innerHTML = getResourceName('aone.eight.urover');
            document.querySelector('.classA1_9').innerHTML = getResourceName('aone.nine.urover');
            document.querySelector('.classA1_10').innerHTML = getResourceName('aone.ten.urover');
            document.querySelector('.videoBtn').innerHTML = getResourceName('aone.vide.btn');
            document.querySelector('.grammarBtn').innerHTML = getResourceName('aone.grammar.btn');
            document.querySelector('.audioBtn').innerHTML = getResourceName('aone.audi.btn');
        }
    });

    function getResourceName(prop) {
        return jQuery.i18n.prop(prop);
    }
</script>
<script>
    function addViewLocal(id) {
        setLocalStorage("lesson_1", id);
        $('#btnBlog').css("visibility", "visible")
    }
</script>

<script>
    $(function () {
        var textfield = $("input[name=user]");
        $('button[type="submit"]').click(function (e) {
            e.preventDefault();

            if (textfield.val() != "") {
                //$("body").scrollTo("#output");
                $("#output").addClass("alert alert-success animated fadeInUp").html("Welcome back " + "<span style='text-transform:uppercase'>" + textfield.val() + "</span>");
                $("#output").removeClass(' alert-danger');
                $("input").css({
                    "height": "0",
                    "padding": "0",
                    "margin": "0",
                    "opacity": "0"
                });
                //change button text
                $('button[type="submit"]').html("continue")
                    .removeClass("btn-info")
                    .addClass("btn-default").click(function () {
                    $("input").css({
                        "height": "auto",
                        "padding": "10px",
                        "opacity": "1"
                    }).val("");
                });
                $('#modalWindow').css('display', 'none');
            } else {
                //remove success mesage replaced with error message
                $("#output").removeClass(' alert alert-success');
                $("#output").addClass("alert alert-danger animated fadeInUp").html("sorry enter a username ");
            }
            //console.log(textfield.val());

        });
    });

</script>
</body>
</html>