<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="核心价值观,使命,愿景">
<meta name="description" content="一彩票关于我们频道详实的介绍了公司愿景、使命及核心价值观，企业文化，商务合作和联系我们的方式。369竞彩圆每个人心中500万的梦，梦想人生从一彩票开始。">
<title>一彩票_关于我们</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/aboutus.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script src="../js/jquery-1.4.4.min.js" type="text/javascript"></script>
<!--关于我们菜单JS start-->
<script type="text/javascript"\>
	$(function () {

	});
	
	
	
	var last_index = 1;
	var last_sub_index = 1;
	
	$(document).ready(function()
		{
			//默认为第一项
			$("#aboutus_li_1").click();
		}
	);
	
	function clickHelpSubItem(obj, sub_index) 
	{
		$("#content_" + sub_index).css("display", "block");
		$("#aboutus_li_" +sub_index).addClass("aboutus_li_current");
		if(sub_index != last_sub_index) {
			$("#content_" + last_sub_index).css("display", "none");
			$("#aboutus_li_" +last_sub_index).removeClass("aboutus_li_current");
		}
		last_sub_index = sub_index;
	}
	
	
	
	
</script>
<!--关于我们菜单JS end-->
<!--分享到微博 start-->
<!--<script type="text/javascript" charset="utf-8">
(function(){
  var _w = 106 , _h = 58;
  var param = {
    url:location.href,
    type:'5',
    count:'1', /**是否显示分享数，1显示(可选)*/
    appkey:'', /**您申请的应用appkey,显示分享来源(可选)*/
    title:'', /**分享的文字内容(可选，默认为所在页面的title)*/
    pic:'', /**分享图片的路径(可选)*/
    ralateUid:'2327945995', /**关联用户的UID，分享微博会@该用户(可选)*/
    rnd:new Date().valueOf()
  }
  var temp = [];
  for( var p in param ){
    temp.push(p + '=' + encodeURIComponent( param[p] || '' ) )
  }
  document.write('<iframe allowTransparency="true" frameborder="0" scrolling="no" src="http://hits.sinajs.cn/A1/weiboshare.html?' + temp.join('&') + '" width="'+ _w+'" height="'+_h+'"></iframe>')
})()
</script>-->
<!--分享到微博end-->
</head>

<body>
<%@include file="/head.jsp" %>
<div class="clear"></div>
<!--浮动QQ客服 start-->
<div class="qqbox" id="divQQbox">
 
  <div class="qqlv" id="meumid" onmouseover="show()"><img src="/images/369caibg/lxkf.png"></div>
 
  <div class="qqkf" style="display:none;" id="contentid" onmouseout="hideMsgBox(event)">
 
    <div class="qqkfbt"  onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,3);" id="qq-3" onfocus="this.blur();">客 服 服 务</div>
 
    <div id="K3" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="http://wpa.qq.com/msgrd?V=1&Uin=2432882152&Site=" target="_blank"><img src="http://wpa.qq.com/pa?p=1:2432882152:4" border="0">&nbsp;2432882152</a></div>
 
      <div class="qqkfhm bgdh"> <a href="http://wpa.qq.com/msgrd?V=1&Uin=2211985193&Site=" target="_blank"><img src="http://wpa.qq.com/pa?p=1:2211985193:4" border="0">&nbsp;2211985193</a></div>
 
    </div>
 
    <div class="qqkfbt" onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,2);" id="qq-2" onfocus="this.blur();">关 注 微 博</div>
 
    <div id="K2" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="http://www.weibo.com/369cai" target="_blank"><img src="../images/img/callcenter_4.jpg" border="0" width="20" height="20">&nbsp;369CAI新浪</a></div>
 
      <div class="qqkfhm bgdh"> <a href="http://t.qq.com/caipiao369" target="_blank"><img src="../images/img/callcenter_3.gif" border="0" width="15" height="15">&nbsp;369CAI腾讯</a></div>
 
    </div>
 
    <div class="qqkfbt" onmouseout="showandhide('qq-','qqkfbt','qqkfbt','K',5,1);" id="qq-1" onfocus="this.blur();"> 商 务 合 作</div>
    
    <div id="K1">
 
      <div class="qqkfhm bgdh"><img src="../images/img/gif-0393.gif" border="0">&nbsp;010 - 56203469</div>
      <div class="qqkfhm bgdh"> <a href="http://wpa.qq.com/msgrd?V=1&Uin=464623812&Site=" target="_blank"><img src="http://wpa.qq.com/pa?p=1:464623812:4" border="0">&nbsp;464623812</a></div>
      <div class="qqkfhm bgdh">联系人：刘先生</div>
 
    </div>
 
    <div class="qqkfbt"  onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,4);" id="qq-4" onfocus="this.blur();">技 术 支 持</div>
 
    <div id="K4" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="tencent://message/?uin=2211985193"><img src="http://wpa.qq.com/pa?p=1:2211985193:4" border="0">&nbsp;2211985193</a><br/></div>

      <div class="qqkfhm bgdh">联系人：宋先生</div>
 
    </div>
 
    <div class="qqkfbt" onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,5);" id="qq-5" onfocus="this.blur();">投 诉 建 议</div>
 
    <div id="K5" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="tencent://message/?uin=2432882152"><img src="http://wpa.qq.com/pa?p=1:2432882152:4" border="0">&nbsp;2432882152</a></div>
 
      <div class="qqkfhm bgdh">联系人：李小姐</div>
 
    </div>
 
  </div>
 
</div>
 
<script language="javascript">
 
function showandhide(h_id,hon_class,hout_class,c_id,totalnumber,activeno) {
 
    var h_id,hon_id,hout_id,c_id,totalnumber,activeno;
 
    for (var i=1;i<=totalnumber;i++) {
 
        document.getElementById(c_id+i).style.display='none';
 
        document.getElementById(h_id+i).className=hout_class;
 
    }
 
    document.getElementById(c_id+activeno).style.display='block';
 
    document.getElementById(h_id+activeno).className=hon_class;
 
}
 
var tips; 
var theTop = 100;
 
var old = theTop;
 
function initFloatTips() 
{ 
        tips = document.getElementById('divQQbox');
 
        moveTips();
 
}
 
function moveTips()
 
{
 
                   var tt=50; 
                  if (window.innerHeight) 
                  {
 
                pos = window.pageYOffset  
 
                  }else if (document.documentElement && document.documentElement.scrollTop) {
 
                pos = document.documentElement.scrollTop  
 
                  }else if (document.body) {
 
                    pos = document.body.scrollTop;  
 
                  }
 
                  //http:
 
                  pos=pos-tips.offsetTop+theTop; 
                  pos=tips.offsetTop+pos/10; 
                  if (pos < theTop){
 
                         pos = theTop;
 
                  }
 
                  if (pos != old) { 
                         tips.style.top = pos+"px";
 
                         tt=10;//alert(tips.style.top);  
 
                  }
 
                  old = pos;
 
                  setTimeout(moveTips,tt);
 
}
 
initFloatTips();
 
        if(typeof(HTMLElement)!="undefined")//给firefox定义contains()方法，ie下不起作用
 
                {  
 
                  HTMLElement.prototype.contains=function (obj)  
 
                  {  
 
                          while(obj!=null&&typeof(obj.tagName)!="undefind"){//
 
           　　 　if(obj==this) return true;  
 
           　　　        　obj=obj.parentNode;
 
           　　          }  
 
                          return false;  
 
                  }
 
        }
 
function show()
 
{
 
        document.getElementById("meumid").style.display="none"
 
        document.getElementById("contentid").style.display="block"
 
}
 
        function hideMsgBox(theEvent){
 
          if (theEvent){
 
                var browser=navigator.userAgent;
 
                if (browser.indexOf("Firefox")>0){//Firefox
 
                    if (document.getElementById("contentid").contains(theEvent.relatedTarget)) {
 
                                return
 
                        }
 
                }
 
                if (browser.indexOf("MSIE")>0 || browser.indexOf("Presto")>=0){
 
                if (document.getElementById('contentid').contains(event.toElement)) {
 
                            return;//结束函式
 
                    }
 
                }
 

          }
 
          document.getElementById("meumid").style.display = "block";
 
          document.getElementById("contentid").style.display = "none";
 
        }
 
</script>
<!--浮动QQ客服 end-->
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
	<!--大背景 start-->
    <div class="aboutus_bg">
    	<div class="aboutus_left">
        	<div class="aboutus_top"></div>
            <div class="aboutus_body">
            	<ul>
                	<li class="aboutus_body_li  aboutus_li_current" id="aboutus_li_1" onclick="clickHelpSubItem(this, 1)">公司介绍</li>
                    <li class="aboutus_body_li" id="aboutus_li_2" onclick="clickHelpSubItem(this, 2)">企业文化</li>
                    <li class="aboutus_body_li" id="aboutus_li_3" onclick="clickHelpSubItem(this, 3)">商务合作</li>
                    <li class="aboutus_body_li" id="aboutus_li_5" onclick="clickHelpSubItem(this, 5)">联系我们</li>
					<li class="aboutus_body_li" id="aboutus_li_6" onclick="clickHelpSubItem(this, 6)">加入我们</li>
                </ul>
            </div>
            <div class="aboutus_bottom"></div>
        </div>
        <div class="aboutus_right">
        	<div class="about_img"><img src="../images/img/aboutus.jpg" width="737" height="122" /></div>
            <!--公司介绍 start-->
            <div class="about_conbg" style="display:block" id="content_1">
        		<div class="about_Route">首页&nbsp; &gt;关于我们&nbsp; &gt; 公司介绍</div>
            	<div class="about_con">
                    <p><strong style="color:#ff6600;">缩水概念提出</strong>
                    <p>2001年11月，公司创始人首创彩票缩水概念，在北京正式推出了全国第一套足彩投注缩水软件——“足球玩玩”软件，并在全国范围内建立起软件代理销售网络。随后几年，在全国各地掀起缩水投注的热潮，直接催生了数千中奖彩民，多位软件用户中得百万大奖。该软件的诞生，为广大中国彩民启发了科学投注的新思路，直接启动了中国足彩投注优化技术的研究发展。<br /><br /></p>
                    
                    <p><strong style="color:#ff6600;">公司的成立</strong>
                    <p>2002年6月，北京比特太奇科技有限公司成立，公司拥有多位博士及硕士组成的技术研发团队，本公司一贯坚持“科学 客观 严谨 创新”的基本理念，成功将现代数学知识与计算机技术应用于彩票研究，独家首创数十项革命性彩票投注优化技术，并结合广泛实践经验形成了一套科学系统的彩票投注优化理论——宏观彩票投注优化理论，并与科普出版社联合推出了足彩投注优化专著《智赢足彩》（2002.9 ISBN 7-110-05359-8）一书。
                    <br /><br /></p>
                    
                    <p><strong style="color:#ff6600;">软件面世</strong>
                    <p>继缩水技术以后，北京比特太奇公司陆续推出了系列彩票缩水过滤软件，涵盖足彩，进球彩，任9场，半全场，双色球，乐透，3D，排列5，篮彩等几乎所有国家已发行彩票品种，系列用软件继承了玩玩软件功能强大，速度快，使用方便，流程科学的特点，使用用户数和中奖数用户持续攀升，形成了彩票软件的高端品牌。
                    <br /><br /></p>
                    <p><strong style="color:#ff6600;"> 网络投注</strong>
                    <p>随着国家政策的开放，我公司也应广大用户要求，与国家体彩和福彩管理部门合作开展了网络购彩业务，旗下一彩票网站涵盖福彩，体彩，足彩等彩票品种，并可结合我司彩票投注优化软件的使用，从而在为彩民提供网络投注便利的同时，大大提高了总体中奖概率，我们将继续发挥技术优势，竭诚服务广大彩民，力争打造中奖率最高的彩票投注平台。</p>
            	</div>
            </div>
        	<!--公司介绍 end-->
             <!--发展战略 start-->
            <div class="about_conbg" id="content_2">
        		<div class="about_Route">首页&nbsp; &gt;关于我们&nbsp; &gt; 企业文化</div>
            	<div class="about_con">
            <p><strong style="color:#ff6600;">愿景</strong>
            <p>彩票改变生活。<br /><br /></p>

            <p><strong style="color:#ff6600;">使命</strong>
            <p>科学购彩，快乐购彩。打造中国中奖率最高的网络彩票平台，为彩民提供最好的工具和最详尽数据，持续为客户创造最大价值。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">核心价值观</strong>
            <p>“科学 客观 严谨 创新”是扎根于我们内心深处的核心信念，是比特太奇公司走到今天的内在动力，更是我们面向未来的共同承诺。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">成就彩民</strong>
            <p>为彩民服务是比特太奇存在的唯一理由，彩民需求是比特太奇发展的原动力。我们坚持以彩民为中心，快速响应彩民需求，持续为彩民创造长期价值，进而成就彩民。为彩民提供有效服务，是我们工作的方向和价值评价的标尺，成就彩民就是成就我们自己。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">开放进取</strong>
            <p>为了更好地满足彩民需求，我们积极进取、勇于开拓，坚持开放与创新。任何先进的技术、产品、解决方案和业务管理，只有转化为商业成功才能产生价值。我们坚持彩民需求导向，并围绕彩民需求持续创新。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">至诚守信</strong>
            <p>只有内心坦荡诚恳，我们才能言出必行，信守承诺。诚信是我们最重要的无形资产，比特太奇坚持以诚信赢得彩民嘱托。<br /><br /></p>
            	</div>
            </div>
        	<!--发展战略 end-->
             <!--商务合作 start-->
            <div class="about_conbg" id="content_3">
        		<div class="about_Route">首页&nbsp; &gt;关于我们&nbsp; &gt; 商务合作</div>
            	<div class="about_con">
            <p>公司期待与线上线下合作伙伴互助共赢，共同发展，公司强大软件技术储备和10年行业经验，都将在合作中发挥更大作用，为双方带来丰厚回报。<br /><br /></p>

            <p><strong style="color:#ff6600;">彩票推广业务</strong>
            <p>如果您是网站拥有人，您可以与旗下一彩票网站交换链接，并可在线推广使用我们的系列彩票软件，在互相提高访问量的同时，我们将统计来自您的网站的用户以及相关彩票投注，按一定比例返还提成。</p>
            <p>如果您是一般彩民，只要你注册成功就可以在用户中心->推荐人查询->推荐朋友的页面看到一个类似<a href="http://www.369cai.com/?reference=2631" target="_blank" style="color:#3366ff; text-decoration:underline;">http://ww<br/>w.369cai.com/?reference=2631</a> 连接,只要你复制以上连接进行推广，你推荐的客户投注后您就可以获得一定的佣金，重要的是这种隶属关系是终生的。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">线下推广业务</strong>
            <p>如果您是投注站拥有人，您不仅可以免费使用我们的系列统计分析软件，也可以为您的客户提供系列缩水过滤软件，软件缩水的结果可以在投注站使用，并且您可推荐您的客户在不方便到投注站投注的时候使用一彩票网站，我们将根据客户投注量为您返还一定的推荐费用。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">数据合作业务</strong>
            <p>我公司拥有多年足彩联赛相关数据，并可提供系列统计分析软件及在线统计分析，期待与合作伙伴展开数据业务方面合作。<br /><br /></p>
            
            <p><strong style="color:#ff6600;">彩票行业解决方案</strong>
            <p>我们拥有十年的彩票行业运营经验，拥有精锐的开发团队。在彩票业务领域，我们可以提供WEB投注、WAP投注、Android/iPhone手机客户端、PC客户端、iPad平板电脑几乎涵盖所有终端的投注方式。如果想进入网络彩票，我们是您首选的合作伙伴。<br /><br /></p>
            	</div>
            </div>
        	<!--商务合作 end-->
           <!--  <!--客户服务 start-->
            <div class="about_conbg" id="content_4">
        		<div class="about_Route">首页&nbsp; &gt;关于我们&nbsp; &gt; 客户服务</div>
            	<div class="about_con">
				<p><font style="color:#ff6600;">商务合作</font>
                  <p>MBL：+86 139 2515 3154, +852 5163 0207(Hong Kong) 
                  <p>FAX：+86-10-5620 3469
                  <p>QQ:  464623812 
                  <p>Mail: liuyanfeng@369cai.com, 注：(at) = @
                  <p>联系人：刘先生
                    <br />
                    <br />
			        <br />
                    
                  <p><font style="color:#ff6600;">客户服务</font>
                  <p>电话：+86-10-5974 1769 
                  <p>传真：+86-10-5620 3469
                  <p>QQ：2432882152
                  <p>邮件：crm@369cai.com, 注：(at) = @
                  <p>联系人：李小姐
                    <br />
                    <br />
			        <br />
                    
                  <p><font style="color:#ff6600;">技术支持</font>
                  <p>电话：+86-10-5974 1769 
                  <p>传真：+86-10-5620 3469
                  <p>QQ：2211985193
                  <p>邮件：support@369cai.com, 注：(at) = @
                  <p>联系人：宋先生
                    <br />
                    <br />
			        <br />
                    
                  <p><font style="color:#ff6600;">财务税务</font>
                  <p>电话：+86-10-5974 1769 
                  <p>传真：+86-10-5620 3469
                  <p>QQ：2211985193
                  <p>邮件：finance@369cai.com, 注：(at) = @
                  <p>联系人：宋先生

			
			
            	</div>
            </div>
        	<!--客户服务 end-->
             <!--联系我们 start-->
            <div class="about_conbg" id="content_5">
        		<div class="about_Route">首页&nbsp; &gt;关于我们&nbsp; &gt; 联系我们</div>
            	<div class="about_con">
                    
                    
                    <p><strong style="color:#ff6600;">北京比特太奇科技有限公司</strong><br/>
                    <p>地址：北京上地辉煌国际5号楼10层, 100085
                    <p> Room 1016 Building 5, Huihuang Plaza, Shangdi, Beijing, 100085, China
                    <p>电话：+86-10-5620 3469 
                    <p>传真：+86-10-6241 0075
                    <p> 邮件：sales(at)369cai.com, 注：(at) = @
                    <p>关注我们：<img src="../images/img/callcenter_3.gif" style="padding-top:10px;" width="25" height="25">&nbsp;&nbsp; <a href="http://t.qq.com/caipiao369" target="_blank" style="padding-bottom:4px; color:#39F; text-decoration:underline;">腾讯微博</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <img src="../images/img/callcenter_4.jpg" style="padding-top:10px;" width="25" height="25">&nbsp;&nbsp; <a href="http://www.weibo.com/369cai" target="_blank" style="padding-bottom:10px; color:#39F; text-decoration:underline;">新浪微博</a>
			
                    <br />
                    <br />
			       
			         
			        <p><font style="color:#ff6600;">商务合作</font>
                    <p>MBL：+86 139 2515 3154, +852 5163 0207(Hong Kong) 
                    <p>PHONE：+86-10-5620 3469
                    <p>FAX：+86-10-6241 0075
                    <p>QQ:  464623812 
                    <p>Mail: liuyanfeng(at)369cai.com, 注：(at) = @
                    <p>联系人：刘先生
                    <br />
                    <br />

                    
                    <p><font style="color:#ff6600;">客户服务</font>
                    <p>电话：+86-10-5620 3469 
                    <p>传真：+86-10-6241 0075
                    <p>QQ：2432882152
                    <p>邮件：crm(at)369cai.com, 注：(at) = @
                    <p>联系人：李小姐
                    <br />
                    <br />

                    
                    <p><font style="color:#ff6600;">技术支持</font>
                    <p>电话：+86-10-5620 3469 
                    <p>传真：+86-10-6241 0075
                    <p>QQ：2211985193
                    <p>邮件：support(at)369cai.com, 注：(at) = @
                    <p>联系人：宋先生
                    <br />
                    <br />

                    
                    <p><font style="color:#ff6600;">财务税务</font>
                    <p>电话：+86-10-5620 3469 
                    <p>传真：+86-10-6241 0075
                    <p>QQ：2211985193
                    <p>邮件：finance(at)369cai.com, 注：(at) = @
                    <p>联系人：宋先生
                    <br />
                    <br />
			        <br />
                    <br />
                    <p><strong style="color:#ff6600;">北京比特太奇科技有限公司</strong> <font style="color:#ff6600;">市场部</font><br/>
                    <p>地址：北京市朝阳区北苑路13号院1号楼B1208室,100107
                    <p> B1208, Building 1, No.13 Beiyuan Road, Chaoyang, Beijing,100107, China 
                    <p>电话：+86-10-5620 3469 
                    <p>传真：+86-10-6241 0075
                    <p> 邮件：sales(at)369cai.com, 注：(at) = @
                    <br />
                    <br />
                    <br />
                    <p><strong style="color:#ff6600;">北京比特太奇科技有限公司</strong> <font style="color:#ff6600;"> 华南运营中心</font><br/>
                    <p>地址：广州市天河区东圃熙湖街11号1502房, 510630
                    <p>Room 1502,No. 11 Xihu Street,Dongpu,Zhongshan Avenue,Guangzhou,510630,China
                    <p>电话：+86-20-8232 7922
                    <p>传真：+86-20-6120 8252
                    <p>邮件：sales(at)369cai.com, 注：(at) = @

            	</div>
            </div>
        	<!--联系我们 end-->
             <!--加入我们 start-->
            <div class="about_conbg" id="content_6">
        		<div class="about_Route">首页&nbsp; &gt;关于我们&nbsp; &gt; 加入我们</div>
            	<div class="about_con">
            
<span><strong>公司目前诚聘如下人才，热忱欢迎您的加入！</strong></span><br /><br />
<span><strong>简历投递：</strong></span>hr@369cai.com <br /><br />

<span><strong>JAVA软件工程师</strong></span><br /><br />
<strong>职位描述</strong><br /><br />
（1）具备JAVA语言基础知识<br /><br />
（2）熟悉SSH架构，有相关工作经验优先<br /><br />
（3）具备一定数学基础，熟悉组合数学相关算法<br /><br />
（4）熟悉C，C++，使用过VC优先<br /><br />
<br /><br /><br />

<span><strong>网页设计制作</strong></span><br /><br />
<strong>职位描述</strong><br /><br />
（1）1年以上网页设计经验<br /><br />
（2）熟悉DREAMWEAVER,DIV/CSS<br /><br />
（3）能够设计BANNER及图片，并切图制作HTML<br /><br />
（4）对色彩搭配敏感，能把握整体布局<br /><br />
 （5）有美工工作经验者优先<br /><br />
<br /><br /><br />
<span><strong>网站推广</strong></span><br /><br />
<strong><strong>职位描述</strong></strong><br /><br />
（1）有SEO推广经验<br /><br />
（2）熟悉电子商务流程及推广平台<br /><br />
（3）与其他网站合作推广及谈判<br /><br />
 （4）有APP推广经验者优先<br /><br />
 （5）熟悉彩票相关玩法<br /><br />
 
 
            	</div>
            </div>
        	<!--加入我们 end-->
            
      </div>
    </div>
    <!--大背景 end-->

    
    
</div>
<!--Web Body end-->
<div class="clear"></div>
<div class="aboutus_line"></div>
<%@include file="/foot.jsp" %>
</body>
</html>