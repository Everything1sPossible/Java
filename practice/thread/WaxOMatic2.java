package com.sjh.thinkinginjava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        CarExt car = new CarExt();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOn2(car));
        exec.execute(new WaxOff2(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdown();
    }
}
