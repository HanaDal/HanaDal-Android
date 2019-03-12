package com.hanadal.dooson.hanadal.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.data.BookCard;
import com.hanadal.dooson.hanadal.ui.show_book.ShowBookActivity;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder>
        implements View.OnClickListener{

    ArrayList<BookCard> arrayList;
    Context context;

    public void add(BookCard data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    public void remove(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public BookListAdapter(ArrayList<BookCard> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_book, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.thisItemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onClick(View v) {
        v.getContext().startActivity(new Intent(context, ShowBookActivity.class));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView challengeName;
        TextView challengeAchieve;
        TextView challengeUserName;
        View thisItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            thisItemView = itemView;
            challengeName = itemView.findViewById(R.id.challenge_name);
            challengeAchieve = itemView.findViewById(R.id.challenge_achieve);
            challengeUserName = itemView.findViewById(R.id.challenge_user_name);
        }
    }
}
