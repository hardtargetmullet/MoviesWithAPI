package com.example.meromero.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abraham Nieto on 9/1/2018.
 */

public class MoviePoster implements Parcelable {
    private final int movieId;
    final String movieName;
    final String movieOverview;
    private final double voteAverage;
    final String releaseDate;
    final String posterUrl;

    public MoviePoster(int mId, String mName, String mMovieOverview, double mVoteAverage,
                       String mReleaseDate, String mPosterUrl) {
        this.movieId = mId;
        this.movieName = mName;
        this.movieOverview = mMovieOverview;
        this.voteAverage = mVoteAverage;
        this.releaseDate = mReleaseDate;
        this.posterUrl = mPosterUrl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(movieId);
        out.writeString(movieName);
        out.writeString(movieOverview);
        out.writeDouble(voteAverage);
        out.writeString(releaseDate);
        out.writeString(posterUrl);
    }

    public static final Parcelable.Creator<MoviePoster> CREATOR
            = new Parcelable.Creator<MoviePoster>() {
        public MoviePoster createFromParcel(Parcel in) {
            return new MoviePoster(in);
        }

        public MoviePoster[] newArray(int size) {
            return new MoviePoster[size];
        }
    };

    private MoviePoster(Parcel in) {
        this.movieId = in.readInt();
        this.movieName = in.readString();
        this.movieOverview = in.readString();
        this.voteAverage = in.readDouble();
        this.releaseDate = in.readString();
        this.posterUrl = in.readString();
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieOverview() {
        return posterUrl;
    }

    public double getMovieVoteAverage() {
        return voteAverage;
    }

    public String getMovieReleaseDate() {
        return releaseDate;
    }

    public String getMoviePosterUrl() {
        return posterUrl;
    }
}
