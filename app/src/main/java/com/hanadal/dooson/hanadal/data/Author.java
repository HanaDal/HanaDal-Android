package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Challenege 나 QnA 에 들어가는 클래스
public class Author {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("pictureUrl")
    @Expose
    public String picture;

}
