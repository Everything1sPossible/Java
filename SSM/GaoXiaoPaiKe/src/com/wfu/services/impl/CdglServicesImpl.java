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
	 * ������ѯ�˵�����ҳ
	 */
	@Override
	public PageResult<SysMenu> getAllMenu(SysMenu sysmenu) {
		try {
			//���߷�ҳ��������ʼ�������ҳ
			PageHelper.startPage(sysmenu.getPageNum(), sysmenu.getPageSize());
			//����ҳ�����װ��PageResult��
			PageResult<SysMenu> result = PageBeanUtil.toPageResult(cdglMapper.getAllMenu(sysmenu));
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
    /**
     * һ���˵�����������ȡ�˵�
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
	 * �����˵�����������ȡ�˵�,������һ���˵���������һ���˵���
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
	 * ����һ���˵������б�
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
	 * ��Ӳ˵�
	 */
	@Override
	public String addMenu(SysMenu sysmenu) {
		JSONObject json = new JSONObject();
		String flag = "0";//���ʧ�ܱ�־
		String msg ;  //��ʾ��
		try {
			String menuLevel = sysmenu.getMenuLevel();
			if (menuLevel.equals("1")) {//һ���˵�
				int maxFMenuId = cdglMapper.getFMenuMaxId();
				sysmenu.setMenuId((maxFMenuId + 1));
				cdglMapper.addMenu(sysmenu);
				flag = "1";
				msg = "һ���˵�["+sysmenu.getMenuName()+"]��ӳɹ�";
				json.put("flag", flag);
				json.put("msg", msg);
			}
			if (menuLevel.equals("2")) {//�����˵�
				int maxSMenuId = cdglMapper.getSMenuMaxId();
				sysmenu.setMenuId((maxSMenuId + 1));
				cdglMapper.addMenu(sysmenu);
				flag = "1";
				msg = "�����˵�["+sysmenu.getMenuName()+"]��ӳɹ�";
				json.put("flag", flag);
				json.put("msg", msg);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = "�������,���ʧ��!";
			json.put("flag", flag);
			json.put("msg", msg);
		}
		return json.toString();
	}
	
	/**
	 * �޸Ĳ˵�
	 */
	@Override
	public String uodateMenu(SysMenu sysmenu){
		JSONObject json = new JSONObject();
		String msg = null ;//�޸Ľ����ʾ��
		try {
			cdglMapper.updateMenu(sysmenu);
			msg = "�޸ĳɹ�!";
		} catch (Exception e) {
			msg = "�������,�޸�ʧ��!";
			logger.error(e.getMessage(), e);
		}
		json.put("msg", msg);
		return json.toString();
	}

	@Autowired
	private CdglMapper cdglMapper = null ;

}
