package com.wfu.system.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class BeanUtils
{
	/**
	 * 私有构造子,禁止被实例化
	 */
    private BeanUtils(){}
    
    /**
     * Bean <==> Map 转换类型列表
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
     * 校验字段的描述类型,是否在转换列表限定范围内
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
     * 通用对象创建及赋值
     * <应用于O/R Mapping框架中,持久化对象的创建>
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
 	   Class  fieldType=null;                   //field类型
 	   String fieldName=null;                   //field名称; 
 	   String setMethodName=null;               //field设置子名称 
 	   Method setMethot=null;                   //field设置子
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
     * 新对象创建赋值
     * @param bean
     * @param properties
     * @throws Exception
     */
    public static void populate(Object bean, Map<String,String> properties)throws Exception
    {
       Class classType=bean.getClass();
  	   Field fieldList[]=classType.getDeclaredFields();
  	   Class  fieldType=null;                   //field类型
  	   String fieldName=null;                   //field名称; 
  	   String setMethodName=null;               //field设置子名称 
  	   Method setMethot=null;                   //field设置子
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
     * 生成数据对象的Map形式copy
     * @param bean       原始Bean
     * @param likeField  模糊查询字段列表 
     * @return
     * @throws Exception
     */
    public static  Map<String,Object> describe(Object bean,String...likeFieldList)throws Exception
    {
 	   Class classType=bean.getClass();
       //获取Bean中的属性列表
	   Field fieldList[]=classType.getDeclaredFields();
	   //计算字段数量
	   int size=fieldList.length;  
	   //计算Map的初始容量
	   int initSize=((Number)(size/0.75)).intValue()+2;
       /**
        * 返回的Map对象
        * Map中映射数的最大值为Bean中字段的数量,
        * 避免HashMap进行rehash处理
        */
	   Map<String,Object> dto=new HashMap<>(initSize); 
	   String fieldName=null;                   //field名称; 
	   String getMethodName=null;               //field访问子名称 
	   Method getMethot=null;                   //field访问子
	   
	   /**
	    * 迭代所有字段的数据类型
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
     * 生成数据对象的Map形式copy
     * @param bean
     * @return
     * @throws Exception
     */
    public static  Map<String,Object> describe(Object bean)throws Exception
    {
 	   Class classType=bean.getClass();
       //获取Bean中的属性列表
	   Field fieldList[]=classType.getDeclaredFields();
	   //计算字段数量
	   int size=fieldList.length;  
	   //计算Map的初始容量
	   int initSize=((Number)(size/0.75)).intValue()+1;
       /**
        * 返回的Map对象
        * Map中映射数的最大值为Bean中字段的数量,
        * 避免HashMap进行rehash处理
        */
	   Map<String,Object> dto=new HashMap<>(initSize); 
	   String fieldName=null;                   //field名称; 
	   String getMethodName=null;               //field访问子名称 
	   Method getMethot=null;                   //field访问子
	   
	   /**
	    * 迭代所有字段的数据类型
	    */
	   for(Field field:fieldList)
	   {
		   if(BeanUtils.checkFieldType(field.getType()))
		   {
			   //获取字段名称
			   fieldName=field.getName();
			   //拼接该字符段访问子
			   getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			   //生成访问子方法
			   getMethot=classType.getMethod(getMethodName, new Class[]{});
			   //调用访问子方法读取Bean数据写入dto
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
