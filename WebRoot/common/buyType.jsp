<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--购买方式选择　start-->
<div class="shop_Method">
	<ul>
   		<li style=" margin-left:15px;">购买方式：</li>
    	<li>
    		<span id="betType_dg"><input name="betType" id="dg_radio" type="radio" class="shop_Method_item" checked="checked" onclick="onChangeBetType(0)"/>代购</span>
    	</li>
      	<li>
      		<span id="betType_hm"><input name="betType" id="hm_radio" type="radio" class="shop_Method_item" onclick="onChangeBetType(1)"/>发起合买</span>
      	</li>
      	<li>
      		<span id="betType_zh"><input name="betType" id="zh_radio" type="radio" class="shop_Method_item" onclick="onChangeBetType(2)"/>追号</span>
      	</li>
    </ul>
</div>
<!--购买方式选择　end-->
<!--合买设置 start-->            
<div id="betType_group_box"  class="shop_together_bg">
	<div class="shop_together">合买设置</div>
    <div class="shop_bg">
		<div class="shop_name">方案份数：</div>
		<div class="shop_con">
			方案共<span class="red" id="stock">0</span>份，每份
			<span class="red" id="stock_money">1</span>元
		</div>
      	<div class="clear"></div>
        <div class="shop_name">我要提成：</div>
    	<div class="shop_con">
		<select name="commision" id="commision">
  			<option>0</option>
  			<option>1</option>
  			<option>2</option>
      		<option>3</option>
  			<option>4</option>
  			<option selected="selected">5</option>
      		<option>6</option>
  			<option>7</option>
  			<option>8</option>
      		<option>9</option>
  			<option>10</option>
		</select> %
	</div>
   <div class="clear"></div>
   <div class="shop_name"><span>*</span>我要认购：</div>
    	<input type="text" class="shop_input" value="0" size="5" id="myself" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="validateGroupBuy()"/> 
        <div class="shop_con">份，共计 
			<span class="red" id="single_money">0</span> 元(至少认购方案的20%，共
			<span id="shareStock" class="red"> 0</span> 份)
		</div>
        <div class="clear"></div>
        <div class="shop_name">我要保底：</div>
        <input type="text" class="shop_input" value="0"  size="5" id="procted" onkeyup="value=value.replace(/[^\d]/g,'');validateGroupBuy()"/>
		<div class="shop_con">份，
			<input name="procted_all" type="checkbox" id="procted_all" onclick="validateGroupBuy()"/> 
   	  		全额保底  共计 <span class="red" id="protect_total">0</span> 元 
   	  	</div>
        <div class="clear"></div>
        <div class="shop_name">是否公开：</div>
    	<div class="shop_Public">
        	<ul>
    			<li><input name="content" type="radio" id="radio1" value="gk" checked="checked" class="shop_Public_item"/></li>
      			<li>完全公开</li>
      			<li><input name="content" type="radio" id="radio2" value="kj" class="shop_Public_item" /></li>
        		<li>开奖后公开</li>
      			<li><input name="content" type="radio" id="radio3" value="only" class="shop_Public_item" /></li>
        		<li>仅跟单人可见</li>
                <li><input name="content" type="radio" id="radio4" value="bm" class="shop_Public_item" /></li>
        		<li>不可见</li>
    		</ul>
        </div>
        <div class="clear"></div>
        <div class="shop_name">方案描述：</div>
        <div class="shop_Surplus">最多100个字符。</div>
        <div class="clear"></div>
        <textarea name="desc" id="desc" class="shop_Scheme"></textarea>
    </div>
</div>
<!--合买设置 end-->

<!--追号设置 start-->
<div id="betType_chase_box" class="shop_together_bg">
	<div class="append_bg">追加设置</div>
    <div class="append_listbox">
    	追加<select id="chaseNumber" onchange="onChaseNumChange()">
 			<option>5</option>
       		<option>10</option>
       		<option>20</option>
       		<option>50</option>
		</select>期
    </div>
    <div class="append_Rules"><a href="#">查看追号规则</a></div>
    <div class="append_textarea">
    	<ul id="chaseTermList"></ul>
    </div>
    <div class="append_total">
    	追号总金额：<strong class="red" id="chaseTotalMoney">0</strong> 元 
    	<input id="all_select" type="checkbox" checked="checked" value="" onclick="onAllSelectChange()"/>全选
    </div>
</div>
<!--追号设置　end-->
            
<!--确认购买 start-->
<div id="confirm_box" class="Purchase_bg">确认购买</div>
<div class="Purchase_total">
	<ul>
	
		<c:if test="${customer.nickName == null}">
			<li>您尚未登录，<a href="/customer/">点击这里登录</a>！</li>
			<div id="thenickname" style="display:none">1</div>
		</c:if>
		<c:if test="${customer.nickName != null}">
		<div id="thenickname" style="display:none">2</div>
			<li>当前用户：<span class='red'>${customer.nickName}</span>，
			账户可用余额：￥<span class='red'>${customer.wallet.balance}元</span></li>
		</c:if>
    	<li>本次投注所需金额为￥<span id="confirm_money" class="red">0</span>元</li>
    	<li><input name="read" type="checkbox" checked="checked"/>我已阅读并同意<a href="/lottery/index.htm?lotteryType=read">《用户合买代购协议》</a></li>
    </ul>
</div>
<div class="Purchase_btn"><input name="" type="image" src="../images/369caibg/buy_btn.jpg" width="130" height="45" onclick="onSubmit()"/></div>
<!--确认购买 end-->