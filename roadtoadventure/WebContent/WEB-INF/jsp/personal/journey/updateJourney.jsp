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
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <br><br><br>
  <div id="main">
    <div class="container">
      <div class="section">
        <div class="row">
          <div class="col s12">
            <ul class="tabs">
              <li class="tab col s4">
                <a class="active" href="#personalJourney">歷程修改</a>
              </li>
              <li class="tab col s4">
                <a class="active" href="#routePlanning">路線規劃</a>
              </li>
              <li class="tab col s4">
                <a href="#journeyContent">路程攻略</a>
              </li>
            </ul>
          </div>
          <div class="col s12" id="personalJourney">
            <div class ="row">
              <form class="col s12" id="journeyForm" name="journeyForm">
                <div class ="row col s3 offset-s9">
  				  <div class="switch">
    				<label>
      				   停用
      				  <input type="checkbox">
      				  <span class="lever"></span>
     				   啟用
    			    </label>
                  </div>                
                </div>
                <div class="row">
                  <div class="input-field col s12">
                    <input class="validate" id="personalJourneyName" name="personalJourneyName" type="text" data-length="50"> <label for="personalJourneyName">歷程名稱</label>
                  </div>
                </div>
            <div class="row">
              <div class = "input-field col s6">
                <input type="text" id = "beginDay" name = "beginDay" class="datepicker">
                <label for="beginDate">起始日期</label>
              </div>
              <div class = "input-field col s6">
                <input type="text" id = "beginTime"  name = "beginTime" class="timepicker">
                <label for="beginTime">起始時間</label>
              </div>
            </div>            
            <div class="row">
              <div class = "input-field col s6">
                <input type="text" id = "endDay" name = "endDay" class="datepicker">
                <label for="endDate">結束日期</label>
              </div>
              <div class = "input-field col s6">
                <input type="text" id = "endTime" name = "endTime" class="timepicker">
                <label for="endTime">結束時間</label>
              </div>
            </div>          
          </form>
          </div>
          </div>
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
			</div>
			<div class = "row col s12" >
                <a class="col s2 offset-s2 btn waves-effect waves-light btn-small modal-trigger"  href="#modal1">等高線顯示</a>
                <button class="col s2 offset-s2 btn waves-effect waves-light btn-small" id="send"  type="button">路線規劃</button>
			
			</div>
            <div class="row" id="map"></div>
          </div>
          <div class="col s12 row" id="journeyContent">
            <textarea id = "personalJourneyContent" name = "personalJourneyContent"></textarea>
          </div>
          <div class ="row col s12 center-align">
            <a class="btn waves-effect waves-light btn-large" id="update"  type="submit" onclick="update()">規劃完成</a>
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
  CKEDITOR.replace('personalJourneyContent');
  $(function(){
    init()
  })
  function formValidate(){

  }  
  function init(){
    var result = JSON.parse('${journey}')
    var wpHTML = "<div  name =\"wayPoint\" class=\"row\">"
	wpHTML += "<div class=\"input-field col s10\">"
	wpHTML += "<input class=\"validate\"  name=\"wayPoints\" type=\"text\"> <label for=\"start\">中途點(請輸入地點)</label>"
	wpHTML += "</div>"
	wpHTML += "<div class=\"col s1 right-align\">"
	wpHTML += "<a id= \"add\" class=\"btn-floating btn-large waves-effect waves-light\" onclick = \"addWayPointField()\"><i class=\"material-icons\">add</i></a>"
	wpHTML += "</div>"
	wpHTML += "<div class=\"col s1 right-align\">"
	wpHTML += "<a id= \"remove\" class=\"btn-floating btn-large waves-effect waves-light\"  onclick = \"removeWayPointField()\"><i class=\"material-icons\" >remove</i></a>"
	wpHTML += "</div>"
	wpHTML += "</div>"
    if(result.success=="1"){
      var arr = result.array;
      for(var i = 0 ; i < result.array.length ; i++){
	    if(i!=0&&i!=(result.array.length-1)){
		  $( "body" ).find( "div[name='wayPoint']" ).eq(i-1).after(wpHTML).ready(function(){ 
			adjustWayPointNo()
		  })
	    }
      }
      locationArray= [];
      $.each(arr, function( index, value ) {
        if(index==0){
          $("#start").val(value.location)
        }else if (index==(arr.length-1)){
          $("#destination").val(value.location)
        }else{
     	  $( "body" ).find( "div[name='wayPoint']" ).eq(index).find("input[name='wayPoints']").val(value.location)
        }
        locationArray.push(value.location)
      });
      overviewPolyline = '${overviewPolyline}'
      setTimeout(function(){
        drawPolyline()
      },5000)
      alert(result.journeyName)
      $("#personalJourneyName").val(result.journeyName)
      $("#beginDay").val(result.beginDay)
      $("#endDay").val(result.endDay)
      $("#beginTime").val(result.beginTime)
      $("#endTime").val(result.endTime)
      $("#personalJourneyContent").val(CKEDITOR.instances.personalJourneyContent.setData(result.content));
    }
  }
  function update(){
		//$("#main").block({ message: "<h5>系統處理中請稍後。</h5>"})
		$.ajax({
		  type: "POST",
		  datatype:"json",
		  data:{	
		    "locationArrayStr":locationArray.join(),
		    "overviewPolyline":overviewPolyline,
		    "personalJourneyContent":CKEDITOR.instances.personalJourneyContent.getData()
		  },
		  url:"${pageContext.request.contextPath}/Personal/Journey/${journeyId}/Update",
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
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYVBpGeFB5L5UqunJlJ19rxxBooiVNNoE&libraries=geometry&callback=planningMap">
  </script>
  
  </body>
</html>
