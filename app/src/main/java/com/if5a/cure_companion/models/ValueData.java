package com.if5a.cure_companion.models;

import java.util.List;

public class ValueData<T> {
    private int success;
    private String message;
    private List<T> data; //tipe data merupakan list

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }
}
