var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
function isCardID(sId)
{ 
	var iSum=0 ;
	var info="" ;
	if(sId.length==15) {
		if(!(/(^\d{15}$)$/.test(sId))){
			$("#imgcard").text("你输入的身份证号非法");
			return false; 
		}   
	}
	else{
		if(sId.search(/^\d{17}([0-9]|x|X)$/)==-1){
			$("#imgcard").text("你输入的身份证长度或格式错误");
			return false; 
		}
		if(aCity[parseInt(sId.substr(0,2))]==null){
			$("#imgcard").text("你的身份证地区非法");
			return false; 
		}
		sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
		var d=new Date(sBirthday.replace(/-/g,"/")) ;
		if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
			$("#imgcard").text("身份证上的出生日期非法");
			return false; 
		}
		var valnum;
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var arrCh = new Array('1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'); 
		var nTemp = 0,i; 
		for(i = 0; i < 17; i ++) { nTemp += sId.substr(i, 1) * arrInt[i]; }
		valnum = arrCh[nTemp % 11].toLowerCase(); 
		if (valnum != sId.substr(17, 1)) 
		{ 
			$("#imgcard").text("你输入的身份证号非法");
			return false; 
		} 
	
	}
	$("#imgcard").text("");
	return true; 
} 

function mmxgcheck(){
     var bool=0;
	if(!isPwd($("#oldpassword").val())){
		$("#imgoldpwd").text("提示：请正确填写旧登录密码!");
		$("#oldpassword").focus();
		bool=1;
	
	}

	if(!isPwd($("#password").val())){
		$("#imgpwd").text("提示：请正确填写新登录密码！");
		$("#password").focus();
		bool=1;
	
	}
	
	if($("#repassword").val()!=$("#password").val()){
		$("#imgrepwd").text("提示：确认密码与新密码输入不一致！");
		$("#repassword").focus();
		bool=1;
	}
	if(bool==1){
	return false;
	}
}

function zlxgcheck(){
	var bool=0;
	if(!isPhone($("#mobileNo").val())){
		$("#imgmobileNo").text("提示：请正确填写手机号码!");
		$("#mobileNo").focus();
		bool=1;
	
	}
	if(!isPwd($("#password").val())){
		$("#imgpwd").text("提示：请正确填写确认密码!");
		$("#email").focus();
		bool=1;
	
	}
	if(bool==1){
	return false;
	}
}
function bankcheck(){
	var bool=0;
	if($("#province").val()=="省份"){
		$("#imgcity").text("提示：请选择发卡银行省份！");	
		$("#province").focus();
		return false;
	}
	if($("#city option:selected").val()=="城市"){
		$("#imgcity").text("提示：请选发卡银行城市！");	
		$("#city").focus();
		return false;
	}
	if($("#subbranch").val()==""){
		$("#imgcity").text("提示：请选输入开户支行！");	
		$("#city").focus();
		return false;
	}
	if($("#bankName").val()==""){
		$("#imgcity").text("提示：请选输入开户名！");	
		$("#city").focus();
		return false;
	}
	if($("#banks").val()=="0"){
		$("#imgbanks").text("提示：请选择开户银行！");	
		$("#banks").focus();
		return false;
	}

	if(!isInt($.trim($("#bankNumber").val()))||$.trim($("#bankNumber").val()).length<5){
	    
		$("#imgbankcrad").text("提示：请正确填写银行卡卡号！");
		$("#bankNumber").focus();
		return false;
	
	}
}

function check()
{
	var bool=0;
	if(!isName($("#nickname").val())){
		 $("#imgnickname").text("提示(必填)：您填写的用户名不符规则,请正确填写.");
			$("#nickname").focus();
			 return false;
		}
		else{
			$("#imgnickname").text("");
		}
	 if(!isPwd($("#password").val())){
			$("#imgpwd").text("提示(必填)：请正确填写登录密码,并且不能为空！");
			$("#password").focus();
			 return false;
		}else{
			$("#imgpwd").text("");
		}
	 if($("#repassword").val()!=$("#password").val()){
			$("#imgrepwd").text("提示(必填)：确认密码与密码输入不一致！");
			$("#repassword").focus();
			 return false;
	    }else{
			$("#imgrepwd").text("");
		}
	 if(!isRealName($("#name").val())){
		 $("#imgname").text("提示(必填)：请正确输入真实姓名,并且不能为空！");
			$("#name").focus();
			 return false;
		}else{
			$("#imgname").text("");
		}
	 if(!isCardID($("#credentNo").val())){
		 return false;
		}else{
			$("#imgcard").text("");
		}
	 if(!isEmil($("#email").val())){
		 $("#imgemail").text("提示(必填)：请正确输入电子邮箱,并且不能为空！");
			$("#email").focus();
			 return false;
		}else{
			$("#imgemail").text("");
		}
	 if(!isPhone($("#mobileNo").val())){
		 $("#imgmobileNo").text("提示：请正确输入手机号码！");
			$("#mobileNo").focus();
			 return false;
		}else{
			$("#imgmobileNo").text("");
		}
	 if($("#mngunm").val()==""){
		 $("#imgmngunm").text("提示：请正确输入验证码！");
			$("#mngunm").focus();
			 return false;
		}else{
			$("#imgmngunm").text("");
		}
	
}
function logincheck(){
	var bool=0;
	 if($("#nickname").val()==""){
		 $("#imgnickname").text("用户名不能为空!");
			$("#nickname").focus();
			bool=1;
		}else{
			$("#imgnickname").text("");
		}  
	 if(!isPwd($("#password").val())){
			$("#imgpwd").text("请正确填写登录密码!");
			$("#password").focus();
			bool=1;
		}else{
			$("#imgpwd").text("");
		}

	 if(bool==1){
	   return false;
	 }
	 
}

function checkPwd(){
	if(!isName($("#nickname").val())){
		 $("#imgnickname").text("提示:您填写的用户名不符规则,请正确填写.");
			$("#nickname").focus();
			return false;
		}
	if(!isCardID($("#credentNo").val())){
			bool=1;
		}else{
			$("#imgcard").text("");
		}
	 if(!isEmil($("#email").val())){
		 $("#imgemail").text("提示:请正确输入电子邮箱,并且不能为空！");
			$("#email").focus();
			bool=1;
	}else{
		$("#imgemail").text("");
	}
}

function checkName_ajax()
{
	if(!isName($("#nickname").val())) {
		$("#imgnickname").text("提示(必填)：您填写的用户名不符规则,请正确填写.");
		$("#nickname").focus();
		return false;
	}
	
	$.Reg.callback = function(json) {
		$("#imgnickname").html("<font style='color:red'>"+json.message+"</font>");
	};
	
	$.Reg._request({action:"checkName",nickname:$("#nickname").val()});
}

function checkpwd_ajax()
{
	$.Reg.callback = function(json){
		if(json.num=="2"){
			$("#imgnickname").html("<font style='color:red'>"+json.nameerr+"</font>");
		}
		if(json.num=="3"){
			$("#imgcredentNo").html("<font style='color:red'>"+json.credenterr+"</font>");
		}
		if(json.num=="1"){
			$("#imgemail").html("<font style='color:red'>"+json.emailerr+"</font>");
		}
	};
	$.Reg._request({action:checkpwd,nickname:$("#nickname").val(),credentNo:$("#credentNo").val(),email:$("#email").val()});
}

function refreshCaptcha()
{
	$('#captchaImg').attr("src","/jcaptcha.jpg?"+Math.round(Math.random()*100000));
}

function isName(s)
{
	if(s.search(/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/)!=-1 && s.replace(/[^\x00-\xff]/g,"**").length>=4&&s.replace(/[^\x00-\xff]/g,"**").length<=15)
		return true;
	else
		return false;
}

function isRealName(s)
{
	if(s.search(/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/)!=-1)
		return true;
	else
		return false;
}

function isPwd(s)
{
	if(s.search(/^[A-Za-z0-9]{6,15}$/)!=-1)
		return true;
	else
		return false;
}

function isPhone(s)
{
	if(s.search(/^(13|15|18)[0-9]{9}$/)!=-1)
		return true;
	else
		return false;
}

function isEmil(s)
{
	if(s.search(/^([a-zA-Z0-9]+[_|\_|\.|\-]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/)!=-1)
		return true;
	else
		return false;
}

function isInt(s)
{
	if(s.search(/[1-9]*[1-9][0-9]*$/)!=-1)
		return true;
	else
		return false;
}

function isCard(s)
{
	if(s.search(/[1-9]*[1-9][0-9]*$/)!=-1 && s.length>=16)
		return true;
	else
		return false;
}

function checkmobile()
{
	var vmobile = $("#imgmobileNo").val();

	if(!isPhone($("#mobileNo").val()))
	{
		$("#imgmobileNo").text("提示：请正确填写手机号码!");
		return false; 
	}
	else
	{
		$("#imgmobileNo").text("");
	}
}

function checkpassword()
{

	if(!isPwd($("#password").val()))
	{
		$("#imgpwd").text("提示：请正确填写确认密码!");
		return false; 
	}
	else
	{
		$("#imgpwd").text("");
	}
}

function checkCard()
{

	if(!isPwd($("#password").val()))
	{
		$("#imgpwd").text("提示：请正确填写确认密码!");
		return false; 
	}
	else
	{
		$("#imgpwd").text("");
	}
}
function checkbankcrad()
{

	if(!isInt($.trim($("#bankNumber").val()))||$.trim($("#bankNumber").val()).length<5) {
		$("#imgbankcrad").text("提示：请正确填写银行卡卡号！");
		$("#bankNumber").focus();
		return false;
	}
	else {
		$("#imgbankcrad").text("");
	}
}

function checkrebankcrad()
{
	if($.trim($("#bankNumber").val())!=$.trim($("#rebankNumber").val())){
		
		$("#imgrebankcrad").text("提示：两次输入的卡号不一致！");
		$("#rebankNumber").focus();
		return false;
	}
	else
	{
		$("#imgrebankcrad").text("");
	}
}

function checkbanks()
{
	if($("#banks").val()=="0"){
		$("#imgbanks").text("提示：请选择开户银行！");
		$("#banks").focus();
		return false;
	}
	else
	{
		$("#imgbanks").text("");
	}
}

function checkcity()
{
	if($("#province").val()=="省份"){
		$("#imgcity").text("提示：请选择发卡银行省份！");	
		$("#province").focus();
		return false;
	}
	else
	{
		$("#imgcity").text("");
	}
	if($("#city").val()=="城市"){
		$("#imgcity").text("提示：请选发卡银行城市！");
		$("#city").focus();
		return false;
	}
	else
	{
		$("#imgcity").text("");
	}
}

