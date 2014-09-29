if (typeof $ =='undefined')
{
	$ = function(id)
	{
		return document.getElementById(id);
	}
}
var g_agt = navigator.userAgent.toLowerCase();
var is_opera = (g_agt.indexOf("opera") != -1);
var g_title = "";
var g_iframeno = 0;

function exist(s)
{
	return document.getElementById(s)!=null;
}
function myInnerHTML(idname, html)
{
	if (exist(idname))
	{
		document.getElementById(idname).innerHTML = html;
	}
}
function dialog(v_w, v_h, v_title /*, v_needhead*/)
{
	var width = v_w;
	var height = v_h;
	var title = v_title;
	g_title = title;
	
	var sBox = '\
		<div id="dialogBox" style="display:none;z-index:19999;width:'+width+'px;">\
			<div class=ts460 style="position:absolute;top:0px;"></div>\
			<div style="position:absolute;height:'+height+'px;top:8px;" >\
			<table border="0" cellpadding="0" cellspacing="0">\
			<tr style="height:'+(height)+'px;"><td style="width:7px;filter:alpha(opacity=20); -moz-opacity:0.4;"></td>\
			<td style="width:'+(width-14)+'px;">\
				<div style="border:1px solid #565656;">\
				<table width="100%" border="0" cellpadding="0" cellspacing="0">\
				';
	if(typeof arguments[3] != "undefined")
	{
		var sClose = '<a href="javascript:void(0);" onclick="javascript:new dialog().close();"><img src="skin/01/images/close_vista.gif" width="34" alt="关闭"></a>';
		if(0 == arguments[3])
		{
			sBox +=	'\
					<tr valign="top">\
						<td id="dialogBody" style="height:' + (height-2) + 'px" bgcolor="#ffffff"></td>\
					</tr>\
			';
		}
		else
		{
			sBox +=	'\
						<tr height="24" bgcolor="#ffffff">\
							<td>\
								<div class="ts3" style="background:#ffffff;border-bottom:1px solid #ffffff;">\
									<div id="dialogBoxTitle" class="ts31" >'+title+'</div>\
									<div id="dialogClose" class="ts32" style="margin-top:5px;margin-right:5px;">' + sClose + '</div>\
								</div>\
							</td>\
						</tr>\
						<tr valign="top">\
							<td id="dialogBody" style="height:' + (height-28) + 'px" bgcolor="#ffffff"></td>\
						</tr>\
			';
		}
	}
	else
	{
		var sClose = '<a href="javascript:void(0);" onclick="javascript:new dialog().close();"><img src="skin/01/images/close_vista.gif" width="34" alt="关闭"></a>';
		sBox +=	'\
					<tr height="24">\
						<td class="ts3">\
							<div>\
								<div id="dialogBoxTitle" class="ts31">'+title+'</div>\
								<div id="dialogClose" class="ts32">' + sClose + '</div>\
							</div>\
						</td>\
					</tr>\
					<tr valign="top">\
						<td id="dialogBody" style="height:' + (height-28) + 'px" bgcolor="#ffffff"></td>\
					</tr>\
		';
	}
		
	sBox += '\
				</table>\
				</div>\
			</td>\
			<td><div style="width:2px;margin-top:7px;height:'+(height-7)+'px;background:#000000;filter:alpha(opacity=20); -moz-opacity:0.2;"></div></td></tr>\
			</table>\
			</div>\
			<div class=ts460 style="padding-left:13px;position:absolute;top:'+(height+8)+'px;width:'+ (width-18) +'px;height:2px;"><table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td style="height:2px;background:#000000;"></td></tr></table></div>\
		</div><div id="dialogBoxShadow" style="display:none;z-index:19998;"></div>\
	';
	
	var sIfram = '\
		<iframe id="dialogIframBG" name="dialogIframBG" frameborder="0" marginheight="0" marginwidth="0" hspace="0" vspace="0" scrolling="no" style="position:absolute;z-index:19997;display:none;"></iframe>\
	';
	
	var sBG = '\
		<div id="dialogBoxBG" style="position:absolute;top:0px;left:0px;width:100%;height:100%;"></div>\
	';
	
	this.init = function()
	{

		document.getElementById('dialogCase')!=null?document.getElementById('dialogCase').parentNode.removeChild(document.getElementById('dialogCase')) : function(){};
		var oDiv = document.createElement('span');
		oDiv.id = "dialogCase";
		if (!is_opera)
		{
			oDiv.innerHTML = sBG + sIfram + sBox;
		}
		else
		{
			oDiv.innerHTML = sBG + sBox;
		}
		
		document.body.appendChild(oDiv);


	}

	this.open = function(_sUrl)
	{		
		this.show();
		var openIframe = "<iframe width='100%' height='100%' name='iframe_parent' id='iframe_parent' src='" + _sUrl + "' frameborder='0' scrolling='no'></iframe>";
		myInnerHTML('dialogBody', openIframe);
	}

	this.show = function()
	{	
		this.middle('dialogBox');
	
		if (document.getElementById('dialogIframBG'))
		{
			document.getElementById('dialogIframBG').style.top = document.getElementById('dialogBox').style.top;
			document.getElementById('dialogIframBG').style.left = document.getElementById('dialogBox').style.left;
			document.getElementById('dialogIframBG').style.width = document.getElementById('dialogBox').offsetWidth + "px";
			document.getElementById('dialogIframBG').style.height = document.getElementById('dialogBox').offsetHeight + "px";
			document.getElementById('dialogIframBG').style.display = 'block';
		}
	
		if (!is_opera) {
			this.shadow();
		}
	}
	
	this.reset = function()
	{
		this.close();
	}

	this.close = function()
	{
		if (window.removeEventListener) 
		{
			window.removeEventListener('resize', this.event_b, false);
			window.removeEventListener('scroll', this.event_b, false);
		} 
		else if (window.detachEvent) 
		{
			try {
				
				window.detachEvent('onresize', this.event_b);
				window.detachEvent('onscroll', this.event_b);
			} catch (e) {}
		}
		if (document.getElementById('dialogIframBG')) {
			document.getElementById('dialogIframBG').style.display = 'none';
		}
		
		document.getElementById('dialogBox').style.display='none';
		document.getElementById('dialogBoxBG').style.display='none';
		document.getElementById('dialogBoxShadow').style.display = "none";
		if (typeof(parent.onDialogClose) == "function")
		{
			parent.onDialogClose(document.getElementById('dialogBoxTitle').innerHTML);
		}
	}

	this.shadow = function()
	{
		this.event_b_show();
		if (window.attachEvent)
		{
			window.attachEvent('onresize', this.event_b);
			window.attachEvent('onscroll', this.event_b);
		}
		else
		{
			window.addEventListener('resize', this.event_b, false);
			window.addEventListener('scroll', this.event_b, false);
		}
	}
	
	this.event_b = function()
	{
		var oShadow = document.getElementById('dialogBoxShadow');
		
		if (oShadow.style.display != "none")
		{
			if (this.event_b_show)
			{
				this.event_b_show();
			}
		}
	}
	
	this.event_b_show = function()
	{

		
		var oShadow = document.getElementById('dialogBoxShadow');
		oShadow.style.position = "absolute";
		oShadow.style.display	= "";		
		oShadow.style.opacity	= "0.2";
		oShadow.style.filter = "alpha(opacity=10)";
		oShadow.style.background	= "#000";
		var screen_height = document.body.clientHeight;
		
		//var sClientWidth = parent ? parent.document.body.offsetWidth : document.body.offsetWidth;
		var sClientWidth =  document.body.offsetWidth;

		//var sClientHeight = parent ? parent.document.body.offsetHeight : document.body.offsetHeight;
		var sClientHeight =  document.body.offsetHeight;
		var sScrollTop = parent ? (parent.document.body.scrollTop+parent.document.documentElement.scrollTop) : (document.body.scrollTop+document.documentElement.scrollTop);
		oShadow.style.top = '0px';
		oShadow.style.left = '0px';
		oShadow.style.width = sClientWidth + "px";
		oShadow.style.height = (screen_height) + "px";
		
		
	}

	this.middle = function(_sId)
	{

		document.getElementById(_sId).style.display='';
		document.getElementById(_sId).style.position="absolute";

		var sClientWidth = parent.document.body.clientWidth;
		var sClientHeight = parent.document.body.clientHeight;
		var sScrollTop = parent.document.body.scrollTop+parent.document.documentElement.scrollTop;

		//var sleft = (sClientWidth - $(_sId).offsetWidth) / 2;
		var sleft =100;
		var iTop = sScrollTop + 10;
		var sTop = iTop > 0 ? iTop : 0;

		document.getElementById(_sId).style.left = sleft + "px";
		document.getElementById(_sId).style.top = sTop + "px";
	}
}

function openWindow(_sUrl, _sWidth, _sHeight, _sTitle /*,_sNeedHead*/)
{
	if(typeof arguments[4] != "undefined")
	{
		var oEdit = new dialog(_sWidth, _sHeight, _sTitle, arguments[4]);
	}
	else
	{
		var oEdit = new dialog(_sWidth, _sHeight, _sTitle);
	}
	oEdit.init();
	oEdit.open(_sUrl);
}

function openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction)
{
	return _openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction, "");
}

function openAlertBlue(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction)
{
	var excss = '.rbs1{border:1px solid #d7e7fe; float:left;}\n' +
'.rb1-12,.rb2-12{height:23px; color:#fff; font-size:12px; background:#355582; padding:3px 5px; border-left:1px solid #fff; border-top:1px solid #fff; border-right:1px solid #6a6a6a; border-bottom:1px solid #6a6a6a; cursor:pointer;}\n' +
'.rb2-12{background:#355582;}\n';
	return _openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction, excss);
}

function _openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction, _excss)
{
	var oEdit = new dialog(_sWidth, _sHeight, _sTitle);
	oEdit.init();
	oEdit.show();
	var framename = "iframe_parent_" + g_iframeno++;
	var openIframe = "<iframe width='100%' height='100%' name='"+framename+"' id='"+framename+"' src='' frameborder='0' scrolling='no'></iframe>";
	myInnerHTML('dialogBody', openIframe);
	var iframe = window.frames[framename];
	iframe.document.open();
	iframe.document.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n');
	iframe.document.write('<html xmlns="http://www.w3.org/1999/xhtml">\n');
	iframe.document.write('<head>\n');
	iframe.document.write('<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n');
	iframe.document.write('<link href="smarty/style/style.css" rel="stylesheet" type="text/css" />\n');
	if (_excss && _excss.length)
	{
		iframe.document.write('<style>\n');
		iframe.document.write(_excss + '\n');
		iframe.document.write('</style>\n');
	}
	iframe.document.write('</head>\n');
	iframe.document.write('<body>\n');
	if(_sAction == undefined)
	{
		_sAction = "new parent.dialog().reset();";
	}
	iframe.document.write(alertHtml(_sWord , _sButton , _sAction)+'\n');
	iframe.document.write('</body>\n');
	iframe.document.write('</html>\n');
	iframe.document.close();
}

function alertHtml(_sWord , _sButton , _sAction)
{
	var html = "";
	
	var html = '<div class="ts4">\
			<div class="ts45" style="border-top:none;padding-top:0;">\
				 '+_sWord+'\
				<div class="c"></div>\
			</div>\
			<div class="ts42 r">\
				<div class="rbs1"><input type="button" value="'+_sButton+'" title="'+_sButton+'" class="rb1-12" onmouseover="this.className=\'rb2-12\';" onmouseout="this.className=\'rb1-12\';" onclick="javascript:'+_sAction+'" /></div>\
				<div class="c"></div>\
			</div>\
		   </div>';
	
	return html;
}
