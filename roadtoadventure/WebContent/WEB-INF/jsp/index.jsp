<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>揪愛騎 Road To Adventure 首頁</title>
<script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <style>
    #map {
      height: 400px;
      width: 100%;
    }
  </style>
</head>
<body>
  <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="#">註冊</a></li>
        <li><a href="#">登入</a></li>
        <li><a id="menu" data-activates="slide-out" ><i class="material-icons">menu</i> </a></li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="#">註冊</a></li>
        <li><a href="#">登入</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br>
      <h1 class="header center orange-text">位置共享</h1>
      <div id = "map" >
        
      </div>
      <br><br>

    </div>
  </div>


  <div class="container">
    <div class="section">

      <!--   Icon Section   -->
      <div class="row">
       <h1 class="header center orange-text">排行榜</h1>
        <div class="col s12 m4">
        <h5>單人</h5>
	    <div class="card horizontal">
	      <div class="card-image">
	        <img src="https://lorempixel.com/100/190/nature/6">
	      </div>
	      <div class="card-stacked">
	        <div class="card-content">
	          <p>Jobs Steve</p>
	        </div>
	      </div>
	    </div>
        </div>

        <div class="col s12 m4">
        <h5>團體</h5>
	    <div class="card horizontal">
	      <div class="card-image">
	        <img src="https://lorempixel.com/100/190/nature/6">
	      </div>
	      <div class="card-stacked">
	        <div class="card-content">
	          <p>Jobs Steve</p>
	        </div>
	      </div>
	    </div>
        </div>

        <div class="col s12 m4">
        <h5>挑戰</h5>
	    <div class="card horizontal">
	      <div class="card-image">
	        <img src="https://lorempixel.com/100/190/nature/6">
	      </div>
	      <div class="card-stacked">
	        <div class="card-content">
	          <p>Jobs Steve</p>
	        </div>
	      </div>
	    </div>
        </div>
      </div>

    </div>
    <br><br>
  </div>

  <footer class="page-footer blue lighten-1">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">Company Bio</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Settings</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Connect</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
      </div>
    </div>
  </footer>

  <!-- leftSlideNav -->
  <ul id="slide-out" class="side-nav">
    <li><div class="user-view">
      <div class="background">
        <img src="assets/images/office.jpg">
      </div>
      <a href="#!user"><img class="circle" src="assets/images/yuna.jpg"></a>
      <a href="#!name"><span class="white-text name">John Doe</span></a>
      <a href="#!email"><span class="white-text email">jdandturk@gmail.com</span></a>
    </div></li>
    <li><a>個人歷程系統</a></li>
    <li><a href="#!" class="subheader">路線規劃</a></li>
    <li><a class="subheader">我的歷程</a></li>
    <li><a >車隊管理系統</a></li>
    <li><a href="#!" class="subheader">路線規劃</a></li>
    <li><a class="subheader">團隊歷程</a></li>
  </ul>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="assets/js/materialize.js"></script>
  <script src="assets/js/init.js"></script>
  <script src="assets/js/map.js"></script>
  <script type="text/javascript">
  $(function(){
	  $("#menu").sideNav();
     })
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYVBpGeFB5L5UqunJlJ19rxxBooiVNNoE&callback=initMap">
  </script>
  </body>
</html>
