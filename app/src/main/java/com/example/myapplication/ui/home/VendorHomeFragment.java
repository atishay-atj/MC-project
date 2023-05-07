package com.example.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.VendorHomeHorAdapter;
import com.example.myapplication.Adapters.VendorHomeVerAdapter;
import com.example.myapplication.Adapters.VendorUpdateVertical;
import com.example.myapplication.R;
import com.example.myapplication.activities.ProfileActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.VendorHomeHorModel;
import com.example.myapplication.models.VendorHomeVerModel;

import java.util.ArrayList;

public class VendorHomeFragment extends Fragment implements VendorUpdateVertical {
    ImageView profile;
    RecyclerView vhomeHorizontalRec,vhomeVerticalRec;
    ArrayList<VendorHomeHorModel> homeHorModellist;
    VendorHomeHorAdapter vhomeHorAdapter;
    ArrayList<VendorHomeVerModel> vhomeVerModellist;
    VendorHomeVerAdapter vhomeVerAdapter;
    private FragmentHomeBinding binding;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root  = inflater.inflate(R.layout.vendor_fragment_home,container,false);
        vhomeHorizontalRec= root.findViewById(R.id.horizontal_home1);
        vhomeVerticalRec= root.findViewById(R.id.vertical_home1);
        profile=root.findViewById(R.id.profileViewer);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        //horizontal Recycler view
        homeHorModellist = new ArrayList<>();
        homeHorModellist.add(new VendorHomeHorModel(R.drawable.restaurant,"Canteen"));
        homeHorModellist.add(new VendorHomeHorModel(R.drawable.sign,"BruBakes"));
        homeHorModellist.add(new VendorHomeHorModel(R.drawable.dosa_icn,"Dosa"));


        vhomeHorAdapter = new VendorHomeHorAdapter(this,getActivity(),homeHorModellist);
        vhomeHorizontalRec.setAdapter(vhomeHorAdapter);
        vhomeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        vhomeHorizontalRec.setHasFixedSize(true);
        vhomeHorizontalRec.setNestedScrollingEnabled(false);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        vhomeVerModellist = new ArrayList<>();
//        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_sandwich,"Paneer Sandwich","Rs.60"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_sandwich,"Aalo sandwich","Rs.40"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.butter_maggi,"butter maggi","Rs.35"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.veg_maggi,"Veg maggi","Rs.35"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.egg_maggi,"egg maggi","Rs.40"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.plain_maggi,"Plain Maggi","Rs.25"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.mixed_paratha,"Mixed paratha","Rs.30"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_paratha,"Aalo paratha","Rs.30"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_paratha,"paneer paratha","Rs.40"));

        vhomeVerAdapter = new VendorHomeVerAdapter(getActivity(),vhomeVerModellist);
        vhomeVerticalRec.setAdapter(vhomeVerAdapter);
        vhomeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
       // vhomeVerAdapter.restoreSwitchStates();
//        vhomeVerticalRec.setHasFixedSize(true);
//        vhomeVerticalRec.setNestedScrollingEnabled(false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    @Override
    public void callback(int position, ArrayList<VendorHomeVerModel> list) {
        vhomeVerAdapter = new VendorHomeVerAdapter(getContext(),list);
        vhomeVerAdapter.notifyDataSetChanged();
        vhomeVerticalRec.setAdapter(vhomeVerAdapter);
    }


}