package com.bakery_app.jitcodez.bakeryapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;

import java.util.List;

/**
 * Created by jitu on 1/3/18.
 */

public class RecipeRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return  new RecipeViewsFactory(this.getApplicationContext());
    }

    class RecipeViewsFactory implements RemoteViewsService.RemoteViewsFactory{
        private Context mContext;
        List<Ingredient> ingredients;
        public RecipeViewsFactory(Context mContext){
            this.mContext=mContext;
        }
        @Override
        public void onCreate() {

        }

        //TODO(1) Place Where Data is changed
        @Override
        public void onDataSetChanged() {
ingredients=RecipeWidget.ingredientList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
          if(ingredients==null)
          {
              return 0;
          }
          else
          {
              return ingredients.size();
          }
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_text_view);
            views.setTextViewText(R.id.text_view_recipe_widget, ingredients.get(position).getIngredient());
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

}
