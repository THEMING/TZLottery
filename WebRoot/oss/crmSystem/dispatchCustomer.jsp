<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>我的客户</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script src="../../util/overlay.jsp" type=text/javascript></script>
<style type="text/css">
	.wrap{margin:0 auto;}
 </style>
<style type="text/css">  
    .mask {    
            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;  
            z-index: 0.5; left: 0px;  
            opacity:0.5; -moz-opacity:0.5;  
        }  
</style>  
<script>
function showMask(){  
    $("#mask").css("height",$(document).height());  
    $("#mask").css("width",$(document).width());  
    $("#mask").show();  
}
function hideMask(){  
    $("#mask").hide();  
}

function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("form").submit();
}
function jumpPage1() {
	$("#pageNo").val($("#pageNum").val());
	$("form").submit();
}

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
	
	
	function closee(sta){
		if(sta==1){
			hideSms();
		}else if(sta==2){
			hideEmail();
		}
		
		$("#addTDiv").hide();
	}
	
	function showSms(){
		$("#smsTDiv").show();
		showMask();
	}
	
	function hideSms(){
		$("#smsTDiv").hide();
		hideMask();
	}
	
	function showEmail(){
		$("#emailTDiv").show();
		showMask();
	}
	
	function hideEmail(){
		$("#emailTDiv").hide();
		hideMask();
	}
	
	function setSmsContent(obj){
		$.ajax({
			type:"post",
			url: "CRMManage.htm?action=getTemplate",
			data:{templateId:obj.value},
			success: function(data, textStatus){
			var a=eval("("+data+")");
			$("#smsContent").val(a.content);
		}})
		
	}
	
	function setEmsilContent(obj){
		$.ajax({
			type:"post",
			url: "CRMManage.htm?action=getTemplate",
			data:{templateId:obj.value},
			success: function(data, textStatus){
			var a=eval("("+data+")");
			$("#emailContent").val(a.content);
			$("#emailTitle").val(a.title);
		}})
		
	}
	
	function doSelectAll()
	{
		str = $("#selectall").val();
		if(str == '全选')
		{
			$("#selectall").val("取消");
			$("[name=cusId]:checkbox").attr("checked",true);
		}
		if(str == '取消')
		{
			$("#selectall").val("全选");
			$("[name=cusId]:checkbox").attr("checked",false);
		}

	}
	
	function checkIsFindCustomer(inputId){
		if($("#"+inputId).val()==""){
			return;
		}
		$.ajax({
			type:"post",
			url: "CRMManage.htm?action=cheackAdminUser",
			data:{adminUserCheck:$("#"+inputId).val()},
			success: function(data, textStatus){
			var a=eval("("+data+")");
		
			if(a.message=="业务员不存在")
			{
			 	$("#"+inputId+"Span").html("<font style='color:red'>"+a.message+"</font>")
			}else{
				$("#"+inputId+"Span").html("")
			}
		}})
	}
	
	function dispath(inputId){
		if($("#"+inputId).val()==""){
			alert("请输入业务员账号");
			return;
		}
		
		var obj = new Object();
		
		if(inputId=="adminUserAll"){
			obj = $("#queryForm").serialize();	
			obj = obj+"&oper="+inputId+"&adminUserCheck="+$("#"+inputId).val()+"&action=dispatchCustomerToSomeOne";
		}else if(inputId=="adminUserPage"){
			var selectedCus = $("[name=cusId]:checkbox").val([]);
			if(selectedCus.length==0){
				alert("页面无数据");
				return;
			}
			var ids = selectedCus[0].value;
				for(var i = 1;i<selectedCus.length;i++){
					ids += ","+ selectedCus[i].value;
				}
				obj.customerIds = ids;alert(ids);
				obj.oper = inputId;
				obj.adminUserCheck = $("#"+inputId).val();
				obj.action = "dispatchCustomerToSomeOne";
		}else if(inputId=="adminUserSelected"){
			var selectedCus = $("[name=cusId]:checked").val([]);
			if(selectedCus.length==0){
				alert("请勾选客户");
				return;
			}
			
			var ids = selectedCus[0].value;
				for(var i = 1;i<selectedCus.length;i++){
					ids += ","+ selectedCus[i].value ;
				}
				obj.customerIds = ids;
				obj.oper = inputId;
				obj.adminUserCheck = $("#"+inputId).val();
				obj.action = "dispatchCustomerToSomeOne";
		}
		
		
		$.ajax({
			type:"post",
			url: "CRMManage.htm",
			data:obj,
			success: function(data, textStatus){
			var a=eval("("+data+")");
			alert(a.message);
			$("form").submit();
		}})
	}
	
</script>
</head>
<body>
<div id="mask" class="mask"></div>  
 <div class="tab">
<s:form id="queryForm" action="dispatchCustomer.htm" method="post">
<table width="60%">
<caption class="redbold">我的客户</caption>
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
    <td  height="25" bgcolor="#ffffff"><div align="center">
    余额:<input id="sBalance" name="sBalance" value="${sBalance}"> - <input id="eBalance" name="eBalance" value="${eBalance}">
	  </div></td>
    <td  height="25" colspan="2">
    条件: <select name="f_orderserch" id="f_orderserch">
    <s:if test="f_orderserch.length()>0"><option value="${f_orderserch}" selected="selected">${f_orderserch}</option></s:if>
    <option value="" >请选择..</option>
    <option value="购彩用户" >购彩用户</option>
    <option value="未购彩用户" >未购彩用户</option>
    </select>
    时间:<input type="text" name="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
  </tr>
  <tr>
  	<td colspan="2">
  		对当前全部<font style='color:red'>${customerPage.totalCount}</font>位客户分配给业务员<input id="adminUserAll" onblur="checkIsFindCustomer('adminUserAll');" placeholder="请输入业务员账号" name="adminUserAll" type="text"><span id="adminUserAllSpan" class="gray"></span>   <input type="button" value="分配" onclick="dispath('adminUserAll')">
  	</td>
  	<td  colspan="2">
  		对当前页<font style='color:red'>${customerPage.pageSize}</font>位客户分配给业务员<input id="adminUserPage" onblur="checkIsFindCustomer('adminUserPage');" placeholder="请输入业务员账号" name="adminUserPage" type="text"><span id="adminUserPageSpan" class="gray"></span>   <input type="button" value="分配" onclick="dispath('adminUserPage')">
  	</td>
  	</tr>
  	<tr>
  		<td colspan="4">
  			对勾选的客户分配给业务员<input id="adminUserSelected" onblur="checkIsFindCustomer('adminUserSelected');" placeholder="请输入业务员账号" name="adminUserSelected" type="text"><span id="adminUserSelectedSpan" class="gray"></span>   <input type="button" value="分配" onclick="dispath('adminUserSelected')">
  		</td>
  	</tr>
</table>

<br>
<br>
<table >
  <tr>
    <%--<td><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/></td>
    --%><td height="25"><div align="center"><input type="button" value="全选" onclick="doSelectAll();" id="selectall"></input></div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">真实姓名</div></td>
    <%--<td height="25"><div align="center">证件号</div></td>
    <td height="25"><div align="center">证件类型</div></td>--%>
    <td height="25"><div align="center">邮箱</div></td>
    <td height="25"><div align="center">手机</div></td>
    <td height="25"><div align="center">资金状态</div></td>
    <td height="25"><div align="center">余额</div></td>
    <td height="25"><div align="center">充值金额</div></td>
    <td height="25"><div align="center">购彩总额</div></td>
    <td height="25"><div align="center">操作</div></td>
    <td height="25"><div align="center">注册时间</div></td>
    <td height="25"><div align="center">最后登陆时间</div></td>
  </tr>
  	<s:iterator id="rs" value="customerPage.result" status="st">
	  <tr>
	  <td><input name="cusId" type="checkbox" id="cusId_${rs.id}" value="${rs.id}"/></td>
      <td height="25" >${rs.nickName}<input type="hidden" id="nickName_${rs.id}" value="${rs.nickName}"></td>
      <td height="25" >${rs.realName}</td>
      <%--<td height="25" ><s:if test="#rs.credentNo!=''">
				<s:property value="#rs.credentNo.substring(0,12)"/>******
				</s:if></td>
      <td height="25" >${rs.credentType}</td>--%>
      <td height="25" ><s:if test="#rs.email!=''">
				****<s:property value="#rs.email.substring(4)"/>
				</s:if><input type="hidden" id="email_${rs.id}" value="${rs.email}"></td>
      <td height="25" ><s:if test="#rs.mobileNo!=''">
				<s:property value="#rs.mobileNo.substring(0,7)"/>****
				</s:if><input type="hidden" id="phoneNum_${rs.id}" value="${rs.mobileNo}"></td>
      <td height="25" >
      <s:if test="#rs.wallet.status==0">正常</s:if>
      <s:else>冻结</s:else>
      </td>
      <td>
      ${rs.wallet.balance }
      </td>
      <td>
      ${rs.bankName }
      </td>
      <td>
      ${rs.bankNumber }
      </td>
      <td height="25" ><a href="/oss/customer/manageCustomer.aspx?action=view&customerId=${rs.id }">资</a>|<a href="/oss/customer/manageFinanialQuery.aspx?customerId=${rs.id }">账</a></td>
      <td height="25" ><s:date name="#rs.registerTime" format="yyyy-MM-dd HH:mm"/></td>
       <td height="25" ><s:date name="#rs.lastLoginTime" format="yyyy-MM-dd HH:mm"/></td>
      </tr>
    </s:iterator>
</table>

<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${customerPage.pageSize}条 共${customerPage.totalCount}条记录 第${customerPage.pageNo}/${customerPage.totalPages}页   
	<s:if test="customerPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${customerPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="customerPage.pageNo>=customerPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${customerPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${customerPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${customerPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	
  <%--<table width="90%" border="0" align="center">
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
--%></s:form>
</div>
</body>
