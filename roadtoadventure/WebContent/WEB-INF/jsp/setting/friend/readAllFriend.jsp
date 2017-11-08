<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>搜尋車隊-揪愛騎 Road To Adventure</title>
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
  var countArr = [0,0,0];
  $(function(){
    appendCards('${user}');
  })
  function appendCards(jsonObj){
	$("#cardDiv").empty();
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
	    console.log(friendId)
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
		$("#cardDiv").append(html)
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
	    "userName":$("#userName").val(),
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
	          //Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 加入成功，稍待車隊管理員審核。", 5000)
		}else{
	        //Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+data.message , 5000)
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
