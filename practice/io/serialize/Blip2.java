package com.sjh.thinkinginjava.io.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Blip2 implements Externalizable {
    Blip2() {
        System.out.println("Blip2 Constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}
