package com.sjh.demo.redislock;

/**
 * @author sjh
 * @Title: LockConstants
 * @ProjectName com.sjh.demo.redislock
 * @Description: TODO
 * @date 2018/11/29 22:28
 */
public class LockConstants {
    public static final String OK = "OK";

    /** NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist. **/
    public static final String NOT_EXIST = "NX";
    public static final String EXIST = "XX";

    /** expx EX|PX, expire time units: EX = seconds; PX = milliseconds **/
    public static final String SECONDS = "EX";
    public static final String MILLISECONDS = "PX";

    private LockConstants() {}
}
