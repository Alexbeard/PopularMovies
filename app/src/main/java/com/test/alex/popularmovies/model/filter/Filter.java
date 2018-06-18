package com.test.alex.popularmovies.model.filter;


import com.test.alex.popularmovies.model.content.Genre;

import java.util.ArrayList;
import java.util.List;


public class Filter {

    private Integer[] selectedIndexesOfGenre;

    public Filter() {
    }

    public void setSelectedIndexesOfGenre(Integer[] selectedIndexesOfGenre) {
        this.selectedIndexesOfGenre = selectedIndexesOfGenre;
    }

    public Integer[] getSelectedIndexesOfGenre() {
        return selectedIndexesOfGenre;
    }

    public List<Integer> getGenreIds(List<Genre> genres, Integer[] which) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < which.length; i++) {
            ids.add(genres.get(which[i]).getId());

        }
        return ids;
    }
}

