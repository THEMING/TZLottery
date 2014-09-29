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
		function qq(){
		    $("#userName").val("");
		    $("#type").val("");
		    $("#wintime").val("");
		    $("#bonus").val("");
		    $("#rankingType").val("");
		    $("#win").val("");
		}
        function validate(){
           if($("#userName").val()==""){
           alert("用户名不能为空")
           return;}
            if($("#type").val()==""){
             alert("类型不能为空")
           return;}
            if($("#wintime").val()==""){
             alert("中奖时间不能为空")
           return;}
            if($("#bonus").val()==""){
             alert("奖金不能为空")
           return;}
            if($("#rankingType").val()==""){
             alert("排行榜不能为空")
           return;}
        }
	</script>
  </head>
  
  <body>
  	<span style="color:red"><s:actionmessage/></span>
    <div >
    	<form id="form" action="/oss/article/manageWinPrize.htm?action=save" method="post">
    		<input type="hidden" id="win" value="${winPrize.id}" name="id">
	    	<table width="75%" cellpadding="5" cellspacing="5"> 
	    		<tr>
	    			<td align="center" >用户名</td> 
	    			<td><input type="text" id="userName" name="userName" value="${winPrize.userName}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center">彩种</td> 
	    			<td><input type="text" id="type" name="type" value="${winPrize.type}"></td>
	    		</tr>
	    		<tr>
	    			<td align="center">中奖时间</td> 
	    			<td><input type="text" id="wintime" name="wintime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"
	    				value="<s:date name="winPrize.winTime" format="yyyy-MM-dd HH:mm:ss"/>" /></td>
	    		</tr>
	    			    		<tr>
	    			<td align="center">中奖金额</td>
	    			<td>
	    			<input type="text" id="bonus" name="bonus" value="${winPrize.bonus}">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="center">排行榜类型</td> 
	    			<td> <input type="text" id="rankingType" name="rankingType" value="${winPrize.rankingType }"> </td>
	    		</tr>
	    		
	    		<tr>
		    		<td align="center" colspan="2">
		    			<input type="submit" value="提交到数据库" onclick="validate()"/>
		    			<input type="button" value="清空内容"  onclick="qq()"/>
		    		</td>
		    	</tr>
	    	</table>
	    </form>
	    <br/>
	    <hr />
	    
	    <form action="/oss/article/manageWinPrize.htm" method="post">
	    <div class="tab" style="margin-left:15px">
	    <table width="80%" cellspacing="3">
	    	    	<tr>
	    		<td align="center">用户名</td>
	    		<td align="center">彩种</td>
	    		<td align="center">中奖时间</td>
	    		<td align="center">中奖金额</td>
	    		<td align="center">排行榜类型</td>
	    		<td></td>
	    		<td></td>
	    	</tr>
	    	<tr></tr>
		    <s:iterator id="rs" value="page.result">
	  			<tr>
	  				<td align="center"><s:property value="userName"></s:property></td>
	  				<td align="center"><s:property value="type"></s:property></td>
	  				<td align="center"><s:date name="winTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	  				<td align="center"><s:property value="bonus"></s:property></td>
	  				<td align="center"><s:property value="rankingType"></s:property></td>
	  				<td align="center"><a href="/oss/article/manageWinPrize.htm?action=delete&id=<s:property value="id" />">删除记录</a></td>
	  				<td align="center"><a href="/oss/article/manageWinPrize.htm?action=detail&id=<s:property value="id" />">修改记录</a></td>
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
