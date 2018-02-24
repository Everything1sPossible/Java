package com.wfu.services;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysLogin;
import com.wfu.web.domain.Teacher;

public interface TeacherglServices {
	
	//��ʼ���γ������б�
    public List<Map<String,Object>> getCourseList();
	
	//������ѯ��ʦ����ҳ
	public PageResult<Teacher> queryByPage(Teacher teacher);
	
	//��ӽ�ʦ
	public String addTeacher(Teacher teacher);
	
	//ע���˺�
	public String signupNewLogin(SysLogin syslogin);
	
	//ɾ����ʦ
	public String deleteTeacher(Teacher teacher);

}
