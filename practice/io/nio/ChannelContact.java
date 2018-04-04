package com.sjh.thinkinginjava.io.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelContact {
    public static void main(String[] args) throws Exception {
        FileChannel channelFrom = new RandomAccessFile("D:\\JavaIO\\ChannelFrom.txt", "rw").getChannel();
        FileChannel channelTo = new RandomAccessFile("D:\\JavaIO\\ChannelTo.txt", "rw").getChannel();
        long position = 0;
        long count = channelFrom.size();
        channelTo.transferFrom(channelFrom, position, count);
//        channelFrom.transferTo(position, count, channelTo);
        ByteBuffer bf = ByteBuffer.allocate(20);
        channelTo.read(bf);
        bf.flip();
        while (bf.hasRemaining()) {
            System.out.println(bf.get());
        }
    }
}
