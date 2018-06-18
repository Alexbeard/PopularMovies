package com.test.alex.popularmovies.ui.main.popularMovies;


import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Module;
import dagger.Provides;


@Module
public class PopularModule {

        PopularMovieContract.View view;

    public PopularModule(PopularMovieContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    PopularMovieContract.View providesPopularMovieView() {
        return view;
    }

    @Provides
    @CustomScope
    PopularMovieContract.Presenter providePopularPresenter(PopularMovieContract.View view, MoviesRepository repository) {
        return new PopularMoviesPresenterImpl(view, repository);
    }
}
