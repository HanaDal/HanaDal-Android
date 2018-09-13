package com.hanadal.dooson.hanadal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.data.Challenge;

import java.util.ArrayList;

public class ChallengeListAdapter extends RecyclerView.Adapter<ChallengeListAdapter.ViewHolder>
        implements View.OnClickListener{

    ArrayList<Challenge> arrayList;
    Context context;

    public void add(Challenge data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    public ChallengeListAdapter(ArrayList<Challenge> arrayList, Context context){
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.challengeFavorite.setOnClickListener(this);
        holder.challengeFork.setOnClickListener(this);
        holder.challengeShare.setOnClickListener(this);
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

        ImageView challengeFavorite;
        ImageView challengeFork;
        ImageView challengeShare;

        public ViewHolder(View itemView) {
            super(itemView);
            challengeName = itemView.findViewById(R.id.challenge_name);
            challengeAchieve = itemView.findViewById(R.id.challenge_achieve);
            challengeUserName = itemView.findViewById(R.id.challenge_user_name);

            challengeImage = itemView.findViewById(R.id.challenge_image);
            challengeUserImage = itemView.findViewById(R.id.challenge_user_image);

            challengeFavorite = itemView.findViewById(R.id.challenge_favorite);
            challengeFork = itemView.findViewById(R.id.challenge_fork);
            challengeShare = itemView.findViewById(R.id.challenge_share);
        }
    }
}
