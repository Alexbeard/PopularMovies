package com.test.alex.popularmovies.ui.searchMovies;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.test.alex.popularmovies.App;
import com.test.alex.popularmovies.R;
import com.test.alex.popularmovies.databinding.ActivitySearchBinding;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.ui.detailMovie.DetailMovieActivity;
import com.test.alex.popularmovies.ui.utils.RxSearch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class SearchActivity extends AppCompatActivity implements SearchContract.View, SearchMovieAdapter.OnItemClickListener {

    ActivitySearchBinding binding;
    @Inject
    SearchContract.Presenter presenter;
    private SearchMovieAdapter adapter;


    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        DaggerSearchComponent.builder()
                .appComponent(App.getAppComponent())
                .searchModule(new SearchModule(this))
                .build().inject(this);

        initToolbar();
        initListMovie();


    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.expandActionView();
        RxSearch.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() > 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    presenter.searchMovie(query);
                });

        return true;
    }

    @Override
    public void showLoading(boolean shown) {
        if (shown) {
            binding.progressView.setVisibility(View.VISIBLE);
        } else {
            binding.progressView.setVisibility(View.GONE);
        }
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

    private void initListMovie() {
        adapter = new SearchMovieAdapter(this);
        binding.listSearch.setLayoutManager(new LinearLayoutManager(this));
        binding.listSearch.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_up);

    }

    @Override
    public void onMovieLoadSucces(List<Movie> movies) {
        adapter.updateMovies(movies);
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Movie movie) {
        ImageView imageView = view.findViewById(R.id.image);
        DetailMovieActivity.start(this, imageView, movie);
    }
}
