<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>註冊-揪愛騎 Road To Adventure</title>
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


  <div class="container">
    <div class="section">
      <div class="row">
        <br><br>
        <form class="col s12">
          <div class="row">
            <div class="input-field col s12">
              <input id="userId" name = "userId" type="text" class="validate">
              <label for="userId">帳號</label>
            </div>
          </div>
          <div class = "row">
            <div class="input-field col s12">
              <input id="userName" name = "userName" type="text" class="validate">
              <label for="uerName">名稱</label>
            </div>          
          </div>
          <div class = "row">
            <div class="input-field col s12">
              <input id="password" name = "password" type="password" class="validate">
              <label for="password">密碼</label>
            </div>
          </div>
          <div class = "row">
            <div class="input-field col s12">
              <input id="checkPassword" name = "checkPassword" type="password" class="validate">
              <label for="checkPassword">確認密碼</label>
            </div>
          </div>
          <div class = "row">
            <div class="input-field col s12">
              <input id="email" name = "email" type="email" class="validate">
              <label for="email">email</label>
            </div>
          </div>
          <div class = "row">
		    <div class="file-field input-field">
		      <div class="btn">
		        <span>照片上傳</span>
		        <input type="file">
		      </div>
		      <div class="file-path-wrapper">
		        <input id = "userPicture" class="file-path validate" type="text">
		      </div>
		    </div>          
          </div>
        </form>
      </div>
      <div>
        <div class = "row center">
          <div class = "col s6">
           <a class="waves-effect waves-light btn" onclick = "rewrite()">重新輸入</a>
          </div>
          <div  class = "col s6">
           <a class="waves-effect waves-light btn" onclick = "signUp()">送出</a>
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
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
  <script type="text/javascript">
  function signUp (){
	$.ajax({
	  type: "POST",
	  data:$("#sysCustomerInforForm").serialize(),
	  url:  "<%=request.getContextPath() %>/SysCustomerInfor/changePassword",
	  async: false ,
	    success: function(data){

	});	  
  }
  function rewrite(){
	  $("input[type='text']").val("")
	  $("input[type='email']").val("")
	  $("input[type='password']").val("")
	  $("input[type='file']").val("")
  }
  </script>

  </body>
</html>