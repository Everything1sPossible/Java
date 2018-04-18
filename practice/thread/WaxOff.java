package com.sjh.thinkinginjava.thread;

import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitingForWaxing();
                System.out.println("Wax off!");
                TimeUnit.SECONDS.sleep(2);
                car.buffed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ending Wax Off task!");
    }
}
