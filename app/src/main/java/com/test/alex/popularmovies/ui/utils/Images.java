package com.test.alex.popularmovies.ui.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.test.alex.popularmovies.BuildConfig;
import com.test.alex.popularmovies.model.content.Movie;


public final class Images {

    public static final String WIDTH_780 = "w780";
    public static final String WIDTH_500 = "w500";

    private Images() {
    }

    public static void loadMovie(@NonNull ImageView imageView, @NonNull Movie movie,
                                 @NonNull String size) {
        loadMovie(imageView, movie.getPosterPath(), size);
    }

    public static void loadMovie(@NonNull ImageView imageView, @NonNull String posterPath,
                                 @NonNull String size) {
        String url = BuildConfig.IMAGES_BASE_URL + size + posterPath;
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }

}

