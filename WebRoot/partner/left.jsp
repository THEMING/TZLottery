<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>一彩票-代理商系统</title>
<link href="../oss/skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<style>   
#foldheader{cursor:hand; font-weight:bold;list-style-image:url(images/fold.gif); } 
#foldinglist {margin:0px; padding-left:12px; padding-bottom:10px;list-style-image:url(images/list.gif); line-height:18px; } 
</style>   
</head>
<body>
	<div id="main">
	
		<div id="left" style="width:216px;" >
		   <div class="blockBav">信息中心</div>
			   <div class="menu" style="width:158px;" align="left">
			
			   	 <table width="147" align="center" border="0" cellpadding="0" cellspacing="0">
			   	 <s:iterator id="channel" value="parentChannelList">
			   		<%--<s:if test="ispass eq true">
					--%><tbody>
					<tr style="cursor: pointer;" height="20"> 
					  <td width="140" height="27">
						  <table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
							  <tr>
								<td width="10%" align="center"><img src="../oss/skin/01/images/arrow_add.gif" width="7" height="12"></td>
								<td style="padding-top: 5px;" valign="bottom" width="90%">
								
								<a href="<s:property value="righturl"/>?chnId=<s:property value="id"/>" target="mainframe" ><s:property value="channelName"/></a>
		
								</td>
							  </tr>
							</tbody>
						 </table>
					 </td>
					</tr>
				  </tbody>
				  <%--</s:if>
				 --%></s:iterator>
				 </table>
			  </div>
		</div>
	 </div>
</body>
</html>