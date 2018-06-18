package com.test.alex.popularmovies.model;


import com.test.alex.popularmovies.model.DBcontent.MovieRealm;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;


public class RealmToMovieMapper implements Function<List<MovieRealm>, List<Movie>> {

    @Override
    public List<Movie> apply(List<MovieRealm> movieRealms) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieRealms.size(); i++) {
            movies.add(new Movie(movieRealms.get(i).getId(), movieRealms.get(i).getPosterPath(), movieRealms.get(i).getOverview(), movieRealms.get(i).getTitle(), movieRealms.get(i).getReleasedDate(), movieRealms.get(i).getVoteAverage(), movieRealms.get(i).getVoteCount(), movieRealms.get(i).getGenres()));
        }
        return movies;
    }
}
