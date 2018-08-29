package com.hanadal.dooson.hanadal.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hanadal.dooson.hanadal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFrame);

        if(mainFragment == null){
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, mainFragment).commit();
        }

        MainPresenter mainPresenter = new MainPresenter();
    }
}