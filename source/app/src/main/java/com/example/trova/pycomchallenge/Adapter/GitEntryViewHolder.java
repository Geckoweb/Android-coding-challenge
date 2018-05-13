package com.example.trova.pycomchallenge.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.trova.pycomchallenge.R;

/**
 * simple class used as an holder to recycle components
 */
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

    /**
     * Getter method
     * @return a reference to a TextView for author name
     */
    public TextView getAuthorNameTextView() {
        return authorNameTextView;
    }

    /**
     * Getter method
     * @return a reference to a TextView for commit sha
     */
    public TextView getCommitIdTextView() {
        return commitIdTextView;
    }

    /**
     * Getter method
     * @return a reference to a TextView for description message
     */
    public TextView getCommitDescriptiontextView() {
        return commitDescriptiontextView;
    }
}
