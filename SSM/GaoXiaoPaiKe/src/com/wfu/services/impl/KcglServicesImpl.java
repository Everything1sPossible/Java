package com.wfu.services.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfu.mybatis.mapper.KcglMapper;
import com.wfu.services.KcglServices;
import com.wfu.system.tools.jsonUtil;
import com.wfu.web.domain.MajorCourse;

@Service
public class KcglServicesImpl implements KcglServices {
	
	private static final Logger logger = LoggerFactory.getLogger(KcglServicesImpl.class);

	@Override
	public String getMajorList(Object department) {
		try {
			List<Map<String, Object>> majorList = kcglMapper.getMajorList(department);
			String json = jsonUtil.objectToJson(majorList, null, null);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String getCourseList() {
		try {
			List<Map<String, Object>> courseList = kcglMapper.getCourseList();
			String json = jsonUtil.objectToJson(courseList, null, null);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	@Override
	public String addOrUpdateMajorCourse(MajorCourse majorCourse) {
		JSONObject json = new JSONObject();
		String flag = "0"; //调整标志
		String msg = "";   //提示语
		try {
			//先根据专业流水号查询中间表
			List<Map<String,Object>> list = kcglMapper.getMajorCourseByMajorId(majorCourse);
			//如果该专业流水号有对应的课程就修改
			if(list != null && list.size()>0){
				kcglMapper.uodateMajorCourse(majorCourse);
				flag = "1";
				msg = "修改成功!";
			}
			//如果该专业流水号没有对应的课程就添加
			else{
				kcglMapper.addMajorCourse(majorCourse);
				flag = "1";
				msg = "新添成功!";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			flag = "0";
			msg = "网络故障,调整失败!";
		}
		json.put("flag", flag);
		json.put("msg", msg);
		return json.toString();
	}
	
	@Autowired
	private KcglMapper kcglMapper = null;

}
