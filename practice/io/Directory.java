package com.sjh.thinkinginjava.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }
    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }
    public static class TreeInfo implements Iterable<File> {
        public List<File> dirs = new ArrayList<File>();
        public List<File> files = new ArrayList<File>();

        public Iterator iterator() {
            return files.iterator();
        }
        void addAll(TreeInfo treeInfo) {
            dirs.addAll(treeInfo.dirs);
            files.addAll(treeInfo.files);
        }

        @Override
        public String toString() {
            return "dirs=" + dirs +
                    "\nfiles=" + files +
                    '}';
        }
    }
    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }
    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }
    public static TreeInfo walk(File start) {
        return recurseDirs(start, ".*");
    }
    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }
    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo treeInfo = new TreeInfo();
        for(File item : startDir.listFiles()) {
            if(item.isDirectory()) {
                treeInfo.dirs.add(item);
                treeInfo.addAll(recurseDirs(item, regex));
            } else {
                if (item.getName().matches(regex)) {
                    treeInfo.files.add(item);
                }
            }
        }
        return treeInfo;
    }

    public static void main(String[] args) {
        for(File file : walk("c:\\mysql")) {
            System.out.println(file.getName());
        }
        System.out.println(walk("c:\\mysql"));
    }
}
