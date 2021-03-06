package com.hanadal.dooson.hanadal.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.data.QnACard;
import com.hanadal.dooson.hanadal.ui.show_qna.ShowQnaActivity;

import java.util.ArrayList;

public class RequestNQnaListAdapter extends RecyclerView.Adapter<RequestNQnaListAdapter.ViewHolder>
        implements View.OnClickListener{

    ArrayList<QnACard> arrayList;
    Context context;
    private boolean request = false;

    public void add(QnACard data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    public void remove(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public RequestNQnaListAdapter(ArrayList<QnACard> arrayList, Context context, boolean request){
        this.arrayList = arrayList;
        this.context = context;
        this.request = request;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = request ?
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false)
                : LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qna, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            if(!request) {
                holder.thisItemView.setOnClickListener(v -> {
                    Intent intent = new Intent(context, ShowQnaActivity.class);
                    intent.putExtra("id", arrayList.get(position).id);
                    context.startActivity(intent);
                });
                holder.commentCount.setText("답변수: " + arrayList.get(position).answerCount);
                StringBuilder stringBuilder = new StringBuilder();
                for (String tag : arrayList.get(position).tags)
                    stringBuilder.append("#").append(tag).append(" ");
                holder.qnaTag.setText(stringBuilder.toString());
            }
            holder.qnaName.setText(arrayList.get(position).title);
            holder.qnaUserName.setText(arrayList.get(position).author.name);

            Glide.with(context)
                    .load(arrayList.get(position).author.picture)
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
                            .override(150))
                    .into(holder.qnaUserImage);
        }catch (NullPointerException ignored){}
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

        ImageView qnaUserImage;
        TextView qnaName;
        TextView commentCount;
        TextView qnaUserName;
        TextView qnaTag;
        View thisItemView;

        public ViewHolder(View itemView) {
            super(itemView);

            thisItemView = itemView;

            qnaName = itemView.findViewById(R.id.qna_name);
            commentCount = itemView.findViewById(R.id.comment_count);
            qnaUserName = itemView.findViewById(R.id.qna_user_name);
            qnaTag = itemView.findViewById(R.id.qna_tag);
            qnaUserImage = itemView.findViewById(R.id.qna_user_image);
        }
    }
}
