package com.test.alex.popularmovies.ui.main.favoriteMovies;


import com.test.alex.popularmovies.data.repository.MoviesRepository;

public class FavoriteMoviesPresenterImpl implements FavoriteMoviesContract.Presenter {

    FavoriteMoviesContract.View view;
    MoviesRepository model;

    public FavoriteMoviesPresenterImpl(FavoriteMoviesContract.View view, MoviesRepository repository) {
        this.view = view;
        model = repository;
        getFavoriteMovies();
    }

    @Override
    public void getFavoriteMovies() {
        model.getFavoriteMovies()
                .subscribe(
                        movies -> view.updateMovieList(movies)
                );
    }

    @Override
    public void getGenres() {
        model.getGenres()
                .subscribe(genres -> view.showFilterDialog(genres));
    }
}
