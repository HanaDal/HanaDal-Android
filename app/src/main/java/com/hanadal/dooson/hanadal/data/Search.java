package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Search {

    @SerializedName("challenges")
    @Expose
    public ArrayList<ChallengeCard> challenges = null;
    @SerializedName("qnas")
    @Expose
    public ArrayList<QnACard> qnas = null;

}
