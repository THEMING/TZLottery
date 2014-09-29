var g_lotteryType = "jclq";
//是否单关或者多关，单关为1，多关为2
var danguan_duoguan =2;
//猜胜负＝1
var PLAY_TYPE_CSF = 1;
//让分胜负
var PLAY_TYPE_RFSF = 2;
//胜分差
var PLAY_TYPE_SFC = 3;
//大小分
var PLAY_TYPE_DXF = 4;

var PLAY_DANGUAN =1;
var PLAY_DUOGUAN =2;

var panel = 1;   		// 串1/多之间的切换
var curRadio = null;	// 串多选项

var matchList = [];   			// 对阵表      （字段：场次编号，主队，让球，客队，截止销售时间）
var choosen_match_list = []; 	// 投注详情列表 （字段：场次编号，胜，负）

var found = false;  // 二分查找的标志位

var hiddenMatch = 0;
var currentTotalBetCount = 0; // 当前投注总注数
var maxBonus =0;//理论最高奖金
var minBonus =0;

var g_betType = BET_TYPE_PERSONAL;

var selectcon = true;
var selectnocon = true;
var selectedonly = false;

var StatusPos = 47;
var SelectionMatchGamePos = 48;
var SelectionPos = 49;
/** 用于向后台封装投注字符串，注意与choosen_match_list中各字段保持对应 */
var CSF_CODE = ["","2","1"];	//1-主胜 2－客胜
var RFSF_CODE = ["","2","1"];	//1-主胜 2－客胜
var SFC_CODE = ["","11","12","13","14","15","16",  	//客胜
                   "01","02","03","04","05","06"];	//主胜
var DXF_CODE = ["","1","2"];	//1-大分，2－小分

var g_plan;

function init()
{
	g_plan = new Plan();
	$.LotteryTerm.callback = function(json) { 
		g_plan.term = json.term;
		g_plan.termStatus = json.termStatus;
	};
	
	$.LotteryTerm._request({type: g_lotteryType});
	$("#tournament_panel").hide();
	
	onChangeBetType(0);
	initdanguanduoguan();
	getMatchList();
}
function initdanguanduoguan()
{
	if(danguan_duoguan==1)
	{
		$("#lab_1c1").show();
		$("#free_go").hide();
		$("#multi_go").hide();
	} 
	else
	{
	}
}

/** 用JSON从服务器获取对阵列表 begin */
(function ($) {
		$.JCLQMatch = {
				url : "/lottery/JCLQMatch.htm?action=getMatchList",
				_request : function(param){
					$.getJSON($.JCLQMatch.url, param, $.JCLQMatch.callback);
				},
				callback : function(){}
			};
})(jQuery);

/** 获取对阵详细信息到js （下一步工作是将jsp页面中的对阵数据用js实现） */
function getMatchList()
{
 	$.JCLQMatch.callback = function(json) {
	 	var matchNum = eval(json).length;
		for(var i=0; i<matchNum; i++)
		{
			var newEle = new Array();
			newEle[0] = eval(json)[i]["matchNo"];       // 场次编号
			newEle[1] = eval(json)[i]["matchName"];	    // 赛事
			newEle[2] = eval(json)[i]["matchTime"];     // 开赛时间
			newEle[3] = eval(json)[i]["remainTime"];    // 剩余销售时间
			newEle[4] = eval(json)[i]["homeTeam"];	 	// 主队
			newEle[5] = eval(json)[i]["rang"];    		// 让球
			newEle[6] = eval(json)[i]["awaryTeam"];   	// 客队
			newEle[7] = eval(json)[i]["sop"]; 			// 胜欧赔
			newEle[8] = eval(json)[i]["fop"]; 			// 负欧赔
			newEle[9] = eval(json)[i]["stzb"]; 			// 胜投注比
			newEle[10] = eval(json)[i]["ftzb"]; 		// 负投注比
			var currRatio = eval(json)[i]["currentRatios"];	//赔率
			var ratios = currRatio.split("|");
			var plRatios; 
			//平均赔率
			if(g_playType==PLAY_TYPE_CSF)
			{
				if(ratios[0]==undefined)
				{
					for(var kk=0; kk<4; kk++) newEle[11 + kk] = "0";
				} 
				else
				{
					plRatios= ratios[0].split(";");
					newEle[11] = plRatios[0].split(",")[0];
					if(newEle[11] == "-1")
					{
						newEle[11] = "0";
						newEle[12] = "0";
					}
					else
					{
						newEle[12] = plRatios[0].split(",")[1];
					}
				
					// 胜平负即时赔率-多关
					newEle[13] = plRatios[1].split(",")[0];
					if(newEle[13] == "-1")
					{
						newEle[13] = "0";
						newEle[14] = "0";
					}
					else
					{
						newEle[14] = plRatios[1].split(",")[1];
					}
				}
			}
			else if(g_playType==PLAY_TYPE_RFSF)
			{
				if(ratios[1]==undefined)
				{
					for(var kk=0; kk<4; kk++) newEle[11 + kk] = "0";
				} 
				else
				{
					plRatios= ratios[1].split(";");
					newEle[15] = plRatios[0].split(",")[0];
					if(newEle[15] == "-1")
					{
						newEle[15] = "0";
						newEle[16] = "0";
					}
					else
					{
						newEle[16] = plRatios[0].split(",")[1];
					}
				
					// 胜平负即时赔率-多关
					newEle[17] = plRatios[1].split(",")[0];
					if(newEle[17] == "-1")
					{
						newEle[17] = "0";
						newEle[18] = "0";
					}
					else
					{
						newEle[18] = plRatios[1].split(",")[1];
					}
				}
			}
			//胜分差有12个选项，分别为：Z1-5,Z6-10,Z11-15,Z16-20,Z21-25,Z26+
			//K1-5,K6-10,K11-15,K16-20,K21-25,K26+
			else if(g_playType==PLAY_TYPE_SFC)
			{
				if(ratios[2]==undefined) 
				{
					for (var k= 0; k<24; k++)
						newEle[19+k] = "0";
				} 
				else{
					plRatios= ratios[2].split(";");
					if(plRatios[0].split(",")[0] == "-1")
					{
						for (var k= 0; k<12; k++)
							newEle[19+k] = "0";
					}
					else
					{
						for (var k= 0; k<12; k++)
						{
							if(k%2==0)
								newEle[19+k/2] = plRatios[0].split(",")[k];
							if(k%2==1)
								newEle[19+(k-1)/2+6] = plRatios[0].split(",")[k];
						}
					}
					
					if(plRatios[1].split(",")[0] == "-1")
					{
						for (var k= 0; k<12; k++)
							newEle[31+k] = "0";
					}
					else
					{
						for (var k= 0; k<12; k++)
						{
							if(k%2==0)
								newEle[31+k/2] = plRatios[1].split(",")[k];
							if(k%2==1)
								newEle[31+(k-1)/2+6] = plRatios[1].split(",")[k];
						}
					}
				}
			}
			else if(g_playType==PLAY_TYPE_DXF)
			{
				if(ratios[3]==undefined) 
				{
					// 大小分即时赔率-单关
					for (var k= 0; k<2; k++)
						newEle[43+k] = "0";
			
					// 大小分即时赔率-多关
					for (var k= 0; k<2; k++)
						newEle[45+k] = "0";
				} 
				else
				{
					plRatios= ratios[3].split(";");
					if(plRatios[0].split(",")[0] == "-1")
					{
						for (var k= 0; k<2; k++)
							newEle[43+k] = "0";
					}
					else
					{
						for (var k= 0; k<2; k++)
							newEle[43+k] = plRatios[0].split(",")[k];
					}
				
					// 猜比分即时赔率-多关
					if(plRatios[1].split(",")[0] == "-1")
					{
						for (var k= 0; k<2; k++)
							newEle[45+k] = "0";
					}
					else
					{
						for (var k= 0; k< 2; k++)
							newEle[45+k] = plRatios[1].split(",")[k];
					}
				}
			}			
			
			// 状态位：销售中
			newEle[StatusPos] = "1"; 
			newEle[SelectionMatchGamePos] = "1"; //显示联赛选中位
			newEle[SelectionPos] = "0"; //是否选择
			matchList[i] = newEle;
		}
		
		// 设置定时任务 
		for(var i=0; i< matchList.length; i++)
		{
			var delay = parseInt(matchList[i][3])*1000;
			setTimeout("performStopTask("+i+")", delay);
			$("#jiezhi_"+matchList[i][0]).countdown({until:delay/1000, compact: true, format: 'DHM'});
		}
		calculateConcede();
		refreshpeilv();
	};
	$.JCLQMatch._request({ptype: g_playType, danduo: danguan_duoguan});
	//$.JCLQMatch._request({});
}
/** 用JSON从服务器获取对阵列表 end */
function changePeilvGailv(obj)
{
	if(obj.value==0)
	{
		$("div[id^='average_sp']").show();show();
		$("div[id^='bet_rate']").hide();
	}else
	{
		$("div[id^='average_sp']").hide();
		$("div[id^='bet_rate']").show();
	}
}

function displayFilter()
{
	for(var i=0; i<matchList.length; i++)
	{
		if(selectedonly)
		{
			if(matchList[i][SelectionPos]=="1")
			{
				$("#line_" + matchList[i][0]).attr("class", "show");
			} 
			else
			{
				$("#line_" + matchList[i][0]).attr("class", "hidden");
			}
			continue;
		}
		
		if(matchList[i][SelectionMatchGamePos]=="1") {
			if(matchList[i][5] == undefined)
			{
				if(selectnocon==true)
				{
					$("#line_" + matchList[i][0]).attr("class", "show");
				} 
				else
				{
					if(matchList[i][SelectionPos]=="0")
					{
						$("#line_" + matchList[i][0]).attr("class", "hidden");
					}
				}
			} 
			else
			{
				if(selectcon==true)
				{
					$("#line_" + matchList[i][0]).attr("class", "show");
				} else
				{
					if(matchList[i][SelectionPos]=="0")
					{
						$("#line_" + matchList[i][0]).attr("class", "hidden");
					}
				}
			}
		}
		else
		{
			if(matchList[i][SelectionPos]=="0")
			{
				$("#line_" + matchList[i][0]).attr("class", "hidden");
			}
		}
	}
}

/** 到期后停止销售对阵表中第index场比赛 */
function performStopTask(index)
{	
	matchList[index][StatusPos]="0"; 
}

function refreshpeilv()
{
	for(var i=0; i<matchList.length; i++)
	{
		if(g_playType==PLAY_TYPE_CSF)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				$("#speilv_"+matchList[i][0]).text(matchList[i][14]);
				$("#fpeilv_"+matchList[i][0]).text(matchList[i][13]);
			} 
			else
			{
				$("#speilv_"+matchList[i][0]).text(matchList[i][12]);
				$("#fpeilv_"+matchList[i][0]).text(matchList[i][11]);
			}
		}
		else if(g_playType==PLAY_TYPE_RFSF)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				$("#speilv_"+matchList[i][0]).text(matchList[i][18]);
				$("#fpeilv_"+matchList[i][0]).text(matchList[i][17]);
			} 
			else
			{
				$("#speilv_"+matchList[i][0]).text(matchList[i][16]);
				$("#fpeilv_"+matchList[i][0]).text(matchList[i][15]);
			}
		}
		else if(g_playType==PLAY_TYPE_SFC)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				for(var j=0;j<6;j++)
				{
					$("#speilv"+j+"_"+matchList[i][0]).text(matchList[i][31+j]);
					$("#fpeilv"+j+"_"+matchList[i][0]).text(matchList[i][31+j+6]);
				}
				
				
				
			} 
			else
			{
				for(var j=0;j<6;j++)
				{
					$("#speilv"+j+"_"+matchList[i][0]).text(matchList[i][19+j]);
					$("#fpeilv"+j+"_"+matchList[i][0]).text(matchList[i][19+j+6]);
				}
			}
		}
		else if(g_playType==PLAY_TYPE_DXF)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				$("#dfpeilv_"+matchList[i][0]).text(matchList[i][45]);
				$("#xfpeilv_"+matchList[i][0]).text(matchList[i][46]);
			} 
			else
			{
				$("#dfpeilv_"+matchList[i][0]).text(matchList[i][43]);
				$("#xfpeilv_"+matchList[i][0]).text(matchList[i][44]);
			}
		}		
	}
}

function isSelected(match)
{	
	for(var i = 0; i < choosen_match_list.length; i++) 
	{	
		if(choosen_match_list[i][0] == match) 
		{
			return true;
		} 
	}
	return false; 
}

function displayAllSelectedMatch() 
{	
	selectedonly=true;
	$(":checkbox[id^='lg']").attr("checked","");
    $("chkConcede").attr("checked","true");
    $("chkNoConcede").attr("checked","true");

	displayFilter();
	$("#selectedMatch").text(choosen_match_list.length);
}

/** 计算让球/非让球场次数 */
function calculateConcede()
{
	var concede = 0, noConcede = 0;

	for(var i=0; i<matchList.length; i++)
	{
		if(matchList[i][5] == undefined)
			noConcede++;
		else
			concede++;
	}
	$("#concedeNum").text(concede);
	$("#noConcedeNum").text(noConcede);
}

/** 显示/隐藏让球(非让球)场次 */
function clickChkConcede()
{
	var oper =[];
	var hiddenMatch = 0;
	selectedonly = false;
	if ($("#chkConcede")[0].checked) {
		selectcon=true;
		oper[0] = "show";
	}
	else
	{
		oper[0] = "hidden";
		selectcon=false;	
	}
	if ($("#chkNoConcede")[0].checked)
	{
		selectnocon=true;
		oper[1] = "show";
	}
	else
	{
		oper[1] = "hidden";
		selectnocon=false;
	}
	
	displayFilter();
}

/** 显示所有比赛场次 */
function chooseDispTime()
{
	if($("#select_timeqqq")[0].selectedIndex == 1)
		alert(1);
	else
		alert(0);
}

/** 选择胜平 */
function clickChkBet(target,matchNo,index)
{
	if(target.checked)
		addToList(matchNo, index);
	else
		deleteFromList(matchNo, index);

	// show and hide "chuan"
	if(danguan_duoguan==PLAY_DUOGUAN)
	{
		var maxallowchosegames = choosen_match_list.length;
		if(g_playType==PLAY_TYPE_CSF || g_playType==PLAY_TYPE_RFSF)
		{
			if(maxallowchosegames >= 8)	maxallowchosegames = 8;
		}
		else
		{
			if(maxallowchosegames >= 3)	maxallowchosegames = 3;
		}

		if (panel == 1)
		{
			//changed by songzs, add: 胜负/让分胜负：8串1，其它3串1.
			for (var i = 2; i<= maxallowchosegames; i++)
			{
				$("#" + i + "c1").show();
				$("#lab_" + i + "c1").show();
			}
			for (var i = maxallowchosegames+1; i<= 8; i++)
			{
				$("#" + i + "c1").hide();
				$("#lab_" + i + "c1").hide();
			}
		}
		else
		{
			if(0==choosen_match_list.length || 1==choosen_match_list.length || 2==choosen_match_list.length)
			{
				for(var j=3; j<=8; j++)
					hide(j);
			}
			else
			{
				for(var j=3; j<=8; j++)
					hide(j);
				
				if(choosen_match_list.length <= maxallowchosegames)
					show(choosen_match_list.length);	
				else
					show(maxallowchosegames);	
			}
		}
	}

	clearChooseBunch();
	currentTotalBetCount = 0;
	maxBonus = 0;
	minBonus = 0;
	
	if(danguan_duoguan==PLAY_DANGUAN)
	{
		clickCalculateDan();
	} 
	else
	{
		$("#matchCount").text(choosen_match_list.length);
		$("#totalBetCount").text(0);
		$("#maxBonus").text(0);
		$("#minBonus").text(0);
		$("#totalBetMoney").text(0);
		$("#confirm_money").text(0);
	}
	$("#selectedMatch").text(choosen_match_list.length);
}

/** 选择串1面板 */
function show_chuan_one_list()
{
	var maxallowchosegames = choosen_match_list.length;
	if(g_playType==PLAY_TYPE_CSF || g_playType==PLAY_TYPE_RFSF)
	{
		if(maxallowchosegames >= 8)	maxallowchosegames = 8;
	}
	else
	{
		if(maxallowchosegames >= 3)	maxallowchosegames = 3;
	}
	if(panel==2) //假如发生切换
	{
		panel = 1;		
		$("#multi_go").attr("class", "title_old");
		$("#free_go").attr("class", "title_cur");
		$("#chuan_one_list").show();
		$("#chuan_multi_list").hide();
		
		for (var i = 2; i<=8; i++)
		{
			$("#" + i + "c1").hide();
			$("#lab_" + i + "c1").hide();
		}
		
		for (var i = 2; i<= choosen_match_list.length; i++)
		{
			if(i<=maxallowchosegames)
			{
				$("#" + i + "c1").show();
				$("#lab_" + i + "c1").show();
			}
		}
		clearChooseBunch();
		currentTotalBetCount = 0;
		maxBonus = 0;
		minBonus =0;
		
		$("#matchCount").text(choosen_match_list.length);
		$("#totalBetCount").text(0);
		$("#maxBonus").text(0);
		$("#minBonus").text(0);
		$("#totalBetMoney").text(0);
		$("#confirm_money").text(0);
	}
}

/** 选择串多面板 */
function show_chuan_multi_list()
{
	var maxallowchosegames = choosen_match_list.length;
	if(g_playType==PLAY_TYPE_CSF || g_playType==PLAY_TYPE_RFSF)
	{
		if(maxallowchosegames >= 8)	maxallowchosegames = 8;
	}
	else
	{
		if(maxallowchosegames >= 3)	maxallowchosegames = 3;
	}
	if(panel==1) //假如发生切换
	{
		panel = 2;		
		$("#multi_go").attr("class", "title_cur");
		$("#free_go").attr("class", "title_old");
		$("#chuan_one_list").hide();
		$("#chuan_multi_list").show();
		
		for(var j=3; j<=8; j++)
			hide(j);
		
		if(choosen_match_list.length<=maxallowchosegames)
		{
			show(choosen_match_list.length);	
		}
		else
		{
			show(maxallowchosegames);	
		}
		clearChooseBunch();
		currentTotalBetCount = 0;
		maxBonus = 0;
		minBonus = 0;
		
		$("#matchCount").text(choosen_match_list.length);
		$("#totalBetCount").text(0);
		$("#maxBonus").text(0);
		$("#minBonus").text(0);
		$("#totalBetMoney").text(0);
		$("#confirm_money").text(0);
	}
}

/** 选择“多串一” */
function clickChkGg(target)
{
	var m = parseInt(target.id.split("c")[0]);
	var n = parseInt(target.id.split("c")[1]);
	
	if(target.checked)
	{
		currentTotalBetCount += calculateBetCountMcN(m, n);
		maxBonus += calculateMaxBonusMcN(m,n);
		minBonus +=calculateMinBonusMcN(m,n);
	}
	else
	{
		currentTotalBetCount -= calculateBetCountMcN(m, n);
		maxBonus -= calculateMaxBonusMcN(m,n);
		minBonus -=calculateMinBonusMcN(m,n);
	}

	$("#totalBetCount").text(currentTotalBetCount* $("#multiple").val());
	$("#maxBonus").text(Math.round(maxBonus)* $("#multiple").val());
	$("#minBonus").text(Math.round(minBonus)* $("#multiple").val());
	$("#totalBetMoney").text(currentTotalBetCount * 2 * $("#multiple").val());
	$("#confirm_money").text($("#totalBetMoney").text());
	if(g_betType == BET_TYPE_GROUP)
		calAndDisplayGroup();
}

function clickCalculateDan()
{
	var m;
	var n;
	
	m=1;
	n=1;
	currentTotalBetCount = calculateBetCountMcNDan(m, n);
		
	maxBonus = calculateMaxBonusMcNDan(m,n);
	minBonus = calculateMinBonusMcNDan(m,n);

	$("#totalBetCount").text(currentTotalBetCount* $("#multiple").val());
	$("#maxBonus").text(Math.round(maxBonus)* $("#multiple").val());
	$("#minBonus").text(Math.round(minBonus)* $("#multiple").val());
	$("#totalBetMoney").text(currentTotalBetCount * 2 * $("#multiple").val());
	$("#confirm_money").text($("#totalBetMoney").text());
	if(g_betType == BET_TYPE_GROUP)
		calAndDisplayGroup();
}

/** 选择“多串多” */
function clickRadioGg(target)
{
	if(curRadio)
		$(curRadio).attr("checked", false);
	$(target).attr("checked", true);
	curRadio = target;
	
	var m = parseInt(target.id.split("c")[0]);
	var n = parseInt(target.id.split("c")[1]);

	currentTotalBetCount = calculateBetCountMcN(m, n);	
	maxBonus = calculateMaxBonusMcN(m, n);
	minBonus = calculateMinBonusMcN(m, n);
	$("#totalBetCount").text(currentTotalBetCount);
	$("#maxBonus").text(Math.round(maxBonus));
	$("#minBonus").text(Math.round(minBonus));
	$("#totalBetMoney").text(currentTotalBetCount * 2 * $("#multiple").val());
	$("#confirm_money").text($("#totalBetMoney").text());
	
	onJCLQMultipleChange();
	
	if(g_betType == BET_TYPE_GROUP)
		calAndDisplayGroup();
}

/** 将选择的比赛加入选择列表 */
function addToList(matchNum, ind)
{
	if(choosen_match_list.length == 0) // 列表为空的情况
	{
		var newEle = new Array();
		newEle[0] = matchNum;
		for(var k=0;k<31;k++)
			newEle[k+1] = 0;
		newEle[ind] = 1;
		choosen_match_list[0] = newEle;
	}
	else  // 列表不为空
	{
		found = false;
		var index = binarySearch(choosen_match_list, matchNum, 0, choosen_match_list.length-1);
		if(found == true)
		{
			choosen_match_list[index][ind]=1;
		}
		else
		{
			var newEle = new Array();
			newEle[0] = matchNum;
			for(var k=0;k<31;k++)
				newEle[k+1] = 0;
			newEle[ind] = 1;
			
			var part1 = choosen_match_list.slice(0, index);    
    		var part2 = choosen_match_list.slice(index);    
			part1.push(newEle);
    		choosen_match_list = part1.concat(part2);
		}		
	}
	for(var i=0; i<matchList.length; i++)
	{
		if(matchList[i][0]==matchNum) 
		{
			matchList[i][SelectionPos] = "1";
			break;
		}
	}
}
/** 删除比赛列表 */
function deleteFromList(matchNum, ind)
{
	var selected;
	for(var i = 0; i < choosen_match_list.length; i++) 
	{
		if(choosen_match_list[i][0] == matchNum) 
		{
			choosen_match_list[i][ind] = 0;
			selected = false;
			for(var k=1;k<=31;k++)
			{
				if(choosen_match_list[i][k]==1)
				{
					selected = true;
					break;
				}
			}
			if(selected == false)
			{
				var part1 = choosen_match_list.slice(0, i+1);
				var part2 = choosen_match_list.slice(i+1);            
				part1.pop();
				choosen_match_list = part1.concat(part2);
				
				for(var j=0; j<matchList.length; j++)
				{
					if(matchList[j][0]==matchNum) 
					{
						matchList[j][SelectionPos] = "0";
						break;
					}
				}
			}
			break;
		}
	}
}

var oldmul=1;
/** 投注倍数 */
function onJCLQMultipleChange()
{
	var mul = Number($("#multiple").val());
	if(g_betType == BET_TYPE_GROUP&&mul > 99)
	{
		alert("投注倍数不能超过99倍 ！");
		$("#multiple").val(99);
		mul = 99;
	
	}
	if(!mul || mul < 1 || mul > 10000){ 
		    alert("投注倍数不能超过10000倍 ,小于1倍！");  
			$("#multiple").val(1);
			mul = 1;
	}
    
	var totalBetCount = currentTotalBetCount;
	$("#totalBetCount").text(currentTotalBetCount* $("#multiple").val());
	$("#totalBetMoney").text(totalBetCount * 2 * mul);
	$("#maxBonus").text(Math.round(maxBonus * mul));
	$("#minBonus").text(Math.round(minBonus * mul));
	$("#confirm_money").text($("#totalBetMoney").text());
	if(g_betType == BET_TYPE_GROUP)
		calAndDisplayGroup();
	oldmul=mul;
}

/** 二分查找 */
function binarySearch(list, target, begin, end)
{
	for(var i = 0; i < choosen_match_list.length; i++) 
	{
		if(choosen_match_list[i][0]==target)
		{
			found = true;
			return i;
		}
	}
	return 0;
}

/** 多串多Radio的隐藏 */
function hide(num)
{
	switch (num) {
		case 3 :
			$("#3c3").hide();	$("#lab_3c3").hide();
			$("#3c4").hide();	$("#lab_3c4").hide();
			break;
		case 4 :
			$("#4c4").hide();	$("#lab_4c4").hide();
			$("#4c5").hide();	$("#lab_4c5").hide();
			$("#4c6").hide();	$("#lab_4c6").hide();
			$("#4c11").hide();	$("#lab_4c11").hide();
			break;
		case 5 :
			$("#5c5").hide();	$("#lab_5c5").hide();
			$("#5c6").hide();	$("#lab_5c6").hide();
			$("#5c10").hide();	$("#lab_5c10").hide();
			$("#5c16").hide();	$("#lab_5c16").hide();
			$("#5c20").hide();	$("#lab_5c20").hide();
			$("#5c26").hide();	$("#lab_5c26").hide();
			break;
		case 6 :
			$("#6c6").hide();	$("#lab_6c6").hide();
			$("#6c7").hide();	$("#lab_6c7").hide();
			$("#6c15").hide();	$("#lab_6c15").hide();
			$("#6c20").hide();	$("#lab_6c20").hide();
			$("#6c22").hide();	$("#lab_6c22").hide();
			$("#6c35").hide();	$("#lab_6c35").hide();
			$("#6c42").hide();	$("#lab_6c42").hide();
			$("#6c50").hide();	$("#lab_6c50").hide();
			$("#6c57").hide();	$("#lab_6c57").hide();
			break;
		case 7 :
			$("#7c7").hide();	$("#lab_7c7").hide();
			$("#7c8").hide();	$("#lab_7c8").hide();
			$("#7c21").hide();	$("#lab_7c21").hide();
			$("#7c35").hide();	$("#lab_7c35").hide();
			$("#7c120").hide();	$("#lab_7c120").hide();
			break;
		case 8 :
			$("#8c8").hide();	$("#lab_8c8").hide();
			$("#8c9").hide();	$("#lab_8c9").hide();
			$("#8c28").hide();	$("#lab_8c28").hide();
			$("#8c56").hide();	$("#lab_8c56").hide();
			$("#8c70").hide();	$("#lab_8c70").hide();
			$("#8c247").hide();	$("#lab_8c247").hide();	
			break;
		default :
	}
}

/** 多串多Radio的显示 */
function show(num)
{
	switch (num) {
		case 3 :
			$("#3c3").show();	$("#lab_3c3").show();
			$("#3c4").show();	$("#lab_3c4").show();
			break;
		case 4 :
			$("#4c4").show();	$("#lab_4c4").show();
			$("#4c5").show();	$("#lab_4c5").show();
			$("#4c6").show();	$("#lab_4c6").show();
			$("#4c11").show();	$("#lab_4c11").show();
			break;
		case 5 :
			$("#5c5").show();	$("#lab_5c5").show();
			$("#5c6").show();	$("#lab_5c6").show();
			$("#5c10").show();	$("#lab_5c10").show();
			$("#5c16").show();	$("#lab_5c16").show();
			$("#5c20").show();	$("#lab_5c20").show();
			$("#5c26").show();	$("#lab_5c26").show();
			break;
		case 6 :
			$("#6c6").show();	$("#lab_6c6").show();
			$("#6c7").show();	$("#lab_6c7").show();
			$("#6c15").show();	$("#lab_6c15").show();
			$("#6c20").show();	$("#lab_6c20").show();
			$("#6c22").show();	$("#lab_6c22").show();
			$("#6c35").show();	$("#lab_6c35").show();
			$("#6c42").show();	$("#lab_6c42").show();
			$("#6c50").show();	$("#lab_6c50").show();
			$("#6c57").show();	$("#lab_6c57").show();
			break;
		case 7 :
			$("#7c7").show();	$("#lab_7c7").show();
			$("#7c8").show();	$("#lab_7c8").show();
			$("#7c21").show();	$("#lab_7c21").show();
			$("#7c35").show();	$("#lab_7c35").show();
			$("#7c120").show();	$("#lab_7c120").show();
			break;
		case 8 :
			$("#8c8").show();	$("#lab_8c8").show();
			$("#8c9").show();	$("#lab_8c9").show();
			$("#8c28").show();	$("#lab_8c28").show();
			$("#8c56").show();	$("#lab_8c56").show();	
			$("#8c70").show();	$("#lab_8c70").show();	
			$("#8c247").show();	$("#lab_8c247").show();	
			break;
		default :
	}
}

/** 清空选择的串 */
function clearChooseBunch()
{
	for(var i=2; i<=8; i++)
		$("#"+i+"c1").attr("checked",false);
	if(curRadio)
	{
		$(curRadio).attr("checked", false);
		curRadio=null;
	}
}

/** 计算当前选择下 former串latter 的注数 */
function calculateBetCountMcN(former, latter)
{
 	switch (former) {
 		case 1 :
       		var sum = 0;
       		for(var i=0; i<choosen_match_list.length; i++)
       		{
       			for(var j=1; j<=31; j++)
       				sum += choosen_match_list[i][j];
       		}
       		return sum;
       	case 2 :
       		return calculateBetCountMc1(2, 0);  // 2串1
       	case 3 :
       		if (1 == latter) // 3串1
       			return calculateBetCountMc1(3, 0); 
       		else if (3 == latter)  // 3串3
       			return calculateBetCountMc1(2, 0);
       		else  // 3串4
       			return calculateBetCountMc1(2, 0)+calculateBetCountMc1(3, 0);
       	case 4 :
       		switch (latter) {
       			case 1:  // 4串1
       				return calculateBetCountMc1(4, 0);
       			case 4:  // 4串4
       				return calculateBetCountMc1(3, 0);
       			case 5:  // 4串5
       				return calculateBetCountMc1(3, 0)+ calculateBetCountMc1(4, 0);
       			case 6:  // 4串6
       				return calculateBetCountMc1(2, 0);
       			case 11:  // 4串11
       				return calculateBetCountMc1(2, 0) + calculateBetCountMc1(3, 0) + calculateBetCountMc1(4, 0);
       			default:
       				return 0;
       		}
       	case 5 :
       		switch (latter) {
   			case 1:  // 5串1
   				return calculateBetCountMc1(5, 0);
   			case 5:  // 5串5
   				return calculateBetCountMc1(4, 0);
   			case 6:  // 5串6
   				return calculateBetCountMc1(4, 0)+ calculateBetCountMc1(5, 0);
   			case 10:  // 5串10
   				return calculateBetCountMc1(2, 0);
   			case 16:  // 5串16
   				return calculateBetCountMc1(3, 0) + calculateBetCountMc1(4, 0) + calculateBetCountMc1(5, 0);
   			case 20:  // 5串20
   				return calculateBetCountMc1(2, 0) + calculateBetCountMc1(3, 0);
   			case 26:  // 5串26
   				return calculateBetCountMc1(2, 0) + calculateBetCountMc1(3, 0) + calculateBetCountMc1(4, 0) + calculateBetCountMc1(5, 0);
   			default:
   				return 0;
   		}
       	case 6 :
       		switch (latter) {
   			case 1:  // 6串1
   				return calculateBetCountMc1(6, 0);
   			case 6:  // 6串6
   				return calculateBetCountMc1(5, 0);
   			case 7:  // 6串7
   				return calculateBetCountMc1(5, 0)+ calculateBetCountMc1(6, 0);
   			case 15:  // 6串15
   				return calculateBetCountMc1(2, 0);
   			case 20:  // 6串20
   				return calculateBetCountMc1(3, 0);
   			case 22:  // 6串22
   				return calculateBetCountMc1(4, 0) + calculateBetCountMc1(5, 0) + calculateBetCountMc1(6, 0);
   			case 35:  // 6串35
   				return calculateBetCountMc1(2, 0) + calculateBetCountMc1(3, 0);
   			case 42:  // 6串42
   				return calculateBetCountMc1(3, 0) + calculateBetCountMc1(4, 0) + calculateBetCountMc1(5, 0) + calculateBetCountMc1(6, 0);
   			case 50:  // 6串50
   				return calculateBetCountMc1(2, 0) + calculateBetCountMc1(3, 0) + calculateBetCountMc1(4, 0);
   			case 57:  // 6串57
   				return calculateBetCountMc1(2, 0) + calculateBetCountMc1(3, 0) + calculateBetCountMc1(4, 0) + calculateBetCountMc1(5, 0) + calculateBetCountMc1(6, 0);
   			default:
   				return 0;
   		}
       	case 7 :
       		switch (latter) {
   			case 1:  // 7串1
   				return calculateBetCountMc1(7, 0);
   			case 7:	// 7串7:返回7个六串1
   				return calculateBetCountMc1(6, 0);  				
   			case 8:	// 7串8：返回7个六串1 和 一个 7串1
   				return calculateBetCountMc1(6, 0) + calculateBetCountMc1(7, 0);  				
   			case 21:// 7串21：返回21个5串1
   				return calculateBetCountMc1(5, 0);  				
   			case 35:// 7串35：返回35个4串1
   				return calculateBetCountMc1(4, 0);  
   			case 120:// 7串120：返回1个7串1,7个6串1, 21个5串1,35个4串1，35个3串1，21个2串1.
   				return calculateBetCountMc1(7, 0) + calculateBetCountMc1(6, 0) + calculateBetCountMc1(5, 0) + calculateBetCountMc1(4, 0) + calculateBetCountMc1(3, 0) + calculateBetCountMc1(2, 0); 
   			default:
   				return 0;
   		}
       	case 8 :
       		switch (latter) {
   			case 1:  	// 8串1
   				return calculateBetCountMc1(8, 0);
   			case 8:		// 8串8:返回8个7串1
   				return calculateBetCountMc1(7, 0);  
   			case 9:		// 8串9：返回8个7串1 和 一个 8串1
   				return calculateBetCountMc1(7, 0) + calculateBetCountMc1(8, 0); 
   			case 28:	// 8串28：返回28个6串1
   				return calculateBetCountMc1(6, 0); 	
   			case 56:	// 8串56：返回56个5串1
   				return calculateBetCountMc1(5, 0); 	
   			case 70:	// 8串70：返回70个4串1
   				return calculateBetCountMc1(4, 0); 	
   			case 247:	// 8串247：返回1个8串1,8个7串1, 28个6串1,56个5串1，70个4串1，56个3串1，28个2串1.
   				return calculateBetCountMc1(8, 0) + calculateBetCountMc1(7, 0) + calculateBetCountMc1(6, 0) + calculateBetCountMc1(5, 0) + calculateBetCountMc1(4, 0) + calculateBetCountMc1(3, 0) + calculateBetCountMc1(2, 0); 
   			default:
   				return 0;
   		}
       	default:
   			alert("大赢家最大只支持\"8串n\"");
   			return 0;
    	}
}

/** 计算当前选择下 former串latter 的注数 */
function calculateBetCountMcNDan(former, latter)
{
	var sum = 0;
	for(var i=0; i<choosen_match_list.length; i++)
	{
		for(var j=1; j<=31; j++)
			sum += choosen_match_list[i][j];
	}
	return sum;
}

/** 计算当前选择下 former串latter 的注数 */
function calculateMaxBonusMcN(former, latter)
{
 	switch (former) {
 		case 1 :
       		var sum = 0;
       		for(var i=0; i<choosen_match_list.length; i++)
       		{
       			sum+=getMaxPeilv(i);
       		}	     	
       		return sum;
       	case 2 :
       		return calculateBonusMc1(2, 0);  // 2串1
       	case 3 :
       		if (1 == latter) // 3串1
       			return calculateBonusMc1(3, 0); 
       		else if (3 == latter)  // 3串3
       			return calculateBonusMc1(2, 0);
       		else  // 3串4
       			return calculateBonusMc1(2, 0)+calculateBonusMc1(3, 0);
       	case 4 :
       		switch (latter) {
       			case 1:  // 4串1
       				return calculateBonusMc1(4, 0);
       			case 4:  // 4串4
       				return calculateBonusMc1(3, 0);
       			case 5:  // 4串5
       				return calculateBonusMc1(3, 0)+ calculateBonusMc1(4, 0);
       			case 6:  // 4串6
       				return calculateBonusMc1(2, 0);
       			case 11:  // 4串11
       				return calculateBonusMc1(2, 0) + calculateBonusMc1(3, 0) + calculateBonusMc1(4, 0);
       			default:
       				return 0;
       		}
       	case 5 :
       		switch (latter) {
   			case 1:  // 5串1
   				return calculateBonusMc1(5, 0);
   			case 5:  // 5串5
   				return calculateBonusMc1(4, 0);
   			case 6:  // 5串6
   				return calculateBonusMc1(4, 0)+ calculateBonusMc1(5, 0);
   			case 10:  // 5串10
   				return calculateBonusMc1(2, 0);
   			case 16:  // 5串16
   				return calculateBonusMc1(3, 0) + calculateBonusMc1(4, 0) + calculateBonusMc1(5, 0);
   			case 20:  // 5串20
   				return calculateBonusMc1(2, 0) + calculateBonusMc1(3, 0);
   			case 26:  // 5串26
   				return calculateBonusMc1(2, 0) + calculateBonusMc1(3, 0) + calculateBonusMc1(4, 0) + calculateBonusMc1(5, 0);
   			default:
   				return 0;
   		}
       	case 6 :
       		switch (latter) {
   			case 1:  // 6串1
   				return calculateBonusMc1(6, 0);
   			case 6:  // 6串6
   				return calculateBonusMc1(5, 0);
   			case 7:  // 6串7
   				return calculateBonusMc1(5, 0)+ calculateBonusMc1(6, 0);
   			case 15:  // 6串15
   				return calculateBonusMc1(2, 0);
   			case 20:  // 6串20
   				return calculateBonusMc1(3, 0);
   			case 22:  // 6串22
   				return calculateBonusMc1(4, 0) + calculateBonusMc1(5, 0) + calculateBonusMc1(6, 0);
   			case 35:  // 6串35
   				return calculateBonusMc1(2, 0) + calculateBonusMc1(3, 0);
   			case 42:  // 6串42
   				return calculateBonusMc1(3, 0) + calculateBonusMc1(4, 0) + calculateBonusMc1(5, 0) + calculateBonusMc1(6, 0);
   			case 50:  // 6串50
   				return calculateBonusMc1(2, 0) + calculateBonusMc1(3, 0) + calculateBonusMc1(4, 0);
   			case 57:  // 6串57
   				return calculateBonusMc1(2, 0) + calculateBonusMc1(3, 0) + calculateBonusMc1(4, 0) + calculateBonusMc1(5, 0) + calculateBonusMc1(6, 0);
   			default:
   				return 0;
   		}
       	case 7 :
       		switch (latter) {
   			case 1:  // 7串1
   				return calculateBonusMc1(7, 0);
   			default:
   				return 0;
       	}
   		case 8 :
   	     	switch (latter) {
   	   		case 1:  // 8串1
   	   			return calculateBonusMc1(8, 0);
   			default:
   				return 0;
       	}
  		default:
   			alert("大赢家最大只支持\"8串n\"");
   			return 0;
   	}
}

function calculateMinBonusMcN(former, latter)
{
 	switch (former) {
 		case 1 :
       		var sum = 0;
       		for(var i=0; i<choosen_match_list.length; i++)
       		{
       			sum+=getMinPeilv(i);
       		}	       	
       		return sum;
       	case 2 :
       		return calculateMinBonusMc1(2, 0);  // 2串1
       	case 3 :
       		if (1 == latter) // 3串1
       			return calculateMinBonusMc1(3, 0); 
       		else if (3 == latter)  // 3串3
       			return calculateMinBonusMc1(2, 0);
       		else  // 3串4
       			return calculateMinBonusMc1(2, 0)+calculateMinBonusMc1(3, 0);
       	case 4 :
       		switch (latter) {
       			case 1:  // 4串1
       				return calculateMinBonusMc1(4, 0);
       			case 4:  // 4串4
       				return calculateMinBonusMc1(3, 0);
       			case 5:  // 4串5
       				return calculateMinBonusMc1(3, 0)+ calculateMinBonusMc1(4, 0);
       			case 6:  // 4串6
       				return calculateMinBonusMc1(2, 0);
       			case 11:  // 4串11
       				return calculateMinBonusMc1(2, 0) + calculateMinBonusMc1(3, 0) + calculateMinBonusMc1(4, 0);
       			default:
       				return 0;
       		}
       	case 5 :
       		switch (latter) {
   			case 1:  // 5串1
   				return calculateMinBonusMc1(5, 0);
   			case 5:  // 5串5
   				return calculateMinBonusMc1(4, 0);
   			case 6:  // 5串6
   				return calculateMinBonusMc1(4, 0)+ calculateMinBonusMc1(5, 0);
   			case 10:  // 5串10
   				return calculateMinBonusMc1(2, 0);
   			case 16:  // 5串16
   				return calculateMinBonusMc1(3, 0) + calculateMinBonusMc1(4, 0) + calculateMinBonusMc1(5, 0);
   			case 20:  // 5串20
   				return calculateMinBonusMc1(2, 0) + calculateMinBonusMc1(3, 0);
   			case 26:  // 5串26
   				return calculateMinBonusMc1(2, 0) + calculateMinBonusMc1(3, 0) + calculateMinBonusMc1(4, 0) + calculateMinBonusMc1(5, 0);
   			default:
   				return 0;
   		}
       	case 6 :
       		switch (latter) {
   			case 1:  // 6串1
   				return calculateMinBonusMc1(6, 0);
   			case 6:  // 6串6
   				return calculateMinBonusMc1(5, 0);
   			case 7:  // 6串7
   				return calculateMinBonusMc1(5, 0)+ calculateMinBonusMc1(6, 0);
   			case 15:  // 6串15
   				return calculateMinBonusMc1(2, 0);
   			case 20:  // 6串20
   				return calculateMinBonusMc1(3, 0);
   			case 22:  // 6串22
   				return calculateMinBonusMc1(4, 0) + calculateMinBonusMc1(5, 0) + calculateMinBonusMc1(6, 0);
   			case 35:  // 6串35
   				return calculateMinBonusMc1(2, 0) + calculateMinBonusMc1(3, 0);
   			case 42:  // 6串42
   				return calculateMinBonusMc1(3, 0) + calculateMinBonusMc1(4, 0) + calculateMinBonusMc1(5, 0) + calculateMinBonusMc1(6, 0);
   			case 50:  // 6串50
   				return calculateMinBonusMc1(2, 0) + calculateMinBonusMc1(3, 0) + calculateMinBonusMc1(4, 0);
   			case 57:  // 6串57
   				return calculateMinBonusMc1(2, 0) + calculateMinBonusMc1(3, 0) + calculateMinBonusMc1(4, 0) + calculateMinBonusMc1(5, 0) + calculateMinBonusMc1(6, 0);
   			default:
   				return 0;
   		}
       	case 7 :
       		switch (latter) {
   			case 1:  // 7串1
   				return calculateMinBonusMc1(7, 0);
   			default:
   				return 0;
       		}
   		case 8 :
   			switch (latter) {
   	   		case 1:  // 8串1
   	   			return calculateMinBonusMc1(8, 0);
   			default:
   				return 0;
       	}
   		default:
   			alert("大赢家最大只支持\"8串n\"");
   			return 0;
   	}
}

function calculateMaxBonusMcNDan(former, latter)
{	
	var sum = 0;
	for(var i=0; i<choosen_match_list.length; i++)
  	{
		sum+=parseFloat(getMaxPeilv(i));
 	}	
	return sum*2;    
}

function calculateMinBonusMcNDan(former, latter)
{
	var sum = 0;
	for(var i=0; i<choosen_match_list.length; i++)
  	{
		sum+=parseFloat(getMinPeilv(i));
  	}	
	return sum*2; 
}
/** 苏老师发明的组合生成算法 */
// para curPos:位置数组，0表示未选，1表示选中
function nextCombine(curPos, over)
{
	var len = curPos.length;
	var count = 0;
	for(var i=0; i<=len-2; i++)
	{
		if(curPos[i]==1)
		{
			if(curPos[i+1]==0)
			{
				curPos[i]=0;
				curPos[i+1]=1;
				for(var j=0; j<count; j++)
					curPos[j]=1;
				for(var j=count; j<i; j++)
					curPos[j]=0;
				break;
			}
			count++;
		}
	}
	if(i==len-1)
		over[0]=1; //结束
	else
		over[0]=0; //未结束
	return curPos;
}

/** 将 choosen_match_list 中所选投注添加到 g_plan, 玩法：m串n */
function addToPlan(m, n)
{
	//假如为 “多串多”，则酌情拆成若干个 “多串1”
	if(danguan_duoguan == 1)
	{
		addToPlanDan(m,n);
		return;
	}
	if ( n!=1)
	{
		switch (m) {
       	case 3 :
       		if (3 == n)  // 3串3
       			addToPlan(2, 1);
       		else  // 3串4
       		{
       			addToPlan(2, 1);
       			addToPlan(3, 1);
       		}
       		break;
       	case 4 :
       		switch (n) {
       			case 4:  // 4串4
       				addToPlan(3, 1);
       				break;
       			case 5:  // 4串5
       				addToPlan(3, 1);
       				addToPlan(4, 1);
       				break;
       			case 6:  // 4串6
       				addToPlan(2, 1);
       				break;
       			case 11:  // 4串11
       				addToPlan(2, 1);
       				addToPlan(3, 1);
       				addToPlan(4, 1);
       				break;
       			default:
       				alert("出错啦！请刷新页面！");
       				return;
       		}
       		break;
       	case 5 :
       		switch (n) {
   			case 5:  // 5串5
   				addToPlan(4, 1);
   				break;
   			case 6:  // 5串6
   				addToPlan(4, 1);
   				addToPlan(5, 1);
   				break;
   			case 10:  // 5串10
   				addToPlan(2, 1);
   				break;
   			case 16:  // 5串16
   				addToPlan(3, 1);
   				addToPlan(4, 1);
   				addToPlan(5, 1);
   				break;
   			case 20:  // 5串20
   				addToPlan(2, 1);
   				addToPlan(3, 1);
   				break;
   			case 26:  // 5串26
   				addToPlan(2, 1);
   				addToPlan(3, 1);
   				addToPlan(4, 1);
   				addToPlan(5, 1);
   				break;
   			default:
   				alert("出错啦！请刷新页面！");
   				return;
   			}
   			break;
       	case 6 :
       		switch (n) {
   			case 6:  // 6串6
   				addToPlan(5, 1);
   				break;
   			case 7:  // 6串7
   				addToPlan(5, 1);
   				addToPlan(6, 1);
   				break;
   			case 15:  // 6串15
   				addToPlan(2, 1);
   				break;
   			case 20:  // 6串20
   				addToPlan(3, 1);
   				break;
   			case 22:  // 6串22
   				addToPlan(4, 1);
   				addToPlan(5, 1);
   				addToPlan(6, 1);
   				break;
   			case 35:  // 6串35
   				addToPlan(2, 1);
   				addToPlan(3, 1);
   				break;
   			case 42:  // 6串42
   				addToPlan(3, 1);
   				addToPlan(4, 1);
   				addToPlan(5, 1);
   				addToPlan(6, 1);
   				break;
   			case 50:  // 6串50
   				addToPlan(2, 1);
   				addToPlan(3, 1);
   				addToPlan(4, 1);
   				break;
   			case 57:  // 6串57
   				addToPlan(2, 1);
   				addToPlan(3, 1);
   				addToPlan(4, 1);
   				addToPlan(5, 1);
   				addToPlan(6, 1);
   				break;
   			default:
   				alert("出错啦！请刷新页面！");
   				return;
   			}
   			break;
   		default:
   			alert("出错啦！请刷新页面！");
   			return;
   		}
   		return;
	}
	var pos = [];  //用来保存所选场次
	var isOver= [];  //是否结束
	isOver[0]=0;
	for(var i=0; i<m; i++)
		pos[i]=1;
	for(var i=m; i<choosen_match_list.length; i++)
		pos[i]=0;
	while(!isOver[0])
	{
		var type_code="";
		
		/** 第1段：前缀 */
		switch (g_playType)
		{
			case PLAY_TYPE_CSF:
				type_code += "CSF|";
				break;
			case PLAY_TYPE_RFSF:
				type_code += "RFSF|";
				break;
			case PLAY_TYPE_SFC:
				type_code += "SFC|";
				break;
			case PLAY_TYPE_DXF:
				type_code += "DXF|";
				break;
		}
		/** 第2段：具体投注内容 */
		for (var i=0; i<choosen_match_list.length; i++)
		{
			if(pos[i]==0)
				continue;
			
			type_code = type_code + choosen_match_list[i][0] + "=";
			for(var k=1; k<choosen_match_list[i].length; k++)
			{
				if(choosen_match_list[i][k]==1)
				{
					switch (g_playType)
					{
						case PLAY_TYPE_CSF:
							type_code += CSF_CODE[k];
							break;
						case PLAY_TYPE_RFSF:
							type_code += RFSF_CODE[k];
							break;
						case PLAY_TYPE_SFC:
							type_code += SFC_CODE[k];
							break;
						case PLAY_TYPE_DXF:
							type_code += DXF_CODE[k];
							break;
					}
					type_code += "/";
				}
			}
			type_code = type_code.substring(0, type_code.length-1);
			type_code += ",";
		}
		type_code = type_code.substring(0, type_code.length-1);
		type_code += "|";
		
		/** 第3段：m串n */
		type_code = type_code + m.toString() + "*" + n.toString();
		g_plan.addCode(type_code);
		pos = nextCombine(pos, isOver);
	}
}

/** 将 choosen_match_list 中所选投注添加到 g_plan, 玩法：m串n */
function addToPlanDan(m, n)
{	
	/** 第2段：具体投注内容 */
	for (var i=0; i<choosen_match_list.length; i++)
	{
		var type_code="";
			
		/** 第1段：前缀 */
		switch (g_playType)
		{
			case PLAY_TYPE_CSF:
				type_code += "CSF|";
				break;
			case PLAY_TYPE_RFSF:
				type_code += "RFSF|";
				break;
			case PLAY_TYPE_SFC:
				type_code += "SFC|";
				break;
			case PLAY_TYPE_DXF:
				type_code += "DXF|";
				break;
		}
		type_code = type_code + choosen_match_list[i][0] + "=";
		for(var k=1; k<choosen_match_list[i].length; k++)
		{
			if(choosen_match_list[i][k]==1)
			{
				switch (g_playType)
				{
					case PLAY_TYPE_CSF:
						type_code += CSF_CODE[k];
						break;
					case PLAY_TYPE_RFSF:
						type_code += RFSF_CODE[k];
						break;
					case PLAY_TYPE_SFC:
						type_code += SFC_CODE[k];
						break;
					case PLAY_TYPE_DXF:
						type_code += DXF_CODE[k];
						break;
				}
				type_code += "/";
			}			
		}
		type_code = type_code.substring(0, type_code.length-1);
		type_code += "|1*1";			
		g_plan.addCode(type_code);
	}	
}

function getMaxPeilv(q)
{
	var pl=0;
	var base;
	if(g_playType==PLAY_TYPE_CSF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=11;
		if(danguan_duoguan==PLAY_DUOGUAN) base=13;
	}
	else if(g_playType==PLAY_TYPE_RFSF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=15;
		if(danguan_duoguan==PLAY_DUOGUAN) base=17;
	}
	else if(g_playType==PLAY_TYPE_SFC)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=19;
		if(danguan_duoguan==PLAY_DUOGUAN) base=31;
	}
	else if(g_playType==PLAY_TYPE_DXF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=43;
		if(danguan_duoguan==PLAY_DUOGUAN) base=45;
	}
	
	for(var j=0; j<matchList.length; j++) {
		if(matchList[j][0]==choosen_match_list[q][0]) {
			if(g_playType==PLAY_TYPE_CSF || g_playType==PLAY_TYPE_RFSF)
			{
				for(var k=1;k<=2;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)<parseFloat(matchList[j][base+k-1]))
						{
							pl=matchList[j][base+k-1];
						}
					}
				}
			}
			else if(g_playType==PLAY_TYPE_SFC)
			{
				for(var k=1;k<=12;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)<parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			else if(g_playType==PLAY_TYPE_DXF)
			{
				for(var k=1;k<=2;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)<parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}			
			break;
		}
	}

	return pl;
}

function getMinPeilv(q)
{
	var pl=100000;
	var base;
	if(g_playType==PLAY_TYPE_CSF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=11;
		if(danguan_duoguan==PLAY_DUOGUAN) base=13;
	}
	else if(g_playType==PLAY_TYPE_RFSF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=15;
		if(danguan_duoguan==PLAY_DUOGUAN) base=17;
	}
	else if(g_playType==PLAY_TYPE_SFC)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=19;
		if(danguan_duoguan==PLAY_DUOGUAN) base=31;
	}
	else if(g_playType==PLAY_TYPE_DXF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=43;
		if(danguan_duoguan==PLAY_DUOGUAN) base=45;
	}
	
	for(var j=0; j<matchList.length; j++) {
		if(matchList[j][0]==choosen_match_list[q][0]) {
			if(g_playType==PLAY_TYPE_CSF || g_playType==PLAY_TYPE_RFSF)
			{
				for(var k=1;k<=2;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)>parseFloat(matchList[j][base+k-1]))
						{
							pl=matchList[j][base+k-1];
						}
					}
				}
			}
			else if(g_playType==PLAY_TYPE_SFC)
			{
				for(var k=1;k<=12;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)>parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			else if(g_playType==PLAY_TYPE_DXF)
			{
				for(var k=1;k<=2;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)>parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}			
			break;
		}
	}
	return pl;
}

function getPeilv(q,w)
{
	var pl=0;
	var base;
	
	if(g_playType==PLAY_TYPE_CSF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=11;
		if(danguan_duoguan==PLAY_DUOGUAN) base=13;
	}
	else if(g_playType==PLAY_TYPE_RFSF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=15;
		if(danguan_duoguan==PLAY_DUOGUAN) base=17;
	}
	else if(g_playType==PLAY_TYPE_SFC)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=19;
		if(danguan_duoguan==PLAY_DUOGUAN) base=31;
	}
	else if(g_playType==PLAY_TYPE_DXF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=43;
		if(danguan_duoguan==PLAY_DUOGUAN) base=45;
	}
	
	for(var j=0; j<matchList.length; j++) {
		if(matchList[j][0]==choosen_match_list[q][0]) {
			pl=matchList[j][base+w-1];
			break;
		}
	}

	return pl;
}

/** 计算当前选择下 m串1 的奖金数 */
function calculateBonusMc1(m, begin)
{
	var mul=2,maxpeilv=0,curpeilv=0,prepeilv=0;
	curpeilv=getMaxPeilv(begin);
	
	if(m==0)
	{		
		return 2;
	}
	else if(choosen_match_list.length-begin == m)
	{	
		for(var j=begin; j<choosen_match_list.length; j++)
		{		
			maxpeilv=getMaxPeilv(j);
			mul*=maxpeilv;
		}

		return mul;
	}
	
	return (curpeilv*calculateBonusMc1(m-1, begin+1) + calculateBonusMc1(m, begin+1));
}

/** 计算当前选择下 m串1 的奖金数 */
function calculateMinBonusMc1(m, begin)
{
	var mul=2,maxpeilv=0,curpeilv=0,prepeilv=0;
	
	curpeilv=getMinPeilv(begin);
	
	if(m==0)
	{	
		return 2;
	}
	else if(choosen_match_list.length-begin == m)
	{		
		for(var j=begin; j<choosen_match_list.length; j++)
		{
		
			maxpeilv=getMinPeilv(j);
			mul*=maxpeilv;
		}
		return mul;
	}
	
	return(curpeilv*calculateMinBonusMc1(m-1, begin+1) + calculateMinBonusMc1(m, begin+1));
}

/** 计算当前选择下 m串1 的注数 */
function calculateBetCountMc1(m, begin)
{
	if(m==0)
		return 1;
	else if(choosen_match_list.length-begin == m)
	{
		var mul=1;
		for(var j=begin; j<choosen_match_list.length; j++)
		{
			var betCount = 0;
			for(var k=1; k<=choosen_match_list[j].length; k++)
			{
				if(choosen_match_list[j][k]==1)
					betCount++;
			}
			mul*=betCount;
		}
		return mul;
	}
	var count = 0;
	for(var k=1; k<=choosen_match_list[begin].length; k++)
	{
		if(choosen_match_list[begin][k]==1)
			count++;
	}
	return count*calculateBetCountMc1(m-1, begin+1) + calculateBetCountMc1(m, begin+1);
}


/******************** 用户确认投注 ***************************/
function onSubmit()
{
	var betTotalNum = $("#totalBetCount").text();
	if(betTotalNum <= 0) {
		alert("请投注!");
		return;
	}
	
	/* 判断选择的投注中是否有截止的 (可以进一步优化代码，choosen_match_list可以只保存matchList中的比赛序号即可) */
	for (var i=0; i<choosen_match_list.length; i++) {
		for(var j=0; j<matchList.length; j++) {
			if(matchList[j][0]==choosen_match_list[i][0]) {
				if (matchList[j][StatusPos] == "0") {
					alert("您选择的比赛场次 "+choosen_match_list[i][0]+" 已经销售截止！");
					return;
				}
				break;
			}
		}
	}

	if(danguan_duoguan==2)
	{		
		/** 添加用户投注到 g_plan */
		for(var i=2; i<=8; i++)
		{
			var checked = $("#"+i+"c1").attr("checked");
			if (checked)
			{
				addToPlan(i, 1); // 添加i串1到g_plan
			}
		}
		if(curRadio)
		{
			addToPlan(parseInt(curRadio.id.split("c")[0]), parseInt(curRadio.id.split("c")[1]));
		}
	} 
	else
	{
		addToPlanDan(1, 1);
	}
	if(g_betType == BET_TYPE_PERSONAL) 
	{
		//just for testing, changed by songzs 2011.12.21
		if(g_plan.termStatus != "销售中") {
			alert("对不起，竞彩暂停销售，请刷新当前页面！");
			return;
		}
	
		var oneMoney = 2;
		g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
		Number($("#multiple").val()), "0", oneMoney);
		submitJCLQPlan(g_plan);	
	}
	else if(g_betType == BET_TYPE_GROUP) 
	{	
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
			submitJCLQPlan(g_plan);
		}		
	}
	else 
	{
		alert("玩法有错误！");
	}
}

var JCLQsubmiting = false;
function submitJCLQPlan(plan)
{
	if(checkiflogin()==false) 
	{
		$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
		return;
	}
	
	if(!JCLQsubmiting) {
		JCLQsubmiting = true;
	}
	else {
		alert("正在投注中，请等待...");
		return;
	}

	if(g_betType == BET_TYPE_PERSONAL) {
		if(confirm("您确定要投注？")) {
			$.post(plan.action, plan.toParams(), JCLQsubmitResponse);
		}
		else {
			JCLQsubmiting=false;
			return;
		}
	}
	else if(g_betType == BET_TYPE_GROUP) {
		if(confirm("您确定要发起合买？")) {
			$.post(plan.action, plan.toParams(), JCLQsubmitResponse);
		}
		else 
		{
			JCLQsubmiting=false;
			return;
		}
	}
}

function JCLQsubmitResponse(response, status)
{
	response = eval('(' + response + ')');

	JCLQsubmiting = false;
	g_plan.cleanCode();
	
	switch(response.status)
	{
	case "_0001":
	case "_10000":
		alert("投注失败");
		alert(response.message);
		break;
	case "_0000":
		if(g_betType == BET_TYPE_PERSONAL)
		{
			alert("恭喜您，投注成功！");
			location.reload();
		}
		else if(g_betType == BET_TYPE_GROUP)
		{
			alert("恭喜您，合买发起成功！");
			location.reload();
		}
		break;
	case "_00000":
		$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
		break;
	}
	if(needreload==2)
	{
		location.reload();
	}
}

function chooseTour()
{
    if($("#tournament_panel").css("display")=="none")
        $("#tournament_panel").show();
	else
		  $("#tournament_panel").hide();
}

function closeTour() 
{
	$("#tournament_panel").hide();
}

function sel(ll)
{
	selectedonly=false;
	
	for(i=0;i<matchList.length;i++)
	{
		if(matchList[i][1]==ll.value)
		{
			if(ll.checked) {
				matchList[i][SelectionMatchGamePos]="1";
			}
			else
			{
				if(matchList[i][SelectionPos]=="0")
					matchList[i][SelectionMatchGamePos]="0";
				
			}
		}
	}
	displayFilter();
}
function quanxuan() 
{
     $(":checkbox[id^='lg']").attr("checked","true");
     $("#chkConcede").attr("checked","true");
     $("#chkNoConcede").attr("checked","true");
     selectcon=true;
     selectnocon=true;
     selectedonly=false;
     for(i=0;i<matchList.length;i++)
 	{
    	 matchList[i][SelectionMatchGamePos]="1";
 	}
     displayFilter();
}

function fanxuan()
{
	$(":checkbox[id^='lg']").each(function(aa,bb){
        bb.checked=false
	});
	$("#chkConcede").attr("checked","true");
	$("#chkNoConcede").attr("checked","true");
	selectcon=true;
	selectnocon=true;
	selectedonly=false;
 	for(i=0;i<matchList.length;i++)
  	{
    	 if(matchList[i][SelectionPos]=="0")
				matchList[i][SelectionMatchGamePos]="0";
  	}
     displayFilter();
}

function zkbf(b)
{
	if($("#bifen"+b).css("display")=="none"){
		if($("#peilv1_"+b).text()=='0')
		{
			for(i=0;i<matchList.length;i++)
			{
				if(matchList[i][0]==b)
					break;
			}
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				for(var k=0;k<31;k++) 
				{
					$("#peilv"+(k+1)+"_"+b).text(matchList[i][66+k]);
				}
			} else
			{
				for(var k=0;k<31;k++)
				{
					$("#peilv"+(k+1)+"_"+b).text(matchList[i][35+k]);
				}
			}
		}
	}
	if($("#bifen"+b).css("display")=="none"){
		$("#bifen"+b).show();
		$("#m"+b).text("隐藏选项")
	}
	else
	{
		$("#bifen"+b).hide();
		$("#m"+b).text("展开选项")
	}
}
