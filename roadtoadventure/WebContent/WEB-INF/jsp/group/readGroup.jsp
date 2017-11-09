<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>車隊管理-揪愛騎 Road To Adventure</title>
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
		var image = result.groupArray[i].groupPicture
		var id = result.groupArray[i].groupId
		var name = result.groupArray[i].groupName
		var status = result.groupArray[i].status
		var description = result.groupArray[i].groupDescription
        if(i%3==0){
          html += "<div class =\"row\">"
        }
        html += "<div class=\"col s4\">"
		html += "<div class=\"card small hoverable\">"
		html += "<div class=\"card-image \">"
	    html += "<img class =\"activator\" src=\""+image+"\">"
	    html += "</div>"
	    html += "<div class=\"card-content\">"
	    html += "<span class=\"card-title activator grey-text text-darken-4\">"+name+"<i class=\"material-icons right\">more_vert</i></span>"
	    html += "</div>"
	    html += "<div class=\"card-action center-align\">"
	    if(status=="1"){
	      html += "<a class=\"waves-effect waves-light btn\"onclick= \"redirectPage('"+id+"')\" >進入</a>"
	    }else{
	      html += "<span class = \"red-text text-lighten-1\">待車隊管理員審核</span>"
 		}
	    html += "</div>"
	    html += "<div class=\"card-reveal\">"
	    html += "<span class=\"card-title grey-text text-darken-4\">"+name+"<i class=\"material-icons right\">close</i></span>"
	    html += "<p>簡介：</p>"
	    html += "<p>"+description+"</p>"
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
  function redirectPage(id){
    window.location = "${pageContext.request.contextPath}/Group/"+id+"/Journey"
  }
  </script>

  </body>
</html>
