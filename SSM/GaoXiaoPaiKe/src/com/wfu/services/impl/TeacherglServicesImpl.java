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
	 * 初始化课程下拉列表
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
	 * 条件查询教师并分页
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
	 * 添加教师
	 */
	@Override
	public String addTeacher(Teacher teacher) {
		JSONObject json = new JSONObject();
		String msg = null; //添加结果提示语
		try {
			teacherglMapper.addTeacher(teacher);
			msg = "["+teacher.getTname()+",编号:"+teacher.getTnumber()+"]添加成功!";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "网络故障,添加失败!";
		}
		json.put("msg", msg);
		return json.toString();
	}
	/**
	 * 注册账号
	 */
	@Override
	public String signupNewLogin(SysLogin syslogin){
		JSONObject json = new JSONObject();
		String msg = null; //注册结果提示语
		try {
			teacherglMapper.signupNewLogin(syslogin);
			msg = "[用户名:"+syslogin.getUsername()+",密码:"+syslogin.getPassword()+"]注册成功!";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "网络故障,注册失败!";
		}
		json.put("msg", msg);
		return json.toString();
	}
	/**
	 * 删除教师
	 */
	@Override
	public String deleteTeacher(Teacher teacher){
		JSONObject json = new JSONObject();
		String msg = null;//删除结果提示语
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
			msg = "删除成功!";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "网络故障,删除失败!";
		}
		json.put("msg", msg);
		return json.toString();
	}
	
	@Autowired
	private TeacherglMapper teacherglMapper = null ;

}
