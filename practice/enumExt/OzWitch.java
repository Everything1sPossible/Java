package com.sjh.thinkinginjava.enumExt;

public enum OzWitch {

    WEST("WEST 西"),
    NORTH("NORTH 北"),
    EAST("EAST 东"),
    SOUTH("SOUTH 南");

    private String description;
    private OzWitch(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for(OzWitch ozWitch : OzWitch.values()) {
            System.out.println(ozWitch + ": " + ozWitch.getDescription());
        }
    }
}
