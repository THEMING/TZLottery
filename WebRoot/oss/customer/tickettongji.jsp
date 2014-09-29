<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../skin/02/taglib.jsp"%>
<head>
<title>拆票数据显示</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
function subm(cmd,strId) {
    alert("开奖");
    $("#Id").val(strId);
    $("#action").val(cmd);
	$("form").submit();
}
</script>
</head>
<body>
 <div class="tab">
<form  action="/oss/customer/manageTicket.htm" method="post">
<table width="90%">
<caption class="redbold">拆票数据</caption>
  <tr>
    <td  height="25">彩种: <s:select list="type" name="f_type" id="f_type" /></td>
   <td  >出票状态:<select name="f_statu" id="f_statu">
     <s:if test="f_statu.length()>0"><option value="${f_statu}" selected="selected">${f_statu }</option></s:if>
          <option value="">请选择..</option>
          <s:iterator id="rs" value="status" >
           <option value="${rs}" >${rs}</option>
          </s:iterator>
    </select></td>
	<td  >玩法类型:<select name="f_playType" id="f_playType">
     <s:if test="f_playType.length()>0"><option value="${f_playType}" selected="selected">${f_playType }</option></s:if>
          <option value="">请选择..</option>
          <s:iterator id="rs" value="playType" >
           <option value="${rs.nick_ne}" >${rs.nick_ne}</option>
          </s:iterator>
    </select></td>
  </tr>
  <tr>
    <td height="25" >期次: <input type="text" name="f_term" value="${f_term }" /></td>
    <td >方案号: <input type="text" name="f_numberNo" value="${f_numberNo }" />
    </td>
    <td  height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" value="查询" />
	  </div></td>
  </tr>
</table>
<br>
<table >
  <tr><!--（成功，失败）-->
    <td height="25"><div align="center">彩种</div></td>
     <td height="25"><div align="center">期次</div></td>
    <td height="25"><div align="center">选号方式</div></td>
    <td height="25"><div align="center">票内容</div></td>
    <td height="25"><div align="center">金额</div></td>
    <td height="25"><div align="center">单注金额</div></td>
    <td height="25"><div align="center">倍数</div></td>
    <td height="25"><div align="center">注数</div></td>
    <td height="25"><div align="center">票状态</div></td>
    <td height="25"><div align="center">送票时间</div></td>
    <td height="25" ><div align="center">返回结果</div></td>
    <td height="25" ><div align="center">方案号</div></td>
     <td height="25" ><div align="center">第三方流水号</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
	  <tr>
      <td height="25" >${rs.type}</td>
       <td height="25" >${rs.termNo}</td>
      <td height="25" >${rs.playType.nick_ne}</td>
      <td height="25" >${rs.content}</td>
      <td height="25" ><strong class="red">${rs.money}</strong></td>
      <td height="25" ><strong class="red">${rs.oneMoney}</strong> </td>
      <td height="25" >${rs.multiple }</td>
      <td height="25" >${rs.count}</td>
      <td height="25" >${rs.status}</td>
      <td height="25" ><s:date name="#rs.sendTicketTime" format="yyyy-MM-dd HH:mm:ss"/></td>
      <td height="25" >${rs.otherMsg}</td>
      <td height="25" >${rs.item.plan.numberNo}</td>
      <td height="25" >${rs.otherOrderID}</td>
      </tr>
    </s:iterator>
</table>
  <table width="90%" border="0" align="center">
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
</form>
</div>

</body>
