package com.wfu.services;

import com.wfu.web.domain.MajorCourse;

public interface KcglServices {
	
	//获取专业
	public String getMajorList(Object department);
	
	//获取课程
	public String getCourseList();
	
	//添加或者修改专业课程中间表
	public String addOrUpdateMajorCourse(MajorCourse majorCourse);

}
