package com.bakery_app.jitcodez.bakeryapp.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.Adapter.MainRecipeAdapter;
import com.bakery_app.jitcodez.bakeryapp.Constants;
import com.bakery_app.jitcodez.bakeryapp.Networking.RecipeRequest;
import com.bakery_app.jitcodez.bakeryapp.Networking.ServiceBuilder;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.fragments.RecipeListFragment;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
       {

    private  boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //Recipe Fragment
        RecipeListFragment recipeListFragment = new RecipeListFragment();
        Bundle b=new Bundle();

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(checkIfTablet()) {
            b.putBoolean(Constants.mTwoPane,true);
            recipeListFragment.setArguments(b);
            fragmentManager.beginTransaction()
                    .add(R.id.list_container, recipeListFragment)
                    .commit();
        }else{
            b.putBoolean(Constants.mTwoPane,false);
            recipeListFragment.setArguments(b);
            fragmentManager.beginTransaction()
                    .add(R.id.list_container, recipeListFragment)
                    .commit();
        }

    }
    public boolean checkIfTablet(){
        DisplayMetrics metrics = new DisplayMetrics();

       getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.5){
            return true;
        }else{
            // smaller device
            return false;
        }
    }

}
