package com.sjh.thinkinginjava.thread;

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                ds.g();
            }
        }.start();
    }
}
