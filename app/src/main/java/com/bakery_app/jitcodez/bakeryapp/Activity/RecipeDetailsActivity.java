package com.bakery_app.jitcodez.bakeryapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.ArrayList;


public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
       Recipe recipe = (Recipe) getIntent().getParcelableExtra("Object");

        Toast.makeText(this,""+recipe.getName(),Toast.LENGTH_LONG).show();

    }
}
