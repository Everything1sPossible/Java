package com.sjh.DesignPattern.CommandPattern;

/**
 * @author sjh
 * @Description: “命令对象”接口实现类，保存“接收者”引用
 * @date 2018/11/25 20:38
 */
public class LigntOnCommand implements Command {

    private Light light;

    public LigntOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
