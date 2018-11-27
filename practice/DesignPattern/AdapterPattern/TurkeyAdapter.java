package com.sjh.DesignPattern.AdapterPattern;

/**
 * @author sjh
 * @Description: 将"火鸡"适配成“鸭子”的适配器
 * @date 2018/11/27 19:07
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}
