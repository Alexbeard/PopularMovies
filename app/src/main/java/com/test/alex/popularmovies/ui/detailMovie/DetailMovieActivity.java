package com.test.alex.popularmovies.ui.detailMovie;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;

import com.test.alex.popularmovies.App;
import com.test.alex.popularmovies.R;
import com.test.alex.popularmovies.databinding.ActivityDetailBinding;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.ui.utils.Images;

import javax.inject.Inject;


public class DetailMovieActivity extends AppCompatActivity implements DetailMovieContract.View {

    private static final String MAXIMUM_RATING = "10";

    private static final String IMAGE = "image";
    private static final String EXTRA_MOVIE = "extraMovie";

    ActivityDetailBinding binding;

    @Inject
    DetailMovieContract.Presenter presenter;

    private Movie movie;


    public static void start(@NonNull Activity activity, @NonNull View transitionImage,
                             @NonNull Movie movie) {
        Intent intent = new Intent(activity, DetailMovieActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindowForAnimation();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        ViewCompat.setTransitionName(binding.appBar, IMAGE);

        initToolbar();
        initViews();

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        DaggerDetailComponent.builder()
                .appComponent(App.getAppComponent())
                .detailModule(new DetailModule(this))
                .build().inject(this);


        presenter.searchMovieInDB(movie);
        presenter.loadGenres(movie.getGenreIds());
        showMovie(movie);

    }

    private void initViews() {
        binding.saveBtn.setOnClickListener(view -> {
            presenter.saveMovie(movie);
            showActionButton(true);
        });

        binding.deleteBtn.setOnClickListener(view -> {
            presenter.deleteMovie(movie);
            showActionButton(false);
        });
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showActionButton(boolean isMovieContainInDB) {
        if (isMovieContainInDB) {
            binding.saveBtn.setVisibility(View.GONE);
            binding.deleteBtn.setVisibility(View.VISIBLE);
        } else {
            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.deleteBtn.setVisibility(View.GONE);
        }
    }

    private void prepareWindowForAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void showMovie(@NonNull Movie movie) {
        String title = movie.getTitle();
        binding.toolbarLayout.setTitle(title);
        binding.toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.color_green));

        Images.loadMovie(binding.image, movie, Images.WIDTH_780);

        String year = movie.getReleasedDate().substring(0, 4);
        binding.contentMovie.title.setText(getString(R.string.movie_title, movie.getTitle(), year));

        binding.contentMovie.overview.setText(movie.getOverview());

        String average = String.valueOf(movie.getVoteAverage());
        average = average.length() > 3 ? average.substring(0, 3) : average;
        average = average.length() == 3 && average.charAt(2) == '0' ? average.substring(0, 1) : average;
        binding.contentMovie.rating.setText(getString(R.string.rating, average, MAXIMUM_RATING));
    }

    @Override
    public void showGenres(String genres) {
        binding.contentMovie.genres.setText(genres);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
