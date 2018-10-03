package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
  GET /api/qna/{id}
  GET /api/challenege/{id}/comment/{no}
*/
public class QnaDetail {

    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tags")
    @Expose
    public ArrayList<String> tags = null;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("answers")
    @Expose
    public ArrayList<Answer> answers = null;

}
