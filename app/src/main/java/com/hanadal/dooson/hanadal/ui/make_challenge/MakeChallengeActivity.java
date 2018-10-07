package com.hanadal.dooson.hanadal.ui.make_challenge;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.util.UtilClass;

import okhttp3.internal.Util;

public class MakeChallengeActivity extends AppCompatActivity
        implements View.OnClickListener, RadioButton.OnCheckedChangeListener{

    TextInputEditText editChallengeName;
    TextInputEditText editChallengeInfo;
    EditText editTag;
    RadioButton radioPublic;
    RadioButton radioNotPublic;
    RadioButton radioPerfect;
    RadioButton radioNotPerfect;
    Button btnStartChallenge;
    ImageView btnBack;

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViOWFlNmJjMzY1ZjBlMTNhODVhMTQ0YSIsImlhdCI6MTUzODAyNTcwMSwiZXhwIjoxNTQwNjE3NzAxLCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.9Ar-ElYJYpe_h9jet6TP3egDmr7vSpwuaz8mh-rr5Nc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_challenge);

        editChallengeName = findViewById(R.id.edit_challenge_name);
        editChallengeInfo = findViewById(R.id.edit_challenge_info);
        editTag = findViewById(R.id.tag_edit);
        radioPublic = findViewById(R.id.radio_public);
        radioNotPublic = findViewById(R.id.radio_not_public);
        radioPerfect = findViewById(R.id.radio_perfect);
        radioNotPerfect = findViewById(R.id.radio_not_perfect);
        btnStartChallenge = findViewById(R.id.btn_start_challenge);
        btnBack = findViewById(R.id.btn_back);

        btnStartChallenge.setOnClickListener(this);
        radioPublic.setOnCheckedChangeListener(this);
        radioNotPublic.setOnCheckedChangeListener(this);
        radioPerfect.setOnCheckedChangeListener(this);
        radioNotPerfect.setOnCheckedChangeListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_challenge:{
                Connector.api.makeMyChallenge(
                        token,
                        editChallengeName.getText().toString(),
                        editChallengeInfo.getText().toString(),
                        radioPublic.isChecked(),
                        radioPerfect.isChecked(),
                        editTag.getText().toString()).enqueue(new Res<Gson>(getApplicationContext()) {
                    @Override
                    public void callback(int code, Gson body) {
                        if(code == 201){
                            UtilClass.Toast(getApplicationContext(), "도전 추가 되었습니다.");
                            finish();
                        }
                    }
                });
                break;
            }
            case R.id.btn_back:{
                finish();
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.radio_public :{
                if(isChecked) radioNotPublic.setChecked(false);
                else radioNotPublic.setChecked(true);
                break;
            }
            case R.id.radio_not_public :{
                if(isChecked) radioPublic.setChecked(false);
                else radioPublic.setChecked(true);
                break;
            }
            case R.id.radio_perfect :{
                if(isChecked) radioNotPerfect.setChecked(false);
                else radioNotPerfect.setChecked(true);
                break;
            }
            case R.id.radio_not_perfect :{
                if(isChecked) radioPerfect.setChecked(false);
                else radioPerfect.setChecked(true);
                break;
            }
        }
    }
}
