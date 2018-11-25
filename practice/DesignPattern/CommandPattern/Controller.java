package com.sjh.DesignPattern.CommandPattern;

/**
 * @author sjh
 * @Description: “控制中心”，保存“命令对象”引用
 * @date 2018/11/25 20:40
 */
public class Controller {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeButton() {
        command.execute();
    }

    public void undoButton() {
        command.undo();
    }
}
