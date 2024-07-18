package com.project.pantry.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.project.pantry.R;
import com.project.pantry.adapter.MenusAdapter;
import com.project.pantry.entities.MenusObject;
import com.project.pantry.network.GsonRequest;
import com.project.pantry.network.VolleySingleton;
import com.project.pantry.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class MenusFragment extends Fragment {

    private static final String TAG = MenusFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    public MenusFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.menus_food));
        View view = inflater.inflate(R.layout.fragment_menu_category, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.food_menu);
        GridLayoutManager mGrid = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mGrid);
        recyclerView.setHasFixedSize(true);

        getRestaurantMenuFromRemoteServer();

        return view;
    }

    private void getRestaurantMenuFromRemoteServer(){
        GsonRequest<MenusObject[]> serverRequest = new GsonRequest<MenusObject[]>(
                Request.Method.GET,
                Helper.PATH_TO_SERVER_ALL_MENUS,
                MenusObject[].class,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(serverRequest);
    }

    private Response.Listener<MenusObject[]> createRequestSuccessListener() {
        return new Response.Listener<MenusObject[]>() {
            @Override
            public void onResponse(MenusObject[] response) {
                try {
                    Log.d(TAG, "Json Response " + response.toString());
                    if(response.length > 0){
                        //display restaurant menu information
                        List<MenusObject> menuObject = new ArrayList<>();
                        for(int i = 0; i < response.length; i++){
                            Log.d(TAG, "Menu name " + response[i].getMenu_name());
                            menuObject.add(new MenusObject(response[i].getId(), response[i].getMenu_name(), response[i].getFood_category(), response[i].getFood_type(), response[i].getMenu_image(), response[i].getCook()));
                        }
                        MenusAdapter mAdapter = new MenusAdapter(getActivity(), menuObject);
                        recyclerView.setAdapter(mAdapter);

                    }else{
                        Toast.makeText(getActivity(), "Restaurant menu is empty", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

}