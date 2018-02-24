package com.wfu.system.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wfu.web.domain.PageResult;

/**
 * json工具类
 *
 */
public class jsonUtil {
	
	/**
	 * Object转化成json
	 * @param obj  :: 操作的对象
	 * @param formater :: 时间格式
	 * @return
	 */
	public static String objToJson(Object obj,SimpleDateFormat formater,String...filterFields){
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(filterFields == null){
				filterFields = new String[]{""};
			}
			mapper.setDateFormat(formater);//设置时间格式
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("转换成json出错....");
		}
	}
	/**
	 * Object转化成json
	 * @param obj  :: 操作的对象
	 * @param formater :: 时间格式
	 * @return
	 */
	public static String returnJson(Object obj,String formater,String...filterFields){
		if(formater == null || formater.equals("")){
			formater = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formater);
		return objToJson(obj,format,filterFields);
	}
	/**
	 * Map/List转成json
	 * @param obj:被转化的obj
	 * @param filterFields:过滤的字段
	 * @param dateFormat:日期格式化
	 * @return
	 */
	public static String objectToJson(Object obj,String[] filterFields,String dateFormat){
		JsonConfig config = new JsonConfig();
		if(filterFields != null){
			config.setExcludes(filterFields);
		}
		//1 处理日期格式
        if(dateFormat==null || dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";//默认为年月日
        }
		if(dateFormat != null){
			config.registerJsonValueProcessor(Date.class, new JsonDateTransform(dateFormat));
		}
		if(obj instanceof List) {
            return JSONArray.fromObject(obj, config).toString();
        }else {
            return JSONObject.fromObject(obj, config).toString();
        }
	}
	
	/**
	 * 将分页实体数据转成json,可过滤字段
	 * @param result:分页实体数据
	 * @param filterFields:需要过滤的数据
	 * @param dateFormat:日期格式化
	 * @return
	 */
	public static<T> String pageToJson(PageResult<T> result,String[] filterFields,String dateFormat){
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList",result.getDataList() );
		jsonMap.put("pageNo",result.getPageNo() );
		jsonMap.put("pageSize",result.getPageSize() );
		jsonMap.put("total",result.getTotal() );
		jsonMap.put("pages",result.getPages() );
		
		JsonConfig config = new JsonConfig();
		if(filterFields != null){
			config.setExcludes(filterFields);
		}
		//1 处理日期格式
        if(dateFormat==null || dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";//默认为年月日
        }
		if(dateFormat != null){
			config.registerJsonValueProcessor(Date.class, new JsonDateTransform(dateFormat));
		}
		return JSONObject.fromObject(jsonMap, config).toString();
	}
	/**
	 * 将分页实体数据转成json,可过滤字段
	 * @param result:分页实体数据
	 * @param filterFields:需要过滤的数据
	 * @param dateFormat:日期格式化
	 * @return
	 */
	public static<T> String pageToJsonNotDatelist(PageResult<T> result,String[] filterFields,String dateFormat){
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("pageNo",result.getPageNo() );
		jsonMap.put("pageSize",result.getPageSize() );
		jsonMap.put("total",result.getTotal() );
		jsonMap.put("pages",result.getPages() );
		
		JsonConfig config = new JsonConfig();
		if(filterFields != null){
			config.setExcludes(filterFields);
		}
		//1 处理日期格式
        if(dateFormat==null || dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";//默认为年月日
        }
		if(dateFormat != null){
			config.registerJsonValueProcessor(Date.class, new JsonDateTransform(dateFormat));
		}
		return JSONObject.fromObject(jsonMap, config).toString();
	}
}
