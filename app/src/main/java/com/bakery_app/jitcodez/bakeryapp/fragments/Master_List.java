package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.bakery_app.jitcodez.bakeryapp.Constants;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.Widget.RecipeWidgetService;
import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;
import com.bakery_app.jitcodez.bakeryapp.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Master_List extends Fragment {

    RecyclerView rv_ingredients;
    RecyclerView rv_steps;
    CardView ingredient_cv;
   String RecipeName;
    boolean cv = false;

    public Master_List() {
        // Required empty public constructor
    }

    static List<Ingredient> ingredients;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootItem = inflater.inflate(R.layout.fragment_master_list, container, false);
        setRetainInstance(true);

        boolean mTwoPane = getArguments().getBoolean(Constants.mTwoPane);
        RecipeName=getArguments().getString(Constants.RecipeName);
        rv_ingredients = (RecyclerView) rootItem.findViewById(R.id.rv_ingridents);
        rv_ingredients.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_ingredients.setItemAnimator(new DefaultItemAnimator());

        ingredients = getArguments().getParcelableArrayList(Constants.Ingredients);
        rv_ingredients.setAdapter(new IngridentAdapter(ingredients, getContext()));


        ingredient_cv = (CardView) rootItem.findViewById(R.id.ingedient_title);
        ingredient_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cv == false) {
                    rv_ingredients.setVisibility(View.VISIBLE);
                    cv = true;
                } else {
                    rv_ingredients.setVisibility(View.GONE);
                    cv = false;
                }
            }
        });

        rv_steps = (RecyclerView) rootItem.findViewById(R.id.rv_steps);
        rv_steps.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_steps.setItemAnimator(new DefaultItemAnimator());

        List<Step> step = getArguments().getParcelableArrayList(Constants.Steps);
        rv_steps.setAdapter(new StepAdapter(step, getContext(), mTwoPane));
        startWidgetService();
        return rootItem;
    }




    void startWidgetService()
    {
        Intent i = new Intent(getContext(), RecipeWidgetService.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Widget_Ingredients", (ArrayList<? extends Parcelable>) ingredients);
        bundle.putString("Widget_Recipe_Name",RecipeName);
        i.putExtra("bundle", bundle);
        i.setAction(RecipeWidgetService.WIDGET_UPDATE_ACTION);
        getContext().startService(i);
    }
}
