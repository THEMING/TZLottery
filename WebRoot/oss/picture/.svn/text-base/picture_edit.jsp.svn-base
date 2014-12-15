<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<title></title>
    <link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script charset="utf-8" src="/kindeditor/kindeditor.js"></script>
	<script>
        var editor;
        KindEditor.ready(function(K) {
                editor = K.create('#editor_id');
        });
	</script>
	<script>
	KE.show({
		id : 'editor_id',
		//cssPath : './index.css',
		imageUploadJson : '/upload.aspx',
		fileManagerJson : '../../jsp/file_manager_json.jsp',
		allowFileManager : true,
		allowUpload : true,
		items:['source','image'],
		afterCreate : function(id) {
			KE.event.ctrl(document, 13, function() {
				KE.sync(id);
				document.forms['example'].submit();
			});
			KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
				KE.sync(id);
				document.forms['example'].submit();
			});
		}
	});
		
		
	</script>
	<script>
		function oncheck(obj){
			$(obj).val($(obj).attr("checked"));
		}
		
		function checkForm()
		{
			flag = true;
			msg = "";
			
			var article_content = KE.util.getData('editor_id');
			if(article_content == "") 
			{
				flag = false;	
				msg += "图片代码不能为空！\n";
			}
			
			if(!flag)
			{
				alert(msg);
			}
			else
			{
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
			   $("#editor_id").val(article_content);
			   var da = new Date();
			   $("#date").val(da.getFullYear()+"-"+(da.getMonth()+1)+"-"+da.getDate()+" "+da.getHours()+":"+da.getMinutes()+":"+da.getSeconds());
			   $("#article_form").submit();
			}
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
				$("#partnerTr").show();
			}else if(obj.id=="isPublic"&&$(obj).attr("checked")==true){
				$("#partnerTr").hide();
			}
		}
		
	</script>
	</head>
	<body>
	<div style="height:20px;padding:10px 0 10px 260px;"><label>**********图片管理**********</label></div>
	<span style="color:red"><s:actionmessage/></span>
    <div >
    	<form action="pictureManage.htm?action=save" method="post" id="article_form" name="article_form">
    	<input type="hidden" value="${article.id}" name="article.id">
    	<input type="hidden" id="date" name="date" />
    	<input type="hidden" id="isPicture" name="isPicture" value="true" />
	    <table width="75%" cellpadding="5" cellspacing="5"> 
	    <tr>
	    	<td align="center" width="12%">图片类别</td>
	    	<td  width="90%">
	    		<s:select list="categoryList" name="category.id" listKey="id" listValue="name" value="category.id"/>
	    	</td>
	    </tr>
	    <tr>
	    	<td align="center" width="8%">图片标题<font color="red" style="padding-left: 5px;">*</font></td> 
	    	<td><input type="text" id="title" name="title" size="80" value="${article.title}"></td>
	    </tr>
	    <%--<tr>
	    	<td align="center" width="8%">关联的代理商</td> 
	    	<td><s:select list="partnerList" name="partner.id" listKey="id" listValue="nickName" value="partner.id" headerValue="请选择..." headerKey=""/></td>
	    </tr>
	    --%><tr>
	    	<td align="center">是否公开</td> 
	    	<td><input type="checkbox" id="isPublic" name="isPublic" value=<s:if test="article.isPublic">true</s:if><s:else>false</s:else> onchange="oncheck(this)" <s:if test="article.isPublic">checked</s:if> />
	    		<input hidden="" id="partnerIdss" name="partnerIdss">
	    	</td>
	    </tr>
	    <tr id="partnerTr" <s:if test="article.isPublic">hidden</s:if> >
	    	<td colspan="2">
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
	    	</td>
	    </tr>
	    <tr>
	    	<td align="center" width="8%">图片链接</td> 
	    	<td><input type="text" name="jumpUrl" size="80" value="${article.jumpUrl}"></td>
	    </tr>
   		<tr>
   			<td align="center" width="8%">图片描述</td> 
   			 <td><textarea id="description" name="description" cols="80" rows="3">${article.description}</textarea></td>
   		</tr>
   		<tr>
   			<td align="center">图片代码<font color="red" style="padding-left: 5px;">*</font></td> 
   			<td>
   				<textarea style="width:670px" rows="20" id="editor_id" name="content">${article.content}
   				</textarea>
   			</td>
   		</tr>
   		<tr>
    		<td align="center" colspan="2">
    			<input type="button" value="保存" onclick="javascript:checkForm();"/>
    			<input type="button" value="返回" onclick="history.back()"/>
    		</td>
    	</tr>
    	</table>
    </form>
    </div>
	</body>
</html>