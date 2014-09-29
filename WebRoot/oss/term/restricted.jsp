<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>限制销售</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>

</script>
</head>
<body>
	<div class="tab">
		<br />
		<s:form id="form_type" action="restricted" method="post" namespace="/oss/term">
			<s:select list="types" name="type">
			</s:select>
			<input type="submit" value="查询"></input>
		</s:form>
		<br />
		<table>
			<tr align="center">
				<td>彩种</td>
				<td>状态</td>
				<td>起始时间</td>
				<td>结束时间</td>
				<td>描述</td>
				<td>操作</td>
			</tr>
			<s:iterator value="list" id="rs">
				<s:form action="restricted" method="post" namespace="/oss/term">
					<s:hidden name="action" value="update"></s:hidden>
					<tr align="center">
						<td><input name="type" type="text" value="${rs.type }" readonly="readonly" /></td>
						<td>
							<s:select list="status" value="#rs.restrictedstatus" name="statue"></s:select>
						</td>
						<td><input type="text" name="startTime" value="<s:date name="#rs.startTime" format="yyyy-MM-dd HH:mm:ss"/>" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
						</td>
						<td><input type="text" name="endTime" value="<s:date name="#rs.endTime" format="yyyy-MM-dd HH:mm:ss"/>" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
						</td>
						<td><input type="text" name="desc" value="${rs.desc }" /></td>
						<td><input type="submit" value="修改"></input></td>
					</tr>
				</s:form>
			</s:iterator>
		</table>
	</div>
</body>
