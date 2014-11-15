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
		<script type="text/javascript">
		function common(){
		    if($("#status").val()!="手动补单" && $("#status").val()!="测试"){
		       $("#dd").show();
		        $("#yhm").hide();
		         $("#zsxm").hide();
		          $("#czje").hide();
		    }else{
		       $("#dd").hide();
		        $("#yhm").show();
		         $("#zsxm").show();
		          $("#czje").show();
		    }
		}
		</script>
	</head>
	<body>
		<s:form action="manageHandAdd" method="post" >
		<s:hidden name="action" value="chongz"/>
		<table>
			<tr>
				<td colspan="3" align="center"><label><s:property value="type"/>手动充值</label><br>
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
				<div id="zsxm">
					真实姓名:<input type="text" name="name" value="${name }" />
				</div>
				</td>
			</tr>
			<tr>
				<td>
					充值渠道:
					<select name="status" id="status" onchange="common();">
                          <option value="手动补单" >手动补单</option>
                          <option value="测试" >测试</option>
                          <option value="易宝" >易宝</option>
                          <option value="支付宝" >支付宝</option>
                      </select> 
				</td>
			</tr>
			<tr>
				<td>
				<div id="dd" style="display:none;">
					订单号:<input type="text" name="serialNumber" id="serialNumber" value="${serialNumber }" />
				</div>
				</td>
			</tr>
			<tr>
				<td>
				   <div id="czje" >
					充值金额:<input type="text" name="deposit_money" value="${deposit_money }" onkeyup="value=value.replace(/[^\d]/g,'');"/>
				   </div>
				</td>

			</tr>
			<tr>
				<td>
					管理员冲值密钥:<input type="password" name="pwd" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit"  value="确定(充值)" />
				</td>
			</tr>
		</table>
		</s:form>
	</body>
</html>