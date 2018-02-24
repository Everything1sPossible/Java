package com.wfu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.wfu.web.domain.SysMenu;

public interface CdglMapper {
	
	//新增菜单
	public void addMenu(SysMenu sysmenu)throws Exception;
	
	//获取一级菜单最大的id号
	public int getFMenuMaxId()throws Exception;
	
	//获取二级菜单最大的id号
	public int getSMenuMaxId()throws Exception;
	
	//修改菜单
	public void updateMenu(SysMenu sysmenu)throws Exception;
	
	//获取全部菜单并分页
	public List<SysMenu> getAllMenu(SysMenu sysMenu)throws Exception;
	
	//获取全部一级菜单
	public List<Map<String,Object>> getFMenu()throws Exception;
	
	//一级菜单根据主键获取菜单
	public Map<String,Object> queryById1(Object menuId)throws Exception;
	
	//二级菜单根据主键获取菜单,并将上一级菜单主键换成一级菜单名
	public Map<String,Object> queryById2(Object menuId)throws Exception;

}
