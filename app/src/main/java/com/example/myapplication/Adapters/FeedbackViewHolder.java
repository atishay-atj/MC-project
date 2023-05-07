package com.example.myapplication.Adapters;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class FeedbackViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewCustomerId;
    public TextView textViewItemName;
    public RatingBar ratingBar;
    public TextView textViewFeedback;

    public FeedbackViewHolder(View itemView) {
        super(itemView);
        textViewCustomerId = itemView.findViewById(R.id.text_view_customer_id);
        textViewItemName = itemView.findViewById(R.id.text_view_item_name);
        ratingBar = itemView.findViewById(R.id.rating_bar);
        textViewFeedback = itemView.findViewById(R.id.text_view_feedback);
    }

}
