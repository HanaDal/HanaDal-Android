package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowChallenge {

    @SerializedName("result")
    @Expose
    public String result;
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
    public List<Diary> diary = null;
    @SerializedName("todo")
    @Expose
    public List<String> todo = null;

    public class Diary {

        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("content")
        @Expose
        public String content;

    }
}
