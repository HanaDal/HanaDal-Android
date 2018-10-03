package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// QnADetail에 사용되는 클래스
public class Answer {

    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("content")
    @Expose
    public String content;

}
