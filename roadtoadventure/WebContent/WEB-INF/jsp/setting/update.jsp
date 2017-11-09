<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>個資修改-揪愛騎 Road To Adventure</title>
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
          <form class="col s12" id="userForm" name="userForm">
            <div class="row">
              <div class="input-field col s12 center-align">
                <img id="userPicture"  class="circle" src="${pageContext.request.contextPath}/assets/images/p1.png" style="width:120px;height:120px;" >              
              </div>
            </div>
            <div class ="row">
              <div class="file-field input-field col s10 ">
                <div class="btn">
                  <span>照片上傳</span> 
                  <input type="file"  id = "uploadImage" name ="uploadImage">
                </div>
                <div class="file-path-wrapper">
                  <input class="file-path validate" type="text">
                </div>
              </div>             
              <div class="col s2">
                <a class="waves-effect waves-light btn-large" onclick="upload()">確認</a>
              </div>                  
            </div>
            <div class="row">
              <div class="input-field col s12">
                <h5 id = "userIdLabel"></h5>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="userName" name="userName" type="text"> <label for="uerName">名稱</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="email" name="email" type="email"> <label for="email">email</label>
              </div>
            </div>
          </form>
        </div>
        <div>
          <div class="row">
            <div class="col s6 offset-s5">
              <a class="waves-effect waves-light btn" onclick="update()">送出</a>
            </div>
          </div>
        </div>
      </div><br>
      <br>
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
  <script src="${pageContext.request.contextPath}/assets/js/ajaxfileupload.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
  <script type="text/javascript">
  $(function(){
    init();
  })
  function init(){
	var result = JSON.parse('${user}')
	if(result.success=="1"){
      $("#userIdLabel").empty().append(result.userId)
      $("#userName").val(result.name)
      $("#email").val(result.email)
	}
  }
  function upload(){
    imageUpload("uploadImage" , "${pageContext.request.contextPath}/File/UploadImg",function(result){
        console.log(result)
        $("#userPicture").attr("src",result)
	      //create(result)
	})
  }
  function update (){
	//$("#main").block({ message: "<h5>系統處理中請稍後。</h5>"})
	$.ajax({
	  type: "POST",
	  datatype:"json",
	  data:{
		  "userName":$("#userName").val(),
		  "email":$("#email").val(),
		  "userPicture":$("#userPicture").attr("src")
	  },
	  url:  "${pageContext.request.contextPath}/User/Setting/Edit/Update",
	  async: false ,
	  success: function(data){
		var result = JSON.parse(data)
        if(result.success=="1"){
        	 //Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;註冊成功，自動跳轉首頁。", 3000,'',function(){
	         //  window.location="${pageContext.request.contextPath}/Index"
             //})
        }else{
	      //$("#main").unblock()
          //Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; 註冊失敗", 5000)
        }
	  }
	});	  
  }
  </script>

  </body>
</html>
