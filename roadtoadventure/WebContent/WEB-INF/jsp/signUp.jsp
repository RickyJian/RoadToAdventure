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
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <div id="main">
    <div class="container">
      <div class="section">
        <div class="row">
          <br>
          <br>
          <form class="col s12" id="signUpForm" name="signUpForm">
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="userId" name="userId" type="text"> <label for="userId">帳號</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="userName" name="userName" type="text"> <label for="uerName">名稱</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="password" name="password" type="password"> <label for="password">密碼</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="checkPassword" name="checkPassword" type="password"> <label for="checkPassword">確認密碼</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="email" name="email" type="email"> <label for="email">email</label>
              </div>
            </div>
            <div class="row">
              <div class="file-field input-field">
                <div class="btn">
                  <span>照片上傳</span> <input type="file">
                </div>
                <div class="file-path-wrapper">
                  <input class="file-path validate" id="userPicture" type="text">
                </div>
              </div>
            </div>
          </form>
        </div>
        <div>
          <div class="row center">
            <div class="col s6">
              <a class="waves-effect waves-light btn" onclick="rewrite()">重新輸入</a>
            </div>
            <div class="col s6">
              <a class="waves-effect waves-light btn" onclick="signUp()">送出</a>
            </div>
          </div>
        </div>
      </div>
      <div class = "section">
        <div class = "row">
          <br><br><br><br><br><br><br>
        </div>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bottom.js"></script>

  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/menu.js"></script>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/block.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/notify.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
  <script type="text/javascript">
  function signUp (){
	$("#main").block({ message: "<h5>系統處理中請稍後。</h5>"})
	$.ajax({
	  type: "POST",
	  datatype:"json",
	  data:$("#signUpForm").serialize(),
	  url:  "${pageContext.request.contextPath}/User/SignUp/Create",
	  async: false ,
	  success: function(data){
		var result = JSON.parse(data)
        if(result.success=="1"){
        	 Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;註冊成功，自動跳轉首頁。", 3000,'',function(){
	           window.location="${pageContext.request.contextPath}/"
             })
        }else{
	      $("#main").unblock()
          Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; 註冊失敗", 5000)
        }
	  }
	});	  
  }
  </script>

  </body>
</html>
