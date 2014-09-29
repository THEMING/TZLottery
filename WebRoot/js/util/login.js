
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
