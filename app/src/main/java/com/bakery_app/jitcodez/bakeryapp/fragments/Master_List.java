package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakery_app.jitcodez.bakeryapp.Adapter.IngridentAdapter;
import com.bakery_app.jitcodez.bakeryapp.Adapter.StepAdapter;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;
import com.bakery_app.jitcodez.bakeryapp.model.Step;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Master_List extends Fragment {

    RecyclerView rv_ingredients;
    RecyclerView rv_steps;
    CardView ingredient_cv;
    boolean cv=false;
    public Master_List() {
        // Required empty public constructor
    }
    //interface to communicate between fragments
    public interface OnStepClickListener{
        void onStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootItem=inflater.inflate(R.layout.fragment_master_list, container, false);
        rv_ingredients=(RecyclerView)rootItem.findViewById(R.id.rv_ingridents);
        rv_ingredients.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_ingredients.setItemAnimator(new DefaultItemAnimator());

        List<Ingredient> ingredients=getArguments().getParcelableArrayList("ingredients");
        rv_ingredients.setAdapter(new IngridentAdapter(ingredients,getContext()));



        ingredient_cv=(CardView)rootItem.findViewById(R.id.ingedient_title);
        ingredient_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cv==false)
                {
                    rv_ingredients.setVisibility(View.VISIBLE);
                    cv=true;
                }
                else
                {
                    rv_ingredients.setVisibility(View.GONE);
                    cv=false;
                }
            }
        });

        rv_steps=(RecyclerView)rootItem.findViewById(R.id.rv_steps);
        rv_steps.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_steps.setItemAnimator(new DefaultItemAnimator());

        List<Step> step=getArguments().getParcelableArrayList("steps");
        rv_steps.setAdapter(new StepAdapter(step,getContext()));
        return rootItem;
    }

}
