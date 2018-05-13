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

    /**
     * Simple constructor that init a data source with an empty list
     */
    public GitEntryAdapter() {
        this.gitEntryList = new ArrayList<>();
    }

    /**
     * Setter for the data source
     * @param gitEntryList data source
     */
    public void setGitEntryList(List<GitEntry> gitEntryList) {
        this.gitEntryList = gitEntryList;
    }


    /**
     * Method called from system when need to create a new view holder to be filled
     * @param parent parent view where inflate git_entry_layout
     * @param viewType
     * @return an empty GitEntryViewHolder
     */
    @NonNull
    @Override
    public GitEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mainView = inflater.inflate(R.layout.git_entry_layout, parent,false) ;
        return new GitEntryViewHolder(mainView) ;
    }

    /**
     * Method called from system when have a GitEntryViewHolder ready to fill
     * @param holder GitEntryViewHolder to be filled
     * @param position the position in the list of data source where gets information
     */
    @Override
    public void onBindViewHolder(@NonNull GitEntryViewHolder holder, int position) {
        GitEntry entry = gitEntryList.get(position);

        holder.getAuthorNameTextView().setText(entry.getAuthorName());
        holder.getCommitIdTextView().setText(entry.getCommitId());
        holder.getCommitDescriptiontextView().setText(entry.getCommitMessage());
    }

    /**
     * Method called from the system to know how many data are currently present
     * @return
     */
    @Override
    public int getItemCount() {
        return gitEntryList.size();
    }
}
