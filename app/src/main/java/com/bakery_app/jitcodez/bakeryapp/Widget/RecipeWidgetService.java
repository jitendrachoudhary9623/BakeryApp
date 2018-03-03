package com.bakery_app.jitcodez.bakeryapp.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;

import java.util.List;


public class RecipeWidgetService extends IntentService {

    public static final String WIDGET_UPDATE_ACTION = "com.bakery_app.jitcodez.bakeryapp.action.update_widget";

    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }
static List<Ingredient> ingredients;
    @Override
    protected void onHandleIntent(Intent intent) {
        {
            if (intent != null && intent.getAction().equals(WIDGET_UPDATE_ACTION))
            {
                Bundle bundle = intent.getBundleExtra("bundle");
                ingredients = bundle.getParcelableArrayList("Widget_Ingredients");

//creates Widget
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidget.class));
               RecipeWidget.updateAppWidget(this, appWidgetManager, appWidgetIds,ingredients);
            }
        }
    }


    public static List<Ingredient> getList()
    {
        return ingredients;
    }


}
