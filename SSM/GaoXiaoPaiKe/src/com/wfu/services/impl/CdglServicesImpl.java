package com.wfu.services.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wfu.mybatis.mapper.CdglMapper;
import com.wfu.services.CdglServices;
import com.wfu.system.tools.PageBeanUtil;
import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysMenu;

@Service
public class CdglServicesImpl implements CdglServices {
	
	private static final Logger logger = LoggerFactory.getLogger(CdglServicesImpl.class);

	/**
	 * 条件查询菜单并分页
	 */
	@Override
	public PageResult<SysMenu> getAllMenu(SysMenu sysmenu) {
		try {
			//告诉分页拦截器开始按规则分页
			PageHelper.startPage(sysmenu.getPageNum(), sysmenu.getPageSize());
			//将分页结果封装成PageResult类
			PageResult<SysMenu> result = PageBeanUtil.toPageResult(cdglMapper.getAllMenu(sysmenu));
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
    /**
     * 一级菜单根据主键获取菜单
     */
	@Override
	public Map<String, Object> queryById1(Object menuId) {
		try {
			Map<String, Object> menuMap1 = cdglMapper.queryById1(menuId);
			return menuMap1;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 二级菜单根据主键获取菜单,并将上一级菜单主键换成一级菜单名
	 */
	@Override
	public Map<String, Object> queryById2(Object menuId) {
		try {
			Map<String, Object> menuMap2 = cdglMapper.queryById2(menuId);
			return menuMap2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 加载一级菜单下拉列表
	 */
	@Override
	public List<Map<String,Object>> getFMenu() {
		try {
			List<Map<String, Object>> FMenuList = cdglMapper.getFMenu();
			return FMenuList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 添加菜单
	 */
	@Override
	public String addMenu(SysMenu sysmenu) {
		JSONObject json = new JSONObject();
		String flag = "0";//添加失败标志
		String msg ;  //提示语
		try {
			String menuLevel = sysmenu.getMenuLevel();
			if (menuLevel.equals("1")) {//一级菜单
				int maxFMenuId = cdglMapper.getFMenuMaxId();
				sysmenu.setMenuId((maxFMenuId + 1));
				cdglMapper.addMenu(sysmenu);
				flag = "1";
				msg = "一级菜单["+sysmenu.getMenuName()+"]添加成功";
				json.put("flag", flag);
				json.put("msg", msg);
			}
			if (menuLevel.equals("2")) {//二级菜单
				int maxSMenuId = cdglMapper.getSMenuMaxId();
				sysmenu.setMenuId((maxSMenuId + 1));
				cdglMapper.addMenu(sysmenu);
				flag = "1";
				msg = "二级菜单["+sysmenu.getMenuName()+"]添加成功";
				json.put("flag", flag);
				json.put("msg", msg);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "网络故障,添加失败!";
			json.put("flag", flag);
			json.put("msg", msg);
		}
		return json.toString();
	}
	
	/**
	 * 修改菜单
	 */
	@Override
	public String uodateMenu(SysMenu sysmenu){
		JSONObject json = new JSONObject();
		String msg = null ;//修改结果提示语
		try {
			cdglMapper.updateMenu(sysmenu);
			msg = "修改成功!";
		} catch (Exception e) {
			msg = "网络故障,修改失败!";
			logger.error(e.getMessage(), e);
		}
		json.put("msg", msg);
		return json.toString();
	}

	@Autowired
	private CdglMapper cdglMapper = null ;

}
