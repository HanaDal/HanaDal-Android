package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
  GET /api/book
  GET /api/trending/book
*/
public class BookCard {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("pictureUrl")
    @Expose
    public String pictureUrl;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("achievementRate")
    @Expose
    public Integer achievementRate;
    @SerializedName("completeDate")
    @Expose
    public String completeDate;

}
