package com.mypath.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mypath.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;
    private TextView tvTitle;
    private TextView tvOverview;
    private RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3C2C3E")));
        findViewById(R.id.relativelayout).setBackgroundColor(Color.parseColor("#A5BECC"));

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);

        tvTitle.setTypeface(ResourcesCompat.getFont(this,R.font.berkshire_swash));
        tvOverview.setTypeface(ResourcesCompat.getFont(this,R.font.aclonica));

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = (float) movie.getVoteAverage();
        rbVoteAverage.setRating(voteAverage);
    }
}