$(function(){
	//初始化新增页面的父菜单下拉列表
	main.addMenuList();
	//初始化单选按钮与下拉列表的级联
	main.radioCheck();
	//初始化一级菜单不允许更改级别
	main.updLevelRadio();
	//返回按钮
	$("#backBtn").click(function(){
		window.location.href = "/GaoXiaoPaiKe/menuView.html";
	});
	//添加按钮
	$("#addBtn").click(function(){
		main.addMenu();
	});
	//修改按钮
	$("#updBtn").click(function(){
		main.updMenu();
	});
});
var main = {
		updLevelRadio:function(){
			var updBtn = $("button.updBtn").html();
			if(updBtn == "修改"){
				if($("#hideLevel").val()=="1"){
					$('div.levelradio').html("");//先清空
					$('div.levelradio').append('<input type="radio" id="menuLevel1" name="menuLevel" checked value="1">一级');
				}
			}
		},
		addMenuList:function(){
			var updBtn = $("button.updBtn").html();
			if(updBtn == "添加"){
				main.FMenuList();
			}
		},
		//新增页面的父菜单下拉列表
		FMenuList:function(){
			$.ajax({
				url:"/GaoXiaoPaiKe/getFMenu.html",
				type:"post",
				dataType:"json",
				success:function(data){
					$('select.selectpicker').html("");//先清空
					//拼接请选择
					$('select.selectpicker').append('<option class="choice" value="">==请选择==</option>');
					for(var i=0;i<data.length;i++){
						main.FMenuHtml(data[i].menuid,data[i].menuname);
					}
					// 缺一不可
			        $('#menuFather').selectpicker('refresh');
			        $('#menuFather').selectpicker('render');
				}
			});
		},
		FMenuHtml:function(menuId,menuName){
			var html = [
			          '<option value="',menuId,'">',menuName,'</option>'
			          ].join('');
			$('select.selectpicker').append(html);
		},
		radioCheck:function(){
		       var radio1=$("#menuLevel1").attr('checked');
		       var radio2=$("#menuLevel2").attr('checked');
		       if(radio1=="checked"){
		           $(".selectpicker").attr("disabled", "disabled");
		           $('#menuFather').selectpicker('refresh');
		           $('#menuFather').selectpicker('render');
		       }
		       if(radio1==undefined&&radio2==undefined){
		           $(".selectpicker").attr("disabled", "disabled");
		           $('#menuFather').selectpicker('refresh');
		           $('#menuFather').selectpicker('render');
		       }
		       $("#menuLevel1").click(function(){
		    	   var updBtn = $("button.updBtn").html();
				   if(updBtn == "修改" || updBtn == "添加"){
					   $('select.selectpicker').append('<option class="option0" selected value="0">一级菜单</option>');
				   }
		           $(".selectpicker").attr("disabled", "disabled");
		           $('#menuFather').selectpicker('refresh');
		           $('#menuFather').selectpicker('render');
		       });
		       $("#menuLevel2").click(function(){
		    	   var updBtn = $("button.updBtn").html();
				   if(updBtn == "修改" || updBtn == "添加"){
					   $('.option0').attr("selected",false);
					   $('.option0').hide();
				   }
		           $(".selectpicker").attr("disabled", false);
		           $('#menuFather').selectpicker('refresh');
		           $('#menuFather').selectpicker('render');
		       });
		},
		//添加菜单
		addMenu:function(){
			var menuName = $("#menuName").val();
			var menuUrl = $("#menuUrl").val();
			var menuLevel = $("input:radio[name='menuLevel']:checked").val();
			var menuFather = $("#menuFather").val();
			var menuState = $("input:radio[name='menuState']:checked").val();
			var param = {"menuName":menuName,"menuUrl":menuUrl,"menuLevel":menuLevel,"menuFather":menuFather,"menuState":menuState};
			$.ajax({
				url:"/GaoXiaoPaiKe/addMenu.html",
				data:JSON.stringify(param),
				type:"post",
				dataType:"json",
				contentType: "application/json",
				success:function(data){
					common.alert("",data.msg);
				}
			});
		},
		//修改菜单
		updMenu:function(){
			var menuId = $("#menuId").val();
			var menuName = $("#menuName").val();
			var menuUrl = $("#menuUrl").val();
			var menuLevel = $("input:radio[name='menuLevel']:checked").val();
			var menuFather = $("#menuFather").val();
			var menuState = $("input:radio[name='menuState']:checked").val();
			var param = {"menuId":menuId,"menuName":menuName,"menuUrl":menuUrl,"menuLevel":menuLevel,"menuFather":menuFather,"menuState":menuState};
			$.ajax({
				url:"/GaoXiaoPaiKe/updateMenu.html",
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