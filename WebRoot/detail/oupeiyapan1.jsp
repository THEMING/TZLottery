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
<style>
.shangjiantou { background:url(../images/369caibg/shangjian.png) no-repeat right; border-top:1px solid #ccc;}
.xiajiantou { background:url(../images/369caibg/xiajian.png) no-repeat right; border-top:1px solid #ccc;}
</style>
</head>
<body>
<%@include file="/head.jsp" %>
<!--right start-->
<div class="right-area">
    <div class="zhukebijiao">
        <ul style="margin:0 auto;">
                   <li style="float:left;">
                      <ul class="zhudui">
                         <li><a href="#" onfocus="this.blur();"><s:property value="hostName"/></a></li>
                         <li>联赛排名：<s:property value="host.rank"/></li>
                      </ul>
                   </li>
                   <li style="float:left;">
                         <table width="250" class="tt-table" border="1" cellspacing="1" cellpadding="5">
                          <tr>
                            <td width="70">&nbsp;</td>
                            <td width="60" class="biaoshi2">让球</td>
                            <td width="50" class="biaoshi1">胜</td>
                            <td width="50" class="biaoshi">平</td>
                            <td width="50" class="biaoshi">负</td>
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
                            <td>--</td>
                            <td><s:property value="matchArrange.sop"/></td>
                            <td><s:property value="matchArrange.pop"/></td>
                            <td><s:property value="matchArrange.fop"/></td>
                          </tr>
                          <tr class="content">
                            <td class="biaoshi2">盘口</td>
                            <td><s:property value="aomenOdd.rang"/></td>
                            <td><s:property value="aomenOdd.sheng"/></td>
                            <td>--</td>
                            <td><s:property value="aomenOdd.fu"/></td>
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
          <li style="margin-left:30px;" id="tab_to_2" class="tab"><a href="#" onfocus="this.blur();" onclick="tab('tab',4,2)">亚盘对比</a></li>
        </ul>
    </div>
    <!--tab change end-->
    <dl id="tab_mo_1" style="display:none">
       <b class="b1"></b><b class="b2"></b><b class="b3"></b>
      <div class="bc">
        <div class="bt ">欧赔对比</div>
        <div class="bb">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#ff6600" align="center">
          <tr>
            <th width="77%" scope="col"><table width="100%" cellspacing="1" cellpadding="0" >
              <tr>
                <th colspan="7" scope="col"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <th width="23%" scope="col" bgcolor="#cccccc">欧赔公司</th>
                    <th width="77%" scope="col">
                        <table width="100%" cellspacing="1" cellpadding="0" align="center">
                          <tr bgcolor="#cccccc">
                            <th colspan="7" scope="col" bgcolor="#cccccc">欧赔</th>
                          </tr>
                          <tr align="center">
                            <th scope="row" bgcolor="#cccccc">胜</th>
                            <td bgcolor="#cccccc">平</td>
                            <td bgcolor="#cccccc">负</td>
                            <td bgcolor="#cccccc">胜率</td>
                            <td bgcolor="#cccccc">和率</td>
                            <td bgcolor="#cccccc">负率</td>
                            <td bgcolor="#cccccc">返还率</td>
                          </tr>
                        </table>
                    </th>
                  </tr>
                  <s:iterator value="allEpList" status="s" id="id0">
                  <tr bgcolor="#EEEDE8"onmouseout="this.bgColor='#EEEDE8';" onmouseover="this.bgColor='#ffcc99';">
                    <th scope="row"><a href="#" onfocus="this.blur();">${company.companyName }</a></th>
                    <td><table width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align:center;">
                      <tr>
                        <th align="center" width="14%" scope="col">${id0.sheng }</th>
                        <th align="center" width="14%" scope="col">${id0.ping }</th>
                        <th align="center" width="14%" scope="col">${id0.fu }</th>
                        <s:iterator value="shengLv0" id="id2" status="sta1">
                        <s:if test="#sta1.index == #s.index">
                        	<td scope="row" width="14%"  align="center" class="bordertop"><s:property value="id2"></s:property>%</td>
                        </s:if>
                        </s:iterator>
                        <s:iterator value="pingLv0" id="id3" status="sta2">
                        <s:if test="#sta2.index == #s.index">
                        	<td scope="row" width="14%"  align="center" class="bordertop"><s:property value="id3"></s:property>%</td>
                        </s:if>
                        </s:iterator>
                        <s:iterator value="fuLv0" id="id4" status="sta3">
                        <s:if test="#sta3.index == #s.index">
                        	<td scope="row" width="14%"  align="center" class="bordertop"><s:property value="id4"></s:property>%</td>
                        </s:if>
                        </s:iterator>
                        <s:iterator value="back0" id="id5" status="sta4">
                        <s:if test="#sta4.index == #s.index">
                        	<td scope="row" width="14%"  align="center" class="bordertop"><s:property value="id5"></s:property>%</td>
                        </s:if>
                        </s:iterator>
                      </tr>
                    </table></td>
                  </tr>
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
          <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#3366ff" align="center">
          <tr>
            <th width="77%" scope="col"><table width="100%" cellspacing="1" cellpadding="0" >
              <tr>
                <th colspan="7" scope="col"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <th width="23%" scope="col" bgcolor="#cccccc">公司</th>
                    <th width="77%" scope="col">
                        <table width="100%" cellspacing="1" cellpadding="0" >
                          <tr align="center">
                            <th width="16%" scope="row" bgcolor="#cccccc">水位</th>
                            <td width="28%" bgcolor="#cccccc">让球</td>
                            <td width="15%" bgcolor="#cccccc">水位</td>
                            <td width="53%" bgcolor="#cccccc">变化时间</td>
                            </tr>
                        </table>
                    </th>
                  </tr>
                  <s:iterator value="allAnList" id="odd">
                  <tr bgcolor="#EEEDE8"onmouseout="this.bgColor='#EEEDE8';" onmouseover="this.bgColor='#ffcc99';">
                    <th scope="row"><a href="#" onfocus="this.blur();" id="companyNameAn_<s:property value="#stat.index"/>">${company.companyName }</a></th>
                    <td><table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
                      <tr >
                        <th width="4%"  scope="col"></th>
                        <th width="12%" scope="col">${sheng }</th>                       
                        <th width="28%" scope="col" >${rangOther }</th>
                        <th width="2%" scope="col"></th>
                        <th width="9%" scope="col">${fu }</th>
                        <th width="53%" scope="col"><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/></th>
                        </tr>                     
                    </table></td>
                  </tr>
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
