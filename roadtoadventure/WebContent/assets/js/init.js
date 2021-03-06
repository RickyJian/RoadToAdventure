function logout(){
  self.parent.location =contextPath+"/j_spring_security_logout.jsp";
}

function deletePre(data) {
  data = data.replace(/<pre.*?>/g, ''); //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀  
  data = data.replace(/<PRE.*?>/g, '');
  data = data.replace("<PRE>", '');
  data = data.replace("</PRE>", '');
  data = data.replace("<pre>", '');
  data = data.replace("</pre>", '');
  var result = data;
  return result;
}

function jsonFmt(val){
  	if(typeof val==="object"){
  	  return val
  	}else{
  		return JSON.parse(val)
  	}
}

(function($){
  $(function(){

    $('.button-collapse').sideNav();

  }); // end of document ready
})(jQuery); // end of jQuery name space


//  page load over
$(function(){
	$("#menu").sideNav();
	 $('.modal').modal();
	 $('#modal1').modal('close');
	datepickerFunc()
	timepickerFunc()
})

function datepickerFunc (){
    $('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 15, // Creates a dropdown of 15 years to control year,
	    today: 'Today',
	    clear: 'Clear',
	    close: 'Ok',
	    format: 'yyyy/mm/dd',
	    closeOnSelect: false // Close upon selecting a date,
	  });
}
function timepickerFunc (){
  $('.timepicker').pickatime({
    default: 'now', // Set default time: 'now', '1:30AM', '16:30'
	fromnow: 0,       // set default time to * milliseconds from now (using with default = 'now')
    twelvehour: false, // Use AM/PM or 24-hour format
    donetext: 'OK', // text for done-button
    cleartext: 'Clear', // text for clear-button
    canceltext: 'Cancel', // Text for cancel-button
    autoclose: false, // automatic close timepicker
    ampmclickable: true, // make AM PM clickable
    aftershow: function(){} //Function for after opening timepicker
  });
}
function rewrite(){
  $("input[type='text']").val("")
  $("input[type='email']").val("")
  $("input[type='password']").val("")
  $("input[type='file']").val("")

}
function imageUpload(id , path ,callback){
  $.ajaxFileUpload({
    type:"post",
    url: path,
    secureuri:false,//一般设置为false
    fileElementId:id,
    dataType: 'text/html;charset=UTF-8',
    success: function (data)  {
      var result = jsonFmt(data)
      callback(result);
    }
  });
}

function block (id){
  $("#"+id).block({ css: { 
      border: 'none', 
      padding: '15px', 
      backgroundColor: 'rgba(0, 0, 0, 0)', 
      '-webkit-border-radius': '10px', 
      '-moz-border-radius': '10px', 
      //opacity: .2, 
      color: '#fff' 
      },
      message: $("#preDiv")
    })
}