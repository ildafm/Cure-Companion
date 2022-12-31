package com.if5a.cure_companion.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilities {
    public static final String BASE_URL = "https://bel4jarweb.000webhostapp.com/pab2/rekom/";
    public static final String BASE_KEY = "rekom";
    private static Retrofit retrofit;

    //retrofit for doctor
    public static Retrofit getDoctorRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL+"index.php/DoctorControll/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    //retrofit for patient
    public static Retrofit getPatientRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL+"index.php/PatientControll/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
