package com.test.alex.popularmovies.ui.detailMovie;


import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Module;
import dagger.Provides;


@Module
public class DetailModule {

    DetailMovieContract.View view;

    public DetailModule(DetailMovieContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    DetailMovieContract.View providesDetailMovieView() {
        return view;
    }

    @Provides
    @CustomScope
    DetailMovieContract.Presenter provideDetailMoviesPresenter(DetailMovieContract.View view, MoviesRepository repository) {
        return new DetailMoviePresenterImpl(view, repository);
    }
}
