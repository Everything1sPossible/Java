package com.sjh.thinkinginjava.handleobjectExt;

import java.util.PriorityQueue;

public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem>{
        private char primary;
        private int secondary;
        private String item;
        public ToDoItem(char primary, int secondary, String item) {
            this.primary = primary;    //主优先级
            this.secondary = secondary; //次优先级
            this.item = item;          //元素
        }
        //优先级比较
        public int compareTo(ToDoItem o) {
            if(primary > o.primary) {
                return 1;
            }
            if (primary == o.primary) {
                if(secondary > o.secondary) {
                    return 1;
                }
                else if(secondary == o.secondary) {
                    return 0;
                }
            }
            return -1;
        }
        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }
    }
    public void add (String item, char primary, int secondary) {
        super.add(new ToDoItem(primary, secondary, item));
    }
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add("Empty trash", 'C', 4);
        toDoList.add("Feed dog", 'A', 2);
        toDoList.add("Feed bird", 'B', 7);
        toDoList.add("Mow lawn", 'C', 3);
        toDoList.add("Water lawn", 'A', 1);
        toDoList.add("Feed cat", 'B', 1);
        while (!toDoList.isEmpty()) {
            System.out.println(toDoList.remove());
        }
    }
}
