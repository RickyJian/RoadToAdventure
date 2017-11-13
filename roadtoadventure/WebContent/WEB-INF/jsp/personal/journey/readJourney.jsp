<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>個人歷程詳情-揪愛騎 Road To Adventure</title>
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
  <style>
    #map {
      height: 500px;
      width: 100%;
    }
    #groupJorneyContent {
      height: 500px;
      width: 100%;
    }
    canvas{
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
  </style>
</head>
<body>
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <div id="main">
    <div class="container">
      <div class="section">
      <br><br>
        <div class="row">
          <div class="col s12">
            <ul class="tabs">
              <li class="tab col s6">
                <a class="active" href="#routePlanning">路線規劃</a>
              </li>
              <li class="tab col s6">
                <a href="#journey">路程攻略</a>
              </li>
            </ul>
          </div>
          <!-- 
           -->
          <div class="col s12" id="routePlanning">
            <div class = "row" >
              <a class="col s2  offset-s5 btn waves-effect waves-light btn-small modal-trigger"  href="#modal1">等高線顯示</a>
	        </div>
          <div class="row" id="map"></div>
          </div>
          <div class="col s12 row" id="journey">
            
          </div>
          
        </div><br>
        <br>
        <br>
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
  <!-- model  -->
    <div id="modal1" class="modal bottom-sheet">
    <div class="modal-content">
      <canvas id="canvas"></canvas>
    </div>
  </div>
  
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/ckeditor/ckeditor.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/block.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/chart/Chart.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/chart/Chart.bundle.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/chart/utils.js"></script>

  <script type="text/javascript">
  //CKEDITOR.replace('groupJourneyContent');
  $(function(){
	  
	  appendRoute()
	  overviewPolyline = '${overviewPolyline}'
      appendContent()
  })
  function appendRoute(){
	locationArray= [];
    var result = JSON.parse('${journey}')
	if(result.success =="1"){
	  var html = "";
	  for(var i = 0 ; i < result.array.length ; i++){
        html += "<div class=\"row\">"
        html += "<div class=\"col s12 center-align\">"
        html += "<h5>"+result.array[i].location+"</h5>"
        html += "</div>"
        html += "</div>"
        if(i!=(result.array.length-1)){
          html += "<div class=\"row center-align\">"
          html += "<i class = \"Large material-icons\">arrow_downward</i>"
          html += "</div>"
        }
        locationArray.push(result.array[i].location)
      }
	  $("#routePlanning").prepend(html)
    }else{
      Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+result.message, 5000)
	}
  }
  function appendContent(){
		var result = JSON.parse('${journey}')
		var content = result.content;
	    var html = "";
		if(result.success =="1"){
		html += "<div class =\"row\">"
	    html += "<div class=\"col s12\">"
		html += "<div class=\"card large\">"
		html += "<div class=\"card-content\">"
		html += "<p>"+content+"</p>"
		html += "</div>"
		html += "</div>"
		html += "</div>"
		html += "</div>"

	      $("#journey").append(html)
		}else{
			Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; "+result.message, 5000)
		}
	  
  }
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYVBpGeFB5L5UqunJlJ19rxxBooiVNNoE&libraries=geometry&callback=drawPolyline">
  </script>
  </body>
</html>
