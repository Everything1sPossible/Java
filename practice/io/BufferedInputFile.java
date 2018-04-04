package com.sjh.thinkinginjava.io;

import java.io.*;

public class BufferedInputFile {
    public static String read(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            sb.append(s + "\n");
        }
        br.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(read("D:\\JavaIO\\Directory.java"));
        DataInputStream dataInputStream = new DataInputStream(
                new BufferedInputStream(new FileInputStream("D:\\JavaIO\\Directory.java")));
        while (dataInputStream.available() != 0) {
            System.out.println((char)dataInputStream.readByte());
        }
    }
}
