<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>車隊新增-揪愛騎 Road To Adventure</title>
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
  <br><br>
  <div id="main">
    <div class="container">
      <div class="section">
        <div class="row">
          <br>
          <br>
          <form class="col s12" id="groupForm" name="groupForm">
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="groupName" name="groupName" type="text" data-length="50"> <label for="groupName">車隊名稱</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <textarea id="groupDescription" name = "groupDescription" class="materialize-textarea" data-length="100"></textarea>
                <label for="groupDescription">車隊簡介</label>
              </div>
            </div>            
            <div class="row">
              <div class="file-field input-field">
                <div class="btn">
                  <span>車隊封面上傳</span> <input type="file" id = "uploadImage" name ="uploadImage">
                </div>
                <div class="file-path-wrapper">
                  <input class="file-path validate" type="text">
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
              <a class="waves-effect waves-light btn" onclick="send()">送出</a>
            </div>
          </div>
        </div>           
      </div>
      <div class = "section">
        <div class = "row">
          <br><br><br><br><br><br><br>
        </div>
      </div>        
      <br>
      <br>
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
  <script src="${pageContext.request.contextPath}/assets/js/ajaxfileupload.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/block.js"></script>
  <script type="text/javascript">
  $(function(){
	  formValidate()
  })
  function formValidate(){
    $("#groupForm").validate({
	  rules: {
	    groupName: {
	      required: true,
	      minlength: 5,
	      maxlength: 20
	    },
	    groupDescription: {
		  maxlength: 100
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
  function send(){
	if($("#groupForm").valid()){
      block("main")
      imageUpload("uploadImage" , "${pageContext.request.contextPath}/File/UploadImg",function(result){
        create(result)
      })
	}
  }
  function create (value){
    $.ajax({
	  type: "POST",
	  dataType: 'json',
	  data:{
		  "groupName":$("#groupName").val(),
		  "groupDescription":$("#groupDescription").val(),
		  "groupPicture":value
	  },
	  url:"${pageContext.request.contextPath}/Group/Create",
      async: false ,
	  success: function(data){
		if(data.success =="1"){
       	  Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;新增成功，自動跳轉管理頁面。", 3000,'',function(){
	           window.location="${pageContext.request.contextPath}/Group/Read"
          })
	    }else{
	      Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 新增失敗。", 5000)
	    }
	    $("#main").unblock();
	  }
    })
  }
  </script>

  </body>
</html>
