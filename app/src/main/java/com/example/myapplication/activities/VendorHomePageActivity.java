package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class VendorHomePageActivity extends AppCompatActivity {
    Button open_menu;
    Button curr_orders;
    Button Payment;
    Button feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_page);
        open_menu = findViewById(R.id.button_edit_menu);
        curr_orders = findViewById(R.id.current_orders);
        Payment = findViewById(R.id.payment_manage);
        feedback = findViewById(R.id.vendor_feedbacks);

        Bundle bundle = new Bundle();

        open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_ema = new Intent(VendorHomePageActivity.this, EditMenuActivity.class);
                startActivity(intent_ema);

               /* FragmentManager vfragmentManager = getSupportFragmentManager();
                FragmentTransaction vfragmentTransaction = vfragmentManager.beginTransaction();
                VendorHomeFragment firstVendorFragment = new VendorHomeFragment();
                vfragmentTransaction.replace(R.id.constraint2, firstVendorFragment);

                vfragmentTransaction.addToBackStack("vfragmentStack");
                vfragmentTransaction.commit();
                firstVendorFragment.setArguments(bundle);*/
                /*open_menu.setVisibility(View.GONE);
                curr_orders.setVisibility(View.GONE);
                Payment.setVisibility(View.GONE);
                feedback.setVisibility(View.GONE);*/

            }
        });

        curr_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                open_menu.setVisibility(View.GONE);
                curr_orders.setVisibility(View.GONE);
                Payment.setVisibility(View.GONE);
                feedback.setVisibility(View.GONE);
            }
        });
        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent_ada = new Intent(VendorHomePageActivity.this, AddBalanceActivity.class);
                startActivity(intent_ada);

                open_menu.setVisibility(View.GONE);
                curr_orders.setVisibility(View.GONE);
                Payment.setVisibility(View.GONE);
                feedback.setVisibility(View.GONE);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent feedbackintent = new Intent(VendorHomePageActivity.this, FeedbacksActivity.class);
                startActivity(feedbackintent);
                open_menu.setVisibility(View.GONE);
                curr_orders.setVisibility(View.GONE);
                Payment.setVisibility(View.GONE);
                feedback.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void onBackPressed() {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

    }
}