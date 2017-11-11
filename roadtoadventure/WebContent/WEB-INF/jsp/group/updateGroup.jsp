<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>車隊新增-揪愛騎 Road To Adventure</title>
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
          <br><br>
        <div class="row">
          <div class="col s12">
            <ul class="tabs">
              <li class="tab col s4"><a href="#info">車隊資訊</a></li>
              <li class="tab col s4"><a class="active" href="#member">成員</a></li>
              <li class="tab col s4"><a href="#ready">待審核成員</a></li>
            </ul>
          </div>
          <div id="info" class="col s12">
          <div class = "row">
            <form class="col s12" id="groupForm" name="groupForm">
              <div class ="row col s3 offset-s10">
  			    <div class="switch">
    			  <label>
    			      停用
  				    <input id= "status"  type="checkbox" >
      			    <span class="lever"></span>
     			     啟用
    			  </label>
                </div>                
              </div>          
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
                <div>
                  <input id = "pic" type = "hidden" >
                </div>
              </div>
            </form>  
            </div>
            <div class="row center">
              <div class="col s6">
                <a class="waves-effect waves-light btn" onclick="rewrite()">重新輸入</a>
              </div>
              <div class="col s6">
                <a class="waves-effect waves-light btn" onclick="send()">送出</a>
              </div>
            </div>       
          </div>
          <div id="member" class="col s12">
            <div class="section">
              <div id ="cardDiv1">
              </div>
	          <br><br><br>
            </div>        
          </div>
          <div id="ready" class="col s12">
            <div class="section">
              <div id ="cardDiv0">
              </div>
	          <br><br><br>
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
  var groupId = "";
  var countArr = [0,0];
  $(function(){
	  formValidate()
	  init()
	  
  })
  function searchMember(){
    $.ajax({
	  type: "POST",
	  dataType: 'json',
	  url:"${pageContext.request.contextPath}/Group/"+groupId+"/User/ReadAll",
	  async: false ,
	  success: function(data){
		console.log(data)
		var result = jsonFmt(data)
	    if(result.success =="1"){
	    	appendCards(result)
	  	}else{
	  	    // Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 新增失敗。", 5000)
	  	}
	 }
   })
  }
  function init(){
    var result = jsonFmt('${group}') 
    if(result.success == "1"){
      $("#groupName").val(result.groupName)
      $("#groupDescription").val(result.groupDescription)
      $("#pic").val(result.groupPicture)
      groupId = result.groupId
      if(result.status=="1"){
    	  $('#status').prop('checked',true)
      }else{
    	  $('#status').prop('checked',false)
      }
    }else{

    }
    searchMember(result)
  }
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
        if(result.success =="1"){
          update(result.image)
        }else{
          update($("#pic").val())
        }
      })
	}
  }
  function update (value){
	var status = 0
	if($('#status').prop('checked')){
	  status = 1;
	}
    $.ajax({
	  type: "POST",
	  dataType: 'json',
	  data:{
		  "groupName":$("#groupName").val(),
		  "groupDescription":$("#groupDescription").val(),
		  "groupPicture":value,
		  "status" : status
	  },
	  url:"${pageContext.request.contextPath}/Group/"+groupId+"/Update",
      async: false ,
	  success: function(data){
		if(data.success =="1"){
       	  Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;更新成功，自動跳轉管理頁面。", 3000,'',function(){
	           window.location="${pageContext.request.contextPath}/Group/Read"
          })
	    }else{
	      Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 新增失敗。", 5000)
	    }
	    $("#main").unblock();
	  }
    })
  }
  function appendCards(result){
	if(result.success =="1"){
	  var html = "";
	  for(var i = 0 ; i < result.userArray.length ; i++){
	    var image = result.userArray[i].userPicture
		var id = result.userArray[i].userId
		var name = result.userArray[i].userName
		var status = result.userArray[i].status
		switch (status){
		  case "0":
		    countArr[0]++;
		    apendCardHTML(countArr[0] , image , name  , status , id , "cardDiv0",html)
	      break;
		  case "1":
		    countArr[1]++;
		    apendCardHTML(countArr[1] , image , name  , status , id , "cardDiv1",html)
		  break;
		}
	  }
	}else{
	  Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+result.message, 5000)
	}
  }
  function apendCardHTML(i , image , name  , status , userId , divId , html){
      if(i%3==0){
          html += "<div class =\"row\">"
        }
        html += "<div class=\"col s4\">"
		html += "<div class=\"card small hoverable\">"
		html += "<div class=\"card-image \">"
	    html += "<img class =\"activator\" src=\""+image+"\">"
	    html += "</div>"
	    html += "<div class=\"card-content\">"
	    html += "<span class=\"card-title activator grey-text text-darken-4\">"+name
	    html += "</div>"
	    html += "<div class=\"card-action center-align\">"
	    if(status=="1"){
          html += "<a class=\"waves-effect waves-light btn  red darken-1 \" onclick =\"deleteFriend('"+userId+"')\" >刪除</a>"
	    }else{
	      html += "<a class=\"waves-effect waves-light btn\" onclick =\"accept('"+userId+"')\" >接受</a>"
 		}
	    html += "</div>"
	    html += "</div>"
	    html += "</div>"
        if(i%3==2){
          html += "</div>"
        }
	    $("#"+divId).append(html)
      }  
  function accept(id){
	    $.ajax({
		  type: "POST",
		  dataType: 'json',
		  data:{
		    "userId":id,
		  },
		  url:"${pageContext.request.contextPath}/Group/"+groupId+"/Update/Friend/Accept",
		  async: false ,
		  success: function(data){
		    if(data.success=="1"){
		      Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 審核成功。", 3000,'',function(){
		        location.reload(); 
	          })
			}else{
		      Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; 審核失敗。", 5000)
			}
		  }
		})
	  }
	  function deleteFriend(id){
	    $.ajax({
		  type: "POST",
		  dataType: 'json',
		  data:{
		    "friendId":id,
		  },
		  url:"${pageContext.request.contextPath}/Group/"+groupId+"/Update/Friend/Delete",
		  async: false ,
		  success: function(data){
		    if(data.success=="1"){
		          //Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 加入成功，稍待車隊管理員審核。", 5000)
			}else{
		        //Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+data.message , 5000)
			}
		  }
		})
	  }  
  </script>

  </body>
</html>
