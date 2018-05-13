package com.example.trova.pycomchallenge.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.trova.pycomchallenge.R;

public class GitEntryViewHolder extends RecyclerView.ViewHolder {

    private TextView authorNameTextView;
    private TextView commitIdTextView;
    private TextView commitDescriptiontextView;

    public GitEntryViewHolder(View itemView) {
        super(itemView);
        authorNameTextView = itemView.findViewById(R.id.author_name);
        commitIdTextView = itemView.findViewById(R.id.commit_id);
        commitDescriptiontextView = itemView.findViewById(R.id.commit_description);
    }

    public TextView getAuthorNameTextView() {
        return authorNameTextView;
    }

    public TextView getCommitIdTextView() {
        return commitIdTextView;
    }

    public TextView getCommitDescriptiontextView() {
        return commitDescriptiontextView;
    }
}
