package com.hanadal.dooson.hanadal.ui.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hanadal.dooson.hanadal.R;

public class SignActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("개꿀입니다.", "^오^;");
                // App code
            }

            @Override
            public void onCancel() {
                Log.e("꺼집니다.", "^오^;");
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("부끄러운줄알아야지.", "^오^;");
                // App code
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e("개꿀입니다!", "^오^;");
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        Log.e("꺼집니다!", "^오^;");
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("부끄러운줄알아야지!", exception.toString());
                        // App code
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}