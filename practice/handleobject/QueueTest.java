package com.sjh.thinkinginjava.handleobject;

import java.util.*;

public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList();
        Queue<String> queue2 = new PriorityQueue<String>();
        queue.add("a");//加入队尾
    }

    public static void priQueue() {
        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> priorityQueue = new PriorityQueue<String>(strings);
    }
}
