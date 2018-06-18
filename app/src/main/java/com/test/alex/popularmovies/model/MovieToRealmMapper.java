package com.test.alex.popularmovies.model;


import com.test.alex.popularmovies.model.DBcontent.MovieRealm;
import com.test.alex.popularmovies.model.content.Movie;

public class MovieToRealmMapper implements Mapper<Movie, MovieRealm> {

    @Override
    public MovieRealm map(Movie movie) {
        return new MovieRealm(movie.getId(), movie.getPosterPath(), movie.getOverview(),
                movie.getTitle(), movie.getReleasedDate(), movie.getVoteAverage(), movie.getmVoteCount(), movie.getGenreIds());
    }
}
