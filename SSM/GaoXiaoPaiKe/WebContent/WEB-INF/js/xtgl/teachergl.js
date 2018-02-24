$(function(){
	//初始化加载教师列表
	main.init();
	//复选按钮与删除按钮的级联(PS:此处的初始化不起作用没搞明白?)
//	main.checkboxWithDelBtn();
	//查询按钮
	$("button.queryBtn").click(function(){
		var url = "/GaoXiaoPaiKe/queryByPage.html";
		main.getTeacherList(url,'');
	});
	//添加按钮
	$("button.addBtn").click(function(){
		window.location.href = "/GaoXiaoPaiKe/addTeacherView.html";
	});
	//删除按钮
	$("button.delBtn").click(function(){
		main.deleteTeacher();
	});
});

var main = {
		//初始化
		init:function(){
			var url = "/GaoXiaoPaiKe/queryByPage.html";
			main.getTeacherList(url,null);
		},
		//加载教师列表
		getTeacherList:function(url,pageNum){
			var qtname = $("#qtname").val();
			var qtnumber = $("#qtnumber").val();
			var param = {"qtname":qtname,"qtnumber":qtnumber,"pageNum":pageNum};
			$.ajax({
				url:url,
				data:JSON.stringify(param),
				type:"post",
				dataType:"json",
				contentType: "application/json",
				success:function(data){
					if(data.dataList == null || data.dataList == ''){
						$($('.table').find('tbody')[0]).html('');//清空一下
						common.notHasData('');
					}
					else{
						$($('table.table').find('tbody')[0]).html('');//清空一下
						var dataList = data.dataList;
						var count = (data.pageNo - 1)*data.pageSize; //序号,方便在表格中查看数据总数
						for(var i=0;i<dataList.length;i++){
							main.dataHtml(++count,dataList[i].tid,dataList[i].tname,dataList[i].tnumber,dataList[i].role,dataList[i].course,dataList[i].department);
						}
						//复选按钮与删除按钮的级联(PS:为什么上面的初始化不起作用没搞明白?)
						var checkboxOen = $('input[name="checkboxOne"]');
						checkboxOen.on("click", function(){
				            $("#allcheck").prop("checked",$("input[name='checkboxOne']").length == $("input[name='checkboxOne']:checked").length ? true : false);
				            $("button.delBtn").attr("disabled",$("input[name='checkboxOne']:checked").length>0?false:true);
						});
						$("#allcheck").click(function() {
				            $('input[name="checkboxOne"]').prop("checked",this.checked);
				            $("button.delBtn").attr("disabled",$("input[name='checkboxOne']:checked").length>0?false:true);
				        });
						//添加分页
						common.pagination(data.pageNo,data.pages,url,main.getTeacherList);
						if(data.pageNo == 1){
							$("ul.pager li.previous").addClass('disabled');
						}
						if(data.pageNo == data.pages){
							$("ul.pager li.next").addClass('disabled');
						}
					}
				}
			});
		},
		//数据html
        dataHtml:function(i,tid,tname,tnumber,role,course,department){
        	var html = [
                    '<tr>',
                        '<td>',
                        '<input type="checkbox" name="checkboxOne" value="'+tid+'">',
                        '</td>',
	                    '<td>',
	                       i,
	                    '</td>',
	                    '<td>',
	                      tname,
	                    '</td>',
	                    '<td>',
	                      tnumber,
	                    '</td>',
	                    '<td>',
	                      role,
	                    '</td>',
		                '<td>',
		                  course,
		                '</td>',
		                '<td>',
		                  department,
		                '</td>',
		                '<td>',
		                   '<a href="/GaoXiaoPaiKe/singupView.html?tid=',tid,'&tname=',tname,'&tnumber=',tnumber,'&role=',role,'">',
		                   '注册账号',
		                   '</a>',
		                '</td>',
                    '</tr>',
            ].join('');
        	$($('.table').find('tbody')[0]).append(html);
        },
        //复选按钮与删除按钮的级联
		checkboxWithDelBtn:function(){
			var checkboxOen = $('input[name="checkboxOne"]');
			checkboxOen.on("click", function(){
				alert(2);
	            $("#allcheck").prop("checked",$("input[name='checkboxOne']").length == $("input[name='checkboxOne']:checked").length ? true : false);
	            $("button.delBtn").attr("disabled",$("input[name='checkboxOne']:checked").length>0?false:true);
			});
//			checkboxOen.click(function(){
//	        	alert(2);
//	            $("#allcheck").prop("checked",$("input[name='checkboxOne']").length == $("input[name='checkboxOne']:checked").length ? true : false);
//	            $("button.delBtn").attr("disabled",$("input[name='checkboxOne']:checked").length>0?false:true);
//	        });
			$("#allcheck").click(function() {
	            $('input[name="checkboxOne"]').prop("checked",this.checked);
	            $("button.delBtn").attr("disabled",$("input[name='checkboxOne']:checked").length>0?false:true);
	        });
		},
		//删除教师
        deleteTeacher:function(){
        	var value = "";
            var check=$("input[name='checkboxOne']:checked");
            for(var i=0;i<check.length;i++){
                value+=$(check[i]).val()+"@";
            }
            var param = {"tids":value};
            $.ajax({
            	url:"/GaoXiaoPaiKe/deleteTeacher.html",
            	type:"post",
            	data:JSON.stringify(param),
            	dataType:"json",
            	contentType: "application/json",
            	success:function(data){
//            		common.alert("",data.msg);
            		window.location.href="/GaoXiaoPaiKe/teacherView.html";
            	}
            });
        }
}