package com.example.myapplication.OrderDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();


    ArrayList<OrderModel> values=new ArrayList<>();
    ArrayList<Item> arr=new ArrayList<>() ;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        recyclerView = findViewById(R.id.rv10);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        df.child("orderDetail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot idSnapshot : snapshot.getChildren()) {
                    String id = idSnapshot.getKey();
                    for (DataSnapshot itemSnapshot : idSnapshot.getChildren()) {

                        String quantity=itemSnapshot.child("itemCount").getValue(String.class);

                        Item item1=new Item(""+itemSnapshot.getKey(),Integer.parseInt(quantity));
                        arr.add(item1);
                    }
                    OrderModel o1=new OrderModel(id,arr);
                    values.add(o1);

                }

                AdapterOrderDetails adapter = new AdapterOrderDetails(values,OrderActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
//        Item item1=new Item("Aalo paratha",2);
//        Item item2=new Item("Paneer paratha",1);
//        Item item3=new Item("Mixed paratha",1);
////        arr.add(item1);
//        arr.add(item2);
//        arr.add(item3);
////        OrderModel o1=new OrderModel(""+9877,arr);
//        OrderModel o2=new OrderModel(""+9555,arr);
////
////        values.add(o1);
//        values.add(o2);
//
//        for(int i=0;i<values.size();i++)
//        {
//            System.out.println(values.get(i).getPhoneNumber());
//        }


//        recyclerView = findViewById(R.id.rv10);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        AdapterOrderDetails adapter = new AdapterOrderDetails(values,this);
//        recyclerView.setAdapter(adapter);

    }
}