package com.sjh.thinkinginjava.innerclasses;

public class Parcel8 {
    public String dest3 = "dest3";
    public Destination destination(final String dest) {
        /**
         * 4.5.一个匿名类,它执行字段初始化
         */
        final String dest2 = "world";
        return new Destination() {
            private String Label = dest;
            private String Label2 = dest3;
            public String readLabel() {
                return Label + Label2;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Destination d = p.destination("hello");
        System.out.println(d.readLabel());
    }
}
