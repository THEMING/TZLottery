<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<link rel="stylesheet" href="/util/editor/themes/default/default.css" />
	<script charset="utf-8" src="/util/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/util/editor/lang/zh_CN.js"></script>
	<script>
        var editor;
        KindEditor.ready(function(K) {
                editor = K.create('#editor_id');
        });
	</script>
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script>
		KE.show({
			id : 'editor_id',
			cssPath : './index.css',
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
	</script>
	<script>
		function jumpPage(pageNo) {
			$("#pageNo").val(pageNo);
			$("form").submit();
		}
		function jumpPage1() {
			$("#pageNo").val($("#pageNum").val());
			$("form").submit();
		}
	</script>
  </head>
  
  <body>
  	<span style="color:red"><s:actionmessage/></span>
    <div >
    	<form action="/oss/article/manageArticle.htm?action=save" method="post">
    		<input type="hidden" value="${article.id}" name="article.id">
	    	<table width="75%" cellpadding="5" cellspacing="5"> 
	    	<tr>
	    			<td align="center">文章类别</td>
	    			<td>
	    			<select  name="type">
	    			<OPTION VALUE="专家推荐">专家推荐</OPTION>
	    			<OPTION VALUE="相关资讯">相关资讯</OPTION>
	    			<OPTION VALUE="彩票新闻">彩票新闻</OPTION>
	    			<OPTION VALUE="网站公告">网站公告</OPTION>
	    			<OPTION VALUE="投注策略">投注策略</OPTION>
	    			<OPTION VALUE="主题新闻">主题新闻</OPTION>
	    			<OPTION VALUE="彩市新闻">彩市新闻</OPTION>
	    			<OPTION VALUE="高频新闻">高频新闻</OPTION>
	    			<select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="center">彩种类别</td> 
	    			<td> <s:select list="lotteryTypes" name="lotteryType"/> </td>
	    		</tr>
	    		<tr>
	    			<td align="center">是否置 顶</td> 
	    			<td><input type="checkbox" <s:if test="article.top">checked</s:if>
						name="top" onchange="oncheck(this)" />
					</td>
	    		</tr>
	    		<tr>
	    			<td align="center" width="8%">新闻标题</td> 
	    			<td><input type="text" name="title" size="80" value="${article.title}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center" width="8%">新闻短标题</td> 
	    			<td><input type="text" name="shortTitle" size="80" value="${article.shortTitle}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center" width="8%">新闻关键词</td> 
	    			<td><input type="text" name="keywords" size="80" value="${article.keywords}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center" width="8%">关联关键词</td> 
	    			<td><input type="text" name="relation" size="80" value="${article.relation}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center" width="8%">新闻跳转</td> 
	    			<td><input type="text" name="jumpUrl" size="80" value="${article.jumpUrl}"></td>
	    		</tr>
	    		
	    		<tr>
	    			<td align="center">新闻来源</td> 
	    			<td><input type="text" name="author" value="${article.author}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center">发布日期</td> 
	    			<td><input type="text" name="date" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"
	    				value="<s:date name="article.publishTime" format="yyyy-MM-dd HH:mm:ss"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td align="center" width="8%">新闻描述</td> 
	    			<td><input type="text" name="desc" size="80" value="${article.desc}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center">内容</td> 
	    			<td>
	    				<textarea style="width:670px" rows="20" id="editor_id" name="content">${article.content}
	    				</textarea>
	    			</td>
	    		</tr>
	    		
	    		<tr>
		    		<td align="center" colspan="2">
		    			<input type="submit" value="提交到数据库"/>
		    			<input type="reset" value="清空内容" />
		    		</td>
		    	</tr>
	    	</table>
	    </form>
	    <br/>
	    <hr />
	    
	    <form action="/oss/article/manageArticle.htm" method="post">
	    <div class="tab" style="margin-left:15px">
	    <table width="80%" cellspacing="3">
	    	<tr><td align="center" colspan="9"><span style="color:red; font-size:20px">文章列表</span></td></tr>
	    	<tr>
	    		<td align="center">标题</td>
	    		<td align="center">作者</td>
	    		<td align="center">日期</td>
	    		<td align="center">类别</td>
	    		<td align="center">彩种</td>
	    		<td align="center">是否置顶</td>
	    		<td></td>
	    		<td></td>
	    		<td></td>
	    	</tr>
	    	<tr></tr>
		    <s:iterator id="rs" value="page.result">
	  			<tr>
	  				<td align="center"><s:property value="title"></s:property></td>
	  				<td align="center"><s:property value="author"></s:property></td>
	  				<td align="center"><s:date name="publishTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	  				<td align="center"><s:property value="type"></s:property></td>
	  				<td align="center"><s:property value="lotteryType"></s:property></td>
	  				<td align="center">
	  					<c:if test="${rs.top}"><span style="color:red">已置顶</span></c:if>
	  				</td>
	  				<td align="center"><a href="/oss/article/manageArticle.htm?action=delete&id=<s:property value="id" />">删除记录</a></td>
	  				<td align="center"><a href="/oss/article/manageArticle.htm?action=detail&id=<s:property value="id" />">修改记录</a></td>
	  				<td align="center"><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id" />">预览</a></td>
	  			</tr>
	  		</s:iterator>
  		</table>
  		</div>
  		<div style="margin:15px; margin-left:50px;">
  			<div>
				<input type="hidden" name="pageNo" id="pageNo" value="1" />
				每页${page.pageSize}条 共${page.totalCount}条记录 第${page.pageNo}/${page.totalPages}页   
				<s:if test="page.pageNo==1">
					<span class="disabled">首页</span> 
					<span class="disabled">前一页</span>
				</s:if>
				<s:else>
					<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
					<a href="javascript:jumpPage(${page.pageNo-1})" class="cr_link">前一页</a>
				</s:else>
				
				<s:if test="page.pageNo>=page.totalPages">
					<span class="disabled">后一页</span> 
					<span class="disabled">末页</span>
				</s:if>
				<s:else>
					<a href="javascript:jumpPage(${page.pageNo+1})" class="cr_link">后一页</a>
					<a href="javascript:jumpPage(${page.totalPages})" class="cr_link">末页</a>
				</s:else>
				转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${page.pageNo}" />页  
				<input type="button" value="跳转" onclick="jumpPage1();"/>
				<br/><br/><br/><br/>
			</div>
  		</div>
  		</form>
    </div>
  </body>
</html>
