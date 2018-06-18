package com.test.alex.popularmovies.ui.main.favoriteMovies;


import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;


public interface FavoriteMoviesContract {
    interface View {
        void updateMovieList(List<Movie> cities);

        void showFilterDialog(List<Genre> genres);
    }

    interface Presenter {
        void getFavoriteMovies();

        void getGenres();
    }
}
