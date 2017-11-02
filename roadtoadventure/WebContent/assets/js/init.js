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
(function($){
  $(function(){

    $('.button-collapse').sideNav();

  }); // end of document ready
})(jQuery); // end of jQuery name space


//  page load over
$(function(){
	$("#menu").sideNav();
})

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
      var result = JSON.parse(data)
      if(result.success=="1"){
        callback(result.image);
      }else{
    	alert(result.message);
      }
    }
  });
}