package com.dhcc.hnzx.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dhcc.common.uuid.Uuid;
import com.dhcc.hnzx.po.PublicClientsPo;
import com.google.common.base.Strings;

/**
 * <br>包名: com.dhcc.hnzx.util</br>
 * <br>文件名称: SqlUtils.java</br>
 * <br>内容摘要: SQL语句工具类(主要用于本框架DButils,写的比较死,约定大于配置,应该也可用于JDBC)</br>
 * @author:  Administrator
 * @Date:  2018-3-29 下午6:47:19
 */
public class SqlUtils {
	
	/**
	 * 查询语句拼接,用于单表查询,JavaBean的字段名应与表字段名严格一一对应
	 * @param tableName : 表名
	 * @param clazz : 目标JavaBean
	 * @param strSql : SQL StringBuilder
	 */
	public static void appendSelectList(String tableName, Object obj, StringBuilder strSql) {
		Class clazz = obj.getClass();
		int fieldNum = 0;
		Field[] fields = clazz.getDeclaredFields();
		strSql.append("select ");
		for(Field field : fields) {
			fieldNum++;
			String fieldName = field.getName();
			//遇到"serialVersionUID"字段直接跳出进入下一次循环
			if (fieldName != null && fieldName.equals("serialVersionUID")) {
				continue;
			}
			if(fieldNum == fields.length) {
				strSql.append(fieldName);
			} else {
				strSql.append(fieldName + ",");
			}
		}
		strSql.append(" from " + tableName);
	}
	/**
	 * 数量查询语句拼接,用于单表查询,只包含左括号,与appendSelectCountRight()方法一起使用
	 * @param strSql : SQL StringBuilder
	 */
	public static void appendSelectCountLeft(StringBuilder strSql) {
		strSql.append("select COUNT(0) from ( ");
	}
	/**
	 * 数量查询语句拼接,用于单表查询,只包含右括号,与appendSelectCountLeft()方法一起使用
	 * @param strSql : SQL StringBuilder
	 */
	public static void appendSelectCountRight(StringBuilder strSql) {
		strSql.append(" )");
	}
	/**
	 * 单一实例查询,用于单一条件查询语句拼接,如果多条件,可以结合appendSelectList()与appendWhereSql()使用
	 * @param conditionName : 条件名称
	 * @param tableName : 表名
	 * @param clazz : 目标JavaBean
	 * @param strSql : SQL StringBuilder
	 */
	public static void appendSelectByOne(String conditionName, String tableName, Object obj, StringBuilder strSql) {
		//拼接查询语句
		appendSelectList(tableName, obj, strSql);
		//拼接单一条件
		strSql.append(" where " + conditionName + " = ?");
	}
	/**
	 * 添加SQL语句拼接,同时返回?占位符对应的数据
	 * @param tableName : 表名
	 * @param clazz : 目标JavaBean
	 * @param strSql : SQL StringBuilder
	 * @return 
	 * @throws Exception
	 */
	public static Object[] appendInsert(String tableName, Object obj, StringBuilder strSql){
		Class clazz = obj.getClass();
		int fieldNum = 0;
		List paramList = new ArrayList();
		//获取类的所有字段
		Field[] fields = clazz.getDeclaredFields();
		strSql.append("insert into " + tableName + "( ");
		for(Field field : fields) {
			fieldNum++;
			String fieldName = field.getName();
			//遇到"serialVersionUID"字段直接跳出进入下一次循环
			if (fieldName != null && fieldName.equals("serialVersionUID")) {
				continue;
			}
			if(fieldNum == fields.length) {
				strSql.append(fieldName);
			} else {
				strSql.append(fieldName + ",");
			}
			String fieldGetMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			Method fieldGetMethod = null;
			Object value = null;
			try {
				fieldGetMethod = clazz.getMethod(fieldGetMethodName, new Class[]{});
				value = fieldGetMethod.invoke(obj, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(fieldName != null && fieldName.equals("id")) {
				paramList.add(new Uuid().getUUID());//如果id字段直接添加uuid值
			} else {
				paramList.add(value);
			}
		}
		strSql.append(" ) ");
		strSql.append(" values( ");
		for(int i = 0; i < paramList.size(); i++) {
			if (i != paramList.size() - 1) {
				strSql.append("?,");
			} else {
				strSql.append("?");
			}
		}
		strSql.append(" ) ");
		return paramList.toArray();
	}
	/**
	 * 条件删除sql语句拼接,同时返回?占位符对应的数据
	 * @param tableName : 表名
	 * @param clazz : 目标JavaBean
	 * @param strSql : SQL StringBuilder
	 * @param conditionNames : 条件字段的名称数组
	 * @return
	 */
	public static Object[] appendDelete(String tableName, Object obj, StringBuilder strSql, String...conditionNames ) {
		Class clazz = obj.getClass();
		strSql.append("delete from " + tableName + " ");
		List paramList = new ArrayList();
		if(conditionNames != null) {
			for(int i = 0; i < conditionNames.length; i++) {
				if(strSql.indexOf("where")>0){
					strSql.append(" and "+ conditionNames[i] + " = ? ");
				}
				else{
					strSql.append(" where "+ conditionNames[i] + " = ? ");
				}
				String fieldGetMethodName = "get"+conditionNames[i].substring(0,1).toUpperCase() + conditionNames[i].substring(1);
				Method fieldGetMethod = null;
				Object value = null;
				try {
					fieldGetMethod = clazz.getMethod(fieldGetMethodName, new Class[]{});
					value = fieldGetMethod.invoke(obj, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				paramList.add(value);
			}
		}
		return paramList.toArray();
	}
	/**
	 * 根据ID进行sql语句拼接,同时返回?占位符对应的数据
	 * @param tableName : 表名
	 * @param clazz : 目标JavaBean
	 * @param strSql : SQL StringBuilder
	 * @return
	 */
	public static Object[] appendUpdateById(String tableName, Object obj, StringBuilder strSql) {
		Class clazz = obj.getClass();
		int fieldNum = 0;
		//获取类的所有字段
		Field[] fields = clazz.getDeclaredFields();
		List paramList = new ArrayList();
		Object idValue = null;
		strSql.append("update " + tableName + " ");
		strSql.append(" set ");
		for(Field field : fields) {
			fieldNum++;
			String fieldName = field.getName();
			//遇到"serialVersionUID"或者"id"字段直接跳出进入下一次循环
			if (fieldName != null && fieldName.equals("serialVersionUID")) {
				continue;
			} 
			String fieldGetMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			Method fieldGetMethod = null;
			Object value = null;
			try {
				fieldGetMethod = clazz.getMethod(fieldGetMethodName, new Class[]{});
				value = fieldGetMethod.invoke(obj, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (fieldName != null && fieldName.equals("id")) {
				idValue = value;
				continue;
			}
			if(fieldNum == fields.length) {
				strSql.append(fieldName + " = ?");
			} else {
				strSql.append(fieldName + " = ?,");
			}
			paramList.add(value);
		}
		strSql.append(" where id=?");
		paramList.add(idValue);
		return paramList.toArray();
	}
	/**
	 * SQL语句条件拼接方法(不进行排序)
	 * @param paramList: 存放占位符数据,顺序一一对应
	 * @param map: key:字段名称 value:[字段表名称,字段是否模糊查询,字段数据]
	 * @param strSql: SQL StringBuilder
	 */
	public static void appendWhereSql(List paramList, Map<String, Object[]> map, StringBuilder strSql) {
		appendWhereSql(paramList, map, strSql, null, null);
	}
	/**
	 * SQL语句条件拼接方法(默认倒序排序)
	 * @param paramList: 存放占位符数据,顺序一一对应
	 * @param map: key:字段名称 value:[字段表名称,字段是否模糊查询,字段数据]
	 * @param strSql: SQL StringBuilder
	 */
	public static void appendWhereSql(List paramList, Map<String, Object[]> map, StringBuilder strSql, String orderName) {
		appendWhereSql(paramList, map, strSql, orderName, "desc");
	}
	/**
	 * SQL语句条件拼接方法(进行排序)
	 * @param paramList: 存放占位符数据,顺序一一对应
	 * @param map: key:字段名称 value:[字段表名称,字段是否模糊查询,字段数据]
	 * @param strSql: SQL StringBuilder
	 * @param orderName: 排序字段名称
	 * @param orderType: 排序方式
	 */
	public static void appendWhereSql(List paramList, Map<String, Object[]> map, StringBuilder strSql, String orderName, String orderType) {
		Set<Entry<String, Object[]>> set = map.entrySet();
		for(Entry<String, Object[]> entry : set) {
			String key = entry.getKey();
			Object[] values = entry.getValue();
			if(!Strings.isNullOrEmpty(String.valueOf(values[2]))){
				if(strSql.indexOf("where")>0){
					if(String.valueOf(values[1]) != null && (String.valueOf(values[1])).equals("true")) {
						strSql.append(" and " + values[0] +" like '%'||?||'%' ");
					}else {
						strSql.append(" and "+ values[0] + " = ? ");
					}
				}
				else{
					if(String.valueOf(values[1]) != null && (String.valueOf(values[1])).equals("true")) {
						strSql.append(" where " + values[0] +" like '%'||?||'%' ");
					}else {
						strSql.append(" where "+ values[0] + " = ? ");
					}
				}
				paramList.add(values[2]);
			}
		}
		if(orderName != null) {
			strSql.append(" order by " + orderName + " " + orderType + "");
		}
	}
	
	public static void main(String[] args) throws Exception {
//		List paramList = new ArrayList();
		StringBuilder strSql = new StringBuilder();
//		strSql.append("select t1.id,t1.clients_name,t1.agency_code,t1.loan_number," +
//							 "t1.clients_category,t1.clients_nature " +
//						"from public_clients t1 ");
//		Map<String, Object[]> map = new HashMap();
//		map.put("id", new Object[]{"id", "false", "123"});
//		map.put("clientsName", new Object[]{"clients_name", "true", "123"});
//		map.put("num", new Object[]{"num", "false", 18});
		
//		SqlUtils.appendSelectCountLeft(strSql);
//		SqlUtils.appendSelectList("public_clients", PublicClientsPo.class, strSql);
//		SqlUtils.appendWhereSql(paramList, map, strSql, "id");
//		SqlUtils.appendSelectCountRight(strSql);
//		appendSelectByOne("id","public_clients", PublicClientsPo.class, strSql);
//		Object[] params = SqlUtils.appendInsert("public_clients", PublicClientsPo.class, strSql);
//		String[] conditionNames = {};
//		Object[] params = SqlUtils.appendDelete("public_clients", PublicClientsPo.class, strSql, conditionNames);
		Object[] params = SqlUtils.appendUpdateById("public_clients", PublicClientsPo.class, strSql);
		System.out.println(strSql.toString());
//		System.out.println(Arrays.toString(params));
//		System.out.println(paramList);
	}

}
