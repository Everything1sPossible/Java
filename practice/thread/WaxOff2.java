package com.sjh.thinkinginjava.thread;

import java.util.concurrent.TimeUnit;

public class WaxOff2 implements Runnable {
    private CarExt car;

    public WaxOff2(CarExt car) {
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
