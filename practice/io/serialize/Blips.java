package com.sjh.thinkinginjava.io.serialize;

import java.io.*;

public class Blips {
    public static void main(String[] args) throws Exception {
        System.out.println("Constructing objects");
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        out.writeObject(blip1);
        out.writeObject(blip2);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("Recovering blip1:");
        blip1 = (Blip1)in.readObject();
        System.out.println(blip1);
//        System.out.println("Recovering blip2:");
//        blip2 = (Blip2)in.readObject();
    }
}
