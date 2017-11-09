<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>個人歷程新增-揪愛騎 Road To Adventure</title>
  <script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

</head>
<body>
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/top.js"></script>
  <br><br>
  <div id="main">
    <div class="container">
      <div class="section">
        <div class="row">
          <br>
          <br>
          <form class="col s12" id="journeyForm" name="journeyForm">
            <div class="row">
              <div class="input-field col s12">
                <input class="validate" id="personalJourneyName" name="personalJourneyName" type="text" data-length="50"> <label for="groupName">歷程名稱</label>
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
        <div>
          <div class="row center">
            <div class="col s6">
              <a class="waves-effect waves-light btn" onclick="rewrite()">重新輸入</a>
            </div>
            <div class="col s6">
              <a class="waves-effect waves-light btn" onclick="send()">送出</a>
            </div>
          </div>
        </div>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/preloader.js"></script>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/materialize.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/init.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/validate/jquery.validate.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/validate/additional-methods.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/validate/lang/messages_zh_TW.js"></script>  
  <script src="${pageContext.request.contextPath}/assets/js/block.js"></script>
  <script type="text/javascript">
  $(function(){
	  formValidate()
  })
  function formValidate(){
    $("#journeyForm").validate({
	  rules: {
		personalJourneyName: {
	      required: true,
	      minlength: 1,
	      maxlength: 50
	    },
	    beginDay: {
		  required: true
	    },
	    beginTime: {
	 	  required: true
	 	},
	 	endDay: {
	 	  required: true
	 	},
	 	endTime: {
            required: true
        }
	  },
	  errorElement : 'div',
	  errorPlacement: function(error, element) {
	    var placement = $(element).data('error');
	    if (placement) {
	      $(placement).append(error)
	    } else {
	      error.insertAfter(element);
	    }
	  }
	});	
  }
  function send(){
	if($("#journeyForm").valid()){
	  block("main")	
      $.ajax({
	    type: "POST",
	    dataType: 'json',
	    data:$("#journeyForm").serialize(),
	    url:"${pageContext.request.contextPath}/Personal/Create",
        async: false ,
	    success: function(data){
		  var result = jsonFmt(data)
		  if(result.success =="1"){
       	    Materialize.toast("<i class = \"material-icons\">done</i>&nbsp;新增成功，自動跳轉歷程管理頁面。", 3000,'',function(){
	          window.location="${pageContext.request.contextPath}/Personal/ReadAll"
            })
	      }else{
	        Materialize.toast("<i class = \"material-icons\">done</i>&nbsp; 新增失敗，請在試一次。", 5000)
	      }
	      $("#main").unblock();
	    }
      })    
    }  
    
  }
  </script>

  </body>
</html>
