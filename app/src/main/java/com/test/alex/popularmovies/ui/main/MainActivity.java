package com.test.alex.popularmovies.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.alex.popularmovies.App;
import com.test.alex.popularmovies.R;
import com.test.alex.popularmovies.databinding.ActivityMainBinding;
import com.test.alex.popularmovies.ui.main.favoriteMovies.FavoriteMoviesFragment;
import com.test.alex.popularmovies.ui.main.popularMovies.PopularMoviesFragment;
import com.test.alex.popularmovies.ui.searchMovies.SearchActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    ActivityMainBinding binding;
    private TabsAdapter adapter;

    @Inject
    MainContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DaggerMainComponent.builder()
                .appComponent(App.getAppComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);

        presenter.checkGenres();

        initToolbar();
        initViewPager();
        initViews();

    }

    private void initViews() {
        binding.search.setOnClickListener(v -> SearchActivity.start(this));
    }


    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
        }

    }

    private void initViewPager() {
        adapter = new TabsAdapter(getSupportFragmentManager());

        adapter.addFrag(new PopularMoviesFragment(), getString(R.string.fragment_popular).toUpperCase());
        adapter.addFrag(new FavoriteMoviesFragment(), getString(R.string.fragment_favorite).toUpperCase());

        binding.viewpager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewpager);
    }

}
