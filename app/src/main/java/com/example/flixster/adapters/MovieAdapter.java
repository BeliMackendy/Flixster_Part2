package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.Target;
import com.example.flixster.DetailActivity;
import com.example.flixster.GlideApp;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
//
//        return new ViewHolder(movieView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Movie movie = movies.get(position);
//
//        holder.tvTitle.setText(movie.getTitle());
//        holder.tvOverview.setText(movie.getOverview());
//
//        String images = movie.getPosterPath();
//        int orientation = holder.itemView.getContext().getResources().getConfiguration().orientation;
//        int x= Target.SIZE_ORIGINAL;
//        int y = Target.SIZE_ORIGINAL;
//
//        if(orientation== Configuration.ORIENTATION_LANDSCAPE)
//        {
//            images = movie.getBackdropPath();
//            x=900;
//            y=500;
//        }
//
//        GlideApp.with(holder.itemView.getContext())
//                .load(images)
//                .placeholder(R.drawable.movie_placeholder)
//                .override(x, y)
//                .centerCrop()
//                .transition(DrawableTransitionOptions.withCrossFade(2000))
//                .into(holder.ivPoster);
//
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == 0) {
            View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(movieView);
        } else {
            View movieView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_backdrop, parent, false);
            viewHolder = new ViewHolder2(movieView2);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        if (holder.getItemViewType() == 0) {
            ViewHolder v1 = (ViewHolder) holder;
            v1.tvTitle.setText(movie.getTitle());
            v1.tvOverview.setText(movie.getOverview());

            String images = movie.getPosterPath();
            int orientation = holder.itemView.getContext().getResources().getConfiguration().orientation;
            int x = Target.SIZE_ORIGINAL;
            int y = Target.SIZE_ORIGINAL;

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                images = movie.getBackdropPath();
                x = 900;
                y = 500;
            }

            GlideApp.with(holder.itemView.getContext())
                    .load(images)
                    .placeholder(R.drawable.movie_placeholder)
                    .override(x, y)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(2000))
                    .transform(new RoundedCorners(10))
                    .into(v1.ivPoster);

            v1.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i  = new Intent(v1.container.getContext(), DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v1.container.getContext(),(View)v1.container,"detail");
                    v1.container.getContext().startActivity(i,options.toBundle());
                }
            });
        } else {
            ViewHolder2 v2 = (ViewHolder2) holder;
            int x = Target.SIZE_ORIGINAL;
            int y = Target.SIZE_ORIGINAL;

            GlideApp.with(holder.itemView.getContext())
                    .load(movie.getBackdropPath())
                    .placeholder(R.drawable.movie_placeholder)
                    .override(x, y)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(2000))
                    .transform(new RoundedCorners(10))
                    .into(v2.ivbackdrop);

            v2.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i  = new Intent(v2.container.getContext(), DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v2.container.getContext(),(View)v2.container,"detail");

                    v2.container.getContext().startActivity(i,options.toBundle());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        int fullbackdrop = 0;

        if (movies.get(position).getVoteAverage() >=5) {
            return 1;
        }

        return fullbackdrop;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        ImageView ivbackdrop;
        RelativeLayout container;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ivbackdrop = itemView.findViewById(R.id.ivbackdrop);
            container = itemView.findViewById(R.id.container);
        }
    }
}
