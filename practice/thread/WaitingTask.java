package com.sjh.thinkinginjava.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch countDownLatch;

    public WaitingTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (Exception e) {
            System.out.println(this + " interrupted");
        }
    }
    public void work() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(10));
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1-3d ", id);
    }
}
