package com.datechnologies.androidtest.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserClient {

    @FormUrlEncoded
    @POST("feedback")
    Call<Feedback> sendLogin(
            @Field("email") String email,
            @Field("password") String password

    );

}
