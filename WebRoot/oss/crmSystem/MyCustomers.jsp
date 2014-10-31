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
	
	function sendMany(status,id){
		hideSms();
		hideEmail();
		var ids = "";
		var nickNames = "";
		var phoneNums = "";
		var emails = "";
		var phoneMsg = "";
		var emailMsg = "";
		var firstFla = 0;
		
		var aa = $('input[name="cusId"]:checked').val([]);
		
		if(id!=null){
			var ob = new Object();
			ob.value = id;
			aa = [ob];
		}else if(aa.length==0){
				alert("请勾选用户");
				return;
		}
		
		for(var i=0;i<aa.length;i++){
			if(status==1&&$("#phoneNum_"+aa[i].value).val()!=""){//短信
			
				if(firstFla==0){
					phoneNums += $("#phoneNum_"+aa[i].value).val();
					ids += $("#cusId_"+aa[i].value).val();
					nickNames += "["+$("#nickName_"+aa[i].value).val()+"]";
					firstFla = 1;
				}else{
					phoneNums += ","+$("#phoneNum_"+aa[i].value).val();
					ids += ","+$("#cusId_"+aa[i].value).val();
					nickNames += ","+"["+$("#nickName_"+aa[i].value).val()+"]";
				}
			}else if(status==2&&$("#email_"+aa[i].value).val()!=""){//邮件
			
				if(firstFla==0){
					emails += $("#email_"+aa[i].value).val();
					ids += $("#cusId_"+aa[i].value).val();
					nickNames += "["+$("#nickName_"+aa[i].value).val()+"]";
					firstFla = 1;
				}else{
					emails += ","+$("#email_"+aa[i].value).val();
					ids += ","+$("#cusId_"+aa[i].value).val();
					nickNames += ","+"["+$("#nickName_"+aa[i].value).val()+"]";
				}
			}else{
				if(status==1&&$("#phoneNum_"+aa[i].value).val()==""){
					phoneMsg = phoneMsg+"["+$("#nickName_"+aa[i].value).val()+"]";
				}else if(status==2&&$("#email_"+aa[i].value).val()==""){
					emailMsg = emailMsg+"["+$("#nickName_"+aa[i].value).val()+"]";
				}
			}
			
		}
		
		if(status==1&&phoneNums!=""){//短信
			if(phoneMsg!=""){
				alert("用户"+phoneMsg+"尚未绑定手机，已从勾选列表中去掉");
			}
			$("#phoneIds").val(ids);
			$("#phoneNums").val(phoneNums);
			$("#phoneNickNames").val(nickNames);
			showSms();
		}else if(status==2&&emails!=""){//邮件
			if(emailMsg!=""){
				alert("用户"+emailMsg+"尚未绑定邮箱，已从勾选列表中去掉");
			}
			$("#emailIds").val(ids);
			$("#emails").val(emails);
			$("#emailNickNames").val(nickNames);
			showEmail();
		}else if(status==1&&phoneMsg!=""){
			alert("所选用户都未绑定手机，操作失败");
		}else if(status==2&&emailMsg!=""){
			alert("所选用户都未绑定邮箱，操作失败");
		}
	}
	
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
	
	function checkFrom(fla){
		if(fla==1){//短信验证
			var idContent = "#smsContent";
			var idArgument = "#smsArgument";
			var idForm = "#smsForm";
		}else if(fla==2){//邮箱验证
			var idContent = "#emailContent";
			var idArgument = "#emailArgument";
			var idForm = "#emailForm";
		}
		
			if($(idContent).val() == "") {
				alert("请选择模板");
				return false;
			}
			
			var cont = $(idContent).val();
			var fla = true;
			var ii=1;
			while(fla){
				var iii = cont.indexOf("{"+ii+"}");
				if(iii!=-1){
					ii++;
				}else{
					fla = false;
				}
			}
			
			var argument = $(idArgument).val();
			var args = new Object();
			args.length = 0;
			if(argument!=""){
				args = argument.split(",");
			}
			
			if($(idArgument).val() == ""&&ii!=1) {
				alert("请输入参数");
				$(idArgument).focus();
				return false;
			}
			
			if((ii-1)!=args.length){
				alert("模板参数个数为:"+(ii-1)+",输入的参数个数为:"+args.length+",参数个数不匹配");
				return false;
			}
			
			var formData=$(idForm).serialize();	   
			$.ajax({
				type:"post",
				url: "CRMManage.htm",
				data:formData,
				success: function(data, textStatus){
				var a=eval("("+data+")");
				alert(a.msg);
				hideSms();
				hideEmail();
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
	
</script>
</head>
<body>
<div id="mask" class="mask"></div>  
 <div class="tab">
<s:form  action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="index"/>
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
</table>

<br>
<input type="button" value="批量发短信" onclick="sendMany(1)"><input type="button" value="批量发邮件" onclick="sendMany(2)">   总充值金额：<font color="red">${paymentMoenySum}</font>（元）   总购彩金额：<font color="red">${outMoneySum}</font>（元）
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
    <td height="25"><div align="center">操作</div></td>
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
       <td height="25" >
       <a href="javascript:sendMany(1,${rs.id })">发短信</a>
       <a href="javascript:sendMany(2,${rs.id })">发邮件</a></td>
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
<div hidden="" id="addTDiv" style="width:550px; height:300px; background-color:#81eb96; z-index:1;">
<form action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="addT"/>
模板：<s:select list="adminSendSomeThingTemplateList" name="adminSendSomeThingTemplate.id" listKey="id" listValue="title" value="adminSendSomeThingTemplate.id" headerValue="请选择..." headerKey=""/>
	<h1>标题：<input type="text" id="t_title" name="t_title"/></h1>
		<br />
		<h1>类型： <s:select list="sendTemplateTypeList" name="sendTemplateType" id="sendTemplateType" listValue="name()" headerValue="请选择..." headerKey=""></s:select></h1>
		<br/>
		<h1>内容：<textarea id="t_content" name="t_content" cols="80" rows="3"></textarea>
		<br />
		<h1>描述：<textarea id="t_description" name="t_description" cols="80" rows="3"></textarea>
		<br />
		<input type="submit" value="新增模板"><input type="button" value="取消" onclick="javascript:closee();">
		</form>
</div>
<br /><br />
<div hidden="" id="smsTDiv" style="top:100px; margin-left:-275px;position:absolute;left:50%; width:550px; height:200px; background-color:#81eb96; z-index:1;">
<form id="smsForm" action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="sendSMS"/>
<s:hidden name="phoneNums" id="phoneNums"/>
<s:hidden name="phoneIds" id="phoneIds"/>
<div align="center">
<h3><strong>发送短信</strong></h3>
</div>
短信模板：<s:select onchange="setSmsContent(this)" list="smsTemplateList" name="smsTemplate.id" listKey="id" listValue="title" value="id" headerValue="请选择..." headerKey=""/>
		<br>
		发送的用户：<input id="phoneNickNames" name="nickNames" type="text">
		<br>
		参数：<input type="text" id="smsArgument" name="smsArgument">
		<br>
		内容：<textarea id="smsContent" name="smsContent" cols="80" rows="3" readonly="readonly"></textarea>
		<br>
		<div style="width:100px;margin-left:-25px;position:absolute;left:50%;">
		<input type="button" onclick="checkFrom(1)" value="提交" ><input type="button" onclick="closee(1)" value="关闭" >
		</div>
		</form>
</div>
<br /><br />
<div hidden="" id="emailTDiv" style="top:100px; margin-left:-275px;position:absolute;left:50%; width:550px; height:200px; background-color:#81eb96; z-index:1;">
<form id="emailForm" action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="sendEmail"/>
<s:hidden name="emails" id="emails"/>
<s:hidden name="emailIds" id="emailIds"/>
<div align="center">
<h3><strong>发送邮件</strong></h3>
</div>
邮件模板：<s:select onchange="setEmsilContent(this)" list="emailTemplateList" name="emailTemplate.id" listKey="id" listValue="title" value="emailTemplate.id" headerValue="请选择..." headerKey=""/>
	<br>
		发送的用户：<input id="emailNickNames" name="nickNames" type="text">
		<br>
		参数：<input type="text" id="emailArgument" name="emailArgument">
		<br>
		标题：<input type="text" id="emailTitle" name="emailTitle" readonly="readonly">
		<br>
		内容：<textarea id="emailContent" name="emailContent" cols="80" rows="3" readonly="readonly"></textarea>
		<br>
		<div style="width:100px;margin-left:-25px;position:absolute;left:50%;">
		<input type="button" onclick="checkFrom(2)" value="提交" ><input type="button" onclick="closee(2)" value="关闭" >
		</div>
		</form>
</div>
</body>
