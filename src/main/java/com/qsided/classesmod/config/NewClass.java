package com.qsided.classesmod.config;

public class NewClass {

    private int id;
    private String name;
    private Double xpBoost;

    public NewClass(int id, String name, Double xpBoost) {
        this.id = id;
        this.name = name;
        this.xpBoost = xpBoost;
    }

    public NewClass(int id, String name) {
        this(id, name, null);
    }

}