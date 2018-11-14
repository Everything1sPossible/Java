package com.sjh.demo;

import redis.clients.jedis.Jedis;

/**
 * @author Admin
 * @Description: redis分布式锁
 */
public class JedLock {

    private static final String REDIS_IP = "127.0.0.1";
    private static final String REDIS_PORT = "6379";
    private static final String REDIS_LOCK_KEY = "redis_lock_key";
    //等待锁的时间
    private static final int RETRY_TIME = 10 * 1000;
    //锁超时的时间
    private static final int EXPIRE_TIME = 6 * 1000;
    private boolean locked;
    private long lockValue;

    public synchronized boolean lock(Jedis jedis) {
        int retryTime = RETRY_TIME;
        try {
            while (retryTime > 0) {
                lockValue = System.nanoTime();
                if ("OK".equalsIgnoreCase(jedis.set(REDIS_LOCK_KEY, String.valueOf(lockValue), "NX", "PX", EXPIRE_TIME))) {
                    locked = true;
                    return locked;
                }
                retryTime -= 100;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized void unlock(Jedis jedis){
        if(locked) {
            String currLockVal = jedis.get(REDIS_LOCK_KEY);
            if(currLockVal!=null && Long.valueOf(currLockVal) == lockValue){
                jedis.del(REDIS_LOCK_KEY);
                locked = false;
            }
        }
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis(REDIS_IP, Integer.valueOf(REDIS_PORT));
        jedis.auth("123456");
        JedLock redLock = new JedLock();
        for (int i = 0; i < 100; i++) {
            int j = i;
            Runnable runnable = () -> {
                if(redLock.lock(jedis)) {
                    System.out.println(Thread.currentThread().getName() + j + ": 获得锁！");
//                    try {
//                        Thread.sleep(25000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + j + ": 处理完成！");
                    redLock.unlock(jedis);
                    System.out.println(Thread.currentThread().getName() + j + ": 释放锁！");
                }else {
                    System.out.println("get lock fail!!!");
                }
            };
            runnable.run();
        }

    }
}
