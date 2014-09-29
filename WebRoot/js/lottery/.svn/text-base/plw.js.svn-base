// JavaScript Document
/*
 *常量区
 */
 
// var RED_BALL_FALSE = "red_ball_false";
// var RED_BALL_TRUE = "red_ball_true";
var BALL_ON  = "ball_down_true";

//玩法 -- 直选，组三，组六
var PLAY_TYPE_ZX = 1;
var PLAY_TYPE_FILE_UPLOAD = 2;

//var PLAY_TYPE_FILE_UPLOAD = 2;
//var PLAY_TYPE_DT = 3;

//投注类型 -- 个人代购 ，合买，追号
var BET_TYPE_PERSONAL = 1;
var BET_TYPE_GROUP = 2;
var BET_TYPE_CHASE = 3;

/*
*全局变量
*/
var g_lotteryType = "plw";
var g_playType = PLAY_TYPE_ZX;
var g_betType = BET_TYPE_PERSONAL;
var g_betCount = 0;

// 存放所有球的对象
// 直选
var g_std_all_wan_BallList = [];
var g_std_all_qian_BallList = [];
var g_std_all_bai_BallList = [];
var g_std_all_shi_BallList = [];
var g_std_all_ge_BallList = [];



//存放当前选中球的索引
// 直选
var g_std_cur_wan_BallList = [];
var g_std_cur_qian_BallList = [];
var g_std_cur_bai_BallList = [];
var g_std_cur_shi_BallList = [];
var g_std_cur_ge_BallList = [];


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
	for(var i = 0; i < 10; i++) 
	{
		// 直选
		g_std_all_wan_BallList[i] = $("#wan_std_red_ball_" + i)[0];
		g_std_all_qian_BallList[i] = $("#qian_std_red_ball_" + i)[0];
		g_std_all_bai_BallList[i] = $("#bai_std_red_ball_" + i)[0];
		g_std_all_shi_BallList[i] = $("#shi_std_red_ball_" + i)[0];
		g_std_all_ge_BallList[i] = $("#ge_std_red_ball_" + i)[0];
		
	}
}

function getTermInfo()
{
	$.LotteryTerm.callback = function(json) {
		g_plan.term = json.term;
		var deadLine = json.stopTime;
		var termStopTime = json.termStopTime;
		g_plan.termStatus = json.termStatus;
		
		if(json.termStatus == "销售中" || json.termStatus == "合买截止") {
			 $("#currentTermStatus").text("正在销售:");
		}
		else {
			 $("#currentTermStatus").text("停止销售:");
		}
		
		$("#currentTerm").text(g_plan.term);
		$("#currentTermStopTime").text(termStopTime);
		$("#countTime").countdown({until:json.deadLine, compact: true, format: 'DHMS',  onExpiry: liftOff});
	};
	
	$.LotteryTerm._request({type: g_lotteryType});
}

function liftOff()
{
	 $("#currentTermStatus").text("停止销售:");
	 g_plan.termStatus = "停止销售";
	 setTimeout("getTermInfo()", 60000);
	 alert("对不起，当前期已经停止销售");
}

/* 点击球事件 */
function onClickRedBall(ball, num, listNum)
{
	$(ball).toggleClass(BALL_ON);
	if($(ball).hasClass(BALL_ON)) {
		if(listNum == 1)
			addBallToList(g_std_cur_wan_BallList, num);
		else if(listNum == 2)
			addBallToList(g_std_cur_qian_BallList, num);
		else if(listNum==3)
			addBallToList(g_std_cur_bai_BallList, num);
		else if(listNum==4)
			addBallToList(g_std_cur_shi_BallList, num);
		else
			addBallToList(g_std_cur_ge_BallList, num);
	}
	else {
		if(listNum == 1)
			deleteBallFromList(g_std_cur_wan_BallList, num);
		else if(listNum==2)
			deleteBallFromList(g_std_cur_qian_BallList, num);
		else if(listNum == 3)
			deleteBallFromList(g_std_cur_bai_BallList, num);
		else if(listNum == 4)
			deleteBallFromList(g_std_cur_shi_BallList, num);
		else
			deleteBallFromList(g_std_cur_ge_BallList, num);
	}
	//validateBetNum(ball);
	displayBetNum();
}



function selectall(num)
{
	var list = [];
	var list2 = [];
	if(num == 1) 
	{
		list = g_std_cur_wan_BallList;
		list2 =  g_std_all_wan_BallList;
	}
	else if (num == 2)
	{
		list = g_std_cur_qian_BallList;
		list2 = g_std_all_qian_BallList;
	}
		else if (num == 3)
	{
		list = g_std_cur_bai_BallList;
		list2 = g_std_all_bai_BallList;
	}
		else if (num == 4)
	{
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else
	{
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


function selectodd(num)
{
	var list = [];
	var list2 = [];
	if(num == 1) 
	{
		list = g_std_cur_wan_BallList;
		list2 =  g_std_all_wan_BallList;
	}
	else if (num == 2)
	{
		list = g_std_cur_qian_BallList;
		list2 = g_std_all_qian_BallList;
	}
	else if(num == 3) 
	{
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 4)
	{
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else
	{
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

function selecteven(num)
{
	selectclear(num);
	var list = [];
	var list2 = [];
	if(num == 1) 
	{
		list = g_std_cur_wan_BallList;
		list2 =  g_std_all_wan_BallList;
	}
	else if(num == 2) 
	{
		list = g_std_cur_qian_BallList;
		list2 =  g_std_all_qian_BallList;
	}
	else if(num == 3) 
	{
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 4)
	{
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else
	{
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

function selectclear(num)
{
	var list = [];
	var list2 = [];
	if (num == 1)
	{
		list = g_std_cur_wan_BallList;
		list2 = g_std_all_wan_BallList;
	}
	else if (num == 2)
	{
		list = g_std_cur_qian_BallList;
		list2 = g_std_all_qian_BallList;
	}
	else if(num == 3) 
	{
		list = g_std_cur_bai_BallList;
		list2 =  g_std_all_bai_BallList;
	}
	else if (num == 4)
	{
		list = g_std_cur_shi_BallList;
		list2 = g_std_all_shi_BallList;
	}
	else
	{
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

//验证球的数目并显示
function validateBetNum(ball)
{
	displayBetNum();
}

//显示当前用户的投注数目
function displayBetNum()
{
	var wan=g_std_cur_wan_BallList.length;
	var qian=g_std_cur_qian_BallList.length;
	var bai = g_std_cur_bai_BallList.length;
	var shi = g_std_cur_shi_BallList.length;
	var ge = g_std_cur_ge_BallList.length;
	var betCount =wan*qian* bai * shi * ge;
	$("#wanCount").text(wan);
	$("#qianCount").text(qian);
	$("#baiCount").text(bai);
	$("#shiCount").text(shi);
	$("#geCount").text(ge);
	$("#betCount").text(betCount);
	$("#betCountMoney").text(betCount * 2);
}

//分析用户选球，加入列表
function addPlan()
{	
	if(g_plan.termStatus != "销售中")
	{
		alert("对不起，当前已经销售截止！");
		return;
	}
	
	var betCount = $("#betCount").text();
	if(betCount < 1) {
		if(g_playType != PLAY_TYPE_FILE_UPLOAD) {
			alert("请选择投注");
			return;
		}
	}
	
	switch(g_playType) {
		case PLAY_TYPE_ZX:  //直选
			var code = g_std_cur_wan_BallList.join("") + "," +g_std_cur_qian_BallList.join("") + "," +g_std_cur_bai_BallList.join("") + "," + g_std_cur_shi_BallList.join("")  + "," + g_std_cur_ge_BallList.join("");
			var type_code = "fs-" + code;
			var displayString = "标准投注   " + code;
			g_plan.addCode(type_code);
			addPlanToList(displayString, betCount, type_code);
			selectclear(1);
			selectclear(2);
			selectclear(3);
			selectclear(4);
			selectclear(5);
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


//加入列表
function addPlanToList(betString, betNum, value)
{	
	var oOption = document.createElement("OPTION");
	oOption.text=betString+" 投注数："+betNum;
	oOption.value=betNum;
	oOption.removeValue=value;
	document.getElementById("planLists").options.add(oOption);
	computeAllMoneyAndDisplay();	
}

//计算所有金额并显示
function computeAllMoneyAndDisplay()
{
var multiple = Number($("#multiple").val());
	if(!multiple) {
		multiple = 1;
		$("#multiple").val(multiple);
	}
	
	var betTotalNum = 0;

	for(var i = 0; i < document.getElementById("planLists").options.length; i++) {
		betTotalNum += Number(document.getElementById("planLists").options[i].value);
	}
		
	var totalBetMoney = betTotalNum * 2 * multiple;
	$("#betTotalNum").text(betTotalNum);
	$("#totalBetMoney").text(totalBetMoney);

	if(g_betType == BET_TYPE_CHASE) {
		calAndDisplayChase();
	}	
	else if(g_betType == BET_TYPE_GROUP) {
		calAndDisplayGroup();
	}
	/*if(betType == "zh") {
		zhiHaoJiSuan();
	}	
	else if(betType = "hm") {
		hm_jisuan();
	}*/
}


//倍数改变函数
function onMultipleChange()
{
	if(!Number($("#multiple").val()) ||
		Number($("#multiple").val()) < 1 ||
		Number($("#multiple").val()) > 99){
		alert("投注倍数不能超过99倍 ,小于1倍！");  
		$("#multiple").val(1);
	}
	computeAllMoneyAndDisplay();     
}

//清楚所有投注框的投注
function cleanAllPlan()
{
	while(document.getElementById("planLists").options.length> 0) 
	{ 
		document.getElementById("planLists").options.remove(0); 
	}
	$("#multipleInput").val("1");
	g_plan.cleanCode();
	computeAllMoneyAndDisplay();
}

//删除单注
function removeli(liobj,value)
{
	g_plan.removecode(value);
	$(liobj).parent().remove();
	computeAllMoneyAndDisplay();
}

function removePlan()
{
	var index = document.getElementById("planLists").selectedIndex;

	while(index >= 0) {
		g_plan.removecode(document.getElementById("planLists").options[index].removeValue);
		document.getElementById("planLists").remove(index);
		index = document.getElementById("planLists").selectedIndex;
	}
	computeAllMoneyAndDisplay();
}

//机选?注
function randomBet()
{
	if(g_plan.termStatus != "销售中")
	{
		alert("对不起，当前已经销售截止！");
		return;
	}
	
	if(g_playType != PLAY_TYPE_ZX) {
		alert("标准投注才能机选");
		return;
	}
	
	var number = $("#randomselect").val();
	
	if(number == 0){
		number = $("#randomText").val();
	}
	
	var stringArray = [];
	var valueArray = [];
	
	for(var i = 0; i < number; ) {
		var wan = random(10, 1);
		var qian = random(10, 1);
		var bai = random(10, 1);
		var shi = random(10, 1);
		var ge = random(10, 1);
		wan[0]--;
		qian[0]--;
		bai[0]--;
		shi[0]--;
		ge[0]--;
		var value =wan.join("") + "," +qian.join("") + "," + bai.join("") + "," + shi.join("") + "," + ge.join("");
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

/** *****************  单式上传 ***********************/
//文件读取
function parseFile()
{
	var isIE = (document.all) ? true : false;
	if(!isIE) {
		alert("目前只支持IE浏览器！");
		return;
	}
	var isIE7 = isIE && (navigator.userAgent.indexOf('MSIE 7.0') != -1); 
	var isIE8 = isIE && (navigator.userAgent.indexOf('MSIE 8.0') != -1); 
	var file = document.getElementById("fileField").value;
	
	if(isIE7 || isIE8) { 
		file.select();
		var path = document.selection.createRange().text; 
		document.selection.empty();
		file = path;
	}
	alert(file);
	getFileCode(file);
}
			
function validateLine(line)
{
	var listAll = [];
	listAll = line.split("|");
	if(listAll.length != 2) {
		return 0;
	}
	var listRed = [];
	var listBlue = [];
	listRed = listAll[0].split(",");
	if(listRed.length < 6) {
		return 0;
	}
	listBlue = listAll[1].split(",");
	if(listBlue.length < 1) {
		return 0;
	}
	return math(listRed.length,6) * math(listBlue.length,1);
}

function getFileCode(file) 
{
	var fso  = new ActiveXObject("Scripting.FileSystemObject"); 
    var file = fso.OpenTextFile(file); 
 
    var nostringArray = [];
	var valueArray = [];
	for(;!(file.AtEndOfStream);) {
		line = file.ReadLine(); 
		var num = validateLine(line);
		if(num == 0) {
			continue;
		}
		var nostring = "投注     " + line;
		if($.inArray(nostring, nostringArray) == -1){
			var k = nostringArray.length;
			nostringArray[k] = nostring;
			valueArray[k] = line;
			//g_plan.addCode(playType+"-"+redList.join(",")+"|"+blueList.join(","));
			//valueArray.push(redList.join(",")+"|"+blueList.join(","));
			addToTextarea(nostringArray[k], num, playType+"-"+valueArray[k]);
		}
	}

	file.Close(); 
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

//-----------------------合买--------------------
function calAndDisplayGroup()
{
	var totalMoney = $("#totalBetMoney").text();
	g_plan.perMoney = 1;
	g_plan.totalPart = totalMoney / g_plan.perMoney;
	$("#stock").text(g_plan.totalPart);
	$("#shareStock").text(Math.ceil(Number(g_plan.totalPart) * 0.20) <= 0 ? 1 : Math.ceil(Number(g_plan.totalPart) * 0.20));
	$("#myself").val($("#shareStock").text());
	$("#single_money").text(Number($("#myself").val()) * g_plan.perMoney);
	$("#protect_total").text(Number($("#procted").val()) * Number(g_plan.perMoney));
}

function validateGroupBuy()
{
	var sumMoney = $("#totalBetMoney").text();
	g_plan.perMoney = 1;
	g_plan.totalPart = sumMoney/g_plan.perMoney;
	$("#stock").text(g_plan.totalPart);
	$("#shareStock").text(Math.ceil(Number(g_plan.totalPart)*0.20)<=0?1:Math.ceil(Number(g_plan.totalPart)*0.20));
	if(!$("#myself").val()) {
		$("#myself").val($("#shareStock").text());
	}
	else if(Number(sumMoney)>0&&Number($("#myself").val())<Number($("#shareStock").text())){
		alert("发起人至少需要认购"+$("#shareStock").text()+"份方案");
		$("#myself").val($("#shareStock").text());
		return false;
	}
	else if(Number(sumMoney)>0&&Number($("#myself").val())>Number(g_plan.totalPart)){
		alert("购买份额不能超过总份额");
		$("#myself").val($("#shareStock").text());
		return false;
	}
	$("#single_money").text(Number($("#myself").val())*g_plan.perMoney);
	if($("#procted_all").attr("checked"))
		$("#procted").val(Number(g_plan.totalPart)-Number($("#myself").val()))
	$("#protect_total").text(Number($("#procted").val())*Number(g_plan.perMoney));
	if(Number(sumMoney)>0&&Number($("#procted").val())+Number($("#myself").val())>Number(g_plan.totalPart)){
		alert("保底份额和认购份额不能超过总份额");
		$("#procted").val("0");
		$("#protect_total").text(Number($("#procted").val())*Number(g_plan.perMoney));
		return false;
	}
	return true;
}

//------------追号-----------
function onChaseNumChange()
{
	var num = Number($("#chaseNumber").val());
	getHistoryTerms(g_lotteryType, num, displayTerms);
}

function displayTerms(termArray)
{
	if($(termArray).size()<1) {
		alert("没有未来期数");
		//$("#withChase").click();
		return;
	}
	
	var zhTermList = [];
	var count = Number($("#betTotalNum").text());
	$(termArray).each(function(i){
		var str = "<td width=\"25%\"><input type=\"checkbox\" checked=\"checked\" value='" + 
			this+"'onclick=\"calAndDisplayChase()\"/> <strong>"+this+"</strong>" +
				"<input value=\"1\" size=\"2\" maxlength=\"2\" type=\"text\" onkeyup=\"inputKeyUp(this);\" class=\"textfield\" onblur=\"calAndDisplayChase();\"/> 倍&nbsp;&nbsp; <span id=\"chaseMoney\">" + Number(count*2)+"元</span></td>"

		//var str ="<li><span class=\"betfont_red\">" + ">删除</span></li>";
		zhTermList.push(str);
		//$("#chaseTermList").append(str);
		
	});
    
	if(zhTermList.length>0) {
		$("#chaseTermList").html("<table><tr>" + zhTermList.join("</tr><tr>") + "</tr></table>");
		
		//$("#chase_items").html(zhTermList);
	}
	calAndDisplayChase();
}

function calAndDisplayChase()
{
	var money=0;
	var count = Number($("#betTotalNum").text());
	
	g_plan.zhterm=[];
	g_plan.zhmultiple=[];
	g_plan.zhamount=[];
	
	//:checkbox 不能有空格
	if($("#chaseTermList :checkbox").size()<1) {
		//$("#withChase").click();
	}
	
	$("#chaseTermList :checkbox").each(function() {
		var bs = 0;
		if($(this).attr("checked")) {
			var bs = $(this).nextAll("input").val();
			if(!Number(bs) || Number(bs)<1){
				$(this).nextAll("input").val(1);
				bs = 1;
			}
			g_plan.zhterm.push($(this).val());
			g_plan.zhmultiple.push(Number(bs));
			g_plan.zhamount.push(count * 2 * Number(bs));
			$(this).nextAll("span").text(Number(count * 2 * Number(bs)) + "元");
			money += Number(count * 2 * Number(bs));
		}
		else {
			$(this).nextAll("input").val(0);
			$(this).nextAll("span").text("0元");
		}
	});
	
	$("#chaseTotalMoney").html(money);
}

function onAllSelectChange()
{
	$("#chaseTermList :checkbox").each(function() {
		if($("#all_select").attr("checked")) {
			$(this).attr("checked", true);
		}
		else {
			$(this).attr("checked", false);
		} 
	});
	
	calAndDisplayChase();
}

function inputKeyUp(object)
{
	object.value=object.value.replace(/[^\d]/g,'');
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
					"</li><li>"+redballs[2]+"</li><li>"+redballs[3]+
					"</li><li>"+redballs[4]+"</li>";
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