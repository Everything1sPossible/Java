package com.sjh.thinkinginjava.handleobjectExt;

import com.sjh.thinkinginjava.generic.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueBehavior {
    private static int count = 15;
    static <T> void test(Queue<T> queue, Generator<T> generator) {
        for(int i = 0; i < count; i++) {
            queue.offer(generator.next());
        }
        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }
    static class Gen implements Generator<String> {
        String s[] = ("Anti Tracks is a complete solution to protect your " +
                "privacy and enhance your PC performance. ").split(" ");
        private int i;
        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        QueueBehavior.test(new LinkedList<String>(), new Gen());
        QueueBehavior.test(new PriorityQueue<String>(), new Gen());
        QueueBehavior.test(new ArrayBlockingQueue<String>(count), new Gen());
        QueueBehavior.test(new ConcurrentLinkedQueue<String>(), new Gen());
        QueueBehavior.test(new LinkedBlockingQueue<String>(), new Gen());
        QueueBehavior.test(new PriorityBlockingQueue<String>(), new Gen());
    }
}
