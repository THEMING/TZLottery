function lh(liid){
 if(7==liid){
		   show();
		  var date=new Date();
	       if(date.getDay()==1)
	       {
	         mon();
		   }
		   if(date.getDay()==2)
	       {
	         tu();
		   }
		   if(date.getDay()==3)
	       {
	        wed();
		   }	
		   if(date.getDay()==4)
	       {
	       Thu();
		   }	
		    if(date.getDay()==5)
	       {
             fri();    
		   }	
		   	if(date.getDay()==6)
	       {
	       sat();
		   }
		   if(date.getDay()==0)
	       {
	       sun();
		   }			
		}
		if(2==liid){
		   show();
		   var date=new Date();
	       if(date.getDay()==0)
	       {
	         mon();
		   }
		   if(date.getDay()==1)
	       {
	         tu();
		   }
		   if(date.getDay()==2)
	       {
	       wed();
		   }	
		   if(date.getDay()==3)
	       {
	       thu();
		   }	
		    if(date.getDay()==4)
	       {
	        fri();
		   }	
		   	if(date.getDay()==5)
	       {
	        sat();
		   }
		   if(date.getDay()==6)
	       {
	        sun();
		   }			
		}
		if(6==liid){
		    hide();
	        $("#q1-SHUANG").show();
	        $("#q1-DLT").show();
	        $("#q1-14SFC").show();
	        $("#q1-CBF").show();
	        $("#q1-SPF").show();
	        $("#q1-ZJQS").show();
	        $("#q1-BQC").show();
		}
		if(1==liid){
		   show(); 
		}
		if(11==liid){
		   show();
		   mon(); 
		}
		if(3==liid){
		   show();
		   tu(); 
		}
		if(8==liid){
		   show();
		   wed();
		}
		if(9==liid){
		   show();
		   Thu();
		}
		if(10==liid){
		   show();
		   fri();
		}
		if(12==liid){
		   show();
		   sat();
		}
		if(4==liid){
		   show();
		   sun();
		}

}
function mon(){
$("#q1-SHUANG").hide();
$("#q1-QXC").hide();
}
function tu(){
 $("#q1-DLT").hide();
}
function wed(){
$("#q1-SHUANG").hide();
$("#q1-QXC").hide();
}
function Thu(){
$("#q1-QLC").hide();
$("#q1-DLT").hide();
$("#q1-QXC").hide();
}
function fri(){
 $("#q1-SHUANG").hide();
$("#q1-DLT").hide();
}
function sat(){
$("#q1-SHUANG").hide();
$("#q1-QLC").hide();
$("#q1-DLT").hide();
$("#q1-QXC").hide();
}
function sun(){
$("#q1-QLC").hide();
$("#q1-DLT").hide();
}
function hide(){
	$("[id^='q1']").each(function(aa,bb){
	     $(bb).hide();
		})
}
function show(){
	$("[id^='q1']").each(function(aa,bb){
	     $(bb).show();
		})
		}
		

$.LotteryTerm.callback = function(json) {
        if(json.type=="双色球")
		$("#countTime").countdown({until:json.deadLine, compact: true, format: 'DHMS',  onExpiry: liftOff});
		if(json.type=="大乐透")
		$("#countTimed").countdown({until:json.deadLine, compact: true, format: 'DHMS',  onExpiry: liftOff});
		
	};

