package com.sjh.thinkinginjava.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFile {
    public static byte[] read(File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                //available()方法可以查看有多少可以存取的字符
                byte[] data = new byte[bufferedInputStream.available()];
                bufferedInputStream.read(data);
                return data;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] read(String filePath) {
        return read(new File(filePath));
    }
}
