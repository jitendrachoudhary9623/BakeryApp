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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bakery_app.jitcodez.bakeryapp.Constants;
import com.bakery_app.jitcodez.bakeryapp.R;
import com.bakery_app.jitcodez.bakeryapp.model.Recipe;
import com.bakery_app.jitcodez.bakeryapp.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.view.View.GONE;

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
    ImageView img;
    private long currentPosition=0;
    boolean mTwoPane = false;
boolean playerPlaying=true;
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
            mSteps = getArguments().getParcelableArrayList(Constants.StepList);
            mTwoPane = getArguments().getBoolean(Constants.mTwoPane);

            position = getArguments().getInt(Constants.Position);
        } catch (Exception e) {
            position = 0;
        }
        if(savedInstanceState!=null)
        {
            playerPlaying=savedInstanceState.getBoolean("PlayerPlaying");
            mTwoPane = savedInstanceState.getBoolean(Constants.sTwoPane);
            currentPosition = savedInstanceState.getLong(Constants.ExoPlayerPosition);
            position = savedInstanceState.getInt("Position1");
        }
        step_desc = (TextView) rootItem.findViewById(R.id.step_description);

        step_short_desc = (TextView) rootItem.findViewById(R.id.step_short_description);

        next = (Button) rootItem.findViewById(R.id.next_button);
        simpleExoPlayerView = (SimpleExoPlayerView) rootItem.findViewById(R.id.video_view);
        img = (ImageView) rootItem.findViewById(R.id.thumbImage);
        //Toast.makeText(getContext(), "" + currentPosition, Toast.LENGTH_LONG).show();

        updateUI();

        //Toast.makeText(getContext(),step.getShortDescription(),Toast.LENGTH_SHORT).show();
        return rootItem;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            currentPosition=player.getCurrentPosition();
            playerPlaying=player.getPlayWhenReady();
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
                simpleExoPlayerView.setVisibility(GONE);
            }
            String thumbnail = mSteps.get(position).getThumbnailURL();
            if (thumbnail.equals("")) {
                img.setVisibility(GONE);
            } else {
                img.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(Uri.parse(thumbnail))
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher_round)
                        .into(img);
            }
        }


    }

    private void initializePlayer(String url) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);


        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "BakeryApp"));

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();


        Uri videoUri = Uri.parse(url);
        MediaSource videoSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

            simpleExoPlayerView.setPlayer(player);
            player.prepare(videoSource);
            player.seekTo(currentPosition);
            player.setPlayWhenReady(playerPlaying);
        }
     /*   player.addListener(new Player.DefaultEventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playWhenReady && playbackState == Player.STATE_READY) {
                   playerPlaying=true;

                } else if (playWhenReady) {
                  playerPlaying=false;
                } else {
                playerPlaying=false;
                }
            }
        });*/


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            /*position = savedInstanceState.getInt("Position1");
            mTwoPane = savedInstanceState.getBoolean(Constants.sTwoPane);
           currentPosition = savedInstanceState.getLong(Constants.ExoPlayerPosition);*/
         //  playerPlaying=savedInstanceState.getBoolean("PlayerPlaying");

        }
        if (mTwoPane) {
            next.setVisibility(View.INVISIBLE);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSteps != null) {
                    position++;
                    currentPosition=0;
                    playerPlaying=true;
                    if (!mTwoPane) {
                        next.setVisibility(View.VISIBLE);
                    } else {
                        next.setVisibility(View.INVISIBLE);

                    }
                    if (player != null) {
                        player.release();
                        player = null;
                    }
                    if (position == mSteps.size() - 1) {

                        next.setVisibility(GONE);


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
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position1", position);
        outState.putBoolean(Constants.sTwoPane, mTwoPane);
        outState.putBoolean("PlayerPlaying", playerPlaying);

        outState.putLong(Constants.ExoPlayerPosition, currentPosition);


    }

    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
            player.release();
        }
    }
}
