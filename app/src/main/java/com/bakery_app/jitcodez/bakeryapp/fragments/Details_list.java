package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakery_app.jitcodez.bakeryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_list extends Fragment {


    public Details_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_list, container, false);
    }

}
