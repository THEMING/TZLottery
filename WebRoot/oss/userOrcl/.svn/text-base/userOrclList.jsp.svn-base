<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>用户中心管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm(cmd) {
    $("#action").val(cmd);
	$("form").submit();
}
$("document").ready(function(){
     
	$("#check_all").toggle(function(){
                $("[name='sId']").attr("checked",'true');
          },
          function(){

                $("[name='sId']").removeAttr("checked");
	
          })

});
</script>
</head>
<body>
 <div class="tab">
<s:form  action="userOrclMng" method="post">
<s:hidden name="action" id="action" value="index"/>
<table width="60%">
<caption class="redbold">老用户中心管理</caption>
  <tr>
  </tr>
</table>

<br>

<table >
  <tr><td colspan="2"><input type="button" onclick="subm('add');" value="导入" /></td><td colspan="2"><input type="button" onclick="subm('alladd');" value="全部导入" /></td><td colspan="10">注："导入"单个或单个页面数据导入."全部导入"会将所有数据一次全部导入。</td></tr>
  <tr>
    <td><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">真实姓名</div></td>
    <td height="25"><div align="center">证件类型</div></td>
    <td height="25"><div align="center">邮箱</div></td>
    <td height="25"><div align="center">手机</div></td>
    <td height="25"><div align="center">资金状态</div></td>
    <td height="25"><div align="center">余额</div></td>
    <td height="25"><div align="center">优惠</div></td>
    <td height="25"><div align="center">代金</div></td>
    <td height="25"><div align="center">中奖金额</div></td>
    <td height="25"><div align="center">注册时间</div></td>
    <td height="25"><div align="center">最后登陆时间</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
	  <tr>
	  <td><input name="sId" type="checkbox" id="sId" value="${rs.id}"/></td>
      <td height="25" >${rs.nameNick}</td>
      <td height="25" >${rs.nameReal}</td>
      <td height="25" >身份证</td>
      <td height="25" >${rs.emil}</td>
      <td height="25" >${rs.phone}</td>
      <td height="25" >
      <s:if test="#rs.statu==1">正常</s:if>
      <s:else>冻结</s:else>
      </td>
      <td height="25" >￥<fmt:formatNumber value="${rs.blacne/100}"  minFractionDigits="2"/></td>
      <td height="25" >￥<fmt:formatNumber value="${rs.coupon/100}"  minFractionDigits="2"/></td>
      <td height="25" >￥<fmt:formatNumber value="${rs.cash_coupon/100}"  minFractionDigits="2"/></td>
       <td height="25" ><fmt:formatNumber value="${rs.zj/100}"  minFractionDigits="2"/></td>
       <td height="25" >${rs.regDate }</td>
       <td height="25" >${rs.lastDate }</td>
       <td height="25" width="25"><a href="userOrclMng.aspx?action=view&uid=${rs.id }">查看</a></td>
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
