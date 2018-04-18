package com.sjh.thinkinginjava.thread;

public class DualSynch {
    /**
     * f()在this同步,而g()有一个在syncObject上同步的块.因此,两个同步相互独立.?????
     * (输出结果与书上示例不同?????)
     */
    private Object syncObject = new Object();
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }
    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
