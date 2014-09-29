<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>369竟彩网_购彩大厅_竞彩篮球_胜负差</title>
<meta name="description" content="一彩票购彩大厅竞彩足球胜平负购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。" />
<meta name="keywords" content="让球胜平负,竞彩足彩胜平负, 竞彩足彩缩水过滤软件,竞彩足彩合买，让球胜平负, 竞彩胜平负合买" />
<link rel="stylesheet" href="/css/common.css" type="text/css" />
<link rel="stylesheet" href="/css/default.css" type="text/css" />
<link rel="stylesheet" href="/css/css.css" type="text/css" />
<link rel="stylesheet" href="/css/guessFootball.css" type="text/css" />
<link rel="stylesheet" href="/css/basketball.css" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/jquery-ui-1.8.16.custom.css"/>
<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/jquery.bgiframe-2.1.2.js"></script>
<script src="../js/jquery.ui.core.js"></script>
<script src="../js/jquery.ui.widget.js"></script>
<script src="../js/jquery.ui.mouse.js"></script>
<script src="../js/jquery.ui.button.js"></script>
<script src="../js/jquery.ui.position.js"></script>
<script src="../js/jquery.ui.dialog.js"></script>
<script src="../js/jquery.effects.core.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
<script type="text/javascript" src="../js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="../js/lottery/guessBasketball.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
<script type="text/javascript">
	var g_playType = 3;		
	var ishide = 0;

	$(document).ready(function()
		{
			var username = "${customer.nickName}";
			 login();
			if(username != "")
			{
				var tip = "<div>当前用户：<span class='red'>" + username + "</span></div>";
				tip += "<div>账户可用余额：" + "￥<span class='red'>${customer.wallet.balance}元</span></div>";
				$("#userTip").html(tip);
			}
			
			var danguan="${danduo}";
			if(danguan==null||danguan==""){
				danguan_duoguan=2;
				$("#tabbb1").addClass("dgtztabed1")
				}
			else{
				danguan_duoguan=1;
				$("#tabbb2").addClass("normal1")
			}
			init();	
		}
	);
	function lqsshc(){
		window.location.href="/lottery/lqsshc.htm?date="+$("#date").val()
	}
	
	function clickdan()
	{

		window.location.href="/lottery/JCLQSFCIndex.htm?danduo=1";
	}
	
	function clickduo()
	{

		window.location.href="/lottery/JCLQSFCIndex.htm";
	}

	function shouqi()
	{

		if(ishide == 0)
		{
			$("#liansai_lb").hide();
			$("#liansai_img").attr("src","../images/lottery/dakai.jpg");
			ishide = 1;
		}
		else
		{
			$("#liansai_lb").show();
			$("#liansai_img").attr("src","../images/lottery/shouqi.jpg");
			ishide = 0;
	
		}
	}
	
	

</script>

<script type="text/javascript">
	function addLoadEvent(func) {
	  var oldonload = window.onload;
	  if (typeof window.onload != 'function') {
		window.onload = func;
	  } else {
		window.onload = function() {
		  oldonload();
		  func();
		}
	  }
	}
	
	
	function addClass(element,value) {
	  if (!element.className) {
		element.className = value;
	  } else {
		newClassName = element.className;
		newClassName+= " ";
		newClassName+= value;
		element.className = newClassName;
	  }
	}
	
	
	function stripeTables() {
		var tables = document.getElementsByTagName("table");
		for (var m=0; m<tables.length; m++) {
			if (tables[m].className == "oddscontent-table") {
				var tbodies = tables[m].getElementsByTagName("tbody");
				for (var i=0; i<tbodies.length; i++) {
					var odd = true;
					var rows = tbodies[i].getElementsByTagName("tr");
					for (var j=0; j<rows.length; j++) {
						if(rows[j].className == "line")
						{
							if (odd == false) {
								odd = true;
							} else {
								addClass(rows[j],"old");
								odd = false;
							}
						}
					}
				}
			}
		}
	}
	
	
	function highlightRows() {
	  if(!document.getElementsByTagName) return false;
		var tables = document.getElementsByTagName("table");
		for (var m=0; m<tables.length; m++) {
			if (tables[m].className == "oddscontent-table") {
				  var tbodies = tables[m].getElementsByTagName("tbody");
				  for (var j=0; j<tbodies.length; j++) {
					 var rows = tbodies[j].getElementsByTagName("tr");
					 for (var i=0; i<rows.length; i++) {
						 if(rows[i].className == "line")
						 {
						   rows[i].oldClassName = rows[i].className
						   rows[i].onmouseover = function() {
							  if( this.className.indexOf("select") == -1)
								 addClass(this,"next");
						   }
						   rows[i].onmouseout = function() {
							  if( this.className.indexOf("select") == -1)
								 this.className = this.oldClassName
						   }
						 }
					 }
				  }
			}
		}
	}
	addLoadEvent(stripeTables);
	addLoadEvent(highlightRows);
	//addLoadEvent(lockRow);	
</script>
</head>

<body>
	<!-- head -->
<%@include  file="../head.jsp"%>
	<div class="lottery_main">
        <div class="ql_weizhi">
            您现在的位置：<a href="/"  style="color:#FF0000">369竞彩首页</a>&gt;
                         <a href="/lottery/JCLQSFIndex.htm">竞彩篮球</a>
        </div>
        <div class="lanqiutabed">
            <ul>
              <li><a href="/lottery/JCLQSFIndex.htm">胜负</a></li>
              <li><a href="/lottery/JCLQRFSFIndex.htm">让分胜负</a></li>
              <li class="tabed"><a href="/lottery/JCLQSFCIndex.htm" style="color:#fff;font-size:14px;font-weight:bold;">胜分差</a></li>
              <li><a href="/lottery/JCLQDXFIndex.htm">大小分</a></li>
              <li class="zhiboalive"><a href="/help/helplanqiu.htm" target="_blank" style="color:#fff">比分直播</a></li>
              <li class="nothing">销售时间：周一到周五 当天比赛截止22:40  周六/日当天比赛截止00:40</li>
            </ul>
        </div>
        <div class="BGTOP-LEFT">
            <div class="one_or_mul-change">
                  <ul>
                     <li id="tabbb1" onclick="clickduo()">过关投注</li>
                     <li id="tabbb2" onclick="clickdan()">单关投注</li>
                  </ul> 
                  <div class="ql_sshc">
                    <div class="ql_ss">
                    赛事回查:
                    </div>
                    <div class="ql_select">
                    <form id="form1" action="lottery/lqsshc.htm" >
                        <select id="date" name="date" onchange="lqsshc()">
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
               </div>
            <div class="ql_liebiao2">
                    <div class="ql_jingcai">
                             选择场次：
              </div>
                    <div class="ql_shouqi">
                           <a href="#"><img id="liansai_img" src="../images/lottery/shouqi.jpg" onclick="shouqi();"></a>
                    </div>
          </div> 
            <div class="ql_liebiao" id="liansai_lb" style="float:left">
               <div  class="ql_yiceng">
                  <div class="ql_show">
    
                    <div class="ql_showzi">
                        <a href="javascript:quanxuan();" >全部显示</a>
                    </div>
                 </div>
                <div class="ql_hide">
                    <div class="ql_hidezi">
                        <a href="javascript:fanxuan();">全部隐藏</a>
                    </div>
    
                </div> 
                
            </div>
                        
            
                <div class="ql_yinglun">
                
                    <%for(Object o: (Set)request.getAttribute("hh")){
                        %>
                    <div class="ql_ck2">
                        <div class="ql_chbox">
                            <input id="lg<%=o %>" type="checkbox" checked="true" value="<%=o %>"  class="chbox" onclick="sel(this)"/>
                        </div>
    
                        <div class="ql_England">
                            <label for="lg<%=o %>"><%=o %></label>
                        </div>
                    </div>
                    <%} %>   
                      
                             
                </div>
    
            
           </div>
            <div class="clear"></div>
     		<!--胜负差表格开始-->
            <div class="tabcont">
               <table width="100%" class="tablefor_shenpingfu" cellpadding="0" cellspacing="0">
                  <colgroup>
                     <col width="10%" />
                     <col width="10%" />
                     <col width="8%" />
                     <col width="20%" />
                     <col width="12%" />
                     <col width="8%" />
                     <col width="8%" />
                     <col width="8%" />
                     <col width="8%" />
                     <col width="8%" />
                  </colgroup>
                  <tbody>
                      <tr>
                        <td>赛事编号</td>
                        <td>赛事类型</td>
                        <td>截止时间</td>
                        <td>客队 VS 主队</td>
                        <td>1-5</td>
                        <td>6-10</td>
                        <td>11-15</td>
                        <td>16-20</td>
                        <td>21-25</td>
                        <td>26+</td>
                      </tr>
                   </tbody>
                </table>
                <table width="100%" class="oddscontent-table" cellspacing="0" cellpadding="0">
                    <colgroup>
                         <col width="10%" />
                         <col width="10%" />
                         <col width="8%" />
                         <col width="20%" />
                         <col width="52%" />              
                  </colgroup>
             
                  <tbody>
                  	<s:iterator value="matchArrangelist">
                  		<tr class="line" id="line_<s:property value='boutIndex'/>">
							<td> <!-- 赛事编号 -->
								<label><s:property value="boutIndex"/></label>
							</td>								
							<td class="colored"> <!-- 赛事类型 -->
								<s:property value="matchName"/>
							</td>
							<td> <!-- 开始/截止时间 -->
								<!--<span style="display:none" title= "截止时间：<s:date name='stopSaleTime' format='yyyy-MM-dd HH:mm:ss'/>"><s:date name="matchTime" format="HH:mm" /></span>-->
								<!--<span id="stopSaleTime_<s:property value='boutIndex'/>" value=<s:date name='stopSaleTime' format='yyyy-MM-dd HH:mm:ss'/> title= "开赛时间：<s:date name='matchTime' format='yyyy-MM-dd HH:mm:ss'/>"><s:date name="stopSaleTime" format="HH:mm" /></span>-->
								<span id="jiezhi_<s:property value='boutIndex'/>"  title= "开赛时间：<s:date name='matchTime' format='yyyy-MM-dd HH:mm:ss'/>">>00:00</span>								
							</td>
							<td> <!-- 主队 -->
								<table width="100%" style="border-left:1px solid #ccc;" border="0" cellspacing="0" cellpadding="0">
	                                <colgroup>
                                       <col width="100%"/>
	                                </colgroup>
	                                <tbody>
	                                  <tr>
                                        <td>客队: <span><s:property value="awaryTeam"/></span></td>
	                                  </tr>
	                                  <tr>
                                        <td>主队: <span><s:property value="homeTeam"/></span></td>
	                                  </tr>
	                                </tbody>
                           	 	</table>
                           	 </td>
                         <td>
								<table width="100%" style="border-left:1px solid #ccc;" border="0" cellspacing="0" cellpadding="0">
	                                <colgroup>
                                       <col width="5%"/>
	                                   <col width="7%"/>
	                                   <col width="8%"/>
	                                   <col width="8%"/>
	                                   <col width="8%"/>
	                                   <col width="8%"/>
	                                   <col width="8%"/>
	                                </colgroup>
	                                <tbody>
	                                  <tr>
                                        <td>客胜</td>
	                                    <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 1 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,1)"><font class="red" id="speilv0_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                    <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 2 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,2)"><font class="red" id="speilv1_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 3 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,3)"><font class="red" id="speilv2_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 4 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,4)"><font class="red" id="speilv3_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 5 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,5)"><font class="red" id="speilv4_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 6 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,6)"><font class="red" id="speilv5_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                  </tr>
	                                  <tr>
                                        <td>主胜</td>
	                                    <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 7 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,7)"><font class="red" id="fpeilv0_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                    <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 8 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,8)"><font class="red" id="fpeilv1_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 9 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,9)"><font class="red" id="fpeilv2_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 10 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,10)"><font class="red" id="fpeilv3_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 11 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,11)"><font class="red" id="fpeilv4_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                     <td class="table_right_bet">
	                                      <label><input matchNo = "<s:property value="boutIndex"/>" index = 12 type="checkbox" class="chenck" onclick="clickChkBet(this,<s:property value="boutIndex"/>,12)"><font class="red" id="fpeilv5_<s:property value='boutIndex'/>">0</font></label>
	                                    </td>
	                                  </tr>
	                                </tbody>
                           	 	</table>
                        	</td>
						</tr>	
                  	</s:iterator>
                  </tbody>
                </table>
            </div>
      </div>
         <!--right-->
         <div class="BGTOP-RIGHT">
             <div class="title_guild">
					<label>帮助中心</label>
		     </div>
             <div class="bangzhu-cont">
                 <ul>
                     <li><a href="/help/lqsfhelp.htm" target="_blank">篮球胜负玩法规则</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </li>
                     <li><a href="/help/lqrfsfhelp.htm" target="_blank">篮球让分胜负玩法规则</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </li>
                     <li><a href="/help/lqdcsfchelp.htm" target="_blank">篮球胜分差玩法规则</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                     <li><a href="/help/lqdxfhelp.htm" target="_blank">篮球大小分玩法规则</a></li>
                 </ul>
             </div>
			 <div class="step_1">
				<div class="title_guild">
					<label>步骤1：确认投注信息</label>
				</div>
				<div class="only_show">
					<div class="show_l_whole"><a href="#" onclick="displayAllSelectedMatch1()"><img id="q1"  src="/images/lottery/only1.jpg"></a><br /><span>[已选择</span> <span class="red" id="selectedMatch">0</span> 场赛事]</div>
				</div>
			</div><!-- step_1 -->
			
			<div class="step_2" style="padding-bottom:17px;">
				<div class="title_guild">
					<label>步骤2：选择过关类型</label>
				</div>
				<div class="one_or_mul_title">
					<ul class="bgcolorstep2">
						<li id="free_go" class="title_cur" onclick="show_chuan_one_list()">自由过关</li>
						<li id="multi_go" class="title_old" onclick="show_chuan_multi_list()">多串过关</li>
					</ul>
				</div>
				<div class="clear"></div>
				<div id="chuan_one_list" class="chuan_one_list" style="padding-top:3px;">
					<table>
						<colgroup>
							<col width="33%"/>
							<col width="33%"/>
							<col width="33%"/>
						</colgroup>
						<tbody>
							<tr>
								<td><label id="lab_1c1" class="chuan_chuan" disabled="disabled" for="1c1" style="display:none;">单关</label></td>
							</tr>
							<tr>
							
								<td><label id="lab_2c1" class="chuan_chuan" for="2c1" style="display:none;"><input id="2c1" type="checkbox" onclick="clickChkGg(this)"/>2串1</label></td>
								<td><label id="lab_3c1" class="chuan_chuan" for="3c1" style="display:none;"><input id="3c1" type="checkbox" onclick="clickChkGg(this)"/>3串1</label></td>
								<td><label id="lab_4c1" class="chuan_chuan" for="4c1" style="display:none;"><input id="4c1" type="checkbox" onclick="clickChkGg(this)"/>4串1</label></td>
							</tr>
							<tr>
								<td><label id="lab_5c1" class="chuan_chuan" for="5c1" style="display:none;"><input id="5c1" type="checkbox" onclick="clickChkGg(this)"/>5串1</label></td>
								<td><label id="lab_6c1" class="chuan_chuan" for="6c1" style="display:none;"><input id="6c1" type="checkbox" onclick="clickChkGg(this)"/>6串1</label></td>
								<td><label id="lab_7c1" class="chuan_chuan" for="7c1" style="display:none;"><input id="7c1" type="checkbox" onclick="clickChkGg(this)"/>7串1</label></td>
							</tr><tr>
								<td><label id="lab_8c1" class="chuan_chuan" for="8c1" style="display:none;"><input id="8c1" type="checkbox" onclick="clickChkGg(this)"/>8串1</label></td>
							</tr>
							<!-- <td><label id="lab_7c1" class="chuan_chuan" for="7c1" style="display:none;"><input id="7c1" type="checkbox" onclick="clickChkGg(this)"/>7串1</label></td>
							<td><label id="lab_8c1" class="chuan_chuan" for="8c1" style="display:none;"><input id="8c1" type="checkbox" onclick="clickChkGg(this)"/>8串1</label></td> -->
							
						</tbody>
					</table>
				</div>
			
				<div id="chuan_multi_list" class="chuan_multi_list" style="display:none; padding-top:3px;">
					<table>
						<colgroup>
							<col width="33%"/>
							<col width="33%"/>
							<col width="33%"/>				
						</colgroup>
						<tbody>
							<tr>
								<td><label id="lab_3c3" class="chuan_chuan" for="3c3" style="display:none;"><input type=radio id="3c3" onclick="clickRadioGg(this)"/>3串3</label></td>
								<td><label id="lab_3c4" class="chuan_chuan" for="3c4" style="display:none;"><input type="radio" id="3c4" onclick="clickRadioGg(this)"/>3串4</label></td>
								<td><label id="lab_4c4" class="chuan_chuan" for="4c4" style="display:none;"><input type="radio" id="4c4" onclick="clickRadioGg(this)"/>4串4</label></td>
							</tr>
							<tr>
								<td><label id="lab_4c5" class="chuan_chuan" for="4c5" style="display:none;"><input type="radio" id="4c5" onclick="clickRadioGg(this)"/>4串5</label></td>
								<td><label id="lab_4c6" class="chuan_chuan" for="4c6" style="display:none;"><input type="radio" id="4c6" onclick="clickRadioGg(this)"/>4串6</label></td>
								<td><label id="lab_4c11" class="chuan_chuan" for="4c11" style="display:none;"><input type="radio" id="4c11" onclick="clickRadioGg(this)"/>4串11</label></td>
							</tr>
							<tr>
								<td><label id="lab_5c5" class="chuan_chuan" for="5c5" style="display:none;"><input type="radio" id="5c5" onclick="clickRadioGg(this)"/>5串5</label></td>
								<td><label id="lab_5c6" class="chuan_chuan" for="5c6" style="display: none;"><input type="radio" id="5c6" onclick="clickRadioGg(this)"/>5串6</label></td>
								<td><label id="lab_5c10" class="chuan_chuan" for="5c10" style="display: none;"><input type="radio" id="5c10" onclick="clickRadioGg(this)"/>5串10</label></td>
							</tr>
							<tr>
								<td><label id="lab_5c16" class="chuan_chuan" for="5c16" style="display: none;"><input type="radio" id="5c16" onclick="clickRadioGg(this)"/>5串16</label></td>
								<td><label id="lab_5c20" class="chuan_chuan" for="5c20" style="display: none;"><input type="radio" id="5c20" onclick="clickRadioGg(this)"/>5串20</label></td>
								<td><label id="lab_5c26" class="chuan_chuan" for="5c26" style="display:none;"><input type="radio" id="5c26" onclick="clickRadioGg(this)"/>5串26</label></td>
							</tr>
							<tr>
								<td><label id="lab_6c6" class="chuan_chuan" for="6c6" style="display:none;"><input type="radio" id="6c6" onclick="clickRadioGg(this)"/>6串6</label></td>
								<td><label id="lab_6c7" class="chuan_chuan" for="6c7" style="display:none;"><input type="radio" id="6c7" onclick="clickRadioGg(this)"/>6串7</label></td>
								<td><label id="lab_6c15" class="chuan_chuan" for="6c15" style="display:none;"><input type="radio" id="6c15" onclick="clickRadioGg(this)"/>6串15</label></td>
							</tr>
							<tr>
								<td><label id="lab_6c20" class="chuan_chuan" for="6c20" style="display:none;"><input type="radio" id="6c20" onclick="clickRadioGg(this)"/>6串20</label></td>
								<td><label id="lab_6c22" class="chuan_chuan" for="6c22" style="display:none;"><input type="radio" id="6c22" onclick="clickRadioGg(this)"/>6串22</label></td>
								<td><label id="lab_6c35" class="chuan_chuan" for="6c35" style="display:none;"><input type="radio" id="6c35" onclick="clickRadioGg(this)"/>6串35</label></td>
							</tr>
							<tr>
								<td><label id="lab_6c42" class="chuan_chuan" for="6c42" style="display:none;"><input type="radio" id="6c42" onclick="clickRadioGg(this)"/>6串42</label></td>
								<td><label id="lab_6c50" class="chuan_chuan" for="6c50" style="display:none;"><input type="radio" id="6c50" onclick="clickRadioGg(this)"/>6串50</label></td>
								<td><label id="lab_6c57" class="chuan_chuan" for="6c57" style="display:none;"><input type="radio" id="6c57" onclick="clickRadioGg(this)"/>6串57</label></td>
							</tr>
							
							<tr>
								<td><label id="lab_7c7" class="chuan_chuan" for="7c7" style="display:none;"><input type="radio" id="7c7" onclick="clickRadioGg(this)"/>7串7</label></td>
								<td><label id="lab_7c8" class="chuan_chuan" for="7c8" style="display:none;"><input type="radio" id="7c8" onclick="clickRadioGg(this)"/>7串8</label></td>
								<td><label id="lab_7c21" class="chuan_chuan" for="7c21" style="display:none;"><input type="radio" id="7c21" onclick="clickRadioGg(this)"/>7串21</label></td>
							</tr>
							<tr>	
								<td><label id="lab_7c35" class="chuan_chuan" for="7c35" style="display:none;"><input type="radio" id="7c35" onclick="clickRadioGg(this)"/>7串35</label></td>
								<td><label id="lab_7c120" class="chuan_chuan" for="7c120" style="display:none;"><input type="radio" id="7c120" onclick="clickRadioGg(this)"/>7串120</label></td>
								<td><label id="lab_8c8" class="chuan_chuan" for="8c8" style="display:none;"><input type="radio" id="8c8" onclick="clickRadio(this)"/>8串8</label></td>
							</tr>
							<tr>
								<td><label id="lab_8c9" class="chuan_chuan" for="8c9" style="display:none;"><input type="radio" id="8c9" onclick="clickRadio(this)"/>8串9</label></td>
								<td><label id="lab_8c28" class="chuan_chuan" for="8c28" style="display:none;"><input type="radio" id="8c28" onclick="clickRadio(this)"/>8串28</label></td>
								<td><label id="lab_8c56" class="chuan_chuan" for="8c56" style="display:none;"><input type="radio" id="8c56" onclick="clickRadio(this)"/>8串56</label></td>
							</tr>
							<tr>
								<td><label id="lab_8c70" class="chuan_chuan" for="8c70" style="display:none;"><input type="radio" id="8c70" onclick="clickRadio(this)"/>8串70</label></td>
								<td><label id="lab_8c247" class="chuan_chuan" for="8c247" style="display:none;"><input type="radio" id="8c247" onclick="clickRadio(this)"/>8串247</label></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div> 
            <!-- step_2 -->
			
			<div class="step_3">
				<div class="title_guild">
					<label>步骤3：确认投注结果</label>
				</div>
				<p class="dc_qr" style="color:#000000">投注&nbsp;<input class="mul" type="text" style="width:40px;" id="multiple" value="1" onkeyup="value=value.replace(/[^\d]/g,'1');" onblur="onJCLQMultipleChange()"/>倍(最高100000倍)<br/>您选择了 <font class="red" id="matchCount">0</font> 场比赛，共<font class="red" id="totalBetCount">0</font>注，<br/>总金额 <font class="red">￥</font><font class="red" id="totalBetMoney">0</font><font class="red" >.00</font>元<br/><span>理论最高奖金：<font class="red" id="maxBonus">￥0.00</font></span><br/><span>即使全部猜对可能最低奖金：<font class="red" id="minBonus">￥0.00</font></span>
					
				</p>
			</div>
			
			<div class="step_3">
				<!-- 选择购买类型 begin -->
				<div id="chooseBetType">
					<span id="betType_dg" style="margin-left:10px">
						<input name="betType" type="radio" id="dg_radio" checked="checked" onclick="onChangeBetType(0)" />代购
					</span>
					<span id="betType_hm" style="margin-left:10px">
						<input name="betType" type="radio" id="hm_radio" onclick="onChangeBetType(1)" />合买
					</span>
				</div>
				<!-- 选择购买类型 end -->
				
				<!-- 发起合买 begin -->
				<div id="betType_group_box" class="betType_group_box">
					<div style="float:left;">
						<ul style="margin-top:5px">
							<li style="margin-left:7px"><font>方案概况：</font> 总共<span class="red" id="stock">0</span>份，每份<span class="red" id="stock_money">1</span>元</li>
						</ul>
						<ul>  <!-- 认购 -->
							<li> <span class="red">*</span><font>我要认购：</font><input type="text" class="text" value="0" size="5" id="myself" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="validateGroupBuy()"/> 份，共计 
								 <span class="red" id="single_money">0</span> 元 
							</li>
							<li> <span style="color:#66CC00; margin-left:10px">(注：至少认购方案的20%，共<span id="shareStock" class="red"> 0</span> 份)</span></li>
						</ul>	
						<ul>  <!-- 保底 -->
							<li><label style="margin-left:7px"><font>我要保底：</font><input type="text" class="input_bg_a" value="0"  size="5" id="procted" onkeyup="value=value.replace(/[^\d]/g,'');validateGroupBuy()"/> 份
								<input name="procted_all" type="checkbox" id="procted_all" onclick="validateGroupBuy()"/>全额 </label>
							</li>
							<li>
								<label style="color:#66CC00; float:right; margin-right:8px">(共计 <span class="red" id="protect_total">0</span> 元)</label> 
							</li>
						</ul>
						<ul>  <!-- 公开情况 -->
							<li><span class="red">*</span><font>是否公开：</font></li>
							<li style="margin-left:12px"><input name="content" type="radio" id="radio1" value="gk" checked="checked" />完全公开
								<input type="radio" name="content" id="radio2" value="kj" />开奖后公开
							</li>
							<li style="margin-left:12px">
								<input type="radio" name="content" id="radio3" value="only" />仅跟单人可见
								<input type="radio" name="content" id="radio4" value="bm" />不公开
							</li>
						</ul>
						<ul>  <!-- 方案描述 -->
							<li style="margin-left:7px"><font>方案描述：</font></li>
							<li> <textarea name="desc" id="desc" value="" LostFocus="validateGroupBuy()" style="width:200px;height:60px;margin-left:8px;"></textarea>
								<span style="color:#66CC00; float:right; margin-right:8px">最多100字符</span>
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- 发起合买 end -->
				
				<!-- 确认购买面板 begin -->
				<div id="confirm_box" style="margin-bottom:10px">
					<div style="text-align:left;float:left;padding:15px;">
						<p>
							<span id="userTip" class="red">您尚未登录，<a href="/login/login.jsp">点击这里登录</a>！</span>
						</p>
						<p>
							本次投注所需金额为￥<span id="confirm_money" class="red">0</span>元
						</p>
						<p>
							<input id="agree_check_box" type="checkbox" checked="checked"/>我已阅读并同意《<a href="#">用户合买代购协议</a>》
						</p>
					</div>
					<div style="float:right; margin-right:20px;margin-top:30px">
						<input type="image" src="/images/ok.jpg" value="确认购买" onclick="onSubmit()"/>
					</div>
					<div class="clear"></div>
				</div>
				<!-- 确认购买面板 end -->
			</div>
         </div> 
	</div>
	<!-- foot -->
    <!--scroll top change-->
    <script type="text/javascript">
	(function() {
		var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
			.text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
				$("html, body").animate({ scrollTop: 0 }, 120);
		}), $backToTopFun = function() {
			var st = $(document).scrollTop(), winh = $(window).height();
			(st > 0)? $backToTopEle.show(): $backToTopEle.hide();
			//IE6下的定位
			if (!window.XMLHttpRequest) {
				$backToTopEle.css("top", st + winh - 166);
			}
		};
		$(window).bind("scroll", $backToTopFun);
		$(function() { $backToTopFun(); });
	})();
	</script>
	<div class="clear"></div> 
<%@include  file="../foot.jsp"%>
</body>
</html>
