package com.test.alex.popularmovies.ui.main.popularMovies;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.test.alex.popularmovies.data.repository.MoviesRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class PopularMoviesPresenterImpl implements PopularMovieContract.Presenter {

    PopularMovieContract.View view;
    MoviesRepository model;
    int page = 1;


    public PopularMoviesPresenterImpl(PopularMovieContract.View view, MoviesRepository repository) {
        this.view = view;
        model = repository;
    }

    @Override
    public void loadFirstPageMovies() {
        model.getMovies(page, null)
                .subscribe(
                        movies -> view.onLoadMoviesSuccess(movies),
                        throwable -> view.showLoading(false), //TODO add error Screen
                        () -> view.showLoading(false),
                        disposable -> view.showLoading(true)
                );

    }


    @Override
    public void loadNextPageMovies() {
        ++page;
        Log.d("TAG", "loadNextPageMovies: " + page);
        model.getMovies(page, null)
                .subscribe(
                        movies -> view.onLoadMoviesSuccess(movies),
                        throwable -> {
                            Logger.d(throwable.getMessage());
                            //TODO add error Screen
                        }

                );

    }

    @Override
    public void getGenres() {
        model.getGenres()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genres -> view.showFilterDialog(genres));
    }
}
