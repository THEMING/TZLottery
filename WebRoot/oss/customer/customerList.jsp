<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>用户中心管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
	function subm(cmd,strId) {
	    $("#customerId").val(strId);
	    $("#action").val(cmd);
		$("form").submit();
	}
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
	
	function operate(id, status) {
		var url = "manageCustomer.htm?action=dj&customerId=" + id;
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
<s:form  action="manageCustomer" method="post">
<s:hidden name="action" id="action" value="index"/>
<table width="60%">
<caption class="redbold">用户中心管理</caption>
  <tr>
    <td  height="25" ><div align="center">
          查询条件:<select name="f_serch" id="f_serch">
    <s:if test="f_serch.length()>0"><option value="${f_serch}" selected="selected">${f_serch}</option></s:if>
     <option value="" >请选择..</option>
    <option value="用户名" >用户名</option>
    <option value="真实姓名" >真实姓名</option>
    <option value="邮箱" >邮箱</option>
    </select>
    </div></td>
    <td  height="25">查询内容:<input type="text" name="f_serchName" value="${f_serchName }"/>
    </td>
    <td  >注册时间<input type="text" name="f_starTime" value="<s:date name="f_starTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_endTime" value="<s:date name="f_endTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
	<td  height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" value="查询" />
	  </div></td>
  </tr>
   <tr>
    <td  height="25" ><div align="center">
        来源: <s:select id="userType" list="userTypes" name="userType" headerKey="" headerValue="请选择.."/>
    </div></td>
    <td  height="25" bgcolor="#ffffff"><div align="right">
    条件: <select name="f_orderserch" id="f_orderserch">
    <s:if test="f_orderserch.length()>0"><option value="${f_orderserch}" selected="selected">${f_orderserch}</option></s:if>
    <option value="" >请选择..</option>
    <option value="购彩用户" >购彩用户</option>
    <option value="未购彩用户" >未购彩用户</option>
    </select>
	  </div></td>
    <td  height="25" colspan="2">
    时间:<input type="text" name="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
  </tr>
</table>

<br>

<table >
  <tr>
    <td><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">真实姓名</div></td>
    <td height="25"><div align="center">证件号</div></td>
    <td height="25"><div align="center">证件类型</div></td>
    <td height="25"><div align="center">邮箱</div></td>
    <td height="25"><div align="center">手机</div></td>
    <td height="25"><div align="center">资金状态</div></td>
    <td height="25"><div align="center">操作</div></td>
    <td height="25"><div align="center">注册时间</div></td>
    <td height="25"><div align="center">最后登陆时间</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result">
	  <tr>
	  <td><input name="cusId" type="checkbox" id="cusId" value="${user.id}"/></td>
      <td height="25" >${rs.nickName}</td>
      <td height="25" >${rs.realName}</td>
      <td height="25" >${rs.credentNo}</td>
      <td height="25" >${rs.credentType}</td>
      <td height="25" >${rs.email}</td>
      <td height="25" >${rs.mobileNo}</td>
      <td height="25" >
      <s:if test="#rs.wallet.status==0">正常</s:if>
      <s:else>冻结</s:else>
      </td>
      <td height="25" ><a href="manageCustomer.aspx?action=view&customerId=${rs.id }">资</a>|<a href="manageFinanialQuery.aspx?customerId=${rs.id }">账</a></td>
      <td height="25" ><s:date name="#rs.registerTime" format="yyyy-MM-dd HH:mm"/></td>
       <td height="25" ><s:date name="#rs.lastLoginTime" format="yyyy-MM-dd HH:mm"/></td>
       <td height="25" >
       	<s:if test="#rs.wallet.status==0">
       		<a href="javascript:operate(${rs.id}, 1)">冻结</a>
		</s:if>
		<s:else><a href="javascript:operate(${rs.id}, 0)">解冻</a></s:else>
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
