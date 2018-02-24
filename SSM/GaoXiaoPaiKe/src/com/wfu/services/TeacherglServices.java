package com.wfu.services;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysLogin;
import com.wfu.web.domain.Teacher;

public interface TeacherglServices {
	
	//初始化课程下拉列表
    public List<Map<String,Object>> getCourseList();
	
	//条件查询教师并分页
	public PageResult<Teacher> queryByPage(Teacher teacher);
	
	//添加教师
	public String addTeacher(Teacher teacher);
	
	//注册账号
	public String signupNewLogin(SysLogin syslogin);
	
	//删除教师
	public String deleteTeacher(Teacher teacher);

}
