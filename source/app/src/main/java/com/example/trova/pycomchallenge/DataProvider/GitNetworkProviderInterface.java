package com.example.trova.pycomchallenge.DataProvider;

import com.example.trova.pycomchallenge.Model.GitEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitNetworkProviderInterface {
    @GET("/repos/{owner}/{repo}/commits")
    Call<List<GitEntry>> getCommits (@Path("owner") String owner, @Path("repo") String repo );
}
