<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>我的客户</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<link href="../../util/jsAndCss/div.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script src="../../util/jsAndCss/div.js" type=text/javascript></script>
<script src="../../util/overlay.jsp" type=text/javascript></script>
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
                editor = K.create('#t_content');
        });
	</script>
	<script>
	KE.show({
			id : 't_content',
			//cssPath : './index.css',
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
	$("#temForm").submit();
}
function jumpPage1() {
	$("#pageNo").val($("#pageNum").val());
	$("#temForm").submit();
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
		
		var aa = $('input[name="temId"]:checked').val([]);
		
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
					ids += $("#temId_"+aa[i].value).val();
					nickNames += "["+$("#nickName_"+aa[i].value).val()+"]";
					firstFla = 1;
				}else{
					phoneNums += ","+$("#phoneNum_"+aa[i].value).val();
					ids += ","+$("#temId_"+aa[i].value).val();
					nickNames += ","+"["+$("#nickName_"+aa[i].value).val()+"]";
				}
			}else if(status==2&&$("#email_"+aa[i].value).val()!=""){//邮件
			
				if(firstFla==0){
					emails += $("#email_"+aa[i].value).val();
					ids += $("#temId_"+aa[i].value).val();
					nickNames += "["+$("#nickName_"+aa[i].value).val()+"]";
					firstFla = 1;
				}else{
					emails += ","+$("#email_"+aa[i].value).val();
					ids += ","+$("#temId_"+aa[i].value).val();
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
	
	function showAddDiv(){
		$("#addTDiv").show();
		showMask();
	}
	
	function hideAddDiv(){
		$("#addTDiv").hide();
		hideMask();
	}
	
	function setSmsContent(obj){
		$.ajax({
			type:"post",
			url: "CRMManageAdmin.htm?action=getTemplate",
			data:{templateId:obj.value},
			success: function(data, textStatus){
			var a=eval("("+data+")");
			$("#smsContent").val(a.content);
		}})
		
	}
	
	function del(obj){
		
		var ids = "";
		if(obj=="many"){
			var aa = $('input[name="temId"]:checked').val([]);
			if(aa.length==0){
				alert("请勾选模板");
				return;
			}
			
			ids = $("#temId_"+aa[0].value).val()
			
			for(var i=1;i<aa.length;i++){
				ids += ","+$("#temId_"+aa[i].value).val();
			}
		}else{
			ids = obj;
		}
		
		if (!confirm("确认要删除？")) {
			return;
        }
		$.ajax({
			type:"post",
			url: "CRMManageAdmin.htm",
			data:{temIds:ids,action:"delTemplate"},
			success: function(data, textStatus){
			var a=eval("("+data+")");
			alert(a.message);
			hideAddDiv();
			
			$("#temForm").submit();
		}})
	}
	
	function setEmsilContent(obj){
		$.ajax({
			type:"post",
			url: "CRMManageAdmin.htm?action=getTemplate",
			data:{templateId:obj.value},
			success: function(data, textStatus){
			var a=eval("("+data+")");
			$("#emailContent").val(a.content);
			$("#emailTitle").val(a.title);
		}})
		
	}
	
	function edit(id){
		showAddDiv();
		$("#t_title").val($("#title_"+id).val());
		$.ajax({
			type:"post",
			url: "CRMManageAdmin.htm?action=getTemplate",
			data:{templateId:id},
			success: function(data, textStatus){
				var a=eval("("+data+")");
				KE.html("t_content", a.content);
				$("#t_description").val($("#description_"+id).val());
				$("#t_id").val(id);
				
				$("#sendTemplateTypee").attr('value',$("#sendTemplateType_"+id).val()); 
		}})
		
	}
	
	function doSelectAll()
	{
		str = $("#selectall").val();
		if(str == '全选')
		{
			$("#selectall").val("取消");
			$("[name=temId]:checkbox").attr("checked",true);
		}
		if(str == '取消')
		{
			$("#selectall").val("全选");
			$("[name=temId]:checkbox").attr("checked",false);
		}

	}
	
</script>
</head>
<body>
<div id="mask" class="mask"></div>  
 <div class="tab" align="center">
 <h3><strong>模板管理</strong></h3>
<s:form id="temForm" name="temForm" action="templateManager.htm" method="post">
	标题:<input type="text" name="t_titleQuer" value="${t_titleQuer }"/>
	类型：<s:select list="sendTemplateTypeList" name="sendTemplateType" id="sendTemplateType" listValue="name()" headerValue="请选择..." headerKey=""></s:select>
		<input type="submit" value="查询" /><input type="button" value="批量删除" onclick="del('many')"><input type="button" value="添加模板" onclick="showAddDiv()">
<br><br>
<table >
  <tr>
    <%--<td><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/></td>
    --%><td height="25"><div align="center"><input type="button" value="全选" onclick="doSelectAll();" id="selectall"></input></div></td>
    <td height="25"><div align="center">类型</div></td>
    <td height="25"><div align="center">标题</div></td>
    <td height="25"><div align="center">内容</div></td>
    <td height="25"><div align="center">描述</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  	<s:iterator id="rs" value="templatePage.result" status="st">
	  <tr>
	  <td><input name="temId" type="checkbox" id="temId_${rs.id}" value="${rs.id}"/></td>
      <td height="25" >${rs.sendTemplateType}<input type="hidden" id="sendTemplateType_${rs.id}" value="${rs.sendTemplateType}"></td>
      <td height="25" >${rs.title}<input type="hidden" id="title_${rs.id}" value="${rs.title}"></td><!-- <s:property value="#rs.content.replace('<','【').replace('>','】')"/> -->
      <td height="25" >
      <div class="dragCss" style="width:600px; height:80px;overflow-y:scroll;">${rs.content}</div>
      </td>
      <td height="25" >${rs.description }<input type="hidden" id="description_${rs.id}" value="${rs.description}"></td>
       <td height="25" >
       <a href="javascript:del(${rs.id })">删除</a>
       <a href="javascript:edit(${rs.id })">编辑</a></td>
      </tr>
    </s:iterator>
</table>

<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${templatePage.pageSize}条 共${templatePage.totalCount}条记录 第${templatePage.pageNo}/${templatePage.totalPages}页   
	<s:if test="templatePage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${templatePage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="templatePage.pageNo>=templatePage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${templatePage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${templatePage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${templatePage.pageNo}" />页  
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
<div hidden="" id="addTDiv" style="top:100px; margin-left:-400px;position:absolute;left:50%; width:800px; height:500px; background-color:#ffffff; z-index:1;">
<form action="CRMManageAdmin.htm" method="post">
<s:hidden name="action" id="action" value="addT"/>
<s:hidden name="t_id" id="t_id"/>
<div align="center">
<h3><strong>模板信息</strong></h3>
</div>
	&nbsp;&nbsp;标题：<input type="text" id="t_title" name="t_title"/>
		<br />
		<h1>&nbsp;&nbsp;类型： <s:select list="sendTemplateTypeList" name="sendTemplateType" id="sendTemplateTypee" listValue="name()" headerValue="请选择..." headerKey=""></s:select></h1>
		<br/>
		<h1>&nbsp;&nbsp;内容：<textarea style="width:800px;height:300px;" id="t_content" name="t_content" cols="80" rows="10"></textarea>
		<br />
		<h1>&nbsp;&nbsp;描述：<textarea id="t_description" name="t_description" cols="80" rows="3"></textarea>
		<br />
		<div style="width:100px;margin-left:-25px;position:absolute;left:50%;">
		<input type="submit" value="确定"><input type="button" value="取消" onclick="javascript:hideAddDiv();">
		</div>
		</form>
</div>
<br /><br />
<div hidden="" id="smsTDiv" style="top:100px; margin-left:-275px;position:absolute;left:50%; width:550px; height:150px; background-color:#81eb96; z-index:1;">
<form id="smsForm" action="CRMManageAdmin.htm" method="post">
<s:hidden name="action" id="action" value="sendSMS"/>
<s:hidden name="phoneNums" id="phoneNums"/>
<s:hidden name="phoneIds" id="phoneIds"/>
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
<div hidden="" id="emailTDiv" style="top:100px; margin-left:-275px;position:absolute;left:50%; width:550px; height:150px; background-color:#81eb96; z-index:1;">
<form id="emailForm" action="CRMManageAdmin.htm" method="post">
<s:hidden name="action" id="action" value="sendEmail"/>
<s:hidden name="emails" id="emails"/>
<s:hidden name="emailIds" id="emailIds"/>
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
