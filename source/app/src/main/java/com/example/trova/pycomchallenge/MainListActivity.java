package com.example.trova.pycomchallenge;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.trova.pycomchallenge.Adapter.GitEntryAdapter;
import com.example.trova.pycomchallenge.DataProvider.GitNetworkProvider;

import java.io.IOException;

public class MainListActivity extends AppCompatActivity {

    private GitEntryAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        RecyclerView mainReciclerView = findViewById(R.id.main_recycler_view);
        mainReciclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GitEntryAdapter();
        mainReciclerView.setAdapter(adapter);
        fetchData();
    }

    private void fetchData() {
        AsyncTask task = new AsyncTask() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                startProgress();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                GitNetworkProvider networkProvider = new GitNetworkProvider();
                try {
                    adapter.setGitEntryList(networkProvider.getDataFromGitHub());
                } catch (IOException e) {
                    // notify user network issue
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                adapter.notifyDataSetChanged();
                stopProgress();
            }
        };

        task.execute();
    }

    private void startProgress(){

        if(progressDialog == null){
            buildProgress();
        }
        progressDialog.show();

    }

    private void stopProgress(){
        progressDialog.dismiss();
    }

    private void buildProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Fetching data from GitHub");
        progressDialog.setIndeterminate(false);
    }
}
