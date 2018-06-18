package com.test.alex.popularmovies.ui.searchMovies;


import com.test.alex.popularmovies.di.AppComponent;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Component;

@CustomScope
@Component(dependencies = AppComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity searchActivity);
}
