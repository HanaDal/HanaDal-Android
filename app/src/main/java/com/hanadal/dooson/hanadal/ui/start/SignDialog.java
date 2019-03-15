package com.hanadal.dooson.hanadal.ui.start;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.JsonObject;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.ui.main.MainActivity;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

@SuppressLint("ValidFragment")
public class SignDialog extends DialogFragment {

    private WebView web;
    private ProgressDialog progressDialog;
    private OkHttpClient app = new OkHttpClient();
    private Activity activity;

    private long backKeyPressedTime = 0;

    public SignDialog(Activity activity){
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                    backKeyPressedTime = System.currentTimeMillis();
                    UtilClass.Toast(getContext(), "한번 더 누르면\n로그인 과정을 취소합니다.");
                    return;
                }
                if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                    dismiss();
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sign, container, false);
        web = view.findViewById(R.id.web);

        UtilClass.loadProgress(getActivity());
        loadFacebook();
        requestLogin();

        return view;
    }

    private void requestLogin(){
        web.setWebViewClient(new WebViewClient() {
            @Nullable
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if(request.getUrl().getPath().startsWith("/api/user/oauth")){
                    Request okRequest = new Request.Builder()
                            .url(request.getUrl().toString())
                            .get()
                            .build();

                    app.newCall(okRequest).enqueue(tokenCallback);

                    return true;
                }
                return false;
            }

            private okhttp3.Callback tokenCallback = new okhttp3.Callback() {
                @Override
                public void onFailure(@NonNull okhttp3.Call call, IOException e) {
                    Log.e("Facebook Token", e.getMessage());
                }

                @Override
                public void onResponse(@NonNull okhttp3.Call call, Response response) {
                    Log.e("Facebook Token", String.valueOf(response.code()));
                    if(response.code() == 200) {
                        UtilClass.saveToken(getContext(), response.header("authentication"));
                        startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
                        activity.finish();
                    }
                }
            };
        });
    }

    private void loadFacebook(){
        Connector.api.facebookLogin().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull retrofit2.Response<JsonObject> response) {
                web.loadUrl(response.headers().get("Location"));
                UtilClass.cancelProgress();
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                UtilClass.Toast(getContext(), "실패");
                dismiss();
            }
        });
    }
}
