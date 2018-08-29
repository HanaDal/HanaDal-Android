package com.hanadal.dooson.hanadal.connect;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hanadal.dooson.hanadal.util.UtilClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class Res<T> implements Callback<T> {
    private Context context;

    public Res(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        callback(response.code(), response.body());
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
        UtilClass.Toast(context, "네트워크 연결이 필요합니다.");
    }

    public abstract void callback(int code, T body);
}