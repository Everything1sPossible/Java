package com.sjh.thinkinginjava.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exce = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            results.add(exce.submit(new TaskWithResult(i)));
        }
        for(Future future : results) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                exce.shutdown();
            }
        }
    }
}
