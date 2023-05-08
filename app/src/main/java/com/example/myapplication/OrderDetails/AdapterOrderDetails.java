package com.example.myapplication.OrderDetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.HomeHorAdapter;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class  AdapterOrderDetails extends RecyclerView.Adapter<AdapterOrderDetails.ViewHolder> {

    private ArrayList<OrderModel> phoneCardList;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();


    public AdapterOrderDetails(ArrayList<OrderModel> values, OrderActivity orderActivity) {
        this.phoneCardList=values;
//        for(int i=0;i<phoneCardList.size();i++)
//        {
//            Toast.makeText(orderActivity, phoneCardList.get(i).getPhoneNumber(), Toast.LENGTH_SHORT).show();
//        }
    }

    @NonNull
    @Override
    public AdapterOrderDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetailcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrderDetails.ViewHolder holder, int position) {
        OrderModel phoneCard = phoneCardList.get(position);
        holder.tv11.setText("Order");

        // Set phone number
        holder.phoneNumberTextView.setText(phoneCard.getPhoneNumber());

        // Set ListView adapter

        List<Item> itemList = phoneCard.getItemList(); // Access the list from OrderModel
        if (itemList != null && !itemList.isEmpty()) {
            ListViewAdapter listViewAdapter = new ListViewAdapter(holder.itemView.getContext(), (ArrayList<Item>) itemList);
            holder.listView.setAdapter(listViewAdapter);
        } else {
            // Handle empty or null list case
            // For example, you can set an empty adapter or hide the ListView
            holder.listView.setAdapter(null);
            // or
            holder.listView.setVisibility(View.GONE);
        }

        holder.completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference()
                        .child("orderDetail").child(phoneCard.getPhoneNumber());
                myRef.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        int a=phoneCardList.size();
        return phoneCardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView phoneNumberTextView;
        private ListView listView;

        private TextView tv11;

        Button notify,completed;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneNumberTextView = itemView.findViewById(R.id.textView5);
            listView = itemView.findViewById(R.id.listView);
            tv11 = itemView.findViewById(R.id.tv11);
            completed=itemView.findViewById(R.id.button6);



        }
    }
}