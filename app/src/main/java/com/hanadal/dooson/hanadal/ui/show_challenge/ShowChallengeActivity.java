package com.hanadal.dooson.hanadal.ui.show_challenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.ChallengeDetail;
import com.hanadal.dooson.hanadal.data.Diary;
import com.hanadal.dooson.hanadal.ui.write_diary.WriteDiaryActivity;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;
import java.util.Objects;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

public class ShowChallengeActivity extends AppCompatActivity {

    private boolean isMine = false;
    private ArrayList<Diary> diaryList = new ArrayList<>();
    private ArrayList<String> todoList = new ArrayList<>();
    private int day;

    private MarkdownView markdownView;
    private ArrayList<TextView> days = new ArrayList<>();
    private ArrayList<EditText> todos = new ArrayList<>();
    private Button btnWriteDiary;

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViOWFlNmJjMzY1ZjBlMTNhODVhMTQ0YSIsImlhdCI6MTUzODAyNTcwMSwiZXhwIjoxNTQwNjE3NzAxLCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.9Ar-ElYJYpe_h9jet6TP3egDmr7vSpwuaz8mh-rr5Nc";

    @Override
    protected void onStart() {
        super.onStart();

        for(TextView tv : days){
            tv.setBackground(getResources().getDrawable(R.drawable.white_circle, null));
            tv.setTextColor(Color.argb(255, 80, 80, 80));
        }

        markdownView.loadMarkdown("");

        Intent intent = getIntent();
        Connector.api.showChallenge(token, Objects.requireNonNull(intent.getExtras()).getString("id"))
                .enqueue(new Res<ChallengeDetail>(getApplicationContext()) {
                    @Override
                    public void callback(int code, ChallengeDetail body) {
                        if (code == 200) {
                            days.get(body.day).setBackground(getResources().getDrawable(R.drawable.purple_circle, null));
                            days.get(body.day).setTextColor(Color.WHITE);
                            isMine = body.isMine;
                            day = body.day;
                            diaryList = body.diary;
                            todoList = body.todo;

                            if (isMine) btnWriteDiary.setVisibility(View.VISIBLE);

                            try {
                                String[] todo = body.todo.get(day).split(", ");
                                for (int i = 0; i < todo.length; i++) {
                                    todos.get(i).setText(todo[i]);
                                    if (!isMine) todos.get(i).setEnabled(false);
                                }
                                markdownView.loadMarkdown("# " + body.diary.get(body.day).title + "\n" + body.diary.get(body.day).content);
                            } catch (Exception ignored) {
                                markdownView.loadMarkdown("");
                            }finally {
                                try{
                                    String[] todo = todoList.get(day).split(", ");
                                    for (int i = 0; i < todo.length; i++) todos.get(i).setText(todo[i]);
                                }catch (Exception e){
                                    for (int i = 0; i < todos.size(); i++) todos.get(i).setText("");
                                }
                            }
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_challenge);
        btnWriteDiary = findViewById(R.id.btn_write_diary);
        btnWriteDiary.setVisibility(View.GONE);

        markdownView = findViewById(R.id.md_diary);
        markdownView.addStyleSheet(new Github());

        setDays();
        setTodos();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write_diary: {
                Intent intent = getIntent();
                Intent i = new Intent(getApplicationContext(), WriteDiaryActivity.class);
                i.putExtra("id", Objects.requireNonNull(intent.getExtras()).getString("id"));
                i.putExtra("day", day);
                startActivity(i);
                break;
            }
            case R.id.btn_back: {
                Intent intent = getIntent();
                StringBuffer todoContent = new StringBuffer();
                for (int i = 0; i < todos.size(); i++) {
                    todoContent.append(todos.get(i).getText().toString());
                    if (i < todos.size() - 1) todoContent.append(", ");
                }

                Connector.api.writeTodo(
                        Objects.requireNonNull(intent.getExtras()).getString("id"),
                        String.valueOf(day),
                        todoContent.toString()).enqueue(new Res<Gson>(getApplicationContext()) {
                    @Override
                    public void callback(int code, Gson body) {
                        if (code == 201) UtilClass.Toast(getApplicationContext(), "ToDo 가 수정되었습니다.");
                        else UtilClass.Toast(getApplicationContext(), "ToDo 를 수정하지 못했습니다.");
                        finish();
                    }
                });
            }
        }
    }

    public void onDaysClick(View v) {
        TextView tmp = (TextView) v;
        int chooseDay = Integer.parseInt(tmp.getText().toString()) - 1;

        if (chooseDay <= day) {
            for (TextView tv : days) {
                if (tv == tmp) {
                    tv.setBackground(getResources().getDrawable(R.drawable.purple_circle, null));
                    tv.setTextColor(Color.WHITE);

                    try {
                        if (diaryList.size() > 0)
                            markdownView.loadMarkdown("# " + diaryList.get(chooseDay).title
                                    + "\n" + diaryList.get(chooseDay).content);
                        else markdownView.loadMarkdown("");
                    }catch (Exception ignored){
                        markdownView.loadMarkdown("");
                    }finally {
                        try{
                            String[] todo = todoList.get(chooseDay).split(", ");
                            for (int i = 0; i < todo.length; i++) todos.get(i).setText(todo[i]);
                        }catch (Exception ignored){
                            for (int i = 0; i < todos.size(); i++) todos.get(i).setText("");
                        }
                    }

                    if(isMine) {
                        if (chooseDay == day){
                            btnWriteDiary.setVisibility(View.VISIBLE);
                            for (int i = 0; i < todos.size(); i++) todos.get(i).setEnabled(true);
                        }
                        else{
                            btnWriteDiary.setVisibility(View.GONE);
                            for (int i = 0; i < todos.size(); i++) todos.get(i).setEnabled(false);
                        }
                    }
                } else {
                    tv.setBackground(getResources().getDrawable(R.drawable.white_circle, null));
                    tv.setTextColor(Color.argb(255, 80, 80, 80));
                }
                findViewById(R.id.scroll_view).requestLayout();
            }
        } else {
            UtilClass.Toast(getApplicationContext(), "아직 진행되지 않은 Day 입니다.");
        }
    }

    @SuppressLint("FindViewByIdCast")
    private void setDays() {
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

    @SuppressLint("FindViewByIdCast")
    private void setTodos() {
        todos.add(findViewById(R.id.edit_will_work_one));
        todos.add(findViewById(R.id.edit_will_work_two));
        todos.add(findViewById(R.id.edit_will_work_three));
        todos.add(findViewById(R.id.edit_will_work_four));
    }
}
