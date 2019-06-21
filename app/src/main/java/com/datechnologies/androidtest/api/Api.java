package com.datechnologies.androidtest.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://dev.datechnologies.co/Tests/scripts/chat_log.php/";

    @GET(".")
    Call <ChatLogMessageModel> getData();


}
