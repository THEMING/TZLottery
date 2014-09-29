<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@ include file="../skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>短信日志记录管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
	function sendAgain(id,pageNo,mobile,smsLogState) {		
		$("#id").val(id);
		$("#sendAgainForm").submit();
		
		caiso.fun.send(postPath+"/oss/customer/smsLogManager.htm?action=sendAgain&id="+id+"&pageNo="+pageNo+"&mobile="+mobile+"&smsLogState="+smsLogState,null,function(data){
		if(data.result==0){alert('发送成功');
		}else{alert('发送失败');}
	});
	}
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>短信日志记录管理</strong></h3>
<form action="/oss/customer/smsLogManager.htm?action=sendAgain&pageNo=${pageNo}&mobile=${mobile}&smsLogState=${smsLogState}" method="post" id="sendAgainForm">
<input type="hidden" name="id" id="id" />
</form>
	<form action="/oss/customer/smsLogManager.htm" method="post" >
	<br/>
		手机号码：<input type="text" name="mobile" id="mobile" value="${mobile}"/>
		短信状态：<s:select list="smsLogStateList" name="smsLogState" id="smsLogState" listValue="text" headerValue="请选择..." headerKey=""></s:select>
		<!-- <input type="text" name="state" id="state" />  -->
		<input type="submit" value="查询" />
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>手机号码</td>
			<td>短信内容</td>
			<td>短信状态</td>
			<td>发送时间</td>
			<td>成功时间</td>
			<td>操作</td>
		</tr>
		<s:iterator value="smsLogPage.result" status="s" id="rs">
			<tr>
				<td>${rs.mobile}</td>
				<td>${rs.content}</td>
				<td>${rs.state.text}</td>
				<td>
				<s:date name="#rs.sendTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<s:date name="#rs.successTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<s:if test="state.toString().equals('FAILURE')">
			      <input type="button" onClick="javascript:sendAgain(${rs.id},${pageNo},${rs.mobile},${smsLogState})" value="重新发送">
			      </s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${smsLogPage.pageSize}条 共${smsLogPage.totalCount}条记录 第${smsLogPage.pageNo}/${smsLogPage.totalPages}页   
	<s:if test="smsLogPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${smsLogPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="smsLogPage.pageNo>=smsLogPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${smsLogPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${smsLogPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${smsLogPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
