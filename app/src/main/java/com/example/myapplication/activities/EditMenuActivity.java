package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.VendorHomeFragment;

public class EditMenuActivity extends AppCompatActivity {

    Boolean frag_showing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Bundle bundle = new Bundle();
         FragmentManager vfragmentManager = getSupportFragmentManager();
         FragmentTransaction vfragmentTransaction = vfragmentManager.beginTransaction();
         VendorHomeFragment firstVendorFragment = new VendorHomeFragment();
         vfragmentTransaction.replace(R.id.constraint2, firstVendorFragment);

         //vfragmentTransaction.addToBackStack("vfragmentStack");
         vfragmentTransaction.commit();
         firstVendorFragment.setArguments(bundle);
         frag_showing = true;
    }

    @Override
    public void onBackPressed() {
        if(frag_showing){
            Intent intent_vhp = new Intent(EditMenuActivity.this,VendorHomePageActivity.class);
            startActivity(intent_vhp);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}