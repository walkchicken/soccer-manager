package com.example.loginregister.activity.loginregister.Clubs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("get_player.php")
    Call<List<Clubs>> getClubs();

    @FormUrlEncoded
    @POST("add_player.php")
    Call<Clubs> insertClub(
            @Field("key") String key,
            @Field("name") String name,
            @Field("weight") String weight,
            @Field("height") String height,
            @Field("position") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_player.php")
    Call<Clubs> updateClub(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("weight") String weight,
            @Field("height") String height,
            @Field("position") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_player.php")
    Call<Clubs> deleteClub(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Clubs> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

}
