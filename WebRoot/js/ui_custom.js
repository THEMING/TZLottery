/*通用函数定义，全局页面调用*/

//全屏弹窗
function showPOP(div){//显示全屏弹窗
	var obj = $("#" + div);
	var POPWidth = obj.width();
	var POPHeight = obj.height();
	var windowHeight;
	if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0"){
		windowHeight = $(document).height()-4;
	}else{
		windowHeight = $(document).height();
    }
	$("body").prepend("<div id='mask' style='position:absolute; z-index:10; left:0; top:0; width:100%; height:" + windowHeight + "px; background:#000; opacity:0.3; filter:alpha(opacity=30)'></div>");
	$("select:not(.noHideSelect)").css("visibility","hidden");

//	$(".POPopacity").css({"background":"#000000","opacity":"0.3"});
	/*
	var screenHeight = document.documentElement.clientHeight;
	if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0"){
	obj.css({ "display": "block", "marginLeft": -POPWidth / 2, "marginTop": (screenHeight - POPHeight) / 2 });
	}else{*/
		obj.css({ "display": "block", "marginLeft": -POPWidth / 2, "marginTop": -POPHeight / 2 });
/*	}*/
};
function hidePOP(div){//隐藏全屏弹窗
	$("select").css("visibility","visible");
	if(div){
		$("#"+div).hide();
	}else{
		$("div.customPOP:visible, div.customTIP:visible").hide();
	}
	if($("div#mask").length>0){
		$("div#mask").remove();
	}
};

function hidePOP_Div() {//隐藏全屏弹窗
    $("select").css("visibility", "visible");
    $("div.customPOP:visible, div.customTIP:visible").hide();
    if ($("#mask").length > 0) {
        $("#mask").remove();
    }
};

//弹出会自动关闭的提示框
function POP_autoClose(div,n){
	hidePOP();
	var obj = $("#" + div);
	obj.fadeIn("fast");
	$(".POPopacity").fadeTo(300,0.3);
	if(n){
		setTimeout("hidePOP()",n);
	}
}

//右下角漂浮窗
function showTIP(obj,div){//弹出漂浮窗
	var toolBarDirWidth = $("#toolBarDir").width();
	if(toolBarDirWidth>0){
		$("#toolBarDir").animate({"width":"0","left":"0"},200);
		setTimeout('$("#toolBarDir").hide()',200);
	}

	if(!$(obj).hasClass("current")){
		$(".TBcontent:visible").hide();
		$("#"+div).slideDown(200);
		$(".toolbarbottom ul").css("backgroundPosition","0 -635px");
		if($(".toolbarbottom ul li.current").length>0){
			$(".toolbarbottom ul li.current").removeClass("current");
		}
		if(!$(obj).attr("type")){
			$(obj).addClass("current");
		}
		if(div == "betting"){
			$("#bettingTitle").show();
		}else{
			$("#bettingTitle").hide();
		}
	}
}
function closeTIP(div){//关闭漂浮窗
	$(".TBcontent:visible").slideUp(200);
	setTimeout("$('.toolbarbottom ul li.current').removeClass('current');$('.toolbarbottom ul').css('backgroundPosition','0 -636px');",200);
	if(div){
		setTimeout("$('#bettingTitle').hide()",200);
	}
}

//右下角漂浮窗_导航
function showToolBarDir(){
    closeTIP();
	var toolBarDirWidth = parseInt($("#toolBarDir").width());
	if(toolBarDirWidth>18){
	    $("#toolBarDir").animate({ "width": "18px", "left": "-18px" }, 200);
	    $("#toolBarDir ul h3").css("backgroundPosition", "-20px -71px");
		//setTimeout('$("#toolBarDir").hide()',200);
	}else{
        $("#toolBarDir").animate({ "width": "185px", "left": "-184px"}, 300);
        $("#toolBarDir ul h3").css("backgroundPosition", "0 -71px");
    }
/*	if($("#bettingTitle").css("dispaly")!=="none"){
		$("#bettingTitle").hide();
	}*/
}

function addNum(input){
	var obj = $("#"+input)
	obj.val(parseInt(obj.val())+1);
}
function lessNum(input){
	var obj = $("#"+input)
	if(parseInt(obj.val())>1){
		obj.val(parseInt(obj.val())-1);
	}
}

//选项卡
function tabToggle(obj,n,classOn,div){
	if(!$(obj).hasClass(classOn)){
		$(obj).addClass(classOn).siblings().removeClass(classOn);
		$("."+div+":eq("+n+")").show().siblings("."+div).hide();
	}
}
//鼠标经过选项卡
 function tabChangelink(obj, id) {
			var tabLi = obj.parentNode.getElementsByTagName("a");
			var tabContents = document.getElementById(id).getElementsByTagName("ul");
			for (var i = 0; i < tabContents.length; i++) {
				if (obj == tabLi[i]) {
					tabLi[i].className = "libg";
					tabContents[i].className = "";
				}
				else {
					tabLi[i].className = "";
					tabContents[i].className = "none";
				}
			}
 }               



//清除文本框默认值
function inputAutoClear(obj){
    obj.onfocus=function()
    {this.style.color='#000000';if(this.value==this.defaultValue){this.value='';}};
    obj.onblur=function()
    {this.style.color='#999999';if(this.value==''){this.value=this.defaultValue;}};
    obj.onfocus();
}

//用户中心菜单伸缩
function userMenu(obj) {
    var slideContent = $(obj).next("p");
    if (slideContent.css("display") == "none") {
        slideContent.slideDown();
        $(obj).children("span").addClass("slideDown");

        getValue("/UsersCenter/RecordLeft.aspx?c=" + obj.id + "&r=1");
    } else {
        slideContent.slideUp();
        $(obj).children("span").removeClass("slideDown");

        getValue("/UsersCenter/RecordLeft.aspx?c=" + obj.id + "&r=0");
    };
}

//滚动表格体tbody
;(function($){
    $.fn.tzgrid = function(options){
        var defualts = {
				width:"730px",
				height:"500px",
				scrolling:"scroll"
			};
        var opts = $.extend({}, defualts, options);
        var obj = $(this);
		if(parseInt(obj.height()) > parseInt(opts.height)){
			obj.children("thead").children("tr").children("th").each(function(){
				$(this).width($(this).width());
			})
			obj.wrap("<div id='tzgridOuter' style='position:relative;'><div id='tzgridInner'></div></div>");
			newObj = obj.clone().prependTo($("#tzgridOuter"));
			newObj.css({"position":"absolute","left":"0","top":"0","zIndex":"2"}).children("tbody").remove();
			newObj.attr("width",opts.width);
			if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0"){
				var innerWidth = parseInt(opts.width);
			}else{
				var innerWidth = parseInt(opts.width)+17;
			}
			$("#tzgridInner").children("table").attr("width",opts.width);
			$("#tzgridOuter").width(opts.width).height(opts.height);
			$("#tzgridInner").css({
				"position":"absolute",
				"left":"0",
				"top":"0",
				"zIndex":"1",
				"width":innerWidth,
				"height":opts.height,
				"overflow-y":opts.scrolling
			});
		}
		obj.children("tbody").children("tr").hover(function(){
			$(this).css("background","#FFF");
		},function(){
			$(this).css("background","none");
		});
    };
})(jQuery);

//鼠标经过表格时背景变色
;(function($){
    $.fn.changeBg = function(options){
        var defualts = {
				bgColor:"#FFF"
			};
        var opts = $.extend({}, defualts, options);
        var obj = $(this);
		obj.children("tbody").children("tr").hover(function(){
			$(this).css("background","#FFF");
		},function(){
			$(this).css("background","none");
		});
    };
})(jQuery);


//基础功能函数
function getValueAjax(url) {
    var xmlHttpRequest;
    var str = "";
    if (window.XMLHttpRequest) //For general cases. 
    {
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open('post', url, false);
        xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;");
        xmlHttpRequest.send('');
        if (xmlHttpRequest.status == 200)
            str = xmlHttpRequest.responseText;
    }
    else //For IE. 
    {
        if (window.ActiveXObject) {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            xmlHttpRequest.open("get", url, false);
            xmlHttpRequest.send();
            str = xmlHttpRequest.responseText;
        }
    }
    return str;
}

function getUserInfo(userid) {
    var url = "/attention/Ajax.aspx?c=3&uid=" + userid;
    var str = getValueAjax(url);
    if (str != "") {
        source = eval('(' + str + ')');
        re = source.ds[0];
    }
    return re;
}

 function qing() {
            document.getElementById("searchText").value = "";
        }
function huan() {
	if (document.getElementById("searchText").value == "") {
		document.getElementById("searchText").value = "全站搜索";
	}
	}

