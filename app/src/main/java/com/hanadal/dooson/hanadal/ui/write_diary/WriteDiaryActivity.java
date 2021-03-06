package com.hanadal.dooson.hanadal.ui.write_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.Objects;

public class WriteDiaryActivity extends AppCompatActivity
        implements View.OnClickListener{

    private EditText diaryTitle;
    private EditText diaryContent;
    private Button diarySend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_my_diary);

        diaryTitle = findViewById(R.id.diary_title);
        diaryContent = findViewById(R.id.diary_content);
        diarySend = findViewById(R.id.diary_send_btn);

        diarySend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        if(diaryTitle.length() > 0 && diaryContent.length() > 0) {
            Connector.api.writeDiary(
                    Objects.requireNonNull(intent.getExtras()).getString("id"),
                    String.valueOf(intent.getExtras().getInt("day")),
                    diaryTitle.getText().toString(),
                    diaryContent.getText().toString()).enqueue(new Res<Gson>(getApplicationContext()) {
                @Override
                public void callback(int code, Gson body) {
                    if (code == 200) {
                        UtilClass.Toast(getApplicationContext(), "다이어리가 작성되었습니다.");
                        finish();
                    }else{
                        Log.e("FAil", String.valueOf(code));
                    }
                }
            });
            UtilClass.loadProgress(this);
        }
    }
}
