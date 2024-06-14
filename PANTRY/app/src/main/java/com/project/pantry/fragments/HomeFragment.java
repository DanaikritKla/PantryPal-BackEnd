package com.project.pantry.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.project.pantry.R;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();



    private LinearLayout generalWrapper;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.application_home));
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        generalWrapper = (LinearLayout)view.findViewById(R.id.general_wrapper);

        return view;
    }

    private void hideView(){
        generalWrapper.setVisibility(View.GONE);
    }

    private void showView(){
        generalWrapper.setVisibility(View.VISIBLE);
    }

}
