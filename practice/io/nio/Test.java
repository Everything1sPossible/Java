package com.sjh.thinkinginjava.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Test {
    private static final int SIZE = 0x8FFFFF;
    public static void main(String[] args) throws Exception {
        FileChannel channel = new RandomAccessFile("D:\\JavaIO\\text2.txt", "rw").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        byte[] bytes = new byte[0];
        while (channel.read(buffer) != -1) {
            bytes = new byte[buffer.position()];
            buffer.flip();//写模式切换到读模式
            buffer.get(bytes);
            buffer.clear();
        }
//        CharBuffer charBuffer = ((ByteBuffer)buffer.rewind()).asCharBuffer();
//        System.out.println("Char Buffer ");
//        while (charBuffer.hasRemaining()) {
//            System.out.print(charBuffer.position() + " -> " + charBuffer.get() + " ");
//        }
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));
    }
}
