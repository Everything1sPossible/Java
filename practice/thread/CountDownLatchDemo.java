package com.sjh.thinkinginjava.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    static final int SIZE = 100;
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch downLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
             exec.execute(new TaskPortion(downLatch));
        }
        for (int i = 0; i < 10; i++) {
            exec.execute(new WaitingTask(downLatch));
        }
        System.out.println("Latched all tasks");
        exec.shutdown();
    }
}
