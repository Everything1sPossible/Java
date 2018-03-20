package com.sjh.thinkinginjava.innerclasses;

public class DotThisChild extends DotThis {
    public static void main(String[] args) {
        DotThisChild dtc = new DotThisChild();
        DotThisChild.Inner i = dtc.new Inner();
        i.outer();
    }
}
