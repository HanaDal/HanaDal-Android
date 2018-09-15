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

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.adapter.ChallengeListAdapter;
import com.hanadal.dooson.hanadal.data.Challenge;

import java.util.ArrayList;

public class ChallengeListFragment extends Fragment {

    RecyclerView challengeList;
    ChallengeListAdapter adapter;
    ArrayList<Challenge> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_challenge_recycler, container, false);
        challengeList = view.findViewById(R.id.fragment_recycler_view);

        adapter = new ChallengeListAdapter(arrayList, getContext());
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new LinearLayoutManager(getContext()));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);

        adapter.add(new Challenge());
        adapter.add(new Challenge());
        adapter.add(new Challenge());
        adapter.add(new Challenge());
        adapter.add(new Challenge());
        adapter.add(new Challenge());
        adapter.add(new Challenge());
        adapter.add(new Challenge());

        return view;
    }
}
