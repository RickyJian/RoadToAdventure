<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>登入-揪愛騎 Road To Adventure</title>
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
          <form method = "post" action = "login.do" class="col s12">
            <div class="row">
	          <div class="input-field col s6 offset-s3" >
		        <input id="userId" name = "userId" type="text" class="validate">
		        <label for="userId">帳號</label>
              </div>
            </div>
            <div class = "row">
	          <div class="input-field col s6 offset-s3" >
                <input id="password" name = "password" type="password" class="validate">
      	        <label for="password">密碼</label>
              </div>
            </div>
            <div class ="row  center-align">
              <div class = "col s4 offset-s2">
	            <a class="waves-effect waves-light btn" onclick ="redirectPage()">註冊</a>    
		      </div>    
              <div class = "col s4">
	            <button class="btn waves-effect waves-light" type="submit">登入</button>        
              </div>
            </div>
          </form>
        </div>
        <div class ="row center-align">
          <div class = "col s12">
		    <font color="red">
		        登入失敗。
		    </font>      
          </div>
          <div class = "col s12">
            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		      <font color="red">
		            原因：
		      <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		      </font>
		    </c:if>      
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
  <script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
  <script type="text/javascript">
  function redirectPage(){
    window.location ="${pageContext.request.contextPath}/SignUp"
  }
  </script>

  </body>
</html>
