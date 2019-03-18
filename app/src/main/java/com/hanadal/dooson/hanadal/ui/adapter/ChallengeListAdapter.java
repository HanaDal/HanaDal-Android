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
import com.hanadal.dooson.hanadal.data.ChallengeCard;
import com.hanadal.dooson.hanadal.ui.show_challenge.ShowChallengeActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChallengeListAdapter extends RecyclerView.Adapter<ChallengeListAdapter.ViewHolder> {

    private ArrayList<ChallengeCard> arrayList;
    private Context context;

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
        holder.bind(context, arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView challengeName;
        TextView challengeAchieve;
        TextView challengeUserName;

        CircleImageView challengeUserImage;
        ImageView challengeImage;

        TextView challengeFavorite;
        TextView challengeFork;
        TextView challengeShare;

        TextView challengeTag;

        View thisItemView;

        ViewHolder(View itemView) {
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

        void bind(Context context, ChallengeCard card){
            challengeFavorite.setOnClickListener(this);
            challengeFork.setOnClickListener(this);
            challengeShare.setOnClickListener(this);

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            try{
                Glide.with(context)
                        .load(card.pictureUrl)
                        .apply(new RequestOptions()
                                .placeholder(circularProgressDrawable)
                                .override(50))
                        .into(challengeImage);

                challengeName.setText(card.name);
                challengeUserName.setText(card.author.name);
                challengeAchieve.setText("달성률 : " + card.achievementRate + "%");
                StringBuilder tags = new StringBuilder();
                for(String s : card.tags) tags.append("#").append(s).append(" ");
                challengeTag.setText(tags);

                Glide.with(context).load(card.pictureUrl).into(challengeImage);
                Glide.with(context).load(card.author.picture)
                        .apply(new RequestOptions()
                                .placeholder(circularProgressDrawable)
                                .override(150))
                        .into(challengeUserImage);

                thisItemView.setOnClickListener(v -> {
                    Intent intent = new Intent(context, ShowChallengeActivity.class);
                    intent.putExtra("id", card.id);
                    intent.putExtra("title", card.name);
                    context.startActivity(intent);
                });
            }catch (NullPointerException ignored){ }
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
    }
}
