package com.test.alex.popularmovies.ui.searchMovies;

import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.ui.utils.LoadingView;

import java.util.List;


public interface SearchContract {

    interface View extends LoadingView {
        void onMovieLoadSucces(List<Movie> movies);
    }

    interface Presenter{
        void searchMovie(String query);
    }

}
