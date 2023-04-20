package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapters.CartAdapter;
import com.example.myapplication.models.CartModel;

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


    public MyCartFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        rec = view.findViewById(R.id.recyclerView);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        list.add(new CartModel(R.drawable.fresh_lime_soda,"Order1","30"));
        cartAdapter = new CartAdapter(list);
        rec.setAdapter(cartAdapter);

        return view;
    }
}