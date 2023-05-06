package com.example.myapplication.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class AddBalanceActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private EditText phoneNumberEditText;
    private EditText amountEditText;
    private TextView totalBalanceTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);


        databaseReference = FirebaseDatabase.getInstance().getReference();

        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        amountEditText = findViewById(R.id.amount_edit_text);
        totalBalanceTextView = findViewById(R.id.total_balance_text_view);

        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                if (!phoneNumber.isEmpty()) {
                    databaseReference.child("users").child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                amountEditText.setVisibility(View.VISIBLE);
                                totalBalanceTextView.setVisibility(View.VISIBLE);
                                String balance_str = snapshot.child("balance").getValue(String.class); //snapshot.getValue(Integer.class);
                                int balance = Integer.parseInt(balance_str);
                                totalBalanceTextView.setText("Total balance: " + balance);
                            } else {
                                Toast.makeText(AddBalanceActivity.this, "Account not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        Button submitButton = findViewById(R.id.submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                String amountString = amountEditText.getText().toString();
                if (!phoneNumber.isEmpty() && !amountString.isEmpty()) {
                    int amount = Integer.parseInt(amountString);
                    /*databaseReference.child("users").child(phoneNumber).runTransaction(new Transaction.Handler() {
                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                            String balance_str = mutableData.child("users").child(phoneNumber).child("balance").getValue(String.class);
                            Log.d("adarsh", "ABA balance_str = "+balance_str);
                            //String balance_str = snapshot.child("balance").getValue(String.class); //snapshot.getValue(Integer.class);
                            if (balance_str == null) {
                                return Transaction.abort();
                            }
                            int balance = Integer.parseInt(balance_str);
                            Log.d("adarsh", "ABA balance_str+amount = "+(balance_str+amount));
                            mutableData.setValue(Integer.toString(balance + amount));
                            Log.d("adarsh", "ABA value set ");
                            return Transaction.success(mutableData);
                        }*/
                    databaseReference.child("users").child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot snapshot){


                        String balanceStr = snapshot.child("balance").getValue(String.class);
                        int balance = Integer.parseInt(balanceStr);
                        balanceStr = Integer.toString(balance + amount);
                        //databaseReference.child("balance").setValue(balanceStr);
                        databaseReference.child("users").child(phoneNumber).child("balance").setValue(balanceStr);

                        totalBalanceTextView.setText("Total balance: " + balanceStr);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(AddBalanceActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                    }
                });

                }
            }
        });



    }
}