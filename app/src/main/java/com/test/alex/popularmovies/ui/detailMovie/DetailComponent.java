package com.test.alex.popularmovies.ui.detailMovie;


import com.test.alex.popularmovies.di.AppComponent;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Component;

/**
 * Created by Aleksandr Litvinchuck on 08.11.2017.
 */
@CustomScope
@Component(dependencies = AppComponent.class, modules = DetailModule.class)
public interface DetailComponent {
    void inject(DetailMovieActivity activity);
}



