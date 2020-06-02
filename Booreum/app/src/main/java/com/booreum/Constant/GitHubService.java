package com.booreum.Constant;

import com.booreum.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GitHubService {

    //심부름 목록 호출
    @GET("/v1/errand")
    Call<User> listErrand();

    //유저 불러오기
    @GET("/v1/auth/users")
    Call<User> loadUser();

    //유저 추가하기
    @POST("/v1/auth/user")
    Call<User> createUser(@Body User user);
}
