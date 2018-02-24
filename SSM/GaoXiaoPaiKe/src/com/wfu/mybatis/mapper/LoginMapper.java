package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.SysLogin;

public interface LoginMapper {
	
	//账号密码验证
	public Map<String,Object> checkLogin(SysLogin sysLogin)throws Exception;
	
	//获取一级菜单
	public List<Map<String,Object>> getFMenu(Map<String,Object> map)throws Exception;
	
	//获取二级菜单
	public List<Map<String,Object>> getSMenu(Object obj)throws Exception;

}
