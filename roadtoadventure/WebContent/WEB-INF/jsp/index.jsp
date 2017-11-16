<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>首頁-揪愛騎 Road To Adventure</title>
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
        <br><br><br>
        <div class = "row">
          <div class="col s4 ">
            <div class="card">
              <br>
              <div class="card-content black-text center-align">
			    <h3>個人歷程</h3>
              </div>
              <br>
              <div class="card-action center-align">
                <a class="waves-effect waves-light btn" onclick ="redirectPage('personal')">進入</a>
              </div>
            </div>
          </div>
          <div class="col s4 ">
            <div class="card">
              <br>
              <div class="card-content black-text center-align">
			    <h3>團隊管理</h3>
              </div>
              <br>
              <div class="card-action center-align">
                <a class="waves-effect waves-light btn" onclick ="redirectPage('group')">進入</a>
              </div>
            </div>
          </div>
          <div class="col s4 ">
            <div class="card">
              <br>
              <div class="card-content black-text center-align">
			    <h3>設定</h3>
              </div>
              <br>
              <div class="card-action center-align">
                <a class="waves-effect waves-light btn" onclick ="redirectPage('setting')">進入</a>
              </div>
            </div>
          </div>
        </div>
	    <br><br><br>
      </div>
      <div class = "section">
        <div class = "row">
          <br><br><br><br><br><br><br>
        </div>
      </div>     
      <br><br>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bottom.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/menu.js"></script>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script type="text/javascript">
  function redirectPage(value){
	var path = "${pageContext.request.contextPath}"
		
    switch (value){
    case "personal" :
        path += "/Personal"
        break;
    case "group":
        path += "/Group"
        break;
    case "setting" :
        path += "/User/Setting"
        break;
    }
	window.location=path;
  }
  </script>

  </body>
</html>
