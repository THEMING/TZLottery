<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>提款查询</title>
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
<h3><strong>提款查询</strong></h3>
	<form action="tikuanChaxun.htm" method="post" >
	<input type="hidden" id="action" name="action" value="tikuanChaxun">
	<br/>
	
	时间:<select name="f_timeName" id="f_timeName">
     <s:if test="f_timeName.length()>0"><option value="${f_timeName}" selected="selected">${f_timeName }</option></s:if>
          <option value="申请时间">申请时间</option>
    </select>:<input type="text" name="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    
    审核条件:<select name="f_statu" id="f_statu">
     <s:if test="f_statu.length()>0"><option value="${f_statu}" selected="selected">${f_statu }</option></s:if>
          <option value="">全部</option>
          <s:iterator id="rs" value="status" >
           <option value="${rs}" >${rs}</option>
          </s:iterator>
    </select>
    
	<input type="submit" value="查询" />
		<br/>
		<br/>
		已提款总金额：<font color="red">${sumTikuanNum}</font>(元)  账户余额：<font color="red">${customer.wallet.balance }</font>(元)
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
    <td height="25"><div align="center">提款时间</div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">姓名</div></td>
    <td height="25"><div align="center">账号</div></td>
    <td height="25"><div align="center">所属银行</div></td>
    <td height="25"><div align="center">开户行</div></td>
    <td height="25"><div align="center">提款金额</div></td>
    <td height="25"><div align="center">状态</div></td>
		</tr>
		<s:iterator id="rs" value="backMoneyRequestPage.result" status="st">
	  <tr>
      <td height="25" ><s:date name="#rs.applyTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" >${rs.customer.nickName}</td>
      <td height="25" >${rs.realName}</td>
      <td height="25" >${rs.bankCard}</td>
      <td height="25" >${rs.bank} </td>
      <td height="25" >${rs.openSpace }</td>
      <td height="25" ><strong class="red">￥${rs.money}</strong></td>
      <td height="25" id="stat_<s:property value='#st.index'/>">${rs.status}</td>
      </tr>
    </s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${backMoneyRequestPage.pageSize}条 共${backMoneyRequestPage.totalCount}条记录 第${backMoneyRequestPage.pageNo}/${backMoneyRequestPage.totalPages}页   
	<s:if test="backMoneyRequestPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${backMoneyRequestPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="backMoneyRequestPage.pageNo>=backMoneyRequestPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${backMoneyRequestPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${backMoneyRequestPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${backMoneyRequestPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
