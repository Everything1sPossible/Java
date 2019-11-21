package com.sjh.bloom;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import redis.clients.jedis.Jedis;

import java.nio.charset.Charset;

/**
 * @author sjh
 * @Description: Redis版布隆过滤器
 * @date 2019/11/21 21:15
 */
public class RedisBloomFilter {

    //预计插入的数量
    private long expectedInsertions;

    //误判率
    private double fpp;

    //数组长度
    private static long numBits;

    //Hash函数数量
    private static int numHashFunctions;

    //bit数组名称
    private static final String bitName = "bloom-bit";

    private static final Jedis jedis = new Jedis("localhost", 6379);

    public RedisBloomFilter(long expectedInsertions, double fpp) {
        this.expectedInsertions = expectedInsertions;
        this.fpp = fpp;

        numBits = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
    }

    //向bit数组添加数据
    public void put(String key) {
        jedis.auth("123456");
        long[] indexs = getIndexs(key);
        for (int i = 0 ; i < indexs.length; i++) {
            jedis.setbit(bitName, indexs[i], true);
        }
    }

    //获取是否存在
    public boolean get(String key) {
        long[] indexs = getIndexs(key);
        for (int i = 0 ; i < indexs.length; i++) {
            Boolean getbit = jedis.getbit(bitName, indexs[i]);
            if (!getbit) {
                System.out.println("【" + key + "】不存在！");
                return false;
            }
        }
        return true;
    }

    /**
     * 根据key获取bitmap下标
     */
    private static long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    //Hash函数
    private static long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }

    //计算Hash函数数量
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    //计算数组长度
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    public static void main(String[] args) {
        RedisBloomFilter bloomFilter = new RedisBloomFilter(100, 0.01);
        bloomFilter.put("1");
        bloomFilter.put("2");
        bloomFilter.put("3");
        bloomFilter.put("4");
        bloomFilter.put("5");
        bloomFilter.put("6");
        bloomFilter.put("7");
        bloomFilter.put("8");
        bloomFilter.put("9");
        bloomFilter.put("10");
        bloomFilter.put("11");
        bloomFilter.put("12");
        bloomFilter.put("13");
        bloomFilter.put("14");
        bloomFilter.put("15");
        bloomFilter.put("16");
        System.out.println(bloomFilter.get("102"));
    }
}
