var aCity = {
	11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
	21:"辽宁",22:"吉林",23:"黑龙江",
	31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",
	41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",
	50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",
	61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",
	71:"台湾",81:"香港",82:"澳门",91:"国外"
};

/* 验证身份证号码 */
function checkCardID(cardId)
{
	$("#imgcard").attr("class", "red");
	
	var iSum = 0 ;
	var info = "" ;
	if(cardId.length == 15) {
		if(!(/(^\d{15}$)$/.test(cardId))) {
			$("#imgcard").text("你输入的身份证号非法");
			return false; 
		}   
	}
	else {
		if(cardId.search(/^\d{17}([0-9]|x|X)$/) == -1) {
			$("#imgcard").text("你输入的身份证长度或格式错误");
			return false; 
		}
		
		if(aCity[parseInt(cardId.substr(0,2))]==null){
			$("#imgcard").text("你的身份证地区非法");
			return false; 
		}
		
		sBirthday=cardId.substr(6,4)+"-"+Number(cardId.substr(10,2))+"-"+Number(cardId.substr(12,2)); 
		var d=new Date(sBirthday.replace(/-/g,"/")) ;
		if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
			$("#imgcard").text("身份证上的出生日期非法");
			return false; 
		}
		
		var valnum;
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var arrCh = new Array('1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'); 
		var nTemp = 0,i; 
		for(i = 0; i < 17; i ++) {
			nTemp += cardId.substr(i, 1) * arrInt[i];
		}
		
		valnum = arrCh[nTemp % 11].toLowerCase(); 
		if(valnum != cardId.substr(17, 1)) {
			$("#imgcard").text("你输入的身份证号非法");
			return false; 
		}
	}
	
	$("#imgcard").text("");
	return true; 
} 

/* 注册检查 */
function registerCheck()
{
	var bool = 0;
	if(!isName($("#nickname").val())) {
		$("#imgnickname").addClass("red");
		$("#imgnickname").text("提示(必填)：您填写的用户名不符规则,请正确填写.");
		$("#nickname").focus();
		return false;
	}
	else {
		$("#imgnickname").text("");
	}

	if(!isPwd($("#password").val())) {
		$("#imgpwd").attr("class","red");
		$("#imgpwd").text("提示(必填)：请正确填写登录密码,并且不能为空！");
		$("#password").focus();
		return false;
	}
	else{
		$("#imgpwd").text("");
	}
	 if($("#imgsuperior").text()=="推荐人不存在")
	 return false;
	if($("#repassword").val() != $("#password").val()) {
		$("#imgrepwd").attr("class","red");
		$("#imgrepwd").text("提示(必填)：确认密码与密码输入不一致！");
		$("#repassword").focus();
		return false;
    }
    else{
		$("#imgrepwd").text("");
	}
	/*
	if(!isRealName($("#name").val())) {
		$("#imgname").attr("class","red");
	 	$("#imgname").text("提示(必填)：请正确输入真实姓名,并且不能为空！");
		$("#name").focus();
		return false;
	}
	else{
		$("#imgname").text("");
	}

	if(!checkCardID($("#credentNo").val())) {
		return false;
	}
	else{
		$("#imgcard").text("");
	}

	if(!checkPhone($("#mobileNo").val())) {
		$("#imgmobileNo").attr("class", "red");
		$("#imgmobileNo").text("提示：请正确输入手机号码！");
		$("#mobileNo").focus();
		return false;
	}
	else{
		$("#imgmobileNo").text("");
	}
	*/
	if(!checkEmail($("#email").val())) {
		$("#imgemail").attr("class", "red");
		$("#imgemail").text("提示(必填)：请正确输入电子邮箱,并且不能为空！");
		$("#email").focus();
		return false;
	}
	else{
		$("#imgemail").text("");
	}
	
	
	if($("#mngunm").val()=="") {
		$("#imgmngunm").attr("class", "red");
		$("#imgmngunm").text("提示：请正确输入验证码！");
		$("#mngunm").focus();
		return false;
	}
	else {
		$("#imgmngunm").text("");
	}
}

/* 登录检查 */
function checkLogin()
{
	var bool = 0;
	if($("#nickname").val() == "") {
		$("#message").text("用户名不能为空!");
		$("#nickname").focus();
		bool=1;
	}  
	
	if(!bool && !isPwd($("#password").val())) {
		$("#message").text("请正确填写登录密码!");
		$("#password").focus();
		bool=1;
	}
	
	if(!bool && $("#mngunm").val() == "") {
		$("#message").text("验证码不能为空!");
		$("#mngunm").focus();
		bool=1;
	}

	if(bool == 1) {
		return false;
	}	 
	else{
	    return true;
	}
}


function checkLogin2()
{
	var bool = 0;
	if($("#nickname2").val() == "") {
		$("#message2").text("用户名不能为空!");
		$("#nickname2").focus();
		bool=1;
	}  
	
	if(!bool && !isPwd($("#password2").val())) {
		$("#message2").text("请正确填写登录密码!");
		$("#password2").focus();
		bool=1;
	}
	
	if(!bool && $("#mngunm2").val() == "") {
		$("#message2").text("验证码不能为空!");
		$("#mngunm2").focus();
		bool=1;
	}

	if(bool == 1) {
		return false;
	}	 
	else{
	    return true;
	}
}

/* 找回密码 验证*/
function checkPwd()
{
	if(!isName($("#nickname").val())) {
		$("#imgnickname").attr("class","red");
		$("#imgnickname").text("提示:您填写的用户名不符规则,请正确填写.");
		$("#nickname").focus();
		return false;
	}
	else {
		$("#imgnickname").text("");
	}
	
	if(!checkCardID($("#credentNo").val())) {
		bool = 1;
		$("#credentNo").focus();
		return false;
	}
	else{
		$("#imgcard").text("");
	}

	if(!checkEmail($("#email").val())) {
		$("#imgemail").attr("class","red");
		$("#imgemail").text("提示:请正确输入电子邮箱,并且不能为空！");
		$("#email").focus();
		bool = 1;
		return false;
	}
	else{
		$("#imgemail").text("");
	}
	return true;
}

function checkChangePwd()
{
	if(!isPwd($("#oldpassword").val())) {
		$("#message2").text("提示：请正确填写旧登录密码!");
		$("#oldpassword").focus();
		return false;
	}

	if(!isPwd($("#newpassword").val())){
		$("#message2").text("提示：请正确填写新登录密码！");
		$("#newpassword").focus();
		return false;
	}
	
	if($("#repassword").val()!=$("#newpassword").val()){
		$("#message2").text("提示：确认密码与新密码输入不一致！");
		$("#repassword").focus();
		return false;
	}
	
	return true;
}

function checkChangeMaterial()
{
	var bool=0;
	
	if(!checkEmail($("#email").val())) {
		$("#message1").text("提示：请正确输入电子邮箱！");
		$("#email").focus();
		return false;
	}
	
	if(!checkPhone($("#mobileNo").val())){
		$("#message1").text("提示：请正确填写手机号码!");
		$("#mobileNo").focus();
		return false;
	}
	
	if(!isPwd($("#password").val())){
		$("#message1").text("提示：请正确填写确认密码!");
		$("#password").focus();
		return false;
	}
	
	//add 2011-08-09
	if(!isRealName($("#realName").val())) {
	 	$("#message1").text("提示：请正确输入真实姓名！");
		$("#realName").focus();
		return false;
	}

	if(!checkCardID($("#credentNo").val())) {
		$("#message1").text("提示：请正确输入身份证号码！");
		$("#credentNo").focus();
		return false;
	}
	//add end
	
	return true;
}

function bankcheck()
{
	var bool = 0;
	if($("#province").val() == "省份") {
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

	if(!isInt($.trim($("#bankNumber").val()))||$.trim($("#bankNumber").val()).length<5) {
		$("#imgbankcrad").text("提示：请正确填写银行卡卡号！");
		$("#bankNumber").focus();
		return false;
	}
}

function checkName()
{
	$("#imgnickname").attr("class", "red");
	if(!isName($("#nickname").val())) {
		$("#imgnickname").text("提示(必填)：您填写的用户名不符规则,请正确填写.");
		$("#nickname").focus();
		return false;
	}
	
	$.Reg.callback = function(json) {
		$("#imgnickname").text(json.message);
	};
	
	$.Reg._request({action:"checkName",nickname:$("#nickname").val()});
}
function checkSuperior(){
              if($("#superior").val()==""){
                $("#imgsuperior").html("(选填)输入推荐您的用户的用户名");
                	return;
                } 
           
             $.ajax({
					type:"post",
					url: "register.htm?action=cheacksuperior",
					data:{superior:$("#superior").val()},
					success: function(data, textStatus){
					var a=eval("("+data+")");
				
			if(a.message=="推荐人不存在")
			{
			 $("#imgsuperior").html("<font style='color:red'>"+a.message+"</font>")
			}
			else{
			 $("#imgsuperior").html("(选填)输入推荐您的用户的用户名")
			}
			}})
                
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

function checkPhone(s)
{
	if(s.search(/^(13|15|18)[0-9]{9}$/)!=-1)
		return true;
	else
		return false;
}

function checkEmail(s)
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

	if(!checkPhone($("#mobileNo").val()))
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

function isNum(str)
{  
	var re = /^[\d]+$/;
	var result=false;
	var final=0;
	if(re.test(str)){
		final=final+1;
	}
	if(re!=0){
		final=final+1;
	}
	if(final==2){
		result=true;
	}
	return result;  
}
