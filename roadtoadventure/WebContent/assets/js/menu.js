var menuHTML =  "";

appendMenuHTML()
function appendMenuHTML(){
  menuHTML ="<ul id=\"slide-out\" class=\"side-nav\">"
  menuHTML +="<li><div class=\"user-view\">"
  menuHTML +="<div class=\"background\">"
  menuHTML +="<img src=\""+contextPath+"/assets/images/cycling.jpg\">"
  menuHTML +="</div>"
  if(userPicture == ""){
    menuHTML +="<a href=\"#!user\"><img class=\"circle\" src=\""+contextPath+"/assets/images/p1.png\"></a>"
  }else{
    menuHTML +="<a href=\""+contextPath+"/User/Setting/Edit\"><img class=\"circle\" src=\""+userPicture+"\"></a>"
  }
  menuHTML +="<a href=\""+contextPath+"/User/Setting/Edit\"><span class=\"white-text name\">"+userName+"</span></a>"
  menuHTML +="<a href=\""+contextPath+"/User/Setting/Edit\"><span class=\"white-text email\">"+email+"</span></a>"
  menuHTML +="</div></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Personal/\"><i class= \"material-icons\">person</i>個人歷程系統</a></li>"
  menuHTML +="<li><div class=\"divider\"></div></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Personal/New\">歷程新增</a></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Personal/ReadAll\">歷程管理</a></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Group/\"><i class= \"material-icons\">group</i>車隊管理系統</a></li>"
  menuHTML +="<li><div class=\"divider\"></div></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Group/New\">車隊新增</a></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Group/Read\">車隊管理</a></li>"
  menuHTML +="<li><a href=\""+contextPath+"/Group/ReadAll\">車隊搜尋</a></li>"
  menuHTML +="<li><a href=\""+contextPath+"/User/Setting\" ><i class= \"material-icons\">settings</i>設定</a></li>"
  menuHTML +="<li><div class=\"divider\"></div></li>"
  menuHTML +="<li><a href=\""+contextPath+"/User/Setting/Edit\">個資修改</a></li>"
  menuHTML +="<li><a href=\""+contextPath+"/User/Setting/Friend\">好友系統</a></li>"
  menuHTML +="<li><a class =\"subheader\"></a></li>"
  menuHTML +="<li><a class =\"subheader\"></a></li>"
  menuHTML +="</ul>"
}

    		
document.write(menuHTML) 