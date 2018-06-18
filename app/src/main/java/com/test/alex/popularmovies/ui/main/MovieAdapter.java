package com.test.alex.popularmovies.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.alex.popularmovies.databinding.ItemMovieBinding;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.ui.utils.Images;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> rawMovies = new ArrayList<>();
    private List<Movie> shownMovies = new ArrayList<>();
    private List<Integer> genreIds = new ArrayList<>();
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(@NonNull View view, @NonNull Movie movie);
    }


    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Movie movie = (Movie) view.getTag();
            listener.onItemClick(view, movie);
        }
    };

    public MovieAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {

        Movie movie = shownMovies.get(position);
        holder.bind(movie);

        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(movie);
    }

    public void addMovies(List<Movie> movies) {
        rawMovies.addAll(movies);
        shownMovies.clear();
        shownMovies.addAll(filtration(rawMovies, genreIds));
        notifyDataSetChanged();
    }

    public void updateMovies(List<Movie> movies) {
        rawMovies.clear();
        rawMovies.addAll(movies);
        shownMovies.clear();
        shownMovies.addAll(filtration(rawMovies, genreIds));
        notifyDataSetChanged();
    }

    public void filterMovies(List<Integer> genreIds) {
        shownMovies.clear();
        this.genreIds = genreIds;
        shownMovies.addAll(filtration(rawMovies, genreIds));
        notifyDataSetChanged();
    }

    private List<Movie> filtration(List<Movie> movies, List<Integer> genreIds) {

        if (genreIds ==null || genreIds.size() == 0) {
            return movies;
        }

        List<Movie> filteredMovie = new ArrayList<>();

        for (int i = 0; i < rawMovies.size(); i++) {
            if (!Collections.disjoint(genreIds, rawMovies.get(i).getGenreIds())) {
                filteredMovie.add(movies.get(i));
            }
        }

        return filteredMovie;
    }



    @Override
    public int getItemCount() {
        return shownMovies.size();
    }

    public Movie getMovie(int position) {
        return shownMovies.get(position);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ItemMovieBinding binding;

        public MovieViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }


        public void bind(Movie movie) {
            Images.loadMovie(binding.image, movie, Images.WIDTH_780);
            binding.title.setText(movie.getTitle());
            binding.overView.setText(movie.getOverview());
        }

    }
}
