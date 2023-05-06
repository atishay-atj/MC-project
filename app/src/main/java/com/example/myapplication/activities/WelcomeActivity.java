package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        Button b1 = (Button) findViewById(R.id.placeorder);
        b1.setOnClickListener(view->{
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        TextView t1 = (TextView) findViewById(R.id.textView2);
        t1.setOnClickListener(view->{
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);
        });
    }

}