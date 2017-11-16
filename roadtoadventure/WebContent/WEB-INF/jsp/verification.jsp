<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>系統逾時-揪愛騎 Road To Adventure</title>
  <sec:authentication var="user" property="principal"/>
  <script type="text/javascript">
    var user = '${user}' 
  </script>
  <sec:authorize access="authenticated">
    <script type="text/javascript">
      var userId = '${user.userId}' 
      var userName = '${user.userName}'    
      var email = '${user.email}'    
      var userPicture = '${user.userPicture}'           
    </script>
  </sec:authorize>
  <script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

</head>
<body>
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <div id="main">
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br><br><br>
      <h2 class="header center orange-text">請驗證帳號</h2>
      <div class="section">
        <div class="row">
          <form id = "verificationForm">
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="verificationCode" name="verificationCode" type="text" data-length="32" > <label for="verificationCode">帳號</label>
              </div>
            </div>
          </form>
        </div>     
        <div class="row center-align">
          <a class="waves-effect waves-light btn" onclick="verify()">送出</a>
        </div>         
      </div>
      <br><br><br><br>
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
  $(function(){
    formValidate()
  })
  
  function formValidate(){
    $("#verificationForm").validate({
	  rules: {
		verificationCode: {
	      required: true,
	      minlength: 32,
	      maxlength: 32
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
  function verify(){
    if($("#verificationForm").valid()){
	  block("main")
	  $.ajax({
	    type: "POST",
		datatype:"json",
		data:{	
		  "verificationCode": $("#verificationCode").val(),
	    },
	    url:"${pageContext.request.contextPath}/User/Setting/Update/Verification",
	    async: false ,
		success: function(data){
		  var result = jsonFmt(data)
		  if(result.success =="1"){
	        Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;認證成功，請重新登入。", 3000,'',function(){
		          window.location="${pageContext.request.contextPath}/j_spring_security_logout.jsp"
	        })
		  }else{
		        Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 認證失敗，請再次嘗試。", 5000)
		  }
		  $("#main").unblock();		  
	    }
	   });	  
     }
  }
  </script>
  </body>
</html>
