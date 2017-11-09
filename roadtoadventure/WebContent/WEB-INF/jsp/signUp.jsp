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
                <input class="validate" id="userId" name="userId" type="text" data-length="20" onblur = "isUserIdCorrect($(this).val())"> <label for="userId">帳號</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="userName" name="userName" type="text" data-length="50"> <label for="uerName">名稱</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="password" name="password" type="password" data-length="20"> <label for="password">密碼</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="checkPassword" name="checkPassword" type="password" data-length="20"> <label for="checkPassword">確認密碼</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="email" name="email" type="email" data-length="50" > <label for="email">email</label>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/preloader.js"></script>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/validate/jquery.validate.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/validate/additional-methods.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/validate/lang/messages_zh_TW.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/block.js"></script>
  <script type="text/javascript">
  var check = false;
  $(function(){
	  formValidate()
  })
  function formValidate(){
    $("#signUpForm").validate({
	  rules: {
	    userId: {
	      required: true,
	      minlength: 5,
	      maxlength: 20
	    },
	    userName: {
		  required: true,
		  minlength: 1,
		  maxlength: 50
	    },
	    password: {
	 	  required: true,
	 	  minlength: 8,
	 	  maxlength: 20
	 	},
	 	checkPassword: {
	 	  required: true,
	 	  minlength: 8,
	 	  maxlength: 20,
	 	  equalTo: "#password"
	 	},
	 	email: {
            required: true,
            email:true,
            maxlength:50
        }
	  },
	  errorElement : 'div',
	  errorPlacement: function(error, element) {
	    var placement = $(element).data('error');
	    if (placement) {
	      $(placement).append(error)
	    } else {
	      error.insertAfter(element);
	    }
	  }
	});	
  }
  function signUp (){
	if($("#signUpForm").valid()){
	  if(!check){
		Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; 請檢查帳戶是否驗證成功", 5000)
		return;
	  }
	block("main")
	$.ajax({
	  type: "POST",
	  datatype:"json",
	  data:$("#signUpForm").serialize(),
	  url:  "${pageContext.request.contextPath}/User/SignUp/Create",
	  async: false ,
	  success: function(data){
		var result = jsonFmt(data)
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
  }
  function isUserIdCorrect (userId){
	if(userId!=""){
	  $.ajax({
	    type: "POST",
	    datatype:"json",
	    data:{"userId":userId},
	    url:  "${pageContext.request.contextPath}/User/Read/IsUserIdCorrect",
	    async: false ,
	    success: function(data){
		  var result = jsonFmt(data)
	      if(result.success=="1"){
	    	if(result.isEmpty){
		      check = true;
	          Materialize.toast("<i class = \"material-icons\">check</i>&nbsp;帳號驗證成功。", 3000) 			  
		    }else{
		      check = false;
	          Materialize.toast("<i class = \"material-icons\">clear</i>&nbsp;帳號已申請過，請更換帳號。", 3000) 			  
			}
	      }else{
		    check = false;
		  }
	    }
	  });	 
    }  
  }
  </script>

  </body>
</html>
