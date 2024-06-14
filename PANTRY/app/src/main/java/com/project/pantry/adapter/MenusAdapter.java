package com.project.pantry.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.pantry.R;
import com.project.pantry.entities.MenusObject;
import com.project.pantry.utils.Helper;

import java.util.List;

public class MenusAdapter extends RecyclerView.Adapter<MenusViewHolder>{

    private Context context;
    private List<MenusObject> categoryObject;

    public MenusAdapter(Context context, List<MenusObject> categoryObject) {
        this.context = context;
        this.categoryObject = categoryObject;
    }

    @Override
    public MenusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menus_list, parent, false);
        return new MenusViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MenusViewHolder holder, int position) {
        final MenusObject catObject = categoryObject.get(position);
        final int id = catObject.getId();
        holder.foodName.setText(catObject.getMenu_name());
        holder.foodType.setText(catObject.getFood_type());
        // use Glide to download and display the category image.
        String serverImagePath = Helper.PUBLIC_FOLDER_IMAGE_FOOD + catObject.getMenu_image();
        Glide.with(context).load(serverImagePath).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().override(300, 300).into(holder.foodImage);

        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryObject.size();
    }


    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
}