package com.datechnologies.androidtest.api;

import com.google.gson.annotations.SerializedName;

public class Feedback {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
