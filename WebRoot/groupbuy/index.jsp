<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>一彩票_合买大厅_足彩合买_竞彩合买_双色球合买_彩票合买</title>
    <meta name="description" content="一彩票合买频道为您提供足彩合买、双色球合买、3D合买、大乐透合买彩种的合买，还包括合买名人的合买方案推荐。开奖信息实时，派奖及时，缩水过滤，彩票软件打造中国中奖率最高的网络彩票平台。" />
    <meta name="keywords" content="彩票合买,足彩合买, 双色球合买,体彩合买,福彩合买,合买方案" />
    <link rel="stylesheet" href="../css/common.css" type="text/css" />
    <link rel="stylesheet" href="../css/groupbuy.css" type="text/css" />
    <link rel="stylesheet" href="../css/default.css" type="text/css" />
	<link rel="stylesheet" href="../css/css.css" type="text/css" />
    <link rel="shortcut icon" href="../chart/favicon.ico" /> 
    <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	function select(type) {
		$("#type").val(type);
		$("#action").val("index");
		$("form").submit();
	}
	function clickItem(obj, index) 
	{
		var dt = $(obj).next("");
		$(obj).parent().siblings().find("ul");
		dt.slideToggle();
		changeIcon($(obj));
		if(index != last_index) {
			changeIcon($("#li_"));
		}
	}
	
	function clickSubItem(obj, sub_index, type) 
	{
		select(type);
	}
	
	function listCurrentMatch()
	{
		$("#action").val("listCurrentMatch");
		$("form").submit();
	}
	</script>
</head>
<body>
<%@include  file="/head.jsp"%>
   <div class="container">
       <div class="groupbuy_main">
	   <div class="left">
			<div class="title">合买大厅</div>
			<div class="menu">
		    	<ul id="gallery">
                    <li style="height:20px; margin-bottom:30px;-margin-bottom:10px; margin-top:10px;" ><a class="on" onfocus="this.blur();" href="javascript:listCurrentMatch();" onclick="changetabname(0);">全部彩种</a></li>
                    <li style="height:29px; background-color:#F90; line-height:2; text-align:center; letter-spacing:5px; font-size:14px; color:#FFF;">福彩</li>
                    <li id="sub_li_1"><a href="javascript:clickSubItem(this, 1, 'ssq');" onfocus="this.blur();" onclick="changetabname(1);"><img src="../images/lottery/pic_ssq.jpg" width="20" height="20"> &nbsp;&nbsp;双色球</a></li>
                    <li id="sub_li_2"><a href="javascript:clickSubItem(this, 2, '3d');" onfocus="this.blur();" onclick="changetabname(2);"><img src="../images/lottery/pic_fc3d.jpg" width="20" height="20"> &nbsp;&nbsp;福彩3D</a></li>
                    <li id="sub_li_3"><a href="javascript:clickSubItem(this, 3, 'qlc');" onfocus="this.blur();" onclick="changetabname(3);"><img src="../images/lottery/pic_qlc.jpg" width="20" height="20"> &nbsp;&nbsp;七乐彩</a></li>
                    <li style="height:29px; background-color:#F90; line-height:2; text-align:center; letter-spacing:5px; font-size:14px; color:#FFF;">体彩</li>
                    <li id="sub_li_4"><a href="javascript:clickSubItem(this, 4, 'dlt');" onfocus="this.blur();" onclick="changetabname(4);"><img src="../images/lottery/pic_dlt.jpg" width="20" height="20"> &nbsp;&nbsp;超级大乐透</a></li>
                    <li id="sub_li_5"><a href="javascript:clickSubItem(this, 5, 'pls');" onfocus="this.blur();" onclick="changetabname(5);"><img src="../images/lottery/pic_pls.jpg" width="20" height="20"> &nbsp;&nbsp;排列三</a></li>
                    <li id="sub_li_6"><a href="javascript:clickSubItem(this, 6, 'plw');" onfocus="this.blur();" onclick="changetabname(6);"><img src="../images/lottery/pic_plw.jpg" width="20" height="20"> &nbsp;&nbsp;排列五</a></li>
                    <li id="sub_li_7"><a href="javascript:clickSubItem(this, 7, 'qxc');" onfocus="this.blur();" onclick="changetabname(7);"><img src="../images/lottery/pic_qxc.jpg" width="20" height="20"> &nbsp;&nbsp;七星彩</a></li>
                    <li style="height:29px; background-color:#F90; line-height:2; text-align:center; letter-spacing:5px; font-size:14px; color:#FFF;">足彩</li>
                    <li id="sub_li_8"><a href="javascript:clickSubItem(this, 8, '14sfc');" onfocus="this.blur();" onclick="changetabname(8);"><img src="../images/lottery/pic_sfc.jpg" width="20" height="20"> &nbsp;&nbsp;足球胜负彩</a></li>
                    <li id="sub_li_9"><a href="javascript:clickSubItem(this, 9, 'r9');" onfocus="this.blur();" onclick="changetabname(9);"><img src="../images/lottery/pic_rx9.jpg" width="20" height="20"> &nbsp;&nbsp;足球任9</a></li>
                    <li id="sub_li_10"><a href="javascript:clickSubItem(this, 10, '6cb');" onfocus="this.blur();" onclick="changetabname(10);"><img src="../images/lottery/pic_lcb.jpg" width="20" height="20"> &nbsp;&nbsp;足彩6场半</a></li>
                    <li id="sub_li_11"><a href="javascript:clickSubItem(this, 11, '4cjq');" onfocus="this.blur();" onclick="changetabname(11);"><img src="../images/lottery/pic_scj.jpg" width="20" height="20"> &nbsp;&nbsp;四场进球</a></li>
                    <li id="sub_li_12"><a href="javascript:changetabname(12);" onfocus="this.blur();" onclick="clickSubItem(this, 12, 'jczq');"><img src="../images/lottery/jingzucai1.gif" width="20" height="20"> &nbsp;&nbsp;竞彩足球</a></li>
                    <li id="sub_li_13"><a href="javascript:changetabname(13);" onfocus="this.blur();" onclick="clickSubItem(this, 13, 'jclq');"><img src="../images/lottery/jingzucai1.gif" width="20" height="20"> &nbsp;&nbsp;竞彩篮球</a></li>
		        </ul>
                <script language="javascript">
				function changetabname(c,cl)
				{
					var d=document.getElementById("gallery").getElementsByTagName("a");
					if(!cl)
					{
						writeCookie("hovers",c,222);
					}
					c=readCookie("hovers")?readCookie("hovers"):c;
					for(i=0;i<d.length;i++)
					{
						d[i].className=i==c?"on":"";
					}
				}
				function writeCookie(name, value, hours)
				{
				  var expire = "";
				  if(hours != null)
				  {
					expire = new Date((new Date()).getTime() + hours * 3600000);
					expire = "; expires=" + expire.toGMTString();
				  }
				  document.cookie = name + "=" + escape(value) + expire;
				}
				
				function readCookie(name)
				{
				  var cookieValue = "";
				  var search = name + "=";
				  if(document.cookie.length > 0)
				  { 
					offset = document.cookie.indexOf(search);
					if (offset != -1)
					{ 
					  offset += search.length;
					  end = document.cookie.indexOf(";", offset);
					  if (end == -1) end = document.cookie.length;
					  cookieValue = unescape(document.cookie.substring(offset, end))
					}
				  }
				  return cookieValue;
				}
				function clear()
				{
					writeCookie("hovers","",222);
					document.location=document.location.href;
				}
				changetabname(0,1)
				</script>
   			</div> 
		</div>		
			<div class="right">
                <div class="start-groupBUY">
                <s:if test="list == null">
                <s:iterator value="page.result" status="st">               
                <s:if test="#st.index == 0">
	                <c:if test="${englishType!='jclq'}">
	 	                <a href="/lottery/index.htm?lotteryType=${englishType}" style="margin-left:100px;"><img src="../images/369caibg/fqhm.png" alt="发起合买" style="border:1px solid #f30;"></a>
	                    <p><strong>${term.type }合买方案&nbsp;&nbsp;&nbsp;第${term.termNo }期</strong></p>
	                    <p>合买截止时间：<s:date name="term.stopTogetherSaleTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;代购截止时间：<s:date name="term.stopSaleTime" format="yyyy-MM-dd HH:mm:ss"/> 
                    	</p>    
	                </c:if>
	                <c:if test="${englishType=='jclq'}">
	                	<a href="/lottery/JCLQSFIndex.htm" style="margin-left:10px;"><img src="../images/369caibg/fqhm.png" alt="发起合买" style="border:1px solid #f30;"></a>
	                </c:if>
				</s:if>			                
                </s:iterator>
                </s:if>
                <s:elseif test="list != null && list.size() == 0">                	
                </s:elseif>
                <s:else>                
                <p><strong>竞彩胜平负</strong></p>
                  <table width="80%" border="0" style="margin-left:30px; margin-bottom:15px;" bgcolor="#cccccc" cellspacing="1" cellpadding="0">
                      <colgroup>
                       <col width="22%">
                       <col width="5%">
                       <col width="22%">
                       <col width="50%">
                      </colgroup>
                      <tr align="center" height="30" bgcolor="#A2CD5A" style="color:#fff;">
                        <td align="right">主队</td>
                        <td></td>
                        <td align="left">客队</td>
                        <td>截止时间</td>
                      </tr>
                   <s:iterator value="list" status="s">
                      <tr>
                        <td height="20" align="right" bgcolor="#F08080" style="color:#fff; font-size:14px;">${homeTeam}</td>
                        <td height="20" align="center" bgcolor="#336633" style="color:#fff; font-size:14px; font-weight:bold;">vs</td>
                        <td height="20" align="left" bgcolor="#836FFF" style="color:#fff; font-size:14px;">${awaryTeam}</td>
                        <td height="20" align="center" bgcolor="#FAFAD2">代购截止时间：<s:date name="stopSaleTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      </s:iterator>
                    </table>
                    <a href="/lottery/JCZQIndex.htm" style="margin-left:580px; ">
                      <img src="../images/369caibg/fqhm.png" alt="发起合买" style="border:1px solid #f30;">
                    </a> 
                  </s:else>              
                </div>
                <form action="#" method="post">
                <input type="hidden" value="index" id="action" name="action"/>
				<div class="index_top">
		        	<span class="top_left"> 状态筛选：
						<s:select id="communityType" list="types" name="communityType" onchange="onChoose()" headerKey="" headerValue="请选择.."/>
					</span>
		            <span class="top_right">
						搜索用户：
						<input type="text" name="name" class="text" value="${name}"/>
		                <select name="lotteryType" id="type">
							<option value="">选择彩种..</option>
							<option value="ssq">双色球</option>
							<option value="dlt">大乐透</option>
							<option value="3d">福彩3D</option>
							<option value="pls">排列三</option>
							<option value="plw">排列五</option>
							<option value="qxc">七星彩</option>
							<option value="qlc">七乐彩</option>
							<option value="14sfc">足球胜负彩</option>
							<option value="r9">足球任9</option>
							<option value="6cb">足彩6场半</option>
							<option value="4cjq">四场进球</option>
							<option value="jczq">竞彩足球</option>
							<option value="jclq">竞彩篮球</option>
		    			</select>
		                <input type="submit" value="搜索"/>
					</span>
		            <div class="clear"></div>
					
					<div class="tab_box">
						<%@include file="group_table.jsp" %>
					</div>
					<div ><jsp:include page="../util/page.jsp"></jsp:include></div>
				</form>
		        </div>
		    <div class="clear"></div>
			</div>
		
	</div>
       <!-- foot -->
       
   </div>
   
   <div class="clear"></div>
<%@include  file="/foot.jsp"%>
</body>
</html>