/*
 *常量区
 */
 
var BALL_ON  = "ball_down_true";

/*
*全局变量
*/
var g_lotteryType = "qxc";
var g_playType = PLAY_TYPE_FS;
var g_betType = BET_TYPE_PERSONAL;

// 存放所有球的对象
var g_all_baiwan_BallList = [];
var g_all_shiwan_BallList = [];
var g_all_wan_BallList = [];
var g_all_qian_BallList = [];
var g_all_bai_BallList = [];
var g_all_shi_BallList = [];
var g_all_ge_BallList = [];

var g_cur_baiwan_BallList = [];
var g_cur_shiwan_BallList = [];
var g_cur_wan_BallList = [];
var g_cur_qian_BallList = [];
var g_cur_bai_BallList = [];
var g_cur_shi_BallList = [];
var g_cur_ge_BallList = [];

var g_plan;

/* 初始化函数 */

function init()
{
	initBallList();
	onChangeBetType(0);
	g_plan.action = "buyLottery.htm";
	getTermInfo();
	getData();
}

function initBallList()
{
	for(var i = 0; i < 10; i++) {
		g_all_baiwan_BallList[i] = $("#baiwan_ball_" + i)[0];
		g_all_shiwan_BallList[i] = $("#shiwan_ball_" + i)[0];
		g_all_wan_BallList[i] = $("#wan_ball_" + i)[0];
		g_all_qian_BallList[i] = $("#qian_ball_" + i)[0];
		g_all_bai_BallList[i] = $("#bai_ball_" + i)[0];
		g_all_shi_BallList[i] = $("#shi_ball_" + i)[0];
		g_all_ge_BallList[i] = $("#ge_ball_" + i)[0];
	}
}

/* 点击球事件 */
function onClickBall(ball, num, listNum)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		if(listNum == 11) 
			addBallToList(g_cur_baiwan_BallList, num);
		else if(listNum == 12) 
			addBallToList(g_cur_shiwan_BallList, num);
		else if(listNum == 13) 
			addBallToList(g_cur_wan_BallList, num);
		else if(listNum == 14) 
			addBallToList(g_cur_qian_BallList, num);
		else if(listNum == 15) 
			addBallToList(g_cur_bai_BallList, num);
		else if(listNum == 16) 
			addBallToList(g_cur_shi_BallList, num);
		else if(listNum == 17) 
			addBallToList(g_cur_ge_BallList, num);
	}
	else {			
		if(listNum == 11) 
			deleteBallFromList(g_cur_baiwan_BallList, num);
		else if(listNum == 12) 
			deleteBallFromList(g_cur_shiwan_BallList, num);
		else if(listNum == 13)  
			deleteBallFromList(g_cur_wan_BallList, num);
		else if(listNum == 14)  
			deleteBallFromList(g_cur_qian_BallList, num);
		else if(listNum == 15) 
			deleteBallFromList(g_cur_bai_BallList, num);
		else if(listNum == 16) 
			deleteBallFromList(g_cur_shi_BallList, num);
		else if(listNum == 17) 
			deleteBallFromList(g_cur_ge_BallList, num);
	}
	displayBetNum(listNum);
}

function selectall(listNum)
{
	var list = [];
	var list2 = [];

	if(listNum == 11) {
		list = g_cur_baiwan_BallList;
		list2 = g_all_baiwan_BallList;
	}
	else if(listNum == 12) {
		list = g_cur_shiwan_BallList;
		list2 = g_all_shiwan_BallList;
	}
	else if(listNum == 13) {
		list = g_cur_wan_BallList;
		list2 = g_all_wan_BallList;
	}
	else if(listNum == 14) {
		list = g_cur_qian_BallList;
		list2 = g_all_qian_BallList;
	}
	else if(listNum == 15) {
		list = g_cur_bai_BallList;
		list2 = g_all_bai_BallList;
	}
	else if(listNum == 16) {
		list = g_cur_shi_BallList;
		list2 = g_all_shi_BallList;
	}
	else if(listNum == 17) {
		list = g_cur_ge_BallList;
		list2 = g_all_ge_BallList;
	}
	
	for(var i = 0; i < list2.length; i++) {
		if(!$(list2[i]).hasClass(BALL_ON)) {
			$(list2[i]).addClass(BALL_ON);
			addBallToList(list, i);
		}
	}
	displayBetNum(listNum);
}

function selectodd(listNum)
{
	var list = [];
	var list2 = [];
	if(listNum == 11) {
		list = g_cur_baiwan_BallList;
		list2 = g_all_baiwan_BallList;
	}
	else if(listNum == 12) {
		list = g_cur_shiwan_BallList;
		list2 = g_all_shiwan_BallList;
	}
	else if(listNum == 13) {
		list = g_cur_wan_BallList;
		list2 = g_all_wan_BallList;
	}
	else if(listNum == 14) {
		list = g_cur_qian_BallList;
		list2 = g_all_qian_BallList;
	}
	else if(listNum == 15) {
		list = g_cur_bai_BallList;
		list2 = g_all_bai_BallList;
	}
	else if(listNum == 16) {
		list = g_cur_shi_BallList;
		list2 = g_all_shi_BallList;
	}
	else if(listNum == 17) {
		list = g_cur_ge_BallList;
		list2 = g_all_ge_BallList;
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
	displayBetNum(listNum);
}

function selecteven(listNum)
{
	var list = [];
	var list2 = [];
	if(listNum == 11) {
		list = g_cur_baiwan_BallList;
		list2 = g_all_baiwan_BallList;
	}
	else if(listNum == 12) {
		list = g_cur_shiwan_BallList;
		list2 = g_all_shiwan_BallList;
	}
	else if(listNum == 13) {
		list = g_cur_wan_BallList;
		list2 = g_all_wan_BallList;
	}
	else if(listNum == 14) {
		list = g_cur_qian_BallList;
		list2 = g_all_qian_BallList;
	}
	else if(listNum == 15) {
		list = g_cur_bai_BallList;
		list2 = g_all_bai_BallList;
	}
	else if(listNum == 16) {
		list = g_cur_shi_BallList;
		list2 = g_all_shi_BallList;
	}
	else if(listNum == 17) {
		list = g_cur_ge_BallList;
		list2 = g_all_ge_BallList;
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
	displayBetNum(listNum);
}

function selectclear(listNum)
{
	var list = [];
	var list2 = [];
	if(listNum == 11) {
		list = g_cur_baiwan_BallList;
		list2 = g_all_baiwan_BallList;
	}
	else if(listNum == 12) {
		list = g_cur_shiwan_BallList;
		list2 = g_all_shiwan_BallList;
	}
	else if(listNum == 13) {
		list = g_cur_wan_BallList;
		list2 = g_all_wan_BallList;
	}
	else if(listNum == 14) {
		list = g_cur_qian_BallList;
		list2 = g_all_qian_BallList;
	}
	else if(listNum == 15) {
		list = g_cur_bai_BallList;
		list2 = g_all_bai_BallList;
	}
	else if(listNum == 16) {
		list = g_cur_shi_BallList;
		list2 = g_all_shi_BallList;
	}
	else if(listNum == 17) {
		list = g_cur_ge_BallList;
		list2 = g_all_ge_BallList;
	}
	
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(list2[index]).removeClass(BALL_ON);
	}
	list.splice(0, list.length);
	displayBetNum(listNum);
}

function clearAllBalls()
{
}

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

//Ͷע���ѡ
function randomSelect(list, m, n)
{
	var randomArray = random(m, n);
	for(var i = 0; i < randomArray.length; i++){
		var j = randomArray[i];
		$(list[j]).click();
	}
}

//��֤�����Ŀ����ʾ
function validateBetNum(ball)
{
	displayBetNum();
}

//��ʾ��ǰ�û���Ͷע��Ŀ
function displayBetNum(listNum)
{
	if(listNum == 11 || listNum == 12 || listNum == 13||listNum == 14||
		listNum == 15||listNum == 16||listNum == 17)  // ֱѡ
	{
		var baiwan = g_cur_baiwan_BallList.length;
		var shiwan = g_cur_shiwan_BallList.length;
		var wan    = g_cur_wan_BallList.length;
		var qian   = g_cur_qian_BallList.length;
		var bai    = g_cur_bai_BallList.length;
		var shi    = g_cur_shi_BallList.length;
		var ge     = g_cur_ge_BallList.length;
		var betCount = baiwan * shiwan * wan * qian * bai * shi * ge;
		
		$("#baiwanCount").text(baiwan);
		$("#shiwanCount").text(shiwan);
		$("#wanCount").text(wan);
		$("#qianCount").text(qian);
		$("#baiCount").text(bai);
		$("#shiCount").text(shi);
		$("#geCount").text(ge);
		$("#betCount").text(betCount);
		$("#betCountMoney").text(betCount * 2);
	}
}

//�����û�ѡ�򣬼����б�
function addPlan()
{	
	/*if(g_plan.termStatus != "销售中") {
		alert("对不起，当前已经销售截止！");
		return;
	}*/
	
	var betCount = $("#betCount").text();
	if(betCount < 1) {
		if(g_playType != PLAY_TYPE_FILE_UPLOAD) {
			alert("请选择投注");
			return;
		}
	}
	
	switch(g_playType) {
		case PLAY_TYPE_FS:  //ֱѡ
			var code =g_cur_baiwan_BallList.join("") + "," +g_cur_shiwan_BallList.join("") + "," +g_cur_wan_BallList.join("") + "," +g_cur_qian_BallList.join("") + "," +g_cur_bai_BallList.join("") + "," + g_cur_shi_BallList.join("")  + "," + g_cur_ge_BallList.join("");
			var type_code = "fs-" + code;
			var displayString = "标准投注   " + code;
			g_plan.addCode(type_code);
			addPlanToList(displayString, betCount, type_code);
			selectclear(11);
			selectclear(12);
			selectclear(13);
			selectclear(14);
			selectclear(15);
			selectclear(16);
			selectclear(17);
			break;
		
		case PLAY_TYPE_FILE_UPLOAD:
			parseFile();
			break;	
		
		case PLAY_TYPE_DT:
			/* */
			break;
		default:
			break;
	}	
}

//��ѡ?ע
function randomBet()
{
	/*if(g_plan.termStatus != "销售中") {
		alert("对不起，当前已经销售截止！");
		return;
	}*/
	
	if(g_playType != PLAY_TYPE_FS) {
		alert("标准投注才能机选");
		return;
	}
	
	var number = $("#randomselect").val();
	
	var stringArray = [];
	var valueArray = [];
	
	for(var i = 0; i < number; ) {
		var baiwan =random(10, 1);
		var shiwan =random(10, 1);
		var wan =random(10, 1);
		var qian=random(10, 1);
		var bai = random(10, 1);
		var shi = random(10, 1);
		var ge = random(10, 1);
		baiwan[0]--;
		shiwan[0]--;
		wan[0]--;
		qian[0]--;
		bai[0]--;
		shi[0]--;
		ge[0]--;
		var value = baiwan.join("") + "," +shiwan.join("") + "," +wan.join("") + "," +qian.join("") + "," +bai.join("") + "," + shi.join("") + "," + ge.join("");
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

/******************** 用户确认投注 ***************************/
function onSubmit()
{
	var betTotalNum = $("#betTotalNum").text();
	if(betTotalNum <= 0) {
		alert("请选择投注号码");
		return;
	}
	if(g_betType == BET_TYPE_GROUP && $("#desc").val().length>100)
	{
		alert("对不起，方案描述不能超过100个字符");
		return;
	}
	if(g_betType == BET_TYPE_PERSONAL) {
		if(g_plan.termStatus != "销售中" && g_plan.termStatus != "合买截止") {
			alert("本期已经销售截止");
			return;
		}
		var oneMoney = 2;
		g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
				Number($("#multiple").val()), "0", oneMoney);
		submitPlan(g_plan);
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
		
		g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
			Number($("#multiple").val()), "0", oneMoney, "0", "0", 
			description, perMoney, totalPart, buyPart, insurePart, commision, secretType);
		submitPlan(g_plan);
	}
	else {
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

/////////////////////////////////////////////////////////////////////////
function lottery_tab(params)
{
	var _settings = {
		lottery_box: undefined,
		lottery_tabs: [],
		lottery_contents: []
	}

	var settings = $.extend(_settings, params);
	if (!settings.lottery_box) {
		return ;
	}

	settings.lottery_tabs.each(function(i) {
		var to_show = i;
		var to_hide;

		settings.lottery_tabs.eq(i).click(function() {
			settings.lottery_tabs.each(function(key, val) {
				if(val.className == "on"){
					to_hide = key;
				}
			});
			settings.lottery_tabs.eq(to_hide).removeClass('on');
			settings.lottery_contents.eq(to_hide).hide();
			settings.lottery_tabs.eq(to_show).addClass('on');
			settings.lottery_contents.eq(to_show).show();
			g_playType = i+1;
		});
	});
}

//显示开奖数据
function displayHistory(last, pre_list)
{
	//alert(last);
	//alert(pre_list);
	
	var history_last = "";
	var redballs = last.split("^")[1].split("|")[0].split(",");
	history_last += "<ul><li>"+redballs[0]+"</li><li>"+redballs[1]+
					"</li><li>"+redballs[2]+"</li><li>"+redballs[3]+"</li><li>"+redballs[4]+"</li><li>"+redballs[5]+"</li><li>"+redballs[6]+"</li>";
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