package com.sjh.thinkinginjava.handleobjectExt;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class Countries {
    public static final String[][] DATA = {
            {"CHINA", "china"}, {"USA", "usa"},
            {"RUSSA", "russa"}, {"UK", "UK"}
    };
    private static class FlyweightMap extends AbstractMap {
        @Override
        public Set<Entry> entrySet() {
            return null;
        }
    }
}
