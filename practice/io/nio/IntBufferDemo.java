package com.sjh.thinkinginjava.io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
    private static final int SIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        System.out.println(intBuffer.get(3));
        intBuffer.put(3, 1811);
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            int i = intBuffer.get();
            System.out.print(i + " ");
        }
    }
}
