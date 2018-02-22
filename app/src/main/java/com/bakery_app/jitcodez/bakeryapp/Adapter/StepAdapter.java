package com.bakery_app.jitcodez.bakeryapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Ingredient;
import com.bakery_app.jitcodez.bakeryapp.model.Step;

import java.util.List;

/**
 * Created by jitu on 18/2/18.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    List<Step> mStep;
    Context mContext;

    public StepAdapter() {

    }

    public StepAdapter(List<Step> step, Context context) {
        mStep = step;
        mContext = context;
    }


    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.steps_item, parent, false);
        StepViewHolder mv = new StepViewHolder(itemView);
        return mv;
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mStep.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

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
