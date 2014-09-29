$(function() {
//	var s = "123";
//	$("#tcp_changjian").append("<input id='chuangjian_userId' type='hidden' value='" + s +"'/>");
//	$("#tcp_bangding").append("<input id='bangding_userId' type='hidden' value='" + s +"'/>");
//	$("#qq_zhuce").click();
	$("#qq_chuangjian").click(function(){
		var username = $("#qq_name").val();
		var pwd = $("#qq_pwd1").val();
		var email = $("#qq_email").val();
		//用户名不存在
		jQuery.ajax({
	        url: "/qqzhuce.htm?action=qqZhuCe",
	        type: 'post',
	        data: {"nickname":username, "password":pwd, "email":email},
	        dataType: 'json',
	        success: function(data, textStatus) {
				if(data.message == "0")
				{
					$("#chuangjian_h").hide();
					$("#chuangjian_s").css("display", "block");
					$("#chuangjian_s").text("用户已存在,请重新填写!");
				}
				if(data.message == "1")
				{
					$("#chuangjian_h").hide();
					$("#chuangjian_s").css("display", "block");
					$("#chuangjian_s").text("用户名不可用,请重新填写!&nbsp;6-16位字符，可由中英文、数字及_、-组成");
				}
				if(data.message == "2")
				{
					$("#chuangjian_h").hide();
					$("#chuangjian_s").css("display", "block");
					$("#chuangjian_s").text("密码格式错误或为空!&nbsp;6-16位字符，可由中英文、数字及_、-组成");
				}
				if(data.message == "3")
				{
					$("#chuangjian_h").hide();
					$("#chuangjian_s").css("display", "block");
					$("#chuangjian_s").text("邮箱格式错误或为空!");
				}
				if(data.message == "成功")
				{
					alert("恭喜你！创建用账号成功！");
					location.href = "/customer/"; 
				}
			},
	        error: function() {
				logger.error('Ajax call failed!');
				alert("注册失败");
			}
   	 	});
	});
	
	//用户名已存在
	$("#qq_bangding").click(function(){
		var username = $("#qq_nickname").val();
		var pwd = $("#qq_pwd2").val();
		jQuery.ajax({
	        url: "/qqbangding.htm?action=qqBangDing",
	        type: 'post',
	        data: {"nickname":username, "password":pwd},
	       	dataType: 'json',
	        success: function(data, textStatus) {
				if(data.message == "成功")
				{
					alert("恭喜你！绑定QQ成功！");
					location.href = "/customer/"; 
				}
				if(data.message == "0")
				{
					$("#bangding_h").hide();
					$("#bangding_s").css("display", "block");
					$("#bangding_s").text("用户名不存在!");
				}
				if(data.message == "1")
				{
					$("#bangding_h").hide();
					$("#bangding_s").css("display", "block");
					$("#bangding_s").text("改账号已绑定QQ!");
				}
				if(data.message == "2")
				{
					$("#bangding_h").hide();
					$("#bangding_s").css("display", "block");
					$("#bangding_s").text("密码错误!");
				}
			},
	        error: function() {
				logger.error('Ajax call failed!');
			}
   	 	});
	});
	
	
});