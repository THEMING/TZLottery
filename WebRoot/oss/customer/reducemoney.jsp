<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<html>
	<head>
		<title>手动充值</title>
		<meta name="heading" content="手动充值" />
		<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
	</head>
	<body>
		<form action="manageHandReduce.aspx" method="post" >
		<input type="hidden"  name="action" value="reduce"/>
		<table>
			<tr>
				<td colspan="3" align="center"><label><s:property value="type"/>手动扣款</label><br>
					<label style=""><s:actionmessage/></label>
				</td>
			</tr>
			<tr>
				<td>
				    <div id="yhm">
					用户名:<input type="text" name="cusname" value="${cusname }" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
				   <div id="czje" >
					扣除可用金额:<input type="text" name="deposit_money"  onkeyup="value=value.replace(/[^\d]/g,'');"/>
				   </div>
				</td>

			</tr>
			<tr>
				<td>
				   <div id="czje" >
					扣除冻结金额:<input type="text" name="freeMoney"  onkeyup="value=value.replace(/[^\d]/g,'');"/>
				   </div>
				</td>

			</tr>
			<tr>
				<td>
					<input type="submit"  value="确定(扣款)" />
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>