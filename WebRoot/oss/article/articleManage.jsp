<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script>
		function add()
		{
		   this.location.href = "/oss/article/manageArticle.htm?action=detail";
		}
	</script>
  </head>
  <body>
  <div class="tab">
    <form action="/oss/article/manageArticle.htm" method="post">
	<table width="60%">
	<caption class="redbold">新闻管理</caption>
  	<tr>
    <td width="120" height="25" ><div align="center">类别: <s:select list="categoryList" name="category.id" listKey="id" listValue="name"  value="category.id" headerKey="-1" headerValue="--全部--"/></div></td>
    <td width="130" height="25">标题：<input type="text" name="title" id="title" value="${title }" /> </td>
    <td width="220" >发布时间:
    <input type="text" name="startTime" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/> -
    <input type="text" name="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
	<td width="150" height="25" bgcolor="#ffffff"><div align="left">
	<input type="submit" value="查询" />&nbsp;&nbsp;
	<input type="button" name="add_btn" value="添加" onclick="javascript:add();"/></div>
	</td>
  	</tr>
	</table>
	<br>
	
	<table width="80%" cellspacing="3">
	 	<tr>
	    <td align="center">标题</td>
	    <td align="center">作者</td>
	    <td align="center">日期</td>
	    <td align="center">类别</td>
	    <td align="center">彩种</td>
	    <td align="center">是否置顶</td>
	    <td colspan="3">操作</td>
	    </tr>
	    <tr></tr>
		<s:iterator id="rs" value="page.result">
	  	<tr>
	  	<td align="center"><s:property value="title"></s:property></td>
	  	<td align="center"><s:property value="author"></s:property></td>
	  	<td align="center"><s:date name="publishTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	  	<td align="center"><s:property value="category.name"></s:property></td>
	  	<td align="center"><s:property value="lotteryType"></s:property></td>
	  	<td align="center">
	  	<c:if test="${rs.top}"><span style="color:red">已置顶</span></c:if>
	  	</td>
	  	<td align="center"><a href="/oss/article/manageArticle.htm?action=delete&id=<s:property value="id" />">删除记录</a></td>
	  	<td align="center"><a href="/oss/article/manageArticle.htm?action=detail&id=<s:property value="id" />">修改记录</a></td>
	  	<td align="center"><a href="http://www.yicp.com/actinfo/news_<s:property value="id" />.html">预览</a></td>
	  	</tr>
	  	</s:iterator>
  		</table>
  		<table width="90%" border="0" align="center">
    	<tr><td><jsp:include page="../../util/page.jsp"></jsp:include></td></tr>
  		</table>
  		</form>
    </div>
  </body>
</html>
