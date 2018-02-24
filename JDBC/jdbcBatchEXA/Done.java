package com.dhcc.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;

import com.dhcc.common.util.DateUtil;
import com.dhcc.common.util.SysConfig;
import com.google.common.base.Strings;

public class Done {
	
	private static Logger log = Logger.getLogger(Done.class);
	
	public static void main(String[] args) {
		done();
		//input2(unGzipFile(SysConfig.FILE_ROOT_PATH+"ftp"+File.separator+"hr_department.20171029.unl.00.gz"));
	}
	
	public static void done() {
		long startTime = System.currentTimeMillis();
		MysqlDao dao = new MysqlDao();
		String deptFileName = "hr_department";
		String userFileName = "hr_employee";
		String postFileName = "hr_jobtitle";
		String postFileName2 = "hr_positionparttime";
		Date date = DateUtil.addDays(-1);
		//定义部门表文件路径
		String deptFilePath = SysConfig.FILE_ROOT_PATH+"ftp"+File.separator+deptFileName+"."+DateUtil.format(date, "yyyyMMdd")+".unl.00.gz";
		//定义员工表文件路径
		String userFilePath = SysConfig.FILE_ROOT_PATH+"ftp"+File.separator+userFileName+"."+DateUtil.format(date, "yyyyMMdd")+".unl.00.gz";
		//定义岗位表文件路径
		String postFilePath = SysConfig.FILE_ROOT_PATH+"ftp"+File.separator+postFileName+"."+DateUtil.format(date, "yyyyMMdd")+".unl.00.gz";
		//定义兼岗表文件路径
		String postFilePath2 = SysConfig.FILE_ROOT_PATH+"ftp"+File.separator+postFileName2+"."+DateUtil.format(date, "yyyyMMdd")+".unl.00.gz";
				
		//解压得到hr_department.20171029.unl.00
		String deptOuputfile = unGzipFile(deptFilePath);
		//解压得到hr_employee.20171029.unl.00
		String userOuputfile = unGzipFile(userFilePath);
		//解压得到hr_jobtitle.20171029.unl.00
		String postOuputfile = unGzipFile(postFilePath);
		//解压得到hr_positionparttime.20171029.unl.00
		String postOuputfile2 = unGzipFile(postFilePath2);
		
		Properties prop = new Properties();
		String url = "";
		String bpmurl = "";
		try {
  			prop.load(SysConfig.class.getResourceAsStream("/jdbc.properties"));
  			url = prop.getProperty("dhccbpm.url", url);
  			bpmurl = prop.getProperty("dhccbpm.bpmurl", url);
  		} catch (IOException e) {
  			System.out.println("jdbc.properties配置文件中，SyncDPUData不存在！");
  		}
		//读取部门文件获取内容
		String deptFileContent = input(deptOuputfile,"GBK");
		String[] deptFileContents = deptFileContent.split("\n");
		//添加部门信息(存在更新,不存在新加)
		log.info("OA-开始添加部门信息..........");
		boolean deptResult = dao.addDeptTable(deptFileContents,url);
		log.info("OA-添加部门信息结束.........."+deptResult);
		log.info("电子表单-开始添加部门信息..........");
		boolean deptResult2 = dao.addDeptTable(deptFileContents,bpmurl);
		log.info("电子表单-添加部门信息结束.........."+deptResult2);
		if(deptResult&&deptResult2){
			File file = new File(deptOuputfile);
			if(file.exists()){
				file.delete();
			}
		}
		//读取员工文件获取内容
		String userFileContent = input(userOuputfile,"GBK");
		String[] userFileContents = userFileContent.split("\n");
		//添加员工信息
		log.info("开始添加员工信息..........");
		boolean userResult = dao.addUserTable(userFileContents,url);
		log.info("添加员工信息结束.........."+userResult);
		log.info("电子表单-开始添加员工信息..........");
		boolean userResult2 = dao.addUserTable(userFileContents,bpmurl);
		log.info("电子表单-添加员工信息结束.........."+userResult2);
		if(userResult&&userResult2){
			File file = new File(userOuputfile);
			if(file.exists()){
				file.delete();
			}
		}
		//读取岗位文件获取内容
		String postFileContent = input(postOuputfile,"GBK");
		String[] postFileContents = postFileContent.split("\n");
		//添加岗位信息
		log.info("开始添加岗位信息..........");
		boolean postResult = dao.addPostTable(postFileContents,url);
		log.info("添加岗位信息结束.........."+postResult);
		log.info("电子表单-开始添加岗位信息..........");
		boolean postResult2 = dao.addPostTable(postFileContents,bpmurl);
		log.info("电子表单-添加岗位信息结束.........."+postResult2);
		if(postResult&&postResult2){
			File file = new File(postOuputfile);
			if(file.exists()){
				file.delete();
			}
		}
		
		//读取兼岗岗位文件获取内容
		String postFileContent2 = input(postOuputfile2,"GBK");
		String[] postFileContents2 = postFileContent2.split("\n");
		//添加兼岗岗位信息
		log.info("开始添加兼岗岗位信息..........");
		boolean postResult3 = dao.addUserPostTable(postFileContents2,url);
		log.info("添加兼岗岗位信息结束.........."+postResult3);
		log.info("电子表单-开始添加兼岗岗位信息..........");
		boolean postResult4 = dao.addUserPostTable(postFileContents2,bpmurl);
		log.info("电子表单-添加兼岗岗位信息结束.........."+postResult4);
		
		if(postResult3&&postResult4){
			File file = new File(postOuputfile2);
			if(file.exists()){
				file.delete();
			}
		}
		
		long endTime = System.currentTimeMillis();
        System.out.println("操作时间::"+(endTime-startTime));
	}
	
	/**
	 * 解压
	 */
	public static String unGzipFile(String sourcedir) {
		File file = new File(sourcedir);
		if(!file.exists()){
			throw new RuntimeException("文件异常,批处理终止!!!!");
		}
        String ouputfile = "";
        try {  
            //建立gzip压缩文件输入流 
            FileInputStream fin = new FileInputStream(file);   
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);   
            //建立解压文件输出流  
            ouputfile = sourcedir.substring(0,sourcedir.lastIndexOf("."));
            FileOutputStream fout = new FileOutputStream(ouputfile);   
            
            int num;
            byte[] buf=new byte[1024];

            while ((num = gzin.read(buf,0,buf.length)) != -1){   
                fout.write(buf,0,num);   
            }
            gzin.close();   
            fout.close();   
            fin.close(); 
            file.delete();//删除压缩文件
            System.out.println(sourcedir+"解压缩成功....");
        } catch (Exception ex){  
        	log.error(ex.getMessage());
        	ex.printStackTrace();
        }  
        return ouputfile;
    }
	/**
	 * 读取文件获取内容
	 * @param ouputfile:文件路径
	 * @return
	 * @throws Exception
	 */
	public static String input(String ouputfile){
		File file = new File(ouputfile);
		FileReader reader;
		StringBuffer sb = new StringBuffer();// 用来将每次读取到的字符拼接
		try {
			reader = new FileReader(file);
			char[] bb = new char[1024];// 用来保存每次读取到的字符  
	        int n;// 每次读取到的字符长度  
	        while ((n = reader.read(bb)) != -1) {  
	        	sb.append(new String(bb, 0, n));  
	        }  
	        reader.close();// 关闭输入流，释放连接  
	        //file.delete();//删除解压后文件
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}  
		return sb.toString();
	}
	/**
	 * 
	 * @param ouputfile:文件路径
	 * @param charset:编码格式
	 * @return
	 */
	public static String input(String ouputfile,String charset){
		File file = new File(ouputfile);
		if(!file.exists()){
			throw new RuntimeException("文件异常,批处理终止!!!!");
		}
		StringBuffer sb = new StringBuffer();
		try {
			InputStreamReader ipr = new InputStreamReader(new FileInputStream(file),Charset.forName(charset));
			BufferedReader br = new BufferedReader(ipr);
			String str = null;
			//每次读取一行
	        while((str=br.readLine())!=null){
	        	sb.append(str+"\n");
	        }
	        br.close();
	        ipr.close();
	        file.delete();//删除解压后文件
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}  
		return sb.toString();
	}

}
