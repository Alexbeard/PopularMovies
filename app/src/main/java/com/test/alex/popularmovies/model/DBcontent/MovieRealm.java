package com.test.alex.popularmovies.model.DBcontent;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovieRealm extends RealmObject {

    @PrimaryKey
    private int mId;

    private String mPosterPath;

    private String mOverview;

    private String mTitle;

    private String mReleasedDate;

    private double mVoteAverage;

    private int mVoteCount;

    private RealmList<Integer> mGenres;

    public MovieRealm(int mId, String mPosterPath, String mOverview, String mTitle, String mReleasedDate, double mVoteAverage, int mVoteCount, List<Integer> mGenres) {
        this.mId = mId;
        this.mPosterPath = mPosterPath;
        this.mOverview = mOverview;
        this.mTitle = mTitle;
        this.mReleasedDate = mReleasedDate;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
        this.mGenres = new RealmList<Integer>(mGenres.toArray(new Integer[mGenres.size()]));
    }

    public MovieRealm() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleasedDate() {
        return mReleasedDate;
    }

    public void setReleasedDate(String mReleasedDate) {
        this.mReleasedDate = mReleasedDate;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public RealmList<Integer> getGenres() {
        return mGenres;
    }
}
