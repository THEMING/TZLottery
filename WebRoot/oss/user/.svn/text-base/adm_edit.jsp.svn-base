<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="skin/01/js/common.js" type=text/javascript></script>
<script src="skin/01/js/passwd.js" type=text/javascript></script>
<script src="skin/01/js/jquery-1.3.2.js"></script>
<script language="javascript">
function formcheck(){

		if(!isUnderline($("#adminName").val())||$("#adminName").val().length<4){
			alert("提示：\n\n请正确填写管理员帐号！(仅限于4-16个英文或数字字符(A-Z，a-z，0-9)及 _ 的组合)");
			$("#admin_name").focus();
			return false;
		}

		if((!isIntAndChar($("#password").val())||$("#password").val().length<4)&&$("#id").val()==""){
			alert("提示：\n\n请正确填写管理员密码！(4-16个英文、数字（A-Z，a-z,0-9!@#$%^&*()）的组合，英文字母请注意大小写)");
			$("#password").focus();
			return false;
		}

		if($("#password").val()!=$("#password2").val()){
			alert("提示：\n\n密码输入不一致！");
			$("#password2").focus();
			return false;
		}
		
		if(!isEmail($("#email").val())&&$("#email").val().length>0){
			alert("提示：\n\n请正确填写电子邮件！");
			$("#email").focus();
			return false;
		}
	
		if(getSelectedValue("role_id")=="请选择.."){
			alert("提示：\n\n请选择管理员角色！");
			$("#role_id").focus();
			return false;
	    }

		return true;
}
</script>
</head>

<body>
<!--编辑部分开始 -->
<div id="edit">
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td><input name="chnId" type="hidden" id="chnId" value='${chnId}'/></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
      <td><span class="title">用户管理 ></span>  管理员</td>
      <td align="right"  nowrap="nowrap"><span class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="mngUser.do?act=getAdmlist" class="blue">返回</a></span></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="25">&nbsp;</td>
    </tr>
  </table>  
  <form id="form" name="form" method="post" action="manageUser.aspx?action=saveUser&chnId=${chnId}" onsubmit="return formcheck();">

  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    
    <tr class="td_bg">
      <th colspan="2" class="redbold">管理员编辑</th>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td width="181"  align="center">管理员帐号：</td>
      <td width="732">
	  <input name="adminUser.id" type="hidden"  value="${adminUser.id}" />
   <s:if test="adminUser.id>0">
	  <input name="adminUser.adminName" type="hidden" id="adminName" value="${adminUser.adminName}" />
	  <strong>${adminUser.adminName}</strong>
	  </s:if>
	  <s:else>
	  <input name="adminUser.adminName" type="text" id="adminName" value="${adminUser.adminName}" size="20" maxlength="16" class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/>
	  仅限于4-16个英文或数字字符(A-Z，a-z，0-9)及 _ 的组合。
	  </s:else>
	  <input name="userId" type="hidden" id="userId"  value="${adminUser.id}"/></td>
    </tr>

    <tr  class="td_bg">
      <td align="center">昵　　称：</td>
      <td><input name="adminUser.nickName" type="text" id="nickName" value="${adminUser.nickName}" size="20" maxlength="20" class="input" onclick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/></td>
    </tr>
     <s:if test="adminUser.id<=0">
    <tr  class="td_bg">
      <td align="center">管理员密码：</td>
      <td><input id="password" name="adminUser.password" size="20" maxlength="16" value="${adminUser.password}" onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)" type="password" class="input" onClick="this.className='focus'"  />
        请输入4-16个英文、数字（A-Z，a-z,0-9）的组合，英文字母请注意大小写</td>
    </tr>
    <tr  class="td_bg">
      <td align="center">重复密码：</td>
      <td><input name="password2" type="password" id="password2" size="20" maxlength="16" class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/>
        请再输入一遍密码</td>
    </tr>
   </s:if>
     <tr class="td_bg">
       <td align="center">电子邮件：</td>
       <td><input name="adminUser.email" type="text" id="email" value="${adminUser.email}" size="50" maxlength="80" class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/></td>
     </tr>
     <tr  class="td_bg">
       <td align="center">角　　色：</td>
       <td>

   	   <select name="adminUser.role.id" id="role_id">
         <option value="请选择..">请选择..</option>
 		 <s:iterator id="rs" value="roleList" status="st">
		 <s:if test="null!=adminUser">
		 <s:if test="#rs.id==adminUser.role.id">
		 <option value="${rs.id}" selected="selected">${rs.name}</option>
		 </s:if>
		 <s:else>
		 <option value="${rs.id}" >${rs.name}</option>
		 </s:else>
		 </s:if>
		 <s:else>
		 <option value="${rs.id}" >${rs.name}</option>
		 </s:else>
		 
		 </s:iterator>
       </select>
       </td>
     </tr>
     <tr onMouseOver="over()" onClick="change()" onMouseOut="out()" class="td_bg">
      <td align="center">用户状态：</td>
      <td><input name="adminUser.ipass" type="checkbox" id="ipass" value="1" checked="checked"/>
        激活</td>
    </tr>
  </table>
  <table height="75" border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td align="center"> <input name="Submit2" type="submit" value="保 存" class="button" onmouseover="this.className='button1'" onmouseout="this.className='button'" />　
        <input name="Submit22" type="button" value="取 消"  class="button" onmouseover="this.className='button1'" onmouseout="this.className='button'"  onclick="history.back()"/>　
        </td>
    </tr>
  </table>
</form>
</div>
<!--编辑部分结束 -->

</body>
</html>