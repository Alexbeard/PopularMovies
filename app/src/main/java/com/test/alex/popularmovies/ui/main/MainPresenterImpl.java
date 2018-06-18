package com.test.alex.popularmovies.ui.main;


import com.test.alex.popularmovies.data.repository.MoviesRepository;

public class MainPresenterImpl implements MainContract.Presenter {

    MainContract.View view;
    MoviesRepository repository;

    public MainPresenterImpl(MainContract.View view, MoviesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void checkGenres() {
        repository.getGenres()
                .subscribe();
    }
}
