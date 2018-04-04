package com.sjh.thinkinginjava.io.serialize.child;

import com.sjh.thinkinginjava.io.serialize.Alien;
import com.sjh.thinkinginjava.io.serialize.AlienFather;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ThawAlien {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("X.file"));
        Alien obj = (Alien)in.readObject();
        System.out.println(obj.alien);
    }
}
