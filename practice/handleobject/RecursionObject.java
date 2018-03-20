package com.sjh.thinkinginjava.handleobject;

import java.util.*;

public class RecursionObject {
    public static void main(String[] args) {
        List list1 = new ArrayList();//最外层List
        List list2 = new ArrayList();//第二层List
        List<String> list3 = new ArrayList();//最内层List
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        list3.add("3a");
        list3.add("3b");
        list3.add("3c");
        list2.add("2a");
        list2.add("2b");
        list2.add("2c");
        list2.add(list3);
        list2.add("2d");
        list1.add("1a");
        list1.add(map);
        list1.add(list2);
        map.put("key4List2", list2);
        list1.add("1b");
        list1.add("1c");
        RecursionObject rl = new RecursionObject();
        String str = rl.recursionObject(list1);
        System.out.println(str);
    }

    /**
     * 解析对象结构,主要用于解析List,Map
     * @param object
     * @return
     */
    public String recursionObject(Object object) {
        StringBuilder sb = new StringBuilder();
        if (object instanceof List) {
            recursionList((List)object, sb);
        }
        else if (object instanceof Map) {
            recursionMap((Map)object, sb);
        }
        sb.deleteCharAt(sb.length() - 1);//删除最后一个","
        return sb.toString();
    }

    /**
     * 递归解析List
     * @param list
     * @param sb
     */
    public void recursionList(List list, StringBuilder sb) {
        sb.append("[");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if(obj instanceof List) {
                recursionList((List)obj, sb);
            }
            else if (obj instanceof Map) {
                recursionMap((Map)obj, sb);
            }
            else {
                sb.append(obj);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1); //删除上一层递归遗留的","
        sb.append("],");
    }

    /**
     * 递归解析Map
     * @param map
     * @param sb
     */
    public void recursionMap(Map map, StringBuilder sb) {
        sb.append("{");
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            sb.append(entry.getKey());
            sb.append("=");
            if(entry.getValue() instanceof List) {
                recursionList((List)entry.getValue(), sb);
            }
            else if (entry.getValue() instanceof Map) {
                recursionMap((Map)entry.getValue(), sb);
            }
            else {
                sb.append(entry.getValue());
            }
            sb.append(",");
        }
        sb.delete(sb.length() - 2, sb.length());//删除上一层递归遗留的",,"
        sb.append("},");
    }
}
