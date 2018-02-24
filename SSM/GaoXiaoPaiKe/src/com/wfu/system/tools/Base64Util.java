package com.wfu.system.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Util {
	
	private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);
	
	/**
	 * base64�������
	 * @param str:�����������
	 * @return
	 */
	public static String ToBase64(String str){
		String compress = new sun.misc.BASE64Encoder().encode(str.getBytes());
		return compress;
	}
	/**
	 * base64�������
	 * @param compress:�����������
	 * @return:������ܺ�����
	 */
	public static String base64ToStr(String compress){
		try {
			byte[] uncompress = new sun.misc.BASE64Decoder().decodeBuffer(compress);
			/**
			 * ���ｫbyte[]ת��String,Ĭ��ʹ�õı����ʽ��iso8859-1,��Ϊ,����ʹ��base64��������������
			 * ��get��ʽ�ύ,Ȼ���ڵ�ַ���б�¶������,�����Ĭ��ʹ��iso8859-1�Ե�ַ�������ݽ��б���,�������Ǻ�̨
			 * ����ڴ�ʹ��iso8859-1���н���...
			 */
			String str = new String(uncompress,"iso8859-1");
			return str;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return compress;
		}
	}
}
