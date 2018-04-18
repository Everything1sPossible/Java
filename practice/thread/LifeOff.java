package com.sjh.thinkinginjava.thread;

public class LifeOff implements Runnable{
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LifeOff() {
    }
    public LifeOff(int countDown) {
        this.countDown = countDown;
    }
    public String status () {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LifeOff") + "), ";
    }
    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
//            Thread.yield();//告诉线程调度器,其他具有相同优先级的线程可以运行.
        }
    }
}
