package com.if5a.cure_companion.utilitis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilityPasien {
    private static final String BASE_URL ="https://bel4jarweb.000webhostapp.com/pab2/rekom/index.php/PatientControll/";

    public static Retrofit mRetrofit;

    public static Retrofit getmRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();
        }
        return mRetrofit;
    }
}
