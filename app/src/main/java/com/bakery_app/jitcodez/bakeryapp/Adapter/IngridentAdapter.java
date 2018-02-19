package com.bakery_app.jitcodez.bakeryapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakery_app.jitcodez.bakeryapp.Activity.RecipeDetailsActivity;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jitu on 18/2/18.
 */

public class IngridentAdapter extends RecyclerView.Adapter<IngridentAdapter.IngredientViewHolder> {

    List<Ingredient> mIngredent;
    Context mContext;

    public IngridentAdapter() {

    }

    public IngridentAdapter(List<Ingredient> ingredient, Context context) {
        mIngredent = ingredient;
        mContext = context;
    }


    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.ingedients_item, parent, false);
        IngredientViewHolder mv = new IngredientViewHolder(itemView);
        return mv;
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mIngredent.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView Ingredient_title;
        TextView Ingredient_Quantity;
        Bundle bundle = new Bundle();

        public IngredientViewHolder(View itemView) {
            super(itemView);
            Ingredient_title = (TextView) itemView.findViewById(R.id.Ingredient_title);
            Ingredient_Quantity = (TextView) itemView.findViewById(R.id.Ingredient_Quantity);
        }

        public void bindData(final int position) {
            Ingredient i=mIngredent.get(position);
            Ingredient_title.setText(i.getIngredient());
            Ingredient_Quantity.setText(i.getQuantity()+" "+i.getMeasure());
        }
    }
}
