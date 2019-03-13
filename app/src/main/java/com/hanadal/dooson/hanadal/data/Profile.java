package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// GET /api/user/profile
public class Profile {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("picture")
    @Expose
    public String picture;
    @SerializedName("point")
    @Expose
    public Integer point;
    @SerializedName("tags")
    @Expose
    public ArrayList<String> tags = null;

}
