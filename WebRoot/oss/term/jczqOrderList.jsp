<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>竟彩兑奖管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
</head>
<body>
 <div class="tab">

<s:form  action="manageSendOrder" method="post" namespace="/oss/term">
<s:hidden name="action" id="action" value="index"/>
<s:hidden name="orderId" id="orderId"/>
<s:hidden name="termId" id="termId"/>
<s:hidden name="type" id="type"/>
<s:hidden name="f_customer" id="f_customer"/>
<h1 class="redbold">注： 共${page.totalCount}条记录 , 中奖金额累计为：${sumBigDecimal } 元</h1>
<table>
	<tr>
	  <td height="25"><div align="center">用户名</div></td>
	  <td height="25"><div align="center">开奖</div></td>
	  <td height="25"><div align="center">中奖金额</div></td>
	  <td height="25"><div align="center">方案号</div></td>
	</tr>
	<s:iterator id="order" value="page.result">
	<tr>
      <td height="25" >${order.customer.nickName}</td>
      <td height="25" >${order.orderResult}</td>
      <td height="25" >${order.winMoney }</td>
      <td height="25" >
      	<s:if test="#order.community!=null"><a href="/oss/customer/manageGroupBuyQuery.aspx?action=detail&numberNo=${order.plan.numberNo }">${order.plan.numberNo }</a></s:if>
      	<s:if test="#order.chaseItem!=null"><a href="/oss/customer/manageChaseItemQuery.aspx?action=detail&itemId=${order.chaseItem.id}">${order.plan.numberNo}</a></s:if>
		<s:if test="#order.customer!=null"><a href="/oss/customer/manageBuyLotteryQuery.aspx?action=detail&numberNo=${order.plan.numberNo }">${order.plan.numberNo }</a></s:if>
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
