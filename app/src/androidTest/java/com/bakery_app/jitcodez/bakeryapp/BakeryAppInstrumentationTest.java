package com.bakery_app.jitcodez.bakeryapp;

import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.app.FragmentManager;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bakery_app.jitcodez.bakeryapp.Activity.MainActivity;
import com.bakery_app.jitcodez.bakeryapp.fragments.RecipeListFragment;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by jitu on 3/3/18.
 */
@RunWith(AndroidJUnit4.class)
public class BakeryAppInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init() {
        Bundle b = new Bundle();
        b.putBoolean("mTwoPane", false);
        RecipeListFragment fragment = new RecipeListFragment();
        fragment.setArguments(b);
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.list_container, fragment).commit();
    }

    @Test
    public void CheckAppWorking() {

        onView(firstView(withId(R.id.main_recipe_lookout)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));


    }

    private <T> Matcher<T> firstView(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }

                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }
}
