package com.example.trova.pycomchallenge.DataProvider;

import com.example.trova.pycomchallenge.Model.GitEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GitNetworkProvider {

    public static final String BASE_URL = "https://api.github.com/";
    public static final ObjectMapper mapper = new ObjectMapper();

    private GitNetworkProviderInterface apiService;

    public GitNetworkProvider() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
        apiService = retrofit.create(GitNetworkProviderInterface.class);
    }

    public void setApiService(GitNetworkProviderInterface apiService) {
        this.apiService = apiService;
    }

    public List<GitEntry> getDataFromGitHub() throws IOException{
        Call<List<GitEntry>> call = apiService.getCommits("rails", "rails");
        Response<List<GitEntry>> result = call.execute();
        if(result != null){
            return result.body();
        }
        return Collections.emptyList();
    }


}
