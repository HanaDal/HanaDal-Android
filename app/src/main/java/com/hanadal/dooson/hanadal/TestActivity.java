package com.hanadal.dooson.hanadal;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonArray;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.util.UtilClass;
import com.hanadal.dooson.hanadal.databinding.*;


public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setActivity(this);

        UtilClass.Toast(getApplicationContext(), "Test");

/*        Connector.api.getUserRepositories("SeungYongSon").enqueue(new Res<JsonArray>(getApplicationContext()) {

            @Override
            public void callback(int code, JsonArray body) {
                Log.d("**TEST**", String.valueOf(code));
                if(code == 200){
                    Log.d("**TEST**", body.toString());
                    binding.textTest.setText(body.toString());
                }
            }
        });*/
    }

    public void onButtonClick(View view){
        UtilClass.Toast(getApplicationContext(), "Test");
    }
}