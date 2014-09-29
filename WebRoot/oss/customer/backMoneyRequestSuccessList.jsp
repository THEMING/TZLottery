<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>提款审核管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>


jQuery(document).ready(function() {
	if ($("#message").val() != "" && $("#message").val() != null) {
		alert($("#message").val());
		
	}
});

function subm(cmd,strId) {
    $("#bmid").val(strId);
    $("#action").val(cmd);
	$("form").submit();
}
</script>
</head>
<body>
 <div class="tab">
 <input type="hidden" id="message" value="${message }"/> 
<s:form  action="manageWithdrawMoneySuccess" method="post" >
<s:hidden name="action" id="action" value="index"></s:hidden>
<s:hidden name="bmid" id="bmid"/>
<table width="60%">
<caption class="redbold">提款审核管理</caption>
  <tr>
    <td  height="25" ><div align="center">
          用户名:<input type="text" name="f_nikname" value="${f_nikname }" /></div></td>
    <td  >真实姓名：<input type="text" name="f_raelname" value="${f_raelname }" />
    </td>
    <td><select name="f_timeName" id="f_timeName">
     <s:if test="f_timeName.length()>0"><option value="${f_timeName}" selected="selected">${f_timeName }</option></s:if>
          <option value="">请选择..</option>
          <option value="申请时间">申请时间</option>
          <option value="返款时间">返款时间</option>
    </select>:<input type="text" name="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
	<td width="150" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" value="查询" />
	  </div></td>
  </tr>
  <tr>
    <td height="25" >充值渠道: <select name="f_bank" id="f_bank">
    <s:if test="f_bank.length()>0"><option value="${f_bank}" selected="selected">${f_bank}</option></s:if>
    <option value="" >请选择..</option>
    <s:iterator id="rs" value="banks" >
     <option value="${rs}" >${rs}</option>
      </s:iterator>
    </select></td>
    <td >银行卡号: <input type="text" name="f_bankCard" value="${f_bankCard }" />
    </td>
    <td  >支行:<input type="text" name="f_openSpace" value="${f_openSpace }" /></td>
    <td  >审核条件:<select name="f_statu" id="f_statu">
     <s:if test="f_statu.length()>0"><option value="${f_statu}" selected="selected">${f_statu }</option></s:if>
          <option value="">请选择..</option>
          <s:iterator id="rs" value="status" >
           <option value="${rs}" >${rs}</option>
          </s:iterator>
    </select></td>
  </tr>
</table>

<br>

<table >
  <tr><!--（成功，失败）-->
    <td height="25"><div align="center">提款时间</div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">姓名</div></td>
    <td height="25"><div align="center">账号</div></td>
    <td height="25"><div align="center">所属银行</div></td>
    <td height="25"><div align="center">开户行</div></td>
    <td height="25"><div align="center">提款金额</div></td>
    <td height="25"><div align="center">状态</div></td>
    <td height="25"><div align="center">操作人</div></td>
    <td height="25"><div align="center">操作时间</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
	  <tr>
      <td height="25" ><s:date name="#rs.applyTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" ><a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }">${rs.customer.nickName}</a></td>
      <td height="25" >${rs.realName}</td>
      <td height="25" >${rs.bankCard}</td>
      <td height="25" >${rs.bank} </td>
      <td height="25" >${rs.openSpace }</td>
      <td height="25" >${rs.money}</td>
      <td height="25" >${rs.status}</td>
      <td height="25" >${rs.user.adminName}</td>
      <td height="25" ><s:date name="#rs.sendTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" >
       <a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }" target="blank">|审核|</a>
      <c:if test="${rs.status=='二级审核'}">
        <input type="button" value="成功" onclick="subm('option',${rs.id});"><input type="button" value="取消" onclick="subm('exit',${rs.id});"></input>　
      </c:if>
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
