<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>一彩票票网_合买大厅_足彩合买_竞彩合买_双色球合买_彩票合买</title>
    <meta name="description" content="一彩票票网合买频道为您提供足彩合买、双色球合买、3D合买、大乐透合买彩种的合买，还包括合买名人的合买方案推荐。开奖信息实时，派奖及时，缩水过滤，彩票软件打造中国中奖率最高的网络彩票平台。" />
    <meta name="keywords" content="彩票合买,足彩合买, 双色球合买,体彩合买,福彩合买,合买方案" />
    <link rel="stylesheet" href="../css/common.css" type="text/css" />
    <link rel="stylesheet" href="../css/groupbuy.css" type="text/css" />
    <link rel="stylesheet" href="../css/default.css" type="text/css" />
	<link rel="stylesheet" href="../css/css.css" type="text/css" />
    <link rel="shortcut icon" href="../chart/favicon.ico" /> 
    <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
    	
    </script>
</head>
<body>
	<s:property value="hostName"/>(总<s:property value="host.rank"/>/主<s:property value="host.hrank"/>/客<s:property value="host.arank"/>)<br />
	<s:property value="visitName"/>(总<s:property value="visit.rank"/>/主<s:property value="visit.hrank"/>/客<s:property value="visit.arank"/>)<br />
	让<s:property value="matchArrange.concede"/><br />
	<s:property value="matchArrange.sop"/>
	<s:property value="matchArrange.pop"/>
	<s:property value="matchArrange.fop"/><br />
	总轮次
	<s:property value="hostLeagueRank.allRound"/>
	<s:property value="hostLeagueRank.winRound"/>胜
	<s:property value="hostLeagueRank.drowRound"/>平
	<s:property value="hostLeagueRank.loseRound"/>负<br />
	总轮次
	<s:property value="visitLeagueRank.allRound"/>
	<s:property value="visitLeagueRank.winRound"/>胜
	<s:property value="visitLeagueRank.drowRound"/>平
	<s:property value="visitLeagueRank.loseRound"/>负<br />
	主    场
	<s:property value="hostLeagueRank.hallRound"/>
	<s:property value="hostLeagueRank.hwinRound"/>胜
	<s:property value="hostLeagueRank.hdrowRound"/>平
	<s:property value="hostLeagueRank.hloseRound"/>负<br />
	主    场
	<s:property value="visitLeagueRank.hallRound"/>
	<s:property value="visitLeagueRank.hwinRound"/>胜
	<s:property value="visitLeagueRank.hdrowRound"/>平
	<s:property value="visitLeagueRank.hloseRound"/>负<br />
	客    场
	<s:property value="hostLeagueRank.aallRound"/>
	<s:property value="hostLeagueRank.awinRound"/>胜
	<s:property value="hostLeagueRank.adrowRound"/>平
	<s:property value="hostLeagueRank.aloseRound"/>负<br />
	客    场
	<s:property value="visitLeagueRank.aallRound"/>
	<s:property value="visitLeagueRank.awinRound"/>胜
	<s:property value="visitLeagueRank.adrowRound"/>平
	<s:property value="visitLeagueRank.aloseRound"/>负<br />
	
	
	<table>
		<s:iterator value="fixedBattleList">
			<tr>
			<td>${league.name}</td>
			<td>${hostTeam.name}</td>
			<td>${visitTeam.name}</td>
			<td>${hostScore} ：${visitScore}</td>
			<td>${leagueName}</td>
			<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
	</table><br />
	<table>
		<s:iterator value="battleList">
			<tr>
			<td>${league.name}</td>
			<td>${hostTeam.name}</td>
			<td>${visitTeam.name}</td>
			<td>${hostScore} ：${visitScore}</td>
			<td>${leagueName}</td>
			<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
	</table><br />
	<table>
		<s:iterator value="hostOverList">
			<tr>
			<td>${league.name}</td>
			<td>${hostTeam.name}(${hostTeam.rank})</td>
			<td>${visitTeam.name}(${visitTeam.rank})</td>
			<td>${hostScore} ：${visitScore}</td>
			<td>${leagueName}</td>
			<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
	</table><br />
	<table>
		<s:iterator value="visitOverList">
			<tr>
			<td>${league.name}</td>
			<td>${hostTeam.name}(${hostTeam.rank})</td>
			<td>${visitTeam.name}(${visitTeam.rank})</td>
			<td>${hostScore} ：${visitScore}</td>
			<td>${leagueName}</td>
			<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
	</table><br />
	<table>
		<s:iterator value="hostFeatureList" status="st">
		<s:if test="#st.index >= 1">
			<tr>
			<td>${league.name}</td>
			<td>${hostTeam.name}(${hostTeam.rank})</td>
			<td>${visitTeam.name}(${visitTeam.rank})</td>
			<td>${hostScore} ：${visitScore}</td>
			<td>${leagueName}</td>
			<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:if>
		</s:iterator>
	</table><br />
	<table>
		<s:iterator value="visitFeatureList" status="st">
		<s:if test="#st.index >= 1">
			<tr>
			<td>${league.name}</td>
			<td>${hostTeam.name}(${hostTeam.rank})</td>
			<td>${visitTeam.name}(${visitTeam.rank})</td>
			<td>${hostScore} ：${visitScore}</td>
			<td>${leagueName}</td>
			<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:if>
		</s:iterator>
	</table><br />
</body>
</html>