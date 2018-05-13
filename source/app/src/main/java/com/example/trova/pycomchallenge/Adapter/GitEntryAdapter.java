package com.example.trova.pycomchallenge.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trova.pycomchallenge.Model.GitEntry;
import com.example.trova.pycomchallenge.R;

import java.util.ArrayList;
import java.util.List;

public class GitEntryAdapter extends RecyclerView.Adapter<GitEntryViewHolder> {

    private List<GitEntry> gitEntryList;

    public GitEntryAdapter() {
        this.gitEntryList = new ArrayList<>();
    }

    public void setGitEntryList(List<GitEntry> gitEntryList) {
        this.gitEntryList = gitEntryList;
    }

    @NonNull
    @Override
    public GitEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mainView = inflater.inflate(R.layout.git_entry_layout, parent,false) ;
        return new GitEntryViewHolder(mainView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull GitEntryViewHolder holder, int position) {
        GitEntry entry = gitEntryList.get(position);

        holder.getAuthorNameTextView().setText(entry.getAuthorName());
        holder.getCommitIdTextView().setText(entry.getCommitId());
        holder.getCommitDescriptiontextView().setText(entry.getCommitMessage());
    }

    @Override
    public int getItemCount() {
        return gitEntryList.size();
    }
}
