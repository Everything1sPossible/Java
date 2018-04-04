package com.sjh.thinkinginjava.io.nio;

import java.nio.*;

public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        byteBuffer.rewind();
        System.out.println("Byte Buffer ");
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.position() + " -> " + byteBuffer.get() + " ");
        }
        System.out.println();
        CharBuffer charBuffer = ((ByteBuffer)byteBuffer.rewind()).asCharBuffer();
        System.out.println("Char Buffer ");
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.position() + " -> " + charBuffer.get() + " ");
        }
        System.out.println();
        FloatBuffer floatBuffer = ((ByteBuffer)byteBuffer.rewind()).asFloatBuffer();
        System.out.println("Float Buffer ");
        while (floatBuffer.hasRemaining()) {
            System.out.print(floatBuffer.position() + " -> " + floatBuffer.get() + " ");
        }
        System.out.println();
        IntBuffer intBuffer = ((ByteBuffer)byteBuffer.rewind()).asIntBuffer();
        System.out.println("Int Buffer ");
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.position() + " -> " + intBuffer.get() + " ");
        }
        System.out.println();
        LongBuffer longBuffer = ((ByteBuffer)byteBuffer.rewind()).asLongBuffer();
        System.out.println("Long Buffer ");
        while (longBuffer.hasRemaining()) {
            System.out.print(longBuffer.position() + " -> " + longBuffer.get() + " ");
        }
        System.out.println();
        ShortBuffer shortBuffer = ((ByteBuffer)byteBuffer.rewind()).asShortBuffer();
        System.out.println("Short Buffer ");
        while (shortBuffer.hasRemaining()) {
            System.out.print(shortBuffer.position() + " -> " + shortBuffer.get() + " ");
        }
        System.out.println();
        DoubleBuffer doubleBuffer = ((ByteBuffer)byteBuffer.rewind()).asDoubleBuffer();
        System.out.println("Double Buffer ");
        while (doubleBuffer.hasRemaining()) {
            System.out.print(doubleBuffer.position() + " -> " + doubleBuffer.get() + " ");
        }
    }
}
