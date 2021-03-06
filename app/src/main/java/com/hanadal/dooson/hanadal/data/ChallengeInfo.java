package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// GET /api/challenege/{id}/info
public class ChallengeInfo {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("tags")
    @Expose
    public ArrayList<String> tags = null;
    @SerializedName("author")
    @Expose
    public Author author;

}
