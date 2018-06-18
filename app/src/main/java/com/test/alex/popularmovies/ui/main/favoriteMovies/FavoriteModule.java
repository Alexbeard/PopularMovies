package com.test.alex.popularmovies.ui.main.favoriteMovies;


import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Module;
import dagger.Provides;


@Module
public class FavoriteModule {
    FavoriteMoviesContract.View view;

    public FavoriteModule(FavoriteMoviesContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    FavoriteMoviesContract.View providesSearchMovieView() {
        return view;
    }

    @Provides
    @CustomScope
    FavoriteMoviesContract.Presenter provideFavoritePresenter(FavoriteMoviesContract.View view, MoviesRepository repository) {
        return new FavoriteMoviesPresenterImpl(view, repository);
    }
}
