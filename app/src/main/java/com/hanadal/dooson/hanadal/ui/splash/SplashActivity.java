package com.hanadal.dooson.hanadal.ui.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hanadal.dooson.hanadal.R;

import static com.hanadal.dooson.hanadal.util.UtilClass.*;

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast(getApplicationContext(), "Test");
    }
}
