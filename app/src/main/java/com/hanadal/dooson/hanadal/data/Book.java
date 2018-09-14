package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

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
    public String author;
    @SerializedName("achievementRate")
    @Expose
    public Integer achievementRate;
    @SerializedName("date")
    @Expose
    public String date;
}
