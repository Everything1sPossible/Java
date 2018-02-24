package com.wfu.system.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class BeanUtils
{
	/**
	 * ˽�й�����,��ֹ��ʵ����
	 */
    private BeanUtils(){}
    
    /**
     * Bean <==> Map ת�������б�
     */
    private final static Class castList[]=
    {
    	   Integer.class,
    	   Long.class,
    	   String.class,
    	   String[].class,
    	   java.util.Date.class,
    	   Double.class,
    	   Boolean.class
    };
    
    /**
     * У���ֶε���������,�Ƿ���ת���б��޶���Χ��
     * @param fieldType
     * @return
     */
    private static boolean checkFieldType(Class fieldType)
    {
    	boolean tag=false;
    	for(Class castType: castList)
    	{
    		if(fieldType==castType)
    		{
    			tag=true;
    			break;
    		}
    	}
    	return tag;
    }
    
    /**
     * ͨ�ö��󴴽�����ֵ
     * <Ӧ����O/R Mapping�����,�־û�����Ĵ���>
     * @param <T>
     * @param classType
     * @param properties
     * @return
     * @throws Exception
     */
    public static<T> T createInstance(Class<T> classType,Map<String,Object> properties)throws Exception
    {
       T   bean=classType.newInstance();
 	   Field  fieldList[]=classType.getDeclaredFields();
 	   Class  fieldType=null;                   //field����
 	   String fieldName=null;                   //field����; 
 	   String setMethodName=null;               //field���������� 
 	   Method setMethot=null;                   //field������
 	   for(Field field:fieldList)
 	   {
 		   fieldType=field.getType();
 		  
 		   if(BeanUtils.checkFieldType(fieldType))
 		   {
 			   fieldName=field.getName();
 			   setMethodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
 			   setMethot=classType.getDeclaredMethod(setMethodName, new Class[]{fieldType});
 			   setMethot.invoke(bean, new Object[]{properties.get(fieldName)});
 		   }
 	   }
 	   return bean;
    }	

    
    /**
     * �¶��󴴽���ֵ
     * @param bean
     * @param properties
     * @throws Exception
     */
    public static void populate(Object bean, Map<String,String> properties)throws Exception
    {
       Class classType=bean.getClass();
  	   Field fieldList[]=classType.getDeclaredFields();
  	   Class  fieldType=null;                   //field����
  	   String fieldName=null;                   //field����; 
  	   String setMethodName=null;               //field���������� 
  	   Method setMethot=null;                   //field������
  	   for(Field field:fieldList)
  	   {
  		   fieldType=field.getType();
  		   if(BeanUtils.checkFieldType(fieldType))
  		   {
  			   fieldName=field.getName();
  			   setMethodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
  			   setMethot=classType.getDeclaredMethod(setMethodName, new Class[]{fieldType});
  			   setMethot.invoke(bean, new Object[]{properties.get(fieldName)});
  		   }
  	   }
    }

    /**
     * �������ݶ����Map��ʽcopy
     * @param bean       ԭʼBean
     * @param likeField  ģ����ѯ�ֶ��б� 
     * @return
     * @throws Exception
     */
    public static  Map<String,Object> describe(Object bean,String...likeFieldList)throws Exception
    {
 	   Class classType=bean.getClass();
       //��ȡBean�е������б�
	   Field fieldList[]=classType.getDeclaredFields();
	   //�����ֶ�����
	   int size=fieldList.length;  
	   //����Map�ĳ�ʼ����
	   int initSize=((Number)(size/0.75)).intValue()+2;
       /**
        * ���ص�Map����
        * Map��ӳ���������ֵΪBean���ֶε�����,
        * ����HashMap����rehash����
        */
	   Map<String,Object> dto=new HashMap<>(initSize); 
	   String fieldName=null;                   //field����; 
	   String getMethodName=null;               //field���������� 
	   Method getMethot=null;                   //field������
	   
	   /**
	    * ���������ֶε���������
	    */
	   for(Field field:fieldList)
	   {
		   if(BeanUtils.checkFieldType(field.getType()))
		   {
			   fieldName=field.getName();
			   getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			   getMethot=classType.getMethod(getMethodName, new Class[]{});
			   
			   Object val=getMethot.invoke(bean, null);
			   if(val!=null && !val.equals(""))
			   {
				   if(BeanUtils.checkLike(fieldName, likeFieldList))
				   {
						   dto.put(fieldName, "%"+val+"%");   
				   }
				   else
				   {
					   dto.put(fieldName, val);
				   }		   
			   }
		   }
	   }
       return dto;
    }
    
    private static boolean checkLike(String fieldName,String...likeFieldList)
    {
    	boolean tag=false;
    	for(String likeFieldName:likeFieldList)
    	{
    		if(fieldName.equals(likeFieldName))
    		{
    			tag=true;
    			break;
    		}
    	}
    	return tag;
    }
    
    
    /**
     * �������ݶ����Map��ʽcopy
     * @param bean
     * @return
     * @throws Exception
     */
    public static  Map<String,Object> describe(Object bean)throws Exception
    {
 	   Class classType=bean.getClass();
       //��ȡBean�е������б�
	   Field fieldList[]=classType.getDeclaredFields();
	   //�����ֶ�����
	   int size=fieldList.length;  
	   //����Map�ĳ�ʼ����
	   int initSize=((Number)(size/0.75)).intValue()+1;
       /**
        * ���ص�Map����
        * Map��ӳ���������ֵΪBean���ֶε�����,
        * ����HashMap����rehash����
        */
	   Map<String,Object> dto=new HashMap<>(initSize); 
	   String fieldName=null;                   //field����; 
	   String getMethodName=null;               //field���������� 
	   Method getMethot=null;                   //field������
	   
	   /**
	    * ���������ֶε���������
	    */
	   for(Field field:fieldList)
	   {
		   if(BeanUtils.checkFieldType(field.getType()))
		   {
			   //��ȡ�ֶ�����
			   fieldName=field.getName();
			   //ƴ�Ӹ��ַ��η�����
			   getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			   //���ɷ����ӷ���
			   getMethot=classType.getMethod(getMethodName, new Class[]{});
			   //���÷����ӷ�����ȡBean����д��dto
			   Object val=getMethot.invoke(bean, null);
			   if(val!=null && !val.equals(""))
			   {
				   dto.put(fieldName, val);   
			   }
		   }
	   }
       return dto;
    }
}
