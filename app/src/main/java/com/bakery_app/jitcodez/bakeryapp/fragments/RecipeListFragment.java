package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.Activity.MainActivity;
import com.bakery_app.jitcodez.bakeryapp.Adapter.MainRecipeAdapter;
import com.bakery_app.jitcodez.bakeryapp.Networking.RecipeRequest;
import com.bakery_app.jitcodez.bakeryapp.Networking.ServiceBuilder;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
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
    List<Recipe> rp=new ArrayList<Recipe>();
    MainRecipeAdapter adapter;
    Context context = getContext();
    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_recipe_list, container, false);
setRetainInstance(true);
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




        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState==null) {

            new RecipeList().execute();
        }
        else
        {
            rp.clear();
            rp=null;
            rp=savedInstanceState.getParcelableArrayList("RecipeList");
            setupRecyclerView(rp);
        }
    }

  /*  public void getRecipes()
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
*/

    class RecipeList extends AsyncTask<Void,Void,List<Recipe>>{
List<Recipe> recipes;
        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            RecipeRequest recipeRequest = ServiceBuilder.buildService(RecipeRequest.class);
            Call<List<Recipe>> caller=recipeRequest.getRecipeList();
            try {
                Response<List<Recipe>> newPostResponse = caller.execute();
                if(newPostResponse.isSuccessful())
                {
                    rp=newPostResponse.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return rp;
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            if(recipes!=null)
            {
                setupRecyclerView(recipes);
            }


        }
    } public void setupRecyclerView(List<Recipe> rp1) {
rp=rp1;
        adapter = new MainRecipeAdapter(rp, getContext());
                rv.setAdapter(adapter);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("RecipeList", (ArrayList<Recipe>) rp);
    }



}
