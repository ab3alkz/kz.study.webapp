<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="author" content="" />
    <title>сайт на bootstrap</title>
    <link href="plugin/font-awesome-4.6.3/css/font-awesome.css" type="text/css" rel="stylesheet"/>
    <link href="plugin/bootstrap-3.3.4-dist/css/bootstrap.css" type="text/css" rel="stylesheet"/>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <link href="plugin/bootstrap-notify/animate.css" type="text/css" rel="stylesheet"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="plugin/jquery/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <%--<script type="text/javascript" src="js/jquery-latest.js"></script>--%>
    <script src="plugin/bootstrap-3.3.4-dist/js/bootstrap.js"></script>

</head>
<!--[if lt IE 9]>
<script>
    var e = ("article,aside,footer,header,figure,figcaption,nav,section").split(',');
    for (var i = 0; i < e.length; i++) {
        document.createElement(e[i]);
    }
</script>
<![endif]-->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<body>
<div class="container panel">
    <header>
        <h1 class="text-uppercase">My-site</h1>
    </header>
    <nav class="row">
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">открыть навигацию</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>

                    </button>
                    <a class="navbar-brand" href="index.jsp"><img class="pulse animated infinite" src="images/logo.png"/></a>

                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Главная</a></li>
                        <li><a href="portfolio.jsp">Портфолио</a></li>
                        <li><a href="contact.jsp">Контакты</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>

    <section class="portfolio" id="close">
        <h1 class="text-uppercase">PORTFOLIO</h1>
        <section class="flex-box">
            <a  id="anima1"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima1';" src="images/portfolio/1-13041H35422.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima2"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima2';" src="images/portfolio/01543_falls_800x480.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima3"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima3';" src="images/portfolio/4564844.png" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima4"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima4';" src="images/portfolio/c8beffbd-b969.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima5"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima5';" src="images/portfolio/d0f289a7-1834.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima6"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima6';" src="images/portfolio/ffff1727-41bc.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima7"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima7';" src="images/portfolio/images.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>
            <a  id="anima8"class="img-thumbnail anima" >
                <div class="close" onclick="window.location.href='#close';"></div>
                <figure >
                    <img   onclick="window.location.href='#anima8';" src="images/portfolio/Jiker2011101121419796.jpg" width="240px" />
                    <figcaption class="text-center">Название картинки</figcaption>
                </figure>
            </a>

            <div class="col-md-12">
                <ul class="pagination">
                    <li><a href="#">«</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">»</a></li>
                </ul>
            </div>
        </section>

    </section>

</div>
<footer class="panel-footer  text-center">
    Copyright © 2017 Cайт на bootstrap | Design by <a href="">Abzal</a>
</footer>
</body>
</html>