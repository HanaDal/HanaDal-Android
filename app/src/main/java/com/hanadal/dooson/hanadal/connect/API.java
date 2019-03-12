package com.hanadal.dooson.hanadal.connect;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hanadal.dooson.hanadal.data.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    /** USER **/

    // 로그인
    @FormUrlEncoded
    @POST("user/login")
    Call<Login> login(@Field("id") String id);

    // 회원가입
    @FormUrlEncoded
    @POST("user/login")
    Call<Gson> resister(@Field("id") String id,
                        @Field("name") String name,
                        @Field("tags") String tags);

    // 프로필 정보 얻기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("user/profile")
    Call<Profile> getProfile(@Header("X-Access-Token") String jwt);

    // 프로필 정보 변경
    @FormUrlEncoded
    @PUT("user/profile")
    Call<Gson> modifyProfile(@Header("X-Access-Token") String jwt,
                             @Field("profileName") String profileName,
                             @Field("profileTags") String profileTags);

    // 공감한 편린들
    @GET("user/cheering")
    Call<ArrayList<ChallengeCard>> getCheering(@Header("X-Access-Token") String jwt);

    // 편린에 공감하기
    @FormUrlEncoded
    @POST("user/cheering")
    Call<Gson> addCheering(@Header("X-Access-Token") String jwt, @Field("id") String id);

    /** Challenge **/

    // 내 도전 조회하기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("challenge")
    Call<ArrayList<ChallengeCard>> getMyChallenge(@Header("X-Access-Token") String jwt);

    // 도전 만들기
    @FormUrlEncoded
    @POST("challenge")
    Call<Gson> makeMyChallenge(@Header("X-Access-Token") String jwt,
                               @Field("title") String title,
                               @Field("description") String description,
                               @Field("isPublic") Boolean isPublic,
                               @Field("isStrict") Boolean isStrict,
                               @Field("tags") String tags);

    // 도전 상세보기
    @GET("challenge/{id}")
    Call<ChallengeDetail> showChallenge(@Header("X-Access-Token") String jwt, @Path("id") String id);

    // 도전 다어이리 작성하기
    @FormUrlEncoded
    @POST("challenge/{id}/diary/{day}")
    Call<Gson> writeDiary(@Path("id") String id,
                          @Path("day") String day,
                          @Field("title") String title,
                          @Field("content") String content);

    // ToDo 작성하기
    @FormUrlEncoded
    @POST("challenge/{id}/todo/{day}")
    Call<Gson> writeTodo(@Path("id") String id,
                         @Path("day") String day,
                         @Field("content") String content);

    // 도전 의견 확인하기
    @GET("challenge/{id}/comment")
    Call<ArrayList<QnACard>> getCommentList(@Path("id") String id);

    // 도전 의견 달기
    @FormUrlEncoded
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("challenge/{id}/comment")
    Call<Gson> writeComment(@Header("X-Access-Token") String jwt,
                                                   @Path("id") String id,
                                                   @Field("title ") String title,
                                                   @Field("content") String content);

    // 도전 의견 상세 보기
    @GET("challenge/{id}/comment/{no}")
    Call<QnaDetail> showComment(@Path("id") String id, @Path("no") String no);

    // 도전 의견에 댓글 달기
    @FormUrlEncoded
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("challenge/{id}/comment/{no}")
    Call<Gson> writeCommentFromComment(@Header("X-Access-Token") String jwt,
                                       @Path("id") String id,
                                       @Path("no") String no,
                                       @Field("content") String content);

    // 도전 정보
    @GET("challenge/{id}/info")
    Call<ChallengeInfo> GetChallengeInfo(@Path("id") String id);

    // 도전에 대한 정보 수정
    @FormUrlEncoded
    @PUT("challenge/{id}/info")
    Call<Gson> modifyChallengeInfo(@Header("X-Access-Token") String jwt,
                                   @Path("id") String id,
                                   @Field("title") String title,
                                   @Field("description") String description,
                                   @Field("tags") String tags);

    // 도전 포크
    @POST("challenge/{id}/fork")
    Call<Gson> forkChallenge(@Header("X-Access-Token") String jwt, @Path("id") String id);

    // 내 책 조회하기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("book")
    Call<ArrayList<BookCard>> getBook(@Header("X-Access-Token") String jwt);

    // 책 상세 보기
    @GET("challenge/book/{id}")
    Call<BookDetail> showBook(@Path("id") String id);

    /** Trending **/

    // 트렌딩 도전
    @GET("trending/challenge")
    Call<ArrayList<ChallengeCard>> getTredingChallenge();

    // 트렌딩 책
    @GET("trending/book")
    Call<ArrayList<BookCard>> getTredingBook();

    /** QnA **/

    // QnA들 가져오기
    @GET("qna")
    Call<ArrayList<QnACard>> getQnA();

    // QnA 작성
    @FormUrlEncoded
    @POST("qna")
    Call<Gson> writeQnA(@Header("X-Access-Token") String jwt,
                        @Field("title") String title,
                        @Field("tags") String tags,
                        @Field("content") String content);

    // QnA 상세 보기
    @GET("qna/{id}")
    Call<QnaDetail> shpwQnA(@Path("id") String id);

    // QnA 에 댓글 달기
    @FormUrlEncoded
    @POST("qna/{id}")
    Call<Gson> addQnAComment(@Header("X-Access-Token") String jwt,
                             @Path("id") String id,
                             @Field("content") String content);

    // QnA 수정
    @FormUrlEncoded
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("qna/{id}")
    Call<Gson> modifyQnA(@Header("X-Access-Token") String jwt,
                         @Path("id") String id,
                         @Field("title") String title,
                         @Field("tags") String tags,
                         @Field("content") String content);

    /** Search **/

    // 도전, QnA 검색
    @GET("search")
    Call<Search> searching(@Query("query") String query);


    @GET("qna/my-question")
    Call<ArrayList<QnACard>> getMyQuestion(@Header("X-Access-Token") String jwt);

    @GET("qna/my-answer")
    Call<ArrayList<QnACard>> getMyAnswer(@Header("X-Access-Token") String jwt);

    @GET("user/login")
    Call<JsonObject> facebookLogin();
}
