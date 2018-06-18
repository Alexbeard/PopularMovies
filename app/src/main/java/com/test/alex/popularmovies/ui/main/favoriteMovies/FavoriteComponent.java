package com.test.alex.popularmovies.ui.main.favoriteMovies;


import com.test.alex.popularmovies.di.AppComponent;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Component;


@CustomScope
@Component(dependencies = AppComponent.class, modules = FavoriteModule.class)
public interface FavoriteComponent {

    void inject(FavoriteMoviesFragment favoriteMoviesFragment);

}
