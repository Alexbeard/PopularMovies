package com.test.alex.popularmovies.ui.main.popularMovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.test.alex.popularmovies.App;
import com.test.alex.popularmovies.R;
import com.test.alex.popularmovies.databinding.FragmentPopularBinding;
import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.model.filter.Filter;
import com.test.alex.popularmovies.ui.detailMovie.DetailMovieActivity;
import com.test.alex.popularmovies.ui.main.MovieAdapter;

import java.util.List;

import javax.inject.Inject;


public class PopularMoviesFragment extends Fragment implements PopularMovieContract.View,
        MovieAdapter.OnItemClickListener {

    FragmentPopularBinding binding;

    @Inject
    PopularMovieContract.Presenter presenter;

    private MovieAdapter adapter;
    private boolean isLoading = false;
    private Filter filter;


    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, parent, false);

        DaggerPopularComponent.builder()
                .appComponent(App.getAppComponent())
                .popularModule(new PopularModule(this))
                .build().inject(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initList();
        initFab();
        filter = new Filter();
        presenter.loadFirstPageMovies();
    }

    private void initFab() {
        binding.fabFilter.setOnClickListener(view -> {
            presenter.getGenres();
        });
    }

    private void initList() {

        manager = new LinearLayoutManager(getContext());
        adapter = new MovieAdapter(this);
        binding.popularList.setLayoutManager(manager);
        binding.popularList.setAdapter(adapter);
        binding.popularList.setItemAnimator(new DefaultItemAnimator());
        binding.popularList.addOnScrollListener(recyclerViewOnScrollListener);

    }

    @Override
    public void showLoading(boolean shown) {
        if (shown) {
            binding.progressView.setVisibility(View.VISIBLE);
            binding.fabFilter.hide();
        } else {
            binding.progressView.setVisibility(View.GONE);
            binding.fabFilter.show();
        }
    }


    @Override
    public void showFilterDialog(List<Genre> genres) {
        new MaterialDialog.Builder(getContext())
                .title(R.string.dialog_title)
                .items(genres)
                .itemsCallbackMultiChoice(filter.getSelectedIndexesOfGenre(),
                        (dialog, which, text) -> {
                            filter.setSelectedIndexesOfGenre(which);
                            adapter.filterMovies(filter.getGenreIds(genres, which));
                            return true;
                        })
                .positiveText(R.string.dialog_accept_btn_title)
                .negativeText(R.string.dialog_cancel_btn_title)
                .show();
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Movie movie) {
        ImageView imageView = view.findViewById(R.id.image);
        DetailMovieActivity.start(getActivity(), imageView, movie);
    }

    @Override
    public void onLoadMoviesSuccess(List<Movie> movies) {
        isLoading = false;
        adapter.addMovies(movies);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = manager.getChildCount();
            int totalItemCount = manager.getItemCount();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

            if (!isLoading)
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    presenter.loadNextPageMovies();
                    isLoading = !isLoading;
                }
        }
    };
}

