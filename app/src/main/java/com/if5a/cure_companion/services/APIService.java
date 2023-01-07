package com.if5a.cure_companion.services;

import com.if5a.cure_companion.models.Schedule;
import com.if5a.cure_companion.models.ValueData;
import com.if5a.cure_companion.models.ValueNoData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("loginPatient")
    Call<ValueNoData> login(@Field("key") String key,
                            @Field("email") String email,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("registerPatient")
    Call<ValueNoData> register(@Field("key") String key,
                               @Field("username") String username,
                               @Field("email") String email,
                               @Field("phone_number") String phone_number,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("loginDoctor")
    Call<ValueNoData> loginDoctor(@Field("key") String key,
                            @Field("email") String email,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("registerDoctor")
    Call<ValueNoData> registerDoctor(@Field("key") String key,
                               @Field("username") String username,
                               @Field("email") String email,
                               @Field("phone_number") String phone_number,
                               @Field("password") String password,
                               @Field("specialist_id") String specialist_id,
                               @Field("department_id") String department_id);

    @POST("getAllDataSchedule")
    @FormUrlEncoded
    Call<ValueData<Schedule>> getAllDataSchedule(@Field("key") String key);
}
