<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<head>
<title>我的客户</title>
<link href="../../oss/skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../../oss/styles/base.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../../oss/skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script charset="utf-8" src="/kindeditor/kindeditor.js"></script>
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
        var editor;
        KindEditor.ready(function(K) {
                editor = K.create('#emailContent');
        });
	</script>
	<script>
	KE.show({
			id : 'emailContent',
			//cssPath : './index.css',
			items:[],
			imageUploadJson : '/upload.aspx',
			fileManagerJson : '../../jsp/file_manager_json.jsp',
			allowFileManager : true,
			allowUpload : true,
			afterCreate : function(id) {
			}
		});
	</script>
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
	$("#queryForm").submit();
}
function jumpPage1() {
	$("#pageNo").val($("#pageNum").val());
	$("#queryForm").submit();
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
		
		var cantSmsMsg = "";//由于超过限制短信数不能发短信的用户
		var cantEmailMsg = "";//由于超过限制邮件数不能发邮件的用户
		
		var canSmsNum = $("#canSmsNum").val();//限制的短信数
		var canEmailNum = $("#canEmailNum").val();//限制的邮件数
		
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
				if(parseInt($("#smsAccept_"+aa[i].value).val())>=parseInt(canSmsNum)){
					cantSmsMsg += "["+$("#nickName_"+aa[i].value).val()+"]";
					continue;
				}
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
				if(parseInt($("#emailAccept_"+aa[i].value).val())>=parseInt(canEmailNum)){
					cantEmailMsg += "["+$("#nickName_"+aa[i].value).val()+"]";
					continue;
				}
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
			var msg = "";
			if(phoneMsg!=""){
				msg = "用户"+phoneMsg+"尚未绑定手机;";
				
			}
			if(cantSmsMsg!=""){
				msg += "用户"+cantSmsMsg+"超过了可发送的短信数;"
			}
			if(msg!=""){
				alert(msg+"已从勾选列表中去掉");
			}
			$("#phoneIds").val(ids);
			$("#phoneNums").val(phoneNums);
			$("#phoneNickNames").val(nickNames);
			showSms();
		}else if(status==2&&emails!=""){//邮件
			var msg = "";
			if(emailMsg!=""){
				msg = "用户"+phoneMsg+"尚未绑定邮箱;";
			}
			if(cantEmailMsg!=""){
				msg += "用户"+cantEmailMsg+"超过了可发送的邮件数;"
			}
			if(msg!=""){
				alert(msg+"已从勾选列表中去掉");
			}
			$("#emailIds").val(ids);
			$("#emails").val(emails);
			$("#emailNickNames").val(nickNames);
			showEmail();
		}else if(status==1&&(phoneMsg!=""||cantSmsMsg!="")){
			var msg = "";
			if(phoneMsg!=""){
				msg = "用户"+phoneMsg+"尚未绑定手机;";
				
			}
			if(cantSmsMsg!=""){
				msg += "用户"+cantSmsMsg+"超过了可发送的短信数;"
			}
			if(msg!=""){
				alert(msg+"已从勾选列表中去掉");
			}
			//alert("所选用户都未绑定手机，操作失败");
		}else if(status==2&&(emailMsg!=""||cantEmailMsg!="")){
			var msg = "";
			if(emailMsg!=""){
				msg = "用户"+phoneMsg+"尚未绑定邮箱;";
			}
			if(cantEmailMsg!=""){
				msg += "用户"+cantEmailMsg+"超过了可发送的邮件数;"
			}
			if(msg!=""){
				alert(msg+"已从勾选列表中去掉");
			}
			//alert("所选用户都未绑定邮箱，操作失败");
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
			KE.html("emailContent", a.content);
			KE.readonly("emailContent",true);
			$("#emailTitle").val(a.title);
		}})
		
	}
	
	function checkFrom(fla){
		var contentContent = "";
		if(fla==1){//短信验证
			var idContent = "#smsContent";
			var idArgument = "#smsArgument";
			var idForm = "#smsForm";
			contentContent = $(idContent).val();
		}else if(fla==2){//邮箱验证
			var idContent = "#emailContent";
			var idArgument = "#emailArgument";
			var idForm = "#emailForm";
			contentContent = KE.util.getData("emailContent");
		}
		
			if(contentContent == "") {
				alert("请选择模板");
				return false;
			}
			
			var cont = contentContent;
			var flaa = true;
			var ii=1;
			while(flaa){
				var iii = cont.indexOf("{"+ii+"}");
				if(iii!=-1){
					ii++;
				}else{
					flaa = false;
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
			//if(fla==2){
			//	formData = formData+contentContent;
			//}
			$.ajax({
				type:"post",
				url: "CRMManage.htm",
				data:formData,
				success: function(data, textStatus){
				var a=eval("("+data+")");
				alert(a.msg);
				hideSms();
				hideEmail();
				$("#queryForm").submit();
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
	
	function cli(obj){
		if($("[name="+obj.name+"]").attr("checked")){
			$("[name="+obj.name+"]").val(true);
		}else{
			$("[name="+obj.name+"]").val(false);
		}
		
	}
	
</script>
</head>
<body>
<div id="mask" class="mask"></div>  
 <div class="tab">
<s:form id="queryForm" action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="index"/>

<input type="hidden" name="canSmsNum" id="canSmsNum" value="${canSmsNum}">
<input type="hidden" name="canEmailNum" id="canEmailNum" value="${canEmailNum}">


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
  	<td ><div align="center">
  		今天还能发短信的客户
  	</div>
  	</td>
  	<td >
  		<input name="canSms" type="checkbox" id="canSms" onclick="cli(this)" <s:if test="canSms">checked</s:if> value="<s:if test="canSms">true</s:if>" />
  	</td>
  	<td ><div align="center">
  		今天还能发邮件的客户
  	</div>
  	</td>
  	<td >
  		<input name="canEmail" type="checkbox" id="canEmail" onclick="cli(this)" <s:if test="canEmail">checked</s:if> value="<s:if test="canEmail">true</s:if>" />
  	</td>
  </tr>
</table>

<br>
<input type="button" value="批量发短信" onclick="sendMany(1)">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="批量发邮件" onclick="sendMany(2)">   总充值金额：<font color="red">${paymentMoenySum}</font>（元）   总购彩金额：<font color="red">${outMoneySum}</font>（元）
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
    <td height="25"><div align="center">用户状态</div></td>
    <td height="25"><div align="center">余额</div></td>
    <td height="25"><div align="center">充值金额</div></td>
    <td height="25"><div align="center">购彩总额</div></td>
    <td height="25"><div align="center">注册时间</div></td>
    <%--<td height="25"><div align="center">最后登陆时间</div></td>
    --%>
    <td height="25"><div align="center">今天已发短信数</div></td>
    <td height="25"><div align="center">今天已发邮件数</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  <s:iterator id="rs" value="customerListMap" status="s">
	  <tr>
	  <td><input name="cusId" type="checkbox" id="cusId_${rs[0]}" value="${rs[0]}"/></td>
      <td height="25" >${rs[1]}<input type="hidden" id="nickName_${rs[0]}" value="${rs[1]}"></td>
      <td height="25" >${rs[2]}</td>
      <td height="25" ><s:if test="#rs[3]!=''">
				****<s:property value="#rs[3].substring(4)"/>
				</s:if><input type="hidden" id="email_${rs[0]}" value="${rs[3]}"></td>
      <td height="25" ><s:if test="#rs[4]!=''">
				<s:property value="#rs[4].substring(0,7)"/>****
				</s:if><input type="hidden" id="phoneNum_${rs[0]}" value="${rs[4]}"></td>
      <td height="25" >
      <s:if test="#rs[5]==0">
      未激活
      </s:if>
      <s:if test="#rs[5]==1">
      已激活
      </s:if>
      <s:if test="#rs[5]==2">
      已锁定
      </s:if>
      </td>
      <td>
      ${rs[6] }
      </td>
      <td>
      ${rs[7] }
      </td>
      <td>
      ${rs[8] }
      </td>
      <td height="25" ><s:date name="#rs[9]" format="yyyy-MM-dd HH:mm"/></td>
       
       <td height="25" ><s:if test="#rs[10]!=null">
				<s:property value="#rs[10].substring(today-7,today)"/>
				</s:if><s:else>0</s:else>
				<input type="hidden" id="sms_accept_${rs[0]}" value="<s:if test="#rs[10]!=null">
				<s:property value="#rs[10].substring(today-1,today)"/>
				</s:if><s:else>0</s:else>" >
				</td>
	   <td height="25" ><s:if test="#rs[11]!=null">
				<s:property value="#rs[11].substring(today-7,today)"/>
				</s:if><s:else>0</s:else>
				<input type="hidden" id="email_accept_${rs[0]}" value="<s:if test="#rs[11]!=null">
				<s:property value="#rs[11].substring(today-1,today)"/>
				</s:if><s:else>0</s:else>" >
				</td>
       <td height="25" >
       <a href="javascript:sendMany(1,${rs[0] })">发短信</a>
       <a href="javascript:sendMany(2,${rs[0] })">发邮件</a></td>
      </tr>
    </s:iterator></table>
<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页<input type="text" name="pageSize" style="width: 30px" id="pageSize" value="${pageSize}" />条 共${totalNum}条记录 第${pageNo}/${totalPages}页   
	<s:if test="#pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${pageNo}-1)" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="pageNo>=totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${pageNo}+1)" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table></s:form>
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
<div hidden="" id="smsTDiv" style="top:100px; margin-left:-275px;position:absolute;left:50%; width:550px; height:300px; background-color:#ffffff; z-index:1;">
<form id="smsForm" action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="sendSMS"/>
<s:hidden name="phoneNums" id="phoneNums"/>
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}">
<s:hidden name="phoneIds" id="phoneIds"/>
<div align="center">
<h3><strong>发送短信</strong></h3>
</div>
&nbsp;&nbsp;短信模板：<s:select onchange="setSmsContent(this)" list="smsTemplateList" name="smsTemplate.id" listKey="id" listValue="title" value="id" headerValue="请选择..." headerKey=""/>
		<br>
		&nbsp;&nbsp;发送的用户：<input id="phoneNickNames" name="nickNames" type="text">
		<br>
		&nbsp;&nbsp;参数：<input type="text" id="smsArgument" name="smsArgument"><font color="red">用半角逗号分隔，例如：参数1,参数2;参数1将会替代内容中的{1},如此类推</font>
		<br>
		&nbsp;&nbsp;内容：<textarea id="smsContent" name="smsContent" cols="80" rows="10" readonly="readonly"></textarea>
		<br>
		<div style="width:100px;margin-left:-25px;position:absolute;left:50%;">
		<input type="button" onclick="checkFrom(1)" value="提交" ><input type="button" onclick="closee(1)" value="关闭" >
		</div>
		</form>
</div>
<br /><br />
<div hidden="" id="emailTDiv" style="top:100px; margin-left:-400px;position:absolute;left:50%; width:800px; height:500px; background-color:#ffffff; z-index:1;">
<form id="emailForm" action="CRMManage.htm" method="post">
<s:hidden name="action" id="action" value="sendEmail"/>
<s:hidden name="emails" id="emails"/>
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}">
<s:hidden name="emailIds" id="emailIds"/>
<div align="center">
<h3><strong>发送邮件</strong></h3>
</div>
&nbsp;&nbsp;邮件模板：<s:select onchange="setEmsilContent(this)" list="emailTemplateList" name="emailTemplate.id" listKey="id" listValue="title" value="emailTemplate.id" headerValue="请选择..." headerKey=""/>
	<br>
		&nbsp;&nbsp;发送的用户：<input id="emailNickNames" name="nickNames" type="text">
		<br>
		&nbsp;&nbsp;参数：<input type="text" id="emailArgument" name="emailArgument"><font color="red">用半角逗号分隔，例如：参数1,参数2;参数1将会替代内容中的{1},如此类推</font>
		<br>
		&nbsp;&nbsp;标题：<input type="text" id="emailTitle" name="emailTitle" readonly="readonly">
		<br>
		&nbsp;&nbsp;内容：<textarea style="width:800px;height:340px;" id="emailContent" name="emailContent" cols="80" rows="10" readonly="readonly"></textarea>
		<br>
		<div style="width:100px;margin-left:-25px;position:absolute;left:50%;">
		<input type="button" onclick="checkFrom(2)" value="提交" ><input type="button" onclick="closee(2)" value="关闭" >
		</div>
		</form>
</div>
</body>
