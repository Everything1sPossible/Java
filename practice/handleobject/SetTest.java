package com.sjh.thinkinginjava.handleobject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet();
        TreeSet treeSet = new TreeSet();
        set.add("a");
        set.add("b");
        set.add("c");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            /**
             * 因为底层是HashMap(数组+链表),此处迭代器获取下一个值,
             * 先从头开始获取链表的值,如果链表上的最后一个值没有next了,再接着获取数组下一个坐标上的链表值
             */
            System.out.println(iterator.next());
        }
    }
}
