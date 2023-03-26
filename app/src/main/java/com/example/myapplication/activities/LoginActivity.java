package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.HomeFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        TextView t1 = (TextView) findViewById(R.id.textView24);
        t1.setOnClickListener(view->{
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        });

        Button b1 = (Button) findViewById(R.id.button8);
        b1.setOnClickListener(view->{
//            Intent intent = new Intent(this, HomeFragment.class);
//            startActivity(intent);
//            Bundle bundle = new Bundle();
            if(getSupportFragmentManager().getFragments()!=null && getSupportFragmentManager().getFragments().size()>0)
            {
                for(int i=0;i<getSupportFragmentManager().getFragments().size();i++)
                {
                    Fragment fragment = getSupportFragmentManager().getFragments().get(i);
                    if(fragment!=null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment firstFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.constraint1,firstFragment);
            fragmentTransaction.addToBackStack("fragmentStack");
            fragmentTransaction.commit();

            Bundle bundle = new Bundle();

            firstFragment.setArguments(bundle);

        });

    }
}