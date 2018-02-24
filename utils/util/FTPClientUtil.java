package com.dhcc.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.util.IOUtils;

import com.google.common.base.Strings;

public class FTPClientUtil {

	public static void main(String[] args) {
		simpleDownloadFromFTP();
		/*downloadFromFTP("9.176.4.248", 21, "ftp_hr", "6Q13RvKQ",
				"/fib/dta/unl", "acc_info.20161102.unl.00.Z", "D:\\HMOA\\");*/

	}

	/**
	 * 从ftp文件服务器下载文件
	 * 
	 * @param ip
	 *            ftp 地址
	 * @param port
	 *            端口号
	 * @param username
	 *            ftp 登录用户名
	 * @param password
	 *            ftp 登录密码
	 * @param ftpGoalDir
	 *            要下载的文件路径
	 * @param renameFileName
	 *            要下载的文件名称
	 * @param sourceFileUrl
	 *            下载文件保存路径，可包含文件名
	 * @return 下载成功则返回 1，否则返回0
	 */
	public static void downloadFromFTP(String ip, int port, String userName,String passWord, String ftpGoalDir, String renameFileName,String sourceFileUrl) {
		FTPClient ftpClient = new FTPClient();
		FileOutputStream fos = null;
		try {
			File dir = new File(sourceFileUrl);
			if(!dir.exists()){
				dir.mkdirs();
			}
			System.out.println("FTP链接开始....");
			ftpClient.setConnectTimeout(30*1000);//设置30s 
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect(ip, port);
			ftpClient.login(userName, passWord);
			System.out.println("FTP链接成功....");
			
			String remoteFileName = ftpGoalDir;
			fos = new FileOutputStream(sourceFileUrl +  File.separator + renameFileName);
			ftpClient.setBufferSize(1024);
			System.out.println("remoteFileName:"+sourceFileUrl +  File.separator + renameFileName);
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			ftpClient.setDataTimeout(600*1000);//10分钟
			ftpClient.enterLocalPassiveMode();//开始本地被动模式 （linux下必须设置）
			ftpClient.retrieveFile(remoteFileName+ File.separator+renameFileName, fos);
			System.out.println("successful!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {
			IOUtils.closeQuietly(fos);
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
	}

	/**
	 * 便捷从FTP文件服务器下载文件
	
	 */
	public static void simpleDownloadFromFTP(){
		System.out.println("FTP，数据下载开始......！");
		Properties prop = new Properties();
		String ip = "9.176.4.248";
		String port="21";
		String userName="ftp_oa";
		String passWord="Y7Z228JY";
		String ftpGoalDir="/fib/dta/unl";
		String renameFileName="";
  		try {
  			prop.load(SysConfig.class.getResourceAsStream("/jdbc.properties"));
  			ip = prop.getProperty("SyncDPUData.ftpUrl", ip);
  			port = prop.getProperty("SyncDPUData.ftpPort", port+"");
  			userName = prop.getProperty("SyncDPUData.ftpUser", userName);
  			passWord = prop.getProperty("SyncDPUData.ftpPwd", passWord);
  			
  			ftpGoalDir = prop.getProperty("SyncDPUData.ftpRemotePath", ftpGoalDir);
  			renameFileName = prop.getProperty("SyncDPUData.fileNames", renameFileName);
  			if(!Strings.isNullOrEmpty(renameFileName)){
  				String [] renameFileNames = renameFileName.split(",");
  				for(String fn:renameFileNames){
  					Date date = DateUtil.addDays(-1);
  					fn =fn+"."+DateUtil.format(date, "yyyyMMdd")+".unl.00.gz";
  					downloadFromFTP(ip,Integer.valueOf(port),userName,passWord,ftpGoalDir,fn,SysConfig.FILE_ROOT_PATH+"ftp");
  				}
  			}
  			System.out.println("FTP，数据下载结束......！");
  		} catch (Exception e) {
  			e.printStackTrace();
  			System.out.println("jdbc.properties配置文件中，SyncDPUData不存在！");
  		}
	}
	
	
	

}
