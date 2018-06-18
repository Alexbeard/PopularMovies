package com.test.alex.popularmovies.model.content;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.annotations.PrimaryKey;


public class Movie implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("original_title")
    private String mTitle;

    @SerializedName("release_date")
    private String mReleasedDate;

    @SerializedName("vote_average")
    private double mVoteAverage;

    @SerializedName("vote_count")
    private int mVoteCount;

    @SerializedName("genre_ids")
    private List<Integer> mGenreIds = null;






    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getPosterPath() {
        return  mPosterPath;
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

    public void setReleasedDate(String releasedDate) {
        mReleasedDate = releasedDate;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount){
        mVoteCount = voteCount;
    }
    public int getmVoteCount(){
        return mVoteCount;
    }

    public List<Integer> getGenreIds(){
        return mGenreIds;
    }
    public void setGenreIds(List<Integer> genreIds){
        mGenreIds = genreIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mPosterPath);
        dest.writeString(this.mOverview);
        dest.writeString(this.mTitle);
        dest.writeString(this.mReleasedDate);
        dest.writeDouble(this.mVoteAverage);
        dest.writeInt(this.mVoteCount);
        dest.writeList(this.mGenreIds);
    }

    public Movie() {
    }

    public Movie(int mId, String mPosterPath, String mOverview, String mTitle, String mReleasedDate, double mVoteAverage, int mVoteCount, List<Integer> mGenres) {
        this.mId = mId;
        this.mPosterPath = mPosterPath;
        this.mOverview = mOverview;
        this.mTitle = mTitle;
        this.mReleasedDate = mReleasedDate;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
        this.mGenreIds = mGenres;
    }

    protected Movie(Parcel in) {
        this.mId = in.readInt();
        this.mPosterPath = in.readString();
        this.mOverview = in.readString();
        this.mTitle = in.readString();
        this.mReleasedDate = in.readString();
        this.mVoteAverage = in.readDouble();
        this.mVoteCount = in.readInt();
        this.mGenreIds = new ArrayList<Integer>();
        in.readList(this.mGenreIds, Integer.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
