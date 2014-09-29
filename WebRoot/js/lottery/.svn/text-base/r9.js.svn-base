

var g_lotteryType = "r9";
var g_playType = PLAY_TYPE_FS;
var g_betType = BET_TYPE_PERSONAL;

var g_all_matchList = [];
var g_cur_matchList = [];

var g_plan;
function initMatchList()
{
	for(var i = 1; i <= 14; i++) {
		g_all_matchList[i] = [];
		g_cur_matchList[i] = [];
		
		g_all_matchList[i][0] = $("#match_" + i + "_0")[0];
		g_all_matchList[i][1] = $("#match_" + i + "_1")[0];
		g_all_matchList[i][3] = $("#match_" + i + "_3")[0];
		
		g_cur_matchList[i][0] = 0;
		g_cur_matchList[i][1] = 0;
		g_cur_matchList[i][3] = 0;
	}
}
/* 点击球事件 */
function onClickNumber(obj, matchNumber, number) 
{
	//与普通球相反，先点击。然后判断
	if($(obj).attr("class") == "on"+number) {
		g_cur_matchList[matchNumber][number] = 0;
	}
	else {
		g_cur_matchList[matchNumber][number] = 1;
	}
	
	if(validateNum()==true)
	{
		betOnclick(obj, "off"+number, "on"+number);
	}
	else
	{
	/* 回退点击点击事件*/
			if($(obj).attr("class") == "on"+number) {
				g_cur_matchList[matchNumber][number] = 1;
			}
			else {
				g_cur_matchList[matchNumber][number] = 0;
			}
	}

}
/* 全选事件 */
function onSelectAll(checkObj, matchNumber)
{
	if(checkObj.checked) {
		$("#match_" + matchNumber + "_0").attr("class", "on0");
		$("#match_" + matchNumber + "_1").attr("class", "on1");
		$("#match_" + matchNumber + "_3").attr("class", "on3");
		
		g_cur_matchList[matchNumber][0] = 1;
		g_cur_matchList[matchNumber][1] = 1;
		g_cur_matchList[matchNumber][3] = 1;
	}
	else {
		$("#match_" + matchNumber + "_0").attr("class", "off0");
		$("#match_" + matchNumber + "_1").attr("class", "off1");
		$("#match_" + matchNumber + "_3").attr("class", "off3");
		
		g_cur_matchList[matchNumber][0] = 0;
		g_cur_matchList[matchNumber][1] = 0;
		g_cur_matchList[matchNumber][3] = 0;
	}
	validateNum();
}
/* 清除所选事件 */
function clearAllMatch()
{
	for(var i = 1; i <= 14; i++) {
		if(g_all_matchList[i][0]) {
			$(g_all_matchList[i][0]).attr("class", "off0");
		}
		if(g_all_matchList[i][1]) {
			$(g_all_matchList[i][1]).attr("class", "off1");
		}
		if(g_all_matchList[i][3]) {
			$(g_all_matchList[i][3]).attr("class", "off3");
		}
	}
	
	for(var i = 1; i <= 14; i++) {
		g_cur_matchList[i][0] = 0;
		g_cur_matchList[i][1] = 0;
		g_cur_matchList[i][3] = 0;
	}
}

function validateNum()
{
	var cur_match_sel_num;
	var cur_match_choose_num=0;
	var sum = 1;

	for(var i = 1; i <= 14; i++) {	
		cur_match_sel_num = 0;

		if(g_cur_matchList[i][3] == 1) {
			cur_match_sel_num=cur_match_sel_num+1;
		}
		if(g_cur_matchList[i][1] == 1) {
			cur_match_sel_num=cur_match_sel_num+1;
			
		}
		if(g_cur_matchList[i][0] == 1) {
			cur_match_sel_num=cur_match_sel_num+1;
		}
		if(g_cur_matchList[i][0] == 1||g_cur_matchList[i][1] == 1||g_cur_matchList[i][3] == 1)
		{
			sum = sum*cur_match_sel_num;
			cur_match_choose_num=cur_match_choose_num+1;
		}
		if(cur_match_choose_num >9)
		{
			alert("对不起，您最多只能选择九场比赛！");
			return false;
		}
		
		if(g_cur_matchList[i][0]==1 && g_cur_matchList[i][1]==1 && g_cur_matchList[i][3]==1)
		{
			
			$("#match_checkbox_" +i ).attr("checked", true);
		}
		else
		{
		$("#match_checkbox_" +i).attr("checked", false);
		}
	}

	if(cur_match_choose_num<9)sum=0;
	$("#betCount").text(sum);
	$("#betCountMoney").text(sum * 2);
	return true;
}
//分析用户选择，加入列表
function addPlan()
{
	var betCount = $("#betCount").text();
	if(betCount < 1) {
		if(g_playType != PLAY_TYPE_FILE_UPLOAD) {
			alert("请选择投注");
			return;
		}
	}
	var cur_match_select;
	switch(g_playType) {
		case PLAY_TYPE_FS:
			var code = "";
			var choose=0;
			for(var i = 1; i <= 14; i++)
			{	
				cur_match_select = false;
				if(g_cur_matchList[i][3] == 1) {
					cur_match_select = true;
					code += "3";
				}
				if(g_cur_matchList[i][1] == 1) {
					cur_match_select = true;
					code += "1";
				}
				if(g_cur_matchList[i][0] == 1) {
					cur_match_select = true;
					code += "0";
				}
				if(!cur_match_select) {
					code += "#";
				}
				else
				{
					choose=choose+1;
				}
				if(i < 14) {
					code += ",";
				}
			}
			if(choose!=9)
			{
				alert("您必须选择九场比赛！");
				return;
			}
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
function clearSelectedBalls(area)
{
	clearAllMatch();
	validateNum();
}
function clearAllBalls()
{
	clearSelectedBalls(0);
}
function onSubmit()
{
	var betTotalNum = $("#betTotalNum").text();
	if(g_playType != PLAY_TYPE_FILE_UPLOAD && betTotalNum <= 0) {
		alert("请选择投注号码");
		return;
	}
	
	//g_plan.addCode("fs-1,1,1,1,1,1,1,1,1,#,#,#,#,#");
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
	if(g_playType == PLAY_TYPE_FILE_UPLOAD) 
	{
		if( 0 == Number($("#uploadTotalBetMoney").text()))
		 {
			alert("请填写注数！");
			$("#uploadBetNum").focus();
			return;
		}
		g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#uploadTotalBetMoney").text()),Number($("#uploadMultiple").val()), "0", oneMoney);
		submitPlanByFile(g_plan);
		}
		else
		{
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
		
		if(g_playType == PLAY_TYPE_FILE_UPLOAD)
		{
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
					"</li><li>"+redballs[2]+"</li><li>"+redballs[3]+"</li><li>"+redballs[4]+"</li><li>"+redballs[5]+"</li><li>"+redballs[6]+"</li><li>"+redballs[7]+"</li><li>"+redballs[8]+"</li><li>"+redballs[9]+"</li><li>"+redballs[10]+"</li><li>"+redballs[11]+"</li><li>"+redballs[12]+"</li><li>"+redballs[13]+"</li>";
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
