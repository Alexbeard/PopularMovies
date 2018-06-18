package com.test.alex.popularmovies.ui.searchMovies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.alex.popularmovies.databinding.ItemSearchBinding;
import com.test.alex.popularmovies.model.content.Movie;
import com.test.alex.popularmovies.ui.utils.Images;

import java.util.ArrayList;
import java.util.List;


public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchViewHolder> {

    private List<Movie> movies = new ArrayList<>();


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

    public SearchMovieAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SearchMovieAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSearchBinding binding = ItemSearchBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SearchMovieAdapter.SearchViewHolder holder, int position) {
        Movie movie = movies.get(position);

        Images.loadMovie(holder.binding.image, movie, Images.WIDTH_500);
        holder.binding.title.setText(movie.getTitle());
        holder.binding.releaseDate.setText(movie.getReleasedDate());

        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(movie);

    }

    public void updateMovies(List<Movie> movies){
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        ItemSearchBinding binding;

        public SearchViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
