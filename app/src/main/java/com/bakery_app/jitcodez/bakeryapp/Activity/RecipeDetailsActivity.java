package com.bakery_app.jitcodez.bakeryapp.Activity;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.Adapter.StepAdapter;
import com.bakery_app.jitcodez.bakeryapp.Constants;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.fragments.Details_list;
import com.bakery_app.jitcodez.bakeryapp.fragments.Master_List;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.ArrayList;


public class RecipeDetailsActivity extends AppCompatActivity {

    private boolean mTwoPane;
    boolean playerReady=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Recipe recipe = (Recipe) getIntent().getParcelableExtra(Constants.OBJECT);
        getSupportActionBar().setTitle("" + recipe.getName());
      //  Toast.makeText(this, "" + recipe.getName(), Toast.LENGTH_LONG).show();

        Bundle b = new Bundle();
        b.putParcelableArrayList(Constants.Ingredients, (ArrayList<? extends Parcelable>) recipe.getIngredients());
        b.putParcelableArrayList(Constants.Steps, (ArrayList<? extends Parcelable>) recipe.getSteps());
        b.putString(Constants.RecipeName,recipe.getName());
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.tablet_mode) != null) {
            mTwoPane = true;
            Master_List master_list = new Master_List();
            b.putBoolean(Constants.mTwoPane, true);
            master_list.setArguments(b);


            fragmentManager.beginTransaction()

                    .add(R.id.master_list_container, master_list)

                    .commit();

            Details_list detail_list = new Details_list();

            fragmentManager.beginTransaction()

                    .add(R.id.detail_list_container, detail_list)

                    .commit();
        } else {
            mTwoPane = false;
            Master_List master_list = new Master_List();
            b.putBoolean(Constants.mTwoPane, false);

            master_list.setArguments(b);

            fragmentManager.beginTransaction()

                    .add(R.id.master_list_container, master_list)
                    .addToBackStack(null)
                    .commit();
        }


    }

}
