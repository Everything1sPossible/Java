package com.sjh.thinkinginjava.generic;

public class LinkedStack<T> {
    private static class Node<T> {
        T item;//存储的元素
        Node<T> next;//下一元素

        public Node() {
            this.item = null;
            this.next = null;
        }

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
        boolean end() {
            return item == null && next == null;
        }
    }
    private Node<T> top = new Node<T>(); //最后一个元素
    public void push(T item) {
        top = new Node<T>(item, top);//每加一个新元素就把之前的顶部元素推下去
    }
    public T pop() {
        T value = top.item;//直接弹出顶部元素
        if (!top.end()) { //如果顶部元素不是最后一个元素
            top = top.next; //刷新顶部元素
        }
        return value;
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<String>();
        linkedStack.push("a");
        linkedStack.push("b");
        linkedStack.push("c");
        String s;
        while ((s = linkedStack.pop()) != null) {
            System.out.println(s);
        }
    }
}
