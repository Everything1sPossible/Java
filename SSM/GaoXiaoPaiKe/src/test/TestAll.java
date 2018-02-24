package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.wfu.mybatis.mapper.TeacherglMapper;
import com.wfu.services.CdglServices;
import com.wfu.services.TeacherglServices;
import com.wfu.system.tools.jsonUtil;
import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysMenu;


//指定测试的程序是Spring程序
@RunWith(SpringJUnit4ClassRunner.class)
//为测试类指定Spring配置文件的路径
@ContextConfiguration(locations="file:D:/MyWork/GaoXiaoPaiKe/WebContent/WEB-INF/config/ApplicationContext.xml")
public class TestAll {
	
	@Test
	public void test() throws Exception{
//		SysMenu menu = new SysMenu();
//		menu.setQmenuName("信息");
//		this.getMenuAll(menu.getPageNum(),menu.getPageSize(),menu);
//		menu.setMenuName("教师管理");
//		menu.setMenuUrl("#");
//		menu.setMenuLevel("1");
//		menu.setMenuFather(0);
//		menu.setMenuState("1");
//		add(menu);
		del();
	}
	public void del() throws Exception{
		String arr[] = new String[]{};
		Map map = new HashMap();
		map.put("tids", arr);
		mapper.deleteTeacher(map);
	}
	
	public void add(SysMenu sysMenu){
		xtglServices.addMenu(sysMenu);
	}
	
	public void getMenuAll(int pageNum,int pageSize,SysMenu sysMenu){
		PageHelper.startPage(pageNum, pageSize);
		PageResult<SysMenu> result = xtglServices.getAllMenu(sysMenu);
		System.out.println(result);
		System.out.println(result.getDataList().get(1));
		String json = jsonUtil.pageToJson(result, null, null);
		System.out.println(json);
	}
	@Autowired
	private TeacherglMapper mapper = null;
	@Autowired
	private TeacherglServices tServices = null ;
	@Autowired
	private CdglServices xtglServices = null ;
	
}
