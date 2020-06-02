package com.booreum.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class GitHubServiceProvider {

    public static GitHubService retrofit = providerGithubService();

    public static GitHubService providerGithubService()
    {
        return new Retrofit.Builder()
            .baseUrl("http://www.booreum.com:3001")
            .addConverterFactory(GsonConverterFactory.create())
            .build() .create(GitHubService.class);
    }
}
