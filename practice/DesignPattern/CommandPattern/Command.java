package com.sjh.DesignPattern.CommandPattern;

/**
 * @author sjh
 * @Description: "命令对象"接口
 * @date 2018/11/25 20:38
 */
public interface Command {
    /**
     * @Description: 执行方法
     * @date 2018/11/25 20:43
     */
    void execute();

    /**
     * @Description: 撤销方法
     * @date 2018/11/25 21:12
     */
    void undo();
}
