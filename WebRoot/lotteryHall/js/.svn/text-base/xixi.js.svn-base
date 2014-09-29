function $(id)
{
    return document.getElementById(id);
};

function getposOffset(what, offsettype)
{ 
    var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop; 
    var parentEl=what.offsetParent; 
    while (parentEl!=null)
    { 
        totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop; 
         parentEl=parentEl.offsetParent; 
    } 
    return totaloffset; 
}

//ff支持s
function isIE()
{
  return !!(window.attachEvent && !window.opera);
}


if(!isIE()){ //firefox innerText define
   HTMLElement.prototype.__defineGetter__( "innerText", 
    function(){
     var anyString = "";
     var childS = this.childNodes;
     for(var i=0; i<childS.length; i++) {
      if(childS[i].nodeType==1)
       anyString += childS[i].tagName=="BR" ? '\n' : childS[i].innerText;
      else if(childS[i].nodeType==3)
       anyString += childS[i].nodeValue;
     }
     return anyString;
    } 
   ); 
   HTMLElement.prototype.__defineSetter__(     "innerText", 
    function(sText){ 
     this.textContent=sText; 
    } 
   ); 
}






//滚动提示
var Sroll={
scrollInfo:[],
isStoped :false, 
oScroll:[],
preTop : 0, 
curTop : 0, 
stopTime : 0, 
oScrollMsg :[],
si:[],
init: function(){
try{  
                 Sroll.oScroll = $("scrollWrap"); 
                 Sroll.oScrollMsg = $("scrollMsg");
                
                 for(var i=0;i<Sroll.scrollInfo.length;i++){
                    Sroll.oScrollMsg.innerHTML+=" <a href=\""+Sroll.scrollInfo[i][1]+"\" target= \"_blank\">"+Sroll.scrollInfo[i][0]+"<span class=\"pot\"></span></a><br />"
                 }
                 with(Sroll.oScroll){  noWrap = true;  } 
                 Sroll.oScroll.onmouseover = new Function('Sroll.isStoped = true'); 
                 Sroll.oScroll.onmouseout = new Function('Sroll.isStoped = false'); 
                 
                 Sroll.si= setInterval('clearInterval(Sroll.si);Sroll.init_srolltext(); ', 1500); 
                }
                catch(e) {} 
},
init_srolltext:function(){ 
 Sroll.oScroll.scrollTop = 0; 
 setInterval('Sroll.scrollUp()', 15); 
},
 
scrollUp:function(){ 
     if(Sroll.isStoped) return; 
     Sroll.curTop += 1; 
     if(Sroll.curTop == 16) { 
         Sroll.stopTime += 1; 
         Sroll.curTop -= 1; 
         if(Sroll.stopTime == 180) { 
             Sroll.curTop = 0; 
             Sroll.stopTime = 0; 
         } 
     }
     else{
         Sroll.preTop = Sroll.oScroll.scrollTop; 
         Sroll.oScroll.scrollTop += 1; 
         if(Sroll.preTop == Sroll.oScroll.scrollTop){ 
             Sroll.oScroll.scrollTop = 0; 
             Sroll.curTop = 15; 
         } 
     } 
} 
}


//左菜单关于详细分类div 2008-10-16

//左菜单关于详细分类div 2008-10-16
//ff支持s
function $(id)
{
    return document.getElementById(id);
};

function isIE()
{
  return !!(window.attachEvent && !window.opera);
}

function getposOffset(what, offsettype)
{ 
    var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop; 
    var parentEl=what.offsetParent; 
    while (parentEl!=null)
    { 
        totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop; 
         parentEl=parentEl.offsetParent; 
    } 
    return totaloffset; 
}

var sortArray;
var sortId=-1;
function showwindowExtra(obj,objdiv,addx,addy,ids){
    if(sortArray[ids].length>0) {
	$("sortTitle").innerHTML=obj.innerHTML;
	$("sortTitle").href=obj;
	if(sortArray[ids].length==0) 
	{
	sortId=-1;
	$("sortContentBox").style.display="none";
	}
	else
	{
	sortId=ids;
	$("sortContentBox").style.display="inline";
	$("sortContent"+sortId).style.display="";
	}
	var x=getposOffset(obj,'left');
    var y=getposOffset(obj,'top');
    var div_obj=$(objdiv);
		div_obj.style.left=(x+addx)+'px';
		div_obj.style.top=(y+addy)+'px';
		div_obj.style.display="inline";

	}
	}
	
	function hidewindowExtra(objdiv,ids){
    var div_obj=$(objdiv);
    var ul_obj=$("sortContent"+ids);
		if(div_obj) div_obj.style.display="none";
		if(ul_obj)	ul_obj.style.display="none";
	}
	
	function showwindowExtra2(){
	if(sortId!=-1)	$("sortContent"+sortId).style.display="";
	$('sortBox').style.display='inline';
	}
	
	function hidewindowExtra2(){
	if(sortId!=-1)	$("sortContent"+sortId).style.display="none";
	$('sortBox').style.display='none';
	}
	
	function loadOver(){
            $("SortBoxShadowLeft").style.height="2px";
            $("SortBoxShadowLeft").style.position="static";
}

var  book='http://book.dangdang.com/';
var list='http://list.book.dangdang.com/';
var categroy='http://category.dangdang.com/';
var stat='http://static.dangdang.com/'
function initSort(){
    sortArray=[
    [
    "游戏",categroy+"game",
  "软件",categroy+"software",
  "教育音像",categroy+"education"
    ]
    ];
   initSortCreate();
}    
    
function initSortCreate(){
    var tempString ="";
        for(var i=0;i<sortArray.length;i++)
        {
        tempString+="<ul id=\"sortContent"+i+"\">";
            for(var j=0;j<sortArray[i].length;j++){
            if(j>=sortArray[i].length-2){
	            tempString+="<li class=\"li_2\"><a href=\""+sortArray[i][j+1]+"\">"+sortArray[i][j]+"</a></li>";
			}
			else{
				tempString+="<li><a href=\""+sortArray[i][j+1]+"\">"+sortArray[i][j]+"</a></li>";
			}

            j++;
            }
        tempString+="</ul>";
        }
        document.write(tempString);
    } 

var start = ["<img src='images/icon_star_gray.gif'/>","<img src='images/icon_star_harf.gif'/>","<img src='images/icon_star_red.gif'/>"];
var starts=[2,0,0,0,0,2,1,0,0,0,2,2,0,0,0,2,2,1,0,0,2,2,2,0,0,2,2,2,1,0,2,2,2,2,0,2,2,2,2,1,2,2,2,2,2];
function getStarsHtml(o)
{
 var s=[],i;
 for(i=0;i<5;i++) s.push(start[starts[(o-1)*10+i]]);
 return s.join('');
}



//关于分类搜索
function gotosearch(){
	var objfrm=document.searchform;
	if(objfrm.catalog.value>=4000000)
	{
		window.location=categroy+"search?q="+objfrm.key.value+"&guan="+objfrm.catalog.value;
		return false;
	}
	
	if(objfrm.catalog.value=="01")
	{
		objfrm.action="http://search.book.dangdang.com/search.aspx"
	}
	if(objfrm.catalog.value=="03")
	{
		objfrm.action="http://search.music.dangdang.com/search.aspx"
	}
	if(objfrm.catalog.value=="05")
	{
		objfrm.action="http://search.movie.dangdang.com/search.aspx"
	}
	return true;
}




///addfavorite
function addBookmark(title,url) 
{
    if (window.sidebar) 
    { 
        window.sidebar.addPanel(title, url,""); 
    } 
    else if( document.all ) 
    {
        window.external.AddFavorite(url, title);
    } 
    else if( window.opera && window.print ) 
    {
       return true;
    }
}



//首页幻灯
var Lantern={

    onChange:[],
    oInterval:[],
    otimeOut:[],
    opacityNum:101,
    cycNum:0,
    showNum:0,
    width:470,//整体宽度
    navyCtr:[],//2维:  0.原长 1.目标长 2.speed 
    navyTime:10,//navy动画时间
    picMoveSpeed:22,//图片移动速度
    timeOut_time:3000,//停滞时间
    info ://0.图片url 1.名称 2.链接地址 
    [],
    
    init: function(){
        Lantern.onChange=false;
        for(var i=0;i<Lantern.info.length;i++)
        {
            var picDiv
            var picTemp
            picDiv=document.createElement('div');
            picTemp=document.createElement('img');
	        picDiv.id ="LanternImg"+i;
            picDiv.name=i;
	        picTemp.src = Lantern.info[i][0];
	        picTemp.style.width = "470px";
	        picDiv.style.position ="absolute";
	        picDiv.style.left ="470px";
	        picDiv.onclick=function(){window.open(Lantern.info[this.name][2]);};
	        picDiv.appendChild(picTemp);
	        document.getElementById("lanternImg").appendChild(picDiv);
	        var divTemp
	        divTemp=document.createElement('div');
	        divTemp.id ="LanternN"+i;
	        divTemp.style.width="275px";
            divTemp.name=i;
	        divTemp.innerHTML="<div class='liclass' id='lanternnum"+i+"'>"+(i+1)+"</div><span id=\"__lanternNc"+i+"\" style=\"display:none\">&nbsp;<b>"+(i+1)+"</b>."+Lantern.info[i][1]+"</span>";
	        if(i==0)
            {
               divTemp.className ="div_off1";
            }
            else if(i==Lantern.info.length-1)
            {
                divTemp.className ="div_off3";
            }
            else
            {
                divTemp.className ="div_off2";
            }
	        //divTemp.className="div_off";
	        if(i==0)
	            divTemp.onclick=function(){window.open(Lantern.info[this.name][2]);};
	        else
	            divTemp.onclick=function(){if(!Lantern.onChange){Lantern.onChange=true;Lantern.setNavy(this.name);}};
	        document.getElementById("lanternNavy").appendChild(divTemp);
        }
        
        Lantern.initNany();
    },
    
    initNany:function(){
        navyCtr=new Array();
        for(var k=0;k<Lantern.info.length;k++)
            Lantern.navyCtr[k]=[];
        document.getElementById("__lanternNc0").style.display ="";
        document.getElementById("lanternnum0").style.display="none";
        document.getElementById("LanternN0").className ="div_on1";
        var onLength,offLength
        onLength=275//;document.getElementById("LanternN0").offsetWidth;
        offLength=27.75;//(Lantern.width-onLength)/(Lantern.info.length-1)
        var numtemp=0;
        for(var j=0;j<Lantern.info.length;j++)
        {
              if(j!=0)//未选
              {
                     Lantern.navyCtr[j][1]=offLength;
                     document.getElementById("__lanternNc"+j).style.display ="none";
                    if(j==Lantern.info.length-1)
                    {
                        document.getElementById("LanternN"+j).className ="div_off3";
                    }
                    else
                    {
                        document.getElementById("LanternN"+j).className ="div_off2";
                    }
                     document.getElementById("LanternN"+j).style.width=offLength+"px";
                     if(j==Lantern.info.length-1) 
                     {
                        document.getElementById("LanternN"+j).style.width=(Lantern.width-onLength-numtemp-7)+"px";  
                     }
                     else
                     {
                        numtemp+=offLength;
                     }
              }
              else//已选
              {
                 Lantern.navyCtr[j][1]=onLength;
              }
        }


        document.getElementById("LanternImg0").style.display ="";
        document.getElementById("LanternImg0").style.left ="0px";
        Lantern.otimeOut=setTimeout("Lantern.cycLantern()",Lantern.timeOut_time);
    },
    
    setNavy:function(i){
        if(i==Lantern.info.length-1)
             document.getElementById("lanternNavy").style.backgroundColor ="#F5F4F2";
        else
             document.getElementById("lanternNavy").style.backgroundColor ="#CCCABE";
             
        document.getElementById("__lanternNc"+i).style.display ="";
        document.getElementById("lanternnum"+i).style.display="none";
        if(i==0)
        {
            document.getElementById("LanternN"+i).className ="div_on1";
        }
        else if(i==Lantern.info.length-1)
        {
            document.getElementById("LanternN"+i).className ="div_on3";
        }
        else
        {
            document.getElementById("LanternN"+i).className ="div_on2";
        }
        document.getElementById("LanternN"+i).style.width=null;
        var onLength,offLength
        onLength=275;//document.getElementById("LanternN"+i).offsetWidth
        offLength=27.75;//(Lantern.width-onLength)/(Lantern.info.length-1)
        var numtemp=0;
        for(var j=0;j<Lantern.info.length;j++)
        {
              Lantern.navyCtr[j][0]=Lantern.navyCtr[j][1];
              if(i!=j)//未选
              {
                     Lantern.navyCtr[j][1]=offLength;
                     document.getElementById("__lanternNc"+j).style.display ="none";
                     document.getElementById("lanternnum"+j).style.display="";
                       if(j==Lantern.info.length-1)
                        {
                            document.getElementById("LanternN"+j).className ="div_off3";
                        }
                        else
                        {
                            document.getElementById("LanternN"+j).className ="div_off2";
                        }
                     if(j==Lantern.info.length-1) 
                     {
                        document.getElementById("LanternN"+j).style.width=(Lantern.width-onLength-numtemp-7)+"px";
                     }
                     else
                     {
                        numtemp+=offLength
                     }
                     document.getElementById("LanternN"+j).style.width=Lantern.navyCtr[j][0]+"px";
              Lantern.navyCtr[j][2]=(Lantern.navyCtr[j][1]-Lantern.navyCtr[j][0])/Lantern.navyTime ;
              }
              else//已选
              {
                 Lantern.navyCtr[j][1]=onLength;
                 document.getElementById("LanternN"+j).style.width=(Lantern.navyCtr[j][0])+"px";
              Lantern.navyCtr[j][2]=(Lantern.navyCtr[j][1]-Lantern.navyCtr[j][0])/Lantern.navyTime ;
             
              }
        }
        document.getElementById("LanternImg"+i).style.display ="";
        if(Lantern.onChange)
        {
                document.getElementById("LanternN"+i).onclick=function(){window.open(Lantern.info[this.name][2]);};
                document.getElementById("LanternN"+Lantern.showNum).onclick=function(){if(!Lantern.onChange){Lantern.onChange=true;Lantern.setNavy(this.name);}};
                document.getElementById("LanternImg"+i).style.zIndex=0;
                document.getElementById("LanternImg"+Lantern.showNum).style.zIndex=-1;
                Lantern.oInterval=setInterval('Lantern.changeLantern('+i+')',10);
        }
    },
    
    imgMoveOver:false,
    navyMoveOver:false,
     changeLantern:function(i){
            if(Lantern.otimeOut!=null)
                clearTimeout(Lantern.otimeOut)
             //move
             if(!Lantern.navyMoveOver)
                Lantern.moveNavy(i);
             if(!Lantern.imgMoveOver)
             {
                Lantern.moveImg(i);
             }
             else
             {
                Lantern.flashImg(i);
             }
    },
    
     moveNavy:function(select){
            var breaktime=0;
            for(var i=0;i<Lantern.info.length;i++)
            {
                  if((Lantern.navyCtr[i][2]>0&&document.getElementById("LanternN"+i).offsetWidth<Lantern.navyCtr[i][1])||(Lantern.navyCtr[i][2]<0&&document.getElementById("LanternN"+i).offsetWidth>Lantern.navyCtr[i][1]))
                  {
                       if(i==select)
                       {
                            document.getElementById("LanternN"+i).style.width=(document.getElementById("LanternN"+i).offsetWidth+Lantern.navyCtr[i][2]-7)+"px";  
                       }
                       else
                       {
                            document.getElementById("LanternN"+i).style.width=(document.getElementById("LanternN"+i).offsetWidth+Lantern.navyCtr[i][2])+"px";  
                       }
                          
                  }
                  else
                  {
                      if(i==select)
                      {
                           for(var j=0;j<Lantern.info.length;j++)
                           {
                                document.getElementById("LanternN"+j).style.width=Lantern.navyCtr[j][1]+"px"; 
                           }

                           Lantern.navyMoveOver=true;
                           break;
                  }
              }
            }
    },
    
     moveImg:function(i){
            if(document.getElementById("LanternImg"+i).offsetLeft>0)
            {
               document.getElementById("LanternImg"+i).style.left=(document.getElementById("LanternImg"+i).offsetLeft-Lantern.picMoveSpeed)+"px";
            }
            else
            {
                document.getElementById("LanternImg"+i).style.left="0px";
                document.getElementById("LanternImg"+Lantern.showNum).style.left=Lantern.width+"px";
                Lantern.imgMoveOver=true;
            }
    },
    
      flashImg:function(i){
             document.getElementById("LanternImg"+i).style.opacity="100"; 
                    Lantern.showNum=i;
                    Lantern.imgMoveOver=false;
                    Lantern.navyMoveOver=false;
                    Lantern.opacityNum=101;
                    Lantern.cycNum=i;
                    clearInterval(Lantern.oInterval);
                    Lantern.otimeOut=setTimeout("Lantern.otimeOut=Lantern.cycLantern()",Lantern.timeOut_time);
                    Lantern.onChange=false;
    },
      
    cycLantern:function(){
        if(!Lantern.onChange)
        {
            Lantern.onChange=true;
            if(Lantern.cycNum==Lantern.info.length-1)
                Lantern.cycNum=0;
            else
                Lantern.cycNum++;
           Lantern.setNavy(Lantern.cycNum)
        }
    },
    moveprevious:function(){
        if(!Lantern.onChange){
            
            if(Lantern.cycNum>0)
                Lantern.cycNum-=1;
            else
                return;
            
            Lantern.onChange=true;
            Lantern.setNavy(Lantern.cycNum)        
        }
    },
    movenext:function(){
        if(!Lantern.onChange){
            
            if(Lantern.cycNum>=Lantern.info.length-1)
                return ;
            else
                Lantern.cycNum+=1;        
             
                Lantern.onChange=true;                
                Lantern.setNavy(Lantern.cycNum);
        }
    }
    
    
}

var productDiv=new Array();
productDiv=[["showproduct1,imgproduct1","productlist1"],["showproduct2,imgproduct2","productlist2"],["showproduct3,imgproduct3","productlist3"],["showproduct4,imgproduct4","productlist4"]];

var playnum=1;

function showproductdiv(id){
if(id==0){id=playnum;}
    for(i=1;i<=4;i++){  
    
            if(i==id){
            $("showproduct"+id).style.display="inline";                  
            $("imgproduct"+id).style.display=""; 
            $("productlist"+id).style.display="";           
            }
            else{            
            $("showproduct"+i).style.display="none";      
            $("imgproduct"+i).style.display="";
            $("productlist"+i).style.display="none";           
            }

            
       
    }

    if(playnum==4){playnum=1}else{playnum++}
    
}
var myplay;
function ProductDivPlay(id){
    if(id==""){id=0}else{playnum=id}
    
    myplay=setInterval("showproductdiv(0)",8000);
}

function ProductDivStop(){

    clearInterval(myplay);
}


