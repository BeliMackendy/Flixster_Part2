package com.example.flixster;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    TextView tv_Title;
    TextView tv_Overview;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv_Title = findViewById(R.id.tv_Title);
        tv_Overview = findViewById(R.id.tv_Overview);
        ratingBar = findViewById(R.id.ratingBar);

        Movie  movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        tv_Title.setText(movie.getTitle());
        tv_Overview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getVoteAverage());

    }
}