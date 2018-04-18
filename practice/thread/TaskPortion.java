package com.sjh.thinkinginjava.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch countDownLatch;

    public TaskPortion(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            work();
            countDownLatch.countDown();
        } catch (Exception e) {

        }
    }
    public void work() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(10));
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("%1-3d ", id);
    }
}
