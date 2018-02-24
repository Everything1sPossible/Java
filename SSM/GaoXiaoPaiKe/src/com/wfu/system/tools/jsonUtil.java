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
 * json������
 *
 */
public class jsonUtil {
	
	/**
	 * Objectת����json
	 * @param obj  :: �����Ķ���
	 * @param formater :: ʱ���ʽ
	 * @return
	 */
	public static String objToJson(Object obj,SimpleDateFormat formater,String...filterFields){
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(filterFields == null){
				filterFields = new String[]{""};
			}
			mapper.setDateFormat(formater);//����ʱ���ʽ
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ת����json����....");
		}
	}
	/**
	 * Objectת����json
	 * @param obj  :: �����Ķ���
	 * @param formater :: ʱ���ʽ
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
	 * Map/Listת��json
	 * @param obj:��ת����obj
	 * @param filterFields:���˵��ֶ�
	 * @param dateFormat:���ڸ�ʽ��
	 * @return
	 */
	public static String objectToJson(Object obj,String[] filterFields,String dateFormat){
		JsonConfig config = new JsonConfig();
		if(filterFields != null){
			config.setExcludes(filterFields);
		}
		//1 �������ڸ�ʽ
        if(dateFormat==null || dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";//Ĭ��Ϊ������
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
	 * ����ҳʵ������ת��json,�ɹ����ֶ�
	 * @param result:��ҳʵ������
	 * @param filterFields:��Ҫ���˵�����
	 * @param dateFormat:���ڸ�ʽ��
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
		//1 �������ڸ�ʽ
        if(dateFormat==null || dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";//Ĭ��Ϊ������
        }
		if(dateFormat != null){
			config.registerJsonValueProcessor(Date.class, new JsonDateTransform(dateFormat));
		}
		return JSONObject.fromObject(jsonMap, config).toString();
	}
	/**
	 * ����ҳʵ������ת��json,�ɹ����ֶ�
	 * @param result:��ҳʵ������
	 * @param filterFields:��Ҫ���˵�����
	 * @param dateFormat:���ڸ�ʽ��
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
		//1 �������ڸ�ʽ
        if(dateFormat==null || dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";//Ĭ��Ϊ������
        }
		if(dateFormat != null){
			config.registerJsonValueProcessor(Date.class, new JsonDateTransform(dateFormat));
		}
		return JSONObject.fromObject(jsonMap, config).toString();
	}
}
