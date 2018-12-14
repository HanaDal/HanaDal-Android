package com.hanadal.dooson.hanadal.ui.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.databinding.ActivitySplashBinding;
import com.hanadal.dooson.hanadal.ui.main.MainActivity;
import com.hanadal.dooson.hanadal.ui.sign.SignActivity;

public class SplashActivity extends AppCompatActivity{
    ActivitySplashBinding binding;
    boolean ani = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        AnimationSet set1 = new AnimationSet(true);

        Animation anim2 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.alpha_anim);

        binding.star.startAnimation(anim2);

        Animation anim3 = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.alpha_anim_two);

        binding.title.setAnimation(anim3);
        binding.subTitle.setAnimation(anim3);

        set1.addAnimation(anim3);
        set1.startNow();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, 3000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(!ani) {
            TranslateAnimation anim = new TranslateAnimation
                    (0,   // fromXDelta
                            0,  // toXDelta
                            binding.moon.getY(),    // fromYDelta
                            0);// toYDelta
            anim.setDuration(2500);
            binding.moon.setAnimation(anim);
            ani = true;
        }
    }

    public void onBackPressed() { }
}
