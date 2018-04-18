package com.sjh.thinkinginjava.thread;

public abstract class IntGenerator {
    /**
     * canceled标志是boolean类型的,所以它是原子性的,即诸如赋值和返回值这样的简单操作
     * 在发生时没有中断的可能,因此不会看到这个域处于在执行这些简单操作的过程中的中间状态.
     * 为了保证可视性,canceled还是volatile的.
     */
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel() {
        canceled = true;
    }
    public boolean isCanceled() {
        return canceled;
    }
}
