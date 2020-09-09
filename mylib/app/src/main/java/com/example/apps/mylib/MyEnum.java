package com.example.apps.mylib;

public enum MyEnum {
    FIRST("this is the first enum value"),
    SECOND("this is the second enum value"),
    THIRD("this is the third enum value");

    private final String field;

    MyEnum(String s) {
        field = s;
    }

    public String getMessage() {
        return field;
    }
}
