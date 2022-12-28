package com.if5a.cure_companion.services;

import com.if5a.cure_companion.models.ValueNoDataPasien;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServicePasien {
    @FormUrlEncoded
    @POST("loginPatient")
    Call<ValueNoDataPasien> login(@Field("key") String key,
                                  @Field("email") String email,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("registerPatient")
    Call<ValueNoDataPasien> register(@Field("key") String key,
                               @Field("username") String username,
                               @Field("email") String email,
                               @Field("phone_number") String phone_number,
                               @Field("password") String password);
}
