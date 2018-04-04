package com.sjh.thinkinginjava.io.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
        Iterator<String> iterator = sortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            String csName = iterator.next();
            System.out.print(csName);
            Iterator aliases = sortedMap.get(csName).aliases().iterator();
            if(aliases.hasNext()) {
                System.out.print(": ");
            }
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if(aliases.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
