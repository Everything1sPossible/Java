package com.sjh.code.codegenerator.core;

import com.sjh.code.codegenerator.core.util.FilePathUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @author sjh
 * @Description: Java文件生成接口抽象类
 * @date 2018/12/14 22:40
 */
public abstract class AbstractFreemarkerTemplate implements JavaTemplateInterface{
    
    /** 默认模板目录*/
    private static final String DEFAULT_TEMPLATE_DRIECTORY = "src/main/resources/templates/freemarker";
    /** 默认编码*/
    private static final String ENCODING = "UTF-8";
    /** Freemarker模板名称*/
    private String freemarkerTemplateName;

    public AbstractFreemarkerTemplate(String freemarkerTemplateName) {
        this.freemarkerTemplateName = freemarkerTemplateName;
    }

    @Override
    public void createJavaFile(FreemarkerEntity freemarkerEntity) {
        System.out.println("==============开始创建Java文件================");
        Map<String, Object> parameterMap = createParameterMap(freemarkerEntity);
        //Java文件名称
        String javaFileName = getJavaFileName(freemarkerEntity);
        parameterMap.put("fileName", FilePathUtil.cutJavaSuffix(javaFileName));
        //文件夹路径
        String dirPath = getDirPath(freemarkerEntity);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File nFile = new File(dirPath + File.separator + javaFileName);
        if (nFile.exists()) {
            throw new RuntimeException("File \'"+javaFileName+"\' already exists");
        }
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);
            Template template = getConfiguration(DEFAULT_TEMPLATE_DRIECTORY).getTemplate(this.freemarkerTemplateName, ENCODING);
            template.process(parameterMap, writer);
            writer.close();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        } 
    }

    /**
     * @Description: 自定义各子模板的参数map
     * @author sjh
     * @date 2018/12/14 22:45
     */
    protected abstract Map<String, Object> createParameterMap(FreemarkerEntity freemarkerEntity);
    
    /**  
     * @Description: 获取生成后的Java文件夹路径
     * @author sjh
     * @date 2018/12/15 0:09
     */
    protected abstract String getDirPath(FreemarkerEntity freemarkerEntity);

    /**
     * @Description: 获取生成后的Java文件名称,要带上后缀.java
     * @author sjh
     * @date 2018/12/15 0:26
     */
    protected abstract String getJavaFileName(FreemarkerEntity freemarkerEntity);

    private Configuration getConfiguration(String templateDirectory) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        try {
            configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
            configuration.setDirectoryForTemplateLoading(new File(templateDirectory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }
}
