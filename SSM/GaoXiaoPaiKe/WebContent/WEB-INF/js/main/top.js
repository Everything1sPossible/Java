$(function(){
	$("#logOut").click(function(){
        if(confirm("确定要退出系统吗?")){
            return true;
        }
        else{
            return false;
        }
    });
});