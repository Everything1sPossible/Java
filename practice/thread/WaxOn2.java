package com.sjh.thinkinginjava.thread;

import java.util.concurrent.TimeUnit;

public class WaxOn2 implements Runnable{
    private CarExt car;
    public WaxOn2(CarExt car) {
        this.car = car;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax on!");
                TimeUnit.SECONDS.sleep(2);
                car.waxed();
                car.waitingForBuffing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ending Wax On task!");
    }
}
