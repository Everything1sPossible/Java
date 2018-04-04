package com.sjh.thinkinginjava.io.serialize;

import java.io.*;

public class Blip3 implements Externalizable{
    private int i;
    private String s;
    public Blip3() {
        System.out.println("Blip3 Constructor");
    }
    public Blip3(int i, String s) {
        System.out.println("Blip3(String x, int a)");
        this.i = i;
        this.s = s;
    }
    @Override
    public String toString() {
        return s + i;
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        s = (String) in.readObject();
        i = in.readInt();
    }
    public static void main(String[] args) throws Exception{
        Blip3 blip3 = new Blip3(47, "aa");
        System.out.println("序列化前::" + blip3);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        out.writeObject(blip3);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("Recovering blip3:");
        blip3 = (Blip3)in.readObject();
        System.out.println("序列化后::" + blip3);
    }
}
