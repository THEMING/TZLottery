/*
 *常量区
 */
 
var BALL_ON  = "ball_down_true";

/*
*全局变量
*/
var g_lotteryType = "qlc";
var g_playType = PLAY_TYPE_FS;
var g_betType = BET_TYPE_PERSONAL;
var MAX_RED_BALL_NUMBER = 31;

//存放所有球的对象
var  g_std_all_ballList = [];
//存放当前选中球的索引
var g_std_cur_ballList = [];
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
	for(var i = 1; i < 31; i++) {
		g_std_all_ballList[i] = $("#std_ball_" + i)[0];
	}
}

/* 点击球事件 */
function onClickBall(ball, num)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		addBallToList(g_std_cur_ballList, num);
	}
	else {
		deleteBallFromList(g_std_cur_ballList, num);
	}
	validateBetNum(ball);
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

function clearSelectedBalls(area)
{
	var list = [];
	list = g_std_cur_ballList;
	for(var i = 0; i < list.length; i++) {
		var index = list[i];
		$(g_std_all_ballList[index]).removeClass(BALL_ON);
	}
	list.splice(0, list.length);
	displayBetNum();
}

function clearAllBalls()
{
	clearSelectedBalls(0);
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

function randomSelectBalls(area)
{
	var list;
	var m;

	var n = $("#randomselect_"+area).val();
	clearSelectedBalls(area);
	
	if(area == 0) {//red balls
		m = 30;
		list = g_std_all_ballList;
	}
	randomSelect(list, m, n);
}

//验证球的数目并显示
function validateBetNum(ball)
{
	var betCount = 0;
	switch (g_playType)
	{
	case PLAY_TYPE_FS:
		var redBallNum = g_std_cur_ballList.length;
		if(redBallNum > MAX_RED_BALL_NUMBER) {
			$(ball).click();
			alert("最多只能选择" + MAX_RED_BALL_NUMBER + "个红球!");
			return;
		}
		betCount = math(redBallNum, 7);
		if(betCount > 10000) {
			$(ball).click();
			alert("单倍投注不能超过10000注!");
			return;
		}
		displayBetNum();
		break;
	default:
		break;
	}
}

//显示当前用户的投注数目
function displayBetNum()
{
	var redBallNum = g_std_cur_ballList.length;
	var betCount = math(redBallNum, 7) ;
	$("#redBallNum").text(redBallNum);
	$("#betCount").text(betCount);
	$("#betCountMoney").text(betCount * 2);
}

//分析用户选球，加入列表
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
		case PLAY_TYPE_FS:
			var code = getCodeFromList(g_std_cur_ballList);
			var type_code = "fs-" + code;
			var displayString = "标准投注   " + code;
			g_plan.addCode(type_code);
			addPlanToList(displayString, betCount, type_code);	
			break;
		
		case PLAY_TYPE_FILE_UPLOAD:
			parseFile();
			break;
			
		default:
			break;
	}
}

//在小于10的数字前面加0
function getCodeFromList(redList)
{
	var code = "";
	for(var i = 0; i < redList.length; i++) {
		if(redList[i] < 10) {
			code += "0" + redList[i];
		}
		else {
			code += redList[i];
		}
		if(i < redList.length - 1) {
			code += ",";
		}
	}
	return code;
}

//机选n注
function randomBet()
{
	/*if(g_plan.termStatus != "销售中")
	{
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
		var redBallList = random(30, 7);
		var value = getCodeFromList(redBallList);
		var code = "fs-" + value;
		var displayString = "标准投注   " + value;
		if($.inArray(displayString, stringArray) == -1) {
			stringArray[stringArray.length] = displayString;
			g_plan.addCode(code);
			valueArray.push(value);
			i++;
		}
	}
	
	for(var k=0;k<stringArray.length;k++) {
		addPlanToList(stringArray[k],1, "fs-" + valueArray[k]);
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
		
		if($("#desc").val().length>100) {
			alert("对不起，方案描述不能超过100个字符");
			return;
		}
	
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
		var commision = $("#commision").val(); //佣金百分比
		
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

//显示开奖数据
function displayHistory(last, pre_list)
{
	//alert(last);
	//alert(pre_list);
	
	var history_last = "";
	var redballs = last.split("^")[1].split("|")[0].split(",");
	var blueball = last.split("^")[1].split("|")[1];
	history_last += "<ul><li>"+redballs[0]+"</li><li>"+redballs[1]+
					"</li><li>"+redballs[2]+"</li><li>"+redballs[3]+"</li><li>"+redballs[4]+"</li><li>"+redballs[5]+"</li><li>"+redballs[6]+"</li>";
	history_last += "<li style=\"color:#0099FF\">"
					+blueball+"</li></ul>";
	$("#history_last").html(history_last);
	
	var history_pre = "<ul>";
	for(var i = 0; i < 4; i++) {
		var term = pre_list[i].split("^")[0];
		var redballs = pre_list[i].split("^")[1].split("|")[0];
		var blueball = pre_list[i].split("^")[1].split("|")[1];
		history_pre += "<li><span class=\"black\">"+ term +"</span>:<span class=\"red\">"+redballs+
						"</span>|<span class=\"blue\">"+blueball+"</span></li>";
	}
	history_pre += "</ul>";
	$("#history_pre_list").html(history_pre);
}
