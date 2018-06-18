package com.test.alex.popularmovies.ui.main;


import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {

    MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MainContract.View providesMainMovieView() {
        return view;
    }

    @Provides
    @CustomScope
    MainContract.Presenter provideMainPresenter(MainContract.View view, MoviesRepository repository) {
        return new MainPresenterImpl(view, repository);
    }
}
