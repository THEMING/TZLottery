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
<script src="skin/01/js/common.js"></script>
<script src="skin/01/js/jquery-1.3.2.js"></script>
<script language="javascript">
function fromcheck(){
	if(getSelectedValue("parentId")=="请选择.."){
	
		alert("提示：\n\n请选择功能版块的父级版块！");
		$("#parentId").focus();
		return false;
	}
	if($("#chnName").val()==""){
		
		alert("提示:\n\n请填写功能版块名称！");
		$("#chnName").focus();
		return false;
	}
	if($("#priority").val()!=""&&!isInt($("#priority").val())){
		
		alert("提示:\n\n请填写数字！");
		$("#priority").focus();
		return false;
	}
	
}
</script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body>
<!--编辑部分开始 -->
<div id="edit">

  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
      <td><span class="title">核心功能 ></span>  功能菜单</td>
      <td align="right"  nowrap="nowrap"><span class="hui operation">管理导航</span><span class="red"> - </span> <a href="mngChannel.do">返回</a></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <form id="form" name="form" method="post" action="adminEditTunnel.aspx?action=sav" onsubmit="return fromcheck();">
 
  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    
    <tr class="td_bg">
      <th colspan="2" class="redbold">编辑功能菜单</th>
    </tr>
	
    <tr  class="td_bg">
      <td width="156" align="center"><strong>所属功能：</strong></td>
      <td width="1087">
	  <select name="channel.parentId" id="parentId" >
        <option>请选择..</option>
		<s:if test="channel.parentId==0">
        <option value="0" selected="selected">功能版块</option>
		</s:if>
		<s:else>
		<option value="0">功能版块</option>
		</s:else>
  		<s:iterator id="chn" value="depthList" >
			<s:if test="#chn.id==channel.parentId">
			<option value="${chn.id}" selected="selected">${chn.channelName}</option>
			</s:if>
			<s:else>
			<option value="${chn.id}">${chn.channelName}</option>
			</s:else>
		
    	</s:iterator>
      </select>
      <input name="channel.id" type="hidden" id="id" value='${channel.id}' /></td>
    </tr>
    <tr class="td_bg">
      <td align="center"><strong>功能名称：</strong></td>
      <td><input name="channel.channelName" type="text"  class="input" id="chnName" onclick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'" value="${channel.channelName}" size="50" maxlength="40"/></td>
    </tr>
    <tr  class="td_bg">
      <td align="center"><strong>描 述：</strong></td>
      <td><textarea name="description" cols="50" rows="5" id="description"  class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'">${channel.description}</textarea></td>
    </tr>
    <tr class="td_bg">
      <td align="center"><strong>功能类型：</strong></td>
      	<td>
		 <input name="channel.channelType" type="radio" value="1" checked="checked"/>
		内部  
		 <input type="radio" name="channel.channelType" value="2"/>
		外部
		</td>
    </tr>
    <tr  class="td_bg">
    </tr>
    <tr  class="td_bg">
      <td align="center"><strong>左侧URL：</strong></td>
      <td>
       <s:if test="channel==null">
	   <input name="channel.righturl" type="text" id="righturl" value='javascript:void(0);' size="50" maxlength="100"  class="input" onClick="this.className='focus'" onblur="if (value ==''){value='javascript:void(0);'}"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/>
	   </s:if>
	   <s:else>
	   <input name="channel.righturl" type="text" id="righturl" value='${channel.righturl}' size="50" maxlength="100"  class="input" onClick="this.className='focus'" onblur="if (value ==''){value='javascript:void(0);'}"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/>
	   </s:else>
	   <span class="STYLE1">当点击左侧导航，左侧导航不需要刷新时，此项可为空</span></td>
    </tr>
    <tr  class="td_bg">
      <td align="center"><strong>排 序：</strong></td>
      <td><input name="channel.priority" type="text" class="input" id="priority" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'" value="${channel.priority}" size="8" maxlength="4"/></td>
    </tr>
    <tr  class="td_bg">
      <td align="center"><strong>状 态：</strong></td>
      <td>
      <s:if test="channel==null  || channel.ispass==1 ">
	  <input name="channel.ispass" type="checkbox" id="isPass" value="1"  checked="checked"/>
	 </s:if>
	 <s:else>
	  <input name="channel.ispass" type="checkbox" id="isPass" value="1"/>
     </s:else>
        激活</td>
    </tr>
  </table>
  <table height="75" border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td align="center"> <input name="Submit2" type="submit" value="保 存" class="button" />　
        <input name="Submit23" type="button" value="取 消" class="button"   onclick="javascript:history.back();" /></td>
    </tr>
  </table>
</form>
</div>
<!--编辑部分结束 -->

</body>
</html>