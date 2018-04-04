package com.sjh.thinkinginjava.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirFile implements FilenameFilter {
    private Pattern pattern;

    public DirFile(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
