package com.sjh.demo;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private static final int EXPIRE_TIME = 60 * 1000;
    private boolean locked;
    private long lockValue;

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";


    public boolean lock(Jedis jedis) {
        int retryTime = RETRY_TIME;
        try {
            while (retryTime > 0) {
                lockValue = System.nanoTime();
                if (LOCK_SUCCESS.equalsIgnoreCase(jedis.set(REDIS_LOCK_KEY, String.valueOf(lockValue), "NX", "PX", EXPIRE_TIME))) {
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

    public void unlock(Jedis jedis){
        if(locked) {
            /**
             * 此处线程不安全，客户端Aget获取到值之后，要进行del操作，但是此时在del前排队一个很耗时的操作，比如keys*，那么有可能锁就自己过期了，
             * 此时再进行del操作就不安全了，因为有可能会把客户端B获取到的锁del了（客户端B的set也排在客户端A的del前）。
             */
            String currLockVal = jedis.get(REDIS_LOCK_KEY);
            if(currLockVal!=null && Long.valueOf(currLockVal) == lockValue){
                jedis.del(REDIS_LOCK_KEY);
                locked = false;
            }
        }
    }
    public boolean unlock2(Jedis jedis){
        if(locked) {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(REDIS_LOCK_KEY), Collections.singletonList(String.valueOf(lockValue)));
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int j = i;
            Runnable runnable = () -> {
                Jedis jedis = new Jedis(REDIS_IP, Integer.valueOf(REDIS_PORT));
                jedis.auth("123456");
                JedLock redLock = new JedLock();
                if(redLock.lock(jedis)) {
                    System.out.println(Thread.currentThread().getName() + j + ": 获得锁！");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + j + ": 处理完成！");
                    if (redLock.unlock2(jedis)) {
                        System.out.println(Thread.currentThread().getName() + j + ": 释放锁！");
                    }
                }else {
                    System.out.println("get lock fail!!!");
                }
            };
            runnable.run();
        }

    }
}
