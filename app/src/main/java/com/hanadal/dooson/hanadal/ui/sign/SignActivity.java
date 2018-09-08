package com.hanadal.dooson.hanadal.ui.sign;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.databinding.ActivitySignBinding;
import com.hanadal.dooson.hanadal.ui.main.MainActivity;

public class SignActivity extends AppCompatActivity {
    ActivitySignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign);
        binding.kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        binding.faceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}