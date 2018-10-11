package com.hanadal.dooson.hanadal.ui.show_qna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.adapter.AnswerAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.Answer;
import com.hanadal.dooson.hanadal.data.QnaDetail;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;
import java.util.Objects;

public class ShowQnaActivity extends AppCompatActivity
        implements View.OnClickListener{

    TextView qnaTitle;
    TextView qnaTag;
    TextView qnaContent;
    TextView qnaUserName;
    ImageView qnaUserImage;
    RecyclerView recyclerView;
    EditText qnaCommentEditText;
    Button qnaCommentBtn;

    AnswerAdapter answerAdapter;
    ArrayList<Answer> arrayList = new ArrayList<>();

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViOWFlNmJjMzY1ZjBlMTNhODVhMTQ0YSIsImlhdCI6MTUzODAyNTcwMSwiZXhwIjoxNTQwNjE3NzAxLCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.9Ar-ElYJYpe_h9jet6TP3egDmr7vSpwuaz8mh-rr5Nc";

    @Override
    protected void onStart() {
        super.onStart();
        answerAdapter.remove();
        Intent intent = getIntent();
        Connector.api.shpwQnA(Objects.requireNonNull(intent.getExtras()).getString("id"))
                .enqueue(new Res<QnaDetail>(getApplicationContext()) {
                    @Override
                    public void callback(int code, QnaDetail body) {
                        if(code == 200) {
                            qnaTitle.setText(body.title);
                            qnaContent.setText(body.content);
                            StringBuilder tag = new StringBuilder();
                            for (String s : body.tags) tag.append("#").append(s).append(" ");
                            qnaTag.setText(tag);
                            Log.e("asdf", body.author.name);

                            String tmp = body.author.name;
                            qnaUserName.setText(tmp);
                            //Glide.with(getApplicationContext()).load(body.author.picture).into(qnaUserImage);
                            for(int i = 0; i < body.answers.size(); i++){
                                answerAdapter.add(body.answers.get(i));
                            }
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qna);

        answerAdapter = new AnswerAdapter(arrayList, getApplicationContext());

        qnaTitle = findViewById(R.id.qna_content_name);
        qnaTag = findViewById(R.id.qna_content_tag);
        qnaContent = findViewById(R.id.qna_content_body_text);
        qnaUserName = findViewById(R.id.qna_content_user_name);
        qnaUserImage = findViewById(R.id.qna_content_user_img);
        qnaCommentEditText = findViewById(R.id.qna_detail_comment_edit);
        qnaCommentBtn = findViewById(R.id.qna_detail_comment_btn);

        recyclerView = findViewById(R.id.qna_content_comment);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(answerAdapter);

        qnaCommentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(qnaCommentEditText.getTextSize() > 0) {
            Intent intent = getIntent();
            Connector.api.addQnAComment(token,
                    Objects.requireNonNull(intent.getExtras()).getString("id"),
                    qnaCommentEditText.getText().toString()).enqueue(new Res<Gson>(getApplicationContext()) {
                @Override
                public void callback(int code, Gson body) {
                    if(code == 201) {
                        UtilClass.Toast(getApplicationContext(), "답변이 달렸습니다.");
                        qnaCommentEditText.setText("");
                        Connector.api.shpwQnA(Objects.requireNonNull(intent.getExtras()).getString("id"))
                                .enqueue(new Res<QnaDetail>(getApplicationContext()) {
                                    @Override
                                    public void callback(int code, QnaDetail body) {
                                        if(code == 200) {
                                            for(int i = 0; i < body.answers.size(); i++){
                                                answerAdapter.add(body.answers.get(i));
                                            }
                                        }
                                    }
                                });
                    }else{
                        UtilClass.Toast(getApplicationContext(), "답변 달기를 실패했습니다!");
                    }
                }
            });
        }else UtilClass.Toast(getApplicationContext(), "내용을 입력 해주세요!");
    }

    public void onBackClick(View v){
        finish();
    }
}
