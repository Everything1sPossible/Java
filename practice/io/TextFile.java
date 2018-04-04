package com.sjh.thinkinginjava.io;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFile extends ArrayList<String> {
    public TextFile(String filePath, String splitter) {
        super(Arrays.asList(read(filePath).split(splitter)));
        if(get(0).equals("")) {
            remove(0);
        }
    }

    public TextFile(String filePath) {
        this(filePath, "\n");
    }

    public static String read(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(filePath).getAbsoluteFile()));
            try{
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public static void write(String filePath, String text) {
        try {
            PrintWriter pw = new PrintWriter(new File(filePath).getAbsoluteFile());
            try {
                pw.print(text);
            } finally {
                pw.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new File(filePath).getAbsoluteFile());
            try {
                for(String item : this) {
                    pw.println(item);
                }
            } finally {
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String file = read("D:\\JavaIO\\Directory.java");
//        write("D:\\JavaIO\\text.txt", file);
        TextFile textFile = new TextFile("D:\\JavaIO\\Directory.java");
        textFile.write("D:\\JavaIO\\text2.txt");
    }
}
