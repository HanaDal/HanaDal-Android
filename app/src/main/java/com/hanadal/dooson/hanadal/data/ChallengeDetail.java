package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// GET /api/callenge/{id}
public class ChallengeDetail {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("isMine")
    @Expose
    public Boolean isMine;
    @SerializedName("day")
    @Expose
    public Integer day;
    @SerializedName("diary")
    @Expose
    public ArrayList<Diary> diary = null;
    @SerializedName("todo")
    @Expose
    public ArrayList<String> todo = null;
    @SerializedName("isStrict")
    @Expose
    public Boolean isStrict;
}
