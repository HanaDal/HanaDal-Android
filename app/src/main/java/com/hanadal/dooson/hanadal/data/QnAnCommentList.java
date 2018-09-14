package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QnAnCommentList {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("authorPictureUrl")
    @Expose
    public String authorPictureUrl;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("answerCount")
    @Expose
    public Integer answerCount;

}
