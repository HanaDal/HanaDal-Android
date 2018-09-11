package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChallengeInfo {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("authorPictureUrl")
    @Expose
    public String authorPictureUrl;
    @SerializedName("author")
    @Expose
    public String author;

}