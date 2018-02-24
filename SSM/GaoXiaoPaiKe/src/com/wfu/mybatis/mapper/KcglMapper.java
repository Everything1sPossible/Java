package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.MajorCourse;

public interface KcglMapper {
	
	//获取专业
	public List<Map<String,Object>> getMajorList(Object department)throws Exception;;
	
	//获取课程
	public List<Map<String,Object>> getCourseList()throws Exception;;
	
	//根据专业流水号查询专业课程中间表
	public List<Map<String,Object>> getMajorCourseByMajorId(MajorCourse majorCourse)throws Exception;;
	
	//根据查询结果,如果该专业流水号没有对应的课程就添加
	public void addMajorCourse(MajorCourse majorCourse)throws Exception;;
	
	//根据查询结果,如果该专业流水号有对应的课程就修改
	public void uodateMajorCourse(MajorCourse majorCourse)throws Exception;;

}
