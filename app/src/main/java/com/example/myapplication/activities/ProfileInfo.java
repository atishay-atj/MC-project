//package com.example.myapplication.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import com.example.myapplication.R;
//
//public class  ProfileInfo extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile_info);
//    }
//}
package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;


public class ProfileInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        ImageView iv;

        Button b1,b2;
        b1 = (Button)findViewById(R.id.button22);
        b2 = (Button)findViewById(R.id.button2);
        iv=findViewById(R.id.burger);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MyCartFragment firstFragment = new MyCartFragment();
                fragmentTransaction.replace(R.id.profile_layout,firstFragment);
                fragmentTransaction.addToBackStack("fragmentStack");
                fragmentTransaction.commit();

//            Bundle bundle = new Bundle();

                firstFragment.setArguments(bundle);
                b1.setVisibility(View.GONE);
                b2.setVisibility(View.GONE);

//                startActivity(new Intent(this, MyCartFragment.class));
            }
        });
    }
}