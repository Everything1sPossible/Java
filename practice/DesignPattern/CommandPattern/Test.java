package com.sjh.DesignPattern.CommandPattern;

/**
 * @author sjh
 * @Title: Test
 * @ProjectName com.sjh.DesignPattern.CommandPattern
 * @Description: TODO
 * @date 2018/11/25 20:40
 */
public class Test {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Light light = new LightImpl();
        Command command = new LigntOnCommand(light);
        controller.setCommand(command);
        controller.executeButton();
        controller.undoButton();
    }
}
