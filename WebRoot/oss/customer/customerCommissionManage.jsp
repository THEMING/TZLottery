<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>用户提成管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="/js/jquery-1.4.4.min.js"></script>
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script>
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("form").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("form").submit();
	}
	function update(num)
	{
		$("#selectVal").val($("#seleVal_"+num).val());
		$("#idTemp").val($("#commId_"+num).val());
		$("#formUpdate").submit();
	}
</script>
</head>
<body>
<div class="tab">
	<form action="/oss/customer/manageCusCommission.htm?action=update" method="post" id="formUpdate">
		<input type="hidden" name="selectVal" id="selectVal" />
		<input type="hidden" name="idTemp" id="idTemp" />
	</form>
	<form action="/oss/customer/listCusCommission.htm" method="post">
	<br/>
		用户名：<input type="text" id="nickName" name="nickName"><input type="submit" value="查询">
	<br/>
	<br/>
	<span style="color:red"><s:actionmessage/></span>
	<table id="tb"  width="90%">
		<tr>
			<td>推广ID</td>
			<td>用户名</td>
			<td>姓名</td>
			<td>电话</td>
			<td>方案名</td>
			<td>提成比率</td>
			<td>操作</td>
		</tr>
		<s:iterator value="customerPage.result" status="s">
			<tr>
				<td>${id}</td>
				<td>${nickName}</td>
				<td>${realName}</td>
				<td>${channel.linkPhone}</td>
				<td>${commission.name}</td>
				<td>${commission.ratio1}</td>
				<td>
					<select id="seleVal_${s.index}">
						<s:iterator value="commissionNames">
							<option><s:property value="Name"/></option>
						</s:iterator>
					</select>
					<input type="hidden" value="<s:property value="id" />" id="commId_<s:property value="#s.index" />">
					<input type="button" value="确认" onclick="update(<s:property value="#s.index" />);">
				</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${customerPage.pageSize}条 共${customerPage.totalCount}条记录 第${customerPage.pageNo}/${customerPage.totalPages}页   
	<s:if test="customerPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${customerPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="customerPage.pageNo>=customerPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${customerPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${customerPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${customerPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
