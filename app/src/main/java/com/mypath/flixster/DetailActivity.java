package com.mypath.flixster;


import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mypath.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;



import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyDrD3ha-MjD84smMjd8tNJUo537rN-qd_8";
    private static final  String VideoUrl = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    // the movie to display
    Movie movie;
    private YouTubePlayerView youTubePlayerView;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        youTubePlayerView = findViewById(R.id.player);

//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3C2C3E")));
        findViewById(R.id.relativelayout).setBackgroundColor(Color.parseColor("#A5BECC"));

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvOverview = findViewById(R.id.tvOverview);
        RatingBar rbVoteAverage = findViewById(R.id.rbVoteAverage);

        tvTitle.setTypeface(ResourcesCompat.getFont(this,R.font.berkshire_swash));
        tvOverview.setTypeface(ResourcesCompat.getFont(this,R.font.aclonica));

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = (float) movie.getVoteAverage();
        rbVoteAverage.setRating(voteAverage);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VideoUrl, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if(results.length()==0)
                        return;
                    String Youtubekey = results.getJSONObject(0).getString("key");
                    initialYoutube(Youtubekey);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });


    }

    private void initialYoutube(String youtubekey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(youtubekey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}