<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>團隊歷程編輯-揪愛騎 Road To Adventure</title>
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


  <div id="main">
    <div class="container">
      <div class="section">
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
      </div><br>
      <br>
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
