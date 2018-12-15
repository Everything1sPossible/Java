package com.sjh.code.codegenerator.core;

import com.sjh.code.codegenerator.core.util.FilePathUtil;
import com.sjh.code.codegenerator.core.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sjh
 * @Description: 普通实体类文件生成类
 * @date 2018/12/14 22:57
 */
public class SimpleEntityJavaTemplate extends AbstractFreemarkerTemplate {

    /** 默认Freemarker模板名称*/
    private static final String DEFAULT_FTL_NAME = "simpleEntity.ftl";
    private static final String SUFFIX_JAVA = ".java";

    public SimpleEntityJavaTemplate(String freemarkerTemplateName) {
        super(freemarkerTemplateName);
    }

    public SimpleEntityJavaTemplate() {
        super(DEFAULT_FTL_NAME);
    }

    @Override
    protected Map<String, Object> createParameterMap(FreemarkerEntity freemarkerEntity) {
        String fieldsCommend = freemarkerEntity.getFieldsCommend();
        String entityFilePath = freemarkerEntity.getEntityFilePath();
        String packageName = FilePathUtil.cutPathToPackage(entityFilePath);
        Map<String, Object> fieldsMap = JsonUtil.jsonToMap(fieldsCommend);
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("package", packageName);
        parameterMap.put("fields", fieldsMap);
        return parameterMap;
    }


    @Override
    protected String getDirPath(FreemarkerEntity freemarkerEntity) {
        return freemarkerEntity.getEntityFilePath();
    }

    @Override
    protected String getJavaFileName(FreemarkerEntity freemarkerEntity) {
        return freemarkerEntity.getFileName() + SUFFIX_JAVA;
    }
}
