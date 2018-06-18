package com.test.alex.popularmovies.data.api;


import com.test.alex.popularmovies.model.response.GenreResponse;
import com.test.alex.popularmovies.model.response.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MoviesApi {

    @GET("discover/movie")
    Observable<MoviesResponse> popularMovies(
            @Query("page") Integer page,
            @Query("with_genres") String genresIds
    );

    @GET("search/movie")
    Observable<MoviesResponse> searchMovies(
            @Query("query") String query
    );

    @GET("genre/movie/list?")
    Observable<GenreResponse> getGenres();
}
