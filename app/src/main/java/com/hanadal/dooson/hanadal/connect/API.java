package com.hanadal.dooson.hanadal.connect;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    @GET("users/{user}/repos")
    Call<JsonArray> getUserRepositories(@Path("user") String userName);
}
