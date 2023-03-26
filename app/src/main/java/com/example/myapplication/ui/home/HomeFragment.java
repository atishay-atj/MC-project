package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.HomeHorAdapter;
import com.example.myapplication.Adapters.HomeVerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.HomeHorModel;
import com.example.myapplication.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView homeHorizontalRec,homeVerticalRec;
    List<HomeHorModel> homeHorModellist;
    HomeHorAdapter homeHorAdapter;
    List<HomeVerModel> homeVerModellist;
    HomeVerAdapter homeVerAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root  = inflater.inflate(R.layout.fragment_home,container,false);
        homeHorizontalRec= root.findViewById(R.id.horizontal_home1);
        homeVerticalRec= root.findViewById(R.id.vertical_home1);

        //horizontal Recycler view
        homeHorModellist = new ArrayList<>();
        homeHorModellist.add(new HomeHorModel(R.drawable.restaurant,"canteen"));
        homeHorModellist.add(new HomeHorModel(R.drawable.sign,"Bru Bakes"));
        homeHorModellist.add(new HomeHorModel(R.drawable.snacks,"Bicano"));


        homeHorAdapter = new HomeHorAdapter(getActivity(),homeHorModellist);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        homeVerModellist = new ArrayList<>();
        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_sandwich,"Paneer Sandwich"));
        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_sandwich,"Aalo sandwich"));
        homeVerModellist.add(new HomeVerModel(R.drawable.butter_maggi,"butter maggi"));
        homeVerModellist.add(new HomeVerModel(R.drawable.veg_maggi,"Veg maggi"));
        homeVerModellist.add(new HomeVerModel(R.drawable.egg_maggi,"egg maggi"));
        homeVerModellist.add(new HomeVerModel(R.drawable.plain_maggi,"Plain Maggi"));
        homeVerModellist.add(new HomeVerModel(R.drawable.mixed_paratha,"Mixed paratha"));
        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_paratha,"Aalo paratha"));
        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_paratha,"paneer paratha"));

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModellist);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}