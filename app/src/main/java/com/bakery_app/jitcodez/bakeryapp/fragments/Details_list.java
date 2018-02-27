package com.bakery_app.jitcodez.bakeryapp.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_list extends Fragment {

    List<Step> mSteps;
    int position = 0;
    TextView step_short_desc, step_desc;
    Button next;
    SimpleExoPlayer player;
    SimpleExoPlayerView simpleExoPlayerView;
    boolean mTwoPane=false;
    public Details_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootItem = inflater.inflate(R.layout.fragment_detail_list, container, false);
        setRetainInstance(true);

        try {
            mSteps = getArguments().getParcelableArrayList("StepList");
            mTwoPane=getArguments().getBoolean("mTwoPane");

            position = getArguments().getInt("Position");
        } catch (Exception e) {
            position = 0;
        }
        step_desc = (TextView) rootItem.findViewById(R.id.step_description);

        step_short_desc = (TextView) rootItem.findViewById(R.id.step_short_description);

        next = (Button) rootItem.findViewById(R.id.next_button);
        simpleExoPlayerView = (SimpleExoPlayerView) rootItem.findViewById(R.id.video_view);

        updateUI();
        //Toast.makeText(getContext(),step.getShortDescription(),Toast.LENGTH_SHORT).show();
        return rootItem;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.release();
            player = null;
        }
    }

    public void updateUI() {
        if (mSteps != null) {
            step_short_desc.setText(mSteps.get(position).getShortDescription());
            step_desc.setText(mSteps.get(position).getDescription());
            String url = mSteps.get(position).getVideoURL();
            if (!url.equals("")) {
                simpleExoPlayerView.setVisibility(View.VISIBLE);

                initializePlayer(url);
            } else {
                simpleExoPlayerView.setVisibility(View.GONE);
            }
        }
    }

    private void initializePlayer(String url) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        simpleExoPlayerView.setPlayer(player);

        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "BakeryApp"));

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();


        Uri videoUri = Uri.parse(url);
        MediaSource videoSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);
        player.prepare(videoSource);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       if(savedInstanceState!=null)
       {
           position=savedInstanceState.getInt("Position1");
            mTwoPane=savedInstanceState.getBoolean("sTwoPane");
           updateUI();
       }
       else
       {
           updateUI();
       }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSteps != null) {
                    position++;
                    if(!mTwoPane) {
                        next.setVisibility(View.VISIBLE);
                    }else
                    {
                        next.setVisibility(View.INVISIBLE);

                    }
                    if (player != null) {
                        player.release();
                        player = null;
                    }
                    if (position == mSteps.size()-1) {

                        next.setVisibility(View.GONE);

                    }
                    if (position == mSteps.size()) {
                        position = 0;


                    }
                    updateUI();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position1",position);
        outState.putBoolean("sTwoPane",mTwoPane);
    }
}
