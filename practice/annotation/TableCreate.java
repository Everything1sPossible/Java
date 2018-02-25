package com.sjh.practice.annotation;


import java.lang.reflect.Field;

public class TableCreate {

    public static void main(String[] args) throws Exception {
        String sql = createTableSQL("com.sjh.practice.annotation.User");
        System.out.println(sql);
    }

    /**
     * 创建表的sql语句
     * @param className:要带包名,例:com.sjh.practice.annotation.User
     * @return:返回创建表的sql语句
     * @throws Exception
     */
    public static String createTableSQL(String className) throws Exception {
        StringBuilder sb = new StringBuilder();
        Class clazz = Class.forName(className);//获取指定类
        DBTable dbTable = (DBTable)clazz.getAnnotation(DBTable.class);//获取DBTable注解
        String tableName = dbTable.tableName();//获取表名
        sb.append(" CREATE TABLE " + tableName + "( \n");
        Field[] fields = clazz.getDeclaredFields();//获取类的所有属性
        for (Field filed : fields) {
            //获取字段FiledType注解
            FiledType filedType = filed.getAnnotation(FiledType.class);
            sb.append(getFiledSQL(filedType));
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * 拼接字段SQL
     * @param filedType
     * @return
     */
    public static String getFiledSQL(FiledType filedType) {
        StringBuilder sb = new StringBuilder();
        if(filedType == null) {
            throw new RuntimeException("请传入FiledType..");
        }
        String filedTypeName = filedType.filedTypeName();
        sb.append(" " + filedTypeName);
        int filedSize = filedType.filedSize();
        sb.append(" " + getFiledType(filedType.filedType()) + "(" + filedSize + ")");
        sb.append(getConstratints(filedType.constraints()) + "\n");
        return sb.toString();
    }

    /**
     * 获取Constraints注解详情
     * @param constraints
     * @return
     */
    public static String getConstratints(Constraints constraints) {
        StringBuilder sb = new StringBuilder();
        if(constraints == null) {
            throw new RuntimeException("请传入Constraints..");
        }
        sb.append(constraints.notNull()? " NOT NULL" : " DEFAULT NULL");
        sb.append(constraints.unique()? " UNIQUE" : "");
        sb.append(constraints.primaryKey()? " PRIMARY KEY" : "");
        return sb.toString();
    }

    public static String getFiledType(FiledTypeEnum filedTypeEnum) {
        String value = "";
        switch (filedTypeEnum){
            case VARCHAR:
                value = "VARCHAR";
                break;
            case VARCHAR2:
                value = "VARCHAR2";
                break;
            case INTEGER:
                value = "INTEGER";
                break;
            default:
                value = "VARCHAR";
        }
        return value;
    }
}
