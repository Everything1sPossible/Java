package com.sjh.thinkinginjava.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffers {
    public static void symmetricScramble(CharBuffer charBuffer) {
        while (charBuffer.hasRemaining()) {
            charBuffer.mark();  //将mark设置为position
            char c1 = charBuffer.get();
            char c2 = charBuffer.get();
            charBuffer.reset(); //将position设置为mark
            charBuffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] datas = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(datas.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(datas);
        System.out.print(cb.rewind());
        symmetricScramble(cb);
        System.out.println();
        System.out.print(cb.rewind());
        symmetricScramble(cb);
        System.out.println();
        System.out.print(cb.rewind());
    }
}
