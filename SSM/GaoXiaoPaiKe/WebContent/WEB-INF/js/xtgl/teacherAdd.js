$(function(){
	//初始化课程下拉列表
	main.getCourseList();
	//初始化课程与教师角色的级联
	main.radioCheck();
	//添加按钮
	$("#addBtn").click(function(){
		main.addTeacher();
	});
	//返回按钮
	$("#backBtn").click(function(){
		window.location.href = "/GaoXiaoPaiKe/teacherView.html";
	});
});

var main = {
		getCourseList:function(){
			$.ajax({
				url:"/GaoXiaoPaiKe/getCourseList.html",
				type:"post",
				dataType:"json",
				success:function(data){
					$("#course").html("");//先清空
					for(var i=0;i<data.length;i++){
						$("#course").append("<option value="+data[i].conum+">"+data[i].coname+"</option>");
					}
					$('#course').selectpicker('refresh');
			        $('#course').selectpicker('render');
				}
			});
		},
		//添加老师
		addTeacher:function(){
			var tname = $("#tname").val();
			var tnumber = $("#tnumber").val();
			var course = $("#course").val();
			var department = $("#department").val();
			var role = $("input:radio[name='role']:checked").val();
			var param = {"tname":tname,"tnumber":tnumber,"course":course,"department":department,"role":role};
			$.ajax({
				url:"/GaoXiaoPaiKe/addTeacher.html",
				data:JSON.stringify(param),
				type:"post",
				dataType:"json",
				contentType: "application/json",
				success:function(data){
					common.alert("",data.msg);
				}
			});
		},
        //课程与教师角色的级联
        radioCheck:function(){
        	var role1 = $("#role1").attr("checked");
            var role2 = $("#role2").attr("checked");
            if(role1==undefined&&role2==undefined){
            	$(".selectpicker").attr("disabled", true);
                $("#course").selectpicker('refresh');
                $("#course").selectpicker('render');
            }
//            if(role1=="checked"){
//            	$("#course option:first").attr("selected", true);
//                $(".selectpicker").attr("disabled", true);
//                $('#course').selectpicker('refresh');
//                $('#course').selectpicker('render');
//            }
            $("#role1").click(function(){
            	$("#course option:first").attr("selected", true);
                $(".selectpicker").attr("disabled", "disabled");
                $('#course').selectpicker('refresh');
                $('#course').selectpicker('render');
            });
            $("#role2").click(function(){
                $(".selectpicker").attr("disabled", false);
                $('#course').selectpicker('refresh');
                $('#course').selectpicker('render');
            });
        }
}