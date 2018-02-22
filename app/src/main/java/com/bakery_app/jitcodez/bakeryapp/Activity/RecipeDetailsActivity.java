package com.bakery_app.jitcodez.bakeryapp.Activity;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.fragments.Master_List;
import com.bakery_app.jitcodez.bakeryapp.fragments.RecipeListFragment;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.ArrayList;


public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
       Recipe recipe = (Recipe) getIntent().getParcelableExtra("Object");

        Toast.makeText(this,""+recipe.getName(),Toast.LENGTH_LONG).show();

        Bundle b=new Bundle();
        b.putParcelableArrayList("ingredients", (ArrayList<? extends Parcelable>) recipe.getIngredients());
        b.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) recipe.getSteps());

        Master_List master_list = new Master_List();
        master_list.setArguments(b);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()

                .add(R.id.master_list_container,master_list)
                .commit();

    }
}
