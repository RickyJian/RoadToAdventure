<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>好友搜尋-揪愛騎 Road To Adventure</title>
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
  <div id = "main">
    <div class="container">
      <div class="section">
        <br><br>
        <div class = "row">
          <div class="input-field col s10">
            <i class="material-icons prefix">search</i>
            <input id="userName" name ="userName" type="text" >
            <label for="userName">朋友搜尋(請輸入帳戶名稱)</label>
          </div>
          <div class="input-field col s2">
		    <a class="waves-effect waves-light btn" onclick = "read()">查詢</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col s12">
          <ul class="tabs">
            <li class="tab col s4"><a class="active" href="#status1">好友</a></li>
            <li class="tab col s4"><a href="#status0">邀請中</a></li>
            <li class="tab col s4"><a href="#status3">待接收邀請</a></li>
          </ul>
        </div>
        <div id="status1" class="col s12">
          <div class="section">
            <div id ="cardDiv1">
            </div>
	        <br><br><br>
          </div>        
        </div>
        <div id="status0" class="col s12">
          <div class="section">
            <div id ="cardDiv0">
            </div>
	        <br><br><br>
          </div>          
        </div>
        <div id="status3" class="col s12">
          <div class="section">
            <div id ="cardDiv3">
            </div>
	        <br><br><br>
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
  <script type="text/javascript">
  var countArr = [0,0,0];
  $(function(){
    appendCards('${account}');
  })
  function appendCards(jsonObj){
	countArr = [0,0,0];
	$("#cardDiv1").empty();
	$("#cardDiv0").empty();
	$("#cardDiv3").empty();
	if(typeof jsonObj === "object"){
	  var result = jsonObj
    }else{
	  var result = JSON.parse(jsonObj)
    }
	if(result.success =="1"){
      var html = "";
	  for(var i = 0 ; i < result.array.length ; i++){
		var image = result.array[i].picture
		var email = result.array[i].email
		var userId = result.array[i].userId
		var friendId = result.array[i].friendId
		var name = result.array[i].name
		var status = result.array[i].status
	    switch (status){
	    case "0":
	    	countArr[0]++;
	      apendCardHTML(countArr[0] , image , name , friendId , "cardDiv0",html)
		  break;
	    case "1":
	    	countArr[1]++;
	      apendCardHTML(countArr[1] , image , name , friendId , "cardDiv1",html)
		  break;
	    case "3":
	    	countArr[2]++;
	      apendCardHTML(countArr[2] , image , name , friendId , "cardDiv3",html)
		  break;
	    }

      }
	}else{
		Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+result.message, 5000)
	}
  }
  function apendCardHTML(i , image , name , userId , divId , html){
      if(i%3==0){
          html += "<div class =\"row\">"
        }
        html += "<div class=\"col s4\">"
        html += "<div class=\"card small hoverable\">"
		html += "<div class=\"card-image\">"
		html += "<img class =\"activator\" src=\""+image+"\">"
	    html += "</div>"
		html += "<div class=\"card-content\">"
		html += "<span class=\"card-title activator grey-text text-darken-4\">"+name+"</span>"
		html += "</div>"	
		switch (divId){
		case "cardDiv0" :
	      html += "<div class=\"card-action center-align\">"
	      html += "<a class=\"waves-effect waves-light btn\" onclick =\"accept('"+userId+"')\" >接受</a>"
	      html += "</div>"
		  break;
		case "cardDiv1" :
	      html += "<div class=\"card-action center-align\">"
	      html += "<a class=\"waves-effect waves-light btn  red darken-1 \" onclick =\"deleteFriend('"+userId+"')\" >刪除</a>"
	      html += "</div>"
		  break;
		case "cardDiv3" :
	      html += "<div class=\"card-action center-align\">"
	      html += "<a class=\"waves-effect waves-light btn amber darken-1\" >邀請中</a>"
	      html += "</div>"
		  break;
		}	    
	    html += "</div>"
	    html += "</div>"
        if(i%3==2){
          html += "</div>"
        }  
	    $("#"+divId).append(html)
  }
  function read(){
    $.ajax({
	  type: "POST",
	  dataType: 'json',
	  data:{
	    "friendName":$("#userName").val(),
	  },
	  url:"${pageContext.request.contextPath}/User/Setting/Friend/ReadByParameter",
	  async: false ,
	  success: function(data){
	    appendCards(data);
	  }
    })
  }
  function accept(id){
    $.ajax({
	  type: "POST",
	  dataType: 'json',
	  data:{
	    "userId":id,
	  },
	  url:"${pageContext.request.contextPath}/User/Setting/Friend/Update/Accept",
	  async: false ,
	  success: function(data){
	    if(data.success=="1"){
	      Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 接受邀請。", 3000,'',function(){
		    location.reload();
		  })
		}else{
	      Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+data.message , 5000)
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
	  url:"${pageContext.request.contextPath}/User/Setting/Friend/Delete",
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
