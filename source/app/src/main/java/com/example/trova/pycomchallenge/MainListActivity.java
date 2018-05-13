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

    /**
     * This private method is used to make errorMessage in viewModel, be observed from this activity
     */
    private void initErrorDialog() {
        viewModel.isInError().observe(this,errorMessage ->{
            if (errorMessage!= null){
                // if there are message to show
                showErrorMessage(errorMessage);
            }else{
                //hide AlertDialog
                hideErrorMessage();
            }
        });
    }

    /**
     * This private method is used to make errorMessage in viewModel, be observed from this activity
     */
    private void initProrgess() {
        viewModel.isWaiting().observe(this, waiting -> {
            if (waiting) {
                startProgress();
            } else {
                stopProgress();
            }
        });
    }

    /**
     * This private method is used to make gitCommits liveData in viewModel, be observed from this activity
     * and trigger a rest call in case the data are not already in memory
     */
    private void fetchData() {
        viewModel.getCommits().observe(this, (List<GitEntry> gitCommits) -> {
            adapter.setGitEntryList(gitCommits);
            adapter.notifyDataSetChanged();
        });
    }


    /**
     * method used to start a progress dialog with a spinner
     */
    private void startProgress(){
        if(progressDialog == null){
            buildProgress();
        }
        progressDialog.show();

    }

    /**
     * method used to dismiss the progress dialog of is non already dismissed
     */
    private void stopProgress(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    /**
     * method used to build a new progress dialog wit a spinner and a message
     */
    private void buildProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getString(R.string.fetching_data));
        progressDialog.setIndeterminate(false);
    }

    /**
     * method used to create an alertDialog with 2 buttons (retry cancel) an a message
     * @param message Custom message to be showed
     */
    private void showErrorMessage(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.default_error_message, message))
                .setTitle(R.string.error);

        builder.setPositiveButton(R.string.retry, (dialog, id) -> {
            fetchData();
            dialog.dismiss();
        });

        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            dialog.dismiss();
        });

        dialog= builder.create();
        dialog.show();
    }


    /**
     * this private method is used to dismiss the error dialog if is showed
     */
    private void hideErrorMessage() {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
