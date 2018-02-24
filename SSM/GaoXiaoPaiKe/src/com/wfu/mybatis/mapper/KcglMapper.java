package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.MajorCourse;

public interface KcglMapper {
	
	//��ȡרҵ
	public List<Map<String,Object>> getMajorList(Object department)throws Exception;;
	
	//��ȡ�γ�
	public List<Map<String,Object>> getCourseList()throws Exception;;
	
	//����רҵ��ˮ�Ų�ѯרҵ�γ��м��
	public List<Map<String,Object>> getMajorCourseByMajorId(MajorCourse majorCourse)throws Exception;;
	
	//���ݲ�ѯ���,�����רҵ��ˮ��û�ж�Ӧ�Ŀγ̾����
	public void addMajorCourse(MajorCourse majorCourse)throws Exception;;
	
	//���ݲ�ѯ���,�����רҵ��ˮ���ж�Ӧ�Ŀγ̾��޸�
	public void uodateMajorCourse(MajorCourse majorCourse)throws Exception;;

}
