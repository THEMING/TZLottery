<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>出票商管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function dosub(num) 
{
	$("#rowId").val(num);
	$("#name").val($("#name_"+num).val());
	$("#status").val($("#status_"+num).val());
	$("#action").val("change");
	$("form").submit();
}
function sub()
{
	$("#action").val("index");
	$("form").submit();
}
</script>
</head>
<body>
<div class="tab">
<s:form  id="form_type" action="supplierManager" method="post" namespace="/oss/term">
<s:hidden name="action" id="action"/>
<s:hidden name="name" id="name"/>
<s:hidden name="status" id="status"/>
<s:hidden name="id" id="rowId"/>
  		<select name="type" id="type">
  		<s:if test="type"><option value="${type }" selected="selected">${type }</option></s:if>
  				<option>全部</option>
  				<option>双色球</option>
  				<option>大乐透</option>
  				<option>福彩3d</option>
  				<option>排列三</option>
  				<option>排列五</option>		
  				<option>重庆时时彩</option>
  				<option>上海时时乐</option>
  				<option>七星彩</option>
  				<option>七乐彩</option>
  				<option>足彩14场</option>
  				<option>足彩任9</option>
  				<option>竞彩足球</option>
  				<option>四场进球</option>
  				<option>足彩6场半</option>
  				<option>竞彩篮球</option>
  		</select>
  		<input type="button" value="查询" onclick="sub();"></input>
  		<br/>
  		<br/>
<table>
	<tr>
	    <td>彩种</td>
	    <td>优先级</td>
		<td>出票商的名称</td>
		<td>状态</td>
		<td>修改人</td>
		<td>备注</td>
		<td>操作</td>
	</tr>
	<s:iterator value="supplierList" id="rs">
		<tr>
			<input type="hidden" value="${rs.id }" id="id_${rs.id }"></input>
			<td id="curType">
				<s:if test="#rs.type == 1">
					双色球
				</s:if>
				<s:elseif test="#rs.type == 2">
					大乐透
				</s:elseif>
				<s:elseif test="#rs.type == 3">
					福彩3d
				</s:elseif>
				<s:elseif test="#rs.type == 4">
					排列三
				</s:elseif>
				<s:elseif test="#rs.type == 5">
					排列五
				</s:elseif>
				<s:elseif test="#rs.type == 6">
					重庆时时彩
				</s:elseif>
				<s:elseif test="#rs.type == 7">
					上海时时乐
				</s:elseif>
				<s:elseif test="#rs.type == 8">
					七星彩
				</s:elseif>
				<s:elseif test="#rs.type == 9">
					七乐彩
				</s:elseif>
				<s:elseif test="#rs.type == 10">
					足彩14场
				</s:elseif>
				<s:elseif test="#rs.type == 11">
					足彩任9
				</s:elseif>
				<s:elseif test="#rs.type == 12">
					竞彩足球
				</s:elseif>
				<s:elseif test="#rs.type == 13">
					四场进球
				</s:elseif>
				<s:elseif test="#rs.type == 14">
					足彩6场半
				</s:elseif>
				<s:elseif test="#rs.type == 15">
					竞彩篮球
				</s:elseif>
			</td>
			<td>${rs.priority }</td>
			<td>
				<select name="na" id="name_${rs.id }">
					<s:if test="#rs.name==\"我中啦\"">
					    <option value="我中啦" selected="selected">我中啦</option>
					</s:if>
					<s:elseif test="#rs.name!=\"我中啦\"">
					    <option value="我中啦">我中啦</option>
					</s:elseif>
					
					<s:if test="#rs.name==\"大赢家\"">
					    <option value="大赢家" selected="selected">大赢家</option>
					</s:if>
					<s:elseif test="#rs.name!=\"大赢家\"">
					    <option value="大赢家">大赢家</option>
					</s:elseif>
				</select>
			</td>
			<td>
			<select name="sta" id="status_${rs.id }">
			<s:if test="#rs.status == 0">
			   <option selected="selected" value="无效">无效</option>
			   <option value="可以正常出票">可以正常出票</option>
  			   <option value="不能出票">不能出票</option>
			</s:if>
			<s:elseif test="#rs.status == 1">
			   <option  value="无效">无效</option>
			   <option selected="selected" value="可以正常出票">可以正常出票</option>
  			   <option value="不能出票">不能出票</option>
			</s:elseif>
			<s:elseif test="#rs.status == 2">
			   <option value="无效">无效</option>
			   <option value="可以正常出票">可以正常出票</option>
  			   <option selected="selected" value="不能出票">不能出票</option>
			</s:elseif>
  			</select>
			</td>
			<td>${rs.changeby }</td>
			<td>${rs.memo }</td>
			<td><input type="button" value="修改" onclick="dosub(${rs.id });"></input></td>
		</tr>
	</s:iterator>
</table>
</s:form>
</div>

</body>
