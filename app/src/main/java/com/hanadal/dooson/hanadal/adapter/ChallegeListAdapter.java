package com.hanadal.dooson.hanadal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;

public class ChallegeListAdapter extends RecyclerView.Adapter<ChallegeListAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_challenge, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView challengeName;
        TextView challengeAchieve;
        TextView challengeUserName;

        ImageView challengeUserImage;
        ImageView challengeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            challengeName = itemView.findViewById(R.id.challenge_name);
            challengeAchieve = itemView.findViewById(R.id.challenge_achieve);
            challengeUserName = itemView.findViewById(R.id.challenge_user_name);

            challengeImage = itemView.findViewById(R.id.challenge_image);
            challengeUserImage = itemView.findViewById(R.id.challenge_user_image);
        }
    }
}
