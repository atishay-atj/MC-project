package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.Adapters.FeedbackAdapter;
import com.example.myapplication.R;
import com.example.myapplication.models.Feedback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedbacksActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFeedbacks;
    private FeedbackAdapter adapter;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        // Get a reference to the "feedbacks" node in your Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerViewFeedbacks = findViewById(R.id.recycler_view_feedbacks);
        // Add a listener to fetch the feedback items from Firebase and update the RecyclerView
//        databaseReference.child("feedbacks").addListenerForSingleValueEvent(new ValueEventListener(){
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                List<Feedback> feedbackItems = new ArrayList<>();
////                for (DataSnapshot feedbackSnapshot : dataSnapshot.getChildren()) {
////                    Feedback feedbackItem = feedbackSnapshot.getValue(Feedback.class);
////                    feedbackItems.add(feedbackItem);
////                }
////                adapter.setFeedbackItems(feedbackItems);
////            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("FeedbacksActivity", "Error fetching feedbacks", databaseError.toException());
//            }
//        });
        adapter = new FeedbackAdapter(getFeedbackItems()); // If has to make static
        recyclerViewFeedbacks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFeedbacks.setAdapter(adapter);
    }



    private List<Feedback> getFeedbackItems() {
        // TODO: Implement a method to fetch feedback items from a data source

        // For now, we'll just return some dummy data
        List<Feedback> feedbackItems = new ArrayList<>();
        feedbackItems.add(new Feedback("9854367545", "SANDWICH", 3.5f, "It was too dry"));
        feedbackItems.add(new Feedback("8875463345", "NOODLES", 4.0f, "taste and quantity is good"));
        feedbackItems.add(new Feedback("9987886211", "ALOO PARATHA", 5.0f, "perfectly cooked"));
        return feedbackItems;
    }
}
