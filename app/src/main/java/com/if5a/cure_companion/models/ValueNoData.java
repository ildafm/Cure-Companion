package com.if5a.cure_companion.models;

public class ValueNoData {
    // penamaan variabel harus sama dengan yang ada di api
    private int success;
    private String message;

    //tidak perlu setter karena kita tidak perlu set data
    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
