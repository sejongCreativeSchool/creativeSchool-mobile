package com.booreum.constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class GitHubServiceProvider {
    public static GitHubService providerGithubService()
    {
        return new Retrofit.Builder()
            .baseUrl("http://booreum.com:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build() .create(GitHubService.class);
    }
}
