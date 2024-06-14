package com.project.pantry.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.pantry.R;

public class MenusViewHolder extends RecyclerView.ViewHolder{

    public TextView foodName;
    public TextView foodType;
    public ImageView foodImage;
    public View mItemView;


    public MenusViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        foodName = (TextView)itemView.findViewById(R.id.food_name);
        foodType = (TextView)itemView.findViewById(R.id.food_type);
        foodImage = (ImageView)itemView.findViewById(R.id.food_image);
    }
}