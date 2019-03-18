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
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.adapter.RequestNQnaListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.QnACard;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;

public class MyQnaCommentListFragment extends Fragment {

    RecyclerView challengeList;
    TextView noText;
    RequestNQnaListAdapter adapter;
    ArrayList<QnACard> arrayList = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        adapter.remove();
        Connector.api.getMyAnswer(UtilClass.getToken(getContext())).enqueue(new Res<ArrayList<QnACard>>(getContext()) {
            @Override
            public void callback(int code, ArrayList<QnACard> body) {
                if(code == 200){
                    if(body.size() > 0) {
                        for (QnACard qac : body)
                            adapter.add(qac);
                    }else{
                        noText.setVisibility(View.VISIBLE);
                        noText.setText("댓글을 남긴 QnA 가 없어요. ㅠㅠ");
                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        challengeList = view.findViewById(R.id.fragment_recycler_view);
        noText = view.findViewById(R.id.no_text);

        adapter = new RequestNQnaListAdapter(arrayList, getContext(), false);
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new LinearLayoutManager(getContext()));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);

        return view;
    }
}