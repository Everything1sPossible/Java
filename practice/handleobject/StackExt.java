package com.sjh.thinkinginjava.handleobject;

import java.util.LinkedList;

public class StackExt<T> {
    private LinkedList<T> statck = new LinkedList<T>();
    private int size;
    //压入栈顶
    public void push(T v) {
        statck.addFirst(v);
        size++;
    }
    //弹出栈顶元素
    public T peek() {
        return statck.getFirst();
    }
    //删除栈顶元素并弹出
     public T pop() {
        size--;
        return statck.removeFirst();
     }
     public int size() {
        return size;
     }

    public static void main(String[] args) {
        StackExt<String> stackExt = new StackExt<String>();
        stackExt.push("a");
        stackExt.push("b");
        stackExt.push("c");
        int stackSize = stackExt.size();
        for(int i = 0; i < stackSize; i++) {
            System.out.println(stackExt.pop());
        }
    }
}
