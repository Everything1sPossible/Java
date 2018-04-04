package com.sjh.thinkinginjava.io.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {
    static int length = 0x8FFFFF;//8M

    public static void main(String[] args) throws Exception {
        MappedByteBuffer mappedByteBuffer = new RandomAccessFile("D:\\JavaIO\\text.txt", "rw").getChannel().
                map(FileChannel.MapMode.READ_WRITE, 0, length);
        for(int i = 0; i < length; i++ ) {
            mappedByteBuffer.put((byte)'x');
        }
        System.out.println("Finished writing");
        for(int i = length / 2; i < length / 2 + 6; i++) {
            System.out.print((char)mappedByteBuffer.get(i) + " ");
        }
    }
}
