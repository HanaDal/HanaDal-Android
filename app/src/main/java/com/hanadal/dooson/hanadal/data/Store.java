package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {

    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("skins")
    @Expose
    public List<Skin> skins = null;
    @SerializedName("medals")
    @Expose
    public List<Medal> medals = null;

    public class Skin {

        @SerializedName("_id")
        @Expose
        public String id;
        @SerializedName("picture")
        @Expose
        public String picture;
        @SerializedName("name")
        @Expose
        public String name;

    }

    public class Medal {

        @SerializedName("picture")
        @Expose
        public String picture;
        @SerializedName("name")
        @Expose
        public String name;

    }
}
