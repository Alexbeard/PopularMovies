package com.test.alex.popularmovies.ui.detailMovie;

import android.text.TextUtils;

import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;

import io.reactivex.Observable;


public class DetailMoviePresenterImpl implements DetailMovieContract.Presenter {

    DetailMovieContract.View view;
    MoviesRepository model;

    public DetailMoviePresenterImpl(DetailMovieContract.View view, MoviesRepository repository) {
        this.view = view;
        model = repository;

    }

    @Override
    public void saveMovie(Movie movie) {
        model.saveMovie(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        model.deleteMovie(movie);
    }

    @Override
    public void searchMovieInDB(Movie movie) {
        boolean isContains = model.searchMovieInDb(movie.getId());
        view.showActionButton(isContains);
    }

    @Override
    public void loadGenres(List<Integer> genresIndexes) {

        model.getGenresByIndexes(genresIndexes.toArray(new Integer[genresIndexes.size()]))
                .flatMap(Observable::fromIterable)
                .map(Genre::getName)
                .toList()
                .subscribe(genres ->  view.showGenres(TextUtils.join(", ", genres)));
    }
}
