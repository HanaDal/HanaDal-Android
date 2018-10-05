package com.hanadal.dooson.hanadal.ui.trending;

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
import com.hanadal.dooson.hanadal.adapter.BookListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.BookCard;

import java.util.ArrayList;

public class TredingBookListFragment extends Fragment {

    RecyclerView challengeList;
    BookListAdapter adapter;
    ArrayList<BookCard> arrayList = new ArrayList<>();

    @Override
    public void onStart()
    {
        adapter.remove();
        super.onStart();
        Connector.api.getTredingBook().enqueue(new Res<ArrayList<BookCard>>(getContext()) {
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