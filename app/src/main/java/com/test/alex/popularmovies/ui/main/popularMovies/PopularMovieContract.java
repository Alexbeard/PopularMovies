package com.test.alex.popularmovies.ui.main.popularMovies;


import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.ui.utils.LoadingView;

import java.util.List;


public interface PopularMovieContract {

    interface View extends LoadingView {

        void onLoadMoviesSuccess(List<Movie> movies);

        void showFilterDialog(List<Genre> genres);

    }

    interface Presenter {

        void loadFirstPageMovies();

        void loadNextPageMovies();

        void getGenres();

    }

}
