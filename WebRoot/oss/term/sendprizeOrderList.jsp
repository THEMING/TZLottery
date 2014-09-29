<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>兑奖管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function doSubmit(cmd,strId) {
     if(!confirm('确实兑奖')){
        return false;
      }
    $("#orderId").val(strId);
    $("#action").val(cmd);
	$("form").submit();
}
</script>
</head>
<body>
 <div class="tab">
<s:form  action="manageSendOrder" method="post" namespace="/oss/term">
<s:hidden name="termId" id="termId"/>
<table width="60%">
<caption class="redbold">兑奖管理</caption>
<caption class="redbold"><s:actionmessage/></caption>
  <tr>
    <td width="120" height="25" ><div align="center">
   彩种: <s:select list="types" name="type" id="type"/></div></td>
     <td width="220" height="25">期次：<input type="text" name="f_term" id=""/></td>
     <td width="220" height="25">客户名：<input type="text" name="f_customer" /></td>
     <td width="150" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" name="submit" value="查询" /> <input type="button" name="back" onclick="javascript:history.back();" value="返回" />
	  </div></td>
  </tr>
</table>
</s:form>
<br>

<s:form  action="manageSendOrder" method="post" namespace="/oss/term">
<s:hidden name="action" id="action" value="index"/>
<s:hidden name="orderId" id="orderId"/>
<s:hidden name="termId" id="termId"/>
<s:hidden name="type" id="type"/>
<s:hidden name="f_customer" id="f_customer"/>
<h1 class="redbold">注：以下为可开奖期次（没有开奖号码不可开奖）<font color="red">已中奖统计金额：${sumWinMoney}元</font></h1>
<table>
	<tr>
	  <td height="25"><div align="center">用户名</div></td>
	  <td height="25"><div align="center">彩种</div></td>
	  <td height="25"><div align="center">期次</div></td>
	  <td height="25"><div align="center">开奖</div></td>
	  <td height="25"><div align="center">中奖金额</div></td>
	  <td height="25"><div align="center">税后金额</div></td>
	  <td height="25"><div align="center">方案号</div></td>
	  <td height="25"><div align="center">操作</div></td>
	</tr>
	<s:iterator id="order" value="page.result">
	<tr>
      <td height="25" >${order.customer.nickName}</td>
      <td height="25" >${order.type}</td>
      <td height="25" >${order.term.termNo}</td>
      <td height="25" >${order.orderResult}</td>
      <td height="25" >${order.winMoney }</td>
      <td height="25" >${order.winTaxMoney }</td>
      <td height="25" >
      	<s:if test="#order.community!=null"><a href="/oss/customer/manageGroupBuyQuery.aspx?action=detail&numberNo=${order.plan.numberNo }">${order.plan.numberNo }</a></s:if>
      	<s:if test="#order.chaseItem!=null"><a href="/oss/customer/manageChaseItemQuery.aspx?action=detail&itemId=${order.chaseItem.id}">${order.plan.numberNo}</a></s:if>
		<s:if test="#order.customer!=null"><a href="/oss/customer/manageBuyLotteryQuery.aspx?action=detail&numberNo=${order.plan.numberNo }">${order.plan.numberNo }</a></s:if>
     </td>
      <td height="25" >
      	<c:if test="${order.orderResult=='已中奖'}">
      		<input type="button" value="兑奖" onclick="doSubmit('sendWin',${order.id});"/>
      	</c:if>
      	<c:if test="${order.orderResult=='已兑奖'}">已兑奖</c:if>
      </td>
      </tr>
      </s:iterator>
</table>
 <table width="90%" border="0" align="center">
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
</s:form>
</div>

</body>
