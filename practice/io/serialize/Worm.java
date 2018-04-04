package com.sjh.thinkinginjava.io.serialize;

import java.io.*;
import java.util.Random;

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private Worm next;
    private char c;

    public Worm(int i, char x) {
        System.out.println("Worm constructor" + i);
        c = x;
        if(--i > 0) {
            next = new Worm(i, (char)(x + 1));
        }
    }

    public Worm() {
        System.out.println("Default Worm");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(":");
        sb.append(c);
        sb.append("(");
        for(Data dat : d) {
            sb.append(dat);
        }
        sb.append(")");
        if(next != null) {
            sb.append(next);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Worm worm = new Worm(6, 'a');
        System.out.println("worm = " + worm);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\JavaIO\\text.txt"));
//        out.writeObject("Worm storage\n");
        out.writeObject(worm);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\JavaIO\\text.txt"));
//        String s = (String)in.readObject();
        Worm worm1 = (Worm)in.readObject();
        System.out.println("Worm1 = " + worm1);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
//        out2.writeObject("Worm storage\n");
        out2.writeObject(worm);
        out2.flush();
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
//        s = (String)in2.readObject();
        Worm worm2 = (Worm)in2.readObject();
        System.out.println("Worm2 = " + worm2);
    }
}
