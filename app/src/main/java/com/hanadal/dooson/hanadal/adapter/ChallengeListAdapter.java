package com.hanadal.dooson.hanadal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.data.ChallengeCard;
import com.hanadal.dooson.hanadal.ui.show_challenge.ShowChallengeActivity;

import java.util.ArrayList;

public class ChallengeListAdapter extends RecyclerView.Adapter<ChallengeListAdapter.ViewHolder>
        implements View.OnClickListener{

    ArrayList<ChallengeCard> arrayList;
    Context context;

    public void add(ChallengeCard data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    public void remove(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public ChallengeListAdapter(ArrayList<ChallengeCard> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_challenge, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.challengeFavorite.setOnClickListener(this);
        holder.challengeFork.setOnClickListener(this);
        holder.challengeShare.setOnClickListener(this);
        holder.thisItemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowChallengeActivity.class);
            intent.putExtra("id", arrayList.get(position).id);
            context.startActivity(intent);
        });

        holder.challengeName.setText(arrayList.get(position).name);
        holder.challengeAchieve.setText("달성률 : " + arrayList.get(position).achievementRate + "%");
        holder.challengeUserName.setText(arrayList.get(position).author.name);
        StringBuilder tags = new StringBuilder();
        for(String s : arrayList.get(position).tags) tags.append("#").append(s).append(" ");
        holder.challengeTag.setText(tags);

        Glide.with(context).load(arrayList.get(position).pictureUrl).into(holder.challengeImage);
        Log.e("테스트", arrayList.get(position).achievementRate.toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.challenge_favorite:{
                break;
            }
            case R.id.challenge_fork:{
                break;
            }
            case R.id.challenge_share:{
                break;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView challengeName;
        TextView challengeAchieve;
        TextView challengeUserName;


        ImageView challengeUserImage;
        ImageView challengeImage;

        TextView challengeFavorite;
        TextView challengeFork;
        TextView challengeShare;

        TextView challengeTag;

        View thisItemView;

        public ViewHolder(View itemView) {
            super(itemView);

            thisItemView = itemView;

            challengeName = itemView.findViewById(R.id.challenge_name);
            challengeAchieve = itemView.findViewById(R.id.challenge_achieve);
            challengeUserName = itemView.findViewById(R.id.challenge_user_name);

            challengeImage = itemView.findViewById(R.id.challenge_image);
            challengeUserImage = itemView.findViewById(R.id.challenge_user_image);

            challengeFavorite = itemView.findViewById(R.id.challenge_favorite);
            challengeFork = itemView.findViewById(R.id.challenge_fork);
            challengeShare = itemView.findViewById(R.id.challenge_share);
            challengeTag = itemView.findViewById(R.id.challenge_tag);
        }
    }
}
