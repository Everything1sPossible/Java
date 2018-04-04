package com.sjh.thinkinginjava.io.serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FreezeAlien {
    public static void main(String[] args) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("X.file"));
        Alien alien = new Alien();
        out.writeObject(alien);
        out.close();
    }
}
