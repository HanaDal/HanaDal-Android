package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QnAnComment {

    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("authorPictureUrl")
    @Expose
    public String authorPictureUrl;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("answers")
    @Expose
    public List<Answer> answers = null;

    public class Answer {

        @SerializedName("authorPcitureUrl")
        @Expose
        public String authorPcitureUrl;
        @SerializedName("author")
        @Expose
        public String author;
        @SerializedName("content")
        @Expose
        public String content;

    }
}
