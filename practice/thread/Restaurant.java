package com.sjh.thinkinginjava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 调度中心
 */
public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
