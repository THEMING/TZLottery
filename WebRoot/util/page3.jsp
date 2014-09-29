<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script>
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("form").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("form").submit();
	}
</script>

<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${mappingPage.pageSize}条 共${mappingPage.totalCount}条记录 第${mappingPage.pageNo}/${mappingPage.totalPages}页   
	<s:if test="mappingPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${mappingPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="mappingPage.pageNo>=mappingPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${mappingPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${mappingPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${mappingPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
</div>
