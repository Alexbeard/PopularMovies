package com.test.alex.popularmovies.data.network;


import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;

import io.reactivex.Observable;


public interface ServerData {

    Observable<List<Movie>> getMovies(Integer page, String genreIds);

    Observable<List<Movie>> searchMovie(String query);

    Observable<List<Genre>> getGenres();

}
