package com.sjh.thinkinginjava.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenCheck implements Runnable {
    private IntGenerator intGenerator;
    private final int id;

    public EvenCheck(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                intGenerator.cancel();
            }
        }
    }

    public static void test (IntGenerator intGenerator, int count) {
        System.out.println("Press Control-C to exit!");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenCheck(intGenerator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator intGenerator) {
        test(intGenerator, 10);
    }
}
