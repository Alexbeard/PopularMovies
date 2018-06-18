package com.test.alex.popularmovies.data.network;


import com.test.alex.popularmovies.data.api.MoviesApi;
import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.model.response.MoviesResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ServerDataImpl implements ServerData {

    MoviesApi moviesApi;

    public ServerDataImpl(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    @Override
    public Observable<List<Movie>> getMovies(Integer page, String genreIds) {
        return moviesApi.popularMovies(page, genreIds)
                .map(MoviesResponse::getMovies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Movie>> searchMovie(String query) {
        return moviesApi.searchMovies(query)
                .map(MoviesResponse::getMovies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        return moviesApi.getGenres()
                .flatMap(genreResponse -> Observable.fromArray(genreResponse.getGenres()))
                .subscribeOn(Schedulers.io());
    }
}
