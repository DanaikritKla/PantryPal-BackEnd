package com.project.pantry;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.project.pantry.entities.MenusObject;
import com.project.pantry.utils.Helper;
import com.project.pantry.utils.PantryApplication;
import com.project.pantry.utils.PantrySharedPreference;

public class FoodDetailActivity extends AppCompatActivity {

    private static final String TAG = FoodDetailActivity.class.getSimpleName();

    private LinearLayout boxWrapper;

    private MenusObject singleMenuItem;

    private LinearLayout.LayoutParams params;

    private Gson gson;
    private PantrySharedPreference shared;


    private String addedOrderNote = "";
    private String addedOrderOptions = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        gson = ((PantryApplication)getApplication()).getGsonObject();
        shared = ((PantryApplication)getApplication()).getShared();

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView menuItemImage = (ImageView)findViewById(R.id.food_item_image);
        TextView menuItemName = (TextView)findViewById(R.id.food_item_name);
        TextView menuItemMaterial = (TextView)findViewById(R.id.food_material);
        TextView menuItemDescription = (TextView)findViewById(R.id.food_description);


        boxWrapper = (LinearLayout)findViewById(R.id.box_wrapper);
        boxWrapper.setVisibility(View.GONE);

        String menuString = getIntent().getExtras().getString("MENU_ITEM");

        if(!TextUtils.isEmpty(menuString)){
            Gson gson = ((PantryApplication)getApplication()).getGsonObject();
            singleMenuItem = gson.fromJson(menuString, MenusObject.class);
            boxWrapper.setVisibility(View.VISIBLE);
            setTitle(singleMenuItem.getMenu_name());

            String serverImagePath = Helper.PUBLIC_FOLDER_IMAGE_FOOD + singleMenuItem.getMenu_image();
            Glide.with(FoodDetailActivity.this).load(serverImagePath).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().override(300, 300).into(menuItemImage);

            menuItemName.setText(singleMenuItem.getMenu_name());
            menuItemMaterial.setText(singleMenuItem.getFood_category());
            menuItemDescription.setText(singleMenuItem.getCook());
        }else {
            Toast.makeText(FoodDetailActivity.this, getString(R.string.not_found), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void invalidateCart() {
        invalidateOptionsMenu();
    }




}