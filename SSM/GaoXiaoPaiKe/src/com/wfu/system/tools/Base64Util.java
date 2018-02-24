package com.wfu.system.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Util {
	
	private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);
	
	/**
	 * base64编码加密
	 * @param str:编码加密数据
	 * @return
	 */
	public static String ToBase64(String str){
		String compress = new sun.misc.BASE64Encoder().encode(str.getBytes());
		return compress;
	}
	/**
	 * base64解码解密
	 * @param compress:解码解密数据
	 * @return:解码解密后数据
	 */
	public static String base64ToStr(String compress){
		try {
			byte[] uncompress = new sun.misc.BASE64Decoder().decodeBuffer(compress);
			/**
			 * 这里将byte[]转成String,默认使用的编码格式是iso8859-1,因为,我们使用base64编码加密是针对于
			 * 用get方式提交,然后在地址栏中暴露的数据,浏览器默认使用iso8859-1对地址栏中数据进行编码,所以我们后台
			 * 针对于此使用iso8859-1进行解码...
			 */
			String str = new String(uncompress,"iso8859-1");
			return str;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return compress;
		}
	}
}
