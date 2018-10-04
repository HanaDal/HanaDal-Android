package com.hanadal.dooson.hanadal.ui.qna;

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
import com.hanadal.dooson.hanadal.adapter.QnaListAdapter;
import com.hanadal.dooson.hanadal.data.QnACard;

import java.util.ArrayList;

public class MyQnaListFragment extends Fragment {

    RecyclerView challengeList;
    QnaListAdapter adapter;
    ArrayList<QnACard> arrayList = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        challengeList = view.findViewById(R.id.fragment_recycler_view);

        adapter = new QnaListAdapter(arrayList, getContext());
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new LinearLayoutManager(getContext()));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);

        return view;
    }
}