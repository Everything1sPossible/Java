package com.wfu.system.tools;

import java.util.List;

import com.github.pagehelper.Page;
import com.wfu.web.domain.PageResult;

public class PageBeanUtil {
	
	@SuppressWarnings("rawtypes")
	private static PageResult result ;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static<T> PageResult<T> toPageResult(List<T> datas){
		if(result == null){
			result = new PageResult<T>();
		}
		if(datas instanceof Page){
			Page page = (Page)datas;
			result.setDataList(page.getResult());
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setPages(page.getPages());
			result.setTotal(page.getTotal());
		}
		else{
			result.setPageNo(1);  
            result.setPageSize(datas.size());  
            result.setDataList(datas);  
            result.setTotal(datas.size()); 
		}
		return result;
	}
}
