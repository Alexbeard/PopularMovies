package com.test.alex.popularmovies.ui.searchMovies;


import com.test.alex.popularmovies.data.repository.MoviesRepository;

public class SearchPresenterImpl implements SearchContract.Presenter {

    SearchContract.View view;
    MoviesRepository model;


    public SearchPresenterImpl(SearchContract.View view, MoviesRepository repository ) {
        this.view = view;
        model = repository;
    }

    @Override
    public void searchMovie(String query) {
        model.searchMovie(query)
                .subscribe(
                        movies -> view.onMovieLoadSucces(movies),
                        throwable -> view.showLoading(false),
                        () -> view.showLoading(false),
                        disposable -> view.showLoading(true)

                );
    }
}
