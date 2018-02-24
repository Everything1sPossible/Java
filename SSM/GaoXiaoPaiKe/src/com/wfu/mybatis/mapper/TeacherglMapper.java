package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.SysLogin;
import com.wfu.web.domain.Teacher;

public interface TeacherglMapper {
	
	//��ʼ���γ������б�
	public List<Map<String,Object>> getCourseList()throws Exception;
	
	//������ѯ��ʦ����ҳ
	public List<Teacher> queryByPage(Teacher teacher)throws Exception;
	
	//��ӽ�ʦ
	public void addTeacher(Teacher teacher)throws Exception;
	
	//ע���˺�
	public void signupNewLogin(SysLogin syslogin)throws Exception;
	
	//ɾ����ʦ
	public void deleteTeacher(Map<String,Object> map)throws Exception;

}
