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
	ÿҳ${mappingPage.pageSize}�� ��${mappingPage.totalCount}����¼ ��${mappingPage.pageNo}/${mappingPage.totalPages}ҳ   
	<s:if test="mappingPage.pageNo==1">
		<span class="disabled">��ҳ</span> 
		<span class="disabled">ǰһҳ</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">��ҳ</a>
		<a href="javascript:jumpPage(${mappingPage.pageNo-1})" class="cr_link">ǰһҳ</a>
	</s:else>
	
	<s:if test="mappingPage.pageNo>=mappingPage.totalPages">
		<span class="disabled">��һҳ</span> 
		<span class="disabled">ĩҳ</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${mappingPage.pageNo+1})" class="cr_link">��һҳ</a>
		<a href="javascript:jumpPage(${mappingPage.totalPages})" class="cr_link">ĩҳ</a>
	</s:else>
	ת ��<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${mappingPage.pageNo}" />ҳ  
	<input type="button" value="��ת" onclick="jumpPage1();"/>
</div>
