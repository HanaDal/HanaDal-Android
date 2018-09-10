package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("point")
    @Expose
    public Integer point;
    @SerializedName("tags")
    @Expose
    public String tags;

}
