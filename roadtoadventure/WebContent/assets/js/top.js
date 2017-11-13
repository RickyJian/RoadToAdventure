var topHTML ="";
appendTopHTML()
function appendTopHTML(){
  topHTML ="<nav class=\"light-blue lighten-1\" role=\"navigation\">"
  topHTML +="<div class=\"nav-wrapper container\"><a id=\"logo-container\" href=\""+contextPath+"/\" class=\"brand-logo\">Logo</a>"
  topHTML +="<ul class=\"right hide-on-med-and-down\">"
  topHTML +="<li><a href=\""+contextPath+"/SignUp\">註冊</a></li>"
  if(user=="anonymousUser"){
    topHTML +="<li><a href=\""+contextPath+"/Login\">登入</a></li>"
  }else{
	topHTML +="<li><a href=\""+contextPath+"/j_spring_security_logout.jsp\">登出</a></li>"
    topHTML +="<li><a id=\"menu\" data-activates=\"slide-out\" ><i class=\"material-icons\">menu</i></a></li>"
  }
	topHTML +="</ul>"
	topHTML +="</div>"
	topHTML +="</nav>" 
}


document.write(topHTML)