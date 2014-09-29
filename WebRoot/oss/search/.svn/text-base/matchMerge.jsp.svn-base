<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/prize_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />
<script src="../../js/jquery-1.4.4.min.js"></script>
<script src="../../js/lottery/common.js"></script>
<script type="text/javascript">
	function subm(max)
	{
		var substr="";
		var substrother="";
		var num=0;
		var str="";
		var strother="";
		for(var i=0;i<max;i++)
		{
			if($("#select_"+i).val()=="标准")
			{
				substr = $("#team_"+i).text();
				str    = $("#index_"+i).text();
				num++;
			}
		}
		
		if(num=1)
		{
			$("#hidden").val(substr);
			$("#index").val(str);
		} else {
			alert("你必须选择唯一一个标准队名");
			return;
		}
		
		
		for(var i=0;i<max;i++)
		{
			if($("#select_"+i).val()=="非标准")
			{
				substrother += $("#team_"+i).text()+"|";
				$("#hiddenOriginal").val(substrother);
				strother += $("#index_"+i).text()+"|";
				$("#indexOriginal").val(strother);
			}
		}
		$("#form").submit();
	}
</script>
</head>
  
  <body>
  
  	<span style="color:red"><s:actionmessage/></span>
  	
  	<form action="/oss/search/searchMatch.htm?action=list" method="post">
  		历史队名<input type="text" name="keyWord" />
  		赛事名称<input type="text" name="leagueType" />
  		<input type="submit" value="搜索" />
  	</form>
  
  	<div>
  	<form action="/oss/search/searchMatch.htm?action=update" method="post" id="form">
  	<input type="hidden" id="hidden" name="standardTeam"></input>
  	<input type="hidden" id="hiddenOriginal" name="originalTeam"></input>
  	<input type="hidden" id="index" name="id" />
  	<input type="hidden" id="indexOriginal" name="idOriginal" />
  		<table border="1" width="100%">
  			<tr>	
  				<td>序号</td>
  				<td>选择</td>
  				<td>队名</td>
  				<td>赛事</td>
  			</tr>
  			<s:set name="max" value="0" /> 			
  			<s:iterator value="mappinglist"  status="st">
  				<tr>
  					<td id="index_<s:property value='#st.index' />" ><s:property value="id" /></td>				
  						<td>
  							<select id="select_<s:property value='#st.index' />">
  								<option>请选择</option>
  								<option>标准</option>
  								<option>非标准</option>
  							</select>
  						</td>
  						<td id="team_<s:property value='#st.index' />" ><s:property value="matchHistoryName" /></td>
  					<td><s:property value="matchName"></s:property></td>
  				</tr>
  				<s:if test="#st.last"><s:set name="max" value="#st.index+1"/></s:if>
  			</s:iterator>
  			<tr><td colspan="4" align="center"><input type="button" value="确认更改" onclick="subm(<s:property value='#max'/>)"/></td></tr>	
  		</table>
  	</form>
  	</div>
  </body>
</html>
