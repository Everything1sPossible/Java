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
		String flag = "0"; //������־
		String msg = "";   //��ʾ��
		try {
			//�ȸ���רҵ��ˮ�Ų�ѯ�м��
			List<Map<String,Object>> list = kcglMapper.getMajorCourseByMajorId(majorCourse);
			//�����רҵ��ˮ���ж�Ӧ�Ŀγ̾��޸�
			if(list != null && list.size()>0){
				kcglMapper.uodateMajorCourse(majorCourse);
				flag = "1";
				msg = "�޸ĳɹ�!";
			}
			//�����רҵ��ˮ��û�ж�Ӧ�Ŀγ̾����
			else{
				kcglMapper.addMajorCourse(majorCourse);
				flag = "1";
				msg = "����ɹ�!";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			flag = "0";
			msg = "�������,����ʧ��!";
		}
		json.put("flag", flag);
		json.put("msg", msg);
		return json.toString();
	}
	
	@Autowired
	private KcglMapper kcglMapper = null;

}
