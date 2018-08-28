package com.hanadal.dooson.hanadal.connect;

import okhttp3.OkHttpClient;
import retrofit2.*;
import retrofit2.converter.gson.*;

public final class Connector {
    public static API api;

    static {
        //String TEST_URL = "https://api.github.com/";
        String URL = "URL HERE";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .baseUrl(URL)
                //.baseUrl(TEST_URL)
                .build();

        api = retrofit.create(API.class);
    }
}
