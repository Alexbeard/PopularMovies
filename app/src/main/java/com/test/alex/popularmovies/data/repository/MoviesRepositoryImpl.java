package com.test.alex.popularmovies.data.repository;


import com.test.alex.popularmovies.data.db.StorageData;
import com.test.alex.popularmovies.data.network.ServerData;
import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MoviesRepositoryImpl implements MoviesRepository {

    ServerData remote;
    StorageData storage;


    public MoviesRepositoryImpl(ServerData remote, StorageData storage) {
        this.remote = remote;
        this.storage = storage;
    }

    @Override
    public Observable<List<Movie>> getMovies(Integer page, String genreIds) {
        return remote.getMovies(page, genreIds);
    }

    @Override
    public Observable<List<Movie>> searchMovie(String query) {
        return remote.searchMovie(query);
    }

    @Override
    public Maybe<List<Genre>> getGenres() {
        return Observable.concat(storage.getGenres(), remote.getGenres()
                .flatMap(storage.getSaveFunc()))
                .filter(genres -> !genres.isEmpty())
                .firstElement()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<List<Movie>> getFavoriteMovies() {
        return storage.getMoviesFromDB();
    }

    @Override
    public void saveMovie(Movie movie) {
        storage.saveMovie(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        storage.deleteMovie(movie);
    }

    @Override
    public boolean searchMovieInDb(int id) {
        return storage.searchMovieInDb(id);
    }

    @Override
    public Observable<List<Genre>> getGenresByIndexes(Integer[] genresIds) {
        return storage.getGenresByIndexes(genresIds);
    }
}
