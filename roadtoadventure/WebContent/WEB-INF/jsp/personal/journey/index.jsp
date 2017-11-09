<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>車隊歷程系統-揪愛騎 Road To Adventure</title>
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
          <div class="col s4 offset-s2 ">
            <div class="card">
              <br>
              <div class="card-content black-text center-align">
	            <h3>歷程新增</h3>
              </div>
              <br>
              <div class="card-action center-align">
                <a class="waves-effect waves-light btn" onclick ="redirectPage('new')">進入</a>
              </div>
            </div>
          </div>
          <div class="col s4 ">
            <div class="card">
              <br>
              <div class="card-content black-text center-align">
			    <h3>歷程管理</h3>
              </div>
              <br>
              <div class="card-action center-align">
                <a class="waves-effect waves-light btn" onclick ="redirectPage('manage')">進入</a>
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
	var path = "${pageContext.request.contextPath}/Group/${groupId}/Journey"
		
    switch (value){
    case "new" :
        path += "/New"
        break;
    case "manage":
        path += "/ReadAll"
        break;
    }
	window.location=path;
  }
  </script>

  </body>
</html>
