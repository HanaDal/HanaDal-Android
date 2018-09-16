package com.hanadal.dooson.hanadal.ui.make_challenge;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.hanadal.dooson.hanadal.R;

public class MakeChallengeActivity extends AppCompatActivity
        implements View.OnClickListener, RadioButton.OnCheckedChangeListener{

    TextInputEditText editChallengeName;
    TextInputEditText editChallengeInfo;
    RadioButton radioPublic;
    RadioButton radioNotPublic;
    RadioButton radioPerfect;
    RadioButton radioNotPerfect;
    Button btnStartChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_challenge);

        editChallengeName = findViewById(R.id.edit_challenge_name);
        editChallengeInfo = findViewById(R.id.edit_challenge_info);
        radioPublic = findViewById(R.id.radio_public);
        radioNotPublic = findViewById(R.id.radio_not_public);
        radioPerfect = findViewById(R.id.radio_perfect);
        radioNotPerfect = findViewById(R.id.radio_not_perfect);
        btnStartChallenge = findViewById(R.id.btn_start_challenge);

        btnStartChallenge.setOnClickListener(this);
        radioPublic.setOnCheckedChangeListener(this);
        radioNotPublic.setOnCheckedChangeListener(this);
        radioPerfect.setOnCheckedChangeListener(this);
        radioNotPerfect.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {

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
