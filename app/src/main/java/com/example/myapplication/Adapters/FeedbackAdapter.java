package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Feedback;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackViewHolder> {

    private List<Feedback> feedbackItems;

    public FeedbackAdapter(List<Feedback> feedbackItems) {
        this.feedbackItems = feedbackItems;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        Feedback feedbackItem = feedbackItems.get(position);
        holder.textViewCustomerId.setText("Customer ID: " + feedbackItem.getCustomerId());
        holder.textViewItemName.setText("Item Name: " + feedbackItem.getItemName());
        holder.ratingBar.setRating(feedbackItem.getRating());
        holder.textViewFeedback.setText(feedbackItem.getFeedback());
    }

    @Override
    public int getItemCount() {
        return feedbackItems.size();
    }

    public void setFeedbackItems(List<Feedback> feedbackItems) {
        this.feedbackItems = feedbackItems;
        notifyDataSetChanged();
    }
}

