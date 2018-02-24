$(function(){
	//初始化专业下拉列表
	main.getMajorList();
	//初始化课程下拉列表
	main.getCourseList();
	//初始化专业与课程的级联
	main.majorWithCourse();
	//添加按钮
	$("#addBtn").click(function(){
		main.addMajorCourse();
	});
	//返回按钮
	$("#refreshBtn").click(function(){
		window.location.href = "/GaoXiaoPaiKe/courseForMajorView.html";
	});
});
var main = {
    //ajax加载专业
	getMajorList:function(){
		var department = $("#department").val();
		var param = {"department":department};
		$.ajax({
			url:"/GaoXiaoPaiKe/getMajorList.html",
			data:JSON.stringify(param),
			dataType:"json",
			type:"post",
			contentType: "application/json",
			success:function(data){
				$("#major").html("");//先清空
				$("#major").append("<option value='0'>无</option>");//拼接上"无"
				for(var i=0;i<data.length;i++){
					$("#major").append("<option value='"+data[i].mid+"'>"+data[i].mname+"</option>");
				}
				//缺一不可,刷新
		        $('#major').selectpicker('refresh');
		        $('#major').selectpicker('render');
			}
		});
	},
	//ajax加载课程
	getCourseList:function(){
        $.ajax({
        	url:"/GaoXiaoPaiKe/getCourseListForKcgl.html",
			dataType:"json",
			type:"post",
			success:function(data){
				$("#course").html("");//先清空
				$("#course").append("<option selected>请先选择专业</option>");//拼接上"无"
				for(var i=0;i<data.length;i++){
					$("#course").append("<option value='"+data[i].coid+"'>"+data[i].coname+"</option>");
				}
				//缺一不可,刷新
		        $('#course').selectpicker('refresh');
		        $('#course').selectpicker('render');
			}
		});
	},
	//专业与课程的级联
	majorWithCourse:function(){
		//先灰化
		$("#course").attr("disabled", true);
        $("#course").selectpicker('refresh');
        $("#course").selectpicker('render');
        //专业change事件
        $("#major").change(function(){
            $("#course").attr("disabled", false);
            $("#course option:first").remove();
            $("#course option:first").attr("selected",true);
            $("#course").selectpicker('refresh');
            $("#course").selectpicker('render');
        });
	},
	//添加
	addMajorCourse:function(){
		var majorId = $("#major").val();
		var courseIdArray = $("#course").val();
		var grade = $("#grade").val();
		if(majorId == 0){
			common.alert("","请选择专业!");
			return false;
		}
		if(courseIdArray == null && courseIdArray.length == 0){
			common.alert("","请选择课程!");
			return false;
		}
		if(grade == null){
			common.alert("","请选择年级!");
			return false;
		}
		var courseIds = courseIdArray.join(",");
		var param = {"mId":majorId,"coIds":courseIds,"grade":grade};
		$.ajax({
			url:"/GaoXiaoPaiKe/addMajorCourse.html",
			data:JSON.stringify(param),
			dataType:"json",
			type:"post",
			contentType: "application/json",
			success:function(data){
				common.alert("",data.msg);
			}
		});
	}
}