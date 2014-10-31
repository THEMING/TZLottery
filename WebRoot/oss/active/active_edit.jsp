<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<script src="../skin/01/js/jquery-1.3.2.js"></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
		<title>活动信息</title>
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		</style>
	</head>
	<body>
		<label style="font-size:16px">活动信息</label>
		<br/>
		<span style="color:red">${sysmsg }</span>
		<br />
		<form action="/oss/active/activitiesMng.htm" method="post">
		<s:hidden name="action" value="save" />
		<input type="hidden" name="id" value="${id}"/>
		<br />
		<h1>活动名：<input type="text" id="name" name="name" value="${name}"/></h1>
		<br />
		<h1>活动简称：<input type="text" id="shortName" name="shortName" value="${shortName}"/></h1>
		<br />
		<h1>活动类型：<s:select list="activityTypes" name="activityType"/></h1>
		<br />
		<h1>订单状态限制：<s:select list="activityOrderTypes" name="activityOrderType" /></h1>
		<br />
		<h1>购彩额度限制：<input type="text" id="threshold" name="threshold" value="${threshold}"/></h1>
		<br />
		<h1>加奖额度：<input type="text" id="givenMoney" name="givenMoney" value="${givenMoney}"/></h1>
		<br />
		<h1>开始时间： <input type="text" id="startTime" name="startTime" value="${startTime}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></h1>
		<br />
		<h1>结束时间： <input type="text" id="endTime" name="endTime" value="${endTime}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></h1>
		<br/>
		<%--<h1>关联的代理商： <s:select list="partnerList" name="partner.id" listKey="id" listValue="nickName" value="partner.id" headerValue="请选择..." headerKey=""/></h1>
		
		<br>
		--%><h1>是否公开： <input type="checkbox" id="isPublic" name="isPublic" value=<s:if test="activity.isPublic">true</s:if><s:else>false</s:else> onchange="oncheck(this)" <s:if test="activity.isPublic">checked</s:if> />
	    		<input hidden="" id="partnerIdss" name="partnerIdss"></h1>
	    <br>
	    <div id="partnerDiv" <s:if test="activity.isPublic">hidden</s:if> >
		<div style="width:670px;" class="tab">
	    			<table>
	    			<tr>
	    				<td colspan="2" align="center">
	    					<font color="red">请选择关联的代理商</font>
	    				</td>
	    			</tr>
	    				<tr>
	    				<td><div align="center"><input type="button" value="全选" onclick="doSelectAll();" id="selectall"></input></div></td>
	    					<td>
	    						代理商名称
	    					</td>
	    				</tr>
	    			</table>
	    		</div>
	    		<div style="width:670px;height: 100px;overflow-y:scroll;" class="tab">
	    			<table>
	    				<s:iterator id="rs" value="partnerList">
				  			<tr>
				  			<td>
				  			<s:if test="mapPartnerSelected[#rs.id]>0">
					  			<input name="partnerId" type="checkbox" checked="checked" id="partnerId_${rs.id}" value="${rs.id}"/>
			                 </s:if>
			                 <s:else>
			                 <input name="partnerId" type="checkbox" id="partnerId_${rs.id}" value="${rs.id}"/>
			                 </s:else>
				  			</td>
				  				<td align="center">${rs.nickName}</td>
				  			</tr>
				  		</s:iterator>
				  		
				  		
				  		
	    			</table>
	    		</div>
		</div>
		
		<br/>
		<h1>图片： <s:select onchange="loadPicture(this)" list="articleList" name="article.id" listKey="id" listValue="title" value="article.id" headerValue="请选择..." headerKey=""/></h1>
		<br/>
		<div id="showPictureArea" style="height: 400px;width:670px;">
		${activity.article.content}
		</div>
		<br/>
		<h1><input type="submit" value="确定" onclick="return check()"/></h1>
		<br/>
		</form>
	</body>
</html>

<script>
	function loadPicture(obj){
		
		$.ajax({
			type:"post",
			url: "/oss/picture/pictureManage.htm?action=getPicture",
			data:{id:obj.value},
			success: function(data, textStatus){
			var a=eval("("+data+")");
		$("#showPictureArea").html(a.content);
			 	//$("#"+id+"_message").html("<font style='color:red'>"+a.message+"</font>");
		}})
	}

	function doSelectAll()
	{
		str = $("#selectall").val();
		if(str == '全选')
		{
			$("#selectall").val("取消");
			$("[name=partnerId]:checkbox").attr("checked",true);
		}
		if(str == '取消')
		{
			$("#selectall").val("全选");
			$("[name=partnerId]:checkbox").attr("checked",false);
		}

	}
	
	function oncheck(obj){
		$(obj).val($(obj).attr("checked"));
		
		if(obj.id=="isPublic"&&$(obj).attr("checked")==false){
			$("#partnerDiv").show();
		}else if(obj.id=="isPublic"&&$(obj).attr("checked")==true){
			$("#partnerDiv").hide();
		}
	}

	function check()
	{
		if($("#name").val() == "") {
			alert("请输入活动名");
			$("#name").focus();
			return false;
		}
		
		if($("#shortName").val() == "") {
			alert("请输入活动简称");
			$("#shortName").focus();
			return false;
		}
		
		if($("#threshold").val() == "") {
			alert("请输入采购额度限制");
			$("#threshold").focus();
			return false;
		}
		
		if(!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test($("#threshold").val())) {
			alert("采购额度限制输入有误");
			$("#threshold").focus();
			return false;
		}
		
		if($("#givenMoney").val() == "") {
			alert("请输入加奖额度");
			$("#givenMoney").focus();
			return false;
		}
		
		if(!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test($("#givenMoney").val())) {
			alert("加奖额度输入有误");
			$("#givenMoney").focus();
			return false;
		}
		
		if($("#startTime").val() == "") {
			alert("请输入活动开始时间");
			$("#startTime").focus();
			return false;
		}
		
		if($("#endTime").val() == "") {
			alert("请输入活动结束时间");
			$("#endTime").focus();
			return false;
		}
		
		if($("#isPublic").attr("checked")==false){
			var partnerIds = $('input[name="partnerId"]:checked').val([]);
			var partnerIdss = "";
			if(partnerIds.length>0){
				partnerIdss = $("#partnerId_"+partnerIds[0].value).val();
			}
			
			for(var i=1;i<partnerIds.length;i++){
				partnerIdss += ","+$("#partnerId_"+partnerIds[i].value).val();
			}
			
			$("#partnerIdss").val(partnerIdss);
		}
		
		return true;
	}
</script>