<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">

<script language="javascript">
	//专家推荐
	$(function () {
		var RecomLast=1;
		$(".RecomTitle li").click(function(){			
			var attra = $(this).index() + 1;
			$(this).addClass("RecomCurrent_bg").siblings().removeClass("RecomCurrent_bg");			
			if( RecomLast==attra){
				$("#RR" + RecomLast).css("display","block");
			}
			else{
				$("#RR" + RecomLast).css("display","none");
				$("#RR" + attra).css("display","block");
			}
			RecomLast=attra;
		});
	});
	
	$(function(){
		$(".ball_arrow").click(function(){
			if($(".ball_Remind").css("display")=="block"){
				$(".ball_Remind").slideUp("slow");
				$(".ball_arrow").css("background-position","118px -9px")
				
			}
			if($(".ball_Remind").css("display")=="none"){
				$(".ball_Remind").slideDown("slow");
				$(".ball_arrow").css("background-position","118px 9px")
			}
		});
	});
</script>
</head>

<body>
        <!--投注页专家推荐 start-->
        <div class="RecomTitleBg" >
        	<div class="RecomTitle">
            	<ul>
               	  <li class="RecomTitle1 RecomCurrent_bg">专家推荐</li>
                  <li class="RecomTitle2">相关资讯</li>
                </ul>
            </div>
        </div>
        <div class="Recom_Expert" style="display:block;" id="RR1" >
        	<div class="Expert_pic">
        		<ul>
            		<li><img src="../images/img/right_26.jpg" width="59" height="61" /></li>
					<li>${Author1}</li>
            	</ul>
            </div>
            <div class="Expert_con">
            	<ul>
            	<s:iterator value="articleExportList1">
        			<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>"><li>${title}</li></a>
        		</s:iterator>
        		</ul>
            </div>
            <div class="clear"></div>
            <div class="Expert_pic">
        		<ul>
            		<li><img src="../images/img/right_26.jpg" width="59" height="61" /></li>
					<li>${Author2}</li>
            	</ul>
            </div>
            <div class="Expert_con">
            	<ul>
            	<s:iterator value="articleExportList2">
        			<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>"><li>${title}</li></a>
        		</s:iterator>
        		</ul>
            </div>
        </div>
  		<div class="RecomCon" id="RR2" >
        	<s:iterator value="articleList">
        	<tr>
        		<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>"><td>${title}</td></a>
        	</tr>
        	</s:iterator>
        </div>
        <!--投注页专家推荐 end-->
<!--Web Body end-->
<div class="clear"></div>
</body>
</html>

