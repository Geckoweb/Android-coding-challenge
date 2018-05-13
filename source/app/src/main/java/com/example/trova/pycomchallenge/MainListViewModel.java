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
    private MutableLiveData<String> errorMessage;

    /**
     * getter for commits data that provide to create it if are not already instntiated
     * @return a LiveData with a list of GitEntry
     */
    public LiveData<List<GitEntry>> getCommits() {
        if (commits == null) {
            commits = new MutableLiveData<List<GitEntry>>();
            fetchData();
        }
        return commits;
    }

    /**
     * getter for waiting flag that provide to create it if are not already instntiated
     * @return a LiveData with the waiting flag
     */
    public LiveData<Boolean> isWaiting() {
        if (waiting == null){
            waiting = new MutableLiveData<>();
        }
        return waiting;
    }

    /**
     * getter for error that provide to create it if are not already instntiated
     * @return a LiveData with the error message to show
     */
    public LiveData<String> isInError() {
        if (errorMessage == null){
            errorMessage = new MutableLiveData<>();
        }
        return errorMessage;
    }

    /**
     * This private method is used to retrieve data fom internet.
     * it provade also to set live data for waiting flag and errorMessage
     */
    private void fetchData() {
        waiting.setValue(true);
        errorMessage.setValue(null);
        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                GitNetworkProvider networkProvider = new GitNetworkProvider();
                try {
                    commits.postValue(networkProvider.getDataFromGitHub());
                } catch (IOException e) {
                    e.printStackTrace();
                    commits = null;
                    errorMessage.postValue(e.getLocalizedMessage());
                }
                waiting.postValue(false);
            }
        });
        task.start();
    }
}
