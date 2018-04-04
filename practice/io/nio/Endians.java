package com.sjh.thinkinginjava.io.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Endians {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[12]);
        byteBuffer.asCharBuffer().put("uvwxyz");
        System.out.println(Arrays.toString(byteBuffer.array()));
        byteBuffer.rewind();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.asCharBuffer().put("uvwxyz");
        System.out.println(Arrays.toString(byteBuffer.array()));
        byteBuffer.rewind();
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.asCharBuffer().put("uvwxyz");
        System.out.println(Arrays.toString(byteBuffer.array()));
    }
}
