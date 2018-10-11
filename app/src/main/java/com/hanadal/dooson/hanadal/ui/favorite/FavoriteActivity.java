package com.hanadal.dooson.hanadal.ui.favorite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.adapter.ChallengeListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.ChallengeCard;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView challengeList;
    ChallengeListAdapter adapter;
    ArrayList<ChallengeCard> arrayList = new ArrayList<>();

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViOWFlNmJjMzY1ZjBlMTNhODVhMTQ0YSIsImlhdCI6MTUzODAyNTcwMSwiZXhwIjoxNTQwNjE3NzAxLCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.9Ar-ElYJYpe_h9jet6TP3egDmr7vSpwuaz8mh-rr5Nc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        challengeList = findViewById(R.id.recycler_view);

        adapter = new ChallengeListAdapter(arrayList, getApplicationContext());
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);

        Connector.api.getCheering(token).enqueue(new Res<ArrayList<ChallengeCard>>(getApplicationContext()) {
            @Override
            public void callback(int code, ArrayList<ChallengeCard> body) {
                if(code == 200){
                    for(ChallengeCard c : body) adapter.add(c);
                }
            }
        });
    }

    public void onBackClick(View v){
        finish();
    }
}
