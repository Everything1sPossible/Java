$(function(){
	//点击修改按钮
	$("#updateBtn").click(function(){
		var url = "/GaoXiaoPaiKe/xgmm.html";
		var loginId = $("#loginId").val();
		var Newpassword = $("#Newpassword").val();
		var Newpassword2 = $("#Newpassword2").val();
		if(Newpassword == ''){
			common.alert("修改密码","请输入新密码!");
			return;
		}
		if(Newpassword2 == ''){
			common.alert("修改密码","请确认新密码!");
			return;
		}
		if(Newpassword != Newpassword2){
			common.alert("修改密码","两次密码输入不一致,请重新输入!");
			return;
		}
		param = {"loginId":loginId,"Newpassword":Newpassword};
		$.ajax({
			url:url,
			data:param,
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.flag == "0"){
					common.alert("修改密码","网络出现故障,密码修改失败,请稍后重试!");
				}
				if(data.flag == "1"){
					common.alert("修改密码","密码修改成功!");
				}
			}
		});
	});
	//点击返回按钮
	$("#backBtn").click(function(){
		window.location.href="/GaoXiaoPaiKe/main2.html";
	});
});