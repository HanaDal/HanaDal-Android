package com.hanadal.dooson.hanadal.ui.my_challenge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.adapter.ChallengeListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.ChallengeCard;
import com.hanadal.dooson.hanadal.util.UtilClass;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChallengeListFragment extends Fragment {

    RecyclerView challengeList;
    TextView noText;
    ChallengeListAdapter adapter;
    ArrayList<ChallengeCard> arrayList = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        adapter.remove();
        Connector.api.getMyChallenge(UtilClass.getToken(getContext())).enqueue(new Res<ArrayList<ChallengeCard>>(getContext()) {
            @Override
            public void callback(int code, ArrayList<ChallengeCard> body) {
                if(code == 200){
                    if(body.size() > 0) {
                        for (ChallengeCard c : body) {
                            adapter.add(c);
                        }
                    }else{
                        noText.setVisibility(View.VISIBLE);
                        noText.setText("진행중인 도전이 없어요. ㅠㅠ");
                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_challenge_recycler, container, false);
        challengeList = view.findViewById(R.id.fragment_recycler_view);
        noText = view.findViewById(R.id.no_text);

        adapter = new ChallengeListAdapter(arrayList, getContext());
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new LinearLayoutManager(getContext()));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);
        return view;
    }
}
