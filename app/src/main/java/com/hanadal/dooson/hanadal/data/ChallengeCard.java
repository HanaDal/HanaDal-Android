package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
  GET /api/user/cheering
  GET /api/challenge
  GET /api/trending/challenge
  GET /api/search
*/
public class ChallengeCard {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("pictureUrl")
    @Expose
    public String pictureUrl;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("tags")
    @Expose
    public ArrayList<String> tags = null;
    @SerializedName("achievementRate")
    @Expose
    public Integer achievementRate;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("isPressed")
    @Expose
    public Boolean isPressed;

}
