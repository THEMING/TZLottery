<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>用户中心管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
	var hidden = true;
	function add()
	{
		if(hidden) {
			$("#addNew").attr("class", "show");
			$("#add").text("取消添加");
			hidden = false;
		}
		else {
			$("#addNew").attr("class", "hidden");
			$("#add").text("添加伙伴");
			hidden = true;
		}
	}
	
	function addNew()
	{
		$("#action_id").val("addNew");
		$("#form_id").submit();
	}
	
	function operate(id, value)
	{
		$("#action_id").val("operate");
		$("#pid").val(id);
		$("#active_id").val(value);
		$("#form_id").submit();
	}
	
	function deleteP(id)
	{
		$("#action_id").val("delete");
		$("#pid").val(id);
		$("#form_id").submit();
	}
</script>
</head>

<body>
	<div class="tab">
	<span style="color:red">${message }</span>
	<s:form id="form_id" action="managePartner" method="post">
	<s:hidden id="action_id" name="action" value="index" />
	<s:hidden id="pid" name="id" />
	<s:hidden id="active_id" name="active" />
	<table>
		<caption class="redbold">
			<span style="margin-right:30px;font-size:16px; color:red">合作伙伴管理中心</span>
			<a style="color:gray"id="add" href="javascript:add()">添加伙伴</a>
		</caption>
  		<tr>
		    <td height="25"><div align="center">用户名</div></td>
		    <td height="25"><div align="center">用户来源</div></td>
		    <td height="25"><div align="center">联系方式</div></td>
		    <td height="25"><div align="center">最后登陆时间</div></td>
		    <td height="25"><div align="center">状态</div></td>
		    <td height="25"></td>
		    <td></td>
		</tr>
		<s:iterator id="rs" value="page.result">
		<tr>
			<td height="25" ><div align="center">${rs.nickName}</div></td>
			<td height="25" ><div align="center">${rs.userType}</div></td>
			<td height="25" ><div align="center">${rs.phone}</div></td>
			<td height="25" ><div align="center"><s:date name="#rs.lastLoginTime" format="yyyy-MM-dd HH:mm"/></div></td>
			<td height="25" ><div align="center">
				<s:if test="#rs.active==0">未激活</s:if>
				<s:else>激活</s:else></div>
			</td>
			<td height="25"><div align="center">
				<s:if test="#rs.active==0">
					<a href="javascript:operate(${rs.id}, true)">激活</a>
				</s:if>
				<s:else>
					<a href="javascript:operate(${rs.id}, false)">冻结</a>
				</s:else></div>
			</td>
			<td><div align="center"><a href="javascript:deleteP(${rs.id})">删除</a></div></td>
		</tr>
		</s:iterator>
	</table>
	<table border="0" align="center">
    	<tr>
    		<td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    	</tr>
	</table>
	
	<table id="addNew" class="hidden">
		<tr>
		  	<td height="25"><div align="center">用户名</div></td>
		    <td height="25"><div align="center">密码</div></td>
		    <td height="25"><div align="center">用户来源</div></td>
		    <td height="25"><div align="center">联系方式</div></td>
		    <td><br></td>
		</tr>
		<tr>
			<td height="25"><div align="center"><input id="nickName" name="nickName" style="width:180px" type="text"/></div></td>
		    <td height="25"><div align="center"><input id="password" name="password" style="width:180px" type="text"/></div></td>
		    <td height="25">
		    	<div align="center"><s:select list="userTypes" name="userType" headerKey="" headerValue="请选择.."/></div>
		    </td>
		    <td height="25"><div align="center"><input id="phone" name="phone" type="text" style="width:180px"/></div></td>
		    <td><div align="center"><input type="button" value="确 定" onclick="addNew()"/></div></td>
		</tr>
	</table>
</s:form>
</div>
</body>
