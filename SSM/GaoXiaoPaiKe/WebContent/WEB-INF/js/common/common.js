var common = {
    /***
	 * bootstrap 弹出框..
	 * @param {} title 标题
	 * @param {} msg   内容主体
	 */
	alert : function(title,msg){
		//先移除
		$('.alertwin').remove();
		title = title ? title : '提示';
		// 针对于固定于顶部的导航条特价上style="margin-top: 80px",让静态框往下来点方便观看
		var html = [
		'<div class="modal fade alertwin" tabindex="-1" role="dialog"', 
		'  aria-hidden="true"  style="margin-top: 80px">', 
		'   <div class="modal-dialog">', 
		'      <div class="modal-content">', 
		'         <div class="modal-header">', 
		'            <button type="button" class="close"',  
		'               data-dismiss="modal" aria-hidden="true">', 
		'                  &times;', 
		'            </button>', 
		'            <h4 class="modal-title" id="myModalLabel">', 
		title, 
		'            </h4>', 
		'         </div>', 
		'         <div class="modal-body">', 
		msg, 
		'         </div>', 
		'         <div class="modal-footer">', 
		'            <button type="button" class="btn btn-primary"',  
		'               data-dismiss="modal">关闭', 
		'            </button>', 
		'         </div>', 
		'      </div>', 
		'</div>'].join('');
		$(html).appendTo('body');
		$('body').find('.alertwin').delegate('','keydown',function(){
			if(event.keyCode == 13){
				$('.alertwin').remove();
				$('.modal-backdrop:last').remove();
			}
		});
		//去掉滚动栏，没有其他的作用
		$('body').css('margin-right','-0px');
		$('.alertwin').modal({});
		$('.alertwin').on('hide.bs.modal', function () {
			$('.alertwin').remove();
			$('.modal-backdrop:last').remove();
	   });	
	},
	/***
	 * 分页
	 */
    pagination:function(pageNo,pages,url,fn){
    	$('.pagerwin').remove();//先移除
    	var html = [
                '<ul class="pager pagerwin">',
                '<li class="previous"><a class="firstPage" href="javascript:void(0);"> 首页</a></li>',
                '<li class="previous"><a class="prePage" href="javascript:void(0);"><span aria-hidden="true">&laquo;</span>上一页</a></li>',
                '<span class="page-number">第', pageNo, '页/共',pages,'页</span>',
                '<input type="hidden" id="pageNum" value="',pageNo,'">',
                '<li class="next"><a class="lastPage" href="javascript:void(0);"> 末页</a></li>',
                '<li class="next"><a class="nextPage" href="javascript:void(0);">下一页<span aria-hidden="true">&raquo;</span></a></li>',
                '</ul>'
        ].join('');
        $(html).appendTo('#pageShow');
        //首页
        $('body').find('a.firstPage').delegate('','click',function(){
        	if(fn){
        		fn(url,1);
        	}
        });
        //上一页
        $('body').find('a.prePage').delegate('','click',function(){
        	if(fn){
        		fn(url,pageNo-1);
        	}
        });
        //末页
    	$('body').find('a.lastPage').delegate('','click',function(){
    		if(fn){
    			fn(url,pages);
    		}
    	});
        //下一页
    	$('body').find('a.nextPage').delegate('','click',function(){
    		if(fn){
    			fn(url,pageNo+1);
    		}
    	});
    },
	/**
	 * 查询无数据
	 */
	notHasData:function(title){
		title = title ? title : "当前无数据";
		var html = [
                '<tr>',
                '<td colspan="100" align="center">',
                title,
                '</td>',
                '</tr>'
        ].join('');
		$($('.table').find('tbody')[0]).append(html);
	}
}