package com.sjh.DesignPattern.TemplatePattern;

/**
 * @author sjh
 * @Title: Test
 * @ProjectName com.sjh.DesignPattern.TemplatePattern
 * @Description: TODO
 * @date 2018/12/2 22:12
 */
public class Test {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareRecipe();
        System.out.println("==============================");
        Coffee coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
