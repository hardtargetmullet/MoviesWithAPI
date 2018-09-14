package com.example.meromero.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Abraham Nieto on 9/6/2018.
 */

public class ApiCaller {

    private static final String TAG = ApiCaller.class.getSimpleName();

    private static final String MY_API_KEY = "XXXXXXXXXX"; // replace "XXXXXXXXX" with your own personal key
    private static final String BASE_API_URL = "https://api.themoviedb.org/3/movie/";

    private final static String QUERY_API_PARAMETER = "api_key";
    private final static String QUERY_PARAM_POPULAR = "popular";
    private final static String QUERY_PARAM_RATING = "top_rated";


    public static URL buildUrl(String s) {
        String getMoviesBy = QUERY_PARAM_POPULAR;

        if (s.equals("top_rated")) {
            getMoviesBy = QUERY_PARAM_RATING;
        }

        Log.d("Hitting API ", BASE_API_URL + getMoviesBy);
        Uri builtUri = Uri.parse(BASE_API_URL + getMoviesBy).buildUpon()
                .appendQueryParameter(QUERY_API_PARAMETER, MY_API_KEY)
                .build();

        URL resultWebsite = null;
        try {
            resultWebsite = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "URI is built " + resultWebsite);

        return resultWebsite;
    }

    /**
     * This method returns the call from the HTTP response to the MovieDB API.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
