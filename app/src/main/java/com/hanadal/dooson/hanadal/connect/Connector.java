package com.hanadal.dooson.hanadal.connect;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Connector {
    public static API api;

    static {
        String URL = "https://hanadal-server.herokuapp.com/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .followRedirects(false)
                        .build())
                .baseUrl(URL)
                .build();

        api = retrofit.create(API.class);
    }
}
