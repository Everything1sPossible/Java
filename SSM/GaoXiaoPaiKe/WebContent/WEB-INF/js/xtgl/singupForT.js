$(function(){
	//初始化对应菜单下拉列表
	main.loginMenuList();
	//注册按钮
	$("#addBtn").click(function(){
		main.signup();
	});
	//返回按钮
	$("#backBtn").click(function(){
		window.location.href="/GaoXiaoPaiKe/teacherView.html";
	});
});

var main = {
		//对应菜单下拉列表
		loginMenuList:function(){
			$.ajax({
				url:"/GaoXiaoPaiKe/loginMenuList.html",
				type:"post",
				dataType:"json",
				success:function(data){
					$("#loginMenu").html("");//先清空
					$("#loginMenu").append("<option value='0'>无</option>");//拼接上"无"
					for(var i=0;i<data.length;i++){
						$("#loginMenu").append("<option value='"+data[i].menuid+"'>"+data[i].menuname+"</option>");
					}
					//缺一不可,刷新
			        $('#loginMenu').selectpicker('refresh');
			        $('#loginMenu').selectpicker('render');
				}
			});
		},
       //注册账号
       signup:function(){
    	   var loginName = $("#loginName").val();
    	   var username = $("#username").val();
    	   var password = $("#password").val();
    	   var password1 = $("#password1").val();
    	   var tid = $("#tid").val();
    	   var loginUser = $("#loginUser").val();
    	   var loginMenuArray = $("#loginMenu").val();//得到一个数组,bootstrap-select多选的特性
    	   if(password != password1){
    		   common.alert("","两次密码不一致!");
    		   return;
    	   }
    	   var loginMenu = loginMenuArray.join(",");//数组转成String,用","分隔
    	   var param = {"loginName":loginName,"username":username,"password":password,"tid":tid,"loginUser":loginUser,"loginMenu":loginMenu};
    	   $.ajax({
    		   url:"/GaoXiaoPaiKe/singupNewLogin.html",
    		   data:JSON.stringify(param),
			   type:"post",
			   dataType:"json",
			   contentType: "application/json",
    		   success:function(data){
    			   common.alert("",data.msg);
    		   }
    	   });
       }
}