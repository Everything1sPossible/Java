package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.SysLogin;

public interface LoginMapper {
	
	//�˺�������֤
	public Map<String,Object> checkLogin(SysLogin sysLogin)throws Exception;
	
	//��ȡһ���˵�
	public List<Map<String,Object>> getFMenu(Map<String,Object> map)throws Exception;
	
	//��ȡ�����˵�
	public List<Map<String,Object>> getSMenu(Object obj)throws Exception;

}
