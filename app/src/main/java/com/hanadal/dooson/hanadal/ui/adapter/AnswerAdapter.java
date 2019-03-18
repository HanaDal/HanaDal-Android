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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.data.Answer;
import com.hanadal.dooson.hanadal.ui.show_qna.ShowQnaActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>
        implements View.OnClickListener{

    private ArrayList<Answer> arrayList;
    private Context context;

    public void add(Answer data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    public void remove(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public AnswerAdapter(ArrayList<Answer> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_qna_detail, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            holder.qnaDetailCommentContent.setText(arrayList.get(position).content);
            holder.qnaDetailCommentUserName.setText(arrayList.get(position).author.name);
            Glide.with(context)
                    .load(arrayList.get(position).author.picture)
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
                            .override(130)).into(holder.qnaDetailCommentUserImage);
        } catch (NullPointerException ignored){}
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
        TextView qnaDetailCommentUserName;
        CircleImageView qnaDetailCommentUserImage;
        TextView qnaDetailCommentContent;

        public ViewHolder(View itemView) {
            super(itemView);

            qnaDetailCommentUserName = itemView.findViewById(R.id.qna_detail_comment_user_name);
            qnaDetailCommentUserImage = itemView.findViewById(R.id.qna_detail_comment_user_image);
            qnaDetailCommentContent = itemView.findViewById(R.id.qna_detail_comment_content);
        }
    }
}
