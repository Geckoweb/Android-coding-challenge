package com.example.trova.pycomchallenge;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.trova.pycomchallenge.Adapter.GitEntryAdapter;
import com.example.trova.pycomchallenge.Model.GitEntry;

import java.util.List;

public class MainListActivity extends AppCompatActivity {

    private GitEntryAdapter adapter;
    private ProgressDialog progressDialog;
    private AlertDialog dialog;
    MainListViewModel viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        viewModel= ViewModelProviders.of(this).get(MainListViewModel.class);
        RecyclerView mainReciclerView = findViewById(R.id.main_recycler_view);
        mainReciclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GitEntryAdapter();
        mainReciclerView.setAdapter(adapter);
        initProrgess();
        initErrorDialog();
        fetchData();
    }

    private void initErrorDialog() {
        viewModel.isInError().observe(this,errorMessage ->{
            if (errorMessage!= null){
                showErrorMessage(errorMessage);
            }else{
                hideErrorMessage();
            }
        });
    }

    private void initProrgess() {
        viewModel.isWaiting().observe(this, waiting -> {
            if (waiting) {
                startProgress();
            } else {
                stopProgress();
            }
        });
    }

    private void fetchData() {
        viewModel.getCommits().observe(this, (List<GitEntry> gitCommits) -> {
            adapter.setGitEntryList(gitCommits);
            adapter.notifyDataSetChanged();
        });
    }


    private void startProgress(){
        if(progressDialog == null){
            buildProgress();
        }
        progressDialog.show();

    }

    private void stopProgress(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void buildProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Fetching data from GitHub");
        progressDialog.setIndeterminate(false);
    }

    private void showErrorMessage(String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Unable to retreive data from server.\n" +
                "message: "+message+"\n" +
                "Please check your internet connection")
                .setTitle("Error");

        builder.setPositiveButton("Retry", (dialog, id) -> {
            fetchData();
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> {
            dialog.dismiss();
        });

        dialog= builder.create();
        dialog.show();
    }


    private void hideErrorMessage() {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
