package com.sjh.thinkinginjava.innerclasses;

public class Parcel5 {
    public Destination destination(String s) {
        /**
         * 4.1.定义在方法中的内部类(局部内部类)
         */
        class PDestination implements Destination {
            private String Label;
            public PDestination(String s) {
                this.Label = s;
            }

            public String readLabel() {
                return Label;
            }
        }
        return new PDestination(s);
    }
    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("hello");
        System.out.println(d.readLabel());
    }
}
