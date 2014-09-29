<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script>
	function ajumpPage(pageNo) {
		$("#pageNo2").val(pageNo);
		$("form").submit();
	}
	function ajumpPage1() {
		$("#pageNo2").val($("#pageNum2").val());
		$("form").submit();
	}
</script>

<div>
	<input type="hidden" name="pageNo2" id="pageNo2" value="1" />
	每页${page2.pageSize}条 共${page2.totalCount}条记录 第${page2.pageNo}/${page2.totalPages}页   
	<s:if test="page2.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:ajumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:ajumpPage(${page2.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="page2.pageNo>=page2.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:ajumpPage(${page2.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:ajumpPage(${page2.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum2" style="width: 30px" id="pageNum2" value="${page2.pageNo}" />页  
	<input type="button" value="跳转" onclick="ajumpPage1();"/>
</div>
