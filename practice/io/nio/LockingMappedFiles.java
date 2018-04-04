package com.sjh.thinkinginjava.io.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockingMappedFiles {
    static final int LENGTH = 0x8FFFFF;
    static FileChannel fc;

    public static void main(String[] args) throws Exception {
        fc = new RandomAccessFile("D:\\JavaIO\\text.txt", "rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            out.put((byte)'x');
        }
        new LockAndModify(out, 0, 0 + LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
    }
    private static class LockAndModify extends Thread {
        private ByteBuffer buff;
        private int start, end;

        public LockAndModify(ByteBuffer mbb, int start, int end) {
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            buff = mbb.slice();
            start();
        }

        @Override
        public void run() {
            try {
                //加锁,加锁的区域为size-position,第三个参数指定是否是共享锁
                FileLock fl = fc.lock(start, end, false);
                System.out.println("Locked : " + start + " to " +end);
                while (buff.position() < buff.limit() - 1) {
                    buff.put((byte)(buff.get() + 1));
                }
                fl.release();//释放锁
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
