package com.example.meromero.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String SIZE_POSTER = "w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MoviePoster moviePoster = (MoviePoster) getIntent().getParcelableExtra("movie_poster");

        if (moviePoster != null) {
            String releaseDate = moviePoster.releaseDate.split("-")[0];
            String movieRating = String.valueOf(moviePoster.getMovieVoteAverage()) + " / 10";
            String full_poster_url = BASE_URL + SIZE_POSTER + moviePoster.posterUrl;

            ImageView imageView = findViewById(R.id.detail_poster_image);
            TextView titleTextView = (TextView) findViewById(R.id.movie_details_title);
            TextView overviewTextView = (TextView) findViewById(R.id.movie_details_overview);
            TextView ratingTextView = (TextView) findViewById(R.id.movie_details_vote_average);
            TextView releaseDateTextView = (TextView) findViewById(R.id.movie_details_release_date);

            titleTextView.setText(moviePoster.movieName);
            overviewTextView.setText(moviePoster.movieOverview);
            ratingTextView.setText(movieRating);
            releaseDateTextView.setText(releaseDate);

            Picasso.with(this)
                    .load(full_poster_url)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
        }
//      } else {
//          Picasso.with(this)
//               .load(R.drawable.ic_launcher_foreground)
//               .error(R.color.colorPrimary)
//                .into(imageView);
//        }
    }
}
