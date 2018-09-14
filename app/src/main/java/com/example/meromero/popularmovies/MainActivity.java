package com.example.meromero.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.meromero.popularmovies.utils.ApiCaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private GridView mGridView;

    private MoviePosterAdapter movieAdapter;

    private MoviePoster[] moviePosters;

    private String searchMoviesBy;

    private static boolean PREFERENCES_HAVE_BEEN_UPDATED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGridView = (GridView) findViewById(R.id.movie_list);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("movie_poster", moviePosters[position]);
                startActivity(intent);
            }
        });

        setupSharedPreferences();
        loadMoviePostersData(searchMoviesBy);
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        searchMoviesBy = sharedPreferences.getString(getString(R.string.pref_order_key), "");
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PREFERENCES_HAVE_BEEN_UPDATED) {
            loadMoviePostersData(searchMoviesBy);
            PREFERENCES_HAVE_BEEN_UPDATED = false;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(getString(R.string.pref_order_key))) {
            searchMoviesBy = sharedPreferences.getString(key, "");
            PREFERENCES_HAVE_BEEN_UPDATED = true;
            loadMoviePostersData(searchMoviesBy);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if (menuItemSelected == R.id.action_search) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMoviePostersData(String order) {
        new FetchMoviePosterData().execute(order);
    }

    private MoviePoster[] getMovieInformationFromJSON(String rawJsonStr) throws JSONException {

        JSONObject moviesResultsJson = new JSONObject(rawJsonStr);

        JSONArray moviesArray = moviesResultsJson.getJSONArray("results");

        moviePosters = new MoviePoster[moviesArray.length()];
        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movie = moviesArray.getJSONObject(i);

            moviePosters[i] = new MoviePoster(
                    movie.getInt("id"),
                    movie.getString("title"),
                    movie.getString("overview"),
                    movie.getDouble("vote_average"),
                    movie.getString("release_date"),
                    movie.getString("poster_path")
            );
        }
        return moviePosters;
    }

    public class FetchMoviePosterData extends AsyncTask<String, Void, MoviePoster[]> {

        @Override
        protected MoviePoster[] doInBackground(String... s) {
            String search_movies_by = s[0];
            URL urlMoviesDbApi = ApiCaller.buildUrl(search_movies_by);

            try {
                String jsonMovieResult = ApiCaller.getResponseFromHttpUrl(urlMoviesDbApi);
                return getMovieInformationFromJSON(jsonMovieResult);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MoviePoster[] moviesData) {
            if (moviesData != null) {
                movieAdapter = new MoviePosterAdapter(MainActivity.this, Arrays.asList(moviesData));
                mGridView.setAdapter(movieAdapter);
            }
        }

    }
}
