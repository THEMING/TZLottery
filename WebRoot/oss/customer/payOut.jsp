<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>提款审核管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
</script>
</head>
<body>
 <div class="tab">
<form action="/oss/customer/caiwushenpi.htm">
<input type="hidden" name="action" value="searche"/>
<table >
<caption class="redbold">提款财务审核详情</caption>
    <tr>
    <td  height="25" ><div align="center">
          用户名:<input type="text" name="nickname" value="${nickname }" /></div></td>
    <td  >真实姓名：<input type="text" name="realname" value="${realname }" />
    </td>
    <td>时间:<input type="text" name="stime" value="<s:date name="stime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/> -
    		<input type="text" name="etime" value="<s:date name="etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
	<td>
		状态：<select name="processFlag" value="${processFlag }">
				<s:if test="processFlag == 5">
					<option value="${processFlag }" selected="selected">等待初(一)审</option>
				</s:if>
				<s:if test="processFlag == 6">
					<option value="${processFlag }" selected="selected">等待初(二)审</option>
				</s:if>
				<s:if test="processFlag == 8">
					<option value="${processFlag }" selected="selected">等待结算</option>
				</s:if>
				<s:if test="processFlag == 2">
					<option value="${processFlag }" selected="selected">等待银行返回结果</option>
				</s:if>
				<s:if test="processFlag == 4">
					<option value="${processFlag }" selected="selected">锁定结算记录</option>
				</s:if>
				<s:if test="processFlag == 7">
					<option value="${processFlag }" selected="selected">完成结算</option>
				</s:if>
				<s:if test="processFlag == 3">
					<option value="${processFlag }" selected="selected">结算失败</option>
				</s:if>
				<s:if test="processFlag == 9">
					<option value="${processFlag }" selected="selected">拒绝结算</option>
				</s:if>
				<s:if test="processFlag == 10">
					<option value="${processFlag }" selected="selected">退票</option>
				</s:if>
				<option value="0">全部</option>
				<option value="5">等待初(一)审</option>
				<option value="6">等待复(二)审</option>
				<option value="8">等待结算</option>
				<option value="2">等待银行返回结果</option>
				<option value="4">锁定结算记录</option>
				<option value="7">完成结算</option>
				<option value="3">结算失败</option>
				<option value="9">拒绝结算</option>
				<option value="10">退票</option>
			  </select>
	</td>
	<td width="50" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" value="查询"/>
	  </div></td>
  </tr>
</table>

<br>

<table >
  <tr><!--（成功，失败）-->
    <td height="25"><div align="center">提款时间</div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">真实姓名</div></td>
    <td height="25"><div align="center">账号</div></td>
    <td height="25"><div align="center">所属银行</div></td>
    <td height="25"><div align="center">开户行</div></td>
    <td height="25"><div align="center">提款金额</div></td>
    <td height="25"><div align="center">状态描述</div></td>
    <td height="25"><div align="center">操作时间</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
	  <tr>
      <td height="25" ><s:date name="#rs.time" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" >${rs.backMoneyRequest.customer.nickName}</td>
      <td height="25" >${rs.backMoneyRequest.realName}</td>
      <td height="25" >${rs.backMoneyRequest.bankCard}</td>
      <td height="25" >${rs.backMoneyRequest.bank} </td>
      <td height="25" >${rs.backMoneyRequest.openSpace }</td>
      <td height="25" >${rs.money}</td>
      <td height="25" >${rs.stateDesc }</td>
      <td height="25" ><s:date name="#rs.StateTime" format="yyyy-MM-dd HH:mm"/></td>
      </tr>
    </s:iterator>
</table>
  <table width="90%" border="0" align="center">
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
</div>
</form>
</body>
