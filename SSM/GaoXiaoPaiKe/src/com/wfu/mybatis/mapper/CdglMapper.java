package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.SysMenu;

public interface CdglMapper {
	
	//�����˵�
	public void addMenu(SysMenu sysmenu)throws Exception;
	
	//��ȡһ���˵�����id��
	public int getFMenuMaxId()throws Exception;
	
	//��ȡ�����˵�����id��
	public int getSMenuMaxId()throws Exception;
	
	//�޸Ĳ˵�
	public void updateMenu(SysMenu sysmenu)throws Exception;
	
	//��ȡȫ���˵�����ҳ
	public List<SysMenu> getAllMenu(SysMenu sysMenu)throws Exception;
	
	//��ȡȫ��һ���˵�
	public List<Map<String,Object>> getFMenu()throws Exception;
	
	//һ���˵�����������ȡ�˵�
	public Map<String,Object> queryById1(Object menuId)throws Exception;
	
	//�����˵�����������ȡ�˵�,������һ���˵���������һ���˵���
	public Map<String,Object> queryById2(Object menuId)throws Exception;

}
