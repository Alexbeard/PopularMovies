package com.test.alex.popularmovies.ui.main.favoriteMovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.test.alex.popularmovies.App;
import com.test.alex.popularmovies.R;
import com.test.alex.popularmovies.databinding.FragmentFavoriteBinding;
import com.test.alex.popularmovies.model.content.Genre;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.model.filter.Filter;
import com.test.alex.popularmovies.ui.detailMovie.DetailMovieActivity;
import com.test.alex.popularmovies.ui.main.MovieAdapter;

import java.util.List;

import javax.inject.Inject;


public class FavoriteMoviesFragment extends Fragment implements FavoriteMoviesContract.View, MovieAdapter.OnItemClickListener {
    FragmentFavoriteBinding binding;

    @Inject
    FavoriteMoviesContract.Presenter presenter;
    private MovieAdapter adapter;
    private Filter filter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, parent, false);
        filter = new Filter();

        DaggerFavoriteComponent.builder()
                .appComponent(App.getAppComponent())
                .favoriteModule(new FavoriteModule(this))
                .build().inject(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initList();
        initFab();
    }

    private void initFab() {
        binding.fabFilter.setOnClickListener(view -> {
            presenter.getGenres();
        });
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


    private void initList() {
        adapter = new MovieAdapter(this);
        binding.popularList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.popularList.setAdapter(adapter);
        binding.popularList.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onItemClick(@NonNull View view, @NonNull Movie movie) {
        ImageView imageView = view.findViewById(R.id.image);
        DetailMovieActivity.start(getActivity(), imageView, movie);
    }

    @Override
    public void updateMovieList(List<Movie> movies) {
        adapter.updateMovies(movies);
    }
}
