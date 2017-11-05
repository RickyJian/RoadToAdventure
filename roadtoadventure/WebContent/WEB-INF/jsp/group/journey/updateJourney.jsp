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
            <div  name ="wayPoint" class="row ">
              <div class="input-field col s10">
                <input class="validate" id="start" name="start" type="text"> <label for="start">起點(請輸入起始地點)</label>
              </div>
              <div class="col s1 right-align">
                <a id= "add" class="btn-floating btn-large waves-effect waves-light" onclick = "addWayPointField(0)"><i class="material-icons">add</i></a>
              </div>
              <div class="col s1 right-align">
                <a style ="display:none;" id= "remove" class="btn-floating btn-large waves-effect waves-light" onclick = "reomveWayPointField(0)"><i class="material-icons">add</i></a>
              </div>
            </div>
            <div  name ="wayPoint" class="row">
              <div class="input-field col s10">
                <input class="validate" id="destination" name="destination" type="text"> <label for="destination">終點(請輸入目的地)</label>
              </div>
              <!-- 
              <div class="col s2 right-align">
                <button class="btn waves-effect waves-light btn-large" id="send"  type="button">路線規劃</button>
              </div>
               -->
			</div>
			<div class = "row col s12" >
                <a class="col s2 offset-s2 btn waves-effect waves-light btn-small modal-trigger"  href="#modal1">等高線顯示</a>
                <button class="col s2 offset-s2 btn waves-effect waves-light btn-small" id="send"  type="button">路線規劃</button>
			
			</div>
            <div class="row" id="map"></div>
          </div>
          <div class="col s12 row" id="journey">
            <textarea id = "groupJourneyContent" name = "groupJourneyContent"></textarea>
          </div>
          <div class ="row col s12 center-align">
            <a class="btn waves-effect waves-light btn-large" id="update"  type="submit" onclick="update()">規劃完成</a>
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
  CKEDITOR.replace('groupJourneyContent');
  $(function(){
	  $('.modal').modal();
	  })
  function update(){
		//$("#main").block({ message: "<h5>系統處理中請稍後。</h5>"})
		$.ajax({
		  type: "POST",
		  datatype:"json",
		  data:{
			//"groupId":'${groupId}',
			//"groupJourneyId":'${journeyId}',			
		    "locationArrayStr":locationArray.join(),
		    "overviewPolyline":overviewPolyline,
		    "groupJourneyContent":CKEDITOR.instances.groupJourneyContent.getData()
		  },
		  url:"${pageContext.request.contextPath}/Group/${groupId}/Journey/${journeyId}/Update",
		  async: false ,
		  success: function(data){
			var result = JSON.parse(data)
	        if(result.success=="1"){
	        	 //Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;註冊成功，自動跳轉首頁。", 3000,'',function(){
		         //  window.location="${pageContext.request.contextPath}/Index"
	             //})
	        }else{
		      //$("#main").unblock()
	          //Materialize.toast("<i class = \"material-icons\">announcement</i>&nbsp; 註冊失敗", 5000)
	        }
		  }
		});	  
  }
  function closeModel(){
	  $('#modal1').modal('close');
	  }
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYVBpGeFB5L5UqunJlJ19rxxBooiVNNoE&callback=planningMap">
  </script>
  
  </body>
</html>
