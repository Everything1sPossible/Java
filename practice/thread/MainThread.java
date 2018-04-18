package com.sjh.thinkinginjava.thread;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {
    public static void main(String[] args) {
//        LifeOff lifeOff = new LifeOff();
//        lifeOff.run();

//        Thread thread = new Thread(new LifeOff());
//        thread.start();

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new LifeOff());
        }
        executor.shutdown();

//        ExecutorService exec = Executors.newFixedThreadPool(5);//预先分配线程
//        for (int i = 0; i < 5; i++) {
//            exec.execute(new LifeOff());
//        }
//        exec.shutdown();

//        ExecutorService exec = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 5; i++) {
//            exec.execute(new LifeOff());
//        }
//        exec.shutdown();
    }
}
