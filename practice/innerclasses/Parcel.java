package com.sjh.thinkinginjava.innerclasses;

public class Parcel {
    class Contents {
        private int i = 11;
        public int value() {return i;}
    }
    class Destination {
        private String label;
        public Destination(String dest) {
            label = dest;
        }
        String readLabel() {return label;}
    }
    public Contents contents() {return new Contents();}
    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
//        Contents c = new Contents(); //编译报错
        Parcel p = new Parcel();
        p.ship("hello");
        Parcel.Contents c1 = p.contents();
        Parcel.Contents c2 = p.new Contents();
    }
}
