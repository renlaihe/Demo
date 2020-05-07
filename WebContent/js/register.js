var obj = {"email":false,"password":false,"nickname":false,"code":false};

//	邮箱验证
$(function(){
	$("#email").blur(function(){
		
		obj.email = false;
		
		var email = $("#email").val();
		
//		前端
//		非空
		if(email.trim().length == 0){
			$("#p_email").html("邮箱不能为空").css("color","red");
			return;
		}
		
//		格式检测
		var regexp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if (!regexp.test(email)) {
			$("#p_email").html("邮箱格式不正确").css("color","red");
			return;
		}
		
//		服务器端
		$("#p_email").html("正在检测......").css("color","green");
		$.post(
			"validateEmail",
			{"email":email},
			function(data){
				if (data) {
					$("#p_email").html("邮箱可以注册").css("color","green");
					obj.email = true;
				} else {
					$("#p_email").html("邮箱已注册").css("color","red");	
				}
			}
		);
	});
});

//	密码验证
$(function(){
	$("#password").blur(function(){
		
		obj.password = false;
	
		var password = $("#password").val();
		
//		非空检测
		if (password.trim() == "") {
			$("#p_password").html("密码不能为空").css("color","red");
			return;
		}
		
//		格式检测
		var regexp = /^[0-9]{6,10}$/;
		if (regexp.test(password)) {
			$("#p_password").html("密码格式正确").css("color","green");
			obj.password = true;
		} else {
			$("#p_password").html("密码格式不正确").css("color","red");
		}
		
	});
});

//	昵称验证
$(function(){
	$("#nickname").blur(function(){
		
		obj.nickname = false;
		
		var nickname = $("#nickname").val();
		
		if (nickname.trim() == "") {
			$("#p_nickname").html("昵称不能为空").css("color","red");
			return;
		}
		
		var regexp = /^[\u4e00-\u9fa5]{2,6}$/;
		if (regexp.test(nickname)) {
			$("#p_nickname").html("昵称格式正确").css("color","green");
			obj.nickname = true;
		} else {
			$("#p_nickname").html("昵称格式不正确").css("color","red");
		}
		
	});
});

//	生成验证码
function createCode(){
	$.post(
		"createCode",
		function(data){
			if (data) {
				$("#p_code").html("验证码已发送").css("color","green");
			}
		}
	);
}	

//	验证码验证
$(function(){
	$("#code").blur(function(){
		
		obj.code = false;
		
		var code = $("#code").val();
		
		if (code.trim().length == 0) {
			$("#p_code").html("验证码不能为空").css("color","red");
			return;
		}
		
		var regexp = /^[0-9]{6}$/;
		if (!regexp.test(code)) {
			$("#p_code").html("验证码格式不正确").css("color","red");
			return;
		}
		
		$.post(
			"checkCode",
			{"code":code},
			function(data){
				if (data) {
					$("#p_code").html("验证码正确").css("color","green");
					obj.code = true;
				} else {
					$("#p_code").html("验证码不正确").css("color","red");
				}
			}
		);
		
	});
});

//	表单提交处理
function putOn(){
//	检查表单项是否通过验证
	if (obj.email&&obj.password&&obj.nickname&&obj.code) {
		document.forms[0].submit();
	} else {
		alert("请检查表单项");
		return false;
	}
}













