package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// GET /api/user/login
public class Login {

    @SerializedName("jwt")
    @Expose
    public String jwt;

}
