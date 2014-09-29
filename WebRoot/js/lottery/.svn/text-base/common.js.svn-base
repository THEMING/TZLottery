/*
 * 所有彩种的公共部分
 */

/********** 常量区 *************/

var RED_BALL_FALSE = "red_ball_false";
var RED_BALL_TRUE = "red_ball_true";

var BLUE_BALL_FALSE = "blue_ball_false";
var BLUE_BALL_TRUE = "blue_ball_true";

//所有彩种都有标准玩法(单式，复式)和单式上传
var PLAY_TYPE_FS = 1;
var PLAY_TYPE_FILE_UPLOAD = 2;

//投注类型 -- 个人代购 ，合买，追号
var BET_TYPE_PERSONAL = 1;
var BET_TYPE_GROUP = 2;
var BET_TYPE_CHASE = 3;
var needreload=1;
var loginType = -1;//0,1,2分别代表首页中3个快捷投注  3代表直接点合买大厅进去的购买 4代表在合买明细中点击合买

var PERSONAL_URL = "/lottery/buyLottery.htm";
var GROUP_URL = "/lottery/buyLottery.htm?action=togetherSale";
var CHASE_URL = "/lottery/buyLottery.htm?action=chaseSale";


function openQQLogin(suburl, nFrom){
	url = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=100226332&redirect_uri=www.369cai.com" + suburl + "&state=" + nFrom; 
	window.open(url);
}

/********** jquery 操作区 *************/
/* 彩期相关json*/
(function ($) {
	$.LotteryTerm = {
		url : "/lottery/lotteryterm.htm",
		_request : function(param) {
			$.getJSON($.LotteryTerm.url, param, $.LotteryTerm.callback);
		},
		callback : function(){}
	};
})(jQuery);

(function ($) {
	$.termResult = {
		url : "/lottery/lotteryterm.htm?action=result",
		_request : function(param){
			$.getJSON($.termResult.url,param,$.termResult.callback);
		},
		callback : function() {}
	};
})(jQuery);

/* 合买相关json*/
(function ($) {
	$.communityJoin = {
		url : "/lottery/buyLottery.htm?action=communityjoin",
		_request : function(param){
			$.getJSON($.communityJoin.url,param,$.communityJoin.callback);
		},
		callback : function(){}
	};
})(jQuery);

function checkiflogin()
{
	if($("#thenickname").text()=="1")
	{
		return false;
	}
	return true;
}

//获取当前彩期数据
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

// 获取历史数据
function getData()
{
	var para = {
		action : "ajaxRequesTermInfo", 
		type : g_lotteryType
	};
	
	$.LotteryTerm.callback = function(json) {
		if(json) {
			var last = "";
			var pre_list = [];
			
			$(json.hoistList).each(function(i) {
				if(i == 0) {
					last += this.term + "^" + this.result;
				}
				else {
					var tt = this.term + "^" + this.result;
					pre_list.push(tt);
				}
			});
			displayHistory(last, pre_list);
			$("#prizePool").text(json.prizepool); 
		}	
	}
	
	$.LotteryTerm._request(para);
}
/********** plan *************/
function Plan()
{
	this.codes=[];
	this.amount;
	this.termStatus;
	this.term;
	this.type;
	this.oneMoney;
	this.playType;
	this.multiple;
	this.zhterm=[];
	this.zhmultiple=[];
	this.zhamount=[];
	this.winStopped=false;
	this.action = PERSONAL_URL;
	this.description;
	this.perMoney;
	this.totalPart;
	this.buyPart;
	this.insurePart;
	this.commision;
	this.secretType;
	this.chaseMoney;
	this.chaseTerm;
	this.chaseMultiple;
	this.chaseStopMoney;
	this.communityId;
	this.setPlan = function(term, type, amount, multiple, winStopped,
							oneMoney, chaseMoney, chaseStopMoney, description,
							perMoney, totalPart, buyPart, insurePart, commision, secretType) {
		this.term = term;
		this.type = type;
		//this.playType = playType;
		this.amount = amount;
		this.multiple = multiple;
		this.winStopped = winStopped;
		this.oneMoney = oneMoney;
		this.chaseMoney = chaseMoney;
		this.chaseStopMoney = chaseStopMoney;
		this.description = description;
		this.perMoney = perMoney;
		this.totalPart = totalPart;
		this.buyPart = buyPart;
		this.insurePart = insurePart;
		this.commision = commision;
		this.secretType = secretType;
	}
	
	this.addCode = function(code){
		this.codes.push(code);
	}
	
	this.removecode = function(value) {
		var index = $.inArray(value,this.codes);
		this.codes.splice(index,1);
	}
	
	this.cleanCode = function(){
		this.codes=[];
	}
	
	this.toParams = function() 
	{
		var params = {};
		
		//代购，合买，追号公共参数
		if(this.term) {
			params["term"] = this.term;
		}
		
		if(this.type) {
			params["lotteryType"] = this.type;
		}
		
		if(this.codes) {
			params["codes"] = this.codes.join("^");
		}
		
		if(this.multiple) {
			params["multiple"] = this.multiple;
		}
		
		if(this.oneMoney) {
			params["oneMoney"] = this.oneMoney;
		}
		
		if(this.amount) {
			params["money"] = this.amount;
		}
		
		//发起合买有关参数
		if(this.secretType) {
			params["secretType"] = this.secretType;
		}
		
		if(this.commision) {
			params["commision"] = this.commision;
		}

		if(this.insurePart) {
			params["insurePart"] = this.insurePart;
		}
		
		if(this.buyPart) {
			params["buyPart"] = this.buyPart;
		}
		
		if(this.totalPart) {
			params["totalPart"] = this.totalPart;
		}
		
		if(this.perMoney) {
			params["perMoney"] = this.perMoney;
		}
		
		if(this.description) {
			params["description"] = this.description;
		}
		
		//参与合买id
		if(this.communityId) {
			params["communityId"] = this.communityId;
		}
		
		//追号有关参数
		if(this.chaseStopMoney) {
			params["chaseStopMoney"] = this.chaseStopMoney;
		}
		
		if(this.zhmultiple) {
			params["chaseMultiple"] = this.zhmultiple.join(",");
		}
		
		if(this.zhterm) {
			params["chaseTerm"] = this.zhterm.join(",");
		}

		if(this.chaseMoney) {
			params["chaseMoney"] = this.chaseMoney;
		}
		
		return params;
	}
}

//点击球时的颜色切换
function betOnclick(betObj, oldstyle, newstyle)
{
	$(betObj).toggleClass(oldstyle);
	$(betObj).toggleClass(newstyle);
}

//单式上传
function submitPlanByFile(plan)
{
	var tip = "您确定要上传此文件？\n";
	
	if(checkiflogin()==false) 
	{

		$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
		return;
	}
	
	if(!submiting) {
		submiting = true;
	}
	else {
		alert("正在投注请稍候");
		return;
	}
	
	
	
	if(g_betType == BET_TYPE_PERSONAL) {
		tip += "代购";
	}
	else if(g_betType == BET_TYPE_GROUP) {
		tip += "发起合买";
	}
	else if(g_betType == BET_TYPE_CHASE) {
		tip += "追号";
	}
	
	if(confirm(tip)) {
		$.ajaxFileUpload({
			url: plan.action,
			secureuri: false,
			fileElementId:'upload',
			dataType: 'text', //json,text
			data: plan.toParams(),
			success: submitResponse,
			error: function (dat, status, e) {
				alert(e);
			}
		});
	} else
	{
		submiting = false;
	}
}

//上传普通
var submiting = false;
function submitPlan(plan)
{
	if(checkiflogin()==false) 
	{
		$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
		return;
	}
	
	if(!submiting) {
		submiting = true;
	}
	else {
		alert("正在投注请稍候");
		return;
	}

	if(g_betType == BET_TYPE_PERSONAL) {
		if(confirm("您确定要投注？")) {
			$.post(plan.action, plan.toParams(), submitResponse);
		}
		else {
			submiting=false;
			return;
		}
	}
	else if(g_betType == BET_TYPE_GROUP) {
		if(confirm("您确定要发起合买？")) {
			$.post(plan.action, plan.toParams(), submitResponse);
		}
		else {
			submiting=false;
			return;
		}
	}
	else if(g_betType == BET_TYPE_CHASE)
	{
		if(confirm("您确定要追号投注？")) {
			$.post(plan.action, plan.toParams(), submitResponse);
		}
		else {
			submiting=false;
			return;
		}
	}
}

function submitResponse(response, status)
{
	response = eval('(' + response + ')');

	submiting = false;

	switch(response.status)
	{
	case "_0001":
	case "_10000":
		alert(response.message);
		break;
	case "_0000":

		if(g_betType == BET_TYPE_PERSONAL)
		{
			alert("恭喜您，投注成功！");
		}
		else if(g_betType == BET_TYPE_GROUP)
		{
			alert("恭喜您，合买发起成功！");
		}
		else if(g_betType == BET_TYPE_CHASE)
		{
			alert("恭喜您，追号投注成功！");
		}
		if(loginType == -1)
		{
			cleanCode();
		}
		break;
	case "_00000":
		$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
		break;
	}
	if(parent.loginType == 1)
	{
		if(parent.needreload==2)
		{
			parent.location.reload();
		}
	}
	else
	{
		if(needreload==2)
		{
			location.reload();
		}
	}	
}


function getHistoryTerms(type, num, callbackFunc)
{
	$.getJSON(
		"/lottery/lotteryterm.htm?action=zhTerm",
		{term : g_plan.term, type : type, num : num},
		callbackFunc
	);
}

// 得到C(m,i)
function math(m, n)
{
	if (m < 0 || n < 0 || m < n) {
		return 0;
	}
	n = n < (m - n) ? n : m - n;
	smath(m,n);
	if (n == 0) {
		return 1;
	}
	var result = m;
	for (var i = 2, j = result - 1; i <= n; i++, j--) {
		result = result * j / i;
	}
	return result;
}

function smath(m,n)
{
	if (m < 0 || n < 0 || m < n) {
		return 0;
	}
	n = n < (m - n) ? n : m - n;
	if (n == 0) {
		return 1;
	}
	n = n < (m - n) ? n : m - n;
}

function random(m, n)
{
	var exist = [];
	for(var i = 1; i < m + 1; i++) {
		exist[i] = 0;
	}
	
	var list = [];
	for(var i = 0; i < n; ) {
		var index = Math.floor(Math.random() * m) + 1;
		if(exist[index] == 0) {
			list.push(index);
			i++;
			exist[index] = 1;
		}
	}
	return list;
}

String.prototype.replaceAll = function(s1,s2)
{    
	return this.replace(new RegExp(s1,"gm"),s2);    
}

/** 修剪字串前后的空格  */
function trim(s) 
{
	var count = s.length;
	var st    = 0;       // start
	var end   = count-1; // end

	if (s == "") return s;
	while (st < count) {
	    if(s.charAt(st) == " ") {
			st ++;
	    }
		else {
			break;
		}
	}
	while (end > st) {
		if (s.charAt(end) == " ") {
			end --;
		}
		else {
			break;
		}
	}
	return s.substring(st,end + 1);
}

function cleanCode()
{
	g_plan.cleanCode();
	cleanAllPlan();
}


/** ***************** 单式上传公共 ***********************/
function checkFile()
{
	var isValid = false;
	var suffix1 = "txt"; 
	var suffix2 = "369"; 
	
	
	var fileName = document.getElementById("upload").value;
	if(fileName == '') {
		alert("您还没有指定上传文件！");
		return false;
	}
	else {
		var temp = fileName.split("."); 
		var extension = temp[temp.length-1]; 
        extension = extension.toLowerCase();         
        //for(i=0;i<suffix.length;i++) {
           	if(extension == suffix1 || extension == suffix2){
        		isValid = true;
            }
       // }
	}
	
	if(isValid == false) {
		alert("您上传的文件必须以txt或369结尾！");
		return false;
	}
	return true;
}

function onUploadFileChange()
{
	var oneMoney = 2;
	var sumMoney = Number($("#uploadMultiple").val()) * Number($("#uploadBetNum").val());
	$("#uploadTotalBetMoney").text(sumMoney * oneMoney);
	
	if(g_betType == BET_TYPE_GROUP) {
		calAndDisplayGroup();
	}
}

/************** 列表区公共操作 **********************/
//加入列表
function addPlanToList(betString, betNum, value, oneMoney)
{
	var oOption = document.createElement("OPTION");
	oOption.text = betString+" 投注数："+betNum;
	oOption.value = betNum;
	oOption.removeValue = value;
	document.getElementById("planLists").options.add(oOption);
	
	clearAllBalls();
	computeAllMoneyAndDisplay(oneMoney);
}

//计算所有金额并显示
function computeAllMoneyAndDisplay(onemoney)
{
	var multiple = Number($("#multiple").val());
	if(!multiple) {
		multiple = 1;
		$("#multiple").val(multiple);
	}
	
	var betTotalNum = 0;
	
	var ss = document.getElementById("planLists").options.length; 
	for(var i=0;i<ss;i++) {
		betTotalNum += Number(document.getElementById("planLists").options[i].value);  
	}
	
	var totalBetMoney;
	if(onemoney) {
		totalBetMoney = betTotalNum * onemoney * multiple;
	}
	else {
		totalBetMoney = betTotalNum * 2 * multiple;
	}
	$("#betTotalNum").text(betTotalNum);
	$("#totalBetMoney").text(totalBetMoney);
	$("#confirm_money").text(totalBetMoney);
	if(g_betType == BET_TYPE_CHASE) {
		calAndDisplayChase();
	}	
	else if(g_betType == BET_TYPE_GROUP) {
		calAndDisplayGroup();
	}
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

//删除所有投注
function cleanAllPlan()
{
	while(document.getElementById("planLists").options.length > 0) { 
		document.getElementById("planLists").options.remove(0); 
	}
	
	$("#multiple").val("1");
	g_plan.cleanCode();
	
	if(g_betType == BET_TYPE_GROUP) {
		$("#desc").val("");
		$("#procted").val("0");
	}
	computeAllMoneyAndDisplay();
}

//删除单注
function removePlan()
{
	var index = document.getElementById("planLists").selectedIndex;
	//alert(index);
	while(index >= 0) {
		//alert(document.getElementById("planLists").options[index].removeValue);
		g_plan.removecode(document.getElementById("planLists").options[index].removeValue);
		document.getElementById("planLists").remove(index);
		index = document.getElementById("planLists").selectedIndex;
	}
	computeAllMoneyAndDisplay();
}

/*********** 合买和追号公共操作 **************/
//代购，合买，追号切换
/*function onChangeBetType(index)
{
	if(index == 1) {
		calAndDisplayGroup();
		$("#betType_group").show();
		$("#betType_chase").hide();
		$("#multiple").attr("readonly", false);
		g_betType = BET_TYPE_GROUP;
		g_plan.action = BET_TYPE_GROUP;
	}
	else if(index == 2) {
		onChaseNumChange();
		$("#betType_group").hide();
		$("#betType_chase").show();
		$("#multiple").attr("readonly", true);
		g_betType = BET_TYPE_CHASE;
		g_plan.action = CHASE_URL;
	}
	else {
		$("#betType_group").hide();
		$("#betType_chase").hide();
		$("#multiple").attr("readonly", false);
		g_betType = BET_TYPE_PERSONAL;
		g_plan.action = PERSONAL_URL;
	}
}
*/

//购买方式
function onChangeBetType(index)
{
	switch(index)
	{
	case 1:
		calAndDisplayGroup();
		$("#hm_radio").attr("checked","checked");
		$("#betType_group_box").show();
		$("#betType_chase_box").hide();
		$("#multiple").attr("readonly", false);
		g_betType = BET_TYPE_GROUP;
		g_plan.action = GROUP_URL;
		break;
	case 2:
		onChaseNumChange();
		$("#zh_radio").attr("checked","checked");
		$("#betType_group_box").hide();
		$("#betType_chase_box").show();
		$("#multiple").attr("readonly", true);
		g_betType = BET_TYPE_CHASE;
		g_plan.action = CHASE_URL;
		break;
	default:
		$("#dg_radio").attr("checked","checked");
		$("#betType_group_box").hide();
		$("#betType_chase_box").hide();
		$("#multiple").attr("readonly", false);
		g_betType = BET_TYPE_PERSONAL;
		g_plan.action = PERSONAL_URL;
		break;
	}
}
//------------合买-----------
function calAndDisplayGroup()
{
	var totalMoney;
	if(g_playType == PLAY_TYPE_FILE_UPLOAD) {
		totalMoney = Number($("#uploadTotalBetMoney").text());
	}
	else {
		totalMoney = $("#totalBetMoney").text();
	}
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
	var sumMoney;
	if(g_playType == PLAY_TYPE_FILE_UPLOAD) {
		sumMoney = Number($("#uploadTotalBetMoney").text());
	}
	else {
		sumMoney = $("#totalBetMoney").text();
	}
	 
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
		$("#single_money").text(Number($("#myself").val()) * g_plan.perMoney);
		return false;
	}
	else if(Number(sumMoney)>0&&Number($("#myself").val())>Number(g_plan.totalPart)){
		alert("购买份额不能超过总份额");
		$("#myself").val($("#shareStock").text());
		$("#single_money").text(Number($("#myself").val()) * g_plan.perMoney);
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
		return;
	}
	
	var zhTermList = "";
	var count = Number($("#betTotalNum").text());
	$(termArray).each(function(i) {
		var oneTerm = "<li><div class=\"append_sortNum\">"+(i+1)+".</div>";
		oneTerm += "<div class=\"append_box\"><input type=\"checkbox\" checked=\"checked\" value='"+this+"' onclick=\"calAndDisplayChase()\" /></div>";
		oneTerm += "<div class=\"append_period\">"+this+"</div>";
		oneTerm += "<input id=\"chaseinput"+i+"\" type=\"text\" class=\"append_input\" value=\"1\" size=\"2\" maxlength=\"2\" onkeyup=\"inputKeyUp(this)\" onblur=\"calAndDisplayChase()\" />";
		oneTerm += "<div class=\"append_value\">倍&nbsp;<span id=\"chaseMoney" +i+ "\">" + Number(count*2)+"元</span></div></li>";
		zhTermList += oneTerm;
	});
    
	if(zhTermList.length>0) {
		$("#chaseTermList").html(zhTermList);
	}
	
	calAndDisplayChase();
}

function calAndDisplayChase()
{
	var money=0;
	var count = Number($("#betTotalNum").text());
		
	g_plan.zhterm = [];
	g_plan.zhmultiple = [];
	g_plan.zhamount = [];
	
	//:checkbox 不能有空格
	if($("#chaseTermList :checkbox").size() < 1) {
		
	}
	
	$("#chaseTermList :checkbox").each(function(i) {
		var bs = 0;
		if($(this).attr("checked")) {
			var bs = $("#chaseinput"+i).val();
			if(!Number(bs) || Number(bs) < 1){
				$("chaseinput"+i).val(1);
				bs = 1;
			}
			g_plan.zhterm.push($(this).val());
			g_plan.zhmultiple.push(Number(bs));
			g_plan.zhamount.push(count * 2 * Number(bs));
			$("#chaseMoney"+i).text(Number(count * 2 * Number(bs)) + "元");
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

function login()
{
    refreshCaptcha2() ;
	$( "#dialog-form" ).dialog({
	autoOpen: false,
	height: 250,
	width: 350,
	modal: true,
	closeText: '关闭',
	buttons: {
		"登录": function() {
		    if(checkLogin2()){
			$.ajax({
					type:"post",
					url: "/login.htm?action=ajaxLogin",
					data:{nickname:$("#nickname2").val(),password:$("#password2").val(),mngunm:$("#mngunm2").val()},
					success: function(data, textStatus){
					var a=eval("("+data+")");
			if(a.message=="welocme")
			{
			  $( "#dialog-form" ).dialog( "close" );
			  $("#thenickname").text("2");
			   onSubmit();
		
			   needreload=2;
			   //location.reload();
			} else
			{
				$( "#message2").text(a.message);
				
			}
			refreshCaptcha2() ;
			}})}
		},
		"关闭": function() {
			$( this ).dialog( "close" );
		}
	},
	close: function() 
	{		
	}
});
}
function lotteyHalllogin(){
    refreshCaptcha2() ;
	$( "#dialog-form" ).dialog({
	autoOpen: false,
	height: 250,
	width: 350,
	modal: true,
	closeText: '关闭',
	buttons: {
		"登录": function() {

		    if(checkLogin2()){
			$.ajax({
					type:"post",
					url: "/login.htm?action=ajaxLogin",
					data:{nickname:$("#nickname2").val(),password:$("#password2").val(),mngunm:$("#mngunm2").val()},
					success: function(data, textStatus){
					var a=eval("("+data+")");
			if(a.message=="welocme")
			{
			  $( "#dialog-form" ).dialog( "close" );
			   location.reload();
			} else
			{
				$( "#message2").text(a.message);
				
			}
			refreshCaptcha2() ;
			}})}
		},
		"关闭": function() {
			$( this ).dialog( "close" );
		}
	},
	close: function() {
		
	}
});
}
function refreshCaptcha2() {
$('#captchaImg2').html("<img src='/jcaptcha.jpg?"+Math.round(Math.random()*100000)
	+" height='20' width='50' onclick='refreshCaptcha2()' style='cursor:hand'/>");
}
function indexlogin(){
   // refreshCaptcha2() ;
	$( "#dialog-form" ).dialog({
	autoOpen: false,
	height: 250,
	width: 350,
	modal: true,
	closeText: '关闭',
	buttons: {
		"登录": function() {
		    if(checkLogin2()){
			$.ajax({
					type:"post",
					url: "/login.htm?action=ajaxLogin",
					data:{nickname:$("#nickname2").val(),password:$("#password2").val(),mngunm:$("#mngunm2").val()},
					success: function(data, textStatus){
					var a=eval("("+data+")");
			if(a.message=="welocme")
			{
			  $( "#dialog-form" ).dialog( "close" );
			  alert("登录成功");
			  needreload=2;
			  $("#thenickname").text("2");
			  if(loginType == 0)
			  {
			  		onQuickBet();
			  }
			  else if(loginType == 1)
			  {
			  		document.frames('zcdz_iframe').onIndexSubmit();
			  }
			  else if(loginType == 2)
			  {
					document.frames('groupbuy_iframe').group_table_buy();
			  }
			  else if(loginType == 3)
			  {
			  		group_table_buy();
			  }
			  else
			  {
				    goBuy($("#lotteryType1").val(),$("#termNo").val(),$("#numpart"),$("#communityId").val());
			  }
			} else
			{
				$( "#message2").text(a.message);
				
			}
			refreshCaptcha2() ;
			}})}
		},
		"关闭": function() {
			$( this ).dialog( "close" );
		}
	},
	close: function() {
		
	}
});
}
function gotoSoftware()
{
	if(g_lotteryType=="ssq")window.open("/help/help-home-5-3-12.htm")
	if(g_lotteryType=="6cb")window.open("/help/help-3-10.htm")
	if(g_lotteryType=="qlc")window.open("/help/help-3-10.htm")
	if(g_lotteryType=="3d")window.open("/help/help-home-5-3-13.htm")
	if(g_lotteryType=="pls")window.open("/help/help-home-5-3-13.htm")
	if(g_lotteryType=="plw")window.open("/help/help-3-10.htm")
	if(g_lotteryType=="qxc")window.open("/help/help-3-10.htm")
	if(g_lotteryType=="4cjq")window.open("/help/help-home-5-3-16.htm")
	if(g_lotteryType=="r9")window.open("/help/help-home-5-3-14.htm")
	if(g_lotteryType=="14sfc")window.open("/help/help-home-5-3-11.htm")
	if(g_lotteryType=="dlt")window.open("/help/help-home-5-3-15.htm")

}
	function displayAllSelectedMatch1(){
		if($("#q1").attr("src")=="/images/lottery/only1.jpg")
		{
		$("#q1").attr("src","/images/lottery/whole1.jpg");
		displayAllSelectedMatch();
		return;
		}
		if($("#q1").attr("src")=="/images/lottery/whole1.jpg")
		{
		$("#q1").attr("src","/images/lottery/only1.jpg");
		quanxuan();
		return;
		}
		
		}