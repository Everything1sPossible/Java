package com.sjh.thinkinginjava.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class GetChannel {
    private static final int SIZE = 1024;
    public static void main(String[] args) throws Exception {
        FileChannel fileChannel = new FileOutputStream("D:\\JavaIO\\NioText.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("hello world!".getBytes("UTF-16BE")));
        fileChannel.close();

        fileChannel = new RandomAccessFile("D:\\JavaIO\\NioText.txt", "rw").getChannel();
        fileChannel.position(fileChannel.size());//移动到最后
        fileChannel.write(ByteBuffer.wrap("hello world2!".getBytes("UTF-16BE")));
        fileChannel.close();

        fileChannel = new FileInputStream("D:\\JavaIO\\NioText.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(SIZE);
        fileChannel.read(buff);
        buff.flip(); //写模式切换到读模式
//        buff.clear();  //重置缓冲区
//        buff.compact();//只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
//        while (buff.hasRemaining()) {
//            System.out.println((char)buff.get());
//        }

        //从缓冲器输出时解码
        String encoding = System.getProperty("file.encoding");
//        System.out.println(encoding);
//        System.out.println(Charset.forName(encoding).decode(buff));

        System.out.println(buff.asCharBuffer());//此处如果在通道写时编码为UTF-16BE时,缓冲器输出不会乱码
    }
}
