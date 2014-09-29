<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票_足球数据</title>
<link rel="stylesheet" href="../css/common.css"/>
<link rel="stylesheet" href="../css/css.css"/>
<link rel="stylesheet" href="../css/default.css"/>
<link rel="stylesheet" href="../css/odds-serve.css"/>
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon"/>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/guessFootball.js"></script>
<script src="../js/jquery.jqchart.js" type="text/javascript" ></script>
<!--save box js start-->
 <style type="text/css">
       .anTips_tcp { visibility: visible; border: 2px solid #73B1EC;width: 330px;z-index: 9999; display: none;	-moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px;}
.anOverdivh {background-color: #FDFBDB;line-height: 30px;height:30px;text-align: center; clear:both;}
     
.shangjiantou { background:url(../images/369caibg/shangjian.png) no-repeat right; color: red;}
.xiajiantou { background:url(../images/369caibg/xiajian.png) no-repeat right; color: green;}
.eptcp {height: 50px;background:#eee;}
.eptcp td {background:#EEEDE8;}
.tips_tcp { border: 2px solid #73B1EC;width: 35 0px;z-index: 9999; display: none;	-moz-border-radius: 15px; -webkit-border-radius: 15px; border-radius: 15px;
 border-radius: 5px;}
.overdivh {background-color: #FDFBDB;line-height: 30px;text-align: center;}
.tips_tcp table tr td span {
    display: inline-block;
    text-indent: 10px;
}
.tips_tcp table {font-size: 12px;}
</style>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/guessFootball.js"></script>
<script src="../js/jquery.jqchart.js" type="text/javascript" ></script>
<!--save box js start-->
<script type="text/javascript">
function addBookmark(title,url) {
if (window.sidebar) { 
window.sidebar.addPanel(title, url,""); 
} else if( document.all ) {
window.external.AddFavorite( url, title);
} else if( window.opera && window.print ) {
return true;
}
}
$(document).ready(function() {
	getepqushi();
      generatequshi();
       }); 

</script> 
<!--save box js end-->

<!--网站导航下拉菜单 start-->
<script type="text/javascript">
<!--
var timeout         = 150;
var closetimer		= 0;
var ddmenuitem      = 0;

// open hidden layer
function mopen(id)
{	
	var companyName=$("#companyNameAn_"+id).text();
	var no = ${no};
	alert(companyName);
	alert(no);
	$.JCZQOdd.callback = function(json){
		var str="";
		$(json.resultArray).each(function(i){
			str += "<td>"+this.shuiwei1+"</td><td>"+this.rangqiu+"</td><td>"+this.shuiwei2+"</td><td>"+this.time+"</td>";
		});
		$("#allContent").html(str);
	};
 	$.JCZQOdd._request({no:no,companyName:companyName});
 
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}

function change(id)
{
	var companyName=$("#companyNameAn_"+id).text();
	var no = ${no};
	alert(companyName);
	alert(no);
	$.JCZQOdd.callback = function(json){
		var str="";
		$(json.resultArray).each(function(i){
			str += "<td>"+this.shuiwei1+"</td><td>"+this.rangqiu+"</td><td>"+this.shuiwei2+"</td><td>"+this.time+"</td>";
		});
		$("#allContent").html(str);
	};
 	$.JCZQOdd._request({no:no,companyName:companyName});
 	
}

(function ($) {
	$.JCZQOdd = {
		url : "/detail/datadetail.htm?action=getTheCompanyAllList",
		_request : function(param){
			$.getJSON($.JCZQOdd.url, param, $.JCZQOdd.callback);
		},
		callback : function(){}
	};
})(jQuery);

// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 
// -->
</script>
<!--网站导航下拉菜单 end-->

<script type="text/javascript">
function tab(a,b,c)
{
for(i=1;i<=b;i++){
if(c==i)
{
// 判断选择模块
document.getElementById(a+"_mo_"+i).style.display = "block";  // 显示模块内容
document.getElementById(a+"_to_"+i).className = "tab";   // 改变菜单为选中样式
}
else{
// 没有选择的模块
document.getElementById(a+"_mo_"+i).style.display = "none"; // 隐藏没有选择的模块
document.getElementById(a+"_to_"+i).className = "";  // 清空没有选择的菜单样式
}
}
}
</script>
</head>
<body>
<%@include file="/head.jsp" %>
<!--right start-->
<div class="right-area">
    <div class="zhukebijiao">
    <ul style="float:left;">
                   <li style="float:left;">
                      <ul class="zhudui">
                         <li><a href="#" onfocus="this.blur();"><s:property value="hostName"/></a></li>
                         <li>联赛排名：<s:property value="host.rank"/></li>
                      </ul>
                   </li>
                   <li style="float:left;">
<table width="300" class="tt-table" border="1" cellspacing="1" cellpadding="5">
                          <tr>
                            <td width="95">&nbsp;</td>
                            <td width="70" class="biaoshi2">让球</td>
                            <td width="66" class="biaoshi1">胜</td>
                            <td width="64" class="biaoshi">平</td>
                            <td width="64" class="biaoshi">负</td>
                          </tr>
                          <tr class="content">
                            <td class="biaoshi">竞彩赔率</td>
                            <td><s:property value="matchArrange.concede"/></td>
                            <td><s:property value="winPeiLv"/></td>
                            <td><s:property value="drowPeiLv"/></td>
                            <td><s:property value="losePeiLv"/></td>
                          </tr>
                          <tr class="content">
                            <td class="biaoshi1">平均欧赔</td>
                            <td>&nbsp;</td>
                            <td><s:property value="matchArrange.sop"/></td>
                            <td><s:property value="matchArrange.pop"/></td>
                            <td><s:property value="matchArrange.fop"/></td>
                          </tr>
                          <tr class="content">
                            <td class="biaoshi2">盘口</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                     </table>
                   </li>
                   <li style="float:left;">
                      <ul class="zhudui">
                         <li><a href="#" onfocus="this.blur();"><s:property value="visitName"/></a></li>
                         <li>联赛排名：<s:property value="visit.rank"/></li>
                      </ul>
                   </li>
      </ul>
  </div>
    <div class="clear"></div>
     <!--tab change start-->
  <div class="tabbed-change">
        <ul>
          <li id="tab_to_1"><a href="#" onfocus="this.blur();" onclick="tab('tab',4,1)">百家欧赔</a></li>
          <li style="margin-left:50px;" id="tab_to_2" class="tab"><a href="#" onfocus="this.blur();" onclick="tab('tab',4,2)">亚盘对比</a></li>
        </ul>
    </div>
    <!--tab change end-->
    <dl id="tab_mo_1" style="display:none">
       <b class="b1"></b><b class="b2"></b><b class="b3"></b>
      <div class="bc">
        <div class="bt ">欧赔对比</div>
        <div class="bb">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#ff6600">
          <tr>
            <th width="77%" scope="col"><table width="100%" cellspacing="1" cellpadding="0" >
              <tr>
                <th colspan="7" scope="col"><table width="100%" border="0" cellspacing="1" cellpadding="0" id="tcp123">
                  <tr>
                    <th width="23%" scope="col" bgcolor="#cccccc">欧赔公司</th>
                    <th width="77%" scope="col">
                        <table width="100%" cellspacing="1" cellpadding="0" >
                          <tr bgcolor="#cccccc">
                            <th colspan="8" scope="col" bgcolor="#cccccc">欧赔</th>
                          </tr>
                          <tr align="center">
                            <th scope="row" bgcolor="#cccccc">胜</th>
                            <td bgcolor="#cccccc">平</td>
                            <td bgcolor="#cccccc">负</td>
                            <td bgcolor="#cccccc">胜率</td>
                            <td bgcolor="#cccccc">和率</td>
                            <td bgcolor="#cccccc">负率</td>
                            <td bgcolor="#cccccc">返还率</td>
                            <td bgcolor="#cccccc">变化时间</td>
                          </tr>
                        </table>
                    </th>
                  </tr>

                  
                  <s:iterator value="allEpList" status="allep">
                  	<div id="ep_<s:property value="#allep.index"/>" >
                  		<input id="id_<s:property value="#allep.index"/>" type="hidden" value="${company.id }" name="id_<s:property value="#allep.index"/>" />
                  		<input id="name_<s:property value="#allep.index"/>" type="hidden" value="${company.companyName }" name="ep_<s:property value="#allep.index"/>" />
                  		<input id="s_<s:property value="#allep.index"/>" type="hidden" value="${sheng }" name="ep_<s:property value="#allep.index"/>" />
                  		<input id="p_<s:property value="#allep.index"/>" type="hidden" value="${ping }" name="ep_<s:property value="#allep.index"/>" />
                  		<input id="f_<s:property value="#allep.index"/>" type="hidden" value="${fu }" name="ep_<s:property value="#allep.index"/>" />
                  		<input id="pk_<s:property value="#allep.index"/>" type="hidden" value="${pankouId }" name="ep_<s:property value="#allep.index"/>" />
                  		<input id="time_<s:property value="#allep.index"/>" type="hidden" value="<s:date name="time" format="MM/dd HH:mm"/>" />
                  	</div>
                  	<s:if test="#allep.last">
                  		<input id="eplast" type="hidden" value="<s:property value="#allep.index"/>" />
                  	</s:if>	
                  </s:iterator>
                  
                </table></th>
                </tr>
            </table></th>
          </tr>
          </table>
        </div>
      </div>
      <b class="b3"></b><b class="b2"></b><b class="b1"></b>
    </dl>
    <dl id="tab_mo_2" style="display:block">
       <b class="b1"></b><b class="b2"></b><b class="b3"></b>
      <div class="bc">
        <div class="bt ">亚盘对比</div>
        <div class="bb">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#3366ff">
          <tr>
            <th width="77%" scope="col"><table width="100%" cellspacing="1" cellpadding="0" >
              <tr>
                <th colspan="7" scope="col">
                <table id="annewlast" width="100%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <th width="23%" scope="col" bgcolor="#cccccc">公司</th>
                    <th width="77%" scope="col">
                        <table width="100%" cellspacing="1" cellpadding="0" >
                          <tr align="center">
                            <th scope="row" bgcolor="#cccccc">水位</th>
                            <td bgcolor="#cccccc">让球</td>
                            <td bgcolor="#cccccc">水位</td>
                            <td bgcolor="#cccccc">变化时间</td>
                            </tr>
                        </table>
                    </th>
                  </tr>
                  <tr>
	                
                      <s:iterator value="allAnList" status="an">
                      	<input type="hidden" value="<s:property value='Company.companyName'/>" id="anCompany_<s:property value='#an.index'/>"/>    
                      	<input type="hidden" value="<s:property value='sheng'/>" id="anSheng_<s:property value='#an.index'/>"/> 
                      	<input type="hidden" value="<s:property value='rang'/>" id="anRang_<s:property value='#an.index'/>"/> 
                      	<input type="hidden" value="<s:property value='fu'/>" id="anFu_<s:property value='#an.index'/>"/> 
                      	<input type="hidden" value=" <s:date name='time' format='yyyy-MM-dd HH:mm:ss'/>" id="anTime_<s:property value='#an.index'/>"/>
                      	<input type="hidden" value="<s:property value='pankouId'/>" id="anPk_<s:property value='#an.index'/>" />
                      	<input type="hidden" value="<s:property value='Company.id'/>" id="anId_<s:property value='#an.index'/>" />      
                      	<s:if test="#an.last">
                  			<input type="hidden" id="anlast" value="<s:property value='#an.index'/>" />
                  		</s:if>
                      	            
                       </s:iterator>
                

                </table></th>
				 </tr>
            </table></th>
          </tr>
          </table>
        </div>
      </div>
      <b class="b3"></b><b class="b2"></b><b class="b1"></b>
    </dl>
</div>
<%@include file="/foot.jsp" %>
</body>
</html>
