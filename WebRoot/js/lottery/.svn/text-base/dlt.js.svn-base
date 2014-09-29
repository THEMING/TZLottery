/*
 *常量区
 */
var RED_BALL_ON  = "ball_down_true";
var BLUE_BALL_ON = "blueball_current"; 
 
 
/*dlt最大球数 */
var MAX_RED_BALL_NUMBER = 20;
var MAX_BLUE_BALL_NUMBER = 10;

//玩法 -- 直选，生肖乐
var PLAY_TYPE_ZX = 1;
var PLAY_TYPE_SXL = 2;
var PLAY_TYPE_FILE_UPLOAD = 3;
/*
*全局变量
*/
var g_lotteryType = "dlt";
var g_playType = PLAY_TYPE_ZX;
var g_betType = BET_TYPE_PERSONAL;
var oneMoney = 2;

//存放所有球的对象
// 直选
var g_std_all_redBallList = [];
var g_std_all_blueBallList = [];

// 生肖乐
var sxl_g_std_all_BallList = [];

//存放当前选中球的索引
// 直选
var g_std_cur_redBallList = [];
var g_std_cur_blueBallList = [];

// 生肖乐
var sxl_g_std_cur_BallList = [];

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
	for(var i = 1; i < 36; i++) {
		g_std_all_redBallList[i] = $("#std_red_ball_" + i)[0];
	}
	for(var i = 1; i < 13; i++) {
		g_std_all_blueBallList[i] = $("#std_blue_ball_" + i)[0];
		sxl_g_std_all_BallList[i] = $("#sxl_std_red_ball_" + i)[0];
	}
}

/* 点击球事件 */
function onClickRedBall(ball, num)
{	
	$(ball).toggleClass(RED_BALL_ON);
	if($(ball).hasClass(RED_BALL_ON)) {
		addBallToList(g_std_cur_redBallList, num);
	}
	else {
		deleteBallFromList(g_std_cur_redBallList, num);
	}
	validateBetNum(ball);
}

function onClickSxlRedBall(ball, num)
{
	$(ball).toggleClass(RED_BALL_ON);
	if($(ball).hasClass(RED_BALL_ON)) {
		addBallToList(sxl_g_std_cur_BallList, num);
	}
	else {
		deleteBallFromList(sxl_g_std_cur_BallList, num);
	}
	validateBetNum(ball);
}

function onClickBlueBall(ball, num) 
{
	$(ball).toggleClass(BLUE_BALL_ON);
	if($(ball).hasClass(BLUE_BALL_ON)) {
		addBallToList(g_std_cur_blueBallList, num);
	}
	else {
		deleteBallFromList(g_std_cur_blueBallList, num);
	}
	validateBetNum(ball);
}

/* 将所选的球顺序加入列表 */
function addBallToList(list, num)
{
	var exist = false;
	for(var i = 1; i < list.length; i++) {// 改1或0
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

function sxl_selectall()
{  
	sxl_selectclear(); 
	for(var i = 1; i < sxl_g_std_all_BallList.length; i++)
	{   
		$(sxl_g_std_all_BallList[i]).toggleClass(RED_BALL_ON);
		addBallToList(sxl_g_std_cur_BallList, i);	
	}
	displaysxlBetNum();
}

function sxl_selectodd()
{
	sxl_selectclear();
	for(var i = 1; i < sxl_g_std_all_BallList.length; i+=2)
	{   
		$(sxl_g_std_all_BallList[i]).toggleClass(RED_BALL_ON);
		addBallToList(sxl_g_std_cur_BallList, i);	
	}
	displaysxlBetNum();
}
function sxl_selecteven()
{
	sxl_selectclear();
	for(var i = 2; i < sxl_g_std_all_BallList.length; i+=2)
	{   
		$(sxl_g_std_all_BallList[i]).toggleClass(RED_BALL_ON);
		addBallToList(sxl_g_std_cur_BallList, i);	
	}
	displaysxlBetNum();
}

function sxl_selectclear()
{
	for(i=0; i<sxl_g_std_cur_BallList.length; i++)
	{
		var index = sxl_g_std_cur_BallList[i];
		$(sxl_g_std_all_BallList[index]).removeClass(RED_BALL_ON);
	}
	sxl_g_std_cur_BallList.splice(0, sxl_g_std_cur_BallList.length);
	displaysxlBetNum();
}

function clearSelectedBalls(area)
{
	
	var list = [];
	if(area == 0) { //red balls
		list = g_std_cur_redBallList;
		for(var i = 0; i < list.length; i++) {
			var index = list[i];
			$(g_std_all_redBallList[index]).removeClass(RED_BALL_ON);
		}
	}
	else if(area == 1) { //blue balls
		list = g_std_cur_blueBallList;
		for(var i = 0; i < list.length; i++) {
			var index = list[i];
			$(g_std_all_blueBallList[index]).removeClass(BLUE_BALL_ON);
		}
	}
	
	list.splice(0, list.length);
	displayBetNum();
}

function clearAllBalls()
{
	clearSelectedBalls(0);
	clearSelectedBalls(1);
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
		m = 35;
		list = g_std_all_redBallList;
	}
	else if(area == 1) {//blue balls
		m = 12;
		list = g_std_all_blueBallList;
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
		var redBallNum = g_std_cur_redBallList.length;
		var blueBallNum = g_std_cur_blueBallList.length;
		if(redBallNum > MAX_RED_BALL_NUMBER) {
			$(ball).click();
			alert("最多只能选择" + MAX_RED_BALL_NUMBER + "个红球!");
			return;
		}
		if(blueBallNum > MAX_BLUE_BALL_NUMBER) {
			$(ball).click();
			alert("最多只能选择" + MAX_BLUE_BALL_NUMBER + "个蓝球!");
			return;
		}
		betCount = math(redBallNum, 6) * math(blueBallNum, 1);
		if(betCount > 10000) {
			$(ball).click();
			alert("单倍投注不能超过10000注!");
			return;
		}
		displayBetNum();
		break;
		
	case PLAY_TYPE_SXL:
		displaysxlBetNum();
		break;
		
	default:
		break;
	}
}

//显示当前用户的投注数目
function displayBetNum()
{
	var redBallNum = g_std_cur_redBallList.length;
	var blueBallNum = g_std_cur_blueBallList.length;
	var betCount = math(redBallNum, 5) * math(blueBallNum, 2);
	$("#redBallNum").text(redBallNum);
	$("#blueBallNum").text(blueBallNum);
	$("#betCount").text(betCount);
	$("#betCountMoney").text(betCount * oneMoney);
}

function displaysxlBetNum()
{
	var count = sxl_g_std_cur_BallList.length;
	var betCount =  math(count, 2);
	
	$("#sxl_Count").text(count);
	$("#sxl_betCount").text(betCount);
	$("#sxl_betCountMoney").text(betCount * 2);
}


//分析用户选球，加入列表
function addPlan()
{
	if(g_plan.termStatus != "销售中") {
		alert("对不起，当前已经销售截止！");
		return;
	}
	
	switch(g_playType) {
		case PLAY_TYPE_ZX:
			var betCount = $("#betCount").text();
			if(betCount < 1) {
				alert("请选择投注");
				return;
			}
			var code = getCodeFromList(g_std_cur_redBallList) + "|" + 
						getCodeFromList(g_std_cur_blueBallList);
			var type_code = "fs-" + code;
						
			var displayString = "标准投注   " + code;
			g_plan.addCode(type_code);
			addPlanToList(displayString, betCount, type_code, oneMoney);	
			clearSelectedBalls(0);
			clearSelectedBalls(1);
			break;
			
		case PLAY_TYPE_SXL:   // 生肖乐
			var sxl_betCount = $("#sxl_betCount").text();
			if(sxl_betCount < 1) {
				alert("请选择投注");
				return;
			}
			
			//var code = sxl_g_std_cur_BallList.join(",");
			var code = getCodeFromList(sxl_g_std_cur_BallList);
			var type_code = "dlt_sxlfs-" + code;
			var displayString = "生肖乐   " + code;
			
			g_plan.addCode(type_code);
			addPlanToList(displayString, sxl_betCount, type_code);	
			sxl_selectclear();
			break;

		default:
			break;
	}
}

// 追加投注被单击
function recomputeMoney()
{
	oneMoney = $("#do_dlt_addition").attr("checked") ? 3 : 2;
	computeAllMoneyAndDisplay(oneMoney);
	displayBetNum();
}

//机选n注
function randomBet()
{
	if(g_plan.termStatus != "销售中")
	{
		alert("对不起，当前已经销售截止！");
		return;
	}
	
	var number = $("#randomselect").val();
	
	var stringArray = [];
	var valueArray = [];
	if(g_playType == PLAY_TYPE_ZX) {
		for(var i = 0; i < number; ) {
			var redBallList = random(35, 5);
			var blueBallList = random(12, 2);
			//var value = redBallList.join(",") + "|" + blueBallList.join(",");
			var value = getCodeFromList(redBallList) + "|" + getCodeFromList(blueBallList);
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
			addPlanToList(stringArray[k], 1, "fs-" + valueArray[k], oneMoney);
		}
	}
	else if(g_playType == PLAY_TYPE_SXL) {
		for(var i = 0; i < number; ) {
			var bai = random(12, 2);	
			if(bai[0] > bai[1]) {
				var temp = bai[0];
				bai[0] = bai[1];
				bai[1] = temp;
			}
			var value = getCodeFromList(bai);
			var code = "dlt_sxlfs-" + value;
			var displayString = "生肖乐单式   " + value;
			
			if($.inArray(displayString, stringArray) == -1) {
				stringArray[stringArray.length] = displayString;
				g_plan.addCode(code);
				valueArray.push(value);
				i++;
			}
		}
		for(var k in stringArray) {
			addPlanToList(stringArray[k], 1, "dlt_sxlfs-" + valueArray[k], oneMoney);
		}
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
		//var oneMoney = 2;
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
		
		//var oneMoney = 2; //一注金额
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
				Number($("#multiple").val()), "0", oneMoney, "0", "0", 
				description, perMoney, totalPart, buyPart, insurePart, commision, secretType);
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
			
			if(to_hide == PLAY_TYPE_ZX - 1 && to_show != PLAY_TYPE_ZX - 1) //从直选切换到生肖乐
			{
				if(!confirm("切换到生肖乐将清空已选投注区，是否切换？"))
					return;
				else
				{
					oneMoney = 2;
					cleanAllPlan();
					$("#dlt_addition_box").attr("class", "hidden");					
				}
			}
			else if(to_hide == PLAY_TYPE_SXL - 1 && to_show != PLAY_TYPE_SXL - 1) // 从生肖乐切换到直选
			{
				if(!confirm("从生肖乐切换到标准投注将清空已选投注区，是否切换？"))
					return;
				else
				{
					oneMoney = 2;
					$("#do_dlt_addition").attr("checked",false);
					cleanAllPlan();
					$("#dlt_addition_box").attr("class", "show");
				}
			}
			
			settings.lottery_tabs.eq(to_hide).removeClass('on');
			settings.lottery_contents.eq(to_hide).hide();
			settings.lottery_tabs.eq(to_show).addClass('on');
			settings.lottery_contents.eq(to_show).show();
			g_playType = i+1;
			
			alert(g_playType);
		});
	});
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

//显示开奖数据
function displayHistory(last, pre_list)
{
	//alert(last);
	//alert(pre_list);
	
	var history_last = "";
	var redballs = last.split("^")[1].split("|")[0].split(",");
	var blueball = last.split("^")[1].split("|")[1].split(",");
	history_last += "<ul><li>"+redballs[0]+"</li><li>"+redballs[1]+
					"</li><li>"+redballs[2]+"</li><li>"+redballs[3]+
					"</li><li>"+redballs[4]+"</li>";
	history_last += "<li style=\"background:url(../images/369caibg/ball_bg.gif) -97px center no-repeat;\">"
					+blueball[0]+"</li><li style=\"background:url(../images/369caibg/ball_bg.gif) -97px center no-repeat;\">"
					+blueball[1]+"</li></ul>";
	$("#history_last").html(history_last);
	
	var history_pre = "<ul>";
	for(var i = 0; i < 4; i++) {
		var term = pre_list[i].split("^")[0];
		var redballs = pre_list[i].split("^")[1].split("|")[0];
		var blueball = pre_list[i].split("^")[1].split("|")[1];
		
		history_pre += "<li><span class=\"black\">"+ term +"</span>&nbsp;<span class=\"red\">"+redballs+
						"</span>&nbsp;|&nbsp;<span class=\"blue\">"+blueball+"</span></li>";
	}
	history_pre += "</ul>";
	$("#history_pre_list").html(history_pre);
}