package com.test.alex.popularmovies.data.repository;


import com.test.alex.popularmovies.data.db.StorageData;
import com.test.alex.popularmovies.data.network.ServerData;

public class RepositoryProvider {

    private static MoviesRepository sMoviesRepository;

    public static MoviesRepository getMoviesRepository(ServerData server, StorageData storage) {
        if (sMoviesRepository == null) {
            sMoviesRepository = new MoviesRepositoryImpl(server, storage);
        }
        return sMoviesRepository;
    }
}
