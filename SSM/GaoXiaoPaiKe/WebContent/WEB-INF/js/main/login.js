$(function(){
	$("#button").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var kaptchaCode = $("#kaptchaCode").val();
		var url = "/GaoXiaoPaiKe/checkLogin.html";
		var param = {"username":username,"password":password,"kaptchaCode":kaptchaCode};
		$.ajax({
			url:url,
			data:param,
			type:"post",
			dataType:"json",
			success:function(data){
				var flag = data.flag;
				var message = data.message;
				var menuId = data.menuId;
				if(flag == "0"){
					$("#msgDiv").html("");
					$("#msgDiv").html(message);
					$("#msgDiv").show();
					$("#kaptchaCode").val("");
					$("#kaptcha").attr("src","Kaptcha.jpg?"+Math.floor(Math.random()*100));
				}
				if(flag == "1"){
					$("#msgDiv").html("");
					$("#msgDiv").html(message);
					$("#msgDiv").show();
					window.location.href = "/GaoXiaoPaiKe/main.html?menuId="+menuId;
				}
			}
		});
	});
});
function changeImg(){
	$("#kaptcha").attr("src","Kaptcha.jpg?"+Math.floor(Math.random()*100));
}
document.onkeydown=function(){
    if ((event.keyCode == 13) ) {
    	$("#button").click();
    }
}