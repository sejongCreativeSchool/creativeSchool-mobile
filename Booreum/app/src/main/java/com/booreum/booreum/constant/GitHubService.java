package com.booreum.booreum.constant;

import com.booreum.booreum.model.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GitHubService {
    public final static String BASE_URL = "https://api.booreum.com";
    @GET("/corona19-masks/v1/storesByGeo/json")
    Call<User> getStoresByGeo();

    @GET("/v1/errand")
    Call<User> listErrand();

    /**
     * 주소 어케할겨
     */
    @GET("/v1/errand")
    Call<User> infoErrand();

    @POST("/v1/errand/")
    Call<User> updateErrand();

    /**
     * 심부름 수정 관련 메소
     * @return
     */

    @DELETE("/v1/errand/")
    Call<User> deleteErrand();

    @POST("/v1/user")
    Call<User> createUser();
}
