package com.sjh.thinkinginjava.handleobjectExt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionsDemo {
    private static List<String> data = listData();
    public static List<String> listData() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        return list;
    }
    public static void main(String[] args) {
        List<String> a = Collections.unmodifiableList(new ArrayList<String>(data));//设置只读
        Iterator<String> iterator = a.iterator();
        System.out.println(iterator.next());//可以获取
        System.out.println(a.add("e"));//不可以更新
    }
}
