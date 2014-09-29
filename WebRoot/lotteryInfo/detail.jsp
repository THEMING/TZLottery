<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${article.title}_一彩票_彩票资讯_彩票开奖信息</title>
	<link rel="stylesheet" href="../css/default.css" type="text/css" />
	<link rel="stylesheet" href="../css/css.css" type="text/css" />
	<link rel="stylesheet" href="../css/lotteryInfo.css" type="text/css"/>
    <LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
    <script src="../js/jquery-1.4.4.min.js"></script>
	<style type="text/css">
		.STYLE6 {
			font-family: "黑体";
			font-size: 18px;
			font-weight:bold;
			color: #000000;
		}
		.STYLE7 {
			font-size: 14px;
			color: #333333;
		}
		.STYLE8 {
			font-size: 14px;
			color: #990000;
		}
	</style>
	<script type="text/javascript">
        function doZoom(size) {
            var zoom = document.all ? document.all['Zoom'] : document.getElementById('Zoom');
            zoom.style.fontSize = size + 'px';
        }
    </script>



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

<body style="font:'Trebuchet MS', Arial, Helvetica, sans-serif">
<%@include file="/head.jsp" %>
<div class="container">
	<div class="left">
		<div class="layout4">
			<table width="711" border="0">
				<tr>
			    	<td width="705" height="56"><div align="center" class="STYLE6">${article.title}</div></td>
			  	</tr>
				<tr>
					<td>
                       <div align="center"><s:date name="article.publishTime" format="yyyy-MM-dd HH:mm:ss"/> 作者：${article.author}</div>
                    </td>
                </tr>
                <tr>
                    <td> 
                         <div align="right" style="margin-right:100px;">
                        字体大小：<a href="javascript:doZoom(16)">大</a>&nbsp;&nbsp;<a href="javascript:doZoom(14)">中</a>&nbsp;&nbsp;<a href="javascript:doZoom(12)">小</a>
                         </div>
                    </td>
                </tr>
                <tr>  
                    <td>
                    </td>
				</tr>
			</table>
		</div>
		<div class="layout4_1" id="Zoom">
         ${article.content}    
         <!-- JiaThis Button BEGIN -->
         <hr style="border:1px solid #fc9" />
            <div id="ckepop">
                <span class="jiathis_txt">分享到：</span>
                <a class="jiathis_button_tools_1"></a>
                <a class="jiathis_button_tools_2"></a>
                <a class="jiathis_button_tools_3"></a>
                <a class="jiathis_button_tools_4"></a>
                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
            </div>
            <script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
            <!-- JiaThis Button END -->  
            <div style="height:20px;">
            </div>
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
