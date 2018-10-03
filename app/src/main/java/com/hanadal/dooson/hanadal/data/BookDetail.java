package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// GET /api/book/{id}
public class BookDetail {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("pictureUrl")
    @Expose
    public String pictureUrl;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("achievementRate")
    @Expose
    public String achievementRate;
    @SerializedName("completeDate")
    @Expose
    public String completeDate;
    @SerializedName("content")
    @Expose
    public ArrayList<String> content = null;

}
