package com.hanadal.dooson.hanadal.connect;

import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.data.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
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
    @POST("user/login")
    Call<Login> login(@Field("id") String id);

    // 회원가입
    @POST("user/login")
    Call<Gson> resister(@Field("id") String id,
                        @Field("name") String name,
                        @Field("tags") String tags);

    // 프로필 정보 얻기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("user/profile")
    Call<Profile> getProfile(@Header("X-Access-Token") String jwt);

    // 프로필 정보 변경
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("user/profile")
    Call<Gson> modifyProfile(@Header("X-Access-Token") String jwt,
                             @Field("profileName") String profileName,
                             @Field("profileTags") String profileTags);

    // 공감한 편린들
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("user/cheering")
    Call<ArrayList<Challenge>> getCheering(@Header("X-Access-Token") String jwt);

    // 편린에 공감하기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/cheering")
    Call<Gson> addCheering(@Header("X-Access-Token") String jwt, @Field("id") String id);

    // 보유 아이템 확인
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("user/item")
    Call<Item> getItem(@Header("X-Access-Token") String jwt);

    // 아이템 구매
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/item")
    Call<Gson> buyItem(@Header("X-Access-Token") String jwt, @Field("id") String id);

    // 상점
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("user/store")
    Call<Store> getStore(@Header("X-Access-Token") String jwt);

    /** Challenge **/

    // 내 도전 조회하기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("challenge")
    Call<ArrayList<Challenge>> getMyChallenge(@Header("X-Access-Token") String jwt);

    // 도전 만들기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("challenge")
    Call<Gson> makeMyChallenge(@Header("X-Access-Token") String jwt,
                               @Field("title") String title,
                               @Field("description") String description,
                               @Field("isPublic") Boolean isPublic,
                               @Field("isStrict") Boolean isStrict,
                               @Field("tags") String tags);

    // 도전 상세보기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("challenge/{id}")
    Call<ShowChallenge> showChallenge(@Header("X-Access-Token") String jwt, @Path("id") String id);

    // 도전 다어이리 작성하기
    @POST("challenge/{id}/diary/{day}")
    Call<Gson> writeDiary(@Path("id") String id,
                          @Path("day") String day,
                          @Field("title") String title,
                          @Field("content") String content);

    // 도전 다이어리 작성하기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("challenge/{id}/todo/{day}")
    Call<Gson> writeTodo(@Path("id") String id,
                         @Path("day") String day,
                         @Field("content") String content);

    // 도전 의견 확인하기
    @GET("challenge/{id}/comment")
    Call<ArrayList<QnAnCommentList>> getCommentList(@Path("id") String id);

    // 도전 의견 달기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("challenge/{id}/comment")
    Call<ArrayList<ChallengeComment>> writeComment(@Header("X-Access-Token") String jwt,
                                                   @Path("id") String id,
                                                   @Field("title ") String title,
                                                   @Field("content") String content);

    // 도전 의견 상세 보기
    @GET("challenge/{id}/comment/{no}")
    Call<QnAnComment> showComment(@Path("id") String id, @Path("no") String no);

    // 도전 의견에 댓글 달기
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
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("challenge/{id}/info")
    Call<Gson> modifyChallengeInfo(@Header("X-Access-Token") String jwt,
                                   @Path("id") String id,
                                   @Field("title") String title,
                                   @Field("description") String description,
                                   @Field("tags") String tags);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("challenge/{id}/fork")
    Call<Gson> forkChallenge(@Header("X-Access-Token") String jwt, @Path("id") String id);

    // 내 책 조회하기
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("book")
    Call<ArrayList<BookList>> getBook(@Header("X-Access-Token") String jwt);

    // 책 상세 보기      책 내용 부분 을 더 상의 필요 ------------ 김원준!!!!!
    @GET("book/{id}")
    Call<Gson> showBook(@Path("id") String id);

    /** Trending **/

    // 트렌딩 도전
    @GET("trending/challenge")
    Call<ArrayList<Challenge>> getTredingChallenge();

    // 트렌딩 책
    @GET("trending/book")
    Call<ArrayList<BookList>> getTredingBook();

    /** QnA **/

    // QnA들 가져오기
    @GET("qna")
    Call<ArrayList<QnAnCommentList>> getQnA();

    // QnA 작성
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("qna")
    Call<Gson> writeQnA(@Header("X-Access-Token") String jwt,
                        @Field("title") String title,
                        @Field("tags") String tags,
                        @Field("content") String content);

    // QnA 상세 보기
    @GET("qna/{id}")
    Call<QnAnComment> shpwQnA(@Path("id") String id);

    // QnA 에 댓글 달기
    @POST("qna/{id}")
    Call<Gson> addQnAComment(@Header("X-Access-Token") String jwt,
                             @Path("id") String id,
                             @Field("content") String content);

    // QnA 수정
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
}
