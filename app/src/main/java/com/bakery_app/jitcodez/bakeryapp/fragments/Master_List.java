package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakery_app.jitcodez.bakeryapp.Adapter.IngridentAdapter;
import com.bakery_app.jitcodez.bakeryapp.Adapter.MainRecipeAdapter;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Master_List extends Fragment {

    RecyclerView rv_ingredients;
    RecyclerView steps;

    public Master_List() {
        // Required empty public constructor
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
        return rootItem;
    }

}
