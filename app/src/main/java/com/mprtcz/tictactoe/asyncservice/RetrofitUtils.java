package com.mprtcz.tictactoe.asyncservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mprtcz.tictactoe.user.endpoint.UserEndpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Azet on 2017-03-23.
 */

public class RetrofitUtils {
    private static final String BASE_URL = "http://192.168.0.7:8080";
    private Retrofit retrofit;
    private static RetrofitUtils retrofitUtils;

    private RetrofitUtils() {createRetrofitInstance();}

    private void createRetrofitInstance() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }

    public static UserEndpoint getUserEndpointRetrofit() {
        Retrofit retrofit = getInstance();
        return  retrofit.create(UserEndpoint.class);
    }

    public static Retrofit getInstance() {
        if(retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils.getRetrofit();
    }
}
