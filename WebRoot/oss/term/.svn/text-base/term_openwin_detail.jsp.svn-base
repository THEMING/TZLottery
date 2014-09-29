<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>开奖明细</title>
		<meta name="heading" content="彩种配置" />
		<script type="text/javascript">
 
</script>

	</head>
	<body>
		<label>${type}-开奖明细</label>
		<br />
		<br />
		<br />
		   <s:iterator id="rs" value="prizelevel">
		     <br />
		    ${rs.name }　　${rs.betNum } 注　　${rs.prize}元
			<br />
			<s:if test="'大乐透'==#rs.term.type.toString()">
			   <s:if test="'八等奖'!=#rs.name && '12选2'!=#rs.name">
			 　　　　${rs.name }追加　　${rs.addBetNum } 注　　${rs.addPrize}元
			<br />
			 </s:if>
			</s:if>
			</s:iterator>
		
		<br />
		<br />
		<br />
	</body>
</html>
