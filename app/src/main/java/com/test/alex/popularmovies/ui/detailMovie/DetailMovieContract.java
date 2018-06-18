package com.test.alex.popularmovies.ui.detailMovie;


import com.test.alex.popularmovies.model.content.Movie;

import java.util.List;


public interface DetailMovieContract {

    interface View {
         void showActionButton(boolean isMovieContainInDB);

         void showGenres(String genres);
    }

    interface Presenter {
        void saveMovie(Movie movie);

        void deleteMovie(Movie movie);

        void searchMovieInDB(Movie movie);

        void loadGenres(List<Integer> genresIndexes);
    }

}
