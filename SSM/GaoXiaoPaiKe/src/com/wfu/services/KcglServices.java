package com.wfu.services;

import com.wfu.web.domain.MajorCourse;

public interface KcglServices {
	
	//��ȡרҵ
	public String getMajorList(Object department);
	
	//��ȡ�γ�
	public String getCourseList();
	
	//��ӻ����޸�רҵ�γ��м��
	public String addOrUpdateMajorCourse(MajorCourse majorCourse);

}
