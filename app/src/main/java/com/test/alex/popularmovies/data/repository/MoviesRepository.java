package com.test.alex.popularmovies.data.repository;


import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface MoviesRepository {

    Observable<List<Movie>> getMovies(Integer page, String genreIds);

    Observable<List<Movie>> searchMovie(String query);

    Maybe<List<Genre>> getGenres();

    Flowable<List<Movie>> getFavoriteMovies();

    void saveMovie(Movie movie);

    void deleteMovie(Movie movie);

    boolean searchMovieInDb(int id);

    Observable<List<Genre>> getGenresByIndexes(Integer[] genresIds);

}
