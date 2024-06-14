package com.project.pantry;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.project.pantry.entities.LoginObject;
import com.project.pantry.fragments.HomeFragment;
import com.project.pantry.fragments.MenusFragment;
import com.project.pantry.utils.PantryApplication;
import com.project.pantry.utils.PantrySharedPreference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private int mCount;
    private String tableData = "";
    private PantrySharedPreference shared;
    private LoginObject user;
    private int users = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Gson mGson = ((PantryApplication)getApplication()).getGsonObject();

        shared = ((PantryApplication)getApplication()).getShared();
        user = mGson.fromJson(shared.getUserData(), LoginObject.class);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //mCount = ((PantryApplication)getApplication()).cartItemCount();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    fragment = new HomeFragment();
                } else if (id == R.id.nav_menu_category) {
                    fragment = new MenusFragment();
                }/* else if (id == R.id.nav_order_payment) {
                    Intent his = new Intent(MainActivity.this, PaymentOrderActivity.class);
                    startActivity(his);
                } else if (id == R.id.nav_order_history) {
                    Intent his = new Intent(MainActivity.this, HistoryOrderActivity.class);
                    startActivity(his);
                } else if (id == R.id.nav_profile) {
                    fragment = new ProfileFragment();
                }*/ else if (id == R.id.nav_logout) {
                    //remove user data from shared preference
                    SharedPreferences mShared = ((PantryApplication)getApplication()).getShared().getInstanceOfSharedPreference();
                    mShared.edit().clear().apply();

                    //Navigate to login page
                    Intent loginPageIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginPageIntent);
                    finish();
                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            Intent optionIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(optionIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuItem menuItem = menu.findItem(R.id.action_shop);
        //DrawCart dCart = new DrawCart(this);
        //menuItem.setIcon(dCart.buildCounterDrawable(mCount, R.drawable.cart));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if (id == R.id.action_shop) {
            Intent checkoutIntent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(checkoutIntent);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        //mCount = ((PantryApplication)getApplication()).cartItemCount();
        super.onResume();
    }

}