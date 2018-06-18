package com.test.alex.popularmovies.data.db;

import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


public interface StorageData {

    void saveMovie(Movie movie);

    void deleteMovie(Movie movie);

    boolean searchMovieInDb(int id);

    Flowable<List<Movie>> getMoviesFromDB();

    Observable<List<Genre>> getGenresByIndexes(Integer[] genresIds);

    Function<List<Genre>, Observable<List<Genre>>> getSaveFunc();

    Observable<List<Genre>> getGenres();

}
