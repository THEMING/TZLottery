
/* 登录注册*/

(function ($){
	$.Login={
		url : "/customer/login.htm",
		_request : function(param){
			$.getJSON($.Login.url,param,$.Login.callback);
		},
		callback : function(){}
	};
	
})(jQuery);

(function ($) {
	$.Reg={
		url : "/customer/register.htm",
		_request : function(param){
			$.getJSON($.Reg.url, param, $.Reg.callback);
		},
		callback : function(){}
	};
})(jQuery);

function login2(){
	$.Login2._request({nickname:$("#nickname1").val(),password:$("#password1").val(),mngunm:$("#mngunm1").val()});
}
function reg1(){
	location.href="/customer/register.htm";
}