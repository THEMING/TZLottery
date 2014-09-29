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
		str += "<td align='center'><input type='text' id='ratio1'/></td>";
		/*
		str += "<td align='center'><input type='checkbox' id='open2'/></td>";
		str += "<td align='center'><input type='text' id='ratio2'/></td>";
		*/
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
		//$("#open2_id").val($("#open2").attr("checked"));
		$("#r1").val($("#ratio1").val());
		//$("#r2").val($("#ratio2").val());
		$("form").submit();
	}
	function amend(num)
	{
		$("#r1").val($("#ration1_"+num).val());
		//$("#r2").val($("#ration2_"+num).val());
		$("#schemeName").val($("#name_"+num).html());
		$("#action_id").val("amend");
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
<!-- 
<input type="hidden" id="open2_id" name="open2" value="false"/>
 -->
<input type="hidden" id="schemeName" name="schemeName" />
<input type="hidden" id="r1" name="ratio1" />
<!-- 
<input type="hidden" id="r2" name="ratio2" />
-->
<table id="tb" width="90%">
	<caption class="redbold">提成方案列表</caption>
	<tr>
		<td colspan="4"></td><td ><a href="javascript:addRow()">添加</a></td>
	</tr>
	<tr id="title">
    	<td height="25"><div align="center">方案名</div></td>
     	<td height="25"><div align="center">开启提成</div></td>
    	<td height="25"><div align="center">提成比例</div></td>
    	<!-- 
    	<td height="25"><div align="center">开启二级提成</div></td>
    	<td height="25"><div align="center">二级提成比例</div></td>
    	 -->
    	<td height="25"><div align="center"></div></td>
    	<td height="25"><div align="center"></div></td>
  	</tr>
  	<s:set name="no" value="0"></s:set>
  	<s:iterator id="rs" value="page.result" status="st">
		<tr id="row<s:property value="t.index+1"/>">
      		<td height="25" align="center" id="name_${st.index}">${rs.name}</td>
			<td height="25" align="center">${rs.open1}</td>
      		<td height="25" align="center"><input type="text" value="${rs.ratio1}" id="ration1_${st.index}" /></td>
      		<!-- 
      		<td height="25" align="center">${rs.open2}</td>
      		<td height="25" align="center"><input type="text" value="${rs.ratio2}" id="ration2_${st.index}" /></td>
      		 -->
      		<td height="25" align="center">暂停</td>
      		<s:set name="no" value="#st.index"></s:set>
      		<td height="25" align="center"><input type="button" value="修改" onclick="amend(<s:property value='#no' />);"></td>
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
