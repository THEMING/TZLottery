<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>提成方案管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<link href="../styles/base.css" rel="stylesheet" type="text/css"/>
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script>
	var newRow = false;
	function addRow() {
		if(newRow) {
			return;
		}
		str = "<tr id='new'><td align='center'><input type='text' name='name' id='name'/></td>"
		str += "<td align='center'><input type='checkbox' id='open1'/></td>"
		str += "<td align='center'><input type='text' name='ratio1' id='name'/></td>";
		str += "<td align='center'><input type='checkbox' id='open2'/></td>";
		str += "<td align='center'><input type='text' name='ratio2' id='name'/></td>";
		str += "<td align='center'><a href='javascript:remove()'>取消</a></td>";
		str += "<td align='center'><a href='javascript:save()'>保存</a></td></tr>";
		$('#tb').append(str);
		newRow = true;  
   }  
   
	function remove()
	{
		var id = $("#tb>tr:last").attr("id");
		$("#new").remove();
		newRow = false;
	}
	
	function save()
	{
		$("#action_id").val("save");
		$("#open1_id").val($("#open1").attr("checked"));
		$("#open2_id").val($("#open2").attr("checked"));
		alert($("#action_id").val());
		$("form").submit();
	}
</script>
</head>
<body>
<div class="tab">
<span style="color:red">${message}</span>
<form action="/oss/customer/manageCusCommission.aspx" method="post" >
<s:hidden id="action_id" name="action" value="index" />
<input type="hidden" id="open1_id" name="open1" value="false"/>
<input type="hidden" id="open2_id" name="open2" value="false"/>
<table id="tb" width="90%">
	<caption class="redbold">提成方案列表</caption>
	<tr>
		<td colspan="6"></td><td ><a href="javascript:addRow()">添加</a></td>
	</tr>
	<tr id="title">
    	<td height="25"><div align="center">方案名</div></td>
     	<td height="25"><div align="center">开启一级提成</div></td>
    	<td height="25"><div align="center">一级提成比例</div></td>
    	<td height="25"><div align="center">开启二级提成</div></td>
    	<td height="25"><div align="center">二级提成比例</div></td>
    	<td height="25"><div align="center"></div></td>
    	<td height="25"><div align="center"></div></td>
  	</tr>
  	<s:iterator id="rs" value="page.result" status="st">
		<tr id="row<s:property value="t.index+1"/>">
      		<td height="25" align="center">${rs.name}</td>
			<td height="25" align="center">${rs.open1}</td>
      		<td height="25" align="center">${rs.ratio1}</td>
      		<td height="25" align="center">${rs.open2}</td>
      		<td height="25" align="center">${rs.ratio2}</td>
      		<td height="25" align="center">暂停</td>
      		<td height="25" align="center">修改</td>
      	</tr>
    </s:iterator>
</table>
<table width="90%" border="0" align="center">
	<tr>
		<td><jsp:include page="../util/page.jsp"></jsp:include></td>
	</tr>
</table>
</form>
</div>

</body>
