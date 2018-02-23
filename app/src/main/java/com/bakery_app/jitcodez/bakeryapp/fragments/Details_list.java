package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;
import com.bakery_app.jitcodez.bakeryapp.model.Step;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_list extends Fragment {

    List<Step> mSteps;
    int position=0;
    TextView tv;
    Button next;
    public Details_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootItem=inflater.inflate(R.layout.fragment_detail_list, container, false);
         try{
             mSteps=getArguments().getParcelableArrayList("StepList");

             position=getArguments().getInt("Position");
         }catch(Exception e)
         {
             position=0;
         }
         tv=(TextView)rootItem.findViewById(R.id.step_detail);
         next=(Button)rootItem.findViewById(R.id.next_button);
         updateUI();
        //Toast.makeText(getContext(),step.getShortDescription(),Toast.LENGTH_SHORT).show();
        return rootItem;
    }




    public void updateUI()
    {
        if(mSteps!=null) {
            tv.setText(mSteps.get(position).getShortDescription());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSteps!=null) {
                    position++;
                    if (position == mSteps.size())
                        position = 0;
                    updateUI();
                }
            }
        });
    }
}
