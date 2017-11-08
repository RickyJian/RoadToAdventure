<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>登入-揪愛騎 Road To Adventure</title>
  <script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

</head>
<body>
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br><br>
      <h2 class="header center orange-text">權限不夠，五秒後自動返回上一頁</h2>
      <br><br><br>

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
	setTimeout(function() {
	  window.history.back();
	}, 5000)
  })
  </script>
  </body>
</html>
