/*
 *常量区
 */
 
var BALL_ON  = "ball_down_true";

//玩法 -- 标准(普通)，组三，组六，上传文件
var PLAY_TYPE_STD = 1;
var PLAY_TYPE_Z3 = 2;
var PLAY_TYPE_Z6 = 3;
var PLAY_TYPE_FILE_UPLOAD = 4;

//玩法 - 子类 (包号,和值)
var SUB_PLAY_TYPE_BH = 1;
var SUB_PLAY_TYPE_HZ = 2;

/*
*全局变量
*/
var g_lotteryType = "pls";
var g_playType = PLAY_TYPE_STD;
var g_subPlayType = SUB_PLAY_TYPE_BH;
var g_betType = BET_TYPE_PERSONAL;
var g_betCount = 0;

// 标准玩法的所有球
var g_std_all_bai_BallList = [];
var g_std_all_shi_BallList = [];
var g_std_all_ge_BallList = [];

// 组3玩法的所有球
var z3_g_std_all_BallList = [];
var z3hz_g_std_all_BallList = [];

// 组6玩法的所有球
var z6_g_std_all_BallList = [];
var z6hz_g_std_all_BallList = [];


// 标准玩法的当前球
var g_std_cur_bai_BallList = [];
var g_std_cur_shi_BallList = [];
var g_std_cur_ge_BallList = [];

// 组3玩法的当前球
var z3_g_std_cur_BallList = [];
var z3hz_g_std_cur_BallList = [];

// 组6玩法的当前球
var z6_g_std_cur_BallList = [];
var z6hz_g_std_cur_BallList = [];

var g_plan;

function initBallList()
{
	for(var i = 0; i < 10; i++) {
		g_std_all_bai_BallList[i] = $("#bai_std_ball_" + i)[0];
		g_std_all_shi_BallList[i] = $("#shi_std_ball_" + i)[0];
		g_std_all_ge_BallList[i] = $("#ge_std_ball_" + i)[0];
		z3_g_std_all_BallList[i] = $("#zu3_ball_" + i)[0];
		z6_g_std_all_BallList[i] = $("#zu6_ball_" + i)[0];
	}
	
	for(var i = 0; i < 27; i++) {
		z3hz_g_std_all_BallList[i] = $("#zu3hz_ball_" + i)[0];
	}
	
	for(var i = 0; i < 25; i++) {
		z6hz_g_std_all_BallList[i] = $("#zu6hz_ball_" + i)[0];
	}
}

/* 点击球事件 */
function onClickBall(ball, num, listNum)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		if(listNum == 1) {
			addBallToList(g_std_cur_bai_BallList, num);
		}
		else if(listNum == 2) {
			addBallToList(g_std_cur_shi_BallList, num);
		}
		else if(listNum == 3) {
			addBallToList(g_std_cur_ge_BallList, num);
		}
	}
	else {
		if(listNum == 1) {
			deleteBallFromList(g_std_cur_bai_BallList, num);
		}
		else if(listNum == 2) {
			deleteBallFromList(g_std_cur_shi_BallList, num);
		}
		else {
			deleteBallFromList(g_std_cur_ge_BallList, num);
		}
	}
	displayBetNum();
}

function onClickZu3Ball(ball, num)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		addBallToList(z3_g_std_cur_BallList, num);
	}
	else {
		deleteBallFromList(z3_g_std_cur_BallList, num);
	}
	displayzu3BetNum();
}

function onClickZu3hzBall(ball, num)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		addBallToList(z3hz_g_std_cur_BallList, num);
	}
	else {
		deleteBallFromList(z3hz_g_std_cur_BallList, num);
	}
	displayzu3hzBetNum();
}


function onClickZu6Ball(ball, num)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		addBallToList(z6_g_std_cur_BallList, num);
	}
	else {
		deleteBallFromList(z6_g_std_cur_BallList, num);
	}
	displayzu6BetNum();
}

function onClickZu6hzBall(ball, num)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		addBallToList(z6hz_g_std_cur_BallList, num);
	}
	else {
		deleteBallFromList(z6hz_g_std_cur_BallList, num);
	}
	displayzu6hzBetNum();
}


function selectall(num)
{
	var list = [];
	var list2 = [];
	if(num == 1) {
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 2) {
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else {
		list = g_std_cur_ge_BallList;
		list2 = g_std_all_ge_BallList;
	}
	
	for(var i = 0; i < list2.length; i++) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	displayBetNum();
}

function zu3_selectall()
{
	list = z3_g_std_cur_BallList;
	list2 = z3_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i++) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	displayzu3BetNum();
}

function zu3hz_selectall()
{
	list = z3hz_g_std_cur_BallList;
	list2 = z3hz_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i++) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	displayzu3hzBetNum();
}

function zu6_selectall()
{
	var list = [];
	var list2 = [];
	list = z6_g_std_cur_BallList;
	list2 = z6_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i++) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	displayzu6BetNum();
}

function zu6hz_selectall()
{
	var list = [];
	var list2 = [];
	list = z6hz_g_std_cur_BallList;
	list2 = z6hz_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i++) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	displayzu6hzBetNum();
}

function selectodd(num)
{
	var list = [];
	var list2 = [];
	if(num == 1) {
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 2) {
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else {
		list = g_std_cur_ge_BallList;
		list2 = g_std_all_ge_BallList;
	}
	
	for(var i = 1; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	
	for(var i = 0; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayBetNum();
}

function zu3_selectodd()
{
	var list = [];
	var list2 = [];
	list = z3_g_std_cur_BallList;
	list2 = z3_g_std_all_BallList;

	
	for(var i = 1; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 0; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu3BetNum();
}

function zu3hz_selectodd()
{
	var list = [];
	var list2 = [];
	list = z3hz_g_std_cur_BallList;
	list2 = z3hz_g_std_all_BallList;

	
	for(var i = 1; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 0; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu3hzBetNum();
}

function zu6_selectodd()
{
	var list = [];
	var list2 = [];
	list = z6_g_std_cur_BallList;
	list2 = z6_g_std_all_BallList;

	
	for(var i = 1; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 0; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu6BetNum();
}


function zu6hz_selectodd()
{
	var list = [];
	var list2 = [];
	list = z6hz_g_std_cur_BallList;
	list2 = z6hz_g_std_all_BallList;

	
	for(var i = 1; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 0; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu6hzBetNum();
}

function selecteven(num)
{
	var list = [];
	var list2 = [];
	if(num == 1) {
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 2) {
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else {
		list = g_std_cur_ge_BallList;
		list2 = g_std_all_ge_BallList;
	}

	for(var i = 0; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 1; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayBetNum();
}

function zu3_selecteven()
{
	var list = [];
	var list2 = [];
	list = z3_g_std_cur_BallList;
	list2 = z3_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 1; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu3BetNum();
}

function zu3hz_selecteven()
{
	var list = [];
	var list2 = [];
	list = z3hz_g_std_cur_BallList;
	list2 = z3hz_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 1; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu3hzBetNum();
}

function zu6_selecteven()
{
	var list = [];
	var list2 = [];
	list = z6_g_std_cur_BallList;
	list2 = z6_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 1; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu6BetNum();
}

function zu6hz_selecteven()
{
	var list = [];
	var list2 = [];
	list = z6hz_g_std_cur_BallList;
	list2 = z6hz_g_std_all_BallList;
	
	for(var i = 0; i < list2.length; i+=2) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	for(var i = 1; i < list2.length; i+=2) {
		if($(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).removeClass(BALL_ON);
			deleteBallFromList(list, i);
		}
	}
	displayzu6hzBetNum();
}

function selectclear(num)
{
	var list = [];
	var list2 = [];
	if(num == 1) {
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 2) {
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else {
		list = g_std_cur_ge_BallList;
		list2 = g_std_all_ge_BallList;
	}
	
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(list2[index]).removeClass(BALL_ON);
	}
	
	list.splice(0, list.length);
	displayBetNum();
}

function zu3_selectclear()
{
	var list = [];
	var list2 = [];
	list = z3_g_std_cur_BallList;
	list2 = z3_g_std_all_BallList;
	
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(list2[index]).removeClass(BALL_ON);
	}
	list.splice(0, list.length);
	displayzu3BetNum();
}

function zu3hz_selectclear()
{
	var list = [];
	var list2 = [];
	list = z3hz_g_std_cur_BallList;
	list2 = z3hz_g_std_all_BallList;	
	
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(list2[index]).removeClass(BALL_ON);
	}
	list.splice(0, list.length);
	displayzu3hzBetNum();
}

function zu6_selectclear()
{
	var list = [];
	var list2 = [];
	list = z6_g_std_cur_BallList;
	list2 = z6_g_std_all_BallList;	
	
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(list2[index]).removeClass(BALL_ON);
	}
	list.splice(0, list.length);
	displayzu6BetNum();
}

function zu6hz_selectclear()
{
	var list = [];
	var list2 = [];
	list = z6hz_g_std_cur_BallList;
	list2 = z6hz_g_std_all_BallList;	
	
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(list2[index]).removeClass(BALL_ON);
	}
	list.splice(0, list.length);
	displayzu6hzBetNum();
}

function clearAllBalls()
{
	
}

/* 将所选的球顺序加入列表 */
function addBallToList(list, num)
{
	var exist = false;
	for(var i = 0; i < list.length; i++) {
		if(list[i].num == num) {
			exist = true;
		}
	}
	if(!exist) {
		list[list.length] = num;
	}
}

function deleteBallFromList(list, num)
{
	for(var i = 0; i < list.length; i++) {
		if(list[i] == num) {
			list.splice(i, 1);
		}
	}
}

//投注区机选
function randomSelect(list, m, n)
{
	var randomArray = random(m, n);
	for(var i = 0; i < randomArray.length; i++){
		var j = randomArray[i];
		$(list[j]).click();
	}
}

//显示当前用户的投注数目
function displayBetNum()
{
	var bai = g_std_cur_bai_BallList.length;
	var shi = g_std_cur_shi_BallList.length;
	var ge = g_std_cur_ge_BallList.length;
	var betCount = bai * shi * ge;
	$("#baiCount").text(bai);
	$("#shiCount").text(shi);
	$("#geCount").text(ge);
	$("#betCount").text(betCount);
	$("#betCountMoney").text(betCount * 2);
}

function displayzu3BetNum()
{
	var count = z3_g_std_cur_BallList.length;
	var betCount =  math(count, 2) * 2;
	$("#zu3_betCount").text(betCount);
	$("#zu3_betCountMoney").text(betCount * 2);
}

function displayzu3hzBetNum()
{
	var z3hz = z3hz_g_std_cur_BallList;
	var betCount=0 ;
	for(var i = 0; i < z3hz.length; i++) {
		betCount+=zxshz(z3hz[i]) ;
	}
	$("#zu3hz_betCount").text(betCount);
	$("#zu3hz_betCountMoney").text(betCount * 2);
}

function displayzu6BetNum()
{
	var count = z6_g_std_cur_BallList.length;
	var betCount =  math(count, 3);
	$("#zu6_betCount").text(betCount);
	$("#zu6_betCountMoney").text(betCount * 2);
}

function displayzu6hzBetNum()
{
	var z6hz = z6hz_g_std_cur_BallList;
	var betCount=0 ;
	for(var i = 0; i < z6hz.length; i++) {
		betCount+=zxlhz(z6hz[i]) ;
	}
	$("#zu6hz_betCount").text(betCount);
	$("#zu6hz_betCountMoney").text(betCount * 2);
}

//分析用户选球，加入列表
function addPlan()
{	
	if(g_plan.termStatus != "销售中") {
		alert("对不起，当前已经销售截止！");
		return;
	}
	
	switch(g_playType) 
	{
	case PLAY_TYPE_STD:
		var betCount = $("#betCount").text();
		if(betCount < 1) {
			alert("请在上面的区域选择好号码");
			return;
		}
		var code = g_std_cur_bai_BallList.join("") + "," + 
				g_std_cur_shi_BallList.join("")  + "," + 
				g_std_cur_ge_BallList.join("");
		var type_code = "fs-" + code;
		var displayString = "标准投注   " + code;
		g_plan.addCode(type_code);
		addPlanToList(displayString, betCount, type_code);
		break;
	case PLAY_TYPE_Z3:
		if(g_subPlayType == SUB_PLAY_TYPE_BH) {
			var betCount = $("#zu3_betCount").text();
			if(betCount < 1) {
				alert("请在上面的区域选择好号码");
				return;
			}
			var code = z3_g_std_cur_BallList.join(",");
			var type_code = "zs_bh-" + code;
			var displayString = "组三包号   " + code;
			g_plan.addCode(type_code);
			addPlanToList(displayString, betCount, type_code);	
		}
		else if(g_subPlayType == SUB_PLAY_TYPE_HZ) {
			var z3betCount = $("#zu3hz_betCount").text();
			if(z3betCount < 1) {
				alert("请在上面的区域选择好号码");
				return;
			}
			
			var num = z3hz_g_std_cur_BallList.length;
			for(var i = 0; i < num; i++) {
				var type_code = "zs_hz-" + z3hz_g_std_cur_BallList[i];
				var displayString = "组三和值   " + z3hz_g_std_cur_BallList[i];
				var betCount = zxshz(z3hz_g_std_cur_BallList[i]);
				g_plan.addCode(type_code);
				addPlanToList(displayString, betCount, type_code);
			}
		}
		break;
	case PLAY_TYPE_Z6:
		if(g_subPlayType == SUB_PLAY_TYPE_BH) {
			var betCount = $("#zu6_betCount").text();
			if(betCount < 1) {
				alert("请在上面的区域选择好号码");
				return;
			}
			var code = z6_g_std_cur_BallList.join(",");
			var type_code = "zl_bh-" + code;
			var displayString = "组六包号   " + code;
			g_plan.addCode(type_code);
			addPlanToList(displayString, betCount, type_code);
		}
		else if(g_subPlayType == SUB_PLAY_TYPE_HZ) {
			var betCount = $("#zu6hz_betCount").text();
			if(betCount < 1) {
				alert("请在上面的区域选择好号码");
				return;
			}
			for(var code in z6hz_g_std_cur_BallList) {
				var code1 = z6hz_g_std_cur_BallList[code];
				var type_code = "zl_hz-" + code1;
				var displayString = "组六和值   " + code1;
				var betCount = zxlhz(code1);
				g_plan.addCode(type_code);
				addPlanToList(displayString, betCount, type_code);
			}
		}
		break;
	default:
		break;
	}
	selectclear(1);
	selectclear(2);
	selectclear(3);
	zu3_selectclear();
	zu3hz_selectclear();
	zu6_selectclear();
	zu6hz_selectclear();
}

//机选n注
function randomBet()
{
	if(g_plan.termStatus != "销售中") {
		alert("对不起，当前已经销售截止！");
		return;
	}

	var number = $("#randomselect").val();
	var stringArray = [];
	var valueArray = [];
	
	if(g_playType == PLAY_TYPE_STD) {
		for(var i = 0; i < number; ) {
			var bai = random(10, 1);
			var shi = random(10, 1);
			var ge = random(10, 1);
			bai[0]--;
			shi[0]--;
			ge[0]--;
			var value = bai.join("") + "," + shi.join("") + "," + ge.join("");
			var code = "fs-" + value;
			var displayString = "标准投注   " + value;
			
			if($.inArray(displayString, stringArray) == -1) {
				stringArray[stringArray.length] = displayString;
				g_plan.addCode(code);
				valueArray.push(value);
				i++;
			}
		}
		for(var k in stringArray) {
			addPlanToList(stringArray[k], 1, "fs-" + valueArray[k]);
		}
	}
	else if(g_playType == PLAY_TYPE_Z3 && g_subPlayType == SUB_PLAY_TYPE_BH) {
		for(var i = 0; i < number; ) {
			var bai = random(10, 2);
			var choice = random(2, 1);
			
			if(choice == 1) {
				var shi=bai[0];
			}
			else {
				var shi=bai[1];
			}
			bai[0]--;
			bai[1]--;
			shi--;
			
			if(bai[0] < 0 || shi < 0 || bai[1] < 0) {
				alert("错误！！！");
				return;
			}
			
			var value = bai.join(",") + "," + shi;
			var code = "zs_bh-" + value;
			
			var displayString = "组三包号   " + value;
			if($.inArray(displayString, stringArray) == -1) {
				stringArray[stringArray.length] = displayString;
				g_plan.addCode(code);
				valueArray.push(value);
				i++;
			}
		}
		
		for(var k in stringArray) {
			addPlanToList(stringArray[k], 1, "zs_bh-"+ valueArray[k]);
		}
	}
	else if(g_playType == PLAY_TYPE_Z3 && g_subPlayType == SUB_PLAY_TYPE_HZ) {
		if(number > 26) {
			alert("和值随机数不得大于26");
			return;
		}
		for(var i = 0; i < number; ) {
			var bai = random(26, 1);
			var betCount=zxshz(Number(bai[0]));
			var value = bai;
			var code = "zs_hz-" + value;
			var displayString = "组三和值   " + value;
			
			if($.inArray(displayString, stringArray) == -1) {
				stringArray[stringArray.length] = displayString;
				g_plan.addCode(code);
				valueArray.push(value);
				i++;
				addPlanToList(displayString,betCount,"zs_hz-"+value);
			}
		}
	}
	else if(g_playType == PLAY_TYPE_Z6 && g_subPlayType == SUB_PLAY_TYPE_BH) {
		for(var i = 0; i < number; ) {
			var bai = random(10, 3);
			while(bai[0] == bai[1] || bai[0] == bai[2] || bai[1] == bai[2]) {
				alert(bai[0]);
				bai = random(10, 3);
			}
			bai[0]--;
			bai[1]--;
			bai[2]--;
			var value = bai.join(",");
			var code = "zl_bh-" + value;
			var displayString = "组六包号   " + value;
			if($.inArray(displayString, stringArray) == -1) {
				stringArray[stringArray.length] = displayString;
				g_plan.addCode(code);
				valueArray.push(value);
				i++;
			}
		}
		
		for(var k in stringArray) {
			addPlanToList(stringArray[k], 1, "zl_bh-"+ valueArray[k]);
		}
	}
	else if(g_playType == PLAY_TYPE_Z6 && g_subPlayType == SUB_PLAY_TYPE_HZ) {
		if(number > 26) {
			alert("和值随机数不得大于26");
			return;
		}
		for(var i = 0; i < number; ) {
			var bai = random(24, 1);
			bai[0] = bai[0]+2;
			var betCount = zxlhz(Number(bai[0]));
			var value = bai;
			var code = "zl_hz-" + value;
			var displayString = "组六和值   " + value;
			if($.inArray(displayString, stringArray) == -1) {
				stringArray[stringArray.length] = displayString;
				g_plan.addCode(code);
				valueArray.push(value);
				i++;
				addPlanToList(displayString,betCount,"zl_hz-"+value);
			}
		}
	}
	else {	
		alert("该玩法不能机选");
		return;
	}
}

/******************** 用户确认投注 ***************************/
function onSubmit()
{
	var betTotalNum = $("#betTotalNum").text();
	if(g_playType != PLAY_TYPE_FILE_UPLOAD && betTotalNum <= 0) {
		alert("请选择投注号码");
		return;
	}
	
	if(g_betType == BET_TYPE_GROUP && $("#desc").val().length > 100) {
		alert("对不起，方案描述不能超过100个字符");
		return;
	}
	
	if(g_playType == PLAY_TYPE_FILE_UPLOAD) {
		if(!checkFile()) {
			return;
		}
	}
	
	if(g_betType == BET_TYPE_PERSONAL) {
		if(g_plan.termStatus != "销售中" && g_plan.termStatus != "合买截止") {
			alert("本期已经销售截止");
			return;
		}
		
		var oneMoney = 2;
		if(g_playType == PLAY_TYPE_FILE_UPLOAD) {
			if( 0 == Number($("#uploadTotalBetMoney").text())) {
				alert("请填写注数！");
				$("#uploadBetNum").focus();
				return;
			}
			g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#uploadTotalBetMoney").text()), 
					Number($("#uploadMultiple").val()), "0", oneMoney);
			submitPlanByFile(g_plan);
		}
		else {
			g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
					Number($("#multiple").val()), "0", oneMoney);
			submitPlan(g_plan);
		}
	}
	else if(g_betType == BET_TYPE_GROUP) {
		if(g_plan.termStatus != "销售中") {
			alert("本期合买已经销售截止");
			return;
		}
		
		if(!validateGroupBuy()) {
			return;
		}
		
		var oneMoney = 2; //一注金额
		var description = encodeURI($("#desc").val());
		var perMoney = "1"; //合买-每份金额
		var totalPart = $("#stock").text();
		var buyPart = $("#myself").val();
		var insurePart = $("#procted").val();
		var secretType = $(":radio[name='content'][checked]").val();
		var commision = $("#commission").val();
		
		if(g_playType == PLAY_TYPE_FILE_UPLOAD) {
			if( 0 == Number($("#uploadTotalBetMoney").text())) {
				alert("请填写注数！");
				$("#uploadBetNum").focus();
				return;
			}
			g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#uploadTotalBetMoney").text()), 
					Number($("#uploadMultiple").val()), "0", oneMoney, "0", "0", description, 
					perMoney, totalPart, buyPart, insurePart, commision, secretType);
			submitPlanByFile(g_plan);
		}
		else {
			g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
					Number($("#multiple").val()), "0", oneMoney, "0", "0", description, 
					perMoney, totalPart, buyPart, insurePart, commision, secretType);
			submitPlan(g_plan);
		}
	}
	else {
		if(g_playType == PLAY_TYPE_FILE_UPLOAD) {
			alert("单式上传不能追号！");
			return;
		}
		var winStopped = $("#iswinStopped").attr("checked") ? "true" : "false";
		if(g_plan.termStatus!="销售中" && g_plan.termStatus!="合买截止"){
			alert("本期已经销售截止");
			return;
		}
		
		g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
			Number($("#multiple").val()), winStopped, oneMoney, Number($("#chaseTotalMoney").text()), 
			Number($("#chaseStopMoney").text()));
		submitPlan(g_plan);
	}
}

//-------------------------------------------------------------
function zxhz(n)
{
	var zu=0;
	for(var i =0;i<10;i++)
	{
		for(var j =0;j<10;j++)
		{	
			for (var k=0;k<10 ;k++ )
			{
			    if (i+j+k==n)
			    {
					 if (i+j+k==n) zu++;
			    }
			}
		}
	}
	return zu;
}

function zxshz(n)
{
	var zu=0;
	for (var  i=0;i<9 ;i++ )
	{
		for (var  j=i;j<10 ;j++ )
		{
			
			  for (var k=j;k<10 ;k++ )
			  {
				    if ((i+j+k==n) && (i==j||i==k || j==k)&&!(i==j&&j==k))
				    	zu++;
			  }
				
		}
	}
	return zu;
}
function zxlhz(n)
{
	var zu=0;
	for (var  i=0;i<9 ;i++ )
	{
		for (var  j=i;j<10 ;j++ )
		{
			
			  for (var k=j;k<10 ;k++ )
			  {
				    if ((i+j+k==n) && (!(i==j&&i==k))&& !(i==j||i==k || j==k))
				    	zu++;
			  }
				
		}
	}
	return zu;
}

//显示开奖数据
function displayHistory(last, pre_list)
{
	//alert(last);
	//alert(pre_list);
	
	var history_last = "";
	var redballs = last.split("^")[1].split("|")[0].split(",");
	history_last += "<ul><li>"+redballs[0]+"</li><li>"+redballs[1]+
					"</li><li>"+redballs[2]+"</li>";
	$("#history_last").html(history_last);
	
	var history_pre = "<ul>";
	for(var i = 0; i < 4; i++) {
		var term = pre_list[i].split("^")[0];
		var redballs = pre_list[i].split("^")[1].split("|")[0];
		
		history_pre += "<li><span class=\"black\">"+ term +"</span>&nbsp;<span class=\"red\">"+redballs+
						"</span></li>";
	}
	history_pre += "</ul>";
	$("#history_pre_list").html(history_pre);
}