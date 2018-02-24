var main = {
		//初始化
		init:function(){
			var url = "/GaoXiaoPaiKe/getAllMenu.html";
			main.list(url,null);
		},
		//ajax获取数据
		list:function(url,pageNum){
			var qmenuName = $("#qmenuName").val();
			var param = {"qmenuName":qmenuName,"pageNum":pageNum};
			$.ajax({
				url:url,
				data:JSON.stringify(param),//必须加上,不然报400
				contentType: "application/json",//必须加上,表明request的数据类型是json
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.dataList == null || data.dataList == ''){
						$($('.table').find('tbody')[0]).html('');//清空一下
						common.notHasData('');
					}
					else{
						$($('.table').find('tbody')[0]).html('');//清空一下
						var dataList = data.dataList;
						var count = (data.pageNo - 1)*data.pageSize; //序号,方便在表格中查看数据总数
						for(var i=0;i<dataList.length;i++){
							main.dataHtml(++count,dataList[i].menuId,dataList[i].menuName,dataList[i].menuUrl,dataList[i].menuLevel,dataList[i].menuFather,dataList[i].menuState);
						}
						common.pagination(data.pageNo,data.pages,url,main.list);
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
        dataHtml:function(count,menuId,menuName,menuUrl,menuLevel,menuFather,menuState){
        	var html = [
                    '<tr>',
	                    '<td>',
	                    count,
	                    '</td>',
	                    '<td>',
	                       menuId,
	                    '</td>',
	                    '<td>',
	                       menuName,
	                    '</td>',
	                    '<td>',
	                       menuUrl,
	                    '</td>',
		                '<td>',
		                   menuLevel,
		                '</td>',
		                '<td>',
		                   menuFather,
		                '</td>',
		                '<td>',
		                   menuState,
		                '</td>',
		                '<td>',
		                   '<a href="/GaoXiaoPaiKe/updateMenuView.html?menuId=',menuId,'">',
		                   '编辑',
		                   '</a>',
		                '</td>',
                    '</tr>',
            ].join('');
        	$($('.table').find('tbody')[0]).append(html);
        }
}
$(function(){
	main.init();//初始化
	//查询按钮
	$("#queryBtn").click(function(){
		var url = "/GaoXiaoPaiKe/getAllMenu.html";
		main.list(url,'');
	});
	//添加按钮
	$("button.addBtn").click(function(){
		window.location.href = "/GaoXiaoPaiKe/updateMenuView.html";
	});
});