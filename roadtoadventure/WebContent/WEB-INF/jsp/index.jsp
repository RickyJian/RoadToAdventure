<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <link href="assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <style>
    #map {
      height: 400px;
      width: 100%;
    }
  </style>
</head>
<body>
<!-- 
 -->
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js">

  </script>
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br>
      <h1 class="header center orange-text">位置共享</h1>
      <div id = "map" >
        
      </div>
      <br><br>

    </div>
  </div>

  <div class="container">
    <div class="section">

      <!--   Icon Section   -->
      <div class="row">
       <h1 class="header center orange-text">排行榜</h1>
        <div class="col s12 m4">
        <h5>單人</h5>
	    <div class="card horizontal">
	      <div class="card-image">
	        <img src="https://lorempixel.com/100/190/nature/6">
	      </div>
	      <div class="card-stacked">
	        <div class="card-content">
	          <p><c:out value = "${user}"/></p>
	        </div>
	      </div>
	    </div>
        </div>

        <div class="col s12 m4">
        <h5>團體</h5>
	    <div class="card horizontal">
	      <div class="card-image">
	        <img src="https://lorempixel.com/100/190/nature/6">
	      </div>
	      <div class="card-stacked">
	        <div class="card-content">
	          <p>Jobs Steve</p>
	        </div>
	      </div>
	    </div>
        </div>

        <div class="col s12 m4">
        <h5>挑戰</h5>
	    <div class="card horizontal">
	      <div class="card-image">
	        <img src="https://lorempixel.com/100/190/nature/6">
	      </div>
	      <div class="card-stacked">
	        <div class="card-content">
	          <p>Jobs Steve</p>
	        </div>
	      </div>
	    </div>
        </div>
      </div>

    </div>
    <div class = "section">
      <div class = "row">
        <br><br><br><br><br><br><br>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bottom.js"></script>

  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/menu.js"></script>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="assets/js/materialize.js"></script>
  <script src="assets/js/init.js"></script>
  <script src="assets/js/map.js"></script>
  <script type="text/javascript">

  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYVBpGeFB5L5UqunJlJ19rxxBooiVNNoE&callback=initMap">
  </script>
  </body>
</html>
