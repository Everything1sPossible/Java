package com.sjh.thinkinginjava.enumExt;

public class EnumClass {
    public static void main(String[] args) {
        for(Shrubbery shrubbery : Shrubbery.values()) {
            System.out.println(shrubbery + " ordinal: " + shrubbery.ordinal());
            System.out.println(shrubbery.compareTo(Shrubbery.CRAWLING) + "");
            System.out.println(shrubbery.equals(Shrubbery.CRAWLING) + "");
            System.out.println(shrubbery == Shrubbery.CRAWLING);
            System.out.println(shrubbery.getDeclaringClass());
            System.out.println(shrubbery.name());
            System.out.println("----------------------------------------");
        }
        for(String s : "GROUND CRAWLING HANGING".split(" ")) {
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrubbery);
        }
    }
}
