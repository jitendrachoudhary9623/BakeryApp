package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.Adapter.MainRecipeAdapter;
import com.bakery_app.jitcodez.bakeryapp.Networking.RecipeRequest;
import com.bakery_app.jitcodez.bakeryapp.Networking.ServiceBuilder;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {

    RecyclerView rv;
    boolean mTwoPane;
    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mTwoPane=getArguments().getBoolean("mTwoPane");
        rv=(RecyclerView)rootView.findViewById(R.id.main_recipe_lookout);
if(mTwoPane) {
    rv.setLayoutManager(new GridLayoutManager(getContext(), 3));


}
else
{
    rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

}

        rv.setItemAnimator(new DefaultItemAnimator());


        getRecipes();
        return rootView;
    }

    public void getRecipes()
    {
        RecipeRequest recipeRequest = ServiceBuilder.buildService(RecipeRequest.class);
        Call<List<Recipe>> caller=recipeRequest.getRecipeList();

        caller.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> rp=response.body();
                rv.setAdapter(new MainRecipeAdapter(rp,getContext()));

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getContext(),"Error Occured",Toast.LENGTH_LONG).show();
            }
        });
    }

}
