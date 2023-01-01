package com.if5a.cure_companion.services;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilities {
    private static final String PREFERENCE_FILE_KEY = "Cure-Companion";

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

    //clear user untuk logout
    public static void clearUser(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("xEmail", null);
        editor.apply();
    }

    public static void setValue(Context context, String xPref, String xValue){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(xPref, xValue);
        editor.commit();
    }

    public static String getValue(Context context, String xPref){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue;
    }

    public static boolean checkValue(Context context, String xPref){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue != null;
    }
}
