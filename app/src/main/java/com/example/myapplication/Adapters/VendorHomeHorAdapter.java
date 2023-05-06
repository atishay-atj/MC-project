package com.example.myapplication.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.VendorHomeHorModel;
import com.example.myapplication.models.VendorHomeVerModel;

import java.util.ArrayList;

public class VendorHomeHorAdapter extends RecyclerView.Adapter<VendorHomeHorAdapter.ViewHolder> {
    VendorUpdateVertical vupdateVertical;

    Activity activity;
    ArrayList<VendorHomeHorModel> list;
    boolean check = true;
    boolean select=true;
    int row_index = -1;


    public VendorHomeHorAdapter(VendorUpdateVertical vupdateVertical, Activity activity, ArrayList<VendorHomeHorModel> list) {
        this.vupdateVertical = vupdateVertical;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        if(check) {
            ArrayList<VendorHomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new VendorHomeVerModel(R.drawable.paneer_sandwich, "Paneer Sandwich", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.aalo_sandwich, "Aalo Sandwich", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.butter_maggi, "Butter Maggi", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.veg_maggi, "Veg Maggi", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.egg_maggi, "Egg Maggi", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.plain_maggi, "Plain Maggi", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.mixed_paratha, "Mixed Paratha", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.aalo_paratha, "Aalo Paratha", true));
            homeVerModels.add(new VendorHomeVerModel(R.drawable.paneer_paratha, "Paneer Paratha", true));

            vupdateVertical.callback(position, homeVerModels);
            check = false;
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index = position;
                    notifyDataSetChanged();
                    if(position==0){
                        ArrayList<VendorHomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.paneer_sandwich,"Paneer Sandwich",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.aalo_sandwich,"Aalo Sandwich",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.butter_maggi,"Butter Maggi",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.veg_maggi,"Veg Maggi",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.egg_maggi,"Egg Maggi",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.plain_maggi,"Plain Maggi",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.mixed_paratha,"Mixed Paratha",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.aalo_paratha,"Aalo Paratha",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.paneer_paratha,"Paneer Paratha",true));
                        vupdateVertical.callback(position,homeVerModels);
                    } else if (position==1) {
                        ArrayList<VendorHomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.strawberry_mojito,"Strawberry Mojito",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.paan_mojito,"Paan Mojito",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.latte,"Iced Latte",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.cappuccino,"Iced Cappuccino",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.veg_pasta,"Veg Pasta",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.non_veg_pasta,"Non Veg Pasta",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.masala_soda,"Masala Soda",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.fresh_lime_soda,"Fresh Lime Soda",true));

                        vupdateVertical.callback(position,homeVerModels);
                    }
                    else if (position==2) {
                        ArrayList<VendorHomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.mysore_masala_dosa,"Mysore Masala Dosa",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.butter_dosa,"Butter Dosa",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.paneer_dosa,"Paneer Dosa",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.plain_dosa,"Plain Dosa",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.uttapam,"Uttapam",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.idli,"Idli",true));
                        homeVerModels.add(new VendorHomeVerModel(R.drawable.vadaa,"Vada",true));

                        vupdateVertical.callback(position,homeVerModels);
                    }
                }
            });

            if(select){
                if(position==0)
                {
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    select=false;
                }
            }
            else
            {
                if(row_index==position){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                }else
                {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }

        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.res1);
            name = itemView.findViewById(R.id.canteen_name);
            cardView = itemView.findViewById(R.id.card1);
        }
    }
}