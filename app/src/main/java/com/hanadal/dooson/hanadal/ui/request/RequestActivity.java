package com.hanadal.dooson.hanadal.ui.request;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.QnACard;
import com.hanadal.dooson.hanadal.ui.adapter.RequestNQnaListAdapter;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity
        implements View.OnClickListener{

    RecyclerView requestRecycler;
    EditText requestEdit;
    Button requestBtn;
    TextView noText;

    Intent intent;

    RequestNQnaListAdapter requestNAnswerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        requestRecycler = findViewById(R.id.request_recycler);
        requestEdit = findViewById(R.id. request_edit);
        requestBtn = findViewById(R.id.request_btn);
        noText = findViewById(R.id.no_text);

        requestNAnswerAdapter = new RequestNQnaListAdapter(new ArrayList<>(), getApplicationContext(), true);
        requestRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        requestRecycler.setHasFixedSize(false);
        requestRecycler.setItemAnimator(new DefaultItemAnimator());
        requestRecycler.setAdapter(requestNAnswerAdapter);

        requestBtn.setOnClickListener(this);

        intent = getIntent();

        loadingRequest();
    }

    private void loadingRequest(){
        UtilClass.loadProgress(this);
        Connector.api.getChallengeComment(intent.getExtras().getString("id"))
                .enqueue(new Res<ArrayList<QnACard>>(getApplicationContext()) {
                    @Override
                    public void callback(int code, ArrayList<QnACard> body) {
                        if(code == 200) {
                            if(body.size() > 0) {
                                noText.setVisibility(View.INVISIBLE);
                                requestNAnswerAdapter.remove();
                                for (QnACard qna : body) {
                                    requestNAnswerAdapter.add(qna);
                                }
                            } else {
                                noText.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(requestEdit.length() > 0){
            Connector.api.writeRequest(
                    intent.getExtras().getString("id"),
                    UtilClass.getToken(getApplicationContext()),
                    requestEdit.getText().toString(),
                    "i_am_challenge_request").enqueue(new Res<Gson>(getApplicationContext()) {
                @Override
                public void callback(int code, Gson body) {
                    if(code == 201){
                        requestEdit.setText("");
                        loadingRequest();
                    }
                }
            });
        }
    }
}
