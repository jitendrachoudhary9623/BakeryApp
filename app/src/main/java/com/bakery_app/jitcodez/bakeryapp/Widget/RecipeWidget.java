package com.bakery_app.jitcodez.bakeryapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    static List<Ingredient> ingredientList;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetIds[],List<Ingredient> ingredients) {


        ingredientList=ingredients;
        for (int appWidgetId : appWidgetIds)
        {
            Intent intent = new Intent(context, RecipeRemoteViewService.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
            views.setRemoteAdapter(R.id.list_view_widget, intent);
            ComponentName component = new ComponentName(context, RecipeWidget.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_view_widget);
            appWidgetManager.updateAppWidget(component, views);
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

