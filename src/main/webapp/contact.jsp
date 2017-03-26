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

    <section>
        <h1 class="text-uppercase">We’d Love to Hear From You, Get In Touch With Us!</h1>
        <figure class="form img-thumbnail"><figcaption>At vero eos et accusamus et iusto odios un dignissimos ducimus qui blan ditiis prasixer esentium voluptatum un deleniti atqueste sites excep turiitate non providentsimils. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, consequunturser magni dolores eos qui ratione voluptatem sequi nesciunt. Lorem ipsum dolor sit amet isse potenti. Vesquam ante aliquet lacusemper elit. Cras neque nulla, convallis non commodo et, euismod nonsese.</figcaption>
            <form  name='form'  class="col-md-6 img-thumbnail"  action="" >
                <input class="img-thumbnail" title="Нельзя писать цивры или другие символы, кроме букв!"  required="required"name="name" id="name" type="text" class="width170px" pattern="[a-zA-ZА-Яа-я]{1,30}" placeholder="Имя"/>
                <input  class="img-thumbnail" required="required" name="email" id="email" type="email"class="width170px" placeholder="Email"/>
                <input  class="img-thumbnail" required="required" name="age" id="age"  title="Не правильный номер телефона!"  pattern="[0-9]{11,12}" type="text"class="width170px" placeholder="Номер телефон" />
                <textarea name="comment"  class="img-thumbnail"  placeholder="Comment..."></textarea>
                <input class="img-thumbnail" type="submit" class="submit" value="отправить" />
            </form>
        </figure>
    </section>
    <section class="map">
        <script type="text/javascript" charset="utf-8" async src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3A433999ef53c9260816d49da4bef19d05d96b28fa69594365e9688fb2c9665dc6&amp;width=&amp;height=320&amp;lang=ru_RU&amp;scroll=true"></script>
    </section>
</div>
<footer class="panel-footer  text-center">
    Copyright © 2015 Cайт на bootstrap | Design by <a href="">Abzal</a>
</footer>
</body>
</html>