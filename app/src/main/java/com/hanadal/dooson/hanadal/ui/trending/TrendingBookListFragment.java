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
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.adapter.BookListAdapter;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.BookCard;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;

public class TrendingBookListFragment extends Fragment {

    RecyclerView challengeList;
    TextView noText;
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
                    if(body.size() > 0) {
                        noText.setVisibility(View.INVISIBLE);
                        for (BookCard b : body) {
                            adapter.add(b);
                        }
                    } else {
                        noText.setVisibility(View.VISIBLE);
                        noText.setText("트렌딩한 책이 없어요. ㅠㅠ");
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

        adapter = new BookListAdapter(arrayList, getContext());
        challengeList.setHasFixedSize(false);
        challengeList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        challengeList.setItemAnimator(new DefaultItemAnimator());
        challengeList.setAdapter(adapter);

        return view;
    }
}