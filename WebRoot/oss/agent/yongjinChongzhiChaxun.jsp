<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@ include file="../skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>佣金充值查询</title>
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
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>佣金充值查询</strong></h3>
	<form action="/oss/agent/yongjinChongzhiChaxun.htm" method="post" >
	<input type="hidden" id="action" name="action" value="yongjinChongzhiChaxun">
	<br/>
	
	充值时间:<input type="text" name="f_starTime" id="f_starTime" value="<s:date name="f_starTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_endTime" id="f_endTime" value="<s:date name="f_endTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    
    充值状态: <select name="f_statu" id="f_statu">
           <s:if test="f_statu.length()>0"><option value="${f_statu}" selected="selected"><s:if test="f_statu">成功</s:if>
      <s:else>失败</s:else></option></s:if>
          <option value="">请选择..</option>
          <option value="true">成功</option>
          <option value="false">失败</option>
          </select>
	<input type="submit" value="查询" />
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
    <td height="25"><div align="center">交易号</div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">金额</div></td>
    <td height="25"><div align="center">发起时间</div></td>
    <td height="25"><div align="center">状态</div></td>
    <td height="25"><div align="center">充值渠道</div></td>
    <td height="25"><div align="center">操作时间</div></td>
    <td height="25"><div align="center">操作人</div></td>
		</tr>
		<s:iterator id="rs" value="paymentRequestPage.result">
	  <tr>
      <td height="25" >${rs.serialNumber}</td>
      <td height="25" ><a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }">${rs.customer.nickName}</a></td>
      <td height="25" >${rs.money}</td>
      <td height="25" ><s:date name="#rs.payTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" >
      <s:if test="#rs.finish"><strong class="red">成功</strong></s:if>
      <s:else>失败</s:else>
      </td>
      <td height="25" >${rs.channel}</td>
      <td height="25" ><s:date name="#rs.optionTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" >${rs.user.adminName}</td>
      </tr>
    </s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${paymentRequestPage.pageSize}条 共${paymentRequestPage.totalCount}条记录 第${paymentRequestPage.pageNo}/${paymentRequestPage.totalPages}页   
	<s:if test="paymentRequestPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${paymentRequestPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="paymentRequestPage.pageNo>=paymentRequestPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${paymentRequestPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${paymentRequestPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${paymentRequestPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
