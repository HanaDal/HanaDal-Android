package com.hanadal.dooson.hanadal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.data.QnACard;
import com.hanadal.dooson.hanadal.ui.show_qna.ShowQnaActivity;

import java.util.ArrayList;

public class QnaListAdapter extends RecyclerView.Adapter<QnaListAdapter.ViewHolder>
        implements View.OnClickListener{

    ArrayList<QnACard> arrayList;
    Context context;

    public void add(QnACard data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    public void remove(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public QnaListAdapter(ArrayList<QnACard> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_qna, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.thisItemView.setOnClickListener(this);
        holder.qnaName.setText(arrayList.get(position).title);
        holder.qnaUserName.setText(arrayList.get(position).author.name);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onClick(View v) {
        v.getContext().startActivity(new Intent(context, ShowQnaActivity.class));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView qnaName;
        TextView commentCount;
        TextView qnaUserName;
        View thisItemView;

        public ViewHolder(View itemView) {
            super(itemView);

            thisItemView = itemView;

            qnaName = itemView.findViewById(R.id.qna_name);
            commentCount = itemView.findViewById(R.id.comment_count);
            qnaUserName = itemView.findViewById(R.id.qna_user_name);
        }
    }
}
