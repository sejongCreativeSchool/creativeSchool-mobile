package com.booreum.Constant;

import com.booreum.model.Errand;
import com.booreum.model.ErrandResults;
import com.booreum.model.User;
import com.booreum.model.UserResult;
import com.booreum.model.UserResults;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GitHubService {

    //심부름 목록 호출
    @GET("/v1/errand")
    Call<ErrandResults> loadErrands();

    //심부름 등록하기
    @POST("/v1/auth/user")
    Call<Errand> uploadErrand(@Body Errand errand);

    //개별유저 불러오기
    @GET("/v1/auth/user/{accessToken}")
    Call<UserResult> loadUser(@Path("accessToken") String accessToken);

    //유저 불러오기
    @GET("/v1/auth/users")
    Call<UserResults> loadUsers();

    //유저 추가하기
    @POST("/v1/auth/user")
    Call<User> createUser(@Body User user);
}
