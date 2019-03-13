package com.hanadal.dooson.hanadal.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.main.MainActivity;
import com.hanadal.dooson.hanadal.util.UtilClass;

import mehdi.sakout.fancybuttons.FancyButton;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Moon;
    private FancyButton LoginBtn;
    private TextView title;
    private TextView subTitle;
    private ImageView star;
    private SignDialog sd;

    private Boolean isAni = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.sub_title);
        star = findViewById(R.id.star);
        Moon = findViewById(R.id.moon);
        LoginBtn = findViewById(R.id.login_button);

        playAnimation();

        new Handler().postDelayed(() ->{
            if(UtilClass.getToken(getApplicationContext()).isEmpty()){
                LoginBtn.setVisibility(View.VISIBLE);
                LoginBtn.setOnClickListener(StartActivity.this);
            } else{
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2500);
    }

    @Override
    public void onClick(View v) {
        sd = new SignDialog(this);
        sd.setCancelable(false);
        sd.show(getFragmentManager(), "Facebook Login");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(!isAni) {
            TranslateAnimation anim = new TranslateAnimation
                    (0,   // fromXDelta
                            0,  // toXDelta
                            Moon.getY(),    // fromYDelta
                            0);// toYDelta
            anim.setDuration(2500);
            Moon.setAnimation(anim);

            isAni = true;
        }
    }

    private void playAnimation(){
        AnimationSet set1 = new AnimationSet(true);

        Animation anim2 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.alpha_anim);

        star.startAnimation(anim2);

        Animation anim3 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.alpha_anim_two);

        title.setAnimation(anim3);
        subTitle.setAnimation(anim3);

        set1.addAnimation(anim3);
        set1.startNow();
    }
}
