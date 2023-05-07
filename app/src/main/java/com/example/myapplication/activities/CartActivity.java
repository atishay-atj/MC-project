package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();

    String uniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        SharedPreferences sharedPreferences = this.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");

        df.child("cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    if(snapshot.child(uniqueId).exists())
                    {
//
                        MyCartFragment fragment = new MyCartFragment(); // create an instance of your fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, fragment).commit();
                    }
                    else{
//
                        BlankFragment fragment = new BlankFragment(); // create an instance of your fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();

                    }
                }
                else{

                    BlankFragment fragment = new BlankFragment(); // create an instance of your fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
//                    Toast.makeText(CartActivity.this, "outside", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}