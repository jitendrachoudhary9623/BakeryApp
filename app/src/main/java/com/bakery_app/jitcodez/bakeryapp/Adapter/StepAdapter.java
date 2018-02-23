package com.bakery_app.jitcodez.bakeryapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakery_app.jitcodez.bakeryapp.Activity.RecipeDetailsActivity;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.fragments.Details_list;
import com.bakery_app.jitcodez.bakeryapp.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jitu on 18/2/18.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    List<Step> mStep;
    Context mContext;
    boolean mTwoPane;
    public StepAdapter() {

    }

    public StepAdapter(List<Step> step, Context context,boolean mTwoPane) {
        mStep = step;
        mContext = context;
        this.mTwoPane=mTwoPane;
    }


    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.steps_item, parent, false);
        StepViewHolder mv = new StepViewHolder(itemView);
        return mv;
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, final int position) {
        holder.bindData(position);
        holder.Steps_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = new Bundle();
                arguments.putParcelableArrayList("StepList", (ArrayList<? extends Parcelable>) mStep);
                arguments.putInt("Position",position);
                Details_list fragment = new Details_list();
                fragment.setArguments(arguments);
                if(mTwoPane) {
                    FragmentManager fm = ((RecipeDetailsActivity) mContext).getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.detail_list_container, fragment)
                            .commit();
                }
                else
                {
                    FragmentManager fm = ((RecipeDetailsActivity) mContext).getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.master_list_container, fragment)
                            .commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStep.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder  {

        TextView Steps_title;
        Bundle bundle = new Bundle();

        public StepViewHolder(View itemView) {
            super(itemView);
            Steps_title = (TextView) itemView.findViewById(R.id.Steps_title);
        }

        public void bindData(final int position) {
            Step i=mStep.get(position);
            Steps_title.setText(i.getShortDescription());

        }



    }
}
