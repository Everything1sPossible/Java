package com.sjh.thinkinginjava.io.nio;

import java.nio.ByteBuffer;

public class GetData {
    private static final int SIZE = 1024;

    public static void main(String[] args) {
        //创建大小SIZE的ByteBuffer对象
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
        int i = 0;
        while (i++ < byteBuffer.limit()) {
            if (byteBuffer.get() != 0) {
                System.out.println("nonzero");
            }
        }
        System.out.println("i = " + i);
        //将缓冲器重置
        byteBuffer.rewind();
        byteBuffer.asCharBuffer().put("Howdy!");//char
        char c;
        while ((c = byteBuffer.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println();
        byteBuffer.rewind();
        byteBuffer.asShortBuffer().put((short)471142);//short
        System.out.println(byteBuffer.getShort());
        byteBuffer.rewind();
        byteBuffer.asIntBuffer().put(99471142);//int
        System.out.println(byteBuffer.getInt());
        byteBuffer.rewind();
        byteBuffer.asLongBuffer().put(99471142);//long
        System.out.println(byteBuffer.getLong());
        byteBuffer.rewind();
        byteBuffer.asFloatBuffer().put(99471142);//float
        System.out.println(byteBuffer.getFloat());
        byteBuffer.rewind();
        byteBuffer.asDoubleBuffer().put(99471142);//double
        System.out.println(byteBuffer.getDouble());
    }
}
