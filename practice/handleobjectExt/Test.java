package com.sjh.thinkinginjava.handleobjectExt;


import com.sjh.thinkinginjava.generic.LinkedStack;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Test {
    public static void main(String[] args) {
//        List list = Arrays.asList(("Anti Tracks is a complete solution to protect your " +
//                "privacy and enhance your PC performance. ").split(" "));
//        Collection collection = list.subList(0, 3);
//        System.out.println(Arrays.toString(collection.toArray()));
//        collection.add("a"); //UnsupportedOperationException
//        HashSet hashSet = new HashSet();
//        LinkedHashSet linkedHashSet = new LinkedHashSet();
//        TreeSet treeSet = new TreeSet();
        HashMap hashMap = new HashMap();
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        String a = "a";
//        String a1 = new String("a");
//        System.out.println(a.equals(a1));

        String[] hellos = "hello hello".split(" ");
        System.out.println(hellos[0].hashCode());   //99162322
        System.out.println(hellos[1].hashCode());   //99162322
        System.out.println(hellos[0] == hellos[1]); //false
        char ch = 'A';
        System.out.println((char)( ch + (char)32));
    }
}
