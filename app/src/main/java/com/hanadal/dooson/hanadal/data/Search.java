package com.hanadal.dooson.hanadal.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("challenges")
    @Expose
    public List<Challenge> challenges = null;
    @SerializedName("qnas")
    @Expose
    public List<Qna> qnas = null;

    public class Challenge {

        @SerializedName("id")
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

    public class Qna {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("tags")
        @Expose
        public String tags;
        @SerializedName("authorPicutreUrl")
        @Expose
        public String authorPicutreUrl;
        @SerializedName("author")
        @Expose
        public String author;
        @SerializedName("answerCount")
        @Expose
        public Integer answerCount;

    }
}
