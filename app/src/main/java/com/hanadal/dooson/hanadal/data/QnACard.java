package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
  GET /api/challenege/{id}/comment
  GET /api/qna
  GEt /api/search
*/
public class QnACard {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tags")
    @Expose
    public ArrayList<String> tags = null;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("answerCount")
    @Expose
    public Integer answerCount;

}
