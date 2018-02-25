package com.sjh.practice.annotation;

@DBTable(tableName = "USER")
public class User {

    @FiledType(filedTypeName = "ID", filedType = FiledTypeEnum.INTEGER, filedSize = 36, constraints = @Constraints(primaryKey = true))
    private int id;
    @FiledType(filedTypeName = "NAME", filedType = FiledTypeEnum.VARCHAR, filedSize = 20, constraints = @Constraints(notNull = false))
    private String name;
    @FiledType(filedTypeName = "NUM", filedType = FiledTypeEnum.VARCHAR, filedSize = 20, constraints = @Constraints(unique = true))
    private String num;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}
