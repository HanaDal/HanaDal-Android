package com.hanadal.dooson.hanadal.ui.my_challenge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.adapter.BookListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.BookCard;

import java.util.ArrayList;

public class BookListFragment extends Fragment {

    RecyclerView challengeList;
    BookListAdapter adapter;
    ArrayList<BookCard> arrayList = new ArrayList<>();

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViYjY5ZmExMjBhMDFjMDAxM2VlOGZjYSIsImlhdCI6MTU0NDY4NzIyNCwiZXhwIjoxNTQ3Mjc5MjI0LCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.Ex2D1-lhwcQa7baTgQLnfgiecuSg37sjtX1xMaRSwxg";

    @Override
    public void onStart() {
        super.onStart();
        Connector.api.getBook(token).enqueue(new Res<ArrayList<BookCard>>(getContext()) {
            @Override
            public void callback(int code, ArrayList<BookCard> body) {
                if(code == 200){
                    for(BookCard b : body){
                        adapter.add(b);
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

        adapter = new BookListAdapter(arrayList, getContext());
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);

        return view;
    }
}