<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>一彩票_购彩大厅_竞彩足球胜平负选号投注合买</title>
	<meta name="description" content="竞彩足球胜平负选号投注合买是进行竞技彩足球彩种胜平负玩法选号,投注,合买的频道。" />
	<meta name="keywords" content="竞彩足球,竞彩,足彩,足彩胜平负,彩票,选号,投注,合买,购彩,369竞彩" />
	<link rel="stylesheet" href="../css/common.css" type="text/css" />
	<link rel="stylesheet" href="../css/default.css" type="text/css" />
	<link rel="stylesheet" href="../css/css.css" type="text/css" />
	<link rel="stylesheet" href="../css/guessFootball.css" type="text/css" />
    <LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/js/lottery/common.js"></script>
	<script type="text/javascript"  src="/js/lottery/guessFootball.js"></script>

	<script type="text/javascript">
		var g_playType = PLAY_TYPE_SPF;
		$(document).ready(function()
			{
				var username = "${customer.nickName}";
				if(username != "")
				{
					var tip = "<div>当前用户：<span class='red'>" + username + "</span></div>";
					tip += "<div>账户可用余额：" + "￥<span class='red'>${customer.wallet.balance}元</span></div>";
					$("#userTip").html(tip);
				}
				init();
			}
		);
		function sshc(){
	   window.location.href="/lottery/sshc.htm?date="+$("#date").val()
		}
	</script>
</head>

<body>
	<!-- head -->
	<%@include  file="../head.jsp"%>

	<div class="lottery_main">
		您现在的位置：<a href="/"  target="_blank" style="color:#FF0000">一彩票首页</a> &gt; <a href="/lotteryHall/">购彩大厅</a> &gt; 竞彩足球
		
		<!-- top_box  begin -->
		<div class="top_box">
			<div class="top_box_info" style="float:right; margin-right:5px">
				<div>销售时间：周一至周五 09:00～22:40&nbsp;&nbsp;周六至周日 09:00～00:40</div>
			</div>
		</div>
		<!-- top_box  end -->
		
		<!-- left_content_box  begin -->
		<div class="left_content_box">
		
		<div class="menu_filter">
			   
				<div class="sshc_select">
				 <div class="ss_le" style="float:left;">
					赛事回查
				 </div>	
				 <div class="ss_re" style="float:left; margin-left:20px;">
					<form id="form1" action="lottery/sshc.htm">
						<select id="date" name="date" onchange="sshc()">
							<option>选择日期</option>
							<%
								 Calendar date;
								for (int i = 0; i < 10; i++) {
									date = Calendar.getInstance();
									date.add(Calendar.DAY_OF_MONTH, -1*i);
									String str = "" + date.get(Calendar.YEAR);
									int ii = date.get(Calendar.MONTH) + 1;
									if (ii < 10)
										str = str + "0" + ii;
									else
										str = str + ii;
									int m = date.get(Calendar.DAY_OF_MONTH);
									if (m < 10)
										str = str + "0" + m;
									else
										str = str + m;
							%>
							<option value="<%=str%>">
								<%=str%>
							</option>
							<%
								}
							%>
						</select>
					</form>
	
					</div>
				</div>	
		  	</div><!-- menu_filter -->
			
			<div class="football_table">
				<table class="tournament_table">
					<colgroup>
						<col width="9%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="10%"/>
						<col width="4%"/>
						<col width="10%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
		
						<col width="8%"/>					
					</colgroup>
					<tbody>
						<tr>
							<th>赛事编号</th>
							<th>赛事</th>
							<th>
								开赛
							</th>
							<th>主队</th>
							<th>让球</th>
							<th>客队</th>
							
							<th>
								全场比分						
							</th>
							<th class="last_th">半场比分</th>
							<th class="last_th">平均欧赔</th>
							<th class="th_table_right_bet_title">让球胜平负</th>
							<th class="th_table_right_bet_title">总进球数</th>

							<th class="th_table_right_bet_title">半全场</th>
							
						</tr>
					</tbody>
				</table>
				
				
				
				<table class="tournament_table" id="d_2011-08-09">
					<colgroup>
						<col width="9%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="10%"/>
						<col width="4%"/>
						<col width="10%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
	
						<col width="8%"/>					
					</colgroup>
					<tbody>
						<s:iterator value="matchArrangelist">
		                	<tr id="line_<s:property value='boutIndex'/>">
								<td> <!-- 赛事编号 -->
									<label><s:property value="boutIndex"/></label>
								</td>								
								<td style="background:#660000;color:#fff;"> <!-- 赛事类型 -->
									<s:property value="matchName"/>
								</td>
								<td> <!-- 开始/截止时间 -->
									<span style="display:none" title= "截止时间：<s:date name='stopSaleTime' format='yyyy-MM-dd HH:mm:ss'/>"><s:date name="matchTime" format="HH:mm" /></span>
									<span id="stopSaleTime_<s:property value='boutIndex'/>" value=<s:date name='stopSaleTime' format='yyyy-MM-dd HH:mm:ss'/> title= "开赛时间：<s:date name='matchTime' format='yyyy-MM-dd HH:mm:ss'/>"><s:date name="matchTime" format="HH:mm" /></span>
								</td>
								<td> <!-- 主队 -->
									<span><s:property value="homeTeam"/></span>
								</td>
								<td> <!-- 让球 -->
									<span><s:property value="concede"/></span>
								</td>
								<td> <!-- 客队 -->
									<span><s:property value="awaryTeam"/></span>
								</td>
								<td><!-- 平均赔率/投注比例 -->
									${wholeScore}
								</td>
								<td>
									<a target="_blank" href="">${halfScore}</a>
								</td>
								<td>
								
										胜<s:property value="sop"/></br>
										平<s:property value="pop"/></br>
										负<s:property value="fop"/></br>
								
								</td>	
								<td class="table_right_bet"> <!-- 指数分析/析亚欧 -->
									${spSfp}
								</td>
								<td class="table_right_bet"> <!-- 胜 -->
									<label> ${spJqs}
								</td>
							
								<td class="table_right_bet"> <!-- 负 -->
									<label>${spBcsfp}
</label>
								</td>
								
							</tr>
						</s:iterator>
					</tbody>
				</table>
				
				
			
				<table class="tournament_table" id="d_2011-08-10">
					<colgroup>
						<col width="9%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="10%"/>
						<col width="4%"/>
						<col width="10%"/>
						<col width="15%"/>
						<col width="4%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
						<col width="8%"/>
					</colgroup>
					<tbody>
						
					</tbody>
				</table>
		
			</div>
		</div>
		<!-- left_content_box  end -->
	
		<!-- right_bet_box  begin -->
		
	<!-- foot -->
	<div class="clear"></div> 
	<%@include file="../foot.jsp"%>
</body>
</html>
