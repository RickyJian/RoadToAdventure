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
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <div id = "main">
    <div class="container">
      <div class="section">
        <br><br><br>
        <div class = "row">
          <div class="input-field col s10">
            <i class="material-icons prefix">search</i>
            <input id="userName" name ="userName" type="text" >
            <label for="userName">帳戶搜尋(請輸入帳戶名稱)</label>
          </div>
          <div class="input-field col s2">
		    <a class="waves-effect waves-light btn" onclick = "read()">查詢</a>
          </div>
        </div>
      </div>
      <div class="section">
        <div id ="cardDiv">
        </div>
	    <br><br><br>
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
		var name = result.array[i].name
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
	    html += "<div class=\"card-action center-align\">"
	    html += "<a class=\"waves-effect waves-light btn\" onclick =\"join('"+userId+"')\" >邀請</a>"
	    html += "</div>"
	    html += "</div>"
	    html += "</div>"
        if(i%3==2){
          html += "</div>"
        }
      }
		$("#cardDiv").append(html)
	}else{
		Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+result.message, 5000)
	}
  }
  function read(){
    $.ajax({
	  type: "POST",
	  dataType: 'json',
	  data:{
	    "userName":$("#userName").val(),
	  },
	  url:"${pageContext.request.contextPath}/User/Setting/Account/ReadByParameter",
	  async: false ,
	  success: function(data){
	    appendCards(data);
	  }
    })
  }
  function join(id){
	    $.ajax({
	  	  type: "POST",
	  	  dataType: 'json',
	  	  data:{
	  	    "userId":id,
	  	  },
	  	  url:"${pageContext.request.contextPath}/User/Setting/Friend/Create/Join",
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
