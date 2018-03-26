package com.sjh.thinkinginjava.handleobjectExt;

import java.util.HashSet;
import java.util.Set;

/**
 * Set工具类
 */
public class Sets {
    /**
     * 求并集
     * @param a:目标Set a
     * @param b:目标Set b
     * @param <T>:泛型类型
     * @return
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 求交集
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    /**
     * 求差集
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.removeAll(b);
        return result;
    }

    /**
     * 求并集除去交集的部分
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}
