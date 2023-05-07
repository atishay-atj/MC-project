package com.example.myapplication.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adapters.CartAdapter;
import com.example.myapplication.R;
import com.example.myapplication.models.CartModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class MyCartFragment extends Fragment {
    List<CartModel> list;
    CartAdapter cartAdapter;
    RecyclerView rec;

    TextView price;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();
    String uniqueId;
//
//    TextView display;

    Button checkOut;

    int total=0;
    public MyCartFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        price=view.findViewById(R.id.textView6);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");
        list = new ArrayList<>();
        rec = view.findViewById(R.id.recyclerView);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        df.child("cart").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot uniqueIdSnapshot : snapshot.child(uniqueId).getChildren()) {
                    String id=uniqueIdSnapshot.getKey();
                    String count = uniqueIdSnapshot.child("itemCount").getValue(String.class);
                    String priceWithPrefix = uniqueIdSnapshot.child("price").getValue(String.class);
                    String price = priceWithPrefix.substring(3);
                    String temp=String.valueOf(Integer.parseInt(count) * Integer.parseInt(price));
                    total += Integer.parseInt(count) * Integer.parseInt(price);
                    list.add(new CartModel(R.drawable.fresh_lime_soda,id,price+"x"+count,temp));
                }
                price.setText(""+total+" Rs");
                cartAdapter = new CartAdapter(list);
                rec.setAdapter(cartAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Inflate the layout for this fragment
//        rec = view.findViewById(R.id.recyclerView);
//        rec.setLayoutManager(new LinearLayoutManager(getContext()));


//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
//        cartAdapter = new CartAdapter(list);
//        rec.setAdapter(cartAdapter);



        //     checkOut button
        checkOut=view.findViewById(R.id.button);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PaymentActivity.class);
                intent.putExtra("amount",total);
                startActivity(intent);
            }
        });



        return view;
    }
}