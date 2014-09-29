var ssq_plan;
var dlt_plan;
var g_betType = BET_TYPE_PERSONAL;

function initQuickBet()
{
	ssq_plan = new Plan();
	dlt_plan = new Plan();
	randomSsq();
	getCurrentTermInfo();
}
		
function changeValue(inputObj)
{
	var number = Number($(inputObj).val());
	if(number < 1) {
		$(inputObj).val("1");
	}
	else if(number > 33) {
		$(inputObj).val("33");
	}
	else {
		$(inputObj).val(number);
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

function randomSsq()
{
	var redBallList = random(33, 6);
	var blueBallList = random(16, 1);
	
	for(var i = 1; i <= 6; i++) {
		if(redBallList[i-1] < 10) {
			$("#ssq_redball_"+i).val("0" + redBallList[i-1]);
		}
		else {
			$("#ssq_redball_"+i).val(redBallList[i-1]);
		}
	}
	
	if(blueBallList[0] < 10) {
		$("#ssq_blueball_1").val("0" + blueBallList[0]);
	}
	else {
		$("#ssq_blueball_1").val(blueBallList[0]);
	}
}

function clearSsq()
{
	for(var i = 1; i <= 6; i++) {
		$("#ssq_redball_"+i).val("");
	}
	
	$("#ssq_blueball_1").val("");
}

function getCurrentTermInfo()
{
	var termUrl = "/lottery/lotteryterm.htm";
	$.getJSON(termUrl, {type: "ssq"}, ssqCallBack);
	//$.getJSON(termUrl, {type: "dlt"}, dltCallBack);
}

function ssqCallBack(json)
{
	ssq_plan.term = json.term;
	$("#ssqCurTerm").text(json.term);
	$("#ssqCurTermStopTime").text(json.termStopTime);
}

function dltCallBack(json)
{
	ssq_plan.term = json.term;
	$("#ssqCurTerm").text(json.term);
	$("#ssqCurTermStopTime").text(json.termStopTime);
}

function getCodeFromInput()
{
	var redBallList = [];
	var blueBallList = [];
	
	for(var i = 1; i <= 6; i++) {
		
		var num = Number($("#ssq_redball_"+i).val());
		if($.inArray(num, redBallList) != -1) {
			alert("错误：红球区域不能出现重复数字："+num);
			return;
		} 
		redBallList[i-1] = Number($("#ssq_redball_"+i).val());
	}
	blueBallList[0] = Number($("#ssq_blueball_1").val());
	
	var code = "fs-" + getCodeFromList(redBallList) + "|" + 
				getCodeFromList(blueBallList);
	return code;
}

function onQuickBet()
{
	var type = "ssq";
	ssq_plan.cleanCode();
	var code = getCodeFromInput(type);
	if(code == null || code == "") {
		return;
	}
	ssq_plan.addCode(code);
	ssq_plan.setPlan(ssq_plan.term, type, "2", 1, "0", 2);
	submitPlan(ssq_plan);
}
