package com.test.alex.popularmovies.model.response;

import com.google.gson.annotations.SerializedName;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.ArrayList;
import java.util.List;


public class MoviesResponse {

    @SerializedName("results")
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        if (mMovies == null) {
            return new ArrayList<>();
        }
        return mMovies;
    }

}
