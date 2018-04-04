package com.sjh.thinkinginjava.io;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        File file = new File("c:/");
        String[] list;
        list = file.list(new DirFile("[0-9a-zA-Z]*"));
//        list = file.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
