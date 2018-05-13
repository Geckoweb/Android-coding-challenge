package com.example.trova.pycomchallenge;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.trova.pycomchallenge.DataProvider.GitNetworkProvider;
import com.example.trova.pycomchallenge.Model.GitEntry;

import java.io.IOException;
import java.util.List;

public class MainListViewModel extends ViewModel {

    private MutableLiveData<List<GitEntry>> commits;
    private MutableLiveData<Boolean> waiting;

    public LiveData<List<GitEntry>> getCommits() {
        if (commits == null) {
            commits = new MutableLiveData<List<GitEntry>>();
            fetchData();
        }
        return commits;
    }

    public LiveData<Boolean> isWaiting() {
        if (waiting == null){
            waiting = new MutableLiveData<>();
        }
        return waiting;
    }

    private void fetchData() {
        waiting.setValue(true);
        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                GitNetworkProvider networkProvider = new GitNetworkProvider();
                try {
                    commits.postValue(networkProvider.getDataFromGitHub());

                } catch (IOException e) {
                    e.printStackTrace();
                    // notify user network issues
                }
                waiting.postValue(false);
            }
        });
        task.start();
    }
}
