package com.dhcc.ftp;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import com.dhcc.common.util.Constant;
import com.dhcc.common.util.HanyuPinyinHelper;
import com.dhcc.common.util.SysConfig;
import com.dhcc.common.uuid.Uuid;
import com.google.common.base.Strings;

public class MysqlDao {
	
	private static Logger log = Logger.getLogger(MysqlDao.class);
	
//	private static String driverClassName;
//	private static String url;
//	private static String user;
//	private static String password;
//	
//	static{
//		driverClassName = (String)PropertyReader.getValueByKey("dhccbpm.driverClassName");
//		url = (String)PropertyReader.getValueByKey("dhccbpm.url");
//		user = (String)PropertyReader.getValueByKey("dhccbpm.user");
//		password = (String)PropertyReader.getValueByKey("dhccbpm.password");
//	}
	/**
	 * 更改员工表(sys_user)和员工岗位表(sys_user_post)(主岗)
	 * t.emp_no,  ---员工PK               -->id
       t.emp_distinct_no,  --员工编码           -->ALIAS
       t.emp_name,   --- 员工姓名                   -->REALNAME
       t.emp_dept_no, ---员工所属部门PK    
       t.emp_gender,  -- 员工性别 0-女 1 男  -->SEX(因sys_user设置0男1女,所以此处要进行颠倒下)
       t.emp_mobile,  --电话                           -->MOBILE
       t.emp_work_phone,  --工作固话电话      -->PHONE
       t.emp_type, ---用工形式                        
       t.emp_jt_no  ---员工岗位PK         -->POSTID(sys_user_post)
       t.emp_join_date ,    --入职时间
        t.emp_work_date ,     -- 参加工作时间
        t.emp_confirm_date ,  --转正时间
        t.emp_terminate_date , ---离职时间
        t.emp_quit_id,   ----离职表PK
        t.emp_status, ----人员状态               -->STATUS
	 */
	public boolean addUserTable(String[] strs,String url){
		log.info("开始执行更新人员sql操作......");
		HanyuPinyinHelper hanyu = new HanyuPinyinHelper();
		Connection conn = null;
		try{
			conn = getConnection( url);
			if(conn==null||conn.isClosed()){
				return false;
			}
			String password = "123456";//初始密码
			//添加sys_user表sql语句
			String sql1 = "insert into sys_user(id,ALIAS,USERNAME,REALNAME,SEX,MOBILE,PHONE,STATUS,password) "
	 		            + "              values(?,?,?,?,?,?,?,?,?) " +
	 		              "      ON DUPLICATE KEY UPDATE " +
	 		              "      SEX=?,MOBILE=?,PHONE=?,STATUS=?";
			//删除sys_user_post表sql语句(主岗)
			String sql2 = " delete from sys_user_post where userId=? and ISPRIMARY='1'";
			//添加sys_user_post表语句
			String sql3 = " insert into sys_user_post(ID,POSTID,USERID,ISPRIMARY)" +
					      "            values(?,?,?,?) ";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			//创建数据库存储过程对象
			CallableStatement cs = conn.prepareCall("{call batchUpdatePinyinSysUser()}");
			for(int i =0;i<strs.length;i++){
	        	String[] ss = strs[i].split("\\|{1}");
	        	//截取掉括号内容,例如'张三(卡)',截取成'张三',只保留原始姓名
	        	if(ss[2].indexOf("（")>-1){
	        		ss[2] = ss[2].substring(0, ss[2].indexOf("（"));
	        	}
	        	if(ss[2].indexOf("(")>-1){
	        		ss[2] = ss[2].substring(0, ss[2].indexOf("("));
	        	}
	        	//添加sys_user操作赋值
	        	ps1.setString(1, ss[0]);
	        	ps1.setString(2, ss[1]);
	        	ps1.setString(3, hanyu.toHanyuPinyin(ss[2]));//转成汉语拼音
	        	ps1.setString(4, ss[2]);
	        	ps1.setString(5, ss[4].equals("1")?"0":"1");//传过来的数据与默认设置相反,所以此处进行颠倒
	        	ps1.setString(6, "null".equals(ss[5])?"":ss[5]);
	        	ps1.setString(7, "null".equals(ss[6])?"":ss[6]);
	        	ps1.setString(8, ss[14].equals("1")?"1":"0"); //员工状态(1:正常,其他的都为0)
	        	ps1.setString(9, new BASE64Encoder().encode(password.getBytes()));//初始化密码:123456
	        	
	        	ps1.setString(10, ss[4].equals("1")?"0":"1");
	        	ps1.setString(11, "null".equals(ss[5])?"":ss[5]);
	        	ps1.setString(12, "null".equals(ss[6])?"":ss[6]);
	        	ps1.setString(13, ss[14].equals("1")?"1":"0");//员工状态
                //删除sys_user_post操作赋值
	        	ps2.setString(1, ss[0]);
	        	//添加sys_user_post操作赋值
	        	ps3.setString(1, new Uuid().getUUID());
	        	ps3.setString(2, ss[8]);
	        	ps3.setString(3, ss[0]);
	        	ps3.setString(4, "1");//默认为1,即主岗
	        	ps1.addBatch();
	        	ps2.addBatch();
	        	ps3.addBatch();
	        }
			//定义事务返回值变量
			boolean tag=false;
			//开启事务
			conn.setAutoCommit(false);
			try{
				//执行批处理
				ps1.executeBatch();
				ps2.executeBatch();
				ps3.executeBatch();
				//执行存储过程
				cs.execute();
				//提交事务
				conn.commit();
				//修改返回值
				tag=true;
			}
			catch(Exception ex){
				//回滚事务
				conn.rollback();
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
			finally{
				//结束事务
				conn.setAutoCommit(true);
			}
			return tag;
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		finally{
			close(conn);
		}
		return false;
	}
	/**
	 * 更改岗位表(sys_post)
	 * JOBTITLE_SORT_ID   --岗位编号                                                                          
		,JOBTITLE_NAME--岗位名称                                                                               -->POSTNAME
		,JOBTITLE_NEED_GM_APPROVE   --本岗位招聘是否需要审批0-不需要 1-需要    
		,JOBTITLE_DESC     ----岗位描述                                                                  -->DESCRIPTION
		,JOBTITLE_STATUS    ---岗位状态                                                                   -->STATE
		,JOBTITLE_DISTINCT_NO  --- 岗位编码                                                           -->ALIAS
		,JOBTITLE_NO   --岗位PK                                         -->id
		,JOBTITLE_SUP_ID  --上级岗位PK                                   -->PPOSTID
		,PB_DEPT_ID    ---岗位所属部门PK                                  -->DEPTID
		,PB_SORT_ID    ---岗位序号                                                                              -->SEQ
	 */
	public boolean addPostTable(String[] strs,String url){
		log.info("开始执行更新岗位sql操作......");
		Connection conn = null;
		try{
			conn = getConnection( url);
			if(conn==null||conn.isClosed()){
				return false;
			}
			//添加sys_post表sql语句
			String sql = "insert into sys_post(id,POSTNAME,DESCRIPTION,DEPTID,PPOSTID,STATE,SEQ,ALIAS) "
	 		           + "              values(?,?,?,?,?,?,?,?) " +
	 		             "      ON DUPLICATE KEY UPDATE " +
	 		             "      POSTNAME=?,DESCRIPTION=?,DEPTID=?,PPOSTID=?,STATE=?,SEQ=?,ALIAS=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i =0;i<strs.length;i++){
	        	String[] ss = strs[i].split("\\|{1}");
	        	//添加sys_post操作赋值
	        	ps.setString(1, ss[6]);
	        	ps.setString(2, ss[1]);
	        	ps.setString(3, "null".equals(ss[3])?"":ss[3]);
	        	ps.setString(4, ss[8]);
	        	ps.setString(5, "null".equals(ss[7])?"-1":ss[7]);
	        	ps.setString(6, ss[4]);
	        	ps.setString(7, ss[9]);
	        	ps.setString(8, ss[5]);
	        	
	        	ps.setString(9, ss[1]);
	        	ps.setString(10, "null".equals(ss[3])?"":ss[3]);
	        	ps.setString(11, ss[8]);
	        	ps.setString(12, "null".equals(ss[7])?"-1":ss[7]);
	        	ps.setString(13, ss[4]);
	        	ps.setString(14, ss[9]);
	        	ps.setString(15, ss[5]);
	        	ps.addBatch();
	        }
			//定义事务返回值变量
			boolean tag=false;
			//开启事务
			conn.setAutoCommit(false);
			try{
				//执行批处理
				ps.executeBatch();
				//提交事务
				conn.commit();
				//修改返回值
				tag=true;
			}
			catch(Exception ex){
				//回滚事务
				conn.rollback();
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
			finally{
				//结束事务
				conn.setAutoCommit(true);
			}
			return tag;
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		finally{
			close(conn);
		}
		return false;
	}
	/**
	 * 更改员工岗位表(sys_user_post)(兼岗)
	 * t.ppt_emp_no, ---员工主键PK        -->userId
       t.JOBTITLE_NO,  --岗位PK          -->postId
       t.ppt_id,      ---pk  
       t.ppt_is_proxy,  ---是否代理职位
	 */
	public boolean addUserPostTable(String[] strs,String url){
		log.info("开始执行更新人员兼岗岗位sql操作......");
		Connection conn = null;
		try{
			conn = getConnection(url);
			if(conn==null||conn.isClosed()){
				return false;
			}
			//删除sys_user_post表sql语句(兼岗)
			String sql1 = " delete from sys_user_post where userId=? and ISPRIMARY='0'";
			//添加sys_user_post表语句
			String sql2 = " insert into sys_user_post(ID,POSTID,USERID,ISPRIMARY)" +
					      "            values(?,?,?,?) ";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			for(int i =0;i<strs.length;i++){
	        	String[] ss = strs[i].split("\\|{1}");
	        	//删除sys_user_post操作赋值(兼岗)
	        	ps1.setString(1, ss[0]);
	        	//添加sys_user_post操作赋值(兼岗)
	        	ps2.setString(1, new Uuid().getUUID());
	        	ps2.setString(2, ss[1]);
	        	ps2.setString(3, ss[0]);
	        	ps2.setString(4, "0");//默认为0,即兼岗
	        	ps1.addBatch();
	        	ps2.addBatch();
	        }
			//定义事务返回值变量
			boolean tag=false;
			//开启事务
			conn.setAutoCommit(false);
			try{
				//执行批处理
				ps1.executeBatch();
				ps2.executeBatch();
				//提交事务
				conn.commit();
				//修改返回值
				tag=true;
			}
			catch(Exception ex){
				//回滚事务
				conn.rollback();
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
			finally{
				//结束事务
				conn.setAutoCommit(true);
			}
			return tag;
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		finally{
			close(conn);
		}
		return false;
	}
	/**
	 * 更改部门表(sys_dept)
	 * @param strs
	 * @return
       t.department_no,   --部门pk                       -->id
       t.department_distinct_no,--部门编码                                -->deptCode
       t.department_name,--部门名称                                              -->deptName
       t.department_desc,---部门简称                                             -->ABBREVIATION
       t.department_cate,--  部门层次 0-总公司 1-分公司 2-部门  -->DEPTLEVEL
       t.department_sort_id,---部门排序号                                    -->SEQ
       t.department_status,---部门状态 1-启用  0-停用                 -->STATE
       t.department_parent_id,  --上级部门pk               -->PDEPTID
		    
	 */
	public boolean addDeptTable(String[] strs,String url){
		log.info("开始执行更新部门sql操作......");
		Connection conn = null;
		try{
			conn = getConnection(url);
			if(conn==null||conn.isClosed()){
				return false;
			}
			String sql = "insert into sys_dept(id,deptCode,deptName,ABBREVIATION,DEPTLEVEL,SEQ,STATE,PDEPTID) "
	 		           + "              values(?,?,?,?,?,?,?,?) " +
	 		             "      ON DUPLICATE KEY UPDATE " +
	 		             "      deptCode=?,deptName=?,ABBREVIATION=?,DEPTLEVEL=?,SEQ=?,STATE=?,PDEPTID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			//创建数据库存储过程对象
			CallableStatement cs = conn.prepareCall("{call update_dept_seq('"+Constant.ROOT_DEPT_ID+"','0')}");
			for(int i =0;i<strs.length;i++){
	        	String[] ss = strs[i].split("\\|{1}");
	        	ps.setString(1, ss[0]);
	        	ps.setString(2, ss[1]);
	        	ps.setString(3, ss[2]);
	        	ps.setString(4, ss[3]);
	        	ps.setString(5, "null".equals(ss[4])?"":ss[4]);
	        	ps.setString(6, ss[5]);
	        	ps.setString(7, ss[6]);
	        	ps.setString(8, ss[7].equals("null")?"-1":ss[7]);//用于处理'哈密市商业银行股份有限公司',传过来的该值父节点部门id为'null'
	        	
	        	ps.setString(9,  ss[1]);
	        	ps.setString(10, ss[2]);
	        	ps.setString(11, ss[3]);
	        	ps.setString(12, "null".equals(ss[4])?"":ss[4]);
	        	ps.setString(13, ss[5]);
	        	ps.setString(14, ss[6]);
	        	ps.setString(15, ss[7].equals("null")?"-1":ss[7]);
	        	ps.addBatch();
	        }
			//定义事务返回值变量
			boolean tag=false;
			//开启事务
			conn.setAutoCommit(false);
			try{
				//执行批处理
				ps.executeBatch();
				//执行存储过程
				cs.execute();
				//提交事务
				conn.commit();
				//修改返回值
				tag=true;
			}
			catch(Exception ex){
				//回滚事务
				conn.rollback();
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
			finally{
				//结束事务
				conn.setAutoCommit(true);
			}
			return tag;
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		finally{
			close(conn);
		}
		return false;
	}
	
	public Connection getConnection(String url) throws Exception{
		if(Strings.isNullOrEmpty(url)){
			return null;
		}
		Properties prop = new Properties();
		String driverClassName = "com.mysql.jdbc.Driver";
		String user="hmoa";
		String password="Passw0rd";
		try {
  			prop.load(SysConfig.class.getResourceAsStream("/jdbc.properties"));
  			driverClassName = prop.getProperty("dhccbpm.driverClassName", driverClassName);
  			if(Strings.isNullOrEmpty(url)){
  				url="jdbc:mysql://127.0.0.1:3306/hmoa?autoReconnect=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
  				url = prop.getProperty("dhccbpm.url", url);
  			}
  			user = prop.getProperty("dhccbpm.user", user);
  			password = prop.getProperty("dhccbpm.password", password);
  		} catch (IOException e) {
  			System.out.println("jdbc.properties配置文件中，SyncDPUData不存在！");
  		}
		//加载驱动
		Class.forName(driverClassName);
		//得到连接
		Connection conn = DriverManager.getConnection(url,user , password);
		return conn;
	}
	
	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
