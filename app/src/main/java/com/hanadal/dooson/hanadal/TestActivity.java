package com.hanadal.dooson.hanadal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.util.UtilClass;

public class TestActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = findViewById(R.id.text_test);

        UtilClass.Toast(getApplicationContext(), "Test");

        Connector.api.getUserRepositories("SeungYongSon").enqueue(new Res<JsonArray>(getApplicationContext()) {

            @Override
            public void callback(int code, JsonArray body) {
                Log.d("**TEST**", String.valueOf(code));
                if(code == 200){
                    Log.d("**TEST**", body.toString());
                    textView.setText(body.toString());
                }
            }
        });
    }
}