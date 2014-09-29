<%@ page language="java" pageEncoding="UTF-8"%>
<!--投注清单 start-->
    <div id="playType_standard_list" class="Betting_menu">
        <div class="Betting_select">投注清单</div>
           <div class="Betting_add_btn_bg">
             	<div class="Betting_add_btn-one">
               	<div class="zidingbtn_1" onclick="addPlan()">添加投注到列表</div>
                <div class="zidingbtn_2" onclick="gotoSoftware()"><a href="#"><img src="/images/index/index001_14.png" ></a></div>
            	<div>
					<select id="randomselect_tcp" name="item"  id="select">
               			<option value="1"> 中6保5</option>
						<option value="2"> 中6保4</option>
						<option value="3"> 中5保5</option>
						<option value="4"> 中5保4</option>
						<option value="5"> 中4保4</option>
                   	</select>
                	<input type="submit" value="在线缩水" style="width: 60px;" onclick="tijiao()" />
				</div>
            </div>
            <div id="Betting_list_tcp" class="Betting_list_tcp" style="color: red;"></div>
            <div class="Betting_list">
             	<select id="planLists" size="10" multiple="multiple" class="Betting_list_area""></select>
            </div>
            <div class="Betting_listbox">
               	<select id="randomselect" name="select" class="listbox" id="select">
           			<option>1</option>
					<option>2</option>
					<option>5</option>
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
					<option>200</option>
                </select>                      		
			</div>
            <div class="Betting_list_jixuan">
            	<div id="random_select_btn" class="btn_1" onclick="randomBet()">机选</div>
                <div class="btn_2"></div>
            </div>
            <div class="Betting_list_empty">
            	<div class="btn_1" onclick="cleanAllPlan()">清空列表</div>
                <div class="btn_2"></div>
            </div>
            <div class="Betting_list_empty">
            	<div class="btn_1" onclick="removePlan()">删除所选</div>
                <div class="btn_2"></div>
            </div>
            <div class="Betting_total">
            	列表内包含<strong class="red" id="betTotalNum">0</strong>注彩票，倍投 
				<input type="text" class="input_bg_a" value="1" id="multiple" name="multiple" size="3"
					onkeyup="value=value.replace(/[^\d]/g,'1');" onblur=onMultipleChange(); /> 
 				倍，合计￥<strong class="red" id="totalBetMoney">0</strong>元
            </div>
       </div>
	</div>
	<div class="clear"></div>
<!--投注清单 end-->