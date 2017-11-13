<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>團隊歷程管理-揪愛騎 Road To Adventure</title>
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
        <div id ="cardDiv">
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
  $(function(){
    appendCards();
  })
  function appendCards(){
	var result = JSON.parse('${group}')
	if(result.success =="1"){
      var html = "";
	  for(var i = 0 ; i < result.groupArray.length ; i++){
		var journeyName = result.groupArray[i].journeyName
		var groupId = result.groupArray[i].groupId
		var journeyId = result.groupArray[i].journeyId
		var beginDate = result.groupArray[i].beginDate
		var endDate = result.groupArray[i].endDate
        if(i%3==0){
          html += "<div class =\"row\">"
        }
        html += "<div class=\"col s4\">"
		html += "<div class=\"card small hoverable\">"
	    html += "<div class=\"card-content\">"
	    html += "<span class=\"card-title grey-text text-darken-4\">"+journeyName+"</span>"
	    html += "<p>起始時間："+beginDate+"</p>"
	    html += "<p>結束時間："+endDate+"</p>"
	    html += "</div>"
	    html += "<div class=\"card-action center-align\">"
	      html += "<a class=\"waves-effect waves-light btn col s4 \"onclick= \"redirectPage('"+groupId+"','"+journeyId+"','edit')\" >編輯</a>"
	      html += "<a class=\"waves-effect waves-light btn col s4 offset-s4 \"onclick= \"redirectPage('"+groupId+"','"+journeyId+"','read')\" >詳情</a>"
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
  function redirectPage(groupId , journeyId , type){
    var path = "${pageContext.request.contextPath}/Group/"+groupId+"/Journey/"+journeyId
    switch (type){
    case "edit" :
      path += "/Edit"
    break;
    case "read" :
      path += "/Read"
    break;
    }
    window.location =path
  }
  </script>

  </body>
</html>
