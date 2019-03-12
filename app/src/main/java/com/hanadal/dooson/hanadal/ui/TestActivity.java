package com.hanadal.dooson.hanadal.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.databinding.ActivityTestBinding;
import com.hanadal.dooson.hanadal.util.UtilClass;


public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setActivity(this);

        UtilClass.Toast(getApplicationContext(), "Test");
    }

    public void onButtonClick(View view){
        UtilClass.Toast(getApplicationContext(), "Test");
    }
}