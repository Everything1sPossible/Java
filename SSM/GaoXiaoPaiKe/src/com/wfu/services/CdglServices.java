package com.wfu.services;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysMenu;

public interface CdglServices {
	
	//�����˵�
    public String addMenu(SysMenu sysmenu);
    
    //�޸Ĳ˵�
    public String uodateMenu(SysMenu sysmenu);
    
	//��ȡȫ���˵�
	public PageResult<SysMenu> getAllMenu(SysMenu sysmenu);
	
	//��ȡȫ��һ���˵�
    public List<Map<String,Object>> getFMenu();

	//һ���˵�����������ȡ�˵�
	public Map<String,Object> queryById1(Object menuId);
	
	//�����˵�����������ȡ�˵�,������һ���˵���������һ���˵���
	public Map<String,Object> queryById2(Object menuId);

}
