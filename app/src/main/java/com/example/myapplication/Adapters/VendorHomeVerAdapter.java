package com.example.myapplication.Adapters;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.AddBalanceActivity;
import com.example.myapplication.models.VendorHomeVerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VendorHomeVerAdapter extends RecyclerView.Adapter<VendorHomeVerAdapter.ViewHolder> {
    public DatabaseReference databaseReference;
    Context context;
    ArrayList<VendorHomeVerModel> list;
    private List<Boolean> toggleButtonStates = new ArrayList<>();
    private SparseBooleanArray switchStates = new SparseBooleanArray();


    public VendorHomeVerAdapter(Context context, ArrayList<VendorHomeVerModel> list){
        this.context= context;
        this.list= list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_vertical_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String itemName = list.get(position).getName();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(itemName);
        if(itemName.equals("Aalo Paratha")) { // remove this If block when available field is added for all items
            databaseReference.child("items").child(itemName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        int available = snapshot.child("available").getValue(Integer.class); //snapshot.getValue(Integer.class);
                        if(available==1)
                            holder.availibility.setChecked(true);
                        else
                            holder.availibility.setChecked(false);

                    } else {
                        //Toast.makeText(buttonView.this, "Account not found", Toast.LENGTH_SHORT).show();
                        holder.availibility.setChecked(list.get(position).getavailibility());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.availibility.setChecked(list.get(position).getavailibility());
        }


        /*holder.availibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleButtonStates.set(position, isChecked);
            }
        });*/

        // Save the state of the switch to SharedPreferences
        /*holder.availibility.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                // add the item in menu

                Toast.makeText(buttonView.getContext(), "Item is avalable now", Toast.LENGTH_SHORT).show();
            }
            else{
                // remove/disable the item from menu
                Toast.makeText(buttonView.getContext(), "Item is not avalable now", Toast.LENGTH_SHORT).show();
            }
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(buttonView.getContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("switch_state_" + position, isChecked);
            editor.apply();
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /*public void restoreSwitchStates() {
        // Retrieve the switch states from SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        for (int i = 0; i < list.size(); i++) {
            boolean switchState = prefs.getBoolean("switch_state_" + i, false);
            switchStates.put(i, switchState);
        }
    }*/


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView name;
        ToggleButton availibility;
//        TextView name;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            imageView = itemview.findViewById(R.id.food1);
            name = itemview.findViewById(R.id.textView3);
            availibility = itemview.findViewById((R.id.availibility));
            availibility.setOnClickListener((View.OnClickListener) this);
            //String itemName = (String) name.getText();

            /* availibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("adarsh","Update Menu--> setting value of item");
                    if(isChecked){
                        // add the item in menu
                        databaseReference.child("items").child(itemName).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Log.d("adarsh","Update Menu--> onDataChange for item :"+itemName);
                                if (snapshot.exists()) {
                                    Log.d("adarsh","Update Menu--> setting value to 1");
                                    databaseReference.child("items").child(itemName).child("available").setValue(1);

                                } else {
                                    //Toast.makeText(buttonView.this, "Account not found", Toast.LENGTH_SHORT).show();
                                    //holder.availibility.setChecked(list.get(position).getavailibility());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Toast.makeText(buttonView.getContext(), "Item is avalable now", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        // remove/disable the item from menu
                        databaseReference.child("items").child(itemName).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Log.d("adarsh","Update Menu--> setting value to 0");
                                    databaseReference.child("items").child(itemName).child("available").setValue(0);

                                } else {
                                    //Toast.makeText(buttonView.this, "Account not found", Toast.LENGTH_SHORT).show();
                                    //holder.availibility.setChecked(list.get(position).getavailibility());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(buttonView.getContext(), "Item is not avalable now", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/


        }

        @Override
        public void onClick(View view) {

            String itemName=name.getText().toString();
            if(availibility.isChecked()){
                databaseReference.child("items").child(itemName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("adarsh","Update Menu--> onDataChange for item :"+itemName);
                        if (snapshot.exists()) {
                            Log.d("adarsh","Update Menu--> setting value to 1");
                            databaseReference.child("items").child(itemName).child("available").setValue(1);

                        } else {
                            //Toast.makeText(buttonView.this, "Account not found", Toast.LENGTH_SHORT).show();
                            //holder.availibility.setChecked(list.get(position).getavailibility());
                            Log.d("adarsh","Update Menu--> snapshot does not exist");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                databaseReference.child("items").child(itemName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.d("adarsh","Update Menu--> setting value to 0");
                            databaseReference.child("items").child(itemName).child("available").setValue(0);

                        } else {
                            //Toast.makeText(buttonView.this, "Account not found", Toast.LENGTH_SHORT).show();
                            //holder.availibility.setChecked(list.get(position).getavailibility());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

}
