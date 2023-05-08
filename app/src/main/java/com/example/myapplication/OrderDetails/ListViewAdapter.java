package com.example.myapplication.OrderDetails;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> itemList;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<Item> itemList) {
        super(context, 0, itemList);
        this.itemList = itemList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemNameTextView = convertView.findViewById(R.id.item);
            viewHolder.quantityTextView = convertView.findViewById(R.id.quantity);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item listItem = itemList.get(position);

        // Set list item values
        viewHolder.itemNameTextView.setText(listItem.getName());
        viewHolder.quantityTextView.setText(String.valueOf(listItem.getQuantity()));

        return convertView;
    }

    private static class ViewHolder {
        TextView itemNameTextView;
        TextView quantityTextView;
    }
}