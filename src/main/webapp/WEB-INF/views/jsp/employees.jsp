<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Waiters page</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Restaurant of health fresh food. Affordable to everyone.">
  <meta name="author" content="Liliya Yalovchenko">
  <link rel="shortcut icon" href="/resources/apple.ico">
  <!-- Bootstrap core CSS -->
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="/resources/css/carousel.css" rel="stylesheet">

</head>
<body style="background-color:  #9ccc65 ">

  <!--Navigation bar-->
  <div class="navbar-wrapper" style="position: relative;">
  <div class="container">
    <div class="navbar navbar-inverse navbar-static-top" style="background-color: inherit; border: none;" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="color: #ffffff;" href="#">Fresh Point</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/" style="background-color: #e33539; color: #ffffff">Home</a></li>
            <li><a href="/about" style="color: #ffffff">About</a></li>
            <li><a href="/menu" style="color: #ffffff">Menu</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" style="color: #ffffff" data-toggle="dropdown">More <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="/employees">Our team</a></li>
                <li><a href="/schema">Restaurant schema</a></li>
                <li><a href="/contact">It is easy to find us</a></li>

              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div> <!--Nav bar finished-->



  <div class="container marketing">
    <div class="row">
      <div class="col-lg-8" style="float: none; margin-left: auto; margin-right: auto;">
        <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">Our team</h1>
        <img src="/resources/images/apple(1).png" width="100" style="margin: 0 auto; display: block;" alt="FreshPoint team">
      </div>
    </div>

    <!-- Three columns of text below the carousel -->
    <div class="row" style="margin-top: 40px;">
      <c:forEach items="${employees}" var="employee">
        <div class="col-lg-4">
          <img class="img-circle" src="/resources/images/boy.png" width="128" alt="Waiter photo">
          <h2 style="color: #ffffff;font-size: 30px;">${employee.firstName}</h2>
          <p style="color: #ffffff;">${employee.position}</p>

        </div><!-- /.col-lg-4 -->
      </c:forEach>
    </div><!-- /.row -->


    <!-- FOOTER -->
    <footer>
      <p class="pull-right"><a href="#">Back to top</a></p>
      <p>&copy; 2016 Fresh Point &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>

  </div><!-- /.container -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script src="/resources/js/bootstrap.min.js"></script>
  <script src="/resources/js/docs.min.js"></script>
</body>
</html>
