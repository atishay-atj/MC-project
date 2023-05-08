package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;
import com.example.myapplication.models.HomeHorModel;
import com.example.myapplication.models.HomeVerModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {
    //
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();


    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;
    LottieAnimationView stepCounterAnim;
    //
    String uniqueId;

    public HomeVerAdapter(Context context,ArrayList<HomeVerModel> list){
        this.context= context;
        this.list= list;
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String mName = list.get(position).getName();
        final String mPrice = list.get(position).getPrice();
//        if(mName=="Aalo Paratha")
//        {
//            final String mImage = String.valueOf(list.get(position).getImage());
//            try {
//                URL imageUrl = new URL(mImage);
//                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
//                conn.connect();
//                InputStream inputStream = conn.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                holder.imageView.setImageBitmap(bitmap);
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//        }
//        else {
            final int mImage = list.get(position).getImage();

//        }
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        df.child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
//                    Toast.makeText(context, itemSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                    String itemName = itemSnapshot.getKey();
                    if(itemName!=null) {
                        DataSnapshot availabilitySnapshot = itemSnapshot.child("available");

                        if (availabilitySnapshot.exists() && availabilitySnapshot.getValue() != null) {
                            int availability = availabilitySnapshot.getValue(Integer.class);
                            if (mName.equals(itemName) && availability == 0) {
                                holder.itemView.setEnabled(false);
                                holder.itemView.setAlpha(0.5f); // Set lower opacity
                                holder.price.setText("Unavailable");
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        HomeVerModel model = list.get(position);
//
//        holder.itemView.setEnabled(model.isEnabled());
//        holder.itemView.setClickable(model.isEnabled());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,null);
                ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                stepCounterAnim = sheetView.findViewById(R.id.stepCounterAnim);

                stepCounterAnim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Added to a Cart",Toast.LENGTH_SHORT).show();
                        df.child("cart").child(uniqueId).addListenerForSingleValueEvent(new ValueEventListener() {
                            String itemName = bottomName.getText().toString();

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    if (snapshot.child(itemName).exists()) {
                                        int itemCount = Integer.parseInt(snapshot.child(itemName).child("itemCount").getValue().toString());
//                            df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
                                        df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue(String.valueOf(itemCount + 1));
                                    } else {
                                        df.child("cart").child(uniqueId).child(itemName);
                                        df.child("cart").child(uniqueId).child(itemName).child("price").setValue(bottomPrice.getText().toString());
                                        df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue("1");
                                    }
                                } else {
                                    df.child("cart").child(uniqueId).child(itemName);
                                    df.child("cart").child(uniqueId).child(itemName).child("price").setValue(bottomPrice.getText().toString());
                                    df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue("1");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        bottomSheetDialog.dismiss();
                    }
                });
//                ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
//                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
//                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);


                bottomName.setText(mName);
                bottomPrice.setText(mPrice);
                bottomImg.setImageResource(mImage);
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        //        TextView name;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            imageView = itemview.findViewById(R.id.food1);
            name = itemview.findViewById(R.id.textView3);
            price = itemview.findViewById(R.id.textView7);
           // itemview.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            df.child("cart").child(uniqueId).addListenerForSingleValueEvent(new ValueEventListener() {
//                String itemName = name.getText().toString();
//
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        if (snapshot.child(itemName).exists()) {
//                            int itemCount = Integer.parseInt(snapshot.child(itemName).child("itemCount").getValue().toString());
////                            df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
//                            df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue(String.valueOf(itemCount + 1));
//                        } else {
//                            df.child("cart").child(uniqueId).child(itemName);
//                            df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
//                            df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue("1");
//                        }
//                    } else {
//                        df.child("cart").child(uniqueId).child(itemName);
//                        df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
//                        df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue("1");
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
    }
}