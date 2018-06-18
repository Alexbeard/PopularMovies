package com.test.alex.popularmovies.ui.searchMovies;


import com.test.alex.popularmovies.data.repository.MoviesRepository;
import com.test.alex.popularmovies.di.CustomScope;

import dagger.Module;
import dagger.Provides;


@Module
public class SearchModule {

    SearchContract.View view;

    public SearchModule(SearchContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    SearchContract.View providesSearchMovieView() {
        return view;
    }

    @Provides
    @CustomScope
    SearchContract.Presenter provideSearchPresenter(SearchContract.View view, MoviesRepository repository) {
        return new SearchPresenterImpl(view, repository);
    }
}
