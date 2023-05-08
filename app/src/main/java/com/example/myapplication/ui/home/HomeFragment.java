package com.example.myapplication.ui.home;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.HomeHorAdapter;
import com.example.myapplication.Adapters.HomeVerAdapter;
import com.example.myapplication.Adapters.UpdateVertical;
import com.example.myapplication.R;
import com.example.myapplication.activities.ProfileActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.HomeHorModel;
import com.example.myapplication.models.HomeVerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVertical {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();


    ImageView profile;
    RecyclerView homeHorizontalRec,homeVerticalRec;
    private SearchView searchView;
    ArrayList<HomeHorModel> homeHorModellist;
    HomeHorAdapter homeHorAdapter;
    ArrayList<HomeVerModel> homeVerModellist;
    HomeVerAdapter homeVerAdapter;
    private FragmentHomeBinding binding;
    TextView welcome;
    SearchView sv;
    String uniqueId;
    String fullName="";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root  = inflater.inflate(R.layout.fragment_home,container,false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");
        welcome=root.findViewById(R.id.textView10);
//        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filter(newText);
//                return true;
//            }
//        });
        df.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    fullName = snapshot.child(uniqueId).child("fullname").getValue(String.class);
                    welcome.setText("Welcome.."+fullName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        homeHorizontalRec= root.findViewById(R.id.horizontal_home1);
        homeVerticalRec= root.findViewById(R.id.vertical_home1);
//        welcome=root.findViewById(R.id.textView10);
//        welcome.setText("Welcome.."+fullName);

//        searchView = root.findViewById(R.id.editTextTextPersonName4);

        profile=root.findViewById(R.id.profileViewer);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        //horizontal Recycler view
        homeHorModellist = new ArrayList<>();
        homeHorModellist.add(new HomeHorModel(R.drawable.restaurant,"Canteen"));
        homeHorModellist.add(new HomeHorModel(R.drawable.sign,"BruBakes"));
        homeHorModellist.add(new HomeHorModel(R.drawable.dosa_icn,"Dosa"));


        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModellist);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        homeVerModellist = new ArrayList<>();
//        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_sandwich,"Paneer Sandwich","Rs.60"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_sandwich,"Aalo sandwich","Rs.40"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.butter_maggi,"butter maggi","Rs.35"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.veg_maggi,"Veg maggi","Rs.35"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.egg_maggi,"egg maggi","Rs.40"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.plain_maggi,"Plain Maggi","Rs.25"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.mixed_paratha,"Mixed paratha","Rs.30"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_paratha,"Aalo paratha","Rs.30"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_paratha,"paneer paratha","Rs.40"));

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModellist);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
//        homeVerticalRec.setHasFixedSize(true);
//        homeVerticalRec.setNestedScrollingEnabled(false);

        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void callback(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }

//    public void filter(String text)
//    {
//        if(text!=null)
//        {
//            homeHorAdapter.setfilter(text);
//        }
//        else {
//            System.out.println("hello");
//        }
//    }
}