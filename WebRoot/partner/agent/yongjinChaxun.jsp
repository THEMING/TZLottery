<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>佣金查询</title>
<link href="../../oss/skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../../oss/styles/base.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
var postPath = "<%=request.getContextPath()%>";
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("form").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("form").submit();
	}
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>佣金查询</strong></h3>
	<form action="yongjin.htm" method="post" >
	
	<br/>
		统计时间：<input type="text" name="stime" id="stime" value="${stime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="etime" id="etime" value="${etime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
			已结算：<select name="yijiesuan" id="yijiesuan">
           <s:if test="yijiesuan.length()>0"><option value="${yijiesuan}" selected="selected"><s:if test="yijiesuan">是</s:if>
      <s:else>否</s:else></option></s:if>
          <option value="">全部</option>
          <option value="true">是</option>
          <option value="false">否</option>
          </select>
		<input type="submit" value="查询" />
		<br/>
		<br/>
		提成比例: <font color="red">${customer.superRatio }</font>%  总佣金：<font color="red">${sumComm}</font>元 已结算佣金：<font color="red">${sumCommEd}</font>元 未结算佣金：<font color="red">${sumCommNo}</font>元 
		
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>统计日期</td>
			<td>佣金(元)</td>
			<td>是否已结算</td>
			<td>结算时间</td>
			<td>消费总额(元)</td>
			<td>消费人数</td>
		</tr>
		<s:iterator value="cpsDayReportPage.result" status="s" id="rs">
			<tr>
				<td>
				<s:property value="#rs.reportDate.substring(0,10)"/>
				</td>
				<td>${rs.commission}</td>
				<td>
				<s:if test="#rs.isPay==true">
			      是
			      </s:if>
			      <s:if test="#rs.isPay==false">
			      否
			      </s:if>
			      </td>
				<td>
				<s:date name="#rs.payTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>${rs.total}</td>
				<td>${rs.salesNum}</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${cpsDayReportPage.pageSize}条 共${cpsDayReportPage.totalCount}条记录 第${cpsDayReportPage.pageNo}/${cpsDayReportPage.totalPages}页   
	<s:if test="cpsDayReportPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${cpsDayReportPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="cpsDayReportPage.pageNo>=cpsDayReportPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${cpsDayReportPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${cpsDayReportPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${cpsDayReportPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
