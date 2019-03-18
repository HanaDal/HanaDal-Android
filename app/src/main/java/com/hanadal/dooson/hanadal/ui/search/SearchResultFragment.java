package com.hanadal.dooson.hanadal.ui.search;

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
import com.hanadal.dooson.hanadal.data.ChallengeCard;
import com.hanadal.dooson.hanadal.data.QnACard;
import com.hanadal.dooson.hanadal.ui.adapter.ChallengeListAdapter;
import com.hanadal.dooson.hanadal.ui.adapter.RequestNQnaListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {

    private ChallengeListAdapter challengeListAdapter;
    private RequestNQnaListAdapter qnaListAdapter;
    private RecyclerView recyclerView;
    private TextView noText;

    void putResult(ArrayList<ChallengeCard> list){
        if(challengeListAdapter == null) {
            challengeListAdapter = new ChallengeListAdapter(new ArrayList<>(), getContext());
            recyclerView.setAdapter(challengeListAdapter);
        }
        challengeListAdapter.remove();
        if(list.size() > 0) {
            for (ChallengeCard c : list) {
                challengeListAdapter.add(c);
            }
        } else{
            noText.setVisibility(View.VISIBLE);
            noText.setText("검색된 도전이 없어요. ㅠㅠ");
        }
    }

    void putResult(List<QnACard> list){
        if(qnaListAdapter == null) {
            qnaListAdapter = new RequestNQnaListAdapter(new ArrayList<>(), getContext(), false);
            recyclerView.setAdapter(qnaListAdapter);
        }

        qnaListAdapter.remove();
        if(list.size() > 0) {
            for (QnACard q : list) {
                qnaListAdapter.add(q);
            }
        } else{
            noText.setVisibility(View.VISIBLE);
            noText.setText("검색된 QnA 가 없어요. ㅠㅠ");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);

        recyclerView = view.findViewById(R.id.fragment_recycler_view);
        noText = view.findViewById(R.id.no_text);

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
