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
import com.hanadal.dooson.hanadal.ui.adapter.QnaListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.QnACard;

import java.util.ArrayList;

public class MyQnaListFragment extends Fragment {

    RecyclerView challengeList;
    QnaListAdapter adapter;
    ArrayList<QnACard> arrayList = new ArrayList<>();

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViYjY5ZmExMjBhMDFjMDAxM2VlOGZjYSIsImlhdCI6MTU0NDY4NzIyNCwiZXhwIjoxNTQ3Mjc5MjI0LCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.Ex2D1-lhwcQa7baTgQLnfgiecuSg37sjtX1xMaRSwxg";

    @Override
    public void onStart() {
        super.onStart();
        adapter.remove();
        Connector.api.getMyQuestion(token).enqueue(new Res<ArrayList<QnACard>>(getContext()) {
            @Override
            public void callback(int code, ArrayList<QnACard> body) {
                if(code == 200){
                    for(QnACard qac : body)
                    adapter.add(qac);
                }
            }
        });
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