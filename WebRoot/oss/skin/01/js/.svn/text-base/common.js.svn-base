/*************************************
*** ┌┈┈┈┈┈┈┈┈┈┈┈┈┈┐ ***
*** ┊  Powered By HongRu.DJJ   ┊ ***
*** ┊  Last Modify 2008-06-25  ┊ ***
*** └┈┈┈┈┈┈┈┈┈┈┈┈┈┘ ***
*** ┌┈┈┈┈┈┈┈┈┈┈┈┈┈┐ ***
*** ┊ msn_dujunjie@hotmail.com ┊ ***
*** └┈┈┈┈┈┈┈┈┈┈┈┈┈┘ ***
*************************************/


//弹出确认框
function getConfirm(str)
{
	var box=window.confirm("提示：\n\n"+str);
	if(box){
		return true;}
	else{
		return false;
	}
}


//身份证验证
function isIdCard(s)
{
	if((s.search(/^[1-9]([0-9]{16}|[0-9]{13})[xX0-9]$/)!=-1)||(s.length==18)||(s.length==15))
		return true;
	else
		return false;
}

//整数验证
function isInt(s)
{
	if(s.search(/^[0-9]+$/)!=-1)
		return true;
	else
		return false;
}

//验证实数
function isFloat(snumber){
	var checkOK = "0123456789.";
	var checkStr = snumber;
	var allValid = true;
	var obj;
	for (i = 0;  i < checkStr.length;  i++){
		ch = checkStr.charAt(i);
		for (j = 0;  j < checkOK.length;  j++)
		if (ch == checkOK.charAt(j)) break;
		if (j == checkOK.length){
			return (false);
		}
	}
	return (true);
}


function isUnderline(s)
{
		
	if(s.search(/^[A-Za-z0-9_]+$/)!=-1)
		return true;
	else
		return false;
	
}

// 密码验证

function specialchar(val){
								
	str = ((/>|<|,|\[|\]|\{|\}|\?|\/|\+|=|\||\'|\\|\"|:|;|\~|\!|\@|\#|\*|\$|\%|\^|\&|\(|\)|`/i).test(val));
									
	if(str){
		return true;
	}
}

function isIntAndChar(s)
{
		
	if(s.search(/^[A-Za-z0-9]+$/)!=-1)
		return true;
	else
		return false;
	
}

function isChar(s)
{
		
	if(s.search(/^[A-Za-z]+$/)!=-1)
		return true;
	else
		return false;
	
}
function JHshStrLen(sString){
	var sStr,iCount,i,strTemp ;
	iCount = 0 ;
	sStr = sString.split("");
	for (i = 0 ; i < sStr.length ; i ++){
		strTemp = escape(sStr[i]);
		if (strTemp.indexOf("%u",0) == -1){ 	 // 表示是汉字
			iCount = iCount + 1 ;
		}else{
			iCount = iCount + 2 ;
		}
	}
	return iCount ;
}


//日期验证
function isDate(sDate) {
	var iYear, iMonth, iDay, iIndex

	var	reg
	reg = new RegExp('[^0-9-]','')
	if (sDate.search(reg) >= 0)
		return false;
	
	iIndex = sDate.indexOf('-');
	if ( iIndex == -1 )
		return false;
	else {
		iYear = parseFloat(sDate.substr(0, iIndex));
		if ( isNaN(iYear) || iYear < 1900 || iYear > 2099 )
			return false;
		else
			sDate = sDate.substring(iIndex + 1, sDate.length);
	}
	
	iIndex = sDate.indexOf('-');
	if ( iIndex == -1 )
		return false;
	else {
		iMonth = parseFloat(sDate.substr(0, iIndex));
		if ( isNaN(iMonth) || iMonth < 1 || iMonth > 12 )
			return false;
		else
			sDate = sDate.substring(iIndex + 1, sDate.length);
	}
	
	iIndex = sDate.indexOf('-');
	if ( iIndex >= 0 )
		return false;
	else {
		iDay = parseFloat(sDate);
		if ( isNaN(iDay) || iDay < 1 || iDay > 31 )
			return false;
	}
	
	
	switch(iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			if ( iDay > 30 )
				return false;
			else
				break;
		case 2:
			if ( ( ( iYear % 4 == 0 && iYear % 100 != 0 ) || iYear % 400 == 0 ) && iDay > 29 )
				return false;
			else if ( (iYear % 4 != 0 || (iYear % 100 == 0 && iYear % 400 != 0)) && iDay > 28 )
				return false;
			else
				break;
		default:
	}
	return true;
}






//获取下拉选项值
function getSelectedValue(idvalue){
	try{
		var obj=document.getElementById(idvalue);
		for(i=0;i<obj.length;i++){
			
			if(obj[i].selected==true){
				if(obj[i].value==""){
					
					return obj[i].innerText; //关键是通过option对象的innerText属性获取到选项文本
				
				}else{
					
					return obj[i].value;
				}
			}
		}
	
	
	}catch (e) {
	}
	return false;
}

//获取下拉选项值
function getCheckedValue(idvalue){
	
	var obj=document.getElementById(idvalue);
	for(i=0;i<obj.length;i++){
		
		if(obj[i].checked==true){
			if(obj[i].value==""){
				
				return obj[i].innerText; //关键是通过option对象的innerText属性获取到选项文本
			
			}else{
				
				return obj[i].value;
			}
		}
	}
	return false;
}

//验证合法邮箱
function isEmail(s)
{
	if (s.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
		return true;
	else
		return false;
}


//返回字符长度
function len(s)
{
	if(s==null)
		return 0;
	else
		return s.replace(/[^\x00-\xff]/g,"**").length;
}

//验证是否是双字节
function isDoubleFont(s)
{
	if(s.search(/[^\x00-\xff]/)!=-1)
		return true;
	else
		return false;
}

//列表框跳转菜单
function  turnit(ss)
{
  if  (ss.style.display=="none")  
    {ss.style.display="";
	for (var i=1;i<4;i++)
{
if (eval('t'+i).innerText==text1.value)
eval('t'+i).className='ss' 
else
eval('t'+i).className=''
}
}
  else
    {ss.style.display="none";   
   }
}
function sele(tid)
{
bb.style.display='none';
text1.value=tid.innerText;
}
function over(tid)
{
for (var i=1;i<4;i++)
{
eval('t'+i).className=''
}
tid.className='ss'
if(typeof(prevObj)!='undefined')
prevObj.bgColor='#FFEEC1';
prevObj=tid;
}
function openb()
{
if (bb.style.display=='')
bb.style.display='none'
}
//表格鼠标移上去效果
  function   change()   
  {   
  var   oObj   =   event.srcElement;   
  if(oObj.tagName.toLowerCase()   ==   "td")   
  {   
  var   oTr   =   oObj.parentNode;   
  for(var   i=1;   i<document.all.table1.rows.length;   i++)   
  {   
  document.all.table1.rows[i].style.backgroundColor   =   "#FFFFFF";   
  document.all.table1.rows[i].tag   =   false;   
  oTr.style.backgroundColor   =   "#fff"; 
  }   
  oTr.style.backgroundColor   =   "#FFEEC1";   
  oTr.tag   =   true;   
  }   
  }   
    
  function   out()   
  {   
  var   oObj   =   event.srcElement;   
  if(oObj.tagName.toLowerCase()   ==   "td")   
  {   
  var   oTr   =   oObj.parentNode;   
  if(!oTr.tag)   
  oTr.style.backgroundColor   =   "#ffffff";   
  }   
  }   
    
  function   over()   
  {   
  var   oObj   =   event.srcElement;   
  if(oObj.tagName.toLowerCase()   ==   "td")   
  {   
  var   oTr   =   oObj.parentNode;   
  if(!oTr.tag)   
  oTr.style.backgroundColor   =   "#FFEEC1";   
  }   
  }   
    





//切换频道
function channelNav(Obj, channel) {
	var channelTabs = document.getElementById('topmenu').getElementsByTagName('a');
	for (i=0; i<channelTabs.length; i++) {
		channelTabs[i].className = '';
	}
	Obj.className = 'current';
	Obj.blur();
	var sideDoc = window.parent.leftframe.document;
	var sideChannels = sideDoc.getElementsByTagName('div')
	for (i=0; i<sideChannels.length; i++) {
		sideChannels[i].style.display = '';
	}
	var sideChannelLinks = sideDoc.getElementsByTagName('a')
	for (i=0; i<sideChannelLinks.length; i++) {
		sideChannelLinks[i].className = '';
	}
}




//侧栏开关
function sideSwitch() {
	var mainFrame = window.parent.document.getElementById('mainframeset');
	var switcher = document.getElementById('sideswitch');
	if (mainFrame.cols == '0,*') {
		mainFrame.cols = '215,*';
		switcher.innerHTML = '关闭左侧栏';
		switcher.className = 'opened';
	} else {
		mainFrame.cols = '0,*';
		switcher.innerHTML = '打开左侧栏';
		switcher.className = 'closed';
	}
}


// 战绩图形显示
function len(s) { 
	var l = 0; 
	var a = s.split(""); 
	for (var i=0;i<a.length;i++) { 
	if (a[i].charCodeAt(0)<299) { 
	l++; 
	} else { 
	l+=2; 
	} 
	} 
	return l; 
} 
function getScorePic(x,y){
	
	
	var i = x.toString(5);


	var a = i.split(""); 
	var k=1;
	var level_1_pic = "";
	var level_2_pic = "";
	var level_3_pic = "";
	var level_4_pic = "";
	var level_5_pic = "";

	for (i=a.length-1;i>=0;i--) { 

		for(var j=0;j<a[i];j++){
			
			if(k==1)
				level_1_pic = level_1_pic + "<img src='skin/01/images/user/05star_"+y+"in.png' width=16 height=16 />";
			else if(k==2)
				level_2_pic = level_2_pic + "<img src='skin/01/images/user/04moon_"+y+"in.png' width=16 height=16 />";
			else if(k==3)
				level_3_pic = level_3_pic + "<img src='skin/01/images/user/03sun_"+y+"in.png' width=16 height=16 />";
			else if(k==4)
				level_4_pic = level_4_pic + "<img src='skin/01/images/user/02diamond_"+y+"in.png' width=16 height=16 />";
			else if(k==5)
				level_5_pic = level_5_pic + "<img src='skin/01/images/user/01crown_"+y+"in.png' width=16 height=16 />";
			
		}
		k++;
	}

	return level_5_pic+level_4_pic+level_3_pic+level_2_pic+level_1_pic;



}