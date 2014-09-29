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
	每页${historyPage.pageSize}条 共${historyPage.totalCount}条记录 第${historyPage.pageNo}/${historyPage.totalPages}页   
	<s:if test="historyPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${historyPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="historyPage.pageNo>=historyPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${historyPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${historyPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${historyPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
</div>
