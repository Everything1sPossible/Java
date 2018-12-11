package com.sjh.DesignPattern.StatusPattern;

/**
 * @author sjh
 * @Title: Test
 * @ProjectName com.sjh.DesignPattern.StatusPattern
 * @Description: TODO
 * @date 2018/12/11 19:00
 */
public class Test {
    public static void main(String[] args) {
        VoteMachine voteMachine = new VoteMachine();
        voteMachine.vote("张三", "A");
        voteMachine.vote("张三", "C");
        voteMachine.vote("张三", "A");
        voteMachine.vote("张三", "B");
        voteMachine.vote("李四", "A");
        voteMachine.vote("李四", "A");
        voteMachine.vote("张三", "A");
    }
}
