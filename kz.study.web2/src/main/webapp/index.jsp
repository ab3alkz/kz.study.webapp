<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="author" content="" />
    <title>STUDY.KZ</title>
    <link href="/kz.study.webix/plugin/font-awesome-4.6.3/css/font-awesome.css" type="text/css" rel="stylesheet"/>
    <link href="/kz.study.webix/plugin/bootstrap-3.3.4-dist/css/bootstrap.css" type="text/css" rel="stylesheet"/>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <link href="/kz.study.webix/plugin/bootstrap-notify/animate.css" type="text/css" rel="stylesheet"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/kz.study.webix/plugin/jquery/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <%--<script type="text/javascript" src="js/jquery-latest.js"></script>--%>
    <script src="/kz.study.webix/plugin/bootstrap-3.3.4-dist/js/bootstrap.js"></script>

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
        <h1 class="text-uppercase">STUDY.KZ</h1>
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

    <section class="carousel slide" id="myCarousel">
        <ol class="carousel-indicators">
            <li class="active" data-target="#myCarousel" data-slide-to="0"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="images/img1.jpg" />
                <div class="carousel-caption">
                    <h3>WELCOME TO OUR WEBSITE</h3>
                    <p>text slaidera  text slaidera</p>
                </div>
            </div>
            <div class="item">
                <img src="images/img2.jpg" />
                <div class="carousel-caption">
                    <h3>WELCOME TO OUR WEBSITE</h3>
                    <p>text slaidera  text slaidera</p>
                </div>
            </div>
            <div class="item">
                <img src="images/img3.jpg" />
                <div class="carousel-caption">
                    <h3>WELCOME TO OUR WEBSITE</h3>
                    <p>text slaidera  text slaidera</p>
                </div>
            </div>
        </div>
        <a class="carousel-control left"  href="#myCarousel" data-slide="prev"></a>
        <a class="carousel-control right"  href="#myCarousel" data-slide="next"></a>
    </section>
    <section class="col-md-12 flex-box">
        <section class="col-md-8">
            <h1 class="text-uppercase">Welcome to our website</h1>
            <p><a class="fa fa-venus-double"></a>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nulla aliquet tincidunt neque. In in ante. Donec non quam sed lacus tincidunt aliquet. Nullam varius lorem a sapien. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas rhoncus lacinia lectus.
            </p>
            <p>Morbi pede risus, mattis vel, mattis sit amet, semper in, dui. Suspendisse nisl metus, commodo ut, tempor sed, malesuada sagittis, pede. Suspendisse in elit nec diam aliquet fermentum. Aliquam erat volutpat. Integer et enim a lorem lacinia convallis. Vestibulum et ipsum. Pellentesque semper. Praesent et metus. Donec dolor.</p>
        </section>
        <section class="col-md-4 ">
            <h1 class="text-uppercase">Contact Info</h1>
            John Doe, Comapny Inc.<br />
            12345 North Main Street<br />
            Anywhere, USA<br />
            <br />
            Phone: 1.800.555.6789<br />
            Fax: 1.888.555.9876<br />
            Email: info@avada.com<br />
            Web: www.avada.com<br />
        </section>

    </section>

    <section class="portfolio">
        <h1 class="text-uppercase">PORTFOLIO</h1>
        <section class="flex-box">
            <a href="portfolio.jsp#anima4" class="anima">
                <figure class="">
                    <img class="img-thumbnail" src="images/portfolio/c8beffbd-b969.jpg" width="240px" />
                    <figcaption class="text-center">Рыбки</figcaption>
                </figure>
            </a>
            <a  href="portfolio.jsp#anima8" class="anima" >
                <figure >
                    <img class="img-thumbnail" src="images/portfolio/Jiker2011101121419796.jpg" width="240px" />
                    <figcaption class="text-center">Птицы</figcaption>
                </figure>
            </a>

            <a  href="portfolio.jsp#anima3" class="anima" >
                <figure >
                    <img class="img-thumbnail" src="images/portfolio/4564844.png" width="240px" />
                    <figcaption class="text-center">Небо</figcaption>
                </figure>
            </a>
            <a  href="portfolio.jsp#anima5" class="anima" >
                <figure >
                    <img class="img-thumbnail" src="images/portfolio/d0f289a7-1834.jpg" width="240px" />
                    <figcaption class="text-center">Горы</figcaption>
                </figure>
            </a><
        </section>
    </section>




</div>
<footer class="panel-footer  text-center">
    Copyright © 2017 Cайт на bootstrap | Design by <a href="">Abzal</a>
</footer>
</body>
</html>
