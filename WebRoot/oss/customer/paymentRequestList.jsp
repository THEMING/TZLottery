<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>充值流水管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/lottery.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function tj(){
$.czTongji.callback = function(json){
		$("#inMoney").text(json.inMoney);
		$("#notMoney").text(json.notMoney);
		$("#count").text(json.count);
	};
	$.czTongji._request({f_user:encodeURI($("#f_user").val()),f_name:encodeURI($("#f_name").val()),f_numNo:$("#f_name").val(),f_starTime:$("#f_starTime").val(),f_endTime:$("#f_endTime").val(),f_moneyChannel:encodeURI($("#f_moneyChannel option:selected").val()),f_statu:encodeURI($("#f_statu option:selected").val()),f_userTypes:encodeURI($("#f_userTypes option:selected").val()),f_sTime:$("#f_sTime").val(),f_eTime:$("#f_eTime").val()});
}
</script>
</head>
<body>
 <div class="tab">
<s:form  action="managePaymentReq" method="post" >
<table width="60%">
<caption class="redbold">用户充值流水管理</caption>
  <tr>
    <td  height="25" ><div align="center">
          用户名:<input type="text" name="f_name" id="f_name" value="${f_name }" /></div></td>
    <td  >交易号：<input type="text" name="f_numNo" id="f_numNo" value="${f_numNo }" />
    </td>
    <td  >充值时间:<input type="text" name="f_starTime" id="f_starTime" value="<s:date name="f_starTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_endTime" id="f_endTime" value="<s:date name="f_endTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
    <td width="150" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" value="查询" />
	  </div></td>
  </tr>
  <tr>
    <td height="25" >充值渠道: <select name="f_moneyChannel" id="f_moneyChannel">
    <s:if test="f_moneyChannel.length()>0"><option value="${f_moneyChannel}" selected="selected">${f_moneyChannel}</option></s:if>
    <option value="" >请选择..</option>
    <s:iterator id="rs" value="moneyChannel" >
     <option value="${rs}" >${rs}</option>
      </s:iterator>
    </select></td>
    <td >充值状态: <select name="f_statu" id="f_statu">
           <s:if test="f_statu.length()>0"><option value="${f_statu}" selected="selected"><s:if test="f_statu">成功</s:if>
      <s:else>失败</s:else></option></s:if>
          <option value="">请选择..</option>
          <option value="true">成功</option>
          <option value="false">失败</option>
          </select>
    </td>
    <td  >操作人:<input type="text" name="f_user" id="f_user" value="${f_user }" /></td>
    <td > </td>
  </tr>
   <tr>

	<td width="200" height="25" bgcolor="#ffffff">  来源: <select name="f_userTypes" id="f_userTypes">
    <s:if test="f_userTypes.length()>0"><option value="${f_userTypes}" selected="selected">${f_userTypes}</option></s:if>
     <option value="">请选择..</option>
    <s:iterator id="rs" value="userTypes" >
         <option value="${rs}" >${rs}</option>
    </s:iterator>
    </select></td>
    <td  height="25" colspan="3">注册时间:<input type="text" name="f_sTime" id="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" id="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
  </tr>
</table>

<br>
<table >
  <tr><td colspan="8"><input type="button" value="统计" onclick="tj();"/> 成功充值总额：￥<span id="inMoney">0</span> 元       未成功充值总额：￥<span id="notMoney">0</span>元 　　充值人数：<span id="count">0</span>人</td></tr>
  <tr><!--（成功，失败）-->
    <td height="25"><div align="center">交易号</div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">金额</div></td>
    <td height="25"><div align="center">发起时间</div></td>
    <td height="25"><div align="center">状态</div></td>
    <td height="25"><div align="center">充值渠道</div></td>
    <td height="25"><div align="center">操作时间</div></td>
    <td height="25"><div align="center">操作人</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
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
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
</s:form>
</div>

</body>
