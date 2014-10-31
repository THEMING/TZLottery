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
			if($("#category_id").val() == "") 
			{
				flag = false;	
				msg += "新闻分类不能为空！\n";
			}
			if($("#lotteryType").val() == "") 
			{
				flag = false;	
				msg += "彩种分类不能为空！\n";
			}
			if($("#title").val() == "") 
			{
				flag = false;	
				msg += "新闻标题不能为空！\n";
			}
			if($("#date").val() == "") 
			{
				flag = false;	
				msg += "新闻发布日期不能为空！\n";
			}
			
			if($("#keywords").val() == "") 
			{
				flag = false;	
				msg += "新闻关键词不能为空！\n";
			}
			
			if($("#relation").val() == "") 
			{
				flag = false;	
				msg += "新闻关联关键词不能为空！\n";
			}
			
			if($("#author").val() == "") 
			{
				flag = false;	
				msg += "新闻来源不能为空！\n";
			}
			if($("#description").val() == "") 
			{
				flag = false;	
				msg += "新闻描述不能为空！\n";
			}
			
			var article_content = KE.util.getData('editor_id');
			if(article_content == "") 
			{
				flag = false;	
				msg += "新闻内容不能为空！\n";
			}
			
			if(!flag)
			{
				alert(msg);
			}
			else
			{
			   $("#editor_id").val(article_content);
			   $("#article_form").submit();
			}
		}
		
	</script>
	</head>
	<body>
	<div style="height:20px;padding:10px 0 10px 260px;"><label>**********新闻编辑**********</label></div>
	<span style="color:red"><s:actionmessage/></span>
    <div >
    	<form action="/oss/article/manageArticle.htm?action=save" method="post" id="article_form" name="article_form">
    	<input type="hidden" value="${article.id}" name="article.id">
	    <table width="75%" cellpadding="5" cellspacing="5"> 
	    <tr>
	    	<td align="center" width="12%">文章类别<font color="red" style="padding-left: 5px;">*</font></td>
	    	<td  width="90%">
	    		<s:select list="categoryList" name="category.id" listKey="id" listValue="name" value="category.id"/>
	    	</td>
	    </tr>
	    <tr>
	    	<td align="center">彩种类别<font color="red" style="padding-left: 5px;">*</font></td> 
	    	<td> <s:select list="lotteryTypes" name="lotteryType"/></td>
	    </tr>
	    <tr>
	    	<td align="center">是否置 顶</td> 
	    	<td><input type="checkbox" <s:if test="article.top">checked</s:if>	name="top" onchange="oncheck(this)" /></td>
	    </tr>
	    <tr>
	    	<td align="center" width="8%">新闻标题<font color="red" style="padding-left: 5px;">*</font></td> 
	    	<td><input type="text" id="title" name="title" size="80" value="${article.title}"></td>
	    </tr>
	    <tr>
	    	<td align="center" width="8%">新闻短标题</td> 
	    	<td><input type="text" name="shortTitle" size="80" value="${article.shortTitle}"></td>
	    </tr>
	    <tr>
	    	<td align="center" width="8%">新闻关键词<font color="red" style="padding-left: 5px;">*</font></td> 
	    	<td><input type="text" id="keywords" name="keywords" size="50" value="${article.keywords}">（关键词之间用逗号分开）</td>
	    </tr>
	    <tr>
	    	<td align="center" width="8%">关联关键词<font color="red" style="padding-left: 5px;">*</font></td> 
	    	<td><input type="text" id="relation" name="relation" size="50" value="${article.relation}">（关联关键词之间用空格分开）</td>
	    </tr>
   		<tr>
  			<td align="center">新闻来源<font color="red" style="padding-left: 5px;">*</font></td> 
  			<c:if test="${empty article.author}">
  			<td><input type="text" id="author" name="author" value="一彩票网"></td>
  			</c:if>
   			<c:if test="${not empty article.author}">
  			<td><input type="text" id="author" name="author" value="${article.author}"></td>
  			</c:if>
   		</tr>
   		<tr>
   			<td align="center">发布日期<font color="red" style="padding-left: 5px;">*</font></td> 
   			<td><input type="text" id="date" name="date" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"
   				value="<s:date name="article.publishTime" format="yyyy-MM-dd HH:mm:ss"/>" /></td>
   		</tr>
   		<tr>
   			<td align="center" width="8%">新闻描述<font color="red" style="padding-left: 5px;">*</font></td> 
   			<!-- 
   			<td><input type="text" id="description" name="description" size="80" value="${article.description}"></td>
   			 -->
   			 <td><textarea id="description" name="description" cols="80" rows="3">${article.description}</textarea></td>
   		</tr>
   		<tr>
   			<td align="center">内容<font color="red" style="padding-left: 5px;">*</font></td> 
   			<td>
   				<textarea style="width:670px" rows="20" id="editor_id" name="content">${article.content}
   				</textarea>
   			</td>
   		</tr>
   		 <tr>
	    	<td align="center" width="8%">新闻跳转</td> 
	    	<td><input type="text" name="jumpUrl" size="80" value="${article.jumpUrl}"></td>
	    </tr>
   		<tr>
    		<td align="center" colspan="2">
    			<input type="button" value="保存" onclick="javascript:checkForm();"/>
    			<input type="reset" value="取消" />
    		</td>
    	</tr>
    	</table>
    </form>
    </div>
	</body>
</html>