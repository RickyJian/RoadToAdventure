<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>車隊歷程系統-揪愛騎 Road To Adventure</title>
  <script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

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


  <div id = "main">
  <div class="container">
    <div class="section">
      <br><br><br>
      <!-- 

       -->
      <div class = "row">
        <div class="col s4 offset-s2 ">
          <div class="card">
            <br>
            <div class="card-content black-text center-align">
			  <h3>歷程新增</h3>
            </div>
            <br>
            <div class="card-action center-align">
              <a class="waves-effect waves-light btn" onclick ="redirectPage('new')">進入</a>
            </div>
          </div>
        </div>
        <div class="col s4 ">
          <div class="card">
            <br>
            <div class="card-content black-text center-align">
			  <h3>歷程管理</h3>
            </div>
            <br>
            <div class="card-action center-align">
              <a class="waves-effect waves-light btn" onclick ="redirectPage('manage')">進入</a>
            </div>
          </div>
        </div>
      </div>
	  <br><br><br>
    </div>
    <br><br>
  </div>
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
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script type="text/javascript">
  function redirectPage(value){
	var path = "${pageContext.request.contextPath}/Group/${groupId}/Journey"
		
    switch (value){
    case "new" :
        path += "/New"
        break;
    case "manage":
        path += "/ReadAll"
        break;
    }
	window.location=path;
  }
  </script>

  </body>
</html>
