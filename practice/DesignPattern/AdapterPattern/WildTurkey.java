package com.sjh.DesignPattern.AdapterPattern;

/**
 * @author sjh
 * @Title: WildTurkey
 * @ProjectName com.sjh.DesignPattern.AdapterPattern
 * @Description: TODO
 * @date 2018/11/27 19:06
 */
public class WildTurkey implements Turkey {
    @Override
    public void fly() {
        System.out.println("i am fly a short distance..");
    }

    @Override
    public void gobble() {
        System.out.println("gobble gobble..");
    }
}
