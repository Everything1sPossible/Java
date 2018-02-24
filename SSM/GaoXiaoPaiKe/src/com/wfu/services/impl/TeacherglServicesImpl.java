package com.wfu.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wfu.mybatis.mapper.TeacherglMapper;
import com.wfu.services.TeacherglServices;
import com.wfu.system.tools.PageBeanUtil;
import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysLogin;
import com.wfu.web.domain.Teacher;

@Service
public class TeacherglServicesImpl implements TeacherglServices {

	private static final Logger logger = LoggerFactory.getLogger(TeacherglServicesImpl.class);
	/**
	 * ��ʼ���γ������б�
	 */
	@Override
	public List<Map<String,Object>> getCourseList(){
		try {
			List<Map<String,Object>> list = teacherglMapper.getCourseList();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * ������ѯ��ʦ����ҳ
	 */
	@Override
	public PageResult<Teacher> queryByPage(Teacher teacher){
		try {
			PageHelper.startPage(teacher.getPageNum(), teacher.getPageSize());
			PageResult<Teacher> result = PageBeanUtil.toPageResult(teacherglMapper.queryByPage(teacher));
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * ��ӽ�ʦ
	 */
	@Override
	public String addTeacher(Teacher teacher) {
		JSONObject json = new JSONObject();
		String msg = null; //��ӽ����ʾ��
		try {
			teacherglMapper.addTeacher(teacher);
			msg = "["+teacher.getTname()+",���:"+teacher.getTnumber()+"]��ӳɹ�!";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "�������,���ʧ��!";
		}
		json.put("msg", msg);
		return json.toString();
	}
	/**
	 * ע���˺�
	 */
	@Override
	public String signupNewLogin(SysLogin syslogin){
		JSONObject json = new JSONObject();
		String msg = null; //ע������ʾ��
		try {
			teacherglMapper.signupNewLogin(syslogin);
			msg = "[�û���:"+syslogin.getUsername()+",����:"+syslogin.getPassword()+"]ע��ɹ�!";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "�������,ע��ʧ��!";
		}
		json.put("msg", msg);
		return json.toString();
	}
	/**
	 * ɾ����ʦ
	 */
	@Override
	public String deleteTeacher(Teacher teacher){
		JSONObject json = new JSONObject();
		String msg = null;//ɾ�������ʾ��
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(teacher.getTids() != null && !teacher.getTids().equals("")){
				String[] tids = teacher.getTids().split("@");
				map.put("tids", tids);
			}
			else{
				String[] tids = new String[]{};
				map.put("tids", tids);
			}
			teacherglMapper.deleteTeacher(map);
			msg = "ɾ���ɹ�!";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "�������,ɾ��ʧ��!";
		}
		json.put("msg", msg);
		return json.toString();
	}
	
	@Autowired
	private TeacherglMapper teacherglMapper = null ;

}
