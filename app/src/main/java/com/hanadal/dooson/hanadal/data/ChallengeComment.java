package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChallengeComment {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("authorPicutreUrl")
    @Expose
    public String authorPicutreUrl;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("answerCount")
    @Expose
    public Integer answerCount;

}