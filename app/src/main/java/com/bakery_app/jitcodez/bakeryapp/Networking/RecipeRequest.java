package com.bakery_app.jitcodez.bakeryapp.Networking;

import com.bakery_app.jitcodez.bakeryapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jitu on 18/2/18.
 */

public interface RecipeRequest {

    @GET("baking.json")
    Call<List<Recipe>> getRecipeList();
}
