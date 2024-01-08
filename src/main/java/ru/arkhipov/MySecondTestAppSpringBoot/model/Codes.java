package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;
public enum Codes {


    SUCCESS("success"),

    FAILED("failed"),
    UNSUPPORTED("failed");

    private final String name;

    Codes(String name){
        this.name = name;
    }

    @JsonValue
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
