package com.hanadal.dooson.hanadal.ui.favorite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.adapter.ChallengeListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.ChallengeCard;
import com.hanadal.dooson.hanadal.ui.start.StartActivity;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView challengeList;
    ChallengeListAdapter adapter;
    ArrayList<ChallengeCard> arrayList = new ArrayList<>();

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

        UtilClass.loadProgress(this);

        Connector.api.getCheering(UtilClass.getToken(getApplicationContext())).enqueue(new Res<ArrayList<ChallengeCard>>(getApplicationContext()) {
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
