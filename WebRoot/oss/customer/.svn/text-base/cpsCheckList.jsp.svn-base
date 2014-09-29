<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>CPS审核管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
	var pageNo;
	$("document").ready(function(){
		pageNo = "${pageNo}";
		$("#check_all").toggle(function(){
			$("[name='id']").attr("checked",'true');
		},
		function(){
			$("[name='id']").removeAttr("checked");
		})
	});
	
	function operate(id, status)
	{
		var url = "manageCpsCheck.htm?action=check&customerId=" + id;
		url += "&statu=" + status;
		if(pageNo != "") {
			url += "&pageNo=" + pageNo;
		}
		this.location.href = url;
	}
	
</script>
</head>
<body>
 <div class="tab">
<s:form  action="manageCpsCheck" method="post">
<s:hidden name="action" id="action" value="index"/>
<table width="60%">
<caption class="redbold">CPS审核管理</caption>
</table>
<br>
<table >
  <tr>
    <td><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">真实姓名</div></td>
    <td height="25"><div align="center">身份证号</div></td>
    <td height="25"><div align="center">联系电话</div></td>
    <td height="25"><div align="center">邮件</div></td>
    <td height="25"><div align="center">QQ</div></td>
    <td height="25"><div align="center">审核状态</div></td>
    <td height="25"><div align="center">申请时间</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
	  <tr>
	  <td><input name="cusId" type="checkbox" id="cusId" value="${user.id}"/></td>
      <td height="25" >${rs.nickName}</td>
      <td height="25" >${rs.channel.realName}</td>
      <td height="25" >${rs.channel.idCardNo}</td>
      <td height="25" >${rs.channel.linkPhone}</td>
      <td height="25" >${rs.channel.email}</td>
      <td height="25" >${rs.channel.QQ}</td>
      <td height="25" >
      <s:if test="#rs.isPass==0">待审</s:if>
      <s:if test="#rs.isPass==1">通过</s:if>
      <s:if test="#rs.isPass==2">未通过</s:if>
      </td>
      <td height="25" ><s:date name="#rs.createTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" ><a href="manageCpsCheck.aspx?action=view&customerId=${rs.id }">审核</a>
      <s:if test="#rs.isPass==0">
      <input type="button" onClick="javascript:operate(${rs.id}, 1)" value="通过">
      <input type="button" onClick="javascript:operate(${rs.id}, 2)" value="取消">
      </s:if>
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
