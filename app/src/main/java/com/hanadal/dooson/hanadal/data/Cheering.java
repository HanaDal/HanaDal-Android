package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cheering {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("pictureUrl")
    @Expose
    public String pictureUrl;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("tags")
    @Expose
    public List<String> tags = null;
    @SerializedName("achievementRate")
    @Expose
    public Integer achievementRate;
    @SerializedName("authorPictureUrl")
    @Expose
    public String authorPictureUrl;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("isPressed")
    @Expose
    public Boolean isPressed;

}