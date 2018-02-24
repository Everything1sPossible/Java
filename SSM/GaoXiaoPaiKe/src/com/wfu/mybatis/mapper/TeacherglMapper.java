package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.SysLogin;
import com.wfu.web.domain.Teacher;

public interface TeacherglMapper {
	
	//初始化课程下拉列表
	public List<Map<String,Object>> getCourseList()throws Exception;
	
	//条件查询教师并分页
	public List<Teacher> queryByPage(Teacher teacher)throws Exception;
	
	//添加教师
	public void addTeacher(Teacher teacher)throws Exception;
	
	//注册账号
	public void signupNewLogin(SysLogin syslogin)throws Exception;
	
	//删除教师
	public void deleteTeacher(Map<String,Object> map)throws Exception;

}
