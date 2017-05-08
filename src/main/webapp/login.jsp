<%--
    Document   : login
    Author     : a.amanzhol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Аутентификация пользователя</title>
    <script src="/study/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="/study/plugin/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/study/plugin/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="/study/js/login.js" type="text/javascript"></script>
    <link href="/study/css/login.css" rel="stylesheet" type="text/css"/>
    <script src="/study/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="/study/plugin/bootstrap-3.3.4-dist/js/bootstrap.js"  type="text/javascript"></script>
</head>
<body>
<div id="loginMark"/>
<div class="container">
    <div style="padding-top: 100px">

        <div class="center-block">

            <div style="height: 30px"></div>

            <div class="panel panel-default">
                <div class="panel-heading"><i class="fa fa-lock fa-1x"></i>&nbsp;&nbsp; Аутентификация пользователя
                </div>
                <div class="panel-body" id="loginBody">
                    <div style="height: 10px"></div>

                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
                        <input class="form-control" id="username" type="text" placeholder="Имя пользователя" onkeypress="submitOnEnter(event);">
                    </div>

                    <div style="height: 10px"></div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                        <input class="form-control" id="password" type="password" placeholder="Пароль" onkeypress="submitOnEnter(event);">
                    </div>

                    <div class="loadingContainer">
                        <i class="fa fa-refresh fa-spin fa-3x fa-fw"></i>
                        <span class="sr-only">Loading...</span>
                    </div>

                    <div class="errmsg">

                    </div>

                    <div style="text-align: center">
                        <button type="button" id="enter" class="btn btn-default" onclick="login()">
                            Выполнить вход
                        </button>
                    </div>
                    <br>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function submitOnEnter(event) {
        if (event.keyCode == 13) {
            login()
        }
    }
</script>
</body>
</html>
