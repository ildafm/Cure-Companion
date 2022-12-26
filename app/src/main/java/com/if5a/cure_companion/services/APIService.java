package com.if5a.cure_companion.services;

import com.if5a.cure_companion.models.ValueNoData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("loginAdmin")
    @FormUrlEncoded
    Call<ValueNoData> login(@Field("key") String key,
                            @Field("email") String email,
                            @Field("password") String password);
}
