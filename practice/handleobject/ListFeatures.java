package com.sjh.thinkinginjava.handleobject;

import java.util.*;

public class ListFeatures {
    public static void main(String[] args) {
//        arrayList();
//        linkedList();
        String s = null;
        System.out.println(s.length());

    }

    public static void stack() {
        Stack stack = new Stack();
    }

    public static void linkedList() {
        LinkedList<String> list = new LinkedList<String>();
        list.addFirst("a");
        list.addFirst("b");
        for (String str: list) {
            System.out.println(str);
        }
    }

    public static void arrayList() {
        List<String> list = new ArrayList();
        List<String> list2 = new LinkedList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals("b")) {
                iterator.remove();
            }
            else {
                System.out.println(str);
            }
        }
    }
}
