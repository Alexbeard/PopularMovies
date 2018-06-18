package com.test.alex.popularmovies.ui.main.popularMovies;


import com.test.alex.popularmovies.di.AppComponent;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Component;


@CustomScope
@Component(dependencies = AppComponent.class, modules = PopularModule.class)
public interface PopularComponent {
    void inject(PopularMoviesFragment popularMoviesFragment);
}
