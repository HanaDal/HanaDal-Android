package com.hanadal.dooson.hanadal.ui.make_qna;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.util.UtilClass;

//Todo("id 값들 바꾸기")
public class MakeQnaActivity extends AppCompatActivity
        implements View.OnClickListener{

    TextInputEditText editChallengeName;
    TextInputEditText editChallengeInfo;
    EditText editTag;
    Button btnStartChallenge;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_qna);

        editChallengeName = findViewById(R.id.edit_challenge_name);
        editChallengeInfo = findViewById(R.id.edit_challenge_info);
        editTag = findViewById(R.id.tag_edit);
        btnStartChallenge = findViewById(R.id.btn_start_challenge);
        btnBack = findViewById(R.id.btn_back);

        btnStartChallenge.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_challenge:{
                Connector.api.writeQnA(
                        UtilClass.getToken(getApplicationContext()),
                        editChallengeName.getText().toString(),
                        editTag.getText().toString(),
                        editChallengeInfo.getText().toString()).enqueue(new Res<Gson>(getApplicationContext()) {
                    @Override
                    public void callback(int code, Gson body) {
                        if(code == 201){
                            UtilClass.Toast(getApplicationContext(), "QnA 가 등록 되었습니다.");
                            finish();
                        }
                    }
                });
                UtilClass.loadProgress(this);
                break;
            }
            case R.id.btn_back:{
                finish();
                break;
            }
        }
    }
}
