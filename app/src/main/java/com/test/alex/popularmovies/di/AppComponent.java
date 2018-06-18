package com.test.alex.popularmovies.di;


import com.test.alex.popularmovies.data.repository.MoviesRepository;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {
    MoviesRepository getRepository();
}