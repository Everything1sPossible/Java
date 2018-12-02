package com.sjh.DesignPattern.TemplatePattern;

/**
 * @author sjh
 * @Title: Tea
 * @ProjectName com.sjh.DesignPattern.TemplatePattern
 * @Description: TODO
 * @date 2018/12/2 22:09
 */
public class Tea extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("Steeping the tea..");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Lemon..");
    }
}
