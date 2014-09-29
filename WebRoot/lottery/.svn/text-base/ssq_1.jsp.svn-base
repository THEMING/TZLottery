<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>双色球_投注</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/lottery.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="favicon.ico" /> 

<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
<script type="text/javascript" src="../js/lottery/ssq.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>

<!--专家推荐 start-->
<script language="javascript">
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
</script>
<!--专家推荐 end-->

<!--投注方式tab start-->
<script>
$(function () {
		var theLast=2;
		$(".BMTitleBg li:not(:first)").click(function(){			
			var attra = $(this).index() + 1;
			$(this).addClass("BMTitleBg_current").siblings().removeClass("BMTitleBg_current");			
			if( theLast==attra){
				$("#GG" + theLast).css("display","block");
			}
			else{
				$("#GG" + theLast).css("display","none");
				$("#GG" + attra).css("display","block");
			}
			theLast=attra;
		});
	});
</script>
<!--投注方式tab end-->
<!--玩法规则JS start-->
<script>
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
<!--玩法规则JS end-->
</head>

<body>
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
    <!--left start-->
  	<div class="lotter_left">
    	<!--球的种类信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="images/lottery/cz_18.png" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span>150000000000</span>元人民币</li>
                    <li>每周二、四、日晚21：30开奖，单注最高奖金&nbsp;&nbsp;<span style=" font-size:16px; font-weight:bold;">1000</span>&nbsp;&nbsp;万元！</li>
                    <li>正在销售：第&nbsp;&nbsp;<span>2011106</span> &nbsp;&nbsp;期</li>
                    <li>投注截止时间：<span>2011-09-11 19:30:00</span></li>
                </ul>
            </div>
            <div class="ball_time">
            	<ul>
                	<li><span>距本期销售截止时间还有</span></li>
                    <li class="ball_time_bg"><img src="../images/lottery/20110910162351.png" width="166" height="22" /></li>
                    <li class="ball_arrow">玩法规则</li>
                </ul>
            </div>
            <div class="clear"></div>
            <div class="ball_Remind">
				<ul>
					<li><span>玩法说明：</span></li>
					<li>1、选择倍投注时只需上传单倍方案；上传的方案注数必须跟填写的一致，否则可能无法出票。</li>
					<li>2、请严格参照“标准格式样本”格式上传方案。</li>
					<li>3、文件格式必须是文本文件。 </li>
					<li>4、由于上传的文件较大，会导致上传时间及在本页停留时间较长，请耐心等待。</li> 
				</ul>
			</div>
        </div>
        <!--球的种类信息 end-->
        <!--玩法选择 start-->
        <div class="BMTitleBg">
        	<ul>
            	<li>玩法选择：</li>
            	<li class="BMTitleBg_current">标准玩法</li>
            	<li>单式上传</li>
        	</ul>
        </div>
        <!--玩法选择 end-->
        <!--标准玩法 start-->
        <div class="Ball_Method" style="display:block;"  id="GG2">       	
            <!--标准玩法 start-->
            <div class="BM_body">            	
            	<div class="BM_select">选择投注:</div>                
                
                <div class="BM_redarea">
                	<div class="redarea_title">红色球</div>
                    <div class="redarea_ball">
                    	<ul>
                        	<li class="redarea_ball_li" onclick="onClickRedBall(this,1)">01</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,2)">02</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,3)">03</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,4)">04</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,5)">05</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,6)">06</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,7)">07</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,8)">08</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,9)">09</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,10)">10</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,11)">11</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,12)">12</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,13)">13</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,14)">14</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,15)">15</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,16)">16</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,17)">17</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,18)">18</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,19)">19</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,20)">20</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,21)">21</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,22)">22</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,23)">23</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,24)">24</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,25)">25</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,26)">26</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,27)">27</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,28)">28</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,29)">29</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,30)">30</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,31)">31</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,32)">32</li>
                            <li class="redarea_ball_li" onclick="onClickRedBall(this,33)">33</li>
                        </ul>
                    </div>
                    <div class="redarea_operat">
                          <div class="redarea_listbox">
                          		<select name="select" class="listbox" id="select">
                          			<option>6</option>
                                	<option>8</option>
                                	<option>10</option>
                          		</select>
                          </div>
                          <input name="" class="jixuan_empty" type="image" src="images/369caibg/369cai_61.gif" />
                          <input name="" class="jixuan_empty" type="image" src="images/369caibg/369cai_63.gif" />
                    </div>
                </div>
                <div class="BM_bluearea">
                	<div class="bluearea_title">蓝色球</div>
                    <div class="bluearea_ball">
                    	<ul>
                        	<li class="bluearea_ball_li" onclick="onClickRedBall(this,1)">01</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,2)">02</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,3)">03</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,4)">04</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,5)">05</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,6)">06</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,7)">07</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,8)">08</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,9)">09</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,10)">10</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,11)">11</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,12)">12</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,13)">13</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,14)">14</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,15)">15</li>
                            <li class="bluearea_ball_li" onclick="onClickRedBall(this,16)">16</li>
                        </ul>                    </div>
                    <div class="bluearea_operat">
                    	 <div class="bluearea_listbox">
                          		<select name="select" class="listbox" id="select">
                          			<option>6</option>
                                	<option>8</option>
                                	<option>10</option>
                          		</select>
                          </div>
                          <input name="" class="jixuan_empty" type="image" src="images/369caibg/369cai_61.gif" />
                          <input name="" class="jixuan_empty" type="image" src="images/369caibg/369cai_63.gif" />
                    </div>
                </div>
                <div class="ball_total">您共选择了&nbsp;&nbsp;<span>0</span>&nbsp;&nbsp;个红球号码，&nbsp;&nbsp;<span>0</span>&nbsp;&nbsp;个蓝球号码，共&nbsp;&nbsp;<span>0注</span>&nbsp;&nbsp;，&nbsp;&nbsp;共<span>￥0元</span>&nbsp;&nbsp;</div>
                           
            </div>
            <!--标准玩法 end-->
            <!--投注清单 start-->
            <div class="Betting_menu">
            	<div class="Betting_select">投注清单</div>
                <div class="Betting_add_btn_bg">
                	<div class="Betting_add_btn">
                	<div class="btn_1">添加投注到列表</div>
                    <div class="btn_2"></div>
                </div>
                <div class="Betting_list"><textarea class="Betting_list_area" name="" cols="65" rows="10"></textarea></div>
                <div class="Betting_listbox">
                	<select name="select" class="listbox" id="select">
                    	<option>6</option>
                        <option>8</option>
                        <option>10</option>
                    </select>                      		
                </div>
                <div class="Betting_list_jixuan">
                	<div class="btn_1">机选</div>
                    <div class="btn_2"></div>
                </div>
                <div class="Betting_list_empty">
                	<div class="btn_1">清空列表</div>
                    <div class="btn_2"></div>
                </div>
                <div class="Betting_list_empty">
                	<div class="btn_1">删除所选</div>
                    <div class="btn_2"></div>
                </div>
                <div class="Betting_total">
                列表内包含&nbsp;&nbsp;<span>0注</span>&nbsp;&nbsp;彩票，倍投  倍，合计&nbsp;&nbsp;<span>￥0元</span>&nbsp;&nbsp;
                </div>
            </div>
            </div>
            <!--投注清单 end-->
            <!--购买方式　start-->
            <div class="shop_Method">
            	<ul>
               		<li style=" margin-left:15px;">购买方式：</li>
                	<li><input name="" type="radio" value="1" class="shop_Method_item"/></li>
                  	<li>代购</li>
                  	<li><input name="" type="radio" value="2" checked="checked" class="shop_Method_item" /></li>
                    <li>合买</li>
                  	<li><input name="" type="radio" value="3" class="shop_Method_item" /></li>
                    <li>追号</li>
                </ul>
            </div>
            <!--购买方式　end-->
            <!--合买设置 start-->            
            <div class="shop_together_bg" style="display:block;">
            	<div class="shop_together">合买设置</div>
                <div class="shop_bg">
               	  <div class="shop_name">方案份数：</div>
               	  <div class="shop_con">方案共0份，每份 1元</div>
                  <div class="clear"></div>
                    <div class="shop_name">我要提成：</div>
                	<div class="shop_con">
<select name="select2" id="select2">
                        	<option>0</option>
                        	<option>1</option>
                        	<option>2</option>
                            <option>3</option>
                        	<option>4</option>
                        	<option>5</option>
                            <option>6</option>
                        	<option>7</option>
                        	<option>8</option>
                            <option>9</option>
                        	<option>10</option>
              	    	</select>
                		%
                	</div>
                    <div class="clear"></div>
                    <div class="shop_name"><span>*</span>我要认购：</div>
                	<input type="text" name="textfield" id="textfield" value="1" class="shop_input" />
                    <div class="shop_con">份，共计 1元（至少认购方案的20％，共1份）</div>
                    <div class="clear"></div>
                    <div class="shop_name">我要保底：</div>
                    <input type="text" name="textfield" id="textfield" value="1" class="shop_input" />
       	    		<div class="shop_con">份，
                   	  <input type="checkbox" name="checkbox" id="checkbox" />
               	  每份 1元</div>
                    <div class="clear"></div>
                    <div class="shop_name">是否公开：</div>
                	<div class="shop_Public">
                    	<ul>
                			<li><input name="" type="radio" value="1" checked="checked" class="shop_Public_item"/></li>
                  			<li>完全公开</li>
                  			<li><input name="" type="radio" value="2" class="shop_Public_item" /></li>
                    		<li>开奖后公开</li>
                  			<li><input name="" type="radio" value="3" class="shop_Public_item" /></li>
                    		<li>仅跟单人可见</li>
                            <li><input name="" type="radio" value="3" class="shop_Public_item" /></li>
                    		<li>不可见</li>
                		</ul>
                    </div>
                    <div class="clear"></div>
                    <div class="shop_name">方案描述：</div>
                    <div class="shop_Surplus">最多100个字符。</div>
                    <div class="clear"></div>
                    <textarea name="" cols="" rows=""  class="shop_Scheme"></textarea>
                </div>
            </div>
            <!--合买设置 end-->
            <!--追加设置 start-->
            <div class="shop_together_bg" style="display:none;">
           	  <div class="append_bg">追加设置</div>
                <div class="append_listbox">
                	<select name="select3" id="select3">
                  		<option>追加5期</option>
                        <option>追加10期</option>
                        <option>追加20期</option>
                        <option>追加50期</option>
                    </select>
                </div>
                <div class="append_Rules"><a href="#">查看追号规则</a></div>
                <div class="append_textarea">
                	<ul>
                    	<li>
                            	<div class="append_sortNum">01.</div>
                                <div class="append_box"><input name="" type="checkbox" value="" /></div>
                                <div class="append_period">11107期</div>
                                <input name="" type="text" value="" class="append_input" />
                                <div class="append_value">倍&nbsp;￥0.00元</div>
                        </li>
                        <li>
                            	<div class="append_sortNum">02.</div>
                                <div class="append_box"><input name="" type="checkbox" value="" /></div>
                                <div class="append_period">11107期</div>
                                <input name="" type="text" value="" class="append_input" />
                                <div class="append_value">倍&nbsp;￥0.00元</div>
                        </li>
                        <li>
                            	<div class="append_sortNum">03.</div>
                                <div class="append_box"><input name="" type="checkbox" value="" /></div>
                                <div class="append_period">11107期</div>
                                <input name="" type="text" value="" class="append_input" />
                                <div class="append_value">倍&nbsp;￥0.00元</div>
                        </li>
                        <li>
                            	<div class="append_sortNum">04.</div>
                                <div class="append_box"><input name="" type="checkbox" value="" /></div>
                                <div class="append_period">11107期</div>
                                <input name="" type="text" value="" class="append_input" />
                                <div class="append_value">倍&nbsp;￥0.00元</div>
                        </li>
                        <li>
                            	<div class="append_sortNum">05.</div>
                                <div class="append_box"><input name="" type="checkbox" value="" /></div>
                                <div class="append_period">11107期</div>
                                <input name="" type="text" value="" class="append_input" />
                                <div class="append_value">倍&nbsp;￥0.00元</div>
                        </li>
                        <li>
                            	<div class="append_sortNum">06.</div>
                                <div class="append_box"><input name="" type="checkbox" value="" /></div>
                                <div class="append_period">11107期</div>
                                <input name="" type="text" value="" class="append_input" />
                                <div class="append_value">倍&nbsp;￥0.00元</div>
                        </li>
                    </ul>
                
                </div>
                <div class="append_total">追号总金额：0 元<input name="" type="checkbox" checked="checked" value="" />全选</div>
            </div>
            <!--追加设置　end-->
            <!--确认购买 start-->
            <div class="Purchase_bg">确认购买</div>
            <div class="Purchase_total">
            	<ul>
                	<li>当前用户：醉能虎彩， 账户可用余额：￥43.00元</li>
                	<li>本次投注所需金额为￥0元</li>
                	<li><input name="" type="checkbox" checked="checked" value="" />我已阅读并同意<a href="#">《用户合买代购协议》</a></li>
                </ul>
            </div>
            <div class="Purchase_btn"><input name="" type="image" src="images/369caibg/buy_btn.jpg" width="130" height="45" /></div>
            <!--确认购买 end-->
        </div>
        <!--标准玩法 end-->
        <!--单式上传　start-->
        <div class="Ball_Method"  id="GG3">
            <!--上传设置　start-->
            <div class="BM_body">
				<div class="BM_select">上传设置:</div>
                <div class="only_bg">
                	<div class="only_fileName">选择文件：</div>
                    <input name="" type="text" class="only_fileInput" />
                	<div class="only_fileBtn">
                		<div class="btn_1">浏览...</div>
                    	<div class="btn_2"></div>
                	</div>
                    <div class="only_getfiles">该文件由本站提供的<a href="#" title="点击它可以下载对应免费软件">免费软件</a>生成</div>
                	<div class="clear"></div>
                	<div class="only_Betting_num">注数：</div>
                    <input name="" type="text" class="only_Betting_input" />
                    <div class="only_Multiple">倍数：</div>
                    <input name="" type="text" class="only_Multiple_input" />
                    <div class="only_total">总额：&nbsp;&nbsp;<span>0元</span>&nbsp;&nbsp;</div>
                    <div class="only_upload">
                    	<ul>
                        	<li><span>上传说明：</span></li>
                            <li>1、选择倍投注时只需上传单倍方案；上传的方案注数必须跟填写的一致，否则可能无法出票。</li>
                            <li>2、请严格参照“标准格式样本”格式上传方案。</li>
                            <li>3、文件格式必须是文本文件。 </li>
                            <li>4、由于上传的文件较大，会导致上传时间及在本页停留时间较长，请耐心等待。</li> 
                        </ul>
                    </div>
                </div>
            </div>
            
            <!--上传设置 end-->
            <!--购买方式　start-->
            <div class="shop_Method">
            	<ul>
               		<li style=" margin-left:15px;">购买方式：</li>
                	<li><input name="" type="radio" value="1" class="shop_Method_item"/></li>
                  	<li>代购</li>
                  	<li><input name="" type="radio" value="2" checked="checked" class="shop_Method_item" /></li>
                    <li>合买</li>
                </ul>
            </div>
            <!--购买方式　end-->
            <!--合买设置 start-->            
            <div class="shop_together_bg" style="display:block;">
            	<div class="shop_together">合买设置</div>
                <div class="shop_bg">
               	  <div class="shop_name">方案份数：</div>
               	  <div class="shop_con">方案共0份，每份 1元</div>
                  <div class="clear"></div>
                    <div class="shop_name">我要提成：</div>
                	<div class="shop_con">
<select name="select2" id="select2">
                        	<option>0</option>
                        	<option>1</option>
                        	<option>2</option>
                            <option>3</option>
                        	<option>4</option>
                        	<option>5</option>
                            <option>6</option>
                        	<option>7</option>
                        	<option>8</option>
                            <option>9</option>
                        	<option>10</option>
              	    	</select>
                		%
                	</div>
                    <div class="clear"></div>
                    <div class="shop_name">*我要认购：</div>
                	<input type="text" name="textfield" id="textfield" value="1" class="shop_input" />
                    <div class="shop_con">份，共计 1元（至少认购方案的20％，共1份）</div>
                    <div class="clear"></div>
                    <div class="shop_name">我要保底：</div>
                    <input type="text" name="textfield" id="textfield" value="1" class="shop_input" />
       	    		<div class="shop_con">份，
                   	  <input type="checkbox" name="checkbox" id="checkbox" />
               	  每份 1元</div>
                    <div class="clear"></div>
                    <div class="shop_name">是否公开：</div>
                	<div class="shop_Public">
                    	<ul>
                			<li><input name="" type="radio" value="1" checked="checked" class="shop_Public_item"/></li>
                  			<li>完全公开</li>
                  			<li><input name="" type="radio" value="2" class="shop_Public_item" /></li>
                    		<li>开奖后公开</li>
                  			<li><input name="" type="radio" value="3" class="shop_Public_item" /></li>
                    		<li>仅跟单人可见</li>
                            <li><input name="" type="radio" value="3" class="shop_Public_item" /></li>
                    		<li>不可见</li>
                		</ul>
                    </div>
                    <div class="clear"></div>
                    <div class="shop_name">方案描述：</div>
                    <div class="shop_Surplus">最多100个字符。</div>
                    <div class="clear"></div>
                    <textarea name="" cols="" rows=""  class="shop_Scheme"></textarea>
                </div>
            </div>
            <!--合买设置 end-->
            <!--确认购买 start-->
            <div class="Purchase_bg">确认购买</div>
            <div class="Purchase_total">
            	<ul>
                	<li>当前用户：醉能虎彩， 账户可用余额：￥43.00元</li>
                	<li>本次投注所需金额为￥0元</li>
                	<li><input name="" type="checkbox" checked="checked" value="" />我已阅读并同意<a href="#">《用户合买代购协议》</a></li>
                </ul>
            </div>
            <div class="Purchase_btn"><input name="" type="image" src="images/369caibg/buy_btn.jpg" width="130" height="45" /></div>
            <!--确认购买 end-->
        </div>
        <!--单式上传设置　start-->
    </div>
    <!--left end-->
    <!--right start-->
  	<div class="lotter_right">
    	<!--开奖历史　start-->
        <div class="history_top"></div>
        <div class="history_body">
       	  <div class="history_title">开奖历史</div>
            <div class="history_Last">上期开奖号码</div>
			<div class="history_LastNum">
            	<ul>
                	<li>04</li>
                    <li>09</li>
                    <li>16</li>
                    <li>17</li>
                    <li>22</li>
                    <li>29</li>
                    <li style="background:url(../images/369caibg/ball_bg.gif) -97px center no-repeat;">15</li>                   
                </ul>
            </div>
          <div class="history_preNum">
            	<ul>
                	<li><span class="black">2011107</span>&nbsp;<span class="red">04,09,16,17,22,29</span>&nbsp;|&nbsp;<span class="blue">15</span></li>
                    <li><span class="black">2011107</span>&nbsp;<span class="red">04,09,16,17,22,29</span>&nbsp;|&nbsp;<span class="blue">15</span></li>
                    <li><span class="black">2011107</span>&nbsp;<span class="red">04,09,16,17,22,29</span>&nbsp;|&nbsp;<span class="blue">15</span></li>
                    <li><span class="black">2011107</span>&nbsp;<span class="red">04,09,16,17,22,29</span>&nbsp;|&nbsp;<span class="blue">15</span></li>
                </ul>
          </div>
           <!--<div class="history_more">more</div>-->
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
<div class="Stat">
        	<div class="STitleBg">
            	<div class="STitle">统计分析</div>
                <div class="Stat_softWare">
				  <img src="images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>369cai统计分析专家</h3>
                    首创彩票缩水概念，
                     2001年11月即推出了全
                     国第一套足彩投注缩水软件——“足球玩玩”软件，在全国各地掀起缩水投注的热潮，直接催生了数千中奖彩民 ...

                </div>
                <div class="Stat_btn"><a href="#"><img src="images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
            </div>
        </div>
        <!--统计分析 end-->
        <!--缩水过滤 start-->
        <div class="Stat">
        	<div class="STitleBg">
           	  <div class="STitle">缩水过滤</div>
                <div class="Stat_softWare">
				  <img src="images/img/right_17.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>369cai缩水过滤专家</h3>
                    首创彩票缩水概念，
                     2001年11月即推出了全
                     国第一套足彩投注缩水软件——“足球玩玩”软件，在全国各地掀起缩水投注的热潮，直接催生了数千中奖彩民 ...

                </div>
                <div class="Stat_btn"><a href="#"><img src="images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
            </div>
        </div>
        <!--缩水过滤 end-->
        <!--投注页专家推荐 start-->
        <div class="RecomTitleBg">
        	<div class="RecomTitle">
            	<ul>
               	  <li class="RecomTitle1 RecomCurrent_bg">专家推荐</li>
                  <li class="RecomTitle2">相关资讯</li>
                </ul>
            </div>
        </div>
        <div class="Recom_Expert" style="display:block;" id="RR1">
        	<div class="Expert_pic">
        		<ul>
            		<li><img src="images/img/right_26.jpg" width="59" height="61" /></li>
					<li>大乐透</li>
            	</ul>
            </div>
            <div class="Expert_con">
            	<ul>
                	<li><span>大乐透同尾号"9"倾巢出动 奖池2.44亿</span></li>
					<li>相关资讯萨踏上十冠征程</li>
					<li>今年3D第五次开出豹子号</li>
                </ul>
            </div>
            <div class="Expert_pic">
        		<ul>
            		<li><img src="images/img/right_26.jpg" width="59" height="61" /></li>
					<li>大乐透</li>
            	</ul>
            </div>
            <div class="Expert_con">
            	<ul>
                	<li><span>大乐透同尾号"9"倾巢出动 奖池2.44亿</span></li>
					<li>相关资讯萨踏上十冠征程</li>
					<li>今年3D第五次开出豹子号</li>
                </ul>
            </div>
        </div>
  <div class="RecomCon" id="RR2">
        	<ul>
            	<li>相关资讯萨踏上十冠征程</li>
				<li>大乐透同尾号"9"倾巢出动 奖池2.44亿</li>
				<li>今年3D第五次开出豹子号</li>
				<li>11选5任选五投注实用方法大汇总</li>
            </ul>
        </div>  
        <!--投注页专家推荐 end-->
        <!--/*24小时客服*/ start-->
        <div class="hours_24"><a href="http://wpa.qq.com/msgrd?V=1&Uin=2432882152&Site=" target="_blank"><img src="images/img/right_28.jpg" width="252" height="75" /></a></div>
        <!--/*24小时客服*/　end-->
  </div>
    <!--right end-->
    <div class="clear"></div>
    <!--彩民引导导航　start-->
    <div class="outer">
    	<div class="caimin_nav">
        	<div class="jiaoyi"><img src="images/img/jiaoyi.jpg" width="245" height="150" /></div>
            <!--新手上路　start-->
        	<div class="xinshou">
            	<div class="xinshou_title">新手上路</div>
                <div class="xinshou_body">
                	<ul>
                    	<li class="xinshou_body_li"><a href="http://www.369cai.com/register.htm">免费注册</a></li>
                        <li class="xinshou_body_li"><a href="https://lab.alipay.com/user/reg/index.htm">开通支付宝</a></li>
                        <li class="xinshou_body_li"><a href="https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2Fwww.alipay.com%2Fuser%2Finpour_request.htm">支付宝充值</a></li>
                        <li class="xinshou_body_li"><a href="#">购彩流程</a></li>
                    </ul>
                </div>
            </div>
            <!--新手上路　end-->
            <!--支付方式　start-->
        	<div class="xinshou">
            	<div class="pay_title">支付方式</div>
                <div class="xinshou_body">
                	<ul>
                    	<li class="xinshou_body_li"><a href="http://life.alipay.com/activity/zjqd/tb/qdlry.html?src=yy_zfdq_tbshangcheng_01">支付宝支付</a></li>
                      <li class="xinshou_body_li"><a href="/help/">兑奖说明</a></li>
                        <li class="xinshou_body_li"><a href="/help/">充值与提现</a></li>
                    </ul>
                </div>
            </div>
            <!--支付方式　end-->
            <!--帮助中心　start-->
        	<div class="xinshou">
            	<div class="help_title">帮助中心</div>
                <div class="xinshou_body">
                	<ul>
                   	  <li class="xinshou_body_li"><a href="/help/">常见问题</a></li>
                        <li class="xinshou_body_li"><a href="/lotteryInfo/">热门解释</a></li>
                        <li class="xinshou_body_li" title="010-56203469">客服电话</li>
                    </ul>
                </div>
            </div>
            <!--帮助中心　end-->
        </div>
    </div>
    <!--彩民引导导航　end-->
    <!--购彩流程　start-->
    <div class="outer">
    	<div class="goucai">
        	<div class="goucai_title">
        	九年彩票金牌服务经验、十八家福体彩中心合作伙伴、2万家投注站出票系统支持、百万用户的选择。
        	</div>
          <div class="goucai_1">
            	<ul>
               	  <li class="goucai_li_title"><a href="http://www.369cai.com/register.htm">注册登陆</a></li>
                    <li>简单信息填写， 30秒成为本站会员</li>
                </ul>
          </div>
            <div class="goucai_gt"></div>
            <div class="goucai_2">
            	<ul>
                	<li class="goucai_li_title"><a href="https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2Fwww.alipay.com%2Fuser%2Finpour_request.htm">充值</a></li>
                    <li>多种充值渠道：支付宝、银行卡</li>
                </ul>
            </div>
            <div class="goucai_gt"></div>
      <div class="goucai_3">
            	<ul>
                	<li class="goucai_li_title"><a href="http://www.369cai.com/groupbuy/">购买彩票</a></li>
                    <li>
                      彩种全面，追号、合买、 定制跟单玩法更多
                    </li>
                </ul>
            </div>
            <div class="goucai_gt"></div>
			<div class="goucai_4">
            	<ul>
                	<li class="goucai_li_title"><a href="http://www.369cai.com/customer/">中奖提现</a></li>
                    <li>提现24小时内到账， 绑定银行卡资金更安全
					</li>
                </ul>
            </div>
        </div>
    </div>
    <!--购彩流程　end-->
</div>
<!--Web Body end-->
</body>
</html>

