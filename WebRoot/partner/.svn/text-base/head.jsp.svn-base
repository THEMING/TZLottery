<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>网站后台管理系统</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../oss/skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="../oss/skin/01/js/common.js" type="text/javascript"></script>
<script language="javascript">
//切换频道
function channelNav(Obj, channel) {
	var channelTabs = document.getElementById('topmenu').getElementsByTagName('a');
	for (i=0; i<channelTabs.length; i++) {
		channelTabs[i].className = '';
	}
	Obj.className = 'current';
	Obj.blur();
	var sideDoc = window.parent.leftframe.document;
	var sideChannels = sideDoc.getElementsByTagName('div')
	for (i=0; i<sideChannels.length; i++) {
		sideChannels[i].style.display = '';
	}
	var sideChannelLinks = sideDoc.getElementsByTagName('a')
	for (i=0; i<sideChannelLinks.length; i++) {
		sideChannelLinks[i].className = '';
	}
}
</script>
</head><body>
<div id="header">
   <div id="logo"><img src="../oss/skin/01/images/logo.gif"/></div>
   
      <div id="quickBt">当前用户：<strong>${customer.nickName}</strong>　　[<a href="index.aspx?action=exit"  target="_top">退出登录</a> ] </div>
      <div id="topmenu">
      <ul>
		   	<s:iterator id="channel" value="depthList" status="st">
		   	 <li>
		   	 <s:if test="#st.index==0">
 			<a class=current onclick="channelNav(this, '');" href="index.aspx?action=left&depth=${channel.id}" target="leftFrame"><s:property value="channelName"/></a>
			</s:if>
			<s:else>
 			<a onclick="channelNav(this, '');" href="index.aspx?action=left&depth=${channel.id}" target="leftFrame"><s:property value="channelName"/></a>
			</s:else>
		   	 </li>
		   	</s:iterator>
      </ul>
  	  <%--<ul>
		   	<s:iterator id="channel" value="depthList" status="st">
		   	 <li>
		   	 <s:if test="#st.index==0">
 			<a class=current onclick="channelNav(this, '');" href="adminTunnel.aspx?action=left&depth=${channel.id}" target="leftFrame"><s:property value="channelName"/></a>
			</s:if>
			<s:else>
 			<a onclick="channelNav(this, '');" href="adminTunnel.aspx?action=left&depth=${channel.id}" target="leftFrame"><s:property value="channelName"/></a>
			</s:else>
		   	 </li>
		   	</s:iterator>
      </ul>
   --%></div>
  <div id="sitegrp"></div>
</div>
</body>

</html>