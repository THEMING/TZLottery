var g_lotteryType = "jczq";
var danguan_duoguan =2;
var PLAY_TYPE_SPF = 1;
var PLAY_TYPE_CBF = 2;
var PLAY_TYPE_JQS = 3;
var PLAY_TYPE_BQC = 4;

var PLAY_DANGUAN =1;
var PLAY_DUOGUAN =2;

var panel = 1;   		// 串1/多之间的切换
var curRadio = null;	// 串多选项

var matchList = [];   // 对阵表      （字段：场次编号，主队，让球，客队，截止销售时间）
var anList = [];		//压盘数组
var choosen_match_list = []; // 投注详情列表 （字段：场次编号，胜，平，负）

var found = false;  // 二分查找的标志位

var hiddenMatch = 0;
var currentTotalBetCount = 0; // 当前投注总注数
var maxBonus =0;//理论最高奖金
var minBonus =0;

var g_betType = BET_TYPE_PERSONAL;

var selectcon = true;
var selectnocon = true;
var selectedonly = false;



/** 用于向后台封装投注字符串，注意与choosen_match_list中各字段保持对应 */
var SPF_CODE = ["","3","1","0"];
var JQS_CODE = ["","0","1","2","3","4","5","6","7"];
var CBF_CODE = ["","1:0","2:0","2:1","3:0","3:1","3:2","4:0","4:1","4:2","5:0","5:1","5:2","9:0",
				   "0:0","1:1","2:2","3:3","9:9",
				   "0:1","0:2","1:2","0:3","1:3","2:3","0:4","1:4","2:4","0:5","1:5","2:5","0:9"];
var BQC_CODE = ["","3-3","3-1","3-0","1-3","1-1","1-0","0-3","0-1","0-0"];

var g_plan;

var epalllist = []; //所有欧盘数据

var list = [];


function getepalllist()
{
	var newep;
	var legth = $("#eplast").val();
	var n = 0, sl = 0, pl = 0; fl = 0;
	epalllist = new Array();
	for(var i = 0; i <= parseInt(legth); i++)
	{
//	Math.round(arg);
		newep = new Array();
		newep[0] = $("#name_" + i).val();
		newep[1] = $("#s_" + i).val();
		newep[2] = $("#p_" + i).val();
		newep[3] = $("#f_" + i).val();
		n = parseFloat(newep[1])*parseFloat(newep[2]) + parseFloat(newep[1])*parseFloat(newep[3]) + parseFloat(newep[2])*parseFloat(newep[3]);
		sl = (parseFloat(newep[2])*parseFloat(newep[3])/n)*100;
		pl = (parseFloat(newep[1])*parseFloat(newep[3])/n)*100;
		fl = (parseFloat(newep[2])*parseFloat(newep[1])/n)*100;
		newep[4] = Math.round(sl);
		newep[5] = Math.round(pl);
		newep[6] = Math.round(fl);
		newep[7] = Math.round(parseFloat(newep[1])*parseFloat(newep[4]));
		newep[8] = $("#pk_" + i).val(); //盘口id
		newep[9] = "";
		newep[10] = "";
		newep[11] = "";
		newep[12] = "999999" + $("#id_" + i).val(); //公司id
		newep[13] = $("#time_" + i).val(); //时间
		epalllist[i] = newep;
	}
}

function ov(tips, e, s)
{
	$("#" + s).css("background-color","#ffcc99");
	showtips(tips, e, s);
}

function drawepqushi(list)
{
	var tips = "";
	var id = "tips_" + list[0][12];
	$("body").append("<div class='tips_tcp' id=" + id + " ></div>");
	tips += "<p class='overdivh'><b>" + list[0][0] + "</b>&nbsp;&nbsp;指数变化</p><table>";
	
	var str="<tr bgcolor='#EEEDE8' class='eptcp'  onmouseout='ot(\"tips_\"," + list[0][12] + ");' onmouseover='ov(\"tips_\", event," + list[0][12] + ")';' id='" + list[0][12] + "'>"
			+ "<td bgcolor='#EEEDE8' width='23%' scope='row'><a href='#' onfocus='this.blur();'> " + list[0][0] + "</a></td>"
			+ "<td>" 
			+ "<table width='100%' border='1' cellspacing='0' cellpadding='0' class='eptcp'>";
	var m = 0;
	for(var i = 0; i < list.length; i ++)
	{
		if(list.length > 1)
		{
			if(list[i][8] > list[m][8])
			{
				m = i;
			}
			if(i < list.length - 1)
			{
				if(list[i][1] > list[i + 1][1])
				{
					list[i][9] = "上升";
				}
				else if(list[i][1] < list[i + 1][1])
				{
					list[i][9] = "下降";	
				}
				
				if(list[i][2] > list[i + 1][2])
				{
					list[i][10] = "上升";
				}
				else if(list[i][2] < list[i + 1][2])
				{
					list[i][10] = "下降";	
				}
				
				if(list[i][3] > list[i + 1][3])
				{
					list[i][11] = "上升";
				}
				else if(list[i][3] < list[i + 1][3])
				{
					list[i][11] = "下降";	
				}
			}
		}
		tips += "<tr><td><span>" + list[i][13] + "</span>";
		if(list[i][9] == "上升")
		{
			tips += "<td><span class='shangjiantou' style='width: 60px'>" + list[i][1] + "</span></td>";
		}else if (list[i][9] == "下降")
		{	
			tips += "<td><span class='xiajiantou' style='width: 60px'>" + list[i][1] + "</span></td>";
		}else 
		{
			tips += "<td><span class='' style='width: 60px'>" + list[i][1] + "<em>&nbsp;&nbsp;&nbsp;&nbsp;→</em></span></td>";
		}
		
		if(list[i][10] == "上升")
		{
			tips += "<td><span class='shangjiantou' style='width: 60px'>" + list[i][2] + "</span></td>";
		}else if (list[i][10] == "下降")
		{	
			tips += "<td><span class='xiajiantou' style='width: 60px'>" + list[i][2] + "</span></td>";
		}else 
		{
			tips += "<td><span class='' style='width: 60px'>" + list[i][2] + "<em>&nbsp;&nbsp;&nbsp;&nbsp;→</em></span></td>";
		}
		
		if(list[i][11] == "上升")
		{
			tips += "<td><span class='shangjiantou' style='width: 60px'>" + list[i][3] + "</span></td>";
		}else if (list[i][11] == "下降")
		{	
			tips += "<td><span class='xiajiantou' style='width: 60px'>" + list[i][3] + "</span></td>";
		}else 
		{
			tips += "<td><span class='' style='width: 60px'>" + list[i][3] + "<em>&nbsp;&nbsp;&nbsp;&nbsp;→</em></span></td>";
		}
		
		tips += "</tr>";
	}
	tips += "</table>";	
	$("#" + id).append(tips);
	for(var i = 0; i < list.length; i ++)
	{	
		if(i == m)
		{
			str += "<tr>";
			if(list[i][9] == "上升")
			{
				str += "<th scope='col' class='shangjiantou'>"+list[i][1]+"</th>";
			}else if (list[i][9] == "下降")
			{	
				str += "<th scope='col' class='xiajiantou'>"+list[i][1]+"</th>";
			}else 
			{
				str += "<th scope='col'>"+list[i][1]+"</th>"
			}
			
			if(list[i][10] == "上升")
			{
				str += "<th scope='col' class='shangjiantou'>"+list[i][2]+"</th>";
			}else if (list[i][10] == "下降")
			{	
				str += "<th scope='col' class='xiajiantou'>"+list[i][2]+"</th>";
			}else 
			{
				str += "<th scope='col'>"+list[i][2]+"</th>"
			}
			
			if(list[i][11] == "上升")
			{
				str += "<th scope='col' class='shangjiantou'>"+list[i][3]+"</th>";
			}else if (list[i][11] == "下降")
			{	
				str += "<th scope='col' class='xiajiantou'>"+list[i][3]+"</th>";
			}else 
			{
				str += "<th scope='col'>"+list[i][3]+"</th>"
			}
			str += "<td scope='row' class='bordertop'>" + list[i][4] + "%</td>"
					+ 	"<td scope='row' class='bordertop'>" + list[i][5] + "%</td>"	
					+	"<td scope='row' class='bordertop'>" + list[i][6] + "%</td>"
					+	"<td scope='row' class='bordertop'>" + list[i][7] + "</td>"
					+	"<td scope='row' class='bordertop'>" + list[i][13] + "</td>"
					+ "</tr>";
		}
	}	
	str += "</table></td></tr>";
	$("#tcp123").append(str);

}

function getepqushi()
{	
	getepalllist();
	var companyName = epalllist[0][0];
	var j=0;
	list = new Array();
	for(var i = 0; i < epalllist.length; i ++){
		if(epalllist[i][0] == companyName){
			list[j] = epalllist[i];
			j++;
		}else {
			drawepqushi(list);
			companyName = epalllist[i][0];
			list = new Array();
			j=0;
			list[j] = epalllist[i];
			j++;
			
		}
		if(i == epalllist.length - 1)
		{
			drawepqushi(list);
		}
	}
}

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
		$("#chuan_one_list").hide();

	} else
	{

	}
	
}



/** 用JSON从服务器获取对阵列表 begin */
(function ($) {
	$.JCZQMatch = {
		url : "/lottery/JCZQMatch.htm?action=getMatchList",
		_request : function(param){
			$.getJSON($.JCZQMatch.url, param, $.JCZQMatch.callback);
		},
		callback : function(){}
	};
})(jQuery);

function getAnList(){
		var legth = $("#anlast").val();
		var newAnEle = new Array();
		for(var i = 0; i <= parseInt(legth);i++)
		{
			newAnEle= new Array();
			newAnEle[0] = $("#anCompany_"+i).val();  	      // 公司名字
			newAnEle[1] = $("#anSheng_"+i).val();			  // 胜
			newAnEle[2] = $("#anRang_"+i).val();    		 // 让
			newAnEle[3] = $("#anFu_"+i).val();    		 // 负
			newAnEle[4] = $("#anTime_"+i).val();			 // 变化时间	
			newAnEle[5] = $("#anPk_"+i).val();			//盘口id
			newAnEle[6] = ""; 
			newAnEle[7] = ""; 
			newAnEle[8] = $("#anId_"+i).val();			//公司id
			anList[i]= newAnEle;
		}
	
			
}



function ot(tips, s)
{
	$("#" + s).css("background-color","#EEEDE8");
	hidetips(tips, s);    	
}

//which = 0 
function rangshouqiu(str, which)
{
		var rang = "让";
		var shou = "受";
		var str1 = "-";
		var zero = "0";
		var pingshou = "平手"
		var bq = "0.5";
		var banqiu = "半球";
		var yq = "1";
		var yiqiu = "一球";
		var yqb = "1.5";
		var yiqiuban = "一球半";
		var lq = "2";
		var liangqiu = "两球";
		var lqb = "2.5";
		var liangqiuban = "两球半";
		var sq = "3";
		var sanqiu = "三球";
		var sqb = "3.5";
		var sanqiuban = "三球半";
		var siq = "4";
		var siqiu = "四球";
		var siqb = "4.5";
		var siqiuban = "四球半";
		var wq = "5";
		var wuqiu = "五球";
		var wqb = "5.5";
		var wuqiuban = "五球半";
if(which == 0)
{
	if(str.indexOf(bq)!=-1){
			str = str.replace(bq,banqiu);
		}
		if(str.indexOf(yq)!=-1&& str.indexOf(yqb)==-1){
			str = str.replace(yq,yiqiu);
		}
		if(str.indexOf(yqb)!=-1){
			str = str.replace(yqb,yiqiuban);
		}
		if(str.indexOf(lq)!=-1&&str.indexOf(lqb)==-1){
			str = str.replace(lq,liangqiu);
		}
		if(str.indexOf(lqb)!=-1){
			str = str.replace(lqb,liangqiuban);
		}
		if(str.indexOf(sq)!=-1&&str.indexOf(sqb)==-1){
			str = str.replace(sq,sanqiu);
		}
		if(str.indexOf(sqb)!=-1){
			str = str.replace(sqb,sanqiuban);
		}
		if(str.indexOf(siq)!=-1&&str.indexOf(siqb)==-1){
			str = str.replace(siq,siqiuban);
		}
		if(str.indexOf(siqb)!=-1){
			str = str.replace(siqb,siqiuban);
		}
		if(str.indexOf(wq)!=-1&&str.indexOf(wqb)==-1){
			str = str.replace(wq,wuqiu);
		}
		if(str.indexOf(wqb)!=-1){
			str = str.replace(wqb,wuqiuban);
		}
		if(str.indexOf(zero)!=-1&&str.indexOf(bq)==-1){
			str = str.substring(1);
			if(str.indexOf("0/") != -1)
			{
				str = str.split("/")[0] + "/" + str1 + str.split("/")[1];
				str = str.replace(zero,pingshou);
			}
			str = str.replace(zero,pingshou);
		}
		str = str.replace(str1,rang);
}
else
{
	if(str.indexOf(bq)!=-1){
			str = str.replace(bq,banqiu);
		}
		if(str.indexOf(yq)!=-1&& str.indexOf(yqb)==-1){
			str = str.replace(yq,yiqiu);
		}
		if(str.indexOf(yqb)!=-1){
			str = str.replace(yqb,yiqiuban);
		}
		if(str.indexOf(lq)!=-1&&str.indexOf(lqb)==-1){
			str = str.replace(lq,liangqiu);
		}
		if(str.indexOf(lqb)!=-1){
			str = str.replace(lqb,liangqiuban);
		}
		if(str.indexOf(sq)!=-1&&str.indexOf(sqb)==-1){
			str = str.replace(sq,sanqiu);
		}
		if(str.indexOf(sqb)!=-1){
			str = str.replace(sqb,sanqiuban);
		}
		if(str.indexOf(siq)!=-1&&str.indexOf(siqb)==-1){
			str = str.replace(siq,siqiuban);
		}
		if(str.indexOf(siqb)!=-1){
			str = str.replace(siqb,siqiuban);
		}
		if(str.indexOf(wq)!=-1&&str.indexOf(wqb)==-1){
			str = str.replace(wq,wuqiu);
		}
		if(str.indexOf(wqb)!=-1){
			str = str.replace(wqb,wuqiuban);
		}
		
		if(str.indexOf("0/")!=-1)
		{
			str = str.split("/")[0] + "/" + shou + str.split("/")[1];
			str = str.replace(zero,pingshou);
		}else if(str.equals("0")){
			str = str.replace(zero,pingshou);
		}else{
			str = shou + str;
			str = str.replace(zero,pingshou);
		}
		
}
	
	 return str;	
}

function drawqushi(list)
{
	var str ="";
	var tips = "";
	var id = "anTips_" + list[0][8];
	$("body").append("<div class='anTips_tcp' id=" + id + " ></div>");
	tips += "<p class='overdivh'><b>" + list[0][0] + "</b>&nbsp;&nbsp;指数变化</p><table border='0' cellspacing='0' cellpadding='0'>";
		
	str += "<tr bgcolor='#EEEDE8' class='eptcp' onmouseout='ot(\"anTips_\", " + list[0][8] + ");' onmouseover='ov(\"anTips_\", event," + list[0][8] + ");' id=" + list[0][8] + "><td width='220' scope='row'><a href='#' onfocus='this.blur();'> " + list[0][0] + "</a></td><td><table width='100%' border='1' cellspacing='0' cellpadding='0' class='eptcp'>";
	
	var m = 0;
	for(var i = 0; i < list.length; i ++)
	{
		if(list.length > 1){
				if(list[i][5] > list[m][5])
				{
					m = i;
				}
			if(i < list.length - 1)
			{
				if(list[i][1] > list[i + 1][1])
				{
					list[i][6] = "上升";
				}
				else if(list[i][1] < list[i + 1][1])
				{
					list[i][6] = "下降"; 
				}
		
				if(list[i][3] > list[i + 1][3])
				{
					list[i][7] = "上升";
				}
				else if(list[i][3] < list[i + 1][3])
				{
					list[i][7] = "下降"; 
				}
			}
			
		}
		if(list[i][6] == "上升")
		{
			tips += "<tr><td align='center' class='shangjiantou' style='width: 50px'>" + list[i][1] + "</td>";
		}else if (list[i][6] == "下降")
		{	
			tips += "<td align='center' class='xiajiantou' style='width: 50px'>&nbsp;" + list[i][1] + "</td>";
		}else 
		{
			tips += "<td align='center' class='' style='width: 50px'>&nbsp;" + list[i][1] + "<em>&nbsp;&nbsp;→</em></td>";
		}
		
		var str1 = "-";
		if(list[i][2].indexOf(str1)!=-1){
			list[i][2] = rangshouqiu(list[i][2], 0);
		}else{
			list[i][2] = rangshouqiu(list[i][2], 1);
		}


		tips += "<td align='center' style='width: 100px'>&nbsp;" + list[i][2] + "</td>";
		
		
		if(list[i][7] == "上升")
		{
			tips += "<td align='center' class='shangjiantou' style='width: 50px'>&nbsp;" + list[i][3] + "</td>";
		}else if (list[i][7] == "下降")
		{	 
			tips += "<td align='center' class='xiajiantou' style='width: 50px'>&nbsp;" + list[i][3] + "</td>";
		}else 
		{
			tips += "<td align='center' class='' style='width: 50px'>&nbsp;" + list[i][3] + "<em>&nbsp;&nbsp;→</em></td>"
		}
		tips += "<td align='center' width='182' class='bordertop'>" 
		+ list[i][4]+"</td>"
		+ "</tr>";

	}
	tips += "</table>";	
	$("#" + id).append(tips);
	for(var i = 0; i < list.length; i ++)
	{
		if(i == m){
			
			if(list[i][6] == "上升")
			{
				str += "<th width='182' scope='col' class='shangjiantou'>"+list[i][1]+"</th>";
			}else if (list[i][6] == "下降")
			{	
				str += "<th width='182' scope='col' class='xiajiantou'>"+list[i][1]+"</th>";
			}else 
			{
				str += "<th width='182' scope='col'>"+list[i][1]+"</th>"
			}
			
			str += "</td><td width='182' scope='col'>"+list[i][2]+"</td>"
			
			if(list[i][7] == "上升")
			{
				str += "<th width='182' scope='col' class='shangjiantou'>"+list[i][3]+"</th>";
			}else if (list[i][7] == "下降")
			{	
				str += "<th width='182' scope='col' class='xiajiantou'>"+list[i][3]+"</th>";
			}else 
			{
				str += "<th width='182' scope='col'>"+list[i][3]+"</th>"
			}
			str += "<td width='182' scope='row' class='bordertop'>" 
			+ list[i][4]+"</td>"
			+ "</tr>";
			$("#annewlast").append(str);
		}
		
	}
}

function generatequshi()
{

getAnList();
var companyid=anList[0][0];
var list = [];			//创建一个list
var j = 0;
list = new Array;
 for(var i = 0;i<anList.length;i++)
 {
 	if(anList[i][0]==companyid)
 	{
 		list[j] = anList[i];			//把获取的东西放到clist里
 		j++;
 	} else
 	{
 		drawqushi(list);				//调用drawqushi方法
 		companyid=anList[i][0];
 		list = new Array;
 		j = 0;			
 		list[j] = anList[i];
 		j++;
 	}
 	
 }
}



/** 获取对阵详细信息到js （下一步工作是将jsp页面中的对阵数据用js实现） */
function getMatchList()
{
	$.JCZQMatch.callback = function(json) {
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
			newEle[8] = eval(json)[i]["pop"]; 			// 平欧赔
			newEle[9] = eval(json)[i]["fop"]; 			// 负欧赔
			newEle[10] = eval(json)[i]["stzb"]; 		// 胜投注比
			newEle[11] = eval(json)[i]["ptzb"]; 		// 平投注比
			newEle[12] = eval(json)[i]["ftzb"]; 		// 负投注比
			

			var ratios = eval(json)[i]["currentRatios"].split("|");
			
			
			var plRatios; 
	
			
			if(g_playType==PLAY_TYPE_SPF)
			{
				if(ratios[0]==undefined)
				{
					for(var kk=0;kk<6;kk++) newEle[13+kk]="0";
				} else
				{
					plRatios= ratios[0].split(";");
					newEle[13] = plRatios[0].split(",")[0];
					newEle[14] = plRatios[0].split(",")[1];
					newEle[15] = plRatios[0].split(",")[2];
				
					// 胜平负即时赔率-多关
					newEle[16] = plRatios[1].split(",")[0];
					newEle[17] = plRatios[1].split(",")[1];
					newEle[18] = plRatios[1].split(",")[2];
				}
			}
			
			if(g_playType==PLAY_TYPE_JQS)
			{
				if(ratios[1]==undefined) 
				{
					for (var k= 0; k<8; k++)
						newEle[19+k] = "0";
					for (var k= 0; k<8; k++)
						newEle[27+k] = "0";
				} else
				{
					plRatios= ratios[1].split(";");
				
					for (var k= 0; k<8; k++)
						newEle[19+k] = plRatios[0].split(",")[k];
					for (var k= 0; k<8; k++)
						newEle[27+k] = plRatios[1].split(",")[k];
				}
			}
			
			if(g_playType==PLAY_TYPE_CBF)
			{
				if(ratios[2]==undefined) 
				{
					for (var k= 0; k<31; k++)
						newEle[35+k] = "0";
			
					// 猜比分即时赔率-多关
					for (var k= 0; k<31; k++)
						newEle[66+k] = "0";
				} else
				{
					plRatios= ratios[2].split(";");
					for (var k= 0; k<31; k++)
							newEle[35+k] = plRatios[0].split(",")[k];
				
					// 猜比分即时赔率-多关
					for (var k= 0; k<31; k++)
						newEle[66+k] = plRatios[1].split(",")[k];
				}
			}
			
			if(g_playType==PLAY_TYPE_BQC)
			{
				if(ratios[3]==undefined) 
				{
					for (var k= 0; k<9; k++)
						newEle[97+k] = "0";
				
					//半全场即时赔率-多关
					for (var k= 0; k<9; k++)
						newEle[106+k] = "0";
				} else
				{
					plRatios= ratios[3].split(";");
					//半全场即时赔率-单场
					for (var k= 0; k<9; k++)
						newEle[97+k] = plRatios[0].split(",")[k];
				
					//半全场即时赔率-多关
					for (var k= 0; k<9; k++)
						newEle[106+k] = plRatios[1].split(",")[k];
				}
			}
			
			
			// 状态位：销售中
			newEle[120] = "1"; 
			newEle[121] = "1"; //显示联赛选中位
			newEle[122] = "0"; //是否选择
			matchList[i]= newEle;
		}
		
		/* 设置定时任务 */
		for(var i=0; i< matchList.length; i++)
		{
			var delay = parseInt(matchList[i][3])*1000;
			setTimeout("performStopTask("+i+")", delay);
			$("#jiezhi_"+matchList[i][0]).countdown({until:delay/1000, compact: true, format: 'DHM'});
		}
		calculateConcede();
		refreshpeilv();

	};
	$.JCZQMatch._request({});
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

			if(matchList[i][122]=="1")
			{

				$("#line_" + matchList[i][0]).attr("class", "show");
			} else
			{
					$("#line_" + matchList[i][0]).attr("class", "hidden");
					if(g_playType==PLAY_TYPE_CBF)
					{
						$("#bifen" + matchList[i][0]).hide();
					}
				
			}
			continue;
		}
		
		if(matchList[i][121]=="1") {

	
			if(matchList[i][5] == undefined)
			{

				if(selectnocon==true)
				{

					$("#line_" + matchList[i][0]).attr("class", "show");
				} else
				{
					if(matchList[i][122]=="0")
					{
						$("#line_" + matchList[i][0]).attr("class", "hidden");
						if(g_playType==PLAY_TYPE_CBF)
						{
							$("#bifen" + matchList[i][0]).hide();
						}
					}
				}
			} else
			{
				if(selectcon==true)
				{
					$("#line_" + matchList[i][0]).attr("class", "show");
				} else
				{
					if(matchList[i][122]=="0")
					{
						$("#line_" + matchList[i][0]).attr("class", "hidden");
						if(g_playType==PLAY_TYPE_CBF)
						{
							$("#bifen" + matchList[i][0]).hide();
						}
					}
				}
			}
		}
		else{
			if(matchList[i][122]=="0")
			{
				$("#line_" + matchList[i][0]).attr("class", "hidden");
				if(g_playType==PLAY_TYPE_CBF)
				{
					$("#bifen" + matchList[i][0]).hide();
				}
			}
		}

	}
}

/** 到期后停止销售对阵表中第index场比赛 */
function performStopTask(index)
{	
	matchList[index][120]="0"; 
}

function refreshpeilv()
{

	for(var i=0; i<matchList.length; i++)
	{
		if(g_playType==PLAY_TYPE_SPF)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{

				$("#speilv_"+matchList[i][0]).text(matchList[i][16]);
				$("#ppeilv_"+matchList[i][0]).text(matchList[i][17]);
				$("#fpeilv_"+matchList[i][0]).text(matchList[i][18]);
			} else
			{
				$("#speilv_"+matchList[i][0]).text(matchList[i][13]);
				$("#ppeilv_"+matchList[i][0]).text(matchList[i][14]);
				$("#fpeilv_"+matchList[i][0]).text(matchList[i][15]);
			}
		}

		if(g_playType==PLAY_TYPE_CBF)
		{


		}
		
		if(g_playType==PLAY_TYPE_JQS)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				for(var j=0;j<8;j++)
				{
					$("#jqs"+j+"_"+matchList[i][0]).text(matchList[i][27+j]);
				}
			} else
			{
				for(var j=0;j<8;j++)
				{
					$("#jqs"+j+"_"+matchList[i][0]).text(matchList[i][19+j]);
				}
			}
		}
		if(g_playType==PLAY_TYPE_BQC)
		{
			if(danguan_duoguan== PLAY_DUOGUAN)
			{
				for(var j=0;j<9;j++)
				{
					$("#bqc"+j+"_"+matchList[i][0]).text(matchList[i][106+j]);
				}
			} else
			{
				for(var j=0;j<9;j++)
				{
					$("#bqc"+j+"_"+matchList[i][0]).text(matchList[i][97+j]);
				}	
			}
		}
		
	}

}

function isSelected(match)
{	
	for(var i = 0; i < choosen_match_list.length; i++) 
	{	
		if(choosen_match_list[i][0] == match) {
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

/** 选择胜平负 */
function clickChkBet(target,matchNo,index)
{
	if(target.checked)
	{
		addToList(matchNo, index);
	}
	else
		deleteFromList(matchNo, index);


	// show and hide "chuan"
	if(danguan_duoguan==PLAY_DUOGUAN)
	{
		var maxallowchosegames = choosen_match_list.length;
		if(g_playType==PLAY_TYPE_SPF)
		{
			if(maxallowchosegames >= 8)	maxallowchosegames = 8;
		}
		else
		{
			if(maxallowchosegames >= 4)	maxallowchosegames = 4;
		}

	if (panel == 1)
	{
		//changed by songzs, add: 胜平负：8串1，其它4串1.
		
		
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
	} else
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
	if(g_playType==PLAY_TYPE_SPF)
	{
		if(maxallowchosegames >= 8)	maxallowchosegames = 8;
	}
	else
	{
		if(maxallowchosegames >= 4)	maxallowchosegames = 4;
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
	if(g_playType==PLAY_TYPE_SPF)
	{
		if(maxallowchosegames >= 8)	maxallowchosegames = 8;
	}
	else
	{
		if(maxallowchosegames >= 4)	maxallowchosegames = 4;
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
	
	onJCZQMultipleChange();
	
	if(g_betType == BET_TYPE_GROUP)
		calAndDisplayGroup();
}

/** 查找，若不存在则将所选比赛加入列表 */
function addToList(matchNum, ind)
{
	if(choosen_match_list.length == 0) // 列表为空的情况
	{
		var newEle = new Array();
		newEle[0] = matchNum;
		for(var k=0;k<31;k++)
			newEle[k+1] = 0;
		newEle[ind] = 1;
		choosen_match_list[0]= newEle;
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

			matchList[i][122]="1";
			break;
		}
	}
}
/** 查找，若存在则将所选比赛加入列表 */
function deleteFromList(matchNum, spf)
{
	var selected;
	
	for(var i = 0; i < choosen_match_list.length; i++) 
	{
		if(choosen_match_list[i][0] == matchNum) 
		{
			choosen_match_list[i][spf] = 0;
			selected=false;
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
						matchList[j][122]="0";
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
function onJCZQMultipleChange()
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

       		return sum;
      
}


function calculateMinBonusMcNDan(former, latter)
{
 	
       		var sum = 0;
       	
       			for(var i=0; i<choosen_match_list.length; i++)
       			{
       				sum+=parseFloat(getMinPeilv(i));
       			}	

       		return sum;
      
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

	if ( n!=1 )
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
			case PLAY_TYPE_SPF:
				type_code += "SPF|";
				break;
			case PLAY_TYPE_JQS:
				type_code += "JQS|";
				break;
			case PLAY_TYPE_CBF:
				type_code += "CBF|";
				break;
			case PLAY_TYPE_BQC:
				type_code += "BQC|";
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
						case PLAY_TYPE_SPF:
							type_code += SPF_CODE[k];
							break;
						case PLAY_TYPE_JQS:
							type_code += JQS_CODE[k];
							break;
						case PLAY_TYPE_CBF:
							type_code += CBF_CODE[k];
							break;
						case PLAY_TYPE_BQC:
							type_code += BQC_CODE[k];
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
				case PLAY_TYPE_SPF:
					type_code += "SPF|";
					break;
				case PLAY_TYPE_JQS:
					type_code += "JQS|";
					break;
				case PLAY_TYPE_CBF:
					type_code += "CBF|";
					break;
				case PLAY_TYPE_BQC:
					type_code += "BQC|";
					break;
			}
			type_code = type_code + choosen_match_list[i][0] + "=";
			for(var k=1; k<choosen_match_list[i].length; k++)
			{
				if(choosen_match_list[i][k]==1)
				{
					switch (g_playType)
					{
						case PLAY_TYPE_SPF:
							type_code += SPF_CODE[k];
							break;
						case PLAY_TYPE_JQS:
							type_code += JQS_CODE[k];
							break;
						case PLAY_TYPE_CBF:
							type_code += CBF_CODE[k];
							break;
						case PLAY_TYPE_BQC:
							type_code += BQC_CODE[k];
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
	if(g_playType==PLAY_TYPE_SPF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=13;
		if(danguan_duoguan==PLAY_DUOGUAN) base=16;
	}
	if(g_playType==PLAY_TYPE_JQS)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=19;
		if(danguan_duoguan==PLAY_DUOGUAN) base=27;
	}
	
	if(g_playType==PLAY_TYPE_CBF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=35;
		if(danguan_duoguan==PLAY_DUOGUAN) base=66;
	}
	if(g_playType==PLAY_TYPE_BQC)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=97;
		if(danguan_duoguan==PLAY_DUOGUAN) base=106;
	}
	
	for(var j=0; j<matchList.length; j++) {
		if(matchList[j][0]==choosen_match_list[q][0]) {
			if(g_playType==PLAY_TYPE_SPF)
			{
				for(var k=1;k<=3;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)<parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			
			if(g_playType==PLAY_TYPE_JQS)
			{
				for(var k=1;k<=8;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)<parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			
			if(g_playType==PLAY_TYPE_CBF)
			{
				for(var k=1;k<=31;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)<parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			
			if(g_playType==PLAY_TYPE_BQC)
			{
				for(var k=1;k<=9;k++)
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
	if(g_playType==PLAY_TYPE_SPF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=13;
		if(danguan_duoguan==PLAY_DUOGUAN) base=16;
	}
	if(g_playType==PLAY_TYPE_JQS)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=19;
		if(danguan_duoguan==PLAY_DUOGUAN) base=27;
	}
	
	if(g_playType==PLAY_TYPE_CBF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=35;
		if(danguan_duoguan==PLAY_DUOGUAN) base=66;
	}
	if(g_playType==PLAY_TYPE_BQC)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=97;
		if(danguan_duoguan==PLAY_DUOGUAN) base=106;
	}
	
	for(var j=0; j<matchList.length; j++) {
		if(matchList[j][0]==choosen_match_list[q][0]) {
			if(g_playType==PLAY_TYPE_SPF)
			{
				for(var k=1;k<=3;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)>parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			
			if(g_playType==PLAY_TYPE_JQS)
			{
				for(var k=1;k<=8;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)>parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			
			if(g_playType==PLAY_TYPE_CBF)
			{
				for(var k=1;k<=31;k++)
				{
					if(choosen_match_list[q][k]==1)
					{
						if(parseFloat(pl)>parseFloat(matchList[j][base+k-1])) pl=matchList[j][base+k-1];
					}
				}
			}
			
			if(g_playType==PLAY_TYPE_BQC)
			{
				for(var k=1;k<=9;k++)
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
	
	if(g_playType==PLAY_TYPE_SPF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=13;
		if(danguan_duoguan==PLAY_DUOGUAN) base=16;
	}
	if(g_playType==PLAY_TYPE_JQS)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=19;
		if(danguan_duoguan==PLAY_DUOGUAN) base=27;
	}
	
	if(g_playType==PLAY_TYPE_CBF)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=35;
		if(danguan_duoguan==PLAY_DUOGUAN) base=66;
	}
	
	if(g_playType==PLAY_TYPE_BQC)
	{
		if(danguan_duoguan==PLAY_DANGUAN) base=97;
		if(danguan_duoguan==PLAY_DUOGUAN) base=106;
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
				if (matchList[j][120] == "0") {
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
	} else
	{
		addToPlanDan(1, 1);
	}
	
	if(g_betType == BET_TYPE_PERSONAL) 
	{
		if(g_plan.termStatus != "销售中") {
			alert("对不起，竞彩暂停销售，请刷新当前页面！");
			return;
		}

		var oneMoney = 2;
		g_plan.setPlan(g_plan.term, g_lotteryType, Number($("#totalBetMoney").text()), 
		Number($("#multiple").val()), "0", oneMoney);
		submitJCZQPlan(g_plan);	
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
			submitJCZQPlan(g_plan);
		}		
	}
	else 
	{
		alert("玩法有错误！");
	}
}

var JCZQsubmiting = false;
function submitJCZQPlan(plan)
{
	if(checkiflogin()==false) 
	{

		$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
		return;
	}
	
	if(!JCZQsubmiting) {
		JCZQsubmiting = true;
	}
	else {
		alert("正在投注中，请等待...");
		return;
	}

	if(g_betType == BET_TYPE_PERSONAL) {
		if(confirm("您确定要投注？")) {
			$.post(plan.action, plan.toParams(), JCZQsubmitResponse);
		}
		else {
			JCZQsubmiting=false;
			return;
		}
	}
	else if(g_betType == BET_TYPE_GROUP) {
		if(confirm("您确定要发起合买？")) {
			$.post(plan.action, plan.toParams(), JCZQsubmitResponse);
		}
		else {
			JCZQsubmiting=false;
			return;
		}
	}
}

function JCZQsubmitResponse(response, status)
{

	response = eval('(' + response + ')');

	JCZQsubmiting = false;
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

function chooseTour(){
    if($("#tournament_panel").css("display")=="none")
        $("#tournament_panel").show();
		else
		  $("#tournament_panel").hide();
		}

function closeTour() {
$("#tournament_panel").hide();
}
function sel(ll){

	selectedonly=false;
	
	for(i=0;i<matchList.length;i++)
	{
		if(matchList[i][1]==ll.value)
		{
			if(ll.checked) {
				matchList[i][121]="1";
			}
			else
			{
				if(matchList[i][122]=="0")
					matchList[i][121]="0";
				
			}
		}
	}

	displayFilter();
}
function quanxuan() {
	
     $(":checkbox[id^='lg']").attr("checked","true");
     $("#chkConcede").attr("checked","true");
     $("#chkNoConcede").attr("checked","true");
     selectcon=true;
     selectnocon=true;
     selectedonly=false;
     for(i=0;i<matchList.length;i++)
 	{
    	 matchList[i][121]="1";
 	}
     displayFilter();
}
function fanxuan() {
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
    	 if(matchList[i][122]=="0")
				matchList[i][121]="0";
  	}
     displayFilter();
}


function showtips(tips, e, id)
{
	id = tips + id;
	
	$("#" + id).show();

	var coordinates={"x":0,"y":0};
	if(e.pageX||e.pageY)
	{
		coordinates.x=e.pageX;
		coordinates.y=e.pageY;
	} else
	{
		coordinates.x=e.clientX+document.body.scrollLeft-document.body.clientLeft;
		coordinates.y=e.clientY+document.documentElement.scrollTop;
	}

	$("#" + id).css({"position":"absolute","left":coordinates.x+"px","top":coordinates.y+"px","background-color": "white"});
	if(window.event)
	{
		e.cancelBubble=true;
	}
	return false;

}

function hidetips(tips, id)
{
	if(window.event)
	{
		e=window.event;
		e.cancelBubble=true;
	}
	id = tips + id;
	$("#" + id).hide(); 
	return false;

}



function zkbf(b){
	
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
				for(var k=0;k<31;k++) {
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
	else{
	  $("#bifen"+b).hide();
	  $("#m"+b).text("展开选项")}
	}
