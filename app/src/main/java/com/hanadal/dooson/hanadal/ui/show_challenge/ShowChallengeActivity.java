package com.hanadal.dooson.hanadal.ui.show_challenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.ChallengeDetail;
import com.hanadal.dooson.hanadal.data.Diary;
import com.hanadal.dooson.hanadal.util.UtilClass;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

// Todo("UI 기능 구현")
public class ShowChallengeActivity extends AppCompatActivity{

    private MarkdownView markdownView;
    private ArrayList<TextView> days = new ArrayList<>();
    private boolean isMine = false;
    private ArrayList<Diary> diaryList = new ArrayList<>();
    private int day;


    private TextView willWorkText;
    private ConstraintLayout willWorkView;
    private Button btnWriteDiary;

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViOWFlNmJjMzY1ZjBlMTNhODVhMTQ0YSIsImlhdCI6MTUzODAyNTcwMSwiZXhwIjoxNTQwNjE3NzAxLCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.9Ar-ElYJYpe_h9jet6TP3egDmr7vSpwuaz8mh-rr5Nc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_challenge);

        setDays();

        btnWriteDiary = findViewById(R.id.btn_write_diary);
        btnWriteDiary.setVisibility(View.GONE);

        willWorkText = findViewById(R.id.will_work_text);
        willWorkView = findViewById(R.id.will_work_view);

        markdownView = findViewById(R.id.md_diary);
        markdownView.addStyleSheet(new Github());

        Intent intent = getIntent();
        Connector.api.showChallenge(token, Objects.requireNonNull(intent.getExtras()).getString("id"))
                .enqueue(new Res<ChallengeDetail>(getApplicationContext()) {
            @Override
            public void callback(int code, ChallengeDetail body) {
                if(code == 200){
                    days.get(body.day).setBackground(getResources().getDrawable(R.drawable.purple_circle, null));
                    days.get(body.day).setTextColor(Color.WHITE);
                    isMine = body.isMine;
                    day = body.day;
                    diaryList = body.diary;

                    if(isMine) btnWriteDiary.setVisibility(View.VISIBLE);

                    if(diaryList.size() > 0)
                        markdownView.loadMarkdown("# " + body.diary.get(body.day).title + "\n" + "\n" + body.diary.get(body.day).content);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onDaysClick(View v){
        TextView tmp = (TextView)v;
        int chooseDay = Integer.parseInt(tmp.getText().toString())-1;

        if(chooseDay <= day) {
            for (TextView tv : days) {
                if (tv == tmp) {
                    tv.setBackground(getResources().getDrawable(R.drawable.purple_circle, null));
                    tv.setTextColor(Color.WHITE);

                    if (diaryList.size() > 0)
                        markdownView.loadMarkdown("# " + diaryList.get(day).title + "\n" + "\n" + diaryList.get(day).content);
                    else
                        markdownView.loadMarkdown("");

                    if (chooseDay == day) {
                        btnWriteDiary.setVisibility(View.VISIBLE);
                        willWorkText.setVisibility(View.VISIBLE);
                        willWorkView.setVisibility(View.VISIBLE);
                    }
                    else{
                        btnWriteDiary.setVisibility(View.GONE);
                        willWorkText.setVisibility(View.GONE);
                        willWorkView.setVisibility(View.GONE);
                    }
                } else {
                    tv.setBackground(getResources().getDrawable(R.drawable.white_circle, null));
                    tv.setTextColor(Color.argb(255, 80, 80, 80));
                }
            }
        }else{
            UtilClass.Toast(getApplicationContext(), "아직 진행되지 않은 Day 입니다.");
        }
    }

    @SuppressLint("FindViewByIdCast")
    private void setDays(){
        days.add(findViewById(R.id.day_1));
        days.add(findViewById(R.id.day_2));
        days.add(findViewById(R.id.day_3));
        days.add(findViewById(R.id.day_4));
        days.add(findViewById(R.id.day_5));
        days.add(findViewById(R.id.day_6));
        days.add(findViewById(R.id.day_7));
        days.add(findViewById(R.id.day_8));
        days.add(findViewById(R.id.day_9));
        days.add(findViewById(R.id.day_10));
        days.add(findViewById(R.id.day_11));
        days.add(findViewById(R.id.day_12));
        days.add(findViewById(R.id.day_13));
        days.add(findViewById(R.id.day_14));
        days.add(findViewById(R.id.day_15));
        days.add(findViewById(R.id.day_16));
        days.add(findViewById(R.id.day_17));
        days.add(findViewById(R.id.day_18));
        days.add(findViewById(R.id.day_19));
        days.add(findViewById(R.id.day_20));
        days.add(findViewById(R.id.day_21));
        days.add(findViewById(R.id.day_22));
        days.add(findViewById(R.id.day_23));
        days.add(findViewById(R.id.day_24));
        days.add(findViewById(R.id.day_25));
        days.add(findViewById(R.id.day_26));
        days.add(findViewById(R.id.day_27));
        days.add(findViewById(R.id.day_28));
        days.add(findViewById(R.id.day_29));
        days.add(findViewById(R.id.day_30));
    }
}
