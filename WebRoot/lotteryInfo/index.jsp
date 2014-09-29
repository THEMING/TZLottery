<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="彩票新闻,双色球开奖,足球数据,足彩分析,足球分析,足球对阵">
    <meta name="description" content="一彩票彩票资讯频道为您提供最新的彩票预测分析，包括彩票免费预测、彩票新闻、彩票信息、彩票分析、彩票中奖新闻、彩票技巧、福利彩票预测、体育彩票预测、竞技彩与数字彩新闻热点，专家推荐，缩水讲堂，彩票的相关投资策略。">
	<title>一彩票_彩票资讯_彩票开奖信息</title>
	<link rel="stylesheet" href="../css/default.css" type="text/css" />
	<link rel="stylesheet" href="../css/css.css" type="text/css" />
	<link rel="stylesheet" href="../css/lotteryInfo.css" type="text/css"/>
    <LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
	<script src="../js/jquery-1.4.4.min.js"></script>
	<style type="text/css">
		.STYLE2 {font-size: 14px}
		.STYLE3 {color: #666666}
		.STYLE4 {font-size: 14px; color: #666666; }
		.STYLE5 {color: #999999}
		.STYLE7 {
			font-size: 14px;
			font-family: "宋体";
			font-weight: bold;
			color:#960000
		}
		.STYLE8 {font-size: 14px; color: #990000; }
	</style>
    <script>
	$(document).ready(function() {
		
		function tab(o1,o2,c,e){
			$(o1).each(function(i){
				$(this).bind(e,function(){
					$(o2).hide("").eq(i).show();
					$(o1).each(function(){
						$(this).removeClass(c);
					});
					$(this).addClass(c);
				})
				if ($(this).hasClass(c)) {
					$(this).addClass(c);
					$(o2).hide().eq(i).show();
				}
			})
		}
		tab(".a_b",".f","on","mouseover");
	});
	</script>
</head>

<body>
<%@include file="/head.jsp" %>
<div class="container">
	<div class="left">
		<div class="left1">
			<div class="layout1">
				<table width="711" border="0">
				  <tr>
				    <td width="634" height="26"><span class="STYLE7">&nbsp;&nbsp;&nbsp;彩票新闻</span></td>
				    <td width="67"><a href="#" onfocus="this.blur();">更多&gt;&gt;</a></td>
				  </tr>
				</table>
			</div>
			<div class="layout2">
				<table width="711" border="0">
					<s:iterator value="newsList" id="rs">
						<tr>
				    		<td width="546" height="25"><span class="STYLE2"><span class="STYLE3">&nbsp;&nbsp;&nbsp;
				    			<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" onfocus="this.blur();">●&nbsp;<s:property value="title"/></a></span></span>
				    		</td>
				    		<td width="155"><span class="STYLE5"><s:date name="publishTime" format="yyyy-MM-dd HH:mm:ss"/></span></td>
				  		</tr>
					</s:iterator>
				</table>
			</div>
			
		</div>

		<div id="left2">
			<div class="layout1">
				<table width="711" border="0">
				  <tr>
				    <td width="634" height="26"><span class="STYLE7">&nbsp;&nbsp;&nbsp;专家推荐</span></td>
				    <td width="67"><a href="#" onfocus="this.blur();">更多&gt;&gt;</a></td>
				  </tr>
				</table>
			</div>
			<div class="layout2">
				<table width="711" border="0">
				<s:iterator value="expertList" id="rs">
				  <tr>
				    <td width="546" height="25"><span class="STYLE2"><span class="STYLE3">&nbsp;&nbsp;&nbsp;
				    	<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" onfocus="this.blur();">●&nbsp;<s:property value="title"/></a></span></span>
				    </td>
				    <td width="155"><span class="STYLE5"><s:date name="publishTime" format="yyyy-MM-dd HH:mm:ss"/></span></td>
				  </tr>
				</s:iterator>
			</table>
			</div>
			
			<!-- 
			<div class="layout3">
				<img src="/images/lotteryInfo/quxian_07.gif" width="662" height="7" style="margin:20px 20px 20px 20px;" />
			</div>
			 -->
		</div>
		
		<div id="left3">
			<div class="layout1">
				<table width="711" border="0">
				  <tr>
				    <td width="634" height="26"><span class="STYLE7">&nbsp;&nbsp;&nbsp;缩水讲堂</span></td>
				    <td width="67"><a href="#" onfocus="this.blur();">更多&gt;&gt;</a></td>
				  </tr>
				</table>
			</div>
			<div class="layout2">
				<table width="711" border="0">
				<s:iterator value="lectureList" id="rs">
				  <tr>
				    <td width="546" height="25"><span class="STYLE2"><span class="STYLE3">&nbsp;&nbsp;&nbsp;
				    	<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" onfocus="this.blur();">●&nbsp;<s:property value="title"/></a></span></span>
				    </td>
				    <td width="155"><span class="STYLE5"><s:date name="publishTime" format="yyyy-MM-dd HH:mm:ss"/></span></td>
				  </tr>
				</s:iterator>
			</table>
			</div>
			
			<!-- 
			<div class="layout3">
				<img src="/images/lotteryInfo/quxian_07.gif" width="662" height="7" style="margin:20px 20px 20px 20px;" />
			</div>
			 -->
		</div>
	</div>

	<div class="right">
		  <b class="b1"></b><b class="b2"></b><b class="b3"></b>
          <div class="bc">
            <div class="bt ">新用户注册</div>
            <div class="bb">
               <a href="/customer/register.htm" onfocus="this.blur();">注册</a>成为本站会员<br />即可免费领取一注彩票
            </div>
          </div>
          <b class="b3"></b><b class="b2"></b><b class="b1"></b>
          <div style="margin-top:10px;">
              <b class="b1"></b><b class="b2"></b><b class="b3"></b>
              <div class="bc">
                <div class="bt">竞彩软件</div>
                <div class="bf">
                  <div class="THE-UL">
                        <ul>
                            <li style="margin-left:9px;"><a href="/software" style="text-align:center" onfocus="this.blur()"><img src="../images/soft/jcsf.jpg"  width="61" height="61"/><br />369竞彩胜负软件</a></li>
                            <li style="margin-left:9px;"><a href="/software" style="text-align:center" onfocus="this.blur()"><img src="../images/soft/jcjq.jpg"  width="61" height="61"/><br />369竞彩进球软件</a></li>
                            <li style="margin-left:9px;"><a href="/software" style="text-align:center" onfocus="this.blur()"><img src="../images/soft/jcbqc.jpg"  width="61" height="61"/><br />369竞彩半全场软件 </a></li>
                        </ul>
                    </div>  
                </div>
              </div>
              <b class="b3 bb2"></b><b class="b2 bb2"></b><b class="b1 bb1"></b>
        </div>
        <!--<div class="d_body">
            <b class="b1"></b><b class="b2"></b><b class="b3"></b>
            <div class="d_top">
                <div class="ln"></div>
                <div class="a_b textcenter on">
                    <a href="javascript:">竞彩足球</a>
                </div>
                <div class="a_b textcenter">
                    <a href="javascript:">竞彩篮球</a>
                </div>
                <div class="a_b textcenter">
                    <a href="javascript:">足球胜负</a>
                </div>
                <div class="a_b textcenter">
                    <a href="javascript:">足球单场</a>
                </div>
            </div>
            <div class="d_main clr of">
                <div class="f">
                   <div class="jingcaizuqiu"><img src="../lotteryHall/images/jczq.jpg" width="50" height="50"></div>
                   <div class="list-name">
                     <ul>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                     </ul>
                   </div>
                </div>
                <div class="f">
                   <div class="jingcaizuqiu"><img src="../lotteryHall/images/jclq.jpg" width="50" height="50"></div>
                   <div class="list-name">
                     <ul>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                     </ul>
                   </div>
                </div>
                <div class="f">
                    <div class="jingcaizuqiu"><img src="../lotteryHall/images/14.gif" width="50" height="58"></div>
                   <div class="list-name">
                     <ul>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                     </ul>
                   </div>
                </div>
                <div class="f">
                    <div class="jingcaizuqiu"><img src="../lotteryHall/images/SOCER.gif" width="50" height="50"></div>
                    <div class="list-name">
                     <ul>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                        <li><a href="" onfocus="this.blur();">投注数据</a></li>
                     </ul>
                   </div>
                </div>
            </div>
            <div class="clr of"></div>
        </div>
        <div class="team-duizhen">
           <ul>
              <li><a href="#" onfocus="this.blur();"><img src="../images/lottery/yj.gif" width="50" height="50"> <br />意甲</a></li>
              <li><a href="#" onfocus="this.blur();"><img src="../images/lottery/dj.gif" width="50" height="50"> <br />德甲</a></li>
              <li><a href="#" onfocus="this.blur();"><img src="../images/lottery/xj.gif" width="50" height="50"> <br />西甲</a></li>
              <li><a href="#" onfocus="this.blur();"><img src="../images/lottery/team1.gif" width="50" height="50"> <br />英超</a></li>
           </ul>
        </div>-->
        <div style="margin-top:10px;">
          <b class="b1"></b><b class="b2"></b><b class="b3"></b>
          <div class="bc">
            <div class="bt">竞彩帮助</div>
            <div class="bb">
               <ul>
                  <li class="li1">
                     <h2>代购</h2>
                     <a target="_blank" href="/help/help-home-4-1-1.htm">单式</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-2.htm">复式</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-1-3-2.htm">机选</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-3.htm">追号</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-5.htm">和值</a> <br /> 
                     <a target="_blank" href="/help/help-home-4-1-6.htm">直选</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-7.htm">组三</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-8.htm">组六 </a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-11.htm">单式上传</a><br /> 
                    <a target="_blank" href="/help/help-home-4-1-10.htm">胆拖</a>
                  </li>
                  <li class="li2">
                      <h2>合买</h2>
                     <a target="_blank" href="/help/help-3-2.htm">发起合买</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-2.htm"> 参与合买</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-2-3.htm">资金冻结</a> <br />
                     <a target="_blank" href="/help/help-home-4-2-4.htm">发起人提成</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-2.htm">奖金分配</a> &nbsp;|&nbsp; 
                     <a target="_blank" href="/help/help-3-2.htm">尽快满员</a><br /><a target="_blank" href="/help/help-home-4-2-7.htm">方案保密</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-2-8.htm">方案流产</a>
                  </li>
                  <li class="li3">
                       <h2>账户</h2>
                     <a target="_blank" href="/help/help-1-1.htm">注册</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-1-1.htm">忘记密码</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-1-1.htm">修改资料</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-2-3.htm">身份 <br />验证</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-3-3.htm">支付宝登录</a> &nbsp;|&nbsp;  
                     <a target="_blank" href="/help/help-home-3-3.htm">QQ登录</a>
                  </li>
                  <li class="li4">
                      <h2>兑奖</h2>
                     <a target="_blank" href="/help/help-1-5.htm">查看中奖</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-1-6.htm">如何兑奖</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-5-3.htm">派奖时间</a> <br />
                  <a target="_blank" href="/help/help-home-1-4-4.htm">奖金扣税</a>
                  </li>
               </ul>
            </div>
          </div>
          <b class="b3 bb2"></b><b class="b2 bb2"></b><b class="b1 bb1"></b>
        </div>
		<!--<div class="layout6">
			<div class="layout6_1">
				<table width="110" height="29" border="0">
					<tr>
						<td width="95"><div align="center" class="STYLE8">网站公告</div></td>
					</tr>
				</table>
			</div>
			<div class="layout6_2">
			  <table width="267" border="0">
			    <s:iterator value="publicList" status="st">
			      <tr class="shortenTitle">
			        <td width="261" height="25"><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank">
			          &nbsp;●&nbsp;
			          <s:property value="title"/></td>
		          </tr>
		        </s:iterator>
		      </table>
			</div>
		</div>-->
	</div>
	<div class="clear"></div>
</div>
<%@include file="/foot.jsp" %>
</body>
</html>

