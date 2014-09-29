lastScrollY=0;
function heartBeat()
{ 
	diffY=document.documentElement.scrollTop; 
	percent=.1*(diffY-lastScrollY); 
	if(percent>0)
		percent=Math.ceil(percent); 
	else 
		{
			percent=Math.floor(percent); 
		}
	document.all.ad1.style.pixelTop+=percent;
	document.all.ad2.style.pixelTop+=percent;
	lastScrollY=lastScrollY+percent; 
} 
function closeAd(index)
{
	if(index==1)
	{
	var ad=document.getElementById("ad1");
	}
	else
	{
	var ad=document.getElementById("ad2");
	}
	ad.style.display="none";
}
mysuspendcode="<DIV id=ad1 style='left:10px;POSITION:absolute;TOP:330px;'><a href='http://www.hao123.com' target='_blank' Title='AD'><img src=images/ad/1.jpg border='0'></a><br><img src=images/ad/closea.jpg onClick= closeAd(1) border='0'></div>"
document.write(mysuspendcode); 
mysuspendcode="<DIV id=ad2 style='right:10px;POSITION:absolute;TOP:330px;'><a href='http://www.hao123.com' target='_blank' Title='AD'><img src=images/ad/1.jpg border='0'></a><br><img src=images/ad/closea.jpg onClick= closeAd(2) border='0'></div>"
document.write(mysuspendcode); 
window.setInterval("heartBeat()",1); 
