package com.example.meromero.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abraham Nieto on 9/1/2018.
 */

public class MoviePosterAdapter extends ArrayAdapter<MoviePoster> {

    private final static String BASE_URL = "http://image.tmdb.org/t/p/";
    private final static String SIZE_THUMBNAIL = "w185/";

    private final Context mContext;

    public MoviePosterAdapter(Activity context, List<MoviePoster> moviePosters) {
        super(context, 0, moviePosters);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the MoviePoster object from the ArrayAdapter at the position it is at
        MoviePoster moviePoster = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        }


        String poster_name = moviePoster.getMoviePosterUrl();
        String full_path = BASE_URL + SIZE_THUMBNAIL + poster_name;

        ImageView movieImage = (ImageView) convertView.findViewById(R.id.movie_image);

        Picasso.with(mContext)
                .load(full_path)
                .fit()
                .placeholder(R.mipmap.ic_poster_placeholder)
                .error(R.drawable.ic_launcher_foreground)
                .into(movieImage);

        return convertView;
    }
}
