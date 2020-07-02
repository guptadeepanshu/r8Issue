package com.example.apps.mylib;

public class Api {
    public String findMessage(String key) {
        if (key == null) {
            return null;
        }else if (key.contains(MyEnum.FIRST.name())) {
            return MyEnum.FIRST.getMessage();
        } else if (key.contains(MyEnum.SECOND.name())) {
            return MyEnum.SECOND.getMessage();
        } else if (key.contains(MyEnum.THIRD.name())) {
            return MyEnum.THIRD.getMessage();
        } else {
            return "No match found";
        }
    }
}
